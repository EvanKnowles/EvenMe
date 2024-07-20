
package za.co.knonchalant.evenme.scrape.iol.domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IOLResponse {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sectionsLabels")
    @Expose
    private List<SectionsLabel> sectionsLabels;
    @SerializedName("redisLength")
    @Expose
    private Integer redisLength;
    @SerializedName("contents")
    @Expose
    private List<Content> contents;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SectionsLabel> getSectionsLabels() {
        return sectionsLabels;
    }

    public void setSectionsLabels(List<SectionsLabel> sectionsLabels) {
        this.sectionsLabels = sectionsLabels;
    }

    public Integer getRedisLength() {
        return redisLength;
    }

    public void setRedisLength(Integer redisLength) {
        this.redisLength = redisLength;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

}
