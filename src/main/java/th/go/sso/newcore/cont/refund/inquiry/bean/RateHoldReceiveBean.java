package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;

public class RateHoldReceiveBean {
    private String receiptNo;
    private String receiptName;
    private String idCardNo;
    private String payPeriod;
    private String payPeriodMonth;
    private String payPeriodYear;
    private BigDecimal cntrAmount;
    private String reasonCode;
    private BigDecimal refundCntrRate;
    private BigDecimal refundSicknessRate;
    private BigDecimal refundRetirementRate;
    private BigDecimal refundSickness;
    private BigDecimal refundRetirement;

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public String getPayPeriodMonth() {
        return payPeriodMonth;
    }

    public void setPayPeriodMonth(String payPeriodMonth) {
        this.payPeriodMonth = payPeriodMonth;
    }

    public String getPayPeriodYear() {
        return payPeriodYear;
    }

    public void setPayPeriodYear(String payPeriodYear) {
        this.payPeriodYear = payPeriodYear;
    }

    public BigDecimal getCntrAmount() {
        return cntrAmount;
    }

    public void setCntrAmount(BigDecimal cntrAmount) {
        this.cntrAmount = cntrAmount;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public BigDecimal getRefundCntrRate() {
        return refundCntrRate;
    }

    public void setRefundCntrRate(BigDecimal refundCntrRate) {
        this.refundCntrRate = refundCntrRate;
    }

    public BigDecimal getRefundSicknessRate() {
        return refundSicknessRate;
    }

    public void setRefundSicknessRate(BigDecimal refundSicknessRate) {
        this.refundSicknessRate = refundSicknessRate;
    }

    public BigDecimal getRefundRetirementRate() {
        return refundRetirementRate;
    }

    public void setRefundRetirementRate(BigDecimal refundRetirementRate) {
        this.refundRetirementRate = refundRetirementRate;
    }

    public BigDecimal getRefundSickness() {
        return refundSickness;
    }

    public void setRefundSickness(BigDecimal refundSickness) {
        this.refundSickness = refundSickness;
    }

    public BigDecimal getRefundRetirement() {
        return refundRetirement;
    }

    public void setRefundRetirement(BigDecimal refundRetirement) {
        this.refundRetirement = refundRetirement;
    }
}
