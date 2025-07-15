package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;

public class SplitEmployeePeriodBean {

    private String payPeriod;
    private BigDecimal requestEmpeAmount;
    private BigDecimal refundEmpeAmount;

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public BigDecimal getRequestEmpeAmount() {
        return requestEmpeAmount;
    }

    public void setRequestEmpeAmount(BigDecimal requestEmpeAmount) {
        this.requestEmpeAmount = requestEmpeAmount;
    }

    public BigDecimal getRefundEmpeAmount() {
        return refundEmpeAmount;
    }

    public void setRefundEmpeAmount(BigDecimal refundEmpeAmount) {
        this.refundEmpeAmount = refundEmpeAmount;
    }
}
