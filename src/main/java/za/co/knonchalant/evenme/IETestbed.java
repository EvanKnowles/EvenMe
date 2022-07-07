package za.co.knonchalant.evenme;

import java.io.IOException;
import java.io.OptionalDataException;

public class IETestbed {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StringBuilder articleForURL = News24.getArticleForURL("https://www.news24.com/news24/southafrica/investigations/ramaphosa-farm-theft-money-stolen-from-game-farm-the-earnings-from-private-sales-transactions-20220603");
        NLP nlp = new NLP();
        nlp.openIe(articleForURL.toString());
    }
}
