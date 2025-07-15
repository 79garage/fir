package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class SplitEmployeeRefundCompanyRequestBean {

    private Long refundRequestId;

    private Long departmentReceiveId;
    private String departmentReceiveCode;
    private Long departmentResponsibleId;
    private String ssoBranchCode;
    private String ssoBranchName;
    private String ssoBranchDescription;

    private String receiveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDate;
    private Long companyId;
    private String companyNo;
    private String companyName;
    private String companyDescription;

    private String companyBranchCode;
    private String companyBranchName;
    private String companyBranchDescription;

    private String noticeNo;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date noticeDate;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date payDate;

    private String approveNo;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;
    private String approveStatus;
    private String noticeStatus;
    private String announceStatus;
    private String requestType;
    private String refundTypePlace;
    private String provinceCode;
    private String distCode;
    private String subDistCode;
    private String refundPoOrder;
    private String refundPromptPay;

    private Long refundBankId;
    private String refundBankCode;
    private Long refundBankBranchId;
    private String refundBankBranchCode;
    private Long refundBankAccount;
    private String refundTransferBank;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date refundTransferDate;
    private String requestReasonCode;
    private String requestReasonOther;
    private String refundRemark;

    private BigDecimal requestEmpeAmount;
    private BigDecimal requestTotalEmpeAmount;
    private BigDecimal requestTotalAmount;

    private BigDecimal refundEmpeAmount;
    private BigDecimal refundTotalEmpeAmount;
    private BigDecimal refundTotalAmount;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundUnemploymentAmount;
    private BigDecimal refundInterestAmount;

    private String requestChannel;
    private String disapproveReason;
    private String progressStatus;
    private String createBy;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String updateBy;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date updateDate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date replyDate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date noticeArDate;
    private String announceNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceDate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceArDate;
    private String recordApproveBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date recordApproveDate;
    private String splitRefund;
    private String section;

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public Long getDepartmentReceiveId() {
        return departmentReceiveId;
    }

    public void setDepartmentReceiveId(Long departmentReceiveId) {
        this.departmentReceiveId = departmentReceiveId;
    }

    public String getDepartmentReceiveCode() {
        return departmentReceiveCode;
    }

    public void setDepartmentReceiveCode(String departmentReceiveCode) {
        this.departmentReceiveCode = departmentReceiveCode;
    }

    public Long getDepartmentResponsibleId() {
        return departmentResponsibleId;
    }

    public void setDepartmentResponsibleId(Long departmentResponsibleId) {
        this.departmentResponsibleId = departmentResponsibleId;
    }

    public String getSsoBranchCode() {
        return ssoBranchCode;
    }

    public void setSsoBranchCode(String ssoBranchCode) {
        this.ssoBranchCode = ssoBranchCode;
    }

    public String getSsoBranchName() {
        return ssoBranchName;
    }

    public void setSsoBranchName(String ssoBranchName) {
        this.ssoBranchName = ssoBranchName;
    }

    public String getSsoBranchDescription() {
        return ssoBranchDescription;
    }

    public void setSsoBranchDescription(String ssoBranchDescription) {
        this.ssoBranchDescription = ssoBranchDescription;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
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

    public String getCompanyBranchDescription() {
        return companyBranchDescription;
    }

    public void setCompanyBranchDescription(String companyBranchDescription) {
        this.companyBranchDescription = companyBranchDescription;
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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRefundTypePlace() {
        return refundTypePlace;
    }

    public void setRefundTypePlace(String refundTypePlace) {
        this.refundTypePlace = refundTypePlace;
    }

    public String getRefundPoOrder() {
        return refundPoOrder;
    }

    public void setRefundPoOrder(String refundPoOrder) {
        this.refundPoOrder = refundPoOrder;
    }

    public String getRefundPromptPay() {
        return refundPromptPay;
    }

    public void setRefundPromptPay(String refundPromptPay) {
        this.refundPromptPay = refundPromptPay;
    }

    public Long getRefundBankId() {
        return refundBankId;
    }

    public void setRefundBankId(Long refundBankId) {
        this.refundBankId = refundBankId;
    }

    public String getRefundBankCode() {
        return refundBankCode;
    }

    public void setRefundBankCode(String refundBankCode) {
        this.refundBankCode = refundBankCode;
    }

    public Long getRefundBankBranchId() {
        return refundBankBranchId;
    }

    public void setRefundBankBranchId(Long refundBankBranchId) {
        this.refundBankBranchId = refundBankBranchId;
    }

    public String getRefundBankBranchCode() {
        return refundBankBranchCode;
    }

    public void setRefundBankBranchCode(String refundBankBranchCode) {
        this.refundBankBranchCode = refundBankBranchCode;
    }

    public Long getRefundBankAccount() {
        return refundBankAccount;
    }

    public void setRefundBankAccount(Long refundBankAccount) {
        this.refundBankAccount = refundBankAccount;
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

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }

    public BigDecimal getRequestEmpeAmount() {
        return requestEmpeAmount;
    }

    public void setRequestEmpeAmount(BigDecimal requestEmpeAmount) {
        this.requestEmpeAmount = requestEmpeAmount;
    }

    public BigDecimal getRequestTotalEmpeAmount() {
        return requestTotalEmpeAmount;
    }

    public void setRequestTotalEmpeAmount(BigDecimal requestTotalEmpeAmount) {
        this.requestTotalEmpeAmount = requestTotalEmpeAmount;
    }

    public BigDecimal getRequestTotalAmount() {
        return requestTotalAmount;
    }

    public void setRequestTotalAmount(BigDecimal requestTotalAmount) {
        this.requestTotalAmount = requestTotalAmount;
    }

    public BigDecimal getRefundEmpeAmount() {
        return refundEmpeAmount;
    }

    public void setRefundEmpeAmount(BigDecimal refundEmpeAmount) {
        this.refundEmpeAmount = refundEmpeAmount;
    }

    public BigDecimal getRefundTotalEmpeAmount() {
        return refundTotalEmpeAmount;
    }

    public void setRefundTotalEmpeAmount(BigDecimal refundTotalEmpeAmount) {
        this.refundTotalEmpeAmount = refundTotalEmpeAmount;
    }

    public BigDecimal getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
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

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
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

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public Date getNoticeArDate() {
        return noticeArDate;
    }

    public void setNoticeArDate(Date noticeArDate) {
        this.noticeArDate = noticeArDate;
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

    public Date getAnnounceArDate() {
        return announceArDate;
    }

    public void setAnnounceArDate(Date announceArDate) {
        this.announceArDate = announceArDate;
    }

    public String getRecordApproveBy() {
        return recordApproveBy;
    }

    public void setRecordApproveBy(String recordApproveBy) {
        this.recordApproveBy = recordApproveBy;
    }

    public Date getRecordApproveDate() {
        return recordApproveDate;
    }

    public void setRecordApproveDate(Date recordApproveDate) {
        this.recordApproveDate = recordApproveDate;
    }

    public String getSplitRefund() {
        return splitRefund;
    }

    public void setSplitRefund(String splitRefund) {
        this.splitRefund = splitRefund;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public BigDecimal getRefundSicknessAmount() {
        return refundSicknessAmount;
    }

    public void setRefundSicknessAmount(BigDecimal refundSicknessAmount) {
        this.refundSicknessAmount = refundSicknessAmount;
    }

    public BigDecimal getRefundRetirementAmount() {
        return refundRetirementAmount;
    }

    public void setRefundRetirementAmount(BigDecimal refundRetirementAmount) {
        this.refundRetirementAmount = refundRetirementAmount;
    }

    public BigDecimal getRefundUnemploymentAmount() {
        return refundUnemploymentAmount;
    }

    public void setRefundUnemploymentAmount(BigDecimal refundUnemploymentAmount) {
        this.refundUnemploymentAmount = refundUnemploymentAmount;
    }

    public BigDecimal getRefundInterestAmount() {
        return refundInterestAmount;
    }

    public void setRefundInterestAmount(BigDecimal refundInterestAmount) {
        this.refundInterestAmount = refundInterestAmount;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getSubDistCode() {
        return subDistCode;
    }

    public void setSubDistCode(String subDistCode) {
        this.subDistCode = subDistCode;
    }
}
