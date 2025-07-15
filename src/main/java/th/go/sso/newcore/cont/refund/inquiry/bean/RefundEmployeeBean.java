package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;

public class RefundEmployeeBean {

    private Long receiveEmployeeId;
    private Long insurerId;
    private String idCardNo;
    private String fullName;
    private BigDecimal totalWages;
    private BigDecimal realWages;
    private BigDecimal cntrAmount;
    private BigDecimal empeOverp;
    private String insurerStatus;
    private BigDecimal refundRealWages;
    private String receiptNo;
    private String refundStatus;
    private String refundReasonCode;

    public Long getReceiveEmployeeId() {
        return receiveEmployeeId;
    }

    public void setReceiveEmployeeId(Long receiveEmployeeId) {
        this.receiveEmployeeId = receiveEmployeeId;
    }

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getTotalWages() {
        return totalWages;
    }

    public void setTotalWages(BigDecimal totalWages) {
        this.totalWages = totalWages;
    }

    public BigDecimal getRealWages() {
        return realWages;
    }

    public void setRealWages(BigDecimal realWages) {
        this.realWages = realWages;
    }

    public BigDecimal getCntrAmount() {
        return cntrAmount;
    }

    public void setCntrAmount(BigDecimal cntrAmount) {
        this.cntrAmount = cntrAmount;
    }

    public BigDecimal getEmpeOverp() {
        return empeOverp;
    }

    public void setEmpeOverp(BigDecimal empeOverp) {
        this.empeOverp = empeOverp;
    }

    public String getInsurerStatus() {
        return insurerStatus;
    }

    public void setInsurerStatus(String insurerStatus) {
        this.insurerStatus = insurerStatus;
    }

    public BigDecimal getRefundRealWages() {
        return refundRealWages;
    }

    public void setRefundRealWages(BigDecimal refundRealWages) {
        this.refundRealWages = refundRealWages;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundReasonCode() {
        return refundReasonCode;
    }

    public void setRefundReasonCode(String refundReasonCode) {
        this.refundReasonCode = refundReasonCode;
    }
}
