package e.ychenbrian.currencyconverter.Currency;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import e.ychenbrian.currencyconverter.R;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/7/2018.
 */

public class CurrencyPicker implements CurrencyPickerDialog.CurrencyPickerDialogInteractionListener {

    /**
     * The array to store all currencies that provided by this appliaction.
     * The total number is 153 different currencies, the information includes
     * the currency code, name & flag in integer.
     */
    private final Currency[] CURRENCIES = {
            new Currency("AED", "UAE Dirham", R.drawable.flag_aed),
            new Currency("AFN", "Afghanistan Afghani", R.drawable.flag_afn),
            new Currency("ALL", "Albanian Lek", R.drawable.flag_all),
            new Currency("AMD", "Armenian Dram", R.drawable.flag_amd),
            new Currency("ANG", "Neth. Antillean Guilder", R.drawable.flag_ang),
            new Currency("AOA", "Angolan Kwanza", R.drawable.flag_aoa),
            new Currency("ARS", "Argentine Peso", R.drawable.flag_ars),
            new Currency("AUD", "Australian Dollar", R.drawable.flag_aud),
            new Currency("AWG", "Aruban Florin", R.drawable.flag_awg),
            new Currency("AZN", "Azerbaijanian Manat", R.drawable.flag_azn),
            new Currency("BAM", "Bosnian Convertible Mark", R.drawable.flag_bam),
            new Currency("BBD", "Barbados Dollar", R.drawable.flag_bbd),
            new Currency("BDT", "Bangladeshi Taka", R.drawable.flag_bdt),
            new Currency("BGN", "Bulgarian Lev", R.drawable.flag_bgn),
            new Currency("BHD", "Bahraini Dinar", R.drawable.flag_bhd),
            new Currency("BIF", "Burundi Franc", R.drawable.flag_bif),
            new Currency("BMD", "Bermudian Dollar", R.drawable.flag_bmd),
            new Currency("BND", "Brunei Dollar", R.drawable.flag_bnd),
            new Currency("BOB", "Bolivian Boliviano", R.drawable.flag_bob),
            new Currency("BRL", "Brazilian Real", R.drawable.flag_brl),
            new Currency("BSD", "Bahamian Dollar", R.drawable.flag_bsd),
            new Currency("BTN", "Bhutanese Ngultrum", R.drawable.flag_btn),
            new Currency("BWP", "Botswana Pula", R.drawable.flag_bwp),
            new Currency("BYR", "Belarussian Ruble", R.drawable.flag_byr),
            new Currency("BZD", "Belize Dollar", R.drawable.flag_bzd),
            new Currency("CAD", "Canadian Dollar", R.drawable.flag_cad),
            new Currency("CDF", "Congolese Franc", R.drawable.flag_cdf),
            new Currency("CHF", "Swiss Franc", R.drawable.flag_chf),
            new Currency("CLP", "Chilean Peso", R.drawable.flag_clp),
            new Currency("CNY", "Chinese Yuan Renminbi", R.drawable.flag_cny),
            new Currency("COP", "Colombian Peso", R.drawable.flag_cop),
            new Currency("CRC", "Costa Rican Colon", R.drawable.flag_crc),
            new Currency("CVE", "Cabo Verde Escudo", R.drawable.flag_cve),
            new Currency("CZK", "Czech Koruna", R.drawable.flag_czk),
            new Currency("DJF", "Djibouti Franc", R.drawable.flag_djf),
            new Currency("DKK", "Danish Krone", R.drawable.flag_dkk),
            new Currency("DOP", "Dominican Peso", R.drawable.flag_dop),
            new Currency("DZD", "Algerian Dinar", R.drawable.flag_dzd),
            new Currency("EGP", "Egyptian Pound", R.drawable.flag_egp),
            new Currency("ERN", "Eritrean Nakfa", R.drawable.flag_ern),
            new Currency("ETB", "Ethiopian Birr", R.drawable.flag_etb),
            new Currency("EUR", "Euro", R.drawable.flag_eur),
            new Currency("FJD", "Fiji Dollar", R.drawable.flag_fjd),
            new Currency("FKP", "Falkland Islands Pound", R.drawable.flag_fkp),
            new Currency("GBP", "Pound Sterling", R.drawable.flag_gbp),
            new Currency("GEL", "Georgian Lari", R.drawable.flag_gel),
            new Currency("GHS", "Ghana Cedi", R.drawable.flag_ghs),
            new Currency("GIP", "Gibraltar Pound", R.drawable.flag_gip),
            new Currency("GMD", "Gambian Dalasi", R.drawable.flag_gmd),
            new Currency("GNF", "Guinea Franc", R.drawable.flag_gnf),
            new Currency("GTQ", "Guatemalan Quetzal", R.drawable.flag_gtq),
            new Currency("GYD", "Guyana Dollar", R.drawable.flag_gyd),
            new Currency("HKD", "Hong Kong Dollar", R.drawable.flag_hkd),
            new Currency("HNL", "Honduran Lempira", R.drawable.flag_hnl),
            new Currency("HRK", "Croatian Kuna", R.drawable.flag_hrk),
            new Currency("HTG", "Haitian Gourde", R.drawable.flag_htg),
            new Currency("HUF", "Hungarian Forint", R.drawable.flag_huf),
            new Currency("IDR", "Indonesian Rupiah", R.drawable.flag_idr),
            new Currency("ILS", "New Israeli Sheqel", R.drawable.flag_ils),
            new Currency("INR", "Indian Rupee", R.drawable.flag_inr),
            new Currency("IQD", "Iraqi Dinar", R.drawable.flag_iqd),
            new Currency("IRR", "Iranian Rial", R.drawable.flag_irr),
            new Currency("ISK", "Iceland Krona", R.drawable.flag_isk),
            new Currency("JMD", "Jamaican Dollar", R.drawable.flag_jmd),
            new Currency("JOD", "Jordanian Dinar", R.drawable.flag_jod),
            new Currency("JPY", "Japanese Yen", R.drawable.flag_jpy),
            new Currency("KES", "Kenyan Shilling", R.drawable.flag_kes),
            new Currency("KGS", "Kyrgyzstani Som", R.drawable.flag_kgs),
            new Currency("KHR", "Cambodian Riel", R.drawable.flag_khr),
            new Currency("KMF", "Comoro Franc", R.drawable.flag_kmf),
            new Currency("KRW", "South Korean Won", R.drawable.flag_krw),
            new Currency("KWD", "Kuwaiti Dinar", R.drawable.flag_kwd),
            new Currency("KYD", "Cayman Islands Dollar", R.drawable.flag_kyd),
            new Currency("KZT", "Kaakhstani Tenge", R.drawable.flag_kzt),
            new Currency("LAK", "Laotian Kip", R.drawable.flag_lak),
            new Currency("LBP", "Lebanese Pound", R.drawable.flag_lbp),
            new Currency("LKR", "Sri Lanka Rupee", R.drawable.flag_lkr),
            new Currency("LRD", "Liberian Dollar", R.drawable.flag_lrd),
            new Currency("LSL", "Lesotho Loti", R.drawable.flag_lsl),
            new Currency("LYD", "Libyan Dinar", R.drawable.flag_lyd),
            new Currency("MAD", "Moroccan Dirham", R.drawable.flag_mad),
            new Currency("MDL", "Moldovan Leu", R.drawable.flag_mdl),
            new Currency("MGA", "Malagasy Ariary", R.drawable.flag_mga),
            new Currency("MKD", "Macedonian Denar", R.drawable.flag_mkd),
            new Currency("MMK", "Myanmar Kyat", R.drawable.flag_mmk),
            new Currency("MNT", "Mongolian Tugrik", R.drawable.flag_mnt),
            new Currency("MOP", "Macau Pataca", R.drawable.flag_mop),
            new Currency("MRO", "Mauritanian Quguiya", R.drawable.flag_mro),
            new Currency("MUR", "Mauritius Rupee", R.drawable.flag_mur),
            new Currency("MVR", "Maldivian Rufiyaa", R.drawable.flag_mvr),
            new Currency("MWK", "Malawian Kwacha", R.drawable.flag_mwk),
            new Currency("MXN", "Maxican Peso", R.drawable.flag_mxn),
            new Currency("MYR", "Malaysian Ringgit", R.drawable.flag_myr),
            new Currency("MZN", "Mozambique Metical", R.drawable.flag_mzn),
            new Currency("NAD", "Namibia Dollar", R.drawable.flag_nad),
            new Currency("NGN", "Nigerian Naira", R.drawable.flag_ngn),
            new Currency("NIO", "Nicaraguan Cordoba Oro", R.drawable.flag_nio),
            new Currency("NOK", "Norwegian Krone", R.drawable.flag_nok),
            new Currency("NPR", "Nepalese Rupee", R.drawable.flag_npr),
            new Currency("NZD", "New Zealand Dollar", R.drawable.flag_nzd),
            new Currency("OMR", "Omani Rial", R.drawable.flag_omr),
            new Currency("PAB", "Panama Balboa", R.drawable.flag_pab),
            new Currency("PEN", "Perubian Nuevo Sol", R.drawable.flag_pen),
            new Currency("PGK", "Papua New Guinean Kina", R.drawable.flag_pgk),
            new Currency("PHP", "Philippine Peso", R.drawable.flag_php),
            new Currency("PKR", "Pakistan Rupee", R.drawable.flag_pkr),
            new Currency("PLN", "Polish Zloty", R.drawable.flag_pln),
            new Currency("PYG", "Paraguayan Guarani", R.drawable.flag_pyg),
            new Currency("QAR", "Qatari Rial", R.drawable.flag_qar),
            new Currency("RON", "Romanian Leu", R.drawable.flag_ron),
            new Currency("RSD", "Serbian Dinar", R.drawable.flag_rsd),
            new Currency("RUB", "Russian Ruble", R.drawable.flag_rub),
            new Currency("RWF", "Rwanda Franc", R.drawable.flag_rwf),
            new Currency("SAR", "Saudi Riyal", R.drawable.flag_sar),
            new Currency("SBD", "Solomon Islands Dollar", R.drawable.flag_sbd),
            new Currency("SCR", "Seychelles Rupee", R.drawable.flag_scr),
            new Currency("SDG", "Sudanese Pound", R.drawable.flag_sdg),
            new Currency("SEK", "Swedish Krona", R.drawable.flag_sek),
            new Currency("SGD", " Singapore Dollar", R.drawable.flag_sgd),
            new Currency("SHP", "Saint Helena Pound", R.drawable.flag_shp),
            new Currency("SLL", "Sierra Leonean Leone", R.drawable.flag_sll),
            new Currency("SOS", "Somali Shilling", R.drawable.flag_sos),
            new Currency("SRD", "Surinam Dollar", R.drawable.flag_srd),
            new Currency("SSP", "South Sudanese Pound", R.drawable.flag_ssp),
            new Currency("STD", "Sao Tomean Dobra", R.drawable.flag_std),
            new Currency("SVC", "El Salvador Colon", R.drawable.flag_svc),
            new Currency("SYP", "Syrian Pound", R.drawable.flag_syp),
            new Currency("SZL", "Swazi Lilangeni", R.drawable.flag_szl),
            new Currency("THB", "Thai Baht", R.drawable.flag_thb),
            new Currency("TJS", "Tajikistani Somoni", R.drawable.flag_tjs),
            new Currency("TMT", "Turkmenistan New Manat", R.drawable.flag_tmt),
            new Currency("TND", "Tunisian Dinar", R.drawable.flag_tnd),
            new Currency("TOP", "Tongan Pa'anga", R.drawable.flag_top),
            new Currency("TRY", "Turkish Lira", R.drawable.flag_try),
            new Currency("TTD", "Trinidad and Tobago Dollar", R.drawable.flag_ttd),
            new Currency("TWD", "New Taiwan Dollar", R.drawable.flag_twd),
            new Currency("TZS", " Tanzanian Shilling", R.drawable.flag_tzs),
            new Currency("UAH", "Ukrainian Hryvnia", R.drawable.flag_uah),
            new Currency("UGX", "Uganda Shilling", R.drawable.flag_ugx),
            new Currency("USD", "United States Dollar", R.drawable.flag_usd),
            new Currency("UYU", "Uruguayan Peso", R.drawable.flag_uyu),
            new Currency("UZS", "Uzbekistan Sum", R.drawable.flag_uzs),
            new Currency("VEF", "Venezuelan Bolivar", R.drawable.flag_vef),
            new Currency("VND", "Vietnamese Dong", R.drawable.flag_vnd),
            new Currency("VUV", "Vanuatu Vatu", R.drawable.flag_vuv),
            new Currency("WST", "Samoan Tala", R.drawable.flag_wst),
            new Currency("XAF", "CFA Franc BEAC", R.drawable.flag_xaf),
            new Currency("XCD", "East Caribbean Dollar", R.drawable.flag_xcd),
            new Currency("XOF", "CFA Franc BCEAO", R.drawable.flag_xof),
            new Currency("XPF", "CFP Franc", R.drawable.flag_xpf),
            new Currency("YER", "Yemeni Rial", R.drawable.flag_yer),
            new Currency("ZAR", "South African Rand", R.drawable.flag_zar),
            new Currency("ZMW", "Zambian Kwacha", R.drawable.flag_zmw),
    };

