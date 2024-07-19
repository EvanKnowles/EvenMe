
package za.co.knonchalant.evenme.scrape.news24.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtendedProperties {

    @SerializedName("AudioArticle")
    @Expose
    private String audioArticle;
    @SerializedName("IsExclusiveNews")
    @Expose
    private String isExclusiveNews;

    public String getAudioArticle() {
        return audioArticle;
    }

    public void setAudioArticle(String audioArticle) {
        this.audioArticle = audioArticle;
    }

    public String getIsExclusiveNews() {
        return isExclusiveNews;
    }

    public void setIsExclusiveNews(String isExclusiveNews) {
        this.isExclusiveNews = isExclusiveNews;
    }

}
