package za.co.knonchalant.evenme.scrape;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.Environment;
import za.co.knonchalant.evenme.client.REST;
import za.co.knonchalant.evenme.cache.*;
import za.co.knonchalant.evenme.chatgpt.cypher.ChatGPTCypherBuilder;
import za.co.knonchalant.evenme.scrape.domain.ArticleResult;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewNews24 {
    private static final Logger LOG = LoggerFactory.getLogger(NewNews24.class);
    public static final String URL = "https://cms.capi24.com/v2/Articles/category/%2Fnews24%2Fpolitics?pageNo=1&pageSize=15";

    private final Map<String, String> cookies = new HashMap<>();

    public NewNews24(Environment environment) {
        cookies.put("24cat", environment.catCookie);
        cookies.put("24uat", environment.uatCookie);
        cookies.put("24uid", environment.uidCookie);
    }

    public Map<String, Article> get() throws IOException, InvalidNews24CookieException, CacheException {
        // Retrieve list of articles:
        List<ArticleResult> articleResults = retrieveArticlesList();

        LOG.info("Retrieved " + articleResults.size() + " articles from News24");

        FileBackedCache articleCache = new FileBackedCache(resolveCachePath("articles"), new Scraper(buildHeaders()));
        FileBackedCache cypherCache = new FileBackedCache(resolveCachePath("cypher"), new ChatGPTCypherBuilder());

        // Download each article:
        for (ArticleResult articleResult : articleResults) {
            String normalizedTitle = normalizeTitle(articleResult.getTitle());
            String articleContent = readArticleContent(articleResult, articleCache, normalizedTitle);
            String cypherResult = readCypherContent(cypherCache, articleContent, normalizedTitle);
        }

        return Collections.emptyMap();
    }

    private static String readArticleContent(ArticleResult articleResult, FileBackedCache articleCache, String normalizedTitle) throws IOException, InvalidNews24CookieException {
        return articleCache.get(articleResult.getArticleUrl(), normalizedTitle + ".xhtml");
    }

    private static String readCypherContent(FileBackedCache cypherCache, String articleContent, String normalizedTitle) throws IOException, InvalidNews24CookieException {
        return cypherCache.get(articleContent, normalizedTitle + ".cyp");
    }

    private List<ArticleResult> retrieveArticlesList() throws IOException {
        Map<String, String> headers = buildHeaders();
        String result = REST.url(URL).headers(headers).get();

        Type type = new TypeToken<List<ArticleResult>>() {
        }.getType();

        List<ArticleResult> articleResults = new Gson().fromJson(result, type);

        articleResults.removeIf(a -> a.getTitle().startsWith("LIVE |"));

        return articleResults;
    }

    private Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : cookies.entrySet()) {
            stringBuilder.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("; ");
        }

        headers.put("Cookie", stringBuilder.toString());
        return headers;
    }

    private static Path resolveCachePath(String cacheName) {
        Path cache = Paths.get(cacheName);
        if (!cache.toFile().exists()) {
            cache.toFile().mkdirs();
        }
        return cache;
    }

    private static String normalizeTitle(String title) {
        return title.replaceAll("[^a-zA-Z0-9 ]", "").replaceAll(" ", "_");
    }
}
