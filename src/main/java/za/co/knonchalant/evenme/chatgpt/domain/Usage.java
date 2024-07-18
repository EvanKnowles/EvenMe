
package za.co.knonchalant.evenme.chatgpt.domain;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Usage {

    @SerializedName("completion_tokens")
    private Long mCompletionTokens;
    @SerializedName("prompt_tokens")
    private Long mPromptTokens;
    @SerializedName("total_tokens")
    private Long mTotalTokens;

    public Long getCompletionTokens() {
        return mCompletionTokens;
    }

    public void setCompletionTokens(Long completionTokens) {
        mCompletionTokens = completionTokens;
    }

    public Long getPromptTokens() {
        return mPromptTokens;
    }

    public void setPromptTokens(Long promptTokens) {
        mPromptTokens = promptTokens;
    }

    public Long getTotalTokens() {
        return mTotalTokens;
    }

    public void setTotalTokens(Long totalTokens) {
        mTotalTokens = totalTokens;
    }

}
