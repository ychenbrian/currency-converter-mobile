package e.ychenbrian.currencyconverter.API;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/3/2018.
 */

public class ElementData {

    private String organisation[] = new String[6];
    private String rate[] = new String[6];
    private String date[] = new String[6];
    /**
     * The upper case of currency code
     */
    private String currency;
    /**
     * The total number of bank organisations.
     */
    private int numberOfBankOrganisations;

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency.toUpperCase();
    }

    /**
     * Constructor to call two internal functions.
     * One is set the currency rates with 4-6 different bank organisations,
     * the other is to set the currency code.
     *
     * @param data     the array from getByCurrencyCode method in ObtainData.java
     * @param currency the currency code
     */
    public ElementData(String data[], String currency) {
        setData(data);
        setCurrency(currency);
    }

    /**
     * Decode the data from getByCurrencyCode method, the array should be like:
     * JCB 0.00671045 (05 03 ) DinersClub 0.00670000 (05 02 ) ...
     *
     * @param data the array from getByCurrencyCode method in ObtainData.java
     */
    public void setData(String data[]) {
        //String newStr[] = new String[3];
        numberOfBankOrganisations = data.length / 3;
        for (int i = 0; i < numberOfBankOrganisations; i++) {
            organisation[i] = data[i * 3];
            rate[i] = data[i * 3 + 1];
            date[i] = data[i * 3 + 2];
        }
    }

    /**
     * Get the currency rate from different bank organisations.
     *
     * @param _organisation the name of bank organisation
     * @return the currency rate from the give bank organisation, 0.0 as default
     */
    public String getRate(String _organisation) {
        for (int i = 0; i < numberOfBankOrganisations; i++) {
            if (organisation[i].equals(_organisation)) {
                return rate[i];
            }
        }
        return "0.0";
    }
}
