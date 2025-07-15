package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.RequestBean;

import java.util.List;

public class GetHoldReceiveByIdRequest extends RequestBean {
    private List<Long> holdReceiveId;

    public List<Long> getHoldReceiveId() {
        return holdReceiveId;
    }

    public void setHoldReceiveId(List<Long> holdReceiveId) {
        this.holdReceiveId = holdReceiveId;
    }
}
