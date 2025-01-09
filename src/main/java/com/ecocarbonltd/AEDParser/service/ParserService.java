package com.ecocarbonltd.AEDParser.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ParserService {
    private Map<String, String> currencies = new HashMap<>();
    @Value("${aeduri}")
    private String uri;

    public ParserService() {
        this.currencies = getCurrencyMap();
    }

    public Map<String, String> getCourse(String date) {

        Map<String, String> currencyRates = new HashMap<>();

        try {

            var fullUrl = String.format(uri, date);
            Document doc = Jsoup.connect(fullUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
                    .header("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Referer", "https://www.centralbank.ae/en/forex-eibor/exchange-rates")
                    .postDataCharset("UTF8")
                    .execute()
                    .parse();

            Elements trs = doc.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                if (tds.isEmpty()) {
                    continue;
                }
                var currency = tds.get(1).text();
                var rate = tds.get(2).text();
                var cur = currencies.get(currency);
                if (cur != null) {
                    currencyRates.put(cur, rate);
                } else {
                    log.error("Cannot find currency: {}", currency);
                }
            }

        } catch (Exception ex) {
            log.error("ERROR:{}", ex.getMessage());
        }
        return currencyRates;
    }

    private Map<String, String> getCurrencyMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("US Dollar", "USD");
        map.put("Argentine Peso", "ARS");
        map.put("Australian Dollar", "AUD");
        map.put("Bangladesh Taka", "BDT");
        map.put("Bahrani Dinar", "BHD");
        map.put("Brunei Dollar", "BND");
        map.put("Brazilian Real", "BRL");
        map.put("Botswana Pula", "BWP");
        map.put("Belarus Rouble", "BYN");
        map.put("Canadian Dollar", "CAD");
        map.put("Swiss Franc", "CHF");
        map.put("Chilean Peso", "CLP");
        map.put("Chinese Yuan - Offshore", "CNH");
        map.put("Chinese Yuan", "CNY");
        map.put("Colombian Peso", "COP");
        map.put("Czech Koruna", "CZK");
        map.put("Danish Krone", "DKK");
        map.put("Algerian Dinar", "DZD");
        map.put("Egypt Pound", "EGP");
        map.put("Euro", "EUR");
        map.put("GB Pound", "GBP");
        map.put("Hongkong Dollar", "HKD");
        map.put("Hungarian Forint", "HUF");
        map.put("Indonesia Rupiah", "IDR");
        map.put("Indian Rupee", "INR");
        map.put("Iceland Krona", "ISK");
        map.put("Jordan Dinar", "JOD");
        map.put("Japanese Yen", "JPY");
        map.put("Kenya Shilling", "KES");
        map.put("Korean Won", "KRW");
        map.put("Kuwaiti Dinar", "KWD");
        map.put("Kazakhstan Tenge", "KZT");
        map.put("Lebanon Pound", "LBP");
        map.put("Sri Lanka Rupee", "LKR");
        map.put("Moroccan Dirham", "MAD");
        map.put("Macedonia Denar", "MKD");
        map.put("Mexican Peso", "MXN");
        map.put("Malaysia Ringgit", "MYR");
        map.put("Nigerian Naira", "NGN");
        map.put("Norwegian Krone", "NOK");
        map.put("NewZealand Dollar", "NZD");
        map.put("Omani Rial", "OMR");
        map.put("Peru Sol", "PEN");
        map.put("Philippine Piso", "PHP");
        map.put("Pakistan Rupee", "PKR");
        map.put("Polish Zloty", "PLN");
        map.put("Qatari Riyal", "QAR");
        map.put("Serbian Dinar", "RSD");
        map.put("Russia Rouble", "RUB");
        map.put("Saudi Riyal", "SAR");
        map.put("Sudanese Pound", "SDG");
        map.put("Swedish Krona", "SEK");
        map.put("Singapore Dollar", "SGD");
        map.put("Thai Baht", "THB");
        map.put("Tunisian Dinar", "TND");
        map.put("Turkish Lira", "TRY");
        map.put("Trin Tob Dollar", "TTD");
        map.put("Taiwan Dollar", "TWD");
        map.put("Tanzania Shilling", "TZS");
        map.put("Uganda Shilling", "UGX");
        map.put("Vietnam Dong", "VND");
        map.put("Yemen Rial", "YER");
        map.put("South Africa Rand", "ZAR");
        map.put("Zambian Kwacha", "ZMW");
        map.put("Azerbaijan manat", "AZN");
        map.put("Bulgarian lev", "BGN");
        map.put("Croatian kuna", "HRK");
        map.put("Ethiopian birr", "ETB");
        map.put("Iraqi dinar", "IQD");
        map.put("Israeli new shekel", "ILS");
        map.put("Libyan dinar", "LYD");
        map.put("Mauritian rupee", "MUR");
        map.put("Romanian leu", "RON");
        map.put("Syrian pound", "SYP");
        map.put("Turkmen manat", "TMT");
        map.put("Uzbekistani som", "UZS");
        return map;
    }
    /**
     * AED_CODES = {
     *     "US Dollar": "USD",
     *     "Argentine Peso": "ARS",
     *     "Australian Dollar": "AUD",
     *     "Bangladesh Taka": "BDT",
     *     "Bahrani Dinar": "BHD",
     *     "Brunei Dollar": "BND",
     *     "Brazilian Real": "BRL",
     *     "Botswana Pula": "BWP",
     *     "Belarus Rouble": "BYN",
     *     "Canadian Dollar": "CAD",
     *     "Swiss Franc": "CHF",
     *     "Chilean Peso": "CLP",
     *     "Chinese Yuan - Offshore": "CNH",
     *     "Chinese Yuan": "CNY",
     *     "Colombian Peso": "COP",
     *     "Czech Koruna": "CZK",
     *     "Danish Krone": "DKK",
     *     "Algerian Dinar": "DZD",
     *     "Egypt Pound": "EGP",
     *     "Euro": "EUR",
     *     "GB Pound": "GBP",
     *     "Hongkong Dollar": "HKD",
     *     "Hungarian Forint": "HUF",
     *     "Indonesia Rupiah": "IDR",
     *     "Indian Rupee": "INR",
     *     "Iceland Krona": "ISK",
     *     "Jordan Dinar": "JOD",
     *     "Japanese Yen": "JPY",
     *     "Kenya Shilling": "KES",
     *     "Korean Won": "KRW",
     *     "Kuwaiti Dinar": "KWD",
     *     "Kazakhstan Tenge": "KZT",
     *     "Lebanon Pound": "LBP",
     *     "Sri Lanka Rupee": "LKR",
     *     "Moroccan Dirham": "MAD",
     *     "Macedonia Denar": "MKD",
     *     "Mexican Peso": "MXN",
     *     "Malaysia Ringgit": "MYR",
     *     "Nigerian Naira": "NGN",
     *     "Norwegian Krone": "NOK",
     *     "NewZealand Dollar": "NZD",
     *     "Omani Rial": "OMR",
     *     "Peru Sol": "PEN",
     *     "Philippine Piso": "PHP",
     *     "Pakistan Rupee": "PKR",
     *     "Polish Zloty": "PLN",
     *     "Qatari Riyal": "QAR",
     *     "Serbian Dinar": "RSD",
     *     "Russia Rouble": "RUB",
     *     "Saudi Riyal": "SAR",
     *     "Sudanese Pound": "SDG",
     *     "Swedish Krona": "SEK",
     *     "Singapore Dollar": "SGD",
     *     "Thai Baht": "THB",
     *     "Tunisian Dinar": "TND",
     *     "Turkish Lira": "TRY",
     *     "Trin Tob Dollar": "TTD",
     *     "Taiwan Dollar": "TWD",
     *     "Tanzania Shilling": "TZS",
     *     "Uganda Shilling": "UGX",
     *     "Vietnam Dong": "VND",
     *     "Yemen Rial": "YER",
     *     "South Africa Rand": "ZAR",
     *     "Zambian Kwacha": "ZMW",
     *     "Azerbaijan manat": "AZN",
     *     "Bulgarian lev": "BGN",
     *     "Croatian kuna": "HRK",
     *     "Ethiopian birr": "ETB",
     *     "Iraqi dinar": "IQD",
     *     "Israeli new shekel": "ILS",
     *     "Libyan dinar": "LYD",
     *     "Mauritian rupee": "MUR",
     *     "Romanian leu": "RON",
     *     "Syrian pound": "SYP",
     *     "Turkmen manat": "TMT",
     *     "Uzbekistani som": "UZS",
     * }
     */
}
