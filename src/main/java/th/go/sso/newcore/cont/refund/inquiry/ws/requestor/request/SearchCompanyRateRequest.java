package th.go.sso.newcore.cont.refund.inquiry.ws.requestor.request;

import th.go.sso.newcore.cont.common.dto.RequestBean;

public class SearchCompanyRateRequest extends RequestBean {
    private Long companyId;
    private String period;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
