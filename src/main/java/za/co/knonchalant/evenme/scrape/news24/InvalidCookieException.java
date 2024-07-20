package za.co.knonchalant.evenme.scrape.news24;

public class InvalidCookieException extends Exception {
    public InvalidCookieException(String url) {
        super("Failed to scrape " + url + " because of invalid cookie");
    }
}
