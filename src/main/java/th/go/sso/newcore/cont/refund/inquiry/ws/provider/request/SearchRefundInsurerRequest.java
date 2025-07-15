package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchRefundInsurerRequest extends SearchRequest {

    private Long insurerId;
    private String receiveNo;
    private String progressStatus;
    private String startPeriodMonth;
    private String startPeriodYear;
    private String endPeriodMonth;
    private String endPeriodYear;

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getStartPeriodMonth() {
        return startPeriodMonth;
    }

    public void setStartPeriodMonth(String startPeriodMonth) {
        this.startPeriodMonth = startPeriodMonth;
    }

    public String getStartPeriodYear() {
        return startPeriodYear;
    }

    public void setStartPeriodYear(String startPeriodYear) {
        this.startPeriodYear = startPeriodYear;
    }

    public String getEndPeriodMonth() {
        return endPeriodMonth;
    }

    public void setEndPeriodMonth(String endPeriodMonth) {
        this.endPeriodMonth = endPeriodMonth;
    }

    public String getEndPeriodYear() {
        return endPeriodYear;
    }

    public void setEndPeriodYear(String endPeriodYear) {
        this.endPeriodYear = endPeriodYear;
    }
}
