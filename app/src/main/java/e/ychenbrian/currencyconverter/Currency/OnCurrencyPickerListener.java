package e.ychenbrian.currencyconverter.Currency;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/7/2018.
 */

public interface OnCurrencyPickerListener {
    /**
     * The interface that to handle the selection of currency.
     *
     * @param currency the selected currency by the user
     */
    void onSelectCurrency(Currency currency);
}
