import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.Environment;
import za.co.knonchalant.evenme.Neo;
import za.co.knonchalant.evenme.cache.CacheException;
import za.co.knonchalant.evenme.cache.CacheRecord;
import za.co.knonchalant.evenme.cache.FileBackedCache;
import za.co.knonchalant.evenme.chatgpt.cypher.ChatGPTCypherBuilder;
import za.co.knonchalant.evenme.scrape.news24.InvalidCookieException;
import za.co.knonchalant.evenme.scrape.news24.ArticleProcessor;
import za.co.knonchalant.evenme.scrape.news24.News24ArticleListRetriever;
import za.co.knonchalant.evenme.scrape.sowetan.SowetanArticleListRetriever;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class TestNews24 {

    private static final Logger LOG = LoggerFactory.getLogger(TestNews24.class);

    public static void main(String[] args) throws IOException, InvalidCookieException {
        Environment environment = Environment.fromHardcode();

        FileBackedCache cypherCache = new FileBackedCache(resolveCachePath("cypher"), new ChatGPTCypherBuilder(environment));

        ArticleProcessor news24 = new ArticleProcessor(new News24ArticleListRetriever(environment), new SowetanArticleListRetriever());

        Neo neo = Neo.connect(environment);
        // Turn this on if you want it:
        // clearDatabase(neo);
        //repopulateAllCypherFiles(neo);

        try {
            Map<String, Article> articles = news24.get();
            for (Map.Entry<String, Article> stringArticleEntry : articles.entrySet()) {
                Article article = stringArticleEntry.getValue();
                if (!shouldIgnore(article)) {
                    CacheRecord cypherResult = readCypherContent(cypherCache, article.getArticle(), article.getNormalized());
                    if (!cypherResult.wasCacheHit() && !cypherResult.getResult().isEmpty()) {
                        persistGraphDBNodes(neo, cypherResult.getResult());
                    }
                }
            }

        } catch (CacheException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private static boolean shouldIgnore(Article article) {
        boolean b = true;  // JOHN: b = true. EVAN: b = false
        if (article.getNormalized().toUpperCase().charAt(0) >= 'N') {
            b = !b;
        }
        return b;
    }

    private static void repopulateAllCypherFiles(Neo neo) throws IOException {
        for (Path cypher : Files.find(resolveCachePath("cypher"), 1, (path, basicFileAttributes) -> path.toFile().isFile()).collect(Collectors.toList())) {
            persistGraphDBNodes(neo, Files.readString(cypher));
        }
    }

    private static void persistGraphDBNodes(Neo neo, String cypher) {
        cypher = cypher.replaceAll("```cypher[\r\n]+", "");
        cypher = cypher.replaceAll("```", "");
        if (!cypher.isEmpty()) {
            neo.run(cypher);
        }
    }

    private static void clearDatabase(Neo neo) {
        neo.run("MATCH (n) DETACH DELETE n");
        neo.run("DROP CONSTRAINT party_name");
        neo.run("DROP CONSTRAINT individual_name");
        neo.run("CREATE CONSTRAINT party_name FOR (p:Party) REQUIRE p.name IS UNIQUE");
        neo.run("CREATE CONSTRAINT individual_name FOR (p:Individual) REQUIRE p.name IS UNIQUE");
    }

    private static CacheRecord readCypherContent(FileBackedCache cypherCache, String articleContent, String normalizedTitle) throws IOException, InvalidCookieException {
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
