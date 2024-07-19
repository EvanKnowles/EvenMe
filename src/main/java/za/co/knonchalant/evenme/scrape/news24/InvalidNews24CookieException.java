package za.co.knonchalant.evenme.scrape.news24;

public class InvalidNews24CookieException extends Exception {
    public InvalidNews24CookieException(String url) {
        super("Failed to scrape " + url + " because of invalid cookie");
    }
}
