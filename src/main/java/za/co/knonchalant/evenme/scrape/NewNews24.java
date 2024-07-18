package za.co.knonchalant.evenme.scrape;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.REST;
import za.co.knonchalant.evenme.chatgpt.ChatGPT;
import za.co.knonchalant.evenme.chatgpt.domain.ChatGPTResponse;
import za.co.knonchalant.evenme.scrape.domain.ArticleResult;

import java.io.File;
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
    public static final String URL = "https://cms.capi24.com/v2/Articles/category/%2Fnews24%2Fpolitics?pageNo=1&pageSize=15";
    private static final Map<String, String> COOKIES = new HashMap<>();
    public static final String PROMPT =
            "Create Cypher syntax for all facts from the following news article. Only output the Cypher result, nothing else.\n" +
                    "Create statements that do not fail on create if the data already exists. Create links between nodes. \n" +
                    "Use the following terms: Event, Individual, Outcome, Company, Party, Organization.\n" +
                    "Include descriptions as attributes on nodes.\n" +
                    "Include dates where available as attributes on nodes.";

    static {
        COOKIES.put("24cat", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MjEzMDk5NzksIm5iZiI6MTcyMTMwMjc3OSwiZXhwIjoxNzIxMzA2Mzc3LCJ1c2VyIjp7ImlkIjoiWEpzc0NIeXlIdVgwMUFlRFpjU29OVTkxV1UwMyIsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwidXNlcm5hbWUiOiJFdmFuIEtub3dsZXMifX0.wzED6yi4_4mhQOlakJRgHPpRCv_P3X7QBA65FnLOD5U");
        COOKIES.put("24uat", "eyJhbGciOiJSUzI1NiIsImtpZCI6ImMxNTQwYWM3MWJiOTJhYTA2OTNjODI3MTkwYWNhYmU1YjA1NWNiZWMiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRXZhbiBKYW1lcyBLbm93bGVzIiwicGljdHVyZSI6Imh0dHBzOi8vZ3JhcGguZmFjZWJvb2suY29tLzg1NjE0MDExMS9waWN0dXJlIiwidGZfYm9va21hcmtzIjp0cnVlLCJ0Zl90cmFmZmljX2FsbCI6dHJ1ZSwidGZfd2VhdGhlcl9hbGwiOnRydWUsInRmX25ld3NsZXR0ZXJzX2ZyZWUiOnRydWUsInRmX3JlZ2lzdGVyZWQiOnRydWUsInRmX2NvbW1lbnRzIjp0cnVlLCJ0Zl9hcnRpY2xlX2F1ZGlvIjp0cnVlLCJ0Zl9wZGYiOnRydWUsInRmX2FydGljbGVzIjp0cnVlLCJ0Zl9uZXdzbGV0dGVyc19hbGwiOnRydWUsInRmX3N1YnNjcmliZWQiOnRydWUsInRmX3VzZXJUeXBlIjozLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdHdlbnR5Zm91ci1lbmdsaXNoLWxpdmUiLCJhdWQiOiJ0d2VudHlmb3VyLWVuZ2xpc2gtbGl2ZSIsImF1dGhfdGltZSI6MTcyMTI5MjI3MSwidXNlcl9pZCI6IlhKc3NDSHl5SHVYMDFBZURaY1NvTlU5MVdVMDMiLCJzdWIiOiJYSnNzQ0h5eUh1WDAxQWVEWmNTb05VOTFXVTAzIiwiaWF0IjoxNzIxMzAyNzc3LCJleHAiOjE3MjEzMDYzNzcsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsicGlyYXRlYW5nZWxAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.kQwgJ9tgYXWueaJiwTlOPUFX_DBNBe01j_7MgNuemvAWMS32CNTAnwtgysFKGjNP2lRl_CWuitNIQVy_eDXCdcRXdHYQ-ZouPjiWOL_ViYXb0xZlT5r9N1y_zrrIq5BdnlDKM6LheRQGLtQNkM9L3a2ttGlKG16S9oKe_DocDEa2cojZpIO7TLQG4bdAZljkmLi0K0Ob7vAB7oTD_3Z45kTYR09ySYU-CuJuT47MurHn3MP1DlbKwFfcuyttqsdG17TTXZZH0TBMdbW_COFsSJQr06YUuBlj1eGvzTewBPw4RTkNJeckcB7V3mw5eooRA3WJFGbvDxW1Vbp9ZvGN2w");
        COOKIES.put("24uid", "7496c1ea-4eb1-4450-9214-9d46ab4fba2a");
    }

    public static Map<String, Article> get() throws IOException {
        Map<String, Article> map = new HashMap<String, Article>();
        Map<String, String> headers = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : COOKIES.entrySet()) {
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

        for (ArticleResult articleResult : articleResults) {
            String normalizedTitle = normalizeTitle(articleResult.getTitle());
            Path article = articleCache.resolve(normalizedTitle + ".html");
            String contents;
            if (!article.toFile().exists()) {
                String articleUrl = articleResult.getArticleUrl();
                Document document = Jsoup.connect(articleUrl)
                        .headers(headers)
                        .get();
                Elements lockedElement = document.select(".article__body--locked");
                if (!lockedElement.isEmpty()) {
                    System.out.println("News24 cookie has expired, quitting.");
                    break;
                }

                Elements select = document.select(".article__body");
                contents = select.text();
                Files.write(article, select.text().getBytes(StandardCharsets.UTF_8));
                System.out.println("Retrieved: \"" + articleResult.getTitle() + "\"");

            } else {
                contents = Files.readString(article);
                System.out.println("\"" + articleResult.getTitle() + "\" was already in cache");
            }

            Path cypher = cypherCache.resolve(normalizedTitle + ".cyp");
            if (!cypher.toFile().exists()) {
                ChatGPTResponse submit = ChatGPT.submit(PROMPT + "\n" + contents);
                String cypherResult = submit.getChoices().get(0).getMessage().getContent();
                Files.write(cypher, cypherResult.getBytes(StandardCharsets.UTF_8));
                System.out.println("Cypher'd: \"" + articleResult.getTitle() + "\"");
            } else {
                System.out.println("\"" + articleResult.getTitle() + "\" was already in cypher'd");
            }
        }

        return Collections.emptyMap();
    }

    public static String normalizeTitle(String title) {
        return title.replaceAll("[^a-zA-Z0-9 ]","").replaceAll(" ", "_");
    }
}
