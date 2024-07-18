
package za.co.knonchalant.evenme.chatgpt.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class ChatGPTResponse {

    @SerializedName("choices")
    private List<Choice> mChoices;
    @SerializedName("created")
    private Long mCreated;
    @SerializedName("id")
    private String mId;
    @SerializedName("model")
    private String mModel;
    @SerializedName("object")
    private String mObject;
    @SerializedName("usage")
    private Usage mUsage;

    public List<Choice> getChoices() {
        return mChoices;
    }

    public void setChoices(List<Choice> choices) {
        mChoices = choices;
    }

    public Long getCreated() {
        return mCreated;
    }

    public void setCreated(Long created) {
        mCreated = created;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        mModel = model;
    }

    public String getObject() {
        return mObject;
    }

    public void setObject(String object) {
        mObject = object;
    }

    public Usage getUsage() {
        return mUsage;
    }

    public void setUsage(Usage usage) {
        mUsage = usage;
    }

}
