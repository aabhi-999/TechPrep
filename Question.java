import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    private final String questionText;
    private final List<String> options;
    private final int correctAnswer;   // 1-based index in the ORIGINAL options list
    private final Category category;
    private final String topic;        // sub-topic within category e.g. "JVM Internals"

    // Shuffled state (rebuilt each time displayQuestion is called with shuffle=true)
    private List<String> shuffledOptions;
    private int shuffledCorrectIndex;  // 1-based index after shuffle

    public Question(String questionText, List<String> options,
                    int correctAnswer, Category category, String topic) {
        this.questionText  = questionText;
        this.options       = List.copyOf(options);
        this.correctAnswer = correctAnswer;
        this.category      = category;
        this.topic         = topic;
    }

    /**
     * Display the question, optionally shuffling options so memorised
     * option-positions don't help the user.
     */
    public void displayQuestion(int questionNumber, boolean shuffle) {
        System.out.println("\n  Q" + questionNumber + ": " + questionText);
        System.out.println(Color.DIM + "  [Topic: " + topic + "]" + Color.RESET);
        System.out.println();

        if (shuffle) {
            // Build a shuffled copy and remember where correct answer ended up
            List<String> copy = new ArrayList<>(options);
            String correctText = options.get(correctAnswer - 1);
            Collections.shuffle(copy);
            shuffledOptions = copy;
            shuffledCorrectIndex = copy.indexOf(correctText) + 1;

            for (int i = 0; i < shuffledOptions.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + shuffledOptions.get(i));
            }
        } else {
            shuffledOptions      = new ArrayList<>(options);
            shuffledCorrectIndex = correctAnswer;
            for (int i = 0; i < options.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + options.get(i));
            }
        }
    }

    /** Check whether the user's answer (1-based) is correct after display. */
    public boolean isCorrect(int userAnswer) {
        return userAnswer == shuffledCorrectIndex;
    }

    /** Reveal the correct option text (for feedback). */
    public String getCorrectOptionText() {
        if (shuffledOptions != null) {
            return shuffledOptions.get(shuffledCorrectIndex - 1);
        }
        return options.get(correctAnswer - 1);
    }

    public int getOptionCount()    { return options.size(); }
    public Category getCategory()  { return category; }
    public String getTopic()       { return topic; }
    public String getQuestionText(){ return questionText; }
}
