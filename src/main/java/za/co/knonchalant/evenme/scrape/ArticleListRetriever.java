package za.co.knonchalant.evenme.scrape;

import za.co.knonchalant.evenme.cache.CachePopulator;
import za.co.knonchalant.evenme.scrape.news24.domain.ArticleResult;

import java.io.IOException;
import java.util.List;

public interface ArticleListRetriever {
    List<ArticleResult> retrieveArticlesList() throws IOException;
    CachePopulator getCachePopulator();
}
