package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class RefundRequestBean {

    private Long companyId;
    private String companyDescription;
    private String companyBranchDescription;

    private Long insurerId;
    private String idCardNo;
    private String fullName;
    private String insurerStatus;

    private Long refundRequestId;
    private String requestType;
    private String requestChannel;
    private Long departmentResponsibleId;
    private Long departmentReceiveId;
    private String departmentResponsibleCode;
    private String departmentReceiveCode;
    private String receiveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDate;
    private String requestReasonCode;
    private String requestReasonOther;
    private String refundTypePlace;
    private Long refundBankId;
    private Long refundBankBranchId;
    private String refundBankAccount;
    private String distCode;
    private String subDistCode;
    private String provinceCode;
    private String refundPoOrder;
    private String refundPromptPay;
    private String refundRemark;
    private String section;
    private String status;
    private String progressStatus;
    private String refundChangeType;
    private String refundOperationCode;
    private String reverseRemark;
    private List<ConTbRequesterBean> requesterList;
    private Long refRefundRequestId;
    private String originalReceiveNo;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyBranchDescription() {
        return companyBranchDescription;
    }

    public void setCompanyBranchDescription(String companyBranchDescription) {
        this.companyBranchDescription = companyBranchDescription;
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

    public String getInsurerStatus() {
        return insurerStatus;
    }

    public void setInsurerStatus(String insurerStatus) {
        this.insurerStatus = insurerStatus;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
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

    public Long getDepartmentResponsibleId() {
        return departmentResponsibleId;
    }

    public void setDepartmentResponsibleId(Long departmentResponsibleId) {
        this.departmentResponsibleId = departmentResponsibleId;
    }

    public Long getDepartmentReceiveId() {
        return departmentReceiveId;
    }

    public void setDepartmentReceiveId(Long departmentReceiveId) {
        this.departmentReceiveId = departmentReceiveId;
    }

    public String getDepartmentResponsibleCode() {
        return departmentResponsibleCode;
    }

    public void setDepartmentResponsibleCode(String departmentResponsibleCode) {
        this.departmentResponsibleCode = departmentResponsibleCode;
    }

    public String getDepartmentReceiveCode() {
        return departmentReceiveCode;
    }

    public void setDepartmentReceiveCode(String departmentReceiveCode) {
        this.departmentReceiveCode = departmentReceiveCode;
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

    public String getRefundTypePlace() {
        return refundTypePlace;
    }

    public void setRefundTypePlace(String refundTypePlace) {
        this.refundTypePlace = refundTypePlace;
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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
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

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
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

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getRefundChangeType() {
        return refundChangeType;
    }

    public void setRefundChangeType(String refundChangeType) {
        this.refundChangeType = refundChangeType;
    }

    public String getRefundOperationCode() {
        return refundOperationCode;
    }

    public void setRefundOperationCode(String refundOperationCode) {
        this.refundOperationCode = refundOperationCode;
    }

    public String getReverseRemark() {
        return reverseRemark;
    }

    public void setReverseRemark(String reverseRemark) {
        this.reverseRemark = reverseRemark;
    }

    public List<ConTbRequesterBean> getRequesterList() {
        return requesterList;
    }

    public void setRequesterList(List<ConTbRequesterBean> requesterList) {
        this.requesterList = requesterList;
    }

    public Long getRefRefundRequestId() {
        return refRefundRequestId;
    }

    public void setRefRefundRequestId(Long refRefundRequestId) {
        this.refRefundRequestId = refRefundRequestId;
    }

    public String getOriginalReceiveNo() {
        return originalReceiveNo;
    }

    public void setOriginalReceiveNo(String originalReceiveNo) {
        this.originalReceiveNo = originalReceiveNo;
    }
}
