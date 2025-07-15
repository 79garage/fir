package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ButtonTypeBean {
    private boolean isEdit;
    private boolean isCancel;
    private boolean isSendRequest;

    @JsonProperty("isEdit")
    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    @JsonProperty("isCancel")
    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    @JsonProperty("isSendRequest")
    public boolean isSendRequest() {
        return isSendRequest;
    }

    public void setSendRequest(boolean sendRequest) {
        isSendRequest = sendRequest;
    }
}
