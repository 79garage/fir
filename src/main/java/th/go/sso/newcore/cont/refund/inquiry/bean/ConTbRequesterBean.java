package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class ConTbRequesterBean {
	private Long requesterId;
	private Long refundRequestId;
	private Long refundReceiptId;
	private String orderNo;
	private String orderStatus;
	private String idCardNo;
	private String fullName;
	private String titleCode;
	private String firstName;
	private String lastName;
	private String requesterType;
	private String relation;
	private String address;
	private String village;
	private String moo;
	private String soi;
	private String road;
	private String provinceCode;
	private String provinceName;
	private String distCode;
	private String distName;
	private String subdistCode;
	private String subdistName;
	private String zipCode;
	private String telephone;
	private String mobile;
	private String email;
	private String paymentPlaceType;
	private String bankCode;
	private String bankAcc;
	private String bankAccName;
	private String postOffice;
	private String promptPay;
	private String ssoCode;
	private String receiptNo;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date receiptDate;
	private BigDecimal refundTotalAmount;
	private String relationGroup;
	private String nationalityCode;
	private String nationality;
	private String section;
	private String orderRemark;
	private BigDecimal refundSicknessAmount;
	private BigDecimal refundRetirementAmount;
	private BigDecimal refundUnemploymentAmount;
	private BigDecimal refundSicknessRate;
	private BigDecimal refundUnemploymentRate;
	private BigDecimal refundRetirementRate;
	private BigDecimal refundInterestAmount;

	private String period;
	private String refundPoOrder;
	private String refundPoOrderName;
	private String refundPromptPay;
	private BigDecimal refundEmpeAmount;
	private BigDecimal refundEmprAmount;
	@JsonSerialize(converter = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date approveDate;
	private String depCode;
	private String oldOrderNo;
	private String locName;
	private String locNameRep;

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public Long getRefundRequestId() {
		return refundRequestId;
	}

	public void setRefundRequestId(Long refundRequestId) {
		this.refundRequestId = refundRequestId;
	}

	public Long getRefundReceiptId() {
		return refundReceiptId;
	}

	public void setRefundReceiptId(Long refundReceiptId) {
		this.refundReceiptId = refundReceiptId;
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

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
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

	public String getRequesterType() {
		return requesterType;
	}

	public void setRequesterType(String requesterType) {
		this.requesterType = requesterType;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getDistCode() {
		return distCode;
	}

	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getSubdistCode() {
		return subdistCode;
	}

	public void setSubdistCode(String subdistCode) {
		this.subdistCode = subdistCode;
	}

	public String getSubdistName() {
		return subdistName;
	}

	public void setSubdistName(String subdistName) {
		this.subdistName = subdistName;
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

	public String getPaymentPlaceType() {
		return paymentPlaceType;
	}

	public void setPaymentPlaceType(String paymentPlaceType) {
		this.paymentPlaceType = paymentPlaceType;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankAcc() {
		return bankAcc;
	}

	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}

	public String getBankAccName() {
		return bankAccName;
	}

	public void setBankAccName(String bankAccName) {
		this.bankAccName = bankAccName;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

	public String getPromptPay() {
		return promptPay;
	}

	public void setPromptPay(String promptPay) {
		this.promptPay = promptPay;
	}

	public String getSsoCode() {
		return ssoCode;
	}

	public void setSsoCode(String ssoCode) {
		this.ssoCode = ssoCode;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public BigDecimal getRefundTotalAmount() {
		return refundTotalAmount;
	}

	public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
		this.refundTotalAmount = refundTotalAmount;
	}

	public String getRelationGroup() {
		return relationGroup;
	}

	public void setRelationGroup(String relationGroup) {
		this.relationGroup = relationGroup;
	}

	public String getNationalityCode() {
		return nationalityCode;
	}

	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
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

	public BigDecimal getRefundUnemploymentRate() {
		return refundUnemploymentRate;
	}

	public void setRefundUnemploymentRate(BigDecimal refundUnemploymentRate) {
		this.refundUnemploymentRate = refundUnemploymentRate;
	}

	public BigDecimal getRefundInterestAmount() {
		return refundInterestAmount;
	}

	public void setRefundInterestAmount(BigDecimal refundInterestAmount) {
		this.refundInterestAmount = refundInterestAmount;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRefundPoOrder() {
		return refundPoOrder;
	}

	public void setRefundPoOrder(String refundPoOrder) {
		this.refundPoOrder = refundPoOrder;
	}

	public String getRefundPoOrderName() {
		return refundPoOrderName;
	}

	public void setRefundPoOrderName(String refundPoOrderName) {
		this.refundPoOrderName = refundPoOrderName;
	}

	public String getRefundPromptPay() {
		return refundPromptPay;
	}

	public void setRefundPromptPay(String refundPromptPay) {
		this.refundPromptPay = refundPromptPay;
	}

	public BigDecimal getRefundEmpeAmount() {
		return refundEmpeAmount;
	}

	public void setRefundEmpeAmount(BigDecimal refundEmpeAmount) {
		this.refundEmpeAmount = refundEmpeAmount;
	}

	public BigDecimal getRefundEmprAmount() {
		return refundEmprAmount;
	}

	public void setRefundEmprAmount(BigDecimal refundEmprAmount) {
		this.refundEmprAmount = refundEmprAmount;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getOldOrderNo() {
		return oldOrderNo;
	}

	public void setOldOrderNo(String oldOrderNo) {
		this.oldOrderNo = oldOrderNo;
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
