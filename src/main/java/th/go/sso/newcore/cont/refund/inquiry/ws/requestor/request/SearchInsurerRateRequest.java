package th.go.sso.newcore.cont.refund.inquiry.ws.requestor.request;

import th.go.sso.newcore.cont.common.dto.RequestBean;

public class SearchInsurerRateRequest extends RequestBean {
    private Long insurerId;
    private String period;

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
