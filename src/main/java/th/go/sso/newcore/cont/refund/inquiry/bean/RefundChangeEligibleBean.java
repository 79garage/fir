package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class RefundChangeEligibleBean {

    private String idCardNo;
    private String fullName;
    private String titleCode;
    private String firstName;
    private String lastName;
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
    private String promptpay;
    private String ssoCode;
    private String createBy;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String relationGroup;
    private String nationalityCode;
    private String nationality;
    private Long refundRequestChangeTypeId;
    private Long refundChangeEligibleId;
    private String receiveNo;
    private String refundOperationCode;
    private BigDecimal refundTotalAmount;
    private BigDecimal refundInterestAmount;

    private BigDecimal refundTotalAmountChange;
    private String idCardNoChange;
    private String fullNameChange;
    private String titleCodeChange;
    private String firstNameChange;
    private String lastNameChange;
    private BigDecimal refundInterestAmountChange;
    private String relationChange;
    private String addressChange;
    private String villageChange;
    private String mooChange;
    private String soiChange;
    private String roadChange;
    private String provinceCodeChange;
    private String provinceNameChange;
    private String distCodeChange;
    private String distNameChange;
    private String subdistCodeChange;
    private String subdistNameChange;
    private String zipCodeChange;
    private String telephoneChange;
    private String mobileChange;
    private String emailChange;
    private String paymentPlaceTypeChange;
    private String bankCodeChange;
    private String bankAccChange;
    private String bankAccNameChange;
    private String promptpayChange;
    private String ssoCodeChange;
    private String createByChange;
    private String relationGroupChange;
    private String nationalityCodeChange;
    private String nationalityChange;

    private BigDecimal refundSicknessAmount;
    private BigDecimal refundSicknessAmountChange;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundRetirementAmountChange;
    private BigDecimal refundUnemploymentAmount;
    private BigDecimal refundUnemploymentAmountChange;

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

    public String getPromptpay() {
        return promptpay;
    }

    public void setPromptpay(String promptpay) {
        this.promptpay = promptpay;
    }

    public String getSsoCode() {
        return ssoCode;
    }

    public void setSsoCode(String ssoCode) {
        this.ssoCode = ssoCode;
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

    public Long getRefundRequestChangeTypeId() {
        return refundRequestChangeTypeId;
    }

    public void setRefundRequestChangeTypeId(Long refundRequestChangeTypeId) {
        this.refundRequestChangeTypeId = refundRequestChangeTypeId;
    }

    public Long getRefundChangeEligibleId() {
        return refundChangeEligibleId;
    }

    public void setRefundChangeEligibleId(Long refundChangeEligibleId) {
        this.refundChangeEligibleId = refundChangeEligibleId;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getRefundOperationCode() {
        return refundOperationCode;
    }

    public void setRefundOperationCode(String refundOperationCode) {
        this.refundOperationCode = refundOperationCode;
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

    public BigDecimal getRefundTotalAmountChange() {
        return refundTotalAmountChange;
    }

    public void setRefundTotalAmountChange(BigDecimal refundTotalAmountChange) {
        this.refundTotalAmountChange = refundTotalAmountChange;
    }

    public String getIdCardNoChange() {
        return idCardNoChange;
    }

    public void setIdCardNoChange(String idCardNoChange) {
        this.idCardNoChange = idCardNoChange;
    }

    public String getFullNameChange() {
        return fullNameChange;
    }

    public void setFullNameChange(String fullNameChange) {
        this.fullNameChange = fullNameChange;
    }

    public String getTitleCodeChange() {
        return titleCodeChange;
    }

    public void setTitleCodeChange(String titleCodeChange) {
        this.titleCodeChange = titleCodeChange;
    }

    public String getFirstNameChange() {
        return firstNameChange;
    }

    public void setFirstNameChange(String firstNameChange) {
        this.firstNameChange = firstNameChange;
    }

    public String getLastNameChange() {
        return lastNameChange;
    }

    public void setLastNameChange(String lastNameChange) {
        this.lastNameChange = lastNameChange;
    }

    public BigDecimal getRefundInterestAmountChange() {
        return refundInterestAmountChange;
    }

    public void setRefundInterestAmountChange(BigDecimal refundInterestAmountChange) {
        this.refundInterestAmountChange = refundInterestAmountChange;
    }

    public String getRelationChange() {
        return relationChange;
    }

    public void setRelationChange(String relationChange) {
        this.relationChange = relationChange;
    }

    public String getAddressChange() {
        return addressChange;
    }

    public void setAddressChange(String addressChange) {
        this.addressChange = addressChange;
    }

    public String getVillageChange() {
        return villageChange;
    }

    public void setVillageChange(String villageChange) {
        this.villageChange = villageChange;
    }

    public String getMooChange() {
        return mooChange;
    }

    public void setMooChange(String mooChange) {
        this.mooChange = mooChange;
    }

    public String getSoiChange() {
        return soiChange;
    }

    public void setSoiChange(String soiChange) {
        this.soiChange = soiChange;
    }

    public String getRoadChange() {
        return roadChange;
    }

    public void setRoadChange(String roadChange) {
        this.roadChange = roadChange;
    }

    public String getProvinceCodeChange() {
        return provinceCodeChange;
    }

    public void setProvinceCodeChange(String provinceCodeChange) {
        this.provinceCodeChange = provinceCodeChange;
    }

    public String getProvinceNameChange() {
        return provinceNameChange;
    }

    public void setProvinceNameChange(String provinceNameChange) {
        this.provinceNameChange = provinceNameChange;
    }

    public String getDistCodeChange() {
        return distCodeChange;
    }

    public void setDistCodeChange(String distCodeChange) {
        this.distCodeChange = distCodeChange;
    }

    public String getDistNameChange() {
        return distNameChange;
    }

    public void setDistNameChange(String distNameChange) {
        this.distNameChange = distNameChange;
    }

    public String getSubdistCodeChange() {
        return subdistCodeChange;
    }

    public void setSubdistCodeChange(String subdistCodeChange) {
        this.subdistCodeChange = subdistCodeChange;
    }

    public String getSubdistNameChange() {
        return subdistNameChange;
    }

    public void setSubdistNameChange(String subdistNameChange) {
        this.subdistNameChange = subdistNameChange;
    }

    public String getZipCodeChange() {
        return zipCodeChange;
    }

    public void setZipCodeChange(String zipCodeChange) {
        this.zipCodeChange = zipCodeChange;
    }

    public String getTelephoneChange() {
        return telephoneChange;
    }

    public void setTelephoneChange(String telephoneChange) {
        this.telephoneChange = telephoneChange;
    }

    public String getMobileChange() {
        return mobileChange;
    }

    public void setMobileChange(String mobileChange) {
        this.mobileChange = mobileChange;
    }

    public String getEmailChange() {
        return emailChange;
    }

    public void setEmailChange(String emailChange) {
        this.emailChange = emailChange;
    }

    public String getPaymentPlaceTypeChange() {
        return paymentPlaceTypeChange;
    }

    public void setPaymentPlaceTypeChange(String paymentPlaceTypeChange) {
        this.paymentPlaceTypeChange = paymentPlaceTypeChange;
    }

    public String getBankCodeChange() {
        return bankCodeChange;
    }

    public void setBankCodeChange(String bankCodeChange) {
        this.bankCodeChange = bankCodeChange;
    }

    public String getBankAccChange() {
        return bankAccChange;
    }

    public void setBankAccChange(String bankAccChange) {
        this.bankAccChange = bankAccChange;
    }

    public String getBankAccNameChange() {
        return bankAccNameChange;
    }

    public void setBankAccNameChange(String bankAccNameChange) {
        this.bankAccNameChange = bankAccNameChange;
    }

    public String getPromptpayChange() {
        return promptpayChange;
    }

    public void setPromptpayChange(String promptpayChange) {
        this.promptpayChange = promptpayChange;
    }

    public String getSsoCodeChange() {
        return ssoCodeChange;
    }

    public void setSsoCodeChange(String ssoCodeChange) {
        this.ssoCodeChange = ssoCodeChange;
    }

    public String getCreateByChange() {
        return createByChange;
    }

    public void setCreateByChange(String createByChange) {
        this.createByChange = createByChange;
    }

    public String getRelationGroupChange() {
        return relationGroupChange;
    }

    public void setRelationGroupChange(String relationGroupChange) {
        this.relationGroupChange = relationGroupChange;
    }

    public String getNationalityCodeChange() {
        return nationalityCodeChange;
    }

    public void setNationalityCodeChange(String nationalityCodeChange) {
        this.nationalityCodeChange = nationalityCodeChange;
    }

    public String getNationalityChange() {
        return nationalityChange;
    }

    public void setNationalityChange(String nationalityChange) {
        this.nationalityChange = nationalityChange;
    }

    public BigDecimal getRefundSicknessAmount() {
        return refundSicknessAmount;
    }

    public void setRefundSicknessAmount(BigDecimal refundSicknessAmount) {
        this.refundSicknessAmount = refundSicknessAmount;
    }

    public BigDecimal getRefundSicknessAmountChange() {
        return refundSicknessAmountChange;
    }

    public void setRefundSicknessAmountChange(BigDecimal refundSicknessAmountChange) {
        this.refundSicknessAmountChange = refundSicknessAmountChange;
    }

    public BigDecimal getRefundRetirementAmount() {
        return refundRetirementAmount;
    }

    public void setRefundRetirementAmount(BigDecimal refundRetirementAmount) {
        this.refundRetirementAmount = refundRetirementAmount;
    }

    public BigDecimal getRefundRetirementAmountChange() {
        return refundRetirementAmountChange;
    }

    public void setRefundRetirementAmountChange(BigDecimal refundRetirementAmountChange) {
        this.refundRetirementAmountChange = refundRetirementAmountChange;
    }

    public BigDecimal getRefundUnemploymentAmount() {
        return refundUnemploymentAmount;
    }

    public void setRefundUnemploymentAmount(BigDecimal refundUnemploymentAmount) {
        this.refundUnemploymentAmount = refundUnemploymentAmount;
    }

    public BigDecimal getRefundUnemploymentAmountChange() {
        return refundUnemploymentAmountChange;
    }

    public void setRefundUnemploymentAmountChange(BigDecimal refundUnemploymentAmountChange) {
        this.refundUnemploymentAmountChange = refundUnemploymentAmountChange;
    }
}
