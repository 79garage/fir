package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class ConTbRefundRequestChangeBean {
    private Long refundRequestChangeId;
    private Long refundRequestChangeTypeId;
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
    private String refundRemark;
    private String requestType;
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
    private String announceCreateBy;
    private String announceArCreateBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceCreateDate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceArCreateDate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date cancelApproveDate;
    private String refundFlag;
    private String distCode;
    private String subdistCode;
    private String provinceCode;
    private String zipCode;
    private String departmentReceiveCode;
    private String departmentResponsibleCode;
    private String refundBankCode;
    private String refundBankBranchCode;
    private String eligibleFlag;

    private String receiveNoChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDateChange;
    private String noticeNoChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date noticeDateChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date payDateChange;
    private String refundPromptPayChange;
    private String refundPoOrderChange;
    private String refundBankAccountChange;
    private String refundTypePlaceChange;
    private String refundTransferBankChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date refundTransferDateChange;
    private String requestReasonCodeChange;
    private String requestReasonOtherChange;
    private String approveNoChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDateChange;
    private String approveStatusChange;
    private String noticeStatusChange;
    private String announceStatusChange;
    private String refundRemarkChange;
    private String requestTypeChange;
    private String requestChannelChange;
    private String disapproveReasonChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date replyDateChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date recordApproveDateChange;
    private String recordApproveByChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date noticeArDateChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceArDateChange;
    private String announceNoChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceDateChange;
    private String announceCreateByChange;
    private String announceArCreateByChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceCreateDateChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceArCreateDateChange;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date cancelApproveDateChange;
    private String refundFlagChange;
    private String distCodeChange;
    private String subdistCodeChange;
    private String provinceCodeChange;
    private String zipCodeChange;
    private String departmentReceiveCodeChange;
    private String departmentResponsibleCodeChange;
    private String refundBankCodeChange;
    private String refundBankBranchCodeChange;
    private String eligibleFlagChange;
    private String refundOperationCode;
    private String sourceDate;
    private String manageBy;
    private String createBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String updateBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date updateDate;

    public Long getRefundRequestChangeId() {
        return refundRequestChangeId;
    }

    public void setRefundRequestChangeId(Long refundRequestChangeId) {
        this.refundRequestChangeId = refundRequestChangeId;
    }

    public Long getRefundRequestChangeTypeId() {
        return refundRequestChangeTypeId;
    }

    public void setRefundRequestChangeTypeId(Long refundRequestChangeTypeId) {
        this.refundRequestChangeTypeId = refundRequestChangeTypeId;
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

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
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

    public Date getCancelApproveDate() {
        return cancelApproveDate;
    }

    public void setCancelApproveDate(Date cancelApproveDate) {
        this.cancelApproveDate = cancelApproveDate;
    }

    public String getRefundFlag() {
        return refundFlag;
    }

    public void setRefundFlag(String refundFlag) {
        this.refundFlag = refundFlag;
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

    public String getRefundBankCode() {
        return refundBankCode;
    }

    public void setRefundBankCode(String refundBankCode) {
        this.refundBankCode = refundBankCode;
    }

    public String getRefundBankBranchCode() {
        return refundBankBranchCode;
    }

    public void setRefundBankBranchCode(String refundBankBranchCode) {
        this.refundBankBranchCode = refundBankBranchCode;
    }

    public String getEligibleFlag() {
        return eligibleFlag;
    }

    public void setEligibleFlag(String eligibleFlag) {
        this.eligibleFlag = eligibleFlag;
    }

    public String getReceiveNoChange() {
        return receiveNoChange;
    }

    public void setReceiveNoChange(String receiveNoChange) {
        this.receiveNoChange = receiveNoChange;
    }

    public Date getReceiveDateChange() {
        return receiveDateChange;
    }

    public void setReceiveDateChange(Date receiveDateChange) {
        this.receiveDateChange = receiveDateChange;
    }

    public String getNoticeNoChange() {
        return noticeNoChange;
    }

    public void setNoticeNoChange(String noticeNoChange) {
        this.noticeNoChange = noticeNoChange;
    }

    public Date getNoticeDateChange() {
        return noticeDateChange;
    }

    public void setNoticeDateChange(Date noticeDateChange) {
        this.noticeDateChange = noticeDateChange;
    }

    public Date getPayDateChange() {
        return payDateChange;
    }

    public void setPayDateChange(Date payDateChange) {
        this.payDateChange = payDateChange;
    }

    public String getRefundPromptPayChange() {
        return refundPromptPayChange;
    }

    public void setRefundPromptPayChange(String refundPromptPayChange) {
        this.refundPromptPayChange = refundPromptPayChange;
    }

    public String getRefundPoOrderChange() {
        return refundPoOrderChange;
    }

    public void setRefundPoOrderChange(String refundPoOrderChange) {
        this.refundPoOrderChange = refundPoOrderChange;
    }

    public String getRefundBankAccountChange() {
        return refundBankAccountChange;
    }

    public void setRefundBankAccountChange(String refundBankAccountChange) {
        this.refundBankAccountChange = refundBankAccountChange;
    }

    public String getRefundTypePlaceChange() {
        return refundTypePlaceChange;
    }

    public void setRefundTypePlaceChange(String refundTypePlaceChange) {
        this.refundTypePlaceChange = refundTypePlaceChange;
    }

    public String getRefundTransferBankChange() {
        return refundTransferBankChange;
    }

    public void setRefundTransferBankChange(String refundTransferBankChange) {
        this.refundTransferBankChange = refundTransferBankChange;
    }

    public Date getRefundTransferDateChange() {
        return refundTransferDateChange;
    }

    public void setRefundTransferDateChange(Date refundTransferDateChange) {
        this.refundTransferDateChange = refundTransferDateChange;
    }

    public String getRequestReasonCodeChange() {
        return requestReasonCodeChange;
    }

    public void setRequestReasonCodeChange(String requestReasonCodeChange) {
        this.requestReasonCodeChange = requestReasonCodeChange;
    }

    public String getRequestReasonOtherChange() {
        return requestReasonOtherChange;
    }

    public void setRequestReasonOtherChange(String requestReasonOtherChange) {
        this.requestReasonOtherChange = requestReasonOtherChange;
    }

    public String getApproveNoChange() {
        return approveNoChange;
    }

    public void setApproveNoChange(String approveNoChange) {
        this.approveNoChange = approveNoChange;
    }

    public Date getApproveDateChange() {
        return approveDateChange;
    }

    public void setApproveDateChange(Date approveDateChange) {
        this.approveDateChange = approveDateChange;
    }

    public String getApproveStatusChange() {
        return approveStatusChange;
    }

    public void setApproveStatusChange(String approveStatusChange) {
        this.approveStatusChange = approveStatusChange;
    }

    public String getNoticeStatusChange() {
        return noticeStatusChange;
    }

    public void setNoticeStatusChange(String noticeStatusChange) {
        this.noticeStatusChange = noticeStatusChange;
    }

    public String getAnnounceStatusChange() {
        return announceStatusChange;
    }

    public void setAnnounceStatusChange(String announceStatusChange) {
        this.announceStatusChange = announceStatusChange;
    }

    public String getRefundRemarkChange() {
        return refundRemarkChange;
    }

    public void setRefundRemarkChange(String refundRemarkChange) {
        this.refundRemarkChange = refundRemarkChange;
    }

    public String getRequestTypeChange() {
        return requestTypeChange;
    }

    public void setRequestTypeChange(String requestTypeChange) {
        this.requestTypeChange = requestTypeChange;
    }

    public String getRequestChannelChange() {
        return requestChannelChange;
    }

    public void setRequestChannelChange(String requestChannelChange) {
        this.requestChannelChange = requestChannelChange;
    }

    public String getDisapproveReasonChange() {
        return disapproveReasonChange;
    }

    public void setDisapproveReasonChange(String disapproveReasonChange) {
        this.disapproveReasonChange = disapproveReasonChange;
    }

    public Date getReplyDateChange() {
        return replyDateChange;
    }

    public void setReplyDateChange(Date replyDateChange) {
        this.replyDateChange = replyDateChange;
    }

    public Date getRecordApproveDateChange() {
        return recordApproveDateChange;
    }

    public void setRecordApproveDateChange(Date recordApproveDateChange) {
        this.recordApproveDateChange = recordApproveDateChange;
    }

    public String getRecordApproveByChange() {
        return recordApproveByChange;
    }

    public void setRecordApproveByChange(String recordApproveByChange) {
        this.recordApproveByChange = recordApproveByChange;
    }

    public Date getNoticeArDateChange() {
        return noticeArDateChange;
    }

    public void setNoticeArDateChange(Date noticeArDateChange) {
        this.noticeArDateChange = noticeArDateChange;
    }

    public Date getAnnounceArDateChange() {
        return announceArDateChange;
    }

    public void setAnnounceArDateChange(Date announceArDateChange) {
        this.announceArDateChange = announceArDateChange;
    }

    public String getAnnounceNoChange() {
        return announceNoChange;
    }

    public void setAnnounceNoChange(String announceNoChange) {
        this.announceNoChange = announceNoChange;
    }

    public Date getAnnounceDateChange() {
        return announceDateChange;
    }

    public void setAnnounceDateChange(Date announceDateChange) {
        this.announceDateChange = announceDateChange;
    }

    public String getAnnounceCreateByChange() {
        return announceCreateByChange;
    }

    public void setAnnounceCreateByChange(String announceCreateByChange) {
        this.announceCreateByChange = announceCreateByChange;
    }

    public String getAnnounceArCreateByChange() {
        return announceArCreateByChange;
    }

    public void setAnnounceArCreateByChange(String announceArCreateByChange) {
        this.announceArCreateByChange = announceArCreateByChange;
    }

    public Date getAnnounceCreateDateChange() {
        return announceCreateDateChange;
    }

    public void setAnnounceCreateDateChange(Date announceCreateDateChange) {
        this.announceCreateDateChange = announceCreateDateChange;
    }

    public Date getAnnounceArCreateDateChange() {
        return announceArCreateDateChange;
    }

    public void setAnnounceArCreateDateChange(Date announceArCreateDateChange) {
        this.announceArCreateDateChange = announceArCreateDateChange;
    }

    public Date getCancelApproveDateChange() {
        return cancelApproveDateChange;
    }

    public void setCancelApproveDateChange(Date cancelApproveDateChange) {
        this.cancelApproveDateChange = cancelApproveDateChange;
    }

    public String getRefundFlagChange() {
        return refundFlagChange;
    }

    public void setRefundFlagChange(String refundFlagChange) {
        this.refundFlagChange = refundFlagChange;
    }

    public String getDistCodeChange() {
        return distCodeChange;
    }

    public void setDistCodeChange(String distCodeChange) {
        this.distCodeChange = distCodeChange;
    }

    public String getSubdistCodeChange() {
        return subdistCodeChange;
    }

    public void setSubdistCodeChange(String subdistCodeChange) {
        this.subdistCodeChange = subdistCodeChange;
    }

    public String getProvinceCodeChange() {
        return provinceCodeChange;
    }

    public void setProvinceCodeChange(String provinceCodeChange) {
        this.provinceCodeChange = provinceCodeChange;
    }

    public String getZipCodeChange() {
        return zipCodeChange;
    }

    public void setZipCodeChange(String zipCodeChange) {
        this.zipCodeChange = zipCodeChange;
    }

    public String getDepartmentReceiveCodeChange() {
        return departmentReceiveCodeChange;
    }

    public void setDepartmentReceiveCodeChange(String departmentReceiveCodeChange) {
        this.departmentReceiveCodeChange = departmentReceiveCodeChange;
    }

    public String getDepartmentResponsibleCodeChange() {
        return departmentResponsibleCodeChange;
    }

    public void setDepartmentResponsibleCodeChange(String departmentResponsibleCodeChange) {
        this.departmentResponsibleCodeChange = departmentResponsibleCodeChange;
    }

    public String getRefundBankCodeChange() {
        return refundBankCodeChange;
    }

    public void setRefundBankCodeChange(String refundBankCodeChange) {
        this.refundBankCodeChange = refundBankCodeChange;
    }

    public String getRefundBankBranchCodeChange() {
        return refundBankBranchCodeChange;
    }

    public void setRefundBankBranchCodeChange(String refundBankBranchCodeChange) {
        this.refundBankBranchCodeChange = refundBankBranchCodeChange;
    }

    public String getEligibleFlagChange() {
        return eligibleFlagChange;
    }

    public void setEligibleFlagChange(String eligibleFlagChange) {
        this.eligibleFlagChange = eligibleFlagChange;
    }

    public String getRefundOperationCode() {
        return refundOperationCode;
    }

    public void setRefundOperationCode(String refundOperationCode) {
        this.refundOperationCode = refundOperationCode;
    }

    public String getSourceDate() {
        return sourceDate;
    }

    public void setSourceDate(String sourceDate) {
        this.sourceDate = sourceDate;
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
}
