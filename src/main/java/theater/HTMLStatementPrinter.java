package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Statement printer that renders the statement as HTML.
 */
public class HTMLStatementPrinter extends StatementPrinter {

    public HTMLStatementPrinter(Invoice invoice, Map<String, Play> plays) {
        super(invoice, plays);
    }

    @Override
    public String statement() {
        final StatementData data = new StatementData(getInvoice());

        final StringBuilder result = new StringBuilder();

        // header
        result.append(String.format("<h1>Statement for %s</h1>%n", data.getCustomer()));
        result.append("<table>").append(System.lineSeparator());
        result.append(" <tr><th>play</th><th>seats</th><th>cost</th></tr>")
                .append(System.lineSeparator());

        int totalAmount = 0;
        int volumeCredits = 0;

        // table rows
        for (Performance p : data.getPerformances()) {
            final AbstractPerformanceCalculator calculator =
                    AbstractPerformanceCalculator.createPerformanceCalculator(p, getPlay(p));

            final int thisAmount = calculator.getAmount();
            volumeCredits += calculator.getVolumeCredits();

            result.append(String.format(
                    " <tr><td>%s</td><td>%d</td><td>%s</td></tr>%n",
                    calculator.getPlay().getName(),
                    p.getAudience(),
                    usd(thisAmount)));

            totalAmount += thisAmount;
        }

        result.append("</table>").append(System.lineSeparator());

        // footer
        result.append(String.format(
                "<p>Amount owed is <em>%s</em></p>%n", usd(totalAmount)));
        result.append(String.format(
                "<p>You earned <em>%d</em> credits</p>%n", volumeCredits));

        return result.toString();
    }

    /**
     * Format an amount (in cents) as US currency.
     * @param amount amount in cents
     * @return formatted US currency string
     */
    private String usd(int amount) {
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        return format.format(amount / (double) Constants.PERCENT_FACTOR);
    }
}
