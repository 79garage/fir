package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class RefundHoldReceiveBean {
    private Long refundRequestId;
    private Long holdReceiveId;
    private String payPeriod;
    private String payPeriodMonth;
    private String payPeriodYear;
    private BigDecimal cntrAmount;
    private BigDecimal interestPaid;
    private BigDecimal interestAmount;
    private BigDecimal totalCntr;
    private BigDecimal totalContribution;
    private String receiptNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paymentDate;
    private BigDecimal overPaid;
    private BigDecimal overInterestPaid;
    private BigDecimal totalOver;
    private String progressStatus;
    private String refundReasonCode;
    private String section;
    private String reverseStatus;
    private String reverseRemark;
    private String idCardNo;
    private String fullName;
    private String receiveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;
    private List<OrderBean> orderList;

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public Long getHoldReceiveId() {
        return holdReceiveId;
    }

    public void setHoldReceiveId(Long holdReceiveId) {
        this.holdReceiveId = holdReceiveId;
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

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public BigDecimal getTotalCntr() {
        return totalCntr;
    }

    public void setTotalCntr(BigDecimal totalCntr) {
        this.totalCntr = totalCntr;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getOverPaid() {
        return overPaid;
    }

    public void setOverPaid(BigDecimal overPaid) {
        this.overPaid = overPaid;
    }

    public BigDecimal getOverInterestPaid() {
        return overInterestPaid;
    }

    public void setOverInterestPaid(BigDecimal overInterestPaid) {
        this.overInterestPaid = overInterestPaid;
    }

    public BigDecimal getTotalOver() {
        return totalOver;
    }

    public void setTotalOver(BigDecimal totalOver) {
        this.totalOver = totalOver;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getRefundReasonCode() {
        return refundReasonCode;
    }

    public void setRefundReasonCode(String refundReasonCode) {
        this.refundReasonCode = refundReasonCode;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getReverseStatus() {
        return reverseStatus;
    }

    public void setReverseStatus(String reverseStatus) {
        this.reverseStatus = reverseStatus;
    }

    public String getReverseRemark() {
        return reverseRemark;
    }

    public void setReverseRemark(String reverseRemark) {
        this.reverseRemark = reverseRemark;
    }

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
    }

    public BigDecimal getInterestPaid() {
        return interestPaid;
    }

    public void setInterestPaid(BigDecimal interestPaid) {
        this.interestPaid = interestPaid;
    }

    public BigDecimal getTotalContribution() {
        return totalContribution;
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
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

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }
}
