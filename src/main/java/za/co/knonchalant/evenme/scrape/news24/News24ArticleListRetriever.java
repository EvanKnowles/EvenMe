package za.co.knonchalant.evenme.scrape.news24;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import za.co.knonchalant.evenme.Environment;
import za.co.knonchalant.evenme.cache.CachePopulator;
import za.co.knonchalant.evenme.client.REST;
import za.co.knonchalant.evenme.scrape.ArticleListRetriever;
import za.co.knonchalant.evenme.scrape.news24.domain.ArticleResult;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class News24ArticleListRetriever implements ArticleListRetriever {
    private static final int LAST_PAGE = 2;
    private final Map<String, String> cookies;

    public News24ArticleListRetriever(Environment environment) {
        this.cookies = new HashMap<>();
        cookies.put("24cat", environment.catCookie);
        cookies.put("24uat", environment.uatCookie);
        cookies.put("24uid", environment.uidCookie);
    }

    @Override
    public List<ArticleResult> retrieveArticlesList() throws IOException {
        Map<String, String> headers = buildHeaders();
        List<ArticleResult> allResults = new LinkedList<>();

        for (int page = 1; page <= LAST_PAGE; page++) {
            String result = REST.url(buildURL(page)).headers(headers).get();

            Type type = new TypeToken<List<ArticleResult>>() {
            }.getType();

            List<ArticleResult> articleResults = new Gson().fromJson(result, type);

            articleResults.removeIf(a -> a.getTitle().startsWith("LIVE |") || a.getTitle().startsWith("BREAKING |"));
            allResults.addAll(articleResults);
        }

        return allResults;
    }

    private static String buildURL(int pageNumber) {
        return "https://cms.capi24.com/v2/Articles/category/%2Fnews24%2Fpolitics?pageNo=" + pageNumber + "&pageSize=15";
    }

    @Override
    public CachePopulator getCachePopulator() {
        return new News24Scraper(cookies);
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
}
