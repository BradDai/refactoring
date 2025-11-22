package theater;

import java.util.List;

/**
 * Data needed to render a statement.
 */
public class StatementData {

    private final Invoice invoice;

    /**
     * Create statement data from an invoice.
     *
     * @param invoice the invoice to render
     */
    public StatementData(Invoice invoice) {
        this.invoice = invoice;
    }

    /**
     * Return the customer name.
     *
     * @return customer name
     */
    public String getCustomer() {
        return invoice.getCustomer();
    }

    /**
     * Return the list of performances.
     *
     * @return performances in this invoice
     */
    public List<Performance> getPerformances() {
        return invoice.getPerformances();
    }
}
