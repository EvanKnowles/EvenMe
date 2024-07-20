package za.co.knonchalant.evenme.cache;

import za.co.knonchalant.evenme.scrape.news24.InvalidCookieException;

import java.io.IOException;

public interface CachePopulator {
    String populate(String populateDetail) throws InvalidCookieException, IOException;
}
