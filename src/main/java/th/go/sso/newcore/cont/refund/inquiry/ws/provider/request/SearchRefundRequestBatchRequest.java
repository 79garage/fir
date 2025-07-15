package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import java.util.List;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchRefundRequestBatchRequest extends SearchRequest {
    private List<Long> refundRequestIds;

    public List<Long> getRefundRequestIds() {
        return refundRequestIds;
    }

    public void setRefundRequestIds(List<Long> refundRequestIds) {
        this.refundRequestIds = refundRequestIds;
    }
}
