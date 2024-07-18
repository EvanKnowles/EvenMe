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
    public static final String NEWS24_COOKIE = "FCNBYuxdQAyCtqOlt6XLcA8x9kR14yjGLWbUK0NtUqs-1721284063-1.0.1.1-ZR37jCmnJrg167bQ7S8xeC6lWIlnExr4QuUzTmSC8489z0MusnbIhZl2t0zEda1d7jc4_0feimVctA.qqvaTcg";

    public static Map<String, Article> getArticles() throws IOException {
        String result = REST.sendGet(BASE + "/api/article/loadmore/tag?tagType=topics&tag=politics&pageNumber=1&pageSize=2&breadcrumb=news24/Tags/Topics/politics&isMobile=false&thumbnailItemType=_SmallThumbItem", Collections.emptyMap(), Collections.emptyMap());
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
