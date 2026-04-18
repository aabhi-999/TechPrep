/**
 * Tracks score per sub-topic (e.g. "JVM Internals", "Collections").
 */
public class TopicResult {

    private final String topicName;
    private int attempted = 0;
    private int correct   = 0;

    public TopicResult(String topicName) {
        this.topicName = topicName;
    }

    public void record(boolean wasCorrect) {
        attempted++;
        if (wasCorrect) correct++;
    }

    public String getTopicName() { return topicName; }
    public int getAttempted()    { return attempted; }
    public int getCorrect()      { return correct; }

    public double getPercentage() {
        return attempted == 0 ? 0 : (correct * 100.0 / attempted);
    }

    public String getBar() {
        int filled = (int) Math.round(getPercentage() / 5); // 0-20 blocks
        String bar = "█".repeat(filled) + "░".repeat(20 - filled);
        String color = getPercentage() >= 70 ? Color.GREEN
                     : getPercentage() >= 40 ? Color.YELLOW
                     : Color.RED;
        return color + bar + Color.RESET;
    }
}