    public static final int SORT_BY_NONE = 0;
    public static final int SORT_BY_NAME = 1;
    public static final int SORT_BY_CODE = 2;
    private static final String CURRENCY_TAG = "CURRENCY_PICKER";

    private Context context;
    /**
     * The selected sort information, it can be changed into by name or code.
     */
    private int sortBy = SORT_BY_NONE;
    private OnCurrencyPickerListener onCurrencyPickerListener;
    /**
     * To enable of disable the search option.
     */
    private boolean canSearch = true;

    private List<Currency> currencies;

    /**
     * Default constructor.
     */
    private CurrencyPicker() {
    }

    /**
     * The constructor of build a currency picker to pick the currency
     * that selected by the user with the Builder.
     *
     * @param builder the builder for the users to select currencies
     */
    CurrencyPicker(Builder builder) {
        sortBy = builder.sortBy;
        if (builder.onCurrencyPickerListener != null) {
            onCurrencyPickerListener = builder.onCurrencyPickerListener;
        }
        context = builder.context;
        canSearch = builder.canSearch;
        currencies = new ArrayList<>(Arrays.asList(CURRENCIES));
        sortCurrencies(currencies);
    }

    /**
     * Sort the currencies in a given method, by its name or by its code.
     *
     * @param currencies the array of the currencies that need to be sorted
     */
    public void sortCurrencies(@NonNull List<Currency> currencies) {
        switch (sortBy) {
            case SORT_BY_NAME:
                Collections.sort(currencies, new Comparator<Currency>() {
                    @Override
                    public int compare(Currency currency1, Currency currency2) {
                        return currency1.getName().trim().compareToIgnoreCase(currency2.getName().trim());
                    }
                });
            case SORT_BY_CODE:
                Collections.sort(currencies, new Comparator<Currency>() {
                    @Override
                    public int compare(Currency currency1, Currency currency2) {
                        return currency1.getCode().trim().compareToIgnoreCase(currency2.getCode().trim());
                    }
                });
        }
    }

