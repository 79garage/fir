package th.go.sso.newcore.cont.refund.inquiry.ws.requestor.response;

import th.go.sso.newcore.cont.refund.inquiry.bean.RateBean;

public class SearchRateResponse {
    private String status;
    private String type;
    private String errorCode;
    private String errorMsg;
    private RateBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public RateBean getResult() {
        return result;
    }

    public void setResult(RateBean result) {
        this.result = result;
    }
}
