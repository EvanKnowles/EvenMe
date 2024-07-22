package za.co.knonchalant.evenme.scrape.news24;

public class InvalidCookieException extends Exception {
    public InvalidCookieException(String url) {
        super("Invalid cookie; failed to scrape " + url);
    }
}
