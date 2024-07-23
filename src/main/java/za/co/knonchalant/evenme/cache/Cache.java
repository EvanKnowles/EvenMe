package za.co.knonchalant.evenme.cache;

import za.co.knonchalant.evenme.scrape.news24.InvalidCookieException;

import java.io.IOException;

public abstract class Cache {
    private final CachePopulator cachePopulator;

    public Cache(CachePopulator cachePopulator) {
        this.cachePopulator = cachePopulator;
    }

    public abstract CacheRecord get(String populateDetail, String title) throws IOException, InvalidCookieException;

    protected abstract CacheRecord queryFromCache(String title) throws IOException;

    protected abstract CacheRecord readFromUnderlyingSource(String populateDetail) throws IOException, InvalidCookieException;

    protected CachePopulator getCachePopulator() {
        return cachePopulator;
    }
}
