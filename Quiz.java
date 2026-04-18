import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Quiz {

    private static final int TIME_LIMIT_SECONDS = 20;

    private final List<Question> questions;
    private final Scanner scanner;
    private final boolean shuffleOptions;
    private final boolean timedMode;

    // Analytics
    private int totalCorrect  = 0;
    private int totalAttempted = 0;
    private final Map<String, TopicResult> topicMap = new LinkedHashMap<>();
    private final List<Question> wrongAnswers = new ArrayList<>();

    public Quiz(List<Question> questions, Scanner scanner,
                boolean shuffleOptions, boolean timedMode) {
        this.questions     = questions;
        this.scanner       = scanner;
        this.shuffleOptions = shuffleOptions;
        this.timedMode     = timedMode;
    }

    // ─── Main run ─────────────────────────────────────────────────────────────

    public void run() {
        if (questions.isEmpty()) {
            System.out.println(Color.red("  No questions available for this category."));
            return;
        }

        System.out.println();
        UI.line('─', 60);
        System.out.printf("  %s%d questions%s  |  %s  |  Options: %s%n",
            Color.BOLD, questions.size(), Color.RESET,
            timedMode ? Color.yellow("⏱  Timed (" + TIME_LIMIT_SECONDS + "s each)") : Color.cyan("Untimed"),
            shuffleOptions ? "Shuffled" : "Fixed");
        UI.line('─', 60);

        for (int i = 0; i < questions.size(); i++) {
            askQuestion(questions.get(i), i + 1);
        }

        printSummary(false);
    }

    // ─── Single question ──────────────────────────────────────────────────────

    private void askQuestion(Question q, int number) {
        q.displayQuestion(number, shuffleOptions);

        int answer;
        if (timedMode) {
            answer = readAnswerWithTimer(q.getOptionCount());
        } else {
            answer = readAnswer(q.getOptionCount());
        }

        // Record result
        totalAttempted++;
        boolean correct = (answer != -1) && q.isCorrect(answer);

        topicMap.computeIfAbsent(q.getTopic(), TopicResult::new).record(correct);

        if (correct) {
            totalCorrect++;
            System.out.println("\n  " + Color.GREEN + "✅  Correct!" + Color.RESET);
        } else {
            if (answer == -1) {
                System.out.println("\n  " + Color.RED + "⏰  Time's up!" + Color.RESET);
            } else {
                System.out.println("\n  " + Color.RED + "❌  Incorrect." + Color.RESET);
            }
            System.out.println("  " + Color.DIM + "Correct answer: " + Color.RESET
                             + Color.cyan(q.getCorrectOptionText()));
            wrongAnswers.add(q);
        }

        UI.pause(800);
    }

    // ─── Input reading ────────────────────────────────────────────────────────

    private int readAnswer(int max) {
        while (true) {
            System.out.print("\n  Your answer (1-" + max + "): ");
            String line = scanner.nextLine().trim();
            try {
                int val = Integer.parseInt(line);
                if (val >= 1 && val <= max) return val;
            } catch (NumberFormatException ignored) {}
            System.out.println("  " + Color.red("  Please enter a number between 1 and " + max));
        }
    }

    /**
     * Timed input: runs a countdown thread, returns -1 if time expired.
     * Works on terminals that support line-based input.
     */
    private int readAnswerWithTimer(int max) {
        AtomicBoolean expired = new AtomicBoolean(false);

        Thread countdown = new Thread(() -> {
            try {
                Thread.sleep(TIME_LIMIT_SECONDS * 1000L);
                expired.set(true);
                // Print a newline so console isn't stuck waiting
                System.out.println();
            } catch (InterruptedException ignored) {}
        });
        countdown.setDaemon(true);
        countdown.start();

        System.out.print("\n  Your answer (1-" + max + ") [" + TIME_LIMIT_SECONDS + "s]: ");

        while (!expired.get()) {
            try {
                if (System.in.available() > 0) {
                    String line = scanner.nextLine().trim();
                    countdown.interrupt();
                    try {
                        int val = Integer.parseInt(line);
                        if (val >= 1 && val <= max) return val;
                        System.out.print("  " + Color.red("Invalid. Try again: "));
                    } catch (NumberFormatException ignored) {
                        System.out.print("  " + Color.red("Enter a number: "));
                    }
                }
                Thread.sleep(50);
            } catch (Exception ignored) {}
        }

        return -1; // timed out
    }

    // ─── Retry wrong answers ──────────────────────────────────────────────────

    public boolean hasWrongAnswers() {
        return !wrongAnswers.isEmpty();
    }

    public void retryWrongAnswers() {
        System.out.println();
        UI.line('═', 60);
        System.out.println(Color.bold("  🔁  RETRY — Missed Questions (" + wrongAnswers.size() + ")"));
        UI.line('═', 60);

        List<Question> retry = new ArrayList<>(wrongAnswers);
        Collections.shuffle(retry);
        wrongAnswers.clear();

        int reCorrect = 0;
        for (int i = 0; i < retry.size(); i++) {
            Question q = retry.get(i);
            q.displayQuestion(i + 1, shuffleOptions);
            int answer = readAnswer(q.getOptionCount());
            boolean correct = q.isCorrect(answer);
            if (correct) {
                reCorrect++;
                System.out.println("\n  " + Color.GREEN + "✅  Correct this time!" + Color.RESET);
            } else {
                System.out.println("\n  " + Color.RED + "❌  Still incorrect." + Color.RESET);
                System.out.println("  " + Color.DIM + "Correct answer: " + Color.RESET
                                 + Color.cyan(q.getCorrectOptionText()));
                wrongAnswers.add(q);
            }
            UI.pause(600);
        }

        System.out.println();
        System.out.printf("  Retry score: %s%d / %d%s%n",
            Color.BOLD, reCorrect, retry.size(), Color.RESET);
    }

    // ─── Summary ──────────────────────────────────────────────────────────────

    public void printSummary(boolean isRetry) {
        System.out.println();
        UI.line('═', 60);
        System.out.println(Color.bold("  📊  PERFORMANCE REPORT"));
        UI.line('═', 60);

        double pct = totalAttempted == 0 ? 0 : (totalCorrect * 100.0 / totalAttempted);

        System.out.printf("%n  Overall Score : %s%d / %d  (%.1f%%)%s%n%n",
            Color.BOLD, totalCorrect, totalAttempted, pct, Color.RESET);

        // Per-topic breakdown
        System.out.println(Color.bold("  Topic Breakdown:"));
        System.out.println();

        String weakestTopic = null;
        double weakestPct   = Double.MAX_VALUE;

        for (TopicResult tr : topicMap.values()) {
            System.out.printf("  %-26s %s  %s%d/%d (%.0f%%)%s%n",
                tr.getTopicName(), tr.getBar(),
                Color.DIM, tr.getCorrect(), tr.getAttempted(), tr.getPercentage(), Color.RESET);

            if (tr.getPercentage() < weakestPct) {
                weakestPct   = tr.getPercentage();
                weakestTopic = tr.getTopicName();
            }
        }

        // Readiness verdict
        System.out.println();
        UI.line('─', 60);
        if (pct >= 80) {
            System.out.println(Color.GREEN + Color.BOLD
                + "  🏆  INTERVIEW READY! Great job." + Color.RESET);
        } else if (pct >= 60) {
            System.out.println(Color.YELLOW + Color.BOLD
                + "  📚  ALMOST THERE — keep revising weak areas." + Color.RESET);
        } else {
            System.out.println(Color.RED + Color.BOLD
                + "  🔧  NEEDS WORK — focus on fundamentals." + Color.RESET);
        }

        if (weakestTopic != null && weakestPct < 100) {
            System.out.println("  " + Color.DIM + "💡  Focus area: " + Color.RESET
                             + Color.yellow(weakestTopic)
                             + Color.DIM + " (" + String.format("%.0f", weakestPct) + "% correct)" + Color.RESET);
        }

        UI.line('═', 60);
        System.out.println();
    }
}
