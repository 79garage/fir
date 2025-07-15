package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class RefundCompanyRequestBean {

    private Long refundRequestId;

//    private Long departmentReceiveId;
    private String departmentReceiveCode;
//    private Long departmentResponsibleId;
    private String departmentResponsibleCode;
    private String ssoBranchCode;
    private String ssoBranchName;
    private String ssoBranchDescription;

    private String receiveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDate;
    private String companyNo;
    private String companyName;
    private String companyDescription;

    private String companyBranchCode;
    private String companyBranchName;
    private String companyBranchDescription;
    private String businessType;

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
    private String provinceName;
    private String distName;
    private String subDistName;
    private String refundPoOrder;
    private String refundPromptPay;

    private Long refundBankId;
    private String refundBankCode;
    private Long refundBankBranchId;
    private String refundBankBranchCode;
    private String refundBankAccount;
    private String refundTransferBank;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date refundTransferDate;
    private String requestReasonCode;
    private String requestReasonOther;
    private String refundRemark;

    private BigDecimal requestEmprAmount = BigDecimal.ZERO;
    private BigDecimal requestInterestAmount = BigDecimal.ZERO;
    private BigDecimal requestOverPaidAmount = BigDecimal.ZERO;
    private BigDecimal requestTotalEmprAmount = BigDecimal.ZERO;
    private BigDecimal requestEmpeAmount = BigDecimal.ZERO;
    private BigDecimal requestTotalEmpeAmount = BigDecimal.ZERO;
    private BigDecimal requestTotalAmount = BigDecimal.ZERO;
    private BigDecimal refundEmprAmount = BigDecimal.ZERO;
    private BigDecimal refundOverPaidAmount = BigDecimal.ZERO;
    private BigDecimal refundTotalEmprAmount = BigDecimal.ZERO;
    private BigDecimal refundEmpeAmount = BigDecimal.ZERO;
    private BigDecimal refundTotalEmpeAmount = BigDecimal.ZERO;
    private BigDecimal refundTotalAmount = BigDecimal.ZERO;
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
    private String announceArCreateBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date announceArCreateDate;
    private String recordApproveBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date recordApproveDate;
    private String splitRefund;
    private String section;
    private String reverseStatus;
    private String reverseRemark;
    private String orderRemark;
    private String orderStatus;
    private String orderNo;
    private String eligibleFlag;
    private Boolean isEligible;
    private String refundPoOrderName;
    private String periodStart;
    private String periodEnd;
    private BigDecimal requestAmount;
    private List<ConTbRequesterBean> requesterEligibleList;
    private List<ConTbRequesterBean> requesterList;

    public String getDepartmentReceiveCode() {
        return departmentReceiveCode;
    }

    public void setDepartmentReceiveCode(String departmentReceiveCode) {
        this.departmentReceiveCode = departmentReceiveCode;
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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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

    public BigDecimal getRequestEmprAmount() {
        return requestEmprAmount;
    }

    public void setRequestEmprAmount(BigDecimal requestEmprAmount) {
        this.requestEmprAmount = requestEmprAmount;
    }

    public BigDecimal getRequestInterestAmount() {
        return requestInterestAmount;
    }

    public void setRequestInterestAmount(BigDecimal requestInterestAmount) {
        this.requestInterestAmount = requestInterestAmount;
    }

    public BigDecimal getRequestOverPaidAmount() {
        return requestOverPaidAmount;
    }

    public void setRequestOverPaidAmount(BigDecimal requestOverPaidAmount) {
        this.requestOverPaidAmount = requestOverPaidAmount;
    }

    public BigDecimal getRequestEmpeAmount() {
        return requestEmpeAmount;
    }

    public void setRequestEmpeAmount(BigDecimal requestEmpeAmount) {
        this.requestEmpeAmount = requestEmpeAmount;
    }

    public BigDecimal getRequestTotalAmount() {
        return requestTotalAmount;
    }

    public void setRequestTotalAmount(BigDecimal requestTotalAmount) {
        this.requestTotalAmount = requestTotalAmount;
    }

    public BigDecimal getRefundEmprAmount() {
        return refundEmprAmount;
    }

    public void setRefundEmprAmount(BigDecimal refundEmprAmount) {
        this.refundEmprAmount = refundEmprAmount;
    }

    public BigDecimal getRefundInterestAmount() {
        return refundInterestAmount;
    }

    public void setRefundInterestAmount(BigDecimal refundInterestAmount) {
        this.refundInterestAmount = refundInterestAmount;
    }

    public BigDecimal getRefundOverPaidAmount() {
        return refundOverPaidAmount;
    }

    public void setRefundOverPaidAmount(BigDecimal refundOverPaidAmount) {
        this.refundOverPaidAmount = refundOverPaidAmount;
    }

    public BigDecimal getRefundEmpeAmount() {
        return refundEmpeAmount;
    }

    public void setRefundEmpeAmount(BigDecimal refundEmpeAmount) {
        this.refundEmpeAmount = refundEmpeAmount;
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

    public String getAnnounceArCreateBy() {
        return announceArCreateBy;
    }

    public void setAnnounceArCreateBy(String announceArCreateBy) {
        this.announceArCreateBy = announceArCreateBy;
    }

    public Date getAnnounceArCreateDate() {
        return announceArCreateDate;
    }

    public void setAnnounceArCreateDate(Date announceArCreateDate) {
        this.announceArCreateDate = announceArCreateDate;
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

    public BigDecimal getRequestTotalEmprAmount() {
        return requestTotalEmprAmount;
    }

    public void setRequestTotalEmprAmount(BigDecimal requestTotalEmprAmount) {
        this.requestTotalEmprAmount = requestTotalEmprAmount;
    }

    public BigDecimal getRequestTotalEmpeAmount() {
        return requestTotalEmpeAmount;
    }

    public void setRequestTotalEmpeAmount(BigDecimal requestTotalEmpeAmount) {
        this.requestTotalEmpeAmount = requestTotalEmpeAmount;
    }

    public BigDecimal getRefundTotalEmprAmount() {
        return refundTotalEmprAmount;
    }

    public void setRefundTotalEmprAmount(BigDecimal refundTotalEmprAmount) {
        this.refundTotalEmprAmount = refundTotalEmprAmount;
    }

    public BigDecimal getRefundTotalEmpeAmount() {
        return refundTotalEmpeAmount;
    }

    public void setRefundTotalEmpeAmount(BigDecimal refundTotalEmpeAmount) {
        this.refundTotalEmpeAmount = refundTotalEmpeAmount;
    }

    public String getReverseStatus() {
        return reverseStatus;
    }

    public void setReverseStatus(String reverseStatus) {
        this.reverseStatus = reverseStatus;
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

    public String getReverseRemark() {
        return reverseRemark;
    }

    public void setReverseRemark(String reverseRemark) {
        this.reverseRemark = reverseRemark;
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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public String getSubDistName() {
        return subDistName;
    }

    public void setSubDistName(String subDistName) {
        this.subDistName = subDistName;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

//    public Long getDepartmentReceiveId() {
//        return departmentReceiveId;
//    }
//
//    public void setDepartmentReceiveId(Long departmentReceiveId) {
//        this.departmentReceiveId = departmentReceiveId;
//    }
//
//    public Long getDepartmentResponsibleId() {
//        return departmentResponsibleId;
//    }
//
//    public void setDepartmentResponsibleId(Long departmentResponsibleId) {
//        this.departmentResponsibleId = departmentResponsibleId;
//    }

    public String getDepartmentResponsibleCode() {
        return departmentResponsibleCode;
    }

    public void setDepartmentResponsibleCode(String departmentResponsibleCode) {
        this.departmentResponsibleCode = departmentResponsibleCode;
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

    public String getRefundBankAccount() {
        return refundBankAccount;
    }

    public void setRefundBankAccount(String refundBankAccount) {
        this.refundBankAccount = refundBankAccount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getEligibleFlag() {
        return eligibleFlag;
    }

    public void setEligibleFlag(String eligibleFlag) {
        this.eligibleFlag = eligibleFlag;
    }

    @JsonProperty("isEligible")
    public Boolean getEligible() {
        return isEligible;
    }

    public void setEligible(Boolean eligible) {
        isEligible = eligible;
    }

    public String getRefundPoOrderName() {
        return refundPoOrderName;
    }

    public void setRefundPoOrderName(String refundPoOrderName) {
        this.refundPoOrderName = refundPoOrderName;
    }

    public List<ConTbRequesterBean> getRequesterEligibleList() {
        return requesterEligibleList;
    }

    public void setRequesterEligibleList(List<ConTbRequesterBean> requesterEligibleList) {
        this.requesterEligibleList = requesterEligibleList;
    }

    public List<ConTbRequesterBean> getRequesterList() {
        return requesterList;
    }

    public void setRequesterList(List<ConTbRequesterBean> requesterList) {
        this.requesterList = requesterList;
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
}
