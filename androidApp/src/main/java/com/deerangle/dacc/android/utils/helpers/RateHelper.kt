package com.deerangle.dacc.android.utils.helpers

import android.os.StrictMode
import com.deerangle.dacc.data.model.Currency
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object RateHelper {
    fun getByCurrencyCode(code: String): Currency {
        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val doc = Jsoup.connect("https://www.kuaiyilicai.com/huilv/i-visa/usd/$code.html").get()
        val anchors: Elements = doc.getElementsByAttributeValue("class", "table")
        val anchor = anchors.text().split(" ").toTypedArray()
        val currency = Currency(code = code)
        for (i in anchor.indices) {
            if (anchor[i] == "JCB") {
                currency.jcbRate = (anchor[i + 1]).toDouble()
            } else if (anchor[i] == "Visa") {
                currency.visaRate = (anchor[i + 1]).toDouble()
            } else if (anchor[i] == "MasterCard") {
                currency.mastercardRate = (anchor[i + 1]).toDouble()
            } else if (anchor[i] == "DinersClub") {
                currency.dinersClubRate = (anchor[i + 1]).toDouble()
            } else if (anchor[i] == "中国银联(人民币卡)") {
                currency.unionPayRate = (anchor[i + 1]).toDouble()
            } else {
                continue
            }
        }
        return currency
    }
}
