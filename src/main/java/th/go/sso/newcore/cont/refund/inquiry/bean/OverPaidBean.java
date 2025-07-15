package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;

public class OverPaidBean {
    private Long receiveCompanyId;
    private BigDecimal empeOver;

    public Long getReceiveCompanyId() {
        return receiveCompanyId;
    }

    public void setReceiveCompanyId(Long receiveCompanyId) {
        this.receiveCompanyId = receiveCompanyId;
    }

    public BigDecimal getEmpeOver() {
        return empeOver;
    }

    public void setEmpeOver(BigDecimal empeOver) {
        this.empeOver = empeOver;
    }
}
