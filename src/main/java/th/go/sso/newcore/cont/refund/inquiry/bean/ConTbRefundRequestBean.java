package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class ConTbRefundRequestBean {
    private Long refundRequestId;
    private Long companyId;
    private Long insurerId;
    private Long refundBankId;
    private Long refundBankBranchId;
    private String receiveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDate;
    private String noticeNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date noticeDate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date payDate;
    private String refundPromptPay;
    private String refundPoOrder;
    private String refundBankAccount;
    private String refundTypePlace;
    private String refundTransferBank;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date refundTransferDate;
    private String requestReasonCode;
    private String requestReasonOther;
    private String approveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;
    private String approveStatus;
    private String noticeStatus;
    private String announceStatus;
    private String section;
    private String refundRemark;
    private String createBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String updateBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date updateDate;
    private String requestType;
    private String status;
    private String requestChannel;
    private String disapproveReason;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date replyDate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date recordApproveDate;
    private String recordApproveBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date noticeArDate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceArDate;
    private String announceNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceDate;
    private String splitRefund;
    private Long refRefundRequestId;
    private String refundFlag;
    private String manageBy;
    private String orderFinanceStatus;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date orderFinanceDate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date cancelApproveDate;
    private String reverseStatus;
    private String reasonCode;
    private String requestTypeReasonCode;
    private String paybackStatus;
    private BigDecimal refundCntrAmount;
    private BigDecimal refundSenilityAmount;
    private BigDecimal refundSavingAmount;
    private BigDecimal refundTotalAmount;
    private BigDecimal increaseAmount;
    private String reverseRemark;
    private String distCode;
    private String subdistCode;
    private String provinceCode;
    private String zipCode;
    private String departmentReceiveCode;
    private String departmentResponsibleCode;
    private String modeRefund;
    private String announceCreateBy;
    private String announceArCreateBy;
    private Date announceCreateDate;
    private Date announceArCreateDate;
    private String eligibleFlag;
    private String refundPoOrderName;
    private String periodStart;
    private String periodEnd;
    private BigDecimal requestAmount;
    private String periodStartMonth;
    private String periodStartYear;
    private String periodEndMonth;
    private String periodEndYear;

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

    public Long getRefundBankId() {
        return refundBankId;
    }

    public void setRefundBankId(Long refundBankId) {
        this.refundBankId = refundBankId;
    }

    public Long getRefundBankBranchId() {
        return refundBankBranchId;
    }

    public void setRefundBankBranchId(Long refundBankBranchId) {
        this.refundBankBranchId = refundBankBranchId;
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

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getRefundPromptPay() {
        return refundPromptPay;
    }

    public void setRefundPromptPay(String refundPromptPay) {
        this.refundPromptPay = refundPromptPay;
    }

    public String getRefundPoOrder() {
        return refundPoOrder;
    }

    public void setRefundPoOrder(String refundPoOrder) {
        this.refundPoOrder = refundPoOrder;
    }

    public String getRefundBankAccount() {
        return refundBankAccount;
    }

    public void setRefundBankAccount(String refundBankAccount) {
        this.refundBankAccount = refundBankAccount;
    }

    public String getRefundTypePlace() {
        return refundTypePlace;
    }

    public void setRefundTypePlace(String refundTypePlace) {
        this.refundTypePlace = refundTypePlace;
    }

    public String getRefundTransferBank() {
        return refundTransferBank;
    }

    public void setRefundTransferBank(String refundTransferBank) {
        this.refundTransferBank = refundTransferBank;
    }

    public Date getRefundTransferDate() {
        return refundTransferDate;
    }

    public void setRefundTransferDate(Date refundTransferDate) {
        this.refundTransferDate = refundTransferDate;
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

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
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

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestChannel() {
        return requestChannel;
    }

    public void setRequestChannel(String requestChannel) {
        this.requestChannel = requestChannel;
    }

    public String getDisapproveReason() {
        return disapproveReason;
    }

    public void setDisapproveReason(String disapproveReason) {
        this.disapproveReason = disapproveReason;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public Date getRecordApproveDate() {
        return recordApproveDate;
    }

    public void setRecordApproveDate(Date recordApproveDate) {
        this.recordApproveDate = recordApproveDate;
    }

    public String getRecordApproveBy() {
        return recordApproveBy;
    }

    public void setRecordApproveBy(String recordApproveBy) {
        this.recordApproveBy = recordApproveBy;
    }

    public Date getNoticeArDate() {
        return noticeArDate;
    }

    public void setNoticeArDate(Date noticeArDate) {
        this.noticeArDate = noticeArDate;
    }

    public Date getAnnounceArDate() {
        return announceArDate;
    }

    public void setAnnounceArDate(Date announceArDate) {
        this.announceArDate = announceArDate;
    }

    public String getAnnounceNo() {
        return announceNo;
    }

    public void setAnnounceNo(String announceNo) {
        this.announceNo = announceNo;
    }

    public Date getAnnounceDate() {
        return announceDate;
    }

    public void setAnnounceDate(Date announceDate) {
        this.announceDate = announceDate;
    }

    public String getSplitRefund() {
        return splitRefund;
    }

    public void setSplitRefund(String splitRefund) {
        this.splitRefund = splitRefund;
    }

    public Long getRefRefundRequestId() {
        return refRefundRequestId;
    }

    public void setRefRefundRequestId(Long refRefundRequestId) {
        this.refRefundRequestId = refRefundRequestId;
    }

    public String getRefundFlag() {
        return refundFlag;
    }

    public void setRefundFlag(String refundFlag) {
        this.refundFlag = refundFlag;
    }

    public String getManageBy() {
        return manageBy;
    }

    public void setManageBy(String manageBy) {
        this.manageBy = manageBy;
    }

    public String getOrderFinanceStatus() {
        return orderFinanceStatus;
    }

    public void setOrderFinanceStatus(String orderFinanceStatus) {
        this.orderFinanceStatus = orderFinanceStatus;
    }

    public Date getOrderFinanceDate() {
        return orderFinanceDate;
    }

    public void setOrderFinanceDate(Date orderFinanceDate) {
        this.orderFinanceDate = orderFinanceDate;
    }

    public Date getCancelApproveDate() {
        return cancelApproveDate;
    }

    public void setCancelApproveDate(Date cancelApproveDate) {
        this.cancelApproveDate = cancelApproveDate;
    }

    public String getReverseStatus() {
        return reverseStatus;
    }

    public void setReverseStatus(String reverseStatus) {
        this.reverseStatus = reverseStatus;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getRequestTypeReasonCode() {
        return requestTypeReasonCode;
    }

    public void setRequestTypeReasonCode(String requestTypeReasonCode) {
        this.requestTypeReasonCode = requestTypeReasonCode;
    }

    public String getPaybackStatus() {
        return paybackStatus;
    }

    public void setPaybackStatus(String paybackStatus) {
        this.paybackStatus = paybackStatus;
    }

    public BigDecimal getRefundCntrAmount() {
        return refundCntrAmount;
    }

    public void setRefundCntrAmount(BigDecimal refundCntrAmount) {
        this.refundCntrAmount = refundCntrAmount;
    }

    public BigDecimal getRefundSenilityAmount() {
        return refundSenilityAmount;
    }

    public void setRefundSenilityAmount(BigDecimal refundSenilityAmount) {
        this.refundSenilityAmount = refundSenilityAmount;
    }

    public BigDecimal getRefundSavingAmount() {
        return refundSavingAmount;
    }

    public void setRefundSavingAmount(BigDecimal refundSavingAmount) {
        this.refundSavingAmount = refundSavingAmount;
    }

    public BigDecimal getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
    }

    public BigDecimal getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(BigDecimal increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public String getReverseRemark() {
        return reverseRemark;
    }

    public void setReverseRemark(String reverseRemark) {
        this.reverseRemark = reverseRemark;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getSubdistCode() {
        return subdistCode;
    }

    public void setSubdistCode(String subdistCode) {
        this.subdistCode = subdistCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDepartmentReceiveCode() {
        return departmentReceiveCode;
    }

    public void setDepartmentReceiveCode(String departmentReceiveCode) {
        this.departmentReceiveCode = departmentReceiveCode;
    }

    public String getDepartmentResponsibleCode() {
        return departmentResponsibleCode;
    }

    public void setDepartmentResponsibleCode(String departmentResponsibleCode) {
        this.departmentResponsibleCode = departmentResponsibleCode;
    }

    public String getModeRefund() {
        return modeRefund;
    }

    public void setModeRefund(String modeRefund) {
        this.modeRefund = modeRefund;
    }

    public String getAnnounceCreateBy() {
        return announceCreateBy;
    }

    public void setAnnounceCreateBy(String announceCreateBy) {
        this.announceCreateBy = announceCreateBy;
    }

    public String getAnnounceArCreateBy() {
        return announceArCreateBy;
    }

    public void setAnnounceArCreateBy(String announceArCreateBy) {
        this.announceArCreateBy = announceArCreateBy;
    }

    public Date getAnnounceCreateDate() {
        return announceCreateDate;
    }

    public void setAnnounceCreateDate(Date announceCreateDate) {
        this.announceCreateDate = announceCreateDate;
    }

    public Date getAnnounceArCreateDate() {
        return announceArCreateDate;
    }

    public void setAnnounceArCreateDate(Date announceArCreateDate) {
        this.announceArCreateDate = announceArCreateDate;
    }

    public String getEligibleFlag() {
        return eligibleFlag;
    }

    public void setEligibleFlag(String eligibleFlag) {
        this.eligibleFlag = eligibleFlag;
    }

    public String getRefundPoOrderName() {
        return refundPoOrderName;
    }

    public void setRefundPoOrderName(String refundPoOrderName) {
        this.refundPoOrderName = refundPoOrderName;
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
    }

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getPeriodStartMonth() {
        return periodStartMonth;
    }

    public void setPeriodStartMonth(String periodStartMonth) {
        this.periodStartMonth = periodStartMonth;
    }

    public String getPeriodStartYear() {
        return periodStartYear;
    }

    public void setPeriodStartYear(String periodStartYear) {
        this.periodStartYear = periodStartYear;
    }

    public String getPeriodEndMonth() {
        return periodEndMonth;
    }

    public void setPeriodEndMonth(String periodEndMonth) {
        this.periodEndMonth = periodEndMonth;
    }

    public String getPeriodEndYear() {
        return periodEndYear;
    }

    public void setPeriodEndYear(String periodEndYear) {
        this.periodEndYear = periodEndYear;
    }
}
