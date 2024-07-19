package za.co.knonchalant.evenme;

public class Article {
    private String name;
    private String url;
    private String article;
    private String normalized;

    public Article(String name, String url, String article, String normalized) {
        this.name = name;
        this.url = url;
        this.article = article;
        this.normalized = normalized;
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

    public String getNormalized() {
        return normalized;
    }
}
