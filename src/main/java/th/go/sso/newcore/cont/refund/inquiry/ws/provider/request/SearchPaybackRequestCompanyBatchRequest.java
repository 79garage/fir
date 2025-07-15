package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import java.util.List;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchPaybackRequestCompanyBatchRequest extends SearchRequest {
    private List<Long> paybackRequestIds;

    public List<Long> getPaybackRequestIds() {
        return paybackRequestIds;
    }

    public void setPaybackRequestIds(List<Long> paybackRequestIds) {
        this.paybackRequestIds = paybackRequestIds;
    }
}
