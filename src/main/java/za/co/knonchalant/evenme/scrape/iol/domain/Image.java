
package za.co.knonchalant.evenme.scrape.iol.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("focal_x")
    @Expose
    private Integer focalX;
    @SerializedName("focal_y")
    @Expose
    private Integer focalY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getFocalX() {
        return focalX;
    }

    public void setFocalX(Integer focalX) {
        this.focalX = focalX;
    }

    public Integer getFocalY() {
        return focalY;
    }

    public void setFocalY(Integer focalY) {
        this.focalY = focalY;
    }


}
