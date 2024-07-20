
package za.co.knonchalant.evenme.scrape.iol.domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("contentKey")
    @Expose
    private String contentKey;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("teaserHeadline")
    @Expose
    private String teaserHeadline;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("published")
    @Expose
    private String published;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("sectionLabel")
    @Expose
    private String sectionLabel;
    @SerializedName("publication")
    @Expose
    private String publication;
    @SerializedName("titleKey")
    @Expose
    private String titleKey;
    @SerializedName("secondaryTags")
    @Expose
    private List<Object> secondaryTags;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("image")
    @Expose
    private Image image;

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTeaserHeadline() {
        return teaserHeadline;
    }

    public void setTeaserHeadline(String teaserHeadline) {
        this.teaserHeadline = teaserHeadline;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSectionLabel() {
        return sectionLabel;
    }

    public void setSectionLabel(String sectionLabel) {
        this.sectionLabel = sectionLabel;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public List<Object> getSecondaryTags() {
        return secondaryTags;
    }

    public void setSecondaryTags(List<Object> secondaryTags) {
        this.secondaryTags = secondaryTags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


}
