package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.SearchRequest;


public class SearchRefundDeleteCancelHistoryRequest extends SearchRequest {
    private Long companyId;
    private String idCardNo;
    private String receiveNo;
    private String section;
    private String startPeriodMonth;
    private String startPeriodYear;
    private String endPeriodMonth;
    private String endPeriodYear;

    public Long getCompanyId() {
        return companyId;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public String getSection() {
        return section;
    }

    public String getStartPeriodMonth() {
        return startPeriodMonth;
    }

    public String getStartPeriodYear() {
        return startPeriodYear;
    }

    public String getEndPeriodMonth() {
        return endPeriodMonth;
    }

    public String getEndPeriodYear() {
        return endPeriodYear;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setStartPeriodMonth(String startPeriodMonth) {
        this.startPeriodMonth = startPeriodMonth;
    }

    public void setStartPeriodYear(String startPeriodYear) {
        this.startPeriodYear = startPeriodYear;
    }

    public void setEndPeriodMonth(String endPeriodMonth) {
        this.endPeriodMonth = endPeriodMonth;
    }

    public void setEndPeriodYear(String endPeriodYear) {
        this.endPeriodYear = endPeriodYear;
    }
}
