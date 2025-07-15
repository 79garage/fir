package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class RefundInsurerBean {

	// con_ms_insurer
	private Long insurerId;
	private String idCardNo;
	private String fullName;
	private String insurerStatus;
	// con_ms_department
	private String depCode;
	private String depName;

	// con_tb_refund_request
	private Long refundRequestId;
	private String receiveNo;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date receiveDate;
	private Long departmentReceiveId;
	private String departmentReceiveCode;
	private Long departmentResponsibleId;
	private String departmentResponsibleCode;
	private String departmentCode;
	private String refundTypePlace;
	private String requestReasonCode;
	private String requestReasonOther;
	private Long refundBankId;
	private Long refundBankBranchId;
	private String refundBankAccount;
	private String refundPromptPay;
	private String approveNo;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date approveDate;
	private String noticeStatus;
	private String announceStatus;
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
	private String requestChannel;
	private String progressStatus;
	private String ssoBranchCode;
	private String refundBankCode;
	private String refundBankBranchCode;
	private String refundPoOrder;
	private String refundTransferBank;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date refundTransferDate;
	private String noticeNo;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date noticeDate;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date payDate;
	private String approveStatus;
	private String section;
	private String recordApproveBy;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date recordApproveDate;

	// cal save conTrRefundInsurer
	private BigDecimal requestEmprAmount;
	private BigDecimal requestInterestAmount;
	private BigDecimal requestTotalEmprAmount;
	private BigDecimal requestEmpeAmount;
	private BigDecimal requestTotalEmpeAmount;
	private BigDecimal requestTotalAmount;
	private BigDecimal refundEmprAmount;
	private BigDecimal refundInterestAmount;
	private BigDecimal refundTotalEmprAmount;
	private BigDecimal refundEmpeAmount;
	private BigDecimal refundTotalEmpeAmount;
	private BigDecimal refundTotalAmount;
	private String disapproveReason;
	private BigDecimal refundSicknessAmount;
	private BigDecimal refundRetirementAmount;
	private BigDecimal refundUnemploymentAmount;
	private String reverseRemark;
	private String reverseStatus;
	private String titleCode;
	private String titleName;
	private String idCardNoRef;
	private String firstName;
	private String lastName;
	private String fullNameRef;
	private String address;
	private String village;
	private String moo;
	private String soi;
	private String road;
	private String distCode;
	private String subdistCode;
	private String provinceCode;
	private String distName;
	private String subdistName;
	private String provinceName;
	private String zipCode;
	private String telephone;
	private String mobile;
	private String email;
	private String requesterType;
	private String announceNo;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date announceDate;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date announceArDate;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date noticeArDate;
	private String orderRemark;
	private String orderStatus;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date replyDate;
	private String announceCreateBy;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date announceCreateDate;
	private String announceArCreateBy;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date announceArCreateDate;
	private String refundPoOrderName;
	private String periodStart;
	private String periodEnd;
	private BigDecimal requestAmount;
	private List<ConTbRequesterBean> requesterEligibleList;
	private List<ConTbRequesterBean> requesterList;

	private String locName;
	private String locNameRep;

	public Long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Long insurerId) {
		this.insurerId = insurerId;
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

	public String getInsurerStatus() {
		return insurerStatus;
	}

	public void setInsurerStatus(String insurerStatus) {
		this.insurerStatus = insurerStatus;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Long getRefundRequestId() {
		return refundRequestId;
	}

	public void setRefundRequestId(Long refundRequestId) {
		this.refundRequestId = refundRequestId;
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

	public String getDepartmentResponsibleCode() {
		return departmentResponsibleCode;
	}

	public void setDepartmentResponsibleCode(String departmentResponsibleCode) {
		this.departmentResponsibleCode = departmentResponsibleCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getRefundTypePlace() {
		return refundTypePlace;
	}

	public void setRefundTypePlace(String refundTypePlace) {
		this.refundTypePlace = refundTypePlace;
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

	public String getRefundPromptPay() {
		return refundPromptPay;
	}

	public void setRefundPromptPay(String refundPromptPay) {
		this.refundPromptPay = refundPromptPay;
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

	public String getRequestChannel() {
		return requestChannel;
	}

	public void setRequestChannel(String requestChannel) {
		this.requestChannel = requestChannel;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	public String getSsoBranchCode() {
		return ssoBranchCode;
	}

	public void setSsoBranchCode(String ssoBranchCode) {
		this.ssoBranchCode = ssoBranchCode;
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

	public String getRefundPoOrder() {
		return refundPoOrder;
	}

	public void setRefundPoOrder(String refundPoOrder) {
		this.refundPoOrder = refundPoOrder;
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

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
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

	public BigDecimal getRequestTotalEmprAmount() {
		return requestTotalEmprAmount;
	}

	public void setRequestTotalEmprAmount(BigDecimal requestTotalEmprAmount) {
		this.requestTotalEmprAmount = requestTotalEmprAmount;
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

	public BigDecimal getRefundTotalEmprAmount() {
		return refundTotalEmprAmount;
	}

	public void setRefundTotalEmprAmount(BigDecimal refundTotalEmprAmount) {
		this.refundTotalEmprAmount = refundTotalEmprAmount;
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

	public String getDisapproveReason() {
		return disapproveReason;
	}

	public void setDisapproveReason(String disapproveReason) {
		this.disapproveReason = disapproveReason;
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

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getSubdistName() {
		return subdistName;
	}

	public void setSubdistName(String subdistName) {
		this.subdistName = subdistName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getReverseStatus() {
		return reverseStatus;
	}

	public void setReverseStatus(String reverseStatus) {
		this.reverseStatus = reverseStatus;
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

	public Date getNoticeArDate() {
		return noticeArDate;
	}

	public void setNoticeArDate(Date noticeArDate) {
		this.noticeArDate = noticeArDate;
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

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getAnnounceCreateBy() {
		return announceCreateBy;
	}

	public void setAnnounceCreateBy(String announceCreateBy) {
		this.announceCreateBy = announceCreateBy;
	}

	public Date getAnnounceCreateDate() {
		return announceCreateDate;
	}

	public void setAnnounceCreateDate(Date announceCreateDate) {
		this.announceCreateDate = announceCreateDate;
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

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getIdCardNoRef() {
		return idCardNoRef;
	}

	public void setIdCardNoRef(String idCardNoRef) {
		this.idCardNoRef = idCardNoRef;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullNameRef() {
		return fullNameRef;
	}

	public void setFullNameRef(String fullNameRef) {
		this.fullNameRef = fullNameRef;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getMoo() {
		return moo;
	}

	public void setMoo(String moo) {
		this.moo = moo;
	}

	public String getSoi() {
		return soi;
	}

	public void setSoi(String soi) {
		this.soi = soi;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRequesterType() {
		return requesterType;
	}

	public void setRequesterType(String requesterType) {
		this.requesterType = requesterType;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getLocNameRep() {
		return locNameRep;
	}

	public void setLocNameRep(String locNameRep) {
		this.locNameRep = locNameRep;
	}

}
