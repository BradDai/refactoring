package theater;

/**
 * Helper for calculating data about a single performance.
 */
public abstract class AbstractPerformanceCalculator {

    private final Performance performance;
    private final Play play;

    /**
     * Create a calculator for a performance and its play.
     *
     * @param performance the performance
     * @param play        the corresponding play
     */
    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    /**
     * Return the performance.
     *
     * @return performance
     */
    public Performance getPerformance() {
        return performance;
    }

    /**
     * Return the play.
     *
     * @return play
     */
    public Play getPlay() {
        return play;
    }

    /**
     * Factory method for creating the right calculator subclass.
     *
     * @param performance performance
     * @param play        play
     * @return a performance calculator for the given play type
     * @throws IllegalArgumentException unknown type
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(
            Performance performance, Play play) {

        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            default:
                throw new IllegalArgumentException("unknown type: " + play.getType());
        }
    }

    /**
     * Compute the amount (in cents) for this performance.
     *
     * @return amount in cents
     */
    public abstract int getAmount();

    /**
     * Compute the volume credits for this performance.
     *
     * @return volume credits
     */
    public int getVolumeCredits() {
        return Math.max(
                performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }
}
