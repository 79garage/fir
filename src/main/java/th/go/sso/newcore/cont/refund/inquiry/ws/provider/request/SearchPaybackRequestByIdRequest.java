package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.RequestBean;

public class SearchPaybackRequestByIdRequest  extends RequestBean {

    private Long paybackRequestId;

    public Long getPaybackRequestId() {
        return paybackRequestId;
    }

    public void setPaybackRequestId(Long paybackRequestId) {
        this.paybackRequestId = paybackRequestId;
    }
}
