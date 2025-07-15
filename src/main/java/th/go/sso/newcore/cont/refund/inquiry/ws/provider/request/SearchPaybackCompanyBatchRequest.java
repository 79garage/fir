package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import java.util.Date;
import java.util.List;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchPaybackCompanyBatchRequest extends SearchRequest {
    private String createBy;
    private Date createDate;
    private List<Long> paybackRequestIds;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Long> getPaybackRequestIds() {
        return paybackRequestIds;
    }

    public void setPaybackRequestIds(List<Long> paybackRequestIds) {
        this.paybackRequestIds = paybackRequestIds;
    }
}
