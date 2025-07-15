package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.RequestBean;

public class SearchEmployeeDetailRequest extends RequestBean {

    private Long companyId;
    private Long receiveCompanyId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getReceiveCompanyId() {
        return receiveCompanyId;
    }

    public void setReceiveCompanyId(Long receiveCompanyId) {
        this.receiveCompanyId = receiveCompanyId;
    }
}
