package e.ychenbrian.currencyconverter.API;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/3/2018.
 */

public class OrganisationData {

    private String organisation;
    private String exchangeRate;
    private String date;

    /**
     * @return the name of bank organisation
     */
    public String getOrganisation() {
        return organisation;
    }

    /**
     * @param org the name of the bank organisation
     */
    public void setOrganisation(String org) {
        organisation = org;
    }

    /**
     * @return the currency rate based on USD
     */
    public String getRate() {
        return exchangeRate;
    }

    /**
     * @param rate the currency rate based on USD
     */
    public void setRate(String rate) {
        exchangeRate = rate;
    }

    /**
     * @return the date corresponding the currency rate
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date corresponding the currency rate
     */
    public void setDate(String date) {
        this.date = date.substring(1, 7);
    }

    /**
     * Constructor to set the currency rate in one bank organisation.
     *
     * @param org  name of organisation
     * @param rate currency rate from the organisation
     * @param date the date when obtain the currency rate
     */
    public OrganisationData(String org, String rate, String date) {
        organisation = org;
        exchangeRate = rate;
        this.date = date;
    }
}
