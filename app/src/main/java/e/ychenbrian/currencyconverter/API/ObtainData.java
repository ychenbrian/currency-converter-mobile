package e.ychenbrian.currencyconverter.API;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author ychenbrian
 * @version 1.0
 * @since 5/3/2018.
 */

public class ObtainData {

    /**
     * Static method to obtain the currency rate from Internet
     * by applying crawler technology from kuaiyilicai.com.
     * @param currency code name, such as "usd"
     * @return the currency rates from 4-6 different bank organisations
     */
    public static String[] getByCurrencyCode(String currency) {

        // String url = "http://www.kuaiyilicai.com/huilv/i-visa/usd/gbp.html";
        String url = "http://www.kuaiyilicai.com/huilv/i-visa/usd/" + currency + ".html";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Elements anchors = doc.getElementsByAttributeValue("class", "table");

        String anchor[] = anchors.text().split(" ");

        String subString[] = new String[(anchor.length - 1) / 8 * 3];
        int index = 0;
        for (int i = 0; i < anchor.length; i++) {
            if (anchor[i].equals("JCB") || anchor[i].equals("Visa") ||
                    anchor[i].equals("MasterCard") || anchor[i].equals("Visa_Europe") ||
                    anchor[i].equals("DinersClub") || anchor[i].equals("中国银联(人民币卡)")) {
                subString[index] = anchor[i];
                subString[index + 1] = anchor[i + 1];
                subString[index + 2] = anchor[i + 2];
                index += 3;
                //i += 4;
            }
        }
        return subString;
    }
}
