package za.co.knonchalant.evenme.scrape.news24;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import za.co.knonchalant.evenme.cache.CachePopulator;

import java.io.IOException;
import java.util.Map;

public class News24Scraper implements CachePopulator {
    private final Map<String, String> cookies;

    public News24Scraper(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    @Override
    public String populate(String url) throws InvalidCookieException, IOException {
        Document document = Jsoup.connect(url).cookies(cookies).get();
        Elements lockedElement = document.select(".article__body--locked");
        if (!lockedElement.isEmpty()) {
            throw new InvalidCookieException(url);
        }

        Elements body = document.select(".article__body");
        return body.text();
    }
}
