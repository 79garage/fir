package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class OverRefundCompanyBean {

    private Long receiveCompanyId;
    private Long companyId;
    private Long refundRequestId;
    private Long refundPeriodId;
    private String receiveNo;
    private String payPeriod;
    private String payPeriodMonth;
    private String payPeriodYear;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date payDateOfCntr;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paymentDate;
    private BigDecimal totalWages;
    private String cntrRate;
    private int numOfEmployee;
    private BigDecimal cntrAmount;
    private BigDecimal interestPaid;
    private BigDecimal totalContribution;
    private BigDecimal overPaid;
    private BigDecimal interestAmount;
    private BigDecimal totalOver;
    private String approveNo;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;
    private String receiptNo;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date payDate;
    private String approveStatus;
    private String progressStatus;
    private String noticeStatus;
    private String announceStatus;
    private String refundReasonCode;
    private String requestReasonCode;
    private String requestReasonOther;
    private String reverseStatus;
    private String reverseRemark;
    private String orderNo;
    private String orderStatus;
    private String companyBranchCode;
    private String companyBranchName;
    private String eligibleFlag;
    private List<OrderBean> orderList;

    public Long getReceiveCompanyId() {
        return receiveCompanyId;
    }

    public void setReceiveCompanyId(Long receiveCompanyId) {
        this.receiveCompanyId = receiveCompanyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public Long getRefundPeriodId() {
        return refundPeriodId;
    }

    public void setRefundPeriodId(Long refundPeriodId) {
        this.refundPeriodId = refundPeriodId;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public Date getPayDateOfCntr() {
        return payDateOfCntr;
    }

    public void setPayDateOfCntr(Date payDateOfCntr) {
        this.payDateOfCntr = payDateOfCntr;
    }

    public BigDecimal getTotalWages() {
        return totalWages;
    }

    public void setTotalWages(BigDecimal totalWages) {
        this.totalWages = totalWages;
    }

    public String getCntrRate() {
        return cntrRate;
    }

    public void setCntrRate(String cntrRate) {
        this.cntrRate = cntrRate;
    }

    public int getNumOfEmployee() {
        return numOfEmployee;
    }

    public void setNumOfEmployee(int numOfEmployee) {
        this.numOfEmployee = numOfEmployee;
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

    public BigDecimal getTotalContribution() {
        return totalContribution;
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    public BigDecimal getOverPaid() {
        return overPaid;
    }

    public void setOverPaid(BigDecimal overPaid) {
        this.overPaid = overPaid;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public BigDecimal getTotalOver() {
        return totalOver;
    }

    public void setTotalOver(BigDecimal totalOver) {
        this.totalOver = totalOver;
    }

    public String getApproveNo() {
        return approveNo;
    }

    public void setApproveNo(String approveNo) {
        this.approveNo = approveNo;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getAnnounceStatus() {
        return announceStatus;
    }

    public void setAnnounceStatus(String announceStatus) {
        this.announceStatus = announceStatus;
    }

    public String getRefundReasonCode() {
        return refundReasonCode;
    }

    public void setRefundReasonCode(String refundReasonCode) {
        this.refundReasonCode = refundReasonCode;
    }

    public String getRequestReasonCode() {
        return requestReasonCode;
    }

    public void setRequestReasonCode(String requestReasonCode) {
        this.requestReasonCode = requestReasonCode;
    }

    public String getRequestReasonOther() {
        return requestReasonOther;
    }

    public void setRequestReasonOther(String requestReasonOther) {
        this.requestReasonOther = requestReasonOther;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCompanyBranchCode() {
        return companyBranchCode;
    }

    public void setCompanyBranchCode(String companyBranchCode) {
        this.companyBranchCode = companyBranchCode;
    }

    public String getCompanyBranchName() {
        return companyBranchName;
    }

    public void setCompanyBranchName(String companyBranchName) {
        this.companyBranchName = companyBranchName;
    }

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
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

    public String getEligibleFlag() {
        return eligibleFlag;
    }

    public void setEligibleFlag(String eligibleFlag) {
        this.eligibleFlag = eligibleFlag;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }
}
