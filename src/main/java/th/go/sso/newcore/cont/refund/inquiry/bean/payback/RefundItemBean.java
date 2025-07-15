package th.go.sso.newcore.cont.refund.inquiry.bean.payback;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class RefundItemBean {

	private Long refundRequestId;
	private Long companyId;
	private Long insurerId;
	private String receiveNo;
	private String noticeNo;
	private String announceNo;
	private String approveNo;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date approveDate;
	private String approveStatus;
	private String section;
	private String reasonCode;
	private String refundPeriod;
	private Long trRefundId;
	private String progressStatus;
	private String payStatus;
	private String status;
	private BigDecimal requestEmprAmount;
	private BigDecimal requestEmpeAmount;
	private BigDecimal requestTotalAmount;
	private BigDecimal requestInterestAmount;
	private BigDecimal requestOverPaidAmount;
	private BigDecimal refundEmprAmount;
	private BigDecimal refundEmpeAmount;
	private BigDecimal refundTotalAmount;
	private BigDecimal refundInterestAmount;
	private BigDecimal refundOverPaidAmount;
	private Long requesterId;
	private String orderNo;
	private String orderStatus;
	private BigDecimal refundSicknessAmount;
	private BigDecimal refundRetirementAmount;
	private BigDecimal refundUnEmploymentAmount;
	private BigDecimal refundSicknessRate;
	private BigDecimal refundRetirementRate;
	private BigDecimal refundUnEmploymentRate;
	private String requesterType;
	private String companyNo;
	private String companyName;
	private String companyBranchCode;
	private String companyBranchName;
	private String idCardNo;
	private String fullName;

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

	public String getReceiveNo() {
		return receiveNo;
	}

	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getAnnounceNo() {
		return announceNo;
	}

	public void setAnnounceNo(String announceNo) {
		this.announceNo = announceNo;
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

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getRefundPeriod() {
		return refundPeriod;
	}

	public void setRefundPeriod(String refundPeriod) {
		this.refundPeriod = refundPeriod;
	}

	public Long getTrRefundId() {
		return trRefundId;
	}

	public void setTrRefundId(Long trRefundId) {
		this.trRefundId = trRefundId;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getRequestEmprAmount() {
		return requestEmprAmount;
	}

	public void setRequestEmprAmount(BigDecimal requestEmprAmount) {
		this.requestEmprAmount = requestEmprAmount;
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

	public BigDecimal getRefundEmprAmount() {
		return refundEmprAmount;
	}

	public void setRefundEmprAmount(BigDecimal refundEmprAmount) {
		this.refundEmprAmount = refundEmprAmount;
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

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
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

	public BigDecimal getRefundUnEmploymentAmount() {
		return refundUnEmploymentAmount;
	}

	public void setRefundUnEmploymentAmount(BigDecimal refundUnEmploymentAmount) {
		this.refundUnEmploymentAmount = refundUnEmploymentAmount;
	}

	public BigDecimal getRefundSicknessRate() {
		return refundSicknessRate;
	}

	public void setRefundSicknessRate(BigDecimal refundSicknessRate) {
		this.refundSicknessRate = refundSicknessRate;
	}

	public BigDecimal getRefundRetirementRate() {
		return refundRetirementRate;
	}

	public void setRefundRetirementRate(BigDecimal refundRetirementRate) {
		this.refundRetirementRate = refundRetirementRate;
	}

	public BigDecimal getRefundUnEmploymentRate() {
		return refundUnEmploymentRate;
	}

	public void setRefundUnEmploymentRate(BigDecimal refundUnEmploymentRate) {
		this.refundUnEmploymentRate = refundUnEmploymentRate;
	}

	public String getRequesterType() {
		return requesterType;
	}

	public void setRequesterType(String requesterType) {
		this.requesterType = requesterType;
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
}
