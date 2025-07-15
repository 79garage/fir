package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.List;



public class ConTrReceiveInsurerBean {
    
    private String payperiod;
    private BigDecimal cntrAmount;
    private BigDecimal interestPaid;
    private BigDecimal toTal;
    private BigDecimal toTalRefund;
    private Integer receiveInsurerId;
    private List<RefundRequestReceiptBean> receipList;
    
    public String getPayperiod() {
        return payperiod;
    }
    public void setPayperiod(String payperiod) {
        this.payperiod = payperiod;
    }
    public BigDecimal getCntrAmount() {
        return cntrAmount;
    }
    public void setCntrAmount(BigDecimal cntrAmount) {
        this.cntrAmount = cntrAmount;
    }
    public BigDecimal getInterestPaid() {
        return interestPaid;
    }
    public void setInterestPaid(BigDecimal interestPaid) {
        this.interestPaid = interestPaid;
    }
    public BigDecimal getToTal() {
        return toTal;
    }
    public void setToTal(BigDecimal toTal) {
        this.toTal = toTal;
    }
    public BigDecimal getToTalRefund() {
        return toTalRefund;
    }
    public void setToTalRefund(BigDecimal toTalRefund) {
        this.toTalRefund = toTalRefund;
    }
    public List<RefundRequestReceiptBean> getReceipList() {
        return receipList;
    }
    public void setReceipList(List<RefundRequestReceiptBean> receipList) {
        this.receipList = receipList;
    }
    public Integer getReceiveInsurerId() {
        return receiveInsurerId;
    }
    public void setReceiveInsurerId(Integer receiveInsurerId) {
        this.receiveInsurerId = receiveInsurerId;
    }
    

    
}
