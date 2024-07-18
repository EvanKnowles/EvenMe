
package za.co.knonchalant.evenme.chatgpt.domain;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Choice {

    @SerializedName("finish_reason")
    private String mFinishReason;
    @SerializedName("index")
    private Long mIndex;
    @SerializedName("message")
    private Message mMessage;

    public String getFinishReason() {
        return mFinishReason;
    }

    public void setFinishReason(String finishReason) {
        mFinishReason = finishReason;
    }

    public Long getIndex() {
        return mIndex;
    }

    public void setIndex(Long index) {
        mIndex = index;
    }

    public Message getMessage() {
        return mMessage;
    }

    public void setMessage(Message message) {
        mMessage = message;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "mFinishReason='" + mFinishReason + '\'' +
                ", mIndex=" + mIndex +
                ", mMessage=" + mMessage +
                '}';
    }
}
