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

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewNews24 {
    public static final String URL = "https://cms.capi24.com/v2/Articles/category/%2Fnews24%2Fpolitics?pageNo=1&pageSize=15";
    private static final Map<String, String> COOKIES = new HashMap<>();

    static {
        COOKIES.put("24cat", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MjEyOTk0NzMsIm5iZiI6MTcyMTI5MjI3MywiZXhwIjoxNzIxMjk1NzI2LCJ1c2VyIjp7ImlkIjoiWEpzc0NIeXlIdVgwMUFlRFpjU29OVTkxV1UwMyIsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwidXNlcm5hbWUiOiJFdmFuIEtub3dsZXMifX0.Vs5FbRQvZEvay5MctCFnsCH4t7V6jDzl560vVXsw4Mc");
        COOKIES.put("24uat", "eyJhbGciOiJSUzI1NiIsImtpZCI6ImMxNTQwYWM3MWJiOTJhYTA2OTNjODI3MTkwYWNhYmU1YjA1NWNiZWMiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRXZhbiBKYW1lcyBLbm93bGVzIiwicGljdHVyZSI6Imh0dHBzOi8vZ3JhcGguZmFjZWJvb2suY29tLzg1NjE0MDExMS9waWN0dXJlIiwidGZfYm9va21hcmtzIjp0cnVlLCJ0Zl90cmFmZmljX2FsbCI6dHJ1ZSwidGZfd2VhdGhlcl9hbGwiOnRydWUsInRmX25ld3NsZXR0ZXJzX2ZyZWUiOnRydWUsInRmX3JlZ2lzdGVyZWQiOnRydWUsInRmX2NvbW1lbnRzIjp0cnVlLCJ0Zl9hcnRpY2xlX2F1ZGlvIjp0cnVlLCJ0Zl9wZGYiOnRydWUsInRmX2FydGljbGVzIjp0cnVlLCJ0Zl9uZXdzbGV0dGVyc19hbGwiOnRydWUsInRmX3N1YnNjcmliZWQiOnRydWUsInRmX3VzZXJUeXBlIjozLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdHdlbnR5Zm91ci1lbmdsaXNoLWxpdmUiLCJhdWQiOiJ0d2VudHlmb3VyLWVuZ2xpc2gtbGl2ZSIsImF1dGhfdGltZSI6MTcyMTI5MjI3MSwidXNlcl9pZCI6IlhKc3NDSHl5SHVYMDFBZURaY1NvTlU5MVdVMDMiLCJzdWIiOiJYSnNzQ0h5eUh1WDAxQWVEWmNTb05VOTFXVTAzIiwiaWF0IjoxNzIxMjkyMjcxLCJleHAiOjE3MjEyOTU4NzEsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsicGlyYXRlYW5nZWxAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.ELBH-KFMI3K9x54RktWYLMh49R5u0tvjljapGCUhavw7uUIvaT-KjQ7jY2PgZCj59h6yVRUMIOMThsIAvnl7_yROWFdY8VDBfLcXLyi_F3vSN-mp56jrKrMRXAERdl61qgy06am_JNwghRaeV_qGwD-OnsHC5G7CrSZ_q2OigrfhAD9QzR2hwWb-1SoLaSsOG0-_Tf_DoflGkGOz3O0ZtMVo8lAIqRIIOsPpWwEhEWiY6Ri_4isOTVuT_kRINoyGf710_BxcZmLiZ61knaS2gHX8KxcqdUiXErIWnFc5_ZWjd4p97CUiWjHdE714mF_KorV8rqPE5dIF77mYa-kMow");
        COOKIES.put("24uid", "7496c1ea-4eb1-4450-9214-9d46ab4fba2a");
    }

    public static Map<String, Article> get() throws IOException {
        Map<String, Article> map = new HashMap<String, Article>();
        Map<String, String> headers = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : COOKIES.entrySet()) {
            stringBuilder.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("; ");
        }

        headers.put("Cookie", stringBuilder.toString());

        String result = REST.sendGet(URL, Collections.emptyMap(), headers);

        Type type = new TypeToken<List<ArticleResult>>() {
        }.getType();

        List<ArticleResult> articleResults = new Gson().fromJson(result, type);

        for (ArticleResult articleResult : articleResults) {
            String articleUrl = articleResult.getArticleUrl();
            Document document = Jsoup.connect(articleUrl)
                    .headers(headers)
                    .get();
            Elements select = document.select(".article__body");
            System.out.println(select.text());
            ChatGPTResponse submit = ChatGPT.submit("Extract facts and connections from the following article, and express them in Cypher syntax, including nodes and links:\n" + select.text());
            System.out.println(submit.getChoices().get(0).getMessage());
            break;
        }

        System.out.println(articleResults);

        return Collections.emptyMap();
    }
}
