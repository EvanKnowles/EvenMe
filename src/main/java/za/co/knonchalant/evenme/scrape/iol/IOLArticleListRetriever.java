package za.co.knonchalant.evenme.scrape.iol;

import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.cache.CachePopulator;
import za.co.knonchalant.evenme.client.REST;
import za.co.knonchalant.evenme.scrape.ArticleListRetriever;
import za.co.knonchalant.evenme.scrape.iol.domain.Content;
import za.co.knonchalant.evenme.scrape.iol.domain.IOLResponse;
import za.co.knonchalant.evenme.scrape.news24.domain.ArticleResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IOLArticleListRetriever implements ArticleListRetriever {
    public static final String BASE = "https://www.iol.co.za";
    public static final String URL = BASE + "/data/content/v3/section/iol/0/26/news/politics";

    @Override
    public List<ArticleResult> retrieveArticlesList() throws IOException {
        IOLResponse iolResponse = REST.url(URL).get(IOLResponse.class);
        ArrayList<ArticleResult> articleResults = new ArrayList<>();

        for (Content content : iolResponse.getContents()) {
            ArticleResult articleResult = new ArticleResult();
            String makeURl = content.getHeadline().replaceAll("[^a-zA-Z0-9 ]", "").replaceAll(" ", "-").toLowerCase(Locale.ROOT);
            articleResult.setArticleUrl(BASE + "/" + content.getSection() + "/" + makeURl + "-" + content.getContentKey());
            articleResult.setTitle(content.getHeadline());
            articleResults.add(articleResult);
        }

        return articleResults;
    }

    @Override
    public CachePopulator getCachePopulator() {
        return new IOLScraper();
    }
}
