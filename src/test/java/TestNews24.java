import za.co.knonchalant.evenme.Article;
import za.co.knonchalant.evenme.Environment;
import za.co.knonchalant.evenme.scrape.NewNews24;

import java.io.IOException;
import java.util.Map;

public class TestNews24 {

    public static void main(String[] args) throws IOException {
        NewNews24 news24 = new NewNews24(Environment.fromHardcode());
        Map<String, Article> articles = news24.get();
    }
}
