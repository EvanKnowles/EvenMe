package za.co.knonchalant.evenme.scrape.iol;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import za.co.knonchalant.evenme.scrape.news24.InvalidCookieException;
import za.co.knonchalant.evenme.scrape.news24.Scraper;

import java.io.IOException;
import java.util.HashMap;

public class IOLScraper extends Scraper {
    @Override
    public String scrape(String populateDetail) throws InvalidCookieException, IOException {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Cookie", "sessionId=99e717ff-d785-4b42-ad02-58b2ef2fc451; visitorId=ztt0yauo2");
        Document document = Jsoup.connect(populateDetail)
                .headers(headers)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
                .get();
        return document.select(".article-content").text();
    }
}