    /**
     * Return all currencies that listed in above.
     *
     * @return all currency variables
     */
    public List<Currency> getAllCurrencies() {
        return currencies;
    }

    /**
     * Return the status of the search option.
     *
     * @return true means the search is enabled, false for disabled
     */
    public boolean canSearch() {
        return canSearch;
    }

    /**
     * Show the dialog that for the search of currencies.
     *
     * @param supportFragmentManager the fragment manager for the currency search
     */
    public void showDialog(@NonNull FragmentManager supportFragmentManager) {
        if (currencies == null || currencies.isEmpty()) {
            throw new IllegalArgumentException(context.getString(R.string.error_no_currencies_found));
        } else {
            CurrencyPickerDialog currencyPickerDialog = CurrencyPickerDialog.newInstance();
            if (onCurrencyPickerListener != null) {
                currencyPickerDialog.setCurrencyPickerListener(onCurrencyPickerListener);
            }
            currencyPickerDialog.setDialogInteractionListener(this);
            currencyPickerDialog.show(supportFragmentManager, CURRENCY_TAG);
        }
    }

    /**
     * Set the currencies to the currency picker.
     *
     * @param currencies an array of currencies that need to be set into this currency picker
     */
    public void setCurrencies(@NonNull List<Currency> currencies) {
        this.currencies.clear();
        this.currencies.addAll(currencies);
        sortCurrencies(this.currencies);
    }

