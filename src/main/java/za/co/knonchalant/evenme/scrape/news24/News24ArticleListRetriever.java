package za.co.knonchalant.evenme.scrape.news24;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import za.co.knonchalant.evenme.cache.CachePopulator;
import za.co.knonchalant.evenme.client.REST;
import za.co.knonchalant.evenme.scrape.ArticleListRetriever;
import za.co.knonchalant.evenme.scrape.news24.domain.ArticleResult;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class News24ArticleListRetriever implements ArticleListRetriever {
    public static final String URL = "https://cms.capi24.com/v2/Articles/category/%2Fnews24%2Fpolitics?pageNo=1&pageSize=15";

    private final Map<String, String> cookies;

    public News24ArticleListRetriever(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    @Override
    public List<ArticleResult> retrieveArticlesList() throws IOException {
        Map<String, String> headers = buildHeaders();
        String result = REST.url(URL).headers(headers).get();

        Type type = new TypeToken<List<ArticleResult>>() {
        }.getType();

        List<ArticleResult> articleResults = new Gson().fromJson(result, type);

        articleResults.removeIf(a -> a.getTitle().startsWith("LIVE |"));

        return articleResults;
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
