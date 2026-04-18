import java.util.*;

/**
 * TechPrep v2.0 — Modular Java Assessment Tool
 * Entry point: handles menus and session configuration.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final QuestionBank bank = new QuestionBank();

    public static void main(String[] args) {
        UI.banner();
        System.out.println("  " + Color.DIM + "Total questions loaded: "
            + bank.totalCount() + Color.RESET);
        System.out.println();

        boolean keepRunning = true;
        while (keepRunning) {
            keepRunning = mainMenu();
        }

        System.out.println();
        System.out.println("  " + Color.cyan("Thanks for using TechPrep. Good luck with your interviews! 🚀"));
        System.out.println();
        scanner.close();
    }

    // ─── Main menu ────────────────────────────────────────────────────────────

    private static boolean mainMenu() {
        UI.line('─', 60);
        System.out.println(Color.bold("  MAIN MENU"));
        UI.line('─', 60);
        System.out.println("  1. " + Category.JAVA);
        System.out.println("  2. " + Category.PYTHON);
        System.out.println("  3. " + Category.CPP);
        System.out.println("  4. Full Mixed Quiz (all categories)");
        System.out.println("  5. Exit");
        UI.line('─', 60);
        System.out.print("  Choose [1-5]: ");

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1" -> startSession(Category.JAVA);
            case "2" -> startSession(Category.PYTHON);
            case "3" -> startSession(Category.CPP);
            case "4" -> startMixedSession();
            case "5" -> { return false; }
            default  -> System.out.println(Color.red("  Invalid choice. Please enter 1-5."));
        }
        return true;
    }

    // ─── Session setup ────────────────────────────────────────────────────────

    private static void startSession(Category cat) {
        System.out.println();
        System.out.println(Color.bold("  Starting " + cat + " Quiz"));
        System.out.println();

        List<Question> questions = bank.getByCategory(cat);
        Collections.shuffle(questions);

        int count = chooseQuestionCount(questions.size());
        questions  = questions.subList(0, count);

        boolean shuffle = chooseShuffleOptions();
        boolean timed   = chooseTimedMode();

        System.out.println();
        System.out.println(Color.bold("  Press ENTER to begin..."));
        scanner.nextLine();

        Quiz quiz = new Quiz(questions, scanner, shuffle, timed);
        quiz.run();

        handleRetry(quiz);
        promptPlayAgain();
    }

    private static void startMixedSession() {
        System.out.println();
        System.out.println(Color.bold("  Starting Full Mixed Quiz"));
        System.out.println();

        List<Question> all = new ArrayList<>();
        for (Category cat : Category.values()) all.addAll(bank.getByCategory(cat));
        Collections.shuffle(all);

        int count = chooseQuestionCount(all.size());
        all = all.subList(0, count);

        boolean shuffle = chooseShuffleOptions();
        boolean timed   = chooseTimedMode();

        System.out.println();
        System.out.println(Color.bold("  Press ENTER to begin..."));
        scanner.nextLine();

        Quiz quiz = new Quiz(all, scanner, shuffle, timed);
        quiz.run();

        handleRetry(quiz);
        promptPlayAgain();
    }

    // ─── Config helpers ───────────────────────────────────────────────────────

    private static int chooseQuestionCount(int max) {
        System.out.println("  How many questions? (1-" + max + ", or press ENTER for all)");
        System.out.print("  Count: ");
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) return max;
        try {
            int n = Integer.parseInt(line);
            if (n >= 1 && n <= max) return n;
        } catch (NumberFormatException ignored) {}
        System.out.println(Color.yellow("  Invalid — using all " + max + " questions."));
        return max;
    }

    private static boolean chooseShuffleOptions() {
        System.out.print("  Shuffle answer options? (y/n, default y): ");
        String line = scanner.nextLine().trim().toLowerCase();
        return !line.equals("n");
    }

    private static boolean chooseTimedMode() {
        System.out.print("  Enable timed mode (20s per question)? (y/n, default n): ");
        String line = scanner.nextLine().trim().toLowerCase();
        return line.equals("y");
    }

    private static void handleRetry(Quiz quiz) {
        if (!quiz.hasWrongAnswers()) return;
        System.out.print("\n  You have wrong answers. Retry them now? (y/n): ");
        String line = scanner.nextLine().trim().toLowerCase();
        if (line.equals("y")) {
            quiz.retryWrongAnswers();
            if (quiz.hasWrongAnswers()) {
                System.out.println(Color.yellow("\n  Still " + "some questions to review — check those topics!"));
            } else {
                System.out.println(Color.GREEN + "\n  🎉  You answered all retries correctly!" + Color.RESET);
            }
        }
    }

    private static void promptPlayAgain() {
        System.out.print("  Back to main menu? (y/n, default y): ");
        String line = scanner.nextLine().trim().toLowerCase();
        if (line.equals("n")) {
            System.exit(0);
        }
        System.out.println();
    }
}
