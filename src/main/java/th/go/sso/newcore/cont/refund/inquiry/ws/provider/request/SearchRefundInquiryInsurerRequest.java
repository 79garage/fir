package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;


import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchRefundInquiryInsurerRequest extends SearchRequest {

    private String idCardNo;
    private String section;
    private String depCode;
    private String receiveNo;
    private String startPeriodMonth;
    private String startPeriodYear;
    private String endPeriodMonth;
    private String endPeriodYear;
    private String progressStatus;
   
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public String getProgressStatus() {
        return progressStatus;
    }
    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }
    public String getEndPeriodYear() {
        return endPeriodYear;
    }
    public void setEndPeriodYear(String endPeriodYear) {
        this.endPeriodYear = endPeriodYear;
    }
    public String getEndPeriodMonth() {
        return endPeriodMonth;
    }
    public void setEndPeriodMonth(String endPeriodMonth) {
        this.endPeriodMonth = endPeriodMonth;
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
    public String getDepCode() {
        return depCode;
    }
    public void setDepCode(String depCode) {
        this.depCode = depCode;
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
}
