package za.co.knonchalant.evenme.scrape.sowetan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import za.co.knonchalant.evenme.scrape.news24.InvalidCookieException;
import za.co.knonchalant.evenme.scrape.news24.Scraper;

import java.io.IOException;

public class SowetanScraper extends Scraper {
    @Override
    public String scrape(String url) throws InvalidCookieException, IOException {
        Document document = Jsoup.connect(url).get();
        Elements select = document.select(".article-widget-text");

        return select.text();
    }
}
