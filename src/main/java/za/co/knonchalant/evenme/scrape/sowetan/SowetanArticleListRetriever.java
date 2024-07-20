package za.co.knonchalant.evenme.scrape.sowetan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import za.co.knonchalant.evenme.cache.CachePopulator;
import za.co.knonchalant.evenme.client.REST;
import za.co.knonchalant.evenme.scrape.ArticleListRetriever;
import za.co.knonchalant.evenme.scrape.news24.domain.ArticleResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SowetanArticleListRetriever implements ArticleListRetriever {
    public static final String BASE_URL = "https://www.sowetanlive.co.za";
    private static final String URL = BASE_URL + "/news/?limit=15&page=1&partial=true";

    @Override
    public List<ArticleResult> retrieveArticlesList() throws IOException {
        ArticleList articleList = REST.url(URL).get(ArticleList.class);
        Document parse = Jsoup.parse(articleList.getHtml());

        ArrayList<ArticleResult> articleResults = new ArrayList<ArticleResult>();
        Elements articles = parse.select("a.article-image");
        for (Element article : articles) {
            ArticleResult articleResult = new ArticleResult();
            articleResult.setArticleUrl(BASE_URL + article.attr("href"));
            articleResult.setTitle(article.attr("title"));

            if (!articleResult.getTitle().startsWith("WATCH |")) {
                articleResults.add(articleResult);
            }
        }

        return articleResults;
    }

    @Override
    public CachePopulator getCachePopulator() {
        return new SowetanScraper();
    }
}
