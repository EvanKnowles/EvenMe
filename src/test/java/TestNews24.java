import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.Environment;
import za.co.knonchalant.evenme.cache.CacheException;
import za.co.knonchalant.evenme.cache.FileBackedCache;
import za.co.knonchalant.evenme.chatgpt.cypher.ChatGPTCypherBuilder;
import za.co.knonchalant.evenme.scrape.news24.InvalidNews24CookieException;
import za.co.knonchalant.evenme.scrape.news24.ArticleProcessor;
import za.co.knonchalant.evenme.scrape.news24.News24ArticleListRetriever;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TestNews24 {

    private static final Logger LOG = LoggerFactory.getLogger(TestNews24.class);

    public static void main(String[] args) throws IOException, InvalidNews24CookieException {
        FileBackedCache cypherCache = new FileBackedCache(resolveCachePath("cypher"), new ChatGPTCypherBuilder());
        Environment environment = Environment.fromHardcode();

        Map<String, String> cookies = new HashMap<>();
        // guess we should generify these... somehow
        cookies.put("24cat", environment.catCookie);
        cookies.put("24uat", environment.uatCookie);
        cookies.put("24uid", environment.uidCookie);

        ArticleProcessor news24 = new ArticleProcessor(new News24ArticleListRetriever(cookies));

        try {
            Map<String, Article> articles = news24.get();
            for (Map.Entry<String, Article> stringArticleEntry : articles.entrySet()) {
                Article article = stringArticleEntry.getValue();
                String cypherResult = readCypherContent(cypherCache, article.getArticle(), article.getNormalized());
                LOG.info("Cypher'd: {}", article.getName());
            }

        } catch (CacheException e) {
            LOG.error("News24 cookie has expired, quitting.");
        }
    }

    private static String readCypherContent(FileBackedCache cypherCache, String articleContent, String normalizedTitle) throws IOException, InvalidNews24CookieException {
        return cypherCache.get(articleContent, normalizedTitle + ".cyp");
    }

    private static Path resolveCachePath(String cacheName) {
        Path cache = Paths.get(cacheName);
        if (!cache.toFile().exists()) {
            cache.toFile().mkdirs();
        }
        return cache;
    }
}
