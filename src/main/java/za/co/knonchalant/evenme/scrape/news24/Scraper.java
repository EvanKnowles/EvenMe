package za.co.knonchalant.evenme.scrape.news24;

import za.co.knonchalant.evenme.cache.CachePopulator;

import java.io.IOException;

public abstract class Scraper implements CachePopulator {
    @Override
    public final String populate(String populateDetail) throws InvalidCookieException, IOException {
        String result = scrape(populateDetail);
        rateLimit();
        return result;
    }

    protected abstract String scrape(String populateDetail) throws InvalidCookieException, IOException;

    void rateLimit() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
