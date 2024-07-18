
package za.co.knonchalant.evenme.scrape.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleResult {

    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("articleType")
    @Expose
    private String articleType;
    @SerializedName("articleUrl")
    @Expose
    private String articleUrl;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("displayDate")
    @Expose
    private String displayDate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("firstPublishedDate")
    @Expose
    private String firstPublishedDate;
    @SerializedName("siteId")
    @Expose
    private Integer siteId;
    @SerializedName("siteBaseUrl")
    @Expose
    private String siteBaseUrl;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("categoryDisplayName")
    @Expose
    private String categoryDisplayName;
    @SerializedName("categoryBreadcrumb")
    @Expose
    private String categoryBreadcrumb;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("lockStatus")
    @Expose
    private LockStatus lockStatus;
    @SerializedName("extendedProperties")
    @Expose
    private ExtendedProperties extendedProperties;
    @SerializedName("isExclusiveNews")
    @Expose
    private Boolean isExclusiveNews;
    @SerializedName("isBreakingNews")
    @Expose
    private Boolean isBreakingNews;
    @SerializedName("accreditationExternalUrl")
    @Expose
    private String accreditationExternalUrl;
    @SerializedName("accreditationUrl")
    @Expose
    private String accreditationUrl;
    @SerializedName("accreditationName")
    @Expose
    private String accreditationName;
    @SerializedName("permatitle")
    @Expose
    private String permatitle;
    @SerializedName("relativeUrl")
    @Expose
    private String relativeUrl;
    @SerializedName("isArchiveArticle")
    @Expose
    private Boolean isArchiveArticle;
    @SerializedName("commentStatus")
    @Expose
    private String commentStatus;
    @SerializedName("articleStatus")
    @Expose
    private String articleStatus;
    @SerializedName("liveEdit")
    @Expose
    private Boolean liveEdit;
    @SerializedName("liveEditChildId")
    @Expose
    private String liveEditChildId;
    @SerializedName("categoryCollectionId")
    @Expose
    private Integer categoryCollectionId;

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getFirstPublishedDate() {
        return firstPublishedDate;
    }

    public void setFirstPublishedDate(String firstPublishedDate) {
        this.firstPublishedDate = firstPublishedDate;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteBaseUrl() {
        return siteBaseUrl;
    }

    public void setSiteBaseUrl(String siteBaseUrl) {
        this.siteBaseUrl = siteBaseUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryDisplayName() {
        return categoryDisplayName;
    }

    public void setCategoryDisplayName(String categoryDisplayName) {
        this.categoryDisplayName = categoryDisplayName;
    }

    public String getCategoryBreadcrumb() {
        return categoryBreadcrumb;
    }

    public void setCategoryBreadcrumb(String categoryBreadcrumb) {
        this.categoryBreadcrumb = categoryBreadcrumb;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public LockStatus getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    public ExtendedProperties getExtendedProperties() {
        return extendedProperties;
    }

    public void setExtendedProperties(ExtendedProperties extendedProperties) {
        this.extendedProperties = extendedProperties;
    }

    public Boolean getIsExclusiveNews() {
        return isExclusiveNews;
    }

    public void setIsExclusiveNews(Boolean isExclusiveNews) {
        this.isExclusiveNews = isExclusiveNews;
    }

    public Boolean getIsBreakingNews() {
        return isBreakingNews;
    }

    public void setIsBreakingNews(Boolean isBreakingNews) {
        this.isBreakingNews = isBreakingNews;
    }

    public String getAccreditationExternalUrl() {
        return accreditationExternalUrl;
    }

    public void setAccreditationExternalUrl(String accreditationExternalUrl) {
        this.accreditationExternalUrl = accreditationExternalUrl;
    }

    public String getAccreditationUrl() {
        return accreditationUrl;
    }

    public void setAccreditationUrl(String accreditationUrl) {
        this.accreditationUrl = accreditationUrl;
    }

    public String getAccreditationName() {
        return accreditationName;
    }

    public void setAccreditationName(String accreditationName) {
        this.accreditationName = accreditationName;
    }

    public String getPermatitle() {
        return permatitle;
    }

    public void setPermatitle(String permatitle) {
        this.permatitle = permatitle;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public void setRelativeUrl(String relativeUrl) {
        this.relativeUrl = relativeUrl;
    }

    public Boolean getIsArchiveArticle() {
        return isArchiveArticle;
    }

    public void setIsArchiveArticle(Boolean isArchiveArticle) {
        this.isArchiveArticle = isArchiveArticle;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Boolean getLiveEdit() {
        return liveEdit;
    }

    public void setLiveEdit(Boolean liveEdit) {
        this.liveEdit = liveEdit;
    }

    public String getLiveEditChildId() {
        return liveEditChildId;
    }

    public void setLiveEditChildId(String liveEditChildId) {
        this.liveEditChildId = liveEditChildId;
    }

    public Integer getCategoryCollectionId() {
        return categoryCollectionId;
    }

    public void setCategoryCollectionId(Integer categoryCollectionId) {
        this.categoryCollectionId = categoryCollectionId;
    }

}
