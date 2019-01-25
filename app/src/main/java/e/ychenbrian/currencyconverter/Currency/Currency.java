package e.ychenbrian.currencyconverter.Currency;

import android.content.Context;
import android.text.TextUtils;

import java.util.Locale;

import e.ychenbrian.currencyconverter.API.ElementData;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/5/2018.
 */

public class Currency {

    private String code;
    private String name;
    private int flag;
    private ElementData rates;

    /**
     * Default constructor that did nothing
     */
    Currency() {
    }

    /**
     * Constructor to set the currency information,
     * including the currency code, name and its flag url.
     *
     * @param code the code of this currency, like "usd"
     * @param name the name of this currency, like "United State Dollar"
     * @param flag the flag of this currency's owner in integer, such as US national flag
     */
    public Currency(String code, String name, int flag) {
        this.code = code;
        this.name = name;
        this.flag = flag;
    }

    /**
     * Return the rates of this currency from 4-6 bank organisations
     *
     * @return the ElementData variable that contains the currency rates based on USD
     */
    public ElementData getRates() {
        return rates;
    }

    /**
     * Fill the currency rates with an array from getByCurrencyCode in ObtainData.java
     *
     * @param rate the array that contain all the information of the currency rates
     */
    public void setRates(String rate[]) {
        if (rates != null) {
            return;
        }
        this.rates = new ElementData(rate, code.toLowerCase());
    }

    /**
     * Return the currency code.
     *
     * @return the currency code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the currency code
     *
     * @param code the given currency code
     */
    public void setCode(String code) {
        this.code = code;
        if (TextUtils.isEmpty(name)) {
            name = new Locale("", code).getDisplayName();
        }
    }

    /**
     * Return the currency name.
     * @return the currency name
     */
    public String getName() {
        return code + ": " + name;
    }

    /**
     * Set the currency name
     * @param name the name of the currency
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the flag information in integer.
     * @return the flag information in integer
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Set the flag information in integer
     * @param flag the flag information in integer
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * Load the integer information of flag with the currency code,
     * the file name should be in the form of flag_usd.png in drawable folder.
     *
     * @param context the parameter to get the resource of flag in the drawable folder
     */
    public void loadFlagByCode(Context context) {
        if (this.flag != -1) {
            return;
        }

        // try & catch to test if there is the file that is looking for
        try {
            this.flag = context.getResources()
                    .getIdentifier("flag_" + this.code.toLowerCase(Locale.ENGLISH), "drawable",
                            context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            this.flag = -1;
        }
    }
}
