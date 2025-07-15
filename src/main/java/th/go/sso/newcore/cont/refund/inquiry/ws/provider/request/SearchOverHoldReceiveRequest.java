package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchOverHoldReceiveRequest extends SearchRequest {
    private String receiptNo;
    private String fullName;
    private String idCardNo;
    private String receiveNo;
    private String progressStatus;
    private String startPeriodMonth;
    private String startPeriodYear;
    private String endPeriodMonth;
    private String endPeriodYear;

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
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
