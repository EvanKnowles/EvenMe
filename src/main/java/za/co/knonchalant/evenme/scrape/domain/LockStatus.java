
package za.co.knonchalant.evenme.scrape.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LockStatus {

    @SerializedName("isLocked")
    @Expose
    private Boolean isLocked;
    @SerializedName("isTrialLocked")
    @Expose
    private Boolean isTrialLocked;
    @SerializedName("isRegistrationLocked")
    @Expose
    private Boolean isRegistrationLocked;

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Boolean getIsTrialLocked() {
        return isTrialLocked;
    }

    public void setIsTrialLocked(Boolean isTrialLocked) {
        this.isTrialLocked = isTrialLocked;
    }

    public Boolean getIsRegistrationLocked() {
        return isRegistrationLocked;
    }

    public void setIsRegistrationLocked(Boolean isRegistrationLocked) {
        this.isRegistrationLocked = isRegistrationLocked;
    }

}
