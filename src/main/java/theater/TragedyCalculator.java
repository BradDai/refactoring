package theater;

/**
 * Calculator for tragedy performances.
 */
public class TragedyCalculator extends AbstractPerformanceCalculator {

    /**
     * Create a tragedy calculator.
     *
     * @param performance performance
     * @param play        play
     */
    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = Constants.TRAGEDY_BASE_AMOUNT;
        if (getPerformance().getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
            result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                    * (getPerformance().getAudience()
                    - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
        }
        return result;
    }
}
