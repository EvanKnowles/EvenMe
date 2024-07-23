package za.co.knonchalant.evenme.scrape.news24;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.cache.*;
import za.co.knonchalant.evenme.scrape.ArticleListRetriever;
import za.co.knonchalant.evenme.scrape.news24.domain.ArticleResult;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleProcessor.class);

    private final ArticleListRetriever[] retrievers;

    public ArticleProcessor(ArticleListRetriever... retrievers) {
        this.retrievers = retrievers;
    }

    public Map<String, Article> get() throws IOException, InvalidCookieException, CacheException {
        HashMap<String, Article> stringArticleHashMap = new HashMap<>();

        for (ArticleListRetriever retriever : retrievers) {
            List<ArticleResult> articleResults = retriever.retrieveArticlesList();

            LOG.info("Retrieved " + articleResults.size() + " articles from " + retriever.getClass().getSimpleName());

            FileBackedCache articleCache = new FileBackedCache(resolveCachePath("articles"), retriever.getCachePopulator());

            for (ArticleResult articleResult : articleResults) {
                try {
                    String normalizedTitle = normalizeTitle(articleResult.getTitle());
                    String articleContent = readArticleContent(articleResult, articleCache, normalizedTitle);
                    stringArticleHashMap.put(articleResult.getTitle(), new Article(articleResult.getTitle(), articleResult.getArticleUrl(), articleContent, normalizedTitle));
                } catch (Exception e) {
                    LOG.error(e + " \n    while reading " + articleResult.getArticleUrl());
                }
            }
        }

        return stringArticleHashMap;
    }

    private static String readArticleContent(ArticleResult articleResult, FileBackedCache articleCache, String normalizedTitle) throws IOException, InvalidCookieException {
        return articleCache.get(articleResult.getArticleUrl(), normalizedTitle + ".xhtml").getResult();
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
