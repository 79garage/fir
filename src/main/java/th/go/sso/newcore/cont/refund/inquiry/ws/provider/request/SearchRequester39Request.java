package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import java.util.List;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchRequester39Request extends SearchRequest {
    private List<Long> refundRequestIds;
    private String requesterType;

    public List<Long> getRefundRequestIds() {
        return refundRequestIds;
    }

    public void setRefundRequestIds(List<Long> refundRequestIds) {
        this.refundRequestIds = refundRequestIds;
    }

    public String getRequesterType() {
        return requesterType;
    }

    public void setRequesterType(String requesterType) {
        this.requesterType = requesterType;
    }
}
