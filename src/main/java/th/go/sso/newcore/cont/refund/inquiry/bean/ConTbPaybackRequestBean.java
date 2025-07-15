package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.dto.AuditBean;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class ConTbPaybackRequestBean extends AuditBean {

    private Long paybackRequestId;
    private Long refundRequestId;
    private Long companyId;
    private Long insurerId;
    private BigDecimal paybackTotalAmount;//คืนเงินสมทบ จำนวนเงินสมทบเรียกคืน
    private String paybackRequestNo;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paybackRequestDate;

    private String paybackRequestType;//ประเภทการเรียกคืน
    private String paybackApproveStatus;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paybackApproveDate;

    private String paybackReceiveStatus;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paybackReceiveDate;//คืนเงินภายในวันที่

    private String reverseStatus;
    private String paybackCause;
    private String section;
    private String status;
    private String remark;

    //refund_request
    private String receiveNo;//เลขที่ใบคำขอ
    private String fullName;
    private String idCardNo;
    private String requestReasonCode;
    private String requestReasonOther;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;//วันที่อนุมัติคืนเงิน con_tb_refund_request.approve_date

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paymentDate;//วันที่ระบุบนใบสำคัญรับเงิน วันที่จ่าย from con_tb_refund_receipt.payment_date?

    private String receiptNo;//เลขที่ใบสำคัญรับเงิน ใบสำคัญ from con_tb_refund_receipt.receipt_no เลขที่ใบสำคัญรับเงินให้ดึงจาก period รายการแรกเสมอรึเปล่า
    private BigDecimal totalAmount;//จำนวน con_tb_refund_receipt.total_amount
    private String companyNo;
    private String companyName;
    private String companyBranchCode;
    private String companyBranchName;
    private String requestType;
    private String requestChannel;
    private String depCode;
    private String orderNo;
    private BigDecimal refundTotalAmount;
    private String allStatus;
    private String paybackStatus;
    private String splitRefund;
    private String type; //แยกบริษัทหรือผู้ประกันตน >>> บริษัท = company, ผู้ประกันตน = insurer

//    private List<OrderBean> orderList;
    private List<RequesterPaybackBean> orderList;
    //refund_period
    private List<PaybackRefundPeriodBean> refundPeriodList;

    public Long getPaybackRequestId() {
        return paybackRequestId;
    }

    public void setPaybackRequestId(Long paybackRequestId) {
        this.paybackRequestId = paybackRequestId;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public BigDecimal getPaybackTotalAmount() {
        return paybackTotalAmount;
    }

    public void setPaybackTotalAmount(BigDecimal paybackTotalAmount) {
        this.paybackTotalAmount = paybackTotalAmount;
    }

    public String getPaybackRequestNo() {
        return paybackRequestNo;
    }

    public void setPaybackRequestNo(String paybackRequestNo) {
        this.paybackRequestNo = paybackRequestNo;
    }

    public Date getPaybackRequestDate() {
        return paybackRequestDate;
    }

    public void setPaybackRequestDate(Date paybackRequestDate) {
        this.paybackRequestDate = paybackRequestDate;
    }

    public String getPaybackRequestType() {
        return paybackRequestType;
    }

    public void setPaybackRequestType(String paybackRequestType) {
        this.paybackRequestType = paybackRequestType;
    }

    public String getPaybackApproveStatus() {
        return paybackApproveStatus;
    }

    public void setPaybackApproveStatus(String paybackApproveStatus) {
        this.paybackApproveStatus = paybackApproveStatus;
    }

    public Date getPaybackApproveDate() {
        return paybackApproveDate;
    }

    public void setPaybackApproveDate(Date paybackApproveDate) {
        this.paybackApproveDate = paybackApproveDate;
    }

    public String getPaybackReceiveStatus() {
        return paybackReceiveStatus;
    }

    public void setPaybackReceiveStatus(String paybackReceiveStatus) {
        this.paybackReceiveStatus = paybackReceiveStatus;
    }

    public Date getPaybackReceiveDate() {
        return paybackReceiveDate;
    }

    public void setPaybackReceiveDate(Date paybackReceiveDate) {
        this.paybackReceiveDate = paybackReceiveDate;
    }

    public String getReverseStatus() {
        return reverseStatus;
    }

    public void setReverseStatus(String reverseStatus) {
        this.reverseStatus = reverseStatus;
    }

    public String getPaybackCause() {
        return paybackCause;
    }

    public void setPaybackCause(String paybackCause) {
        this.paybackCause = paybackCause;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
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

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<PaybackRefundPeriodBean> getRefundPeriodList() {
        return refundPeriodList;
    }

    public void setRefundPeriodList(List<PaybackRefundPeriodBean> refundPeriodList) {
        this.refundPeriodList = refundPeriodList;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestChannel() {
        return requestChannel;
    }

    public void setRequestChannel(String requestChannel) {
        this.requestChannel = requestChannel;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
    }

    public String getAllStatus() {
        return allStatus;
    }

    public void setAllStatus(String allStatus) {
        this.allStatus = allStatus;
    }

    public String getPaybackStatus() {
        return paybackStatus;
    }

    public void setPaybackStatus(String paybackStatus) {
        this.paybackStatus = paybackStatus;
    }

    public List<RequesterPaybackBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<RequesterPaybackBean> orderList) {
        this.orderList = orderList;
    }

    public String getSplitRefund() {
        return splitRefund;
    }

    public void setSplitRefund(String splitRefund) {
        this.splitRefund = splitRefund;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
