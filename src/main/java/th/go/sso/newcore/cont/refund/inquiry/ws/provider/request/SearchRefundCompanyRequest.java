package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import java.util.ArrayList;
import java.util.List;

import th.go.sso.newcore.cont.common.dto.RequestBean;

public class SearchRefundCompanyRequest extends RequestBean {

    private Long companyId;
    private String mode;
    private List<Long> receiveCompanyId = new ArrayList<>();

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

    public List<Long> getReceiveCompanyId() {
        return receiveCompanyId;
    }

    public void setReceiveCompanyId(List<Long> receiveCompanyId) {
        this.receiveCompanyId = receiveCompanyId;
    }
}
