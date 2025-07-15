package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class ConTbPaybackBean {
    private Long paybackRequestId;
    private Long refundRequestId;
    private Long companyId;
    private Long insurerId;
    private BigDecimal paybackTotalAmount;
    private String paybackRequestNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paybackRequestDate;
    private String paybackRequestType;
    private String paybackApproveStatus;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paybackApproveDate;
    private String paybackReceiveStatus;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paybackReceiveDate;
    private String section;
    private String status;
    private String remark;
    private String manageBy;
    private String createBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String updateBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date updateDate;
    private String reverseStatus;
    private String paybackCause;
    private BigDecimal receiveTotalAmount;
    private String depCode;
    private String paybackStatus;
    private String orderNo;
    private String paybackOrderNo;
    private String companyNo;
    private String companyName;
    private String companyBranchCode;
    private String idCardNo;
    private String fullName;
    private Long requesterCompanyId;
    private Long requester33Id;
    private Long requester39Id;

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

    public String getManageBy() {
        return manageBy;
    }

    public void setManageBy(String manageBy) {
        this.manageBy = manageBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public BigDecimal getReceiveTotalAmount() {
        return receiveTotalAmount;
    }

    public void setReceiveTotalAmount(BigDecimal receiveTotalAmount) {
        this.receiveTotalAmount = receiveTotalAmount;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getPaybackStatus() {
        return paybackStatus;
    }

    public void setPaybackStatus(String paybackStatus) {
        this.paybackStatus = paybackStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaybackOrderNo() {
        return paybackOrderNo;
    }

    public void setPaybackOrderNo(String paybackOrderNo) {
        this.paybackOrderNo = paybackOrderNo;
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

    public Long getRequesterCompanyId() {
        return requesterCompanyId;
    }

    public void setRequesterCompanyId(Long requesterCompanyId) {
        this.requesterCompanyId = requesterCompanyId;
    }

    public Long getRequester33Id() {
        return requester33Id;
    }

    public void setRequester33Id(Long requester33Id) {
        this.requester33Id = requester33Id;
    }

    public Long getRequester39Id() {
        return requester39Id;
    }

    public void setRequester39Id(Long requester39Id) {
        this.requester39Id = requester39Id;
    }
}
