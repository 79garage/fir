package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class PaybackRefundPeriodBean {
    private Long refundPeriodId;
    private Long refundRequestId;
    private Long refundCompanyId;
    private Long refundEmployeeId;
    private Long refundInsurerId;
    private String refundPeriod;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;//วันที่อนุมัติคืนเงิน
    private String receiptNo;//ใบสำคัญ;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paymentDate;//วันที่จ่าย
    private BigDecimal refundTotalAmount;//ยอดจ่ายคืน
    private BigDecimal paybackAmount;//ยอดเรียกคืน
    private String orderNo;
    private String orderStatus;
    private Long requesterId;
    private String fullName;
    private String companyBranchName;
    private Long paybackPeriodId;
    private String relationGroup;
    private String paybackStatus;
    private String paybackOrderNo;

    public Long getRefundPeriodId() {
        return refundPeriodId;
    }

    public void setRefundPeriodId(Long refundPeriodId) {
        this.refundPeriodId = refundPeriodId;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public String getRefundPeriod() {
        return refundPeriod;
    }

    public void setRefundPeriod(String refundPeriod) {
        this.refundPeriod = refundPeriod;
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

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public BigDecimal getPaybackAmount() {
        return paybackAmount;
    }

    public void setPaybackAmount(BigDecimal paybackAmount) {
        this.paybackAmount = paybackAmount;
    }

    public BigDecimal getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
    }

    public Long getRefundCompanyId() {
        return refundCompanyId;
    }

    public void setRefundCompanyId(Long refundCompanyId) {
        this.refundCompanyId = refundCompanyId;
    }

    public Long getRefundEmployeeId() {
        return refundEmployeeId;
    }

    public void setRefundEmployeeId(Long refundEmployeeId) {
        this.refundEmployeeId = refundEmployeeId;
    }

    public Long getRefundInsurerId() {
        return refundInsurerId;
    }

    public void setRefundInsurerId(Long refundInsurerId) {
        this.refundInsurerId = refundInsurerId;
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

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyBranchName() {
        return companyBranchName;
    }

    public void setCompanyBranchName(String companyBranchName) {
        this.companyBranchName = companyBranchName;
    }

    public Long getPaybackPeriodId() {
        return paybackPeriodId;
    }

    public void setPaybackPeriodId(Long paybackPeriodId) {
        this.paybackPeriodId = paybackPeriodId;
    }

    public String getRelationGroup() {
        return relationGroup;
    }

    public void setRelationGroup(String relationGroup) {
        this.relationGroup = relationGroup;
    }

    public String getPaybackStatus() {
        return paybackStatus;
    }

    public void setPaybackStatus(String paybackStatus) {
        this.paybackStatus = paybackStatus;
    }

    public String getPaybackOrderNo() {
        return paybackOrderNo;
    }

    public void setPaybackOrderNo(String paybackOrderNo) {
        this.paybackOrderNo = paybackOrderNo;
    }
}
