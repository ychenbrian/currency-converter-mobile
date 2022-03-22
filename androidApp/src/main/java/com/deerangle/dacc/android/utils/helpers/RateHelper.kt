package com.deerangle.dacc.android.utils.helpers

import android.os.StrictMode
import com.deerangle.dacc.data.model.CurrencyRate
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object RateHelper {
    fun getByCurrencyCode(code: String): CurrencyRate {
        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val doc = Jsoup.connect("https://www.kuaiyilicai.com/huilv/i-visa/usd/${code.lowercase()}.html").get()
        val anchors: Elements = doc.getElementsByAttributeValue("class", "table")
        val anchor = anchors.text().split(" ").toTypedArray()
        val rate = CurrencyRate()
        for (i in anchor.indices) {
            if (anchor[i] == "JCB") {
                rate.jcbRate = (anchor[i + 1]).toDouble()
            } else if (anchor[i] == "Visa") {
                rate.visaRate = (anchor[i + 1]).toDouble()
            } else if (anchor[i] == "MasterCard") {
                rate.mastercardRate = (anchor[i + 1]).toDouble()
            } else if (anchor[i] == "DinersClub") {
                rate.dinersClubRate = (anchor[i + 1]).toDouble()
            } else if (anchor[i] == "中国银联(人民币卡)") {
                rate.unionPayRate = (anchor[i + 1]).toDouble()
            } else {
                continue
            }
        }
        return rate
    }
}