    /**
     * Return the Currency object with the given name.
     *
     * @param currencyName the name of currency that need to be returned
     * @return the required currency
     */
    public Currency getCurrencyByName(@NonNull String currencyName) {
        currencyName = currencyName.toUpperCase();
        Currency currency = new Currency();
        currency.setName(currencyName);
        int i = Arrays.binarySearch(CURRENCIES, currency, new NameComparator());
        if (i < 0) {
            return null;
        } else {
            return CURRENCIES[i];
        }
    }

    /**
     * Static class to build the Builder for the currency selection
     * and search fragment, first is to display all currencies with
     * the give sort method, then use the listener to check which currency
     * was selected by the user.
     */
    public static class Builder {
        private Context context;
        private int sortBy = SORT_BY_NONE;
        private boolean canSearch = true;
        private OnCurrencyPickerListener onCurrencyPickerListener;

        public Builder with(@NonNull Context context) {
            this.context = context;
            return this;
        }

        public Builder sortBy(@NonNull int sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public Builder listener(@NonNull OnCurrencyPickerListener onCurrencyPickerListener) {
            this.onCurrencyPickerListener = onCurrencyPickerListener;
            return this;
        }

        public Builder canSearch(@NonNull boolean canSearch) {
            this.canSearch = canSearch;
            return this;
        }

        public CurrencyPicker build() {
            return new CurrencyPicker(this);
        }
    }


    /**
     * The comparator to compare two currency that may be used in the sort of currencies.
     */
    public static class NameComparator implements Comparator<Currency> {
        @Override
        public int compare(Currency currency, Currency nextCurrency) {
            return currency.getCode().compareTo(nextCurrency.getCode());
        }
    }
}
