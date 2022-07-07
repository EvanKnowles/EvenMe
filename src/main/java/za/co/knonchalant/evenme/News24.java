package za.co.knonchalant.evenme;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class News24 {
    public static final String BASE = "https://www.news24.com";
    public static final String NEWS24_COOKIE = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFhZWY1NjlmNTI0MTRlOWY0YTcxMDRiNmQwNzFmMDY2ZGZlZWQ2NzciLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRXZhbiBKYW1lcyBLbm93bGVzIiwicGljdHVyZSI6Imh0dHBzOi8vZ3JhcGguZmFjZWJvb2suY29tLzg1NjE0MDExMS9waWN0dXJlIiwidGZfdHJhZmZpY19hbGwiOnRydWUsInRmX25ld3NsZXR0ZXJzX2ZyZWUiOnRydWUsInRmX3dlYXRoZXJfYWxsIjp0cnVlLCJ0Zl9ib29rbWFya3MiOnRydWUsInRmX3JlZ2lzdGVyZWQiOnRydWUsInRmX2NvbW1lbnRzIjp0cnVlLCJ0Zl9hcnRpY2xlX2F1ZGlvIjp0cnVlLCJ0Zl9wZGYiOnRydWUsInRmX2FydGljbGVzIjp0cnVlLCJ0Zl9uZXdzbGV0dGVyc19hbGwiOnRydWUsInRmX3N1YnNjcmliZWQiOnRydWUsInRmX3VzZXJUeXBlIjozLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdHdlbnR5Zm91ci1lbmdsaXNoLWxpdmUiLCJhdWQiOiJ0d2VudHlmb3VyLWVuZ2xpc2gtbGl2ZSIsImF1dGhfdGltZSI6MTY1MzI4NjUwOSwidXNlcl9pZCI6IlhKc3NDSHl5SHVYMDFBZURaY1NvTlU5MVdVMDMiLCJzdWIiOiJYSnNzQ0h5eUh1WDAxQWVEWmNTb05VOTFXVTAzIiwiaWF0IjoxNjU1MjE4NzIwLCJleHAiOjE2NTUyMjIzMjAsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImZhY2Vib29rLmNvbSI6WyI4NTYxNDAxMTEiXSwiZW1haWwiOlsicGlyYXRlYW5nZWxAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZmFjZWJvb2suY29tIn19.SwM9UKN1HnbOaf5g4UUsQFposaQ7SoAsiq1Fy6jd1kb8fB2u0f-p0gFzT2bWU87viQVT8N7ow_v-9MHxDMcre_o0wdClRd8n9n4rkXgzTlr2NetVuyKQaH3mKHrfUWnssyJMw1qEHUGIdNUZQs5uDccmPRoglj67-M341fhiZen_lt4TQXhDNlCbZpY9Q_wFHfZ78BEgVjmnWgXZ-t4W8ybBEvnnC55jxDxD8A5NmqZhEKiYJeVDxIZSWAiVBfeMZ0jjBHxFrauTgnQXStDSLlLPzLtRcDL4Uy9ewwTuPSannYrUVvrnPsrh39WL3ElF2_q7KIyZWVXNpbFtyZ9DCQ";

    public static Map<String, Article> getArticles() throws IOException {
        String result = REST.sendGet(BASE + "/api/article/loadmore/tag?tagType=topics&tag=politics&pageNumber=1&pageSize=200&breadcrumb=news24/Tags/Topics/politics&isMobile=false&thumbnailItemType=_SmallThumbItem", Collections.emptyMap(), Collections.emptyMap());
        Gson gson = new Gson();
        News24Response news24Response = gson.fromJson(result, News24Response.class);

        Document parse = Jsoup.parse(news24Response.getHtmlContent());

        Map<String, Article> resultArray = new HashMap<>();
        Elements a = parse.select("a");
        for (Element element : a) {
            String url = element.attr("href");
            if (!url.startsWith("http")) {
                url = BASE + url;
            }
            StringBuilder resultString = getArticleForURL(url);
            resultArray.put(url, new Article(element.attr("aria-label"), url, resultString.toString()));

        }

        return resultArray;
    }

    public static StringBuilder getArticleForURL(String url) throws IOException {
        Document document = Jsoup.connect(url).cookie("24uat", NEWS24_COOKIE)
                .get();
        Elements select = document.select(".article__body p");
        StringBuilder resultString = new StringBuilder();
        for (Element element1 : select) {
            resultString.append(element1.text()).append(" ");
        }
        return resultString;
    }

}
