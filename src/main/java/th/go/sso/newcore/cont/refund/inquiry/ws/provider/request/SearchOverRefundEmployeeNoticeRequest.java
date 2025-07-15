package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchOverRefundEmployeeNoticeRequest extends SearchRequest {
    private String depCode;
    private String depRegionCode;
    private String announceStatus;
    private String startPeriodMonth;
    private String startPeriodYear;
    private String endPeriodMonth;
    private String endPeriodYear;

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepRegionCode() {
        return depRegionCode;
    }

    public void setDepRegionCode(String depRegionCode) {
        this.depRegionCode = depRegionCode;
    }

    public String getAnnounceStatus() {
        return announceStatus;
    }

    public void setAnnounceStatus(String announceStatus) {
        this.announceStatus = announceStatus;
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
