import za.co.knonchalant.evenme.Article;

import java.io.IOException;
import java.util.Map;

public class TestNews24 {
    public static void main(String[] args) throws IOException {
        Map<String, Article> articles = za.co.knonchalant.evenme.scrape.NewNews24.get();
    }
}
