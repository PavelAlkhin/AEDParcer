package com.ecocarbonltd.AEDParser.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@ConfigurationProperties
@Slf4j
public class ParserService {
    private Map<String, String> currencyRates = new HashMap<>();
    private Map<String, String> currencies = new HashMap<>();
    @Value("${aeduri}")
    private String uri;

    public ParserService() {
        this.currencies.put("USD", "دولار امريكي");
        this.currencies.put("CNY", "يوان صيني");
        this.currencies.put("EUR", "يورو");
        this.currencies.put("RUB", "روبل روسي");
        this.currencies.put("TRL", "ليرة تركية");
        this.currencies.put("UZS", "سوم أوزبكستاني");
        this.currencies.put("KZT", "تينغ كازاخستاني");
    }

    public Map<String, String> getCourse(String date) {
        try {
            var fullUrl = String.format(uri, date);
            Document doc = Jsoup.connect(fullUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
                    .header("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Accept-Encoding", "gzip,deflate,sdch")
                    .header("Accept", "text/html, */*; q=0.0")
                    .postDataCharset("UTF8")
                    .execute()
                    .parse();

            Elements trs = doc.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                if (tds.size() == 0) {
                    continue;
                }
                var currency = tds.get(1).text();
                var rate = tds.get(2).text();
                currencies.forEach((key, value) -> {
                    if (currency.equals(value)) {
                        currencyRates.put(key, rate);
                        log.info("FIND cur:{}:rate{}", key, rate);
                    }
                });
            }

        } catch (Exception ex) {
            log.error("ERROR:{}", ex);
        }
        return currencyRates;
    }
}
