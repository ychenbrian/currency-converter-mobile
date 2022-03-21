package com.deerangle.dacc.android.ui.home.homemain.currencypicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.deerangle.dacc.android.FragmentCurrencyPickerBinding
import com.deerangle.dacc.android.R
import com.deerangle.dacc.data.model.Currency

class CurrencyPickerDialog(
    val delegate: Delegate
) : DialogFragment() {
    interface Delegate {
        fun onCurrencySelect(currency: Currency)
    }

    private lateinit var binding: FragmentCurrencyPickerBinding
    private var currencyAdapter: CurrencyItemAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = DataBindingUtil.inflate<FragmentCurrencyPickerBinding>(
            layoutInflater,
            R.layout.fragment_currency_picker,
            null,
            false
        ).also {
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
        }.root

        currencyAdapter = CurrencyItemAdapter(object : CurrencyItemCallback {
            override fun currencyClicked(currency: Currency) {
                delegate.onCurrencySelect(currency)
                dismiss()
            }
        })

        binding.frgCurrencyPickerRvMain.apply {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.frgCurrencyPickerEtSearch.addTextChangedListener {
            val input: String = it.toString().lowercase()
            val filtered = getCurrencies().filter {
                it.code?.lowercase()?.contains(input) == true || it.name?.lowercase()?.contains(input) == true
            }
            (binding.frgCurrencyPickerRvMain.adapter as CurrencyItemAdapter).submitList(filtered)
        }

        val currencies = getCurrencies()
        (binding.frgCurrencyPickerRvMain.adapter as CurrencyItemAdapter).submitList(currencies)

        return view
    }

    fun getCurrencies(): List<Currency> {
        return listOf(
            Currency("AWG", "Aruban Florin", R.drawable.flag_awg),
            Currency("AZN", "Azerbaijanian Manat", R.drawable.flag_azn),
            Currency("BAM", "Bosnian Convertible Mark", R.drawable.flag_bam),
            Currency("BBD", "Barbados Dollar", R.drawable.flag_bbd),
            Currency("BDT", "Bangladeshi Taka", R.drawable.flag_bdt),
            Currency("BGN", "Bulgarian Lev", R.drawable.flag_bgn),
            Currency("BHD", "Bahraini Dinar", R.drawable.flag_bhd),
            Currency("BIF", "Burundi Franc", R.drawable.flag_bif),
            Currency("BMD", "Bermudian Dollar", R.drawable.flag_bmd),
            Currency("BND", "Brunei Dollar", R.drawable.flag_bnd),
            Currency("BOB", "Bolivian Boliviano", R.drawable.flag_bob),
            Currency("BRL", "Brazilian Real", R.drawable.flag_brl),
            Currency("BSD", "Bahamian Dollar", R.drawable.flag_bsd),
            Currency("BTN", "Bhutanese Ngultrum", R.drawable.flag_btn),
            Currency("BWP", "Botswana Pula", R.drawable.flag_bwp),
            Currency("BYR", "Belarussian Ruble", R.drawable.flag_byr),
            Currency("BZD", "Belize Dollar", R.drawable.flag_bzd),
            Currency("CAD", "Canadian Dollar", R.drawable.flag_cad),
            Currency("CDF", "Congolese Franc", R.drawable.flag_cdf),
            Currency("CHF", "Swiss Franc", R.drawable.flag_chf),
            Currency("CLP", "Chilean Peso", R.drawable.flag_clp),
            Currency("CNY", "Chinese Yuan Renminbi", R.drawable.flag_cny),
            Currency("COP", "Colombian Peso", R.drawable.flag_cop),
            Currency("CRC", "Costa Rican Colon", R.drawable.flag_crc),
            Currency("CVE", "Cabo Verde Escudo", R.drawable.flag_cve),
            Currency("CZK", "Czech Koruna", R.drawable.flag_czk),
            Currency("DJF", "Djibouti Franc", R.drawable.flag_djf),
            Currency("DKK", "Danish Krone", R.drawable.flag_dkk),
            Currency("DOP", "Dominican Peso", R.drawable.flag_dop),
            Currency("DZD", "Algerian Dinar", R.drawable.flag_dzd),
            Currency("EGP", "Egyptian Pound", R.drawable.flag_egp),
            Currency("ERN", "Eritrean Nakfa", R.drawable.flag_ern),
            Currency("ETB", "Ethiopian Birr", R.drawable.flag_etb),
            Currency("EUR", "Euro", R.drawable.flag_eur),
            Currency("FJD", "Fiji Dollar", R.drawable.flag_fjd),
            Currency("FKP", "Falkland Islands Pound", R.drawable.flag_fkp),
            Currency("GBP", "Pound Sterling", R.drawable.flag_gbp),
            Currency("GEL", "Georgian Lari", R.drawable.flag_gel),
            Currency("GHS", "Ghana Cedi", R.drawable.flag_ghs),
            Currency("GIP", "Gibraltar Pound", R.drawable.flag_gip),
            Currency("GMD", "Gambian Dalasi", R.drawable.flag_gmd),
            Currency("GNF", "Guinea Franc", R.drawable.flag_gnf),
            Currency("GTQ", "Guatemalan Quetzal", R.drawable.flag_gtq),
            Currency("GYD", "Guyana Dollar", R.drawable.flag_gyd),
            Currency("HKD", "Hong Kong Dollar", R.drawable.flag_hkd),
            Currency("HNL", "Honduran Lempira", R.drawable.flag_hnl),
            Currency("HRK", "Croatian Kuna", R.drawable.flag_hrk),
            Currency("HTG", "Haitian Gourde", R.drawable.flag_htg),
            Currency("HUF", "Hungarian Forint", R.drawable.flag_huf),
            Currency("IDR", "Indonesian Rupiah", R.drawable.flag_idr),
            Currency("ILS", "New Israeli Sheqel", R.drawable.flag_ils),
            Currency("INR", "Indian Rupee", R.drawable.flag_inr),
            Currency("IQD", "Iraqi Dinar", R.drawable.flag_iqd),
            Currency("IRR", "Iranian Rial", R.drawable.flag_irr),
            Currency("ISK", "Iceland Krona", R.drawable.flag_isk),
            Currency("JMD", "Jamaican Dollar", R.drawable.flag_jmd),
            Currency("JOD", "Jordanian Dinar", R.drawable.flag_jod),
            Currency("JPY", "Japanese Yen", R.drawable.flag_jpy),
            Currency("KES", "Kenyan Shilling", R.drawable.flag_kes),
            Currency("KGS", "Kyrgyzstani Som", R.drawable.flag_kgs),
            Currency("KHR", "Cambodian Riel", R.drawable.flag_khr),
            Currency("KMF", "Comoro Franc", R.drawable.flag_kmf),
            Currency("KRW", "South Korean Won", R.drawable.flag_krw),
            Currency("KWD", "Kuwaiti Dinar", R.drawable.flag_kwd),
            Currency("KYD", "Cayman Islands Dollar", R.drawable.flag_kyd),
            Currency("KZT", "Kaakhstani Tenge", R.drawable.flag_kzt),
            Currency("LAK", "Laotian Kip", R.drawable.flag_lak),
            Currency("LBP", "Lebanese Pound", R.drawable.flag_lbp),
            Currency("LKR", "Sri Lanka Rupee", R.drawable.flag_lkr),
            Currency("LRD", "Liberian Dollar", R.drawable.flag_lrd),
            Currency("LSL", "Lesotho Loti", R.drawable.flag_lsl),
            Currency("LYD", "Libyan Dinar", R.drawable.flag_lyd),
            Currency("MAD", "Moroccan Dirham", R.drawable.flag_mad),
            Currency("MDL", "Moldovan Leu", R.drawable.flag_mdl),
            Currency("MGA", "Malagasy Ariary", R.drawable.flag_mga),
            Currency("MKD", "Macedonian Denar", R.drawable.flag_mkd),
            Currency("MMK", "Myanmar Kyat", R.drawable.flag_mmk),
            Currency("MNT", "Mongolian Tugrik", R.drawable.flag_mnt),
            Currency("MOP", "Macau Pataca", R.drawable.flag_mop),
            Currency("MRO", "Mauritanian Quguiya", R.drawable.flag_mro),
            Currency("MUR", "Mauritius Rupee", R.drawable.flag_mur),
            Currency("MVR", "Maldivian Rufiyaa", R.drawable.flag_mvr),
            Currency("MWK", "Malawian Kwacha", R.drawable.flag_mwk),
            Currency("MXN", "Maxican Peso", R.drawable.flag_mxn),
            Currency("MYR", "Malaysian Ringgit", R.drawable.flag_myr),
            Currency("MZN", "Mozambique Metical", R.drawable.flag_mzn),
            Currency("NAD", "Namibia Dollar", R.drawable.flag_nad),
            Currency("NGN", "Nigerian Naira", R.drawable.flag_ngn),
            Currency("NIO", "Nicaraguan Cordoba Oro", R.drawable.flag_nio),
            Currency("NOK", "Norwegian Krone", R.drawable.flag_nok),
            Currency("NPR", "Nepalese Rupee", R.drawable.flag_npr),
            Currency("NZD", "New Zealand Dollar", R.drawable.flag_nzd),
            Currency("OMR", "Omani Rial", R.drawable.flag_omr),
            Currency("PAB", "Panama Balboa", R.drawable.flag_pab),
            Currency("PEN", "Perubian Nuevo Sol", R.drawable.flag_pen),
            Currency("PGK", "Papua New Guinean Kina", R.drawable.flag_pgk),
            Currency("PHP", "Philippine Peso", R.drawable.flag_php),
            Currency("PKR", "Pakistan Rupee", R.drawable.flag_pkr),
            Currency("PLN", "Polish Zloty", R.drawable.flag_pln),
            Currency("PYG", "Paraguayan Guarani", R.drawable.flag_pyg),
            Currency("QAR", "Qatari Rial", R.drawable.flag_qar),
            Currency("RON", "Romanian Leu", R.drawable.flag_ron),
            Currency("RSD", "Serbian Dinar", R.drawable.flag_rsd),
            Currency("RUB", "Russian Ruble", R.drawable.flag_rub),
            Currency("RWF", "Rwanda Franc", R.drawable.flag_rwf),
            Currency("SAR", "Saudi Riyal", R.drawable.flag_sar),
            Currency("SBD", "Solomon Islands Dollar", R.drawable.flag_sbd),
            Currency("SCR", "Seychelles Rupee", R.drawable.flag_scr),
            Currency("SDG", "Sudanese Pound", R.drawable.flag_sdg),
            Currency("SEK", "Swedish Krona", R.drawable.flag_sek),
            Currency("SGD", " Singapore Dollar", R.drawable.flag_sgd),
            Currency("SHP", "Saint Helena Pound", R.drawable.flag_shp),
            Currency("SLL", "Sierra Leonean Leone", R.drawable.flag_sll),
            Currency("SOS", "Somali Shilling", R.drawable.flag_sos),
            Currency("SRD", "Surinam Dollar", R.drawable.flag_srd),
            Currency("SSP", "South Sudanese Pound", R.drawable.flag_ssp),
            Currency("STD", "Sao Tomean Dobra", R.drawable.flag_std),
            Currency("SVC", "El Salvador Colon", R.drawable.flag_svc),
            Currency("SYP", "Syrian Pound", R.drawable.flag_syp),
            Currency("SZL", "Swazi Lilangeni", R.drawable.flag_szl),
            Currency("THB", "Thai Baht", R.drawable.flag_thb),
            Currency("TJS", "Tajikistani Somoni", R.drawable.flag_tjs),
            Currency("TMT", "Turkmenistan New Manat", R.drawable.flag_tmt),
            Currency("TND", "Tunisian Dinar", R.drawable.flag_tnd),
            Currency("TOP", "Tongan Pa'anga", R.drawable.flag_top),
            Currency("TRY", "Turkish Lira", R.drawable.flag_try),
            Currency("TTD", "Trinidad and Tobago Dollar", R.drawable.flag_ttd),
            Currency("TWD", "New Taiwan Dollar", R.drawable.flag_twd),
            Currency("TZS", " Tanzanian Shilling", R.drawable.flag_tzs),
            Currency("UAH", "Ukrainian Hryvnia", R.drawable.flag_uah),
            Currency("UGX", "Uganda Shilling", R.drawable.flag_ugx),
            Currency("USD", "United States Dollar", R.drawable.flag_usd),
            Currency("UYU", "Uruguayan Peso", R.drawable.flag_uyu),
            Currency("UZS", "Uzbekistan Sum", R.drawable.flag_uzs),
            Currency("VEF", "Venezuelan Bolivar", R.drawable.flag_vef),
            Currency("VND", "Vietnamese Dong", R.drawable.flag_vnd),
            Currency("VUV", "Vanuatu Vatu", R.drawable.flag_vuv),
            Currency("WST", "Samoan Tala", R.drawable.flag_wst),
            Currency("XAF", "CFA Franc BEAC", R.drawable.flag_xaf),
            Currency("XCD", "East Caribbean Dollar", R.drawable.flag_xcd),
            Currency("XOF", "CFA Franc BCEAO", R.drawable.flag_xof),
            Currency("XPF", "CFP Franc", R.drawable.flag_xpf),
            Currency("YER", "Yemeni Rial", R.drawable.flag_yer),
            Currency("ZAR", "South African Rand", R.drawable.flag_zar),
            Currency("ZMW", "Zambian Kwacha", R.drawable.flag_zmw)
        )
    }
}
