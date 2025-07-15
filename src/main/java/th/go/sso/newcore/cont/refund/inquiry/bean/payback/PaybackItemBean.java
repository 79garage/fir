package th.go.sso.newcore.cont.refund.inquiry.bean.payback;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class PaybackItemBean {

	private Long paybackRequestId;
	private String paybackRequestNo;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date paybackRequestDate;
	private BigDecimal paybackTotalAmount;
	private String paybackApproveStatus;
	private String paybackStatus;
	private String status;
	private Long refundRequestId;
	private String receiveNo;
	private String section;
	private String splitRefund;
	private Long requesterId;
	private String refundPeriod;
	private Long companyId;
	private String companyNo;
	private String companyName;
	private String companyBranchCode;
	private String companyBranchName;
	private Long insurerId;
	private String idCardNo;
	private String fullName;

	public Long getPaybackRequestId() {
		return paybackRequestId;
	}

	public void setPaybackRequestId(Long paybackRequestId) {
		this.paybackRequestId = paybackRequestId;
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

	public BigDecimal getPaybackTotalAmount() {
		return paybackTotalAmount;
	}

	public void setPaybackTotalAmount(BigDecimal paybackTotalAmount) {
		this.paybackTotalAmount = paybackTotalAmount;
	}

	public String getPaybackApproveStatus() {
		return paybackApproveStatus;
	}

	public void setPaybackApproveStatus(String paybackApproveStatus) {
		this.paybackApproveStatus = paybackApproveStatus;
	}

	public String getPaybackStatus() {
		return paybackStatus;
	}

	public void setPaybackStatus(String paybackStatus) {
		this.paybackStatus = paybackStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSplitRefund() {
		return splitRefund;
	}

	public void setSplitRefund(String splitRefund) {
		this.splitRefund = splitRefund;
	}

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
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
}
