import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.Environment;
import za.co.knonchalant.evenme.cache.CacheException;
import za.co.knonchalant.evenme.scrape.InvalidNews24CookieException;
import za.co.knonchalant.evenme.scrape.NewNews24;

import java.io.IOException;
import java.util.Map;

public class TestNews24 {

    private static final Logger LOG = LoggerFactory.getLogger(TestNews24.class);

    public static void main(String[] args) throws IOException, InvalidNews24CookieException {
        NewNews24 news24 = new NewNews24(Environment.fromHardcode());
        try {
            Map<String, Article> articles = news24.get();
        } catch (CacheException e) {
            LOG.error("News24 cookie has expired, quitting.");
        }
    }
}
