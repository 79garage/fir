package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import java.util.Date;
import java.util.List;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchRequesterInsurerBatchRequest extends SearchRequest {

    private String createBy;
    private Date createDate;
    private List<Long> requesterIds;

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

    public List<Long> getRequesterIds() {
        return requesterIds;
    }

    public void setRequesterIds(List<Long> requesterIds) {
        this.requesterIds = requesterIds;
    }
}
