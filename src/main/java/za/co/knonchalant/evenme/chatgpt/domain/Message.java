
package za.co.knonchalant.evenme.chatgpt.domain;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Message {

    @SerializedName("content")
    private String mContent;
    @SerializedName("role")
    private String mRole;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mContent='" + mContent + '\'' +
                ", mRole='" + mRole + '\'' +
                '}';
    }
}
