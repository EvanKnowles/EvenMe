package za.co.knonchalant.evenme;

public class Article {
    private String name;
    private String url;
    private String article;

    public Article(String name, String url, String article) {
        this.name = name;
        this.url = url;
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getArticle() {
        return article;
    }
}
