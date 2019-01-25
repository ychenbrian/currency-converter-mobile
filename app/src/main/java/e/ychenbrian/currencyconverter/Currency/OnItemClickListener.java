package e.ychenbrian.currencyconverter.Currency;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/7/2018.
 */

public interface OnItemClickListener {
    /**
     * The interface to handle the clicked item.
     * @param currency the currency that clicked by the user
     */
    void onItemClicked(Currency currency);
}
