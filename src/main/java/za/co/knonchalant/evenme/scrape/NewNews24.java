package za.co.knonchalant.evenme.scrape;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.Environment;
import za.co.knonchalant.evenme.REST;
import za.co.knonchalant.evenme.chatgpt.ChatGPT;
import za.co.knonchalant.evenme.chatgpt.domain.ChatGPTResponse;
import za.co.knonchalant.evenme.scrape.domain.ArticleResult;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewNews24 {
    private static final Logger LOG = LoggerFactory.getLogger(NewNews24.class);
    public static final String URL = "https://cms.capi24.com/v2/Articles/category/%2Fnews24%2Fpolitics?pageNo=1&pageSize=15";
    public static final String PROMPT =
            "Create Cypher syntax for all facts from the following news article. Only output the Cypher result, nothing else.\n" +
                    "Create statements that do not fail on create if the data already exists. Create links between nodes. \n" +
                    "Use the following terms: Event, Individual, Outcome, Company, Party, Organization.\n" +
                    "Include descriptions as attributes on nodes.\n" +
                    "Include dates where available as attributes on nodes.";

    private final Map<String, String> cookies = new HashMap<>();

    public NewNews24(Environment environment) {
        cookies.put("24cat", environment.catCookie);
        cookies.put("24uat", environment.uatCookie);
        cookies.put("24uid", environment.uidCookie);
    }

    public Map<String, Article> get() throws IOException {
        Map<String, String> headers = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : cookies.entrySet()) {
            stringBuilder.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("; ");
        }

        Path articleCache = Paths.get("articles");
        if (!articleCache.toFile().exists()) {
            articleCache.toFile().mkdirs();
        }

        Path cypherCache = Paths.get("cypher");
        if (!cypherCache.toFile().exists()) {
            cypherCache.toFile().mkdirs();
        }
        headers.put("Cookie", stringBuilder.toString());

        String result = REST.sendGet(URL, Collections.emptyMap(), headers);

        Type type = new TypeToken<List<ArticleResult>>() {
        }.getType();

        List<ArticleResult> articleResults = new Gson().fromJson(result, type);

        try {
            for (ArticleResult articleResult : articleResults) {
                String normalizedTitle = normalizeTitle(articleResult.getTitle());
                Path article = articleCache.resolve(normalizedTitle + ".html");
                String contents;
                contents = loadArticle(articleResult, article, headers);

                Path cypher = cypherCache.resolve(normalizedTitle + ".cyp");
                if (!cypher.toFile().exists()) {
                    ChatGPTResponse submit = ChatGPT.submit(PROMPT + "\n" + contents);
                    String cypherResult = submit.getChoices().get(0).getMessage().getContent();
                    Files.write(cypher, cypherResult.getBytes(StandardCharsets.UTF_8));
                    LOG.debug("Cypher'd: \"{}\"", articleResult.getTitle());
                } else {
                    LOG.debug("\"{}\" was already in cypher'd", articleResult.getTitle());
                }
            }
        } catch (InvalidNews24CookieException ignored) {
            LOG.warn("News24 cookie has expired, quitting.");
        }
        return Collections.emptyMap();
    }

    private static String loadArticle(ArticleResult articleResult, Path article, Map<String, String> headers) throws IOException, InvalidNews24CookieException {
        String contents;
        if (article.toFile().exists()) {
            LOG.debug("\"{}\" was already in cache", articleResult.getTitle());
            contents = Files.readString(article);
            return contents;
        }

        Elements body = scrapeArticle(articleResult, headers);
        contents = body.text();
        Files.write(article, contents.getBytes(StandardCharsets.UTF_8));
        LOG.info("Retrieved: \"{}\"", articleResult.getTitle());
        return contents;
    }

    private static Elements scrapeArticle(ArticleResult articleResult, Map<String, String> headers) throws IOException, InvalidNews24CookieException {
        String articleUrl = articleResult.getArticleUrl();
        Document document = Jsoup.connect(articleUrl).headers(headers).get();
        Elements lockedElement = document.select(".article__body--locked");
        if (!lockedElement.isEmpty()) {
            throw new InvalidNews24CookieException();
        }

        return document.select(".article__body");
    }

    public static String normalizeTitle(String title) {
        return title.replaceAll("[^a-zA-Z0-9 ]", "").replaceAll(" ", "_");
    }
}
