package za.co.knonchalant.evenme.cache;

import za.co.knonchalant.evenme.scrape.InvalidNews24CookieException;

import java.io.IOException;

public interface CachePopulator {
    String populate(String populateDetail) throws InvalidNews24CookieException, IOException;
}
