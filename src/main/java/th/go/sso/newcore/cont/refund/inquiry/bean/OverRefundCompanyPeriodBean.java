package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class OverRefundCompanyPeriodBean {
    private Long refundRequestId;
    private Long refundPeriodId;
    private String refundPeriod;
    private Long companyId;
    private String companyNo;
    private String companyBranchCode;
    private String companyName;
    private String receiveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDate;
    private BigDecimal refundEmprAmount;
    private BigDecimal requestTotalAmount;
    private BigDecimal refundTotalAmount;
    private String approveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;
    private String receiptNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paymentDate;
    private String progressStatus;
    private String approveStatus;
    private BigDecimal overPaid;
    private String orderStatus;
    private String orderNo;
    private String modeRefund;
    private String reverseStatus;
    private String reverseRemark;
    private String noticeStatus;
    private String announceStatus;
    private List<OrderBean> orderList;

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

    public String getRefundPeriod() {
        return refundPeriod;
    }

    public void setRefundPeriod(String refundPeriod) {
        this.refundPeriod = refundPeriod;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyBranchCode() {
        return companyBranchCode;
    }

    public void setCompanyBranchCode(String companyBranchCode) {
        this.companyBranchCode = companyBranchCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public BigDecimal getRefundEmprAmount() {
        return refundEmprAmount;
    }

    public void setRefundEmprAmount(BigDecimal refundEmprAmount) {
        this.refundEmprAmount = refundEmprAmount;
    }

    public BigDecimal getRequestTotalAmount() {
        return requestTotalAmount;
    }

    public void setRequestTotalAmount(BigDecimal requestTotalAmount) {
        this.requestTotalAmount = requestTotalAmount;
    }

    public BigDecimal getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public BigDecimal getOverPaid() {
        return overPaid;
    }

    public void setOverPaid(BigDecimal overPaid) {
        this.overPaid = overPaid;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getModeRefund() {
        return modeRefund;
    }

    public void setModeRefund(String modeRefund) {
        this.modeRefund = modeRefund;
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
}
