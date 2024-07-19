package za.co.knonchalant.evenme.scrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import za.co.knonchalant.evenme.cache.CachePopulator;

import java.io.IOException;
import java.util.Map;

public class Scraper implements CachePopulator {
    private final Map<String, String> headers;

    public Scraper(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public String populate(String url) throws InvalidNews24CookieException, IOException {
        Document document = Jsoup.connect(url).headers(headers).get();
        Elements lockedElement = document.select(".article__body--locked");
        if (!lockedElement.isEmpty()) {
            throw new InvalidNews24CookieException(url);
        }

        Elements body = document.select(".article__body");
        return body.text();
    }
}
