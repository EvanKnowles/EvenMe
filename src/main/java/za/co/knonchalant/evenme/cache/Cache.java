package za.co.knonchalant.evenme.cache;

import za.co.knonchalant.evenme.scrape.news24.InvalidCookieException;

import java.io.IOException;

public abstract class Cache {
    private final CachePopulator cachePopulator;

    public Cache(CachePopulator cachePopulator) {
        this.cachePopulator = cachePopulator;
    }

    public abstract String get(String populateDetail, String title) throws IOException, InvalidCookieException;

    protected abstract String queryFromCache(String title) throws IOException;

    protected abstract String readFromUnderlyingSource(String populateDetail) throws IOException, InvalidCookieException;

    protected CachePopulator getCachePopulator() {
        return cachePopulator;
    }
}
