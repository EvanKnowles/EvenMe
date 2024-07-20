
package za.co.knonchalant.evenme.scrape.iol.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SectionsLabel {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("uuid")
    @Expose
    private String uuid;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
