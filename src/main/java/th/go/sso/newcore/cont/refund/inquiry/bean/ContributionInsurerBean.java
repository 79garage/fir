package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class ContributionInsurerBean {

    private Long receiveInsurerId;
    private Long refundRequestId;
    private String payPeriod;
    private BigDecimal cntrAmount;
    private BigDecimal interestPaid;
    private BigDecimal totalContribution;
    private BigDecimal empeOverp;
    private BigDecimal overInterestPaid;
    private BigDecimal totalOver;
    private String approveNo;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;
    private String receiptNo;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date payDate;
    private String progressStatus;
    private String noticeStatus;
    private String announceStatus;
    private String section; // 1 = con_tr_receive_insurer
    private String reverseStatus;
    private String reverseRemark;
    private List<OrderBean> orderList;

    public Long getReceiveInsurerId() {
        return receiveInsurerId;
    }

    public void setReceiveInsurerId(Long receiveInsurerId) {
        this.receiveInsurerId = receiveInsurerId;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
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

    public BigDecimal getEmpeOverp() {
        return empeOverp;
    }

    public void setEmpeOverp(BigDecimal empeOverp) {
        this.empeOverp = empeOverp;
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
}
