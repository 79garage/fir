package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.RequestBean;

public class SearchRefundCompanyByIdRequest extends RequestBean {

    private Long companyId;
    private String mode;
    private Long refundRequestId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }
}
