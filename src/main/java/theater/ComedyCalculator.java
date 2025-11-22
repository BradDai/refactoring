package theater;

/**
 * Calculator for comedy performances.
 */
public class ComedyCalculator extends AbstractPerformanceCalculator {

    /**
     * Create a comedy calculator.
     *
     * @param performance performance
     * @param play        play
     */
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = Constants.COMEDY_BASE_AMOUNT;
        if (getPerformance().getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
            result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                    + (Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                    * (getPerformance().getAudience()
                    - Constants.COMEDY_AUDIENCE_THRESHOLD));
        }
        result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * getPerformance().getAudience();
        return result;
    }

    @Override
    public int getVolumeCredits() {
        int result = super.getVolumeCredits();
        result += getPerformance().getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        return result;
    }
}
