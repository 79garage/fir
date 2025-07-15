package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class RefundReportBean {
    private String receiveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDate;
    private Long companyId;
    private String companyNo;
    private String companyName;
    private String companyBranchCode;
    private String companyBranchName;
    private Long insurerId;
    private String idCardNo;
    private String fullName;
    private String firstName;
    private String lastName;
    private String refundPeriod;
    private String refundPeriodMonth;
    private String refundPeriodYear;
    private BigDecimal requestEmprAmount;
    private BigDecimal requestEmpeAmount;
    private BigDecimal requestInterestAmount;
    private BigDecimal requestTotalAmount;
    private BigDecimal refundEmprAmount;
    private BigDecimal refundEmpeAmount;
    private BigDecimal refundInterestAmount;
    private BigDecimal refundTotalAmount;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundUnemploymentAmount;
    private String refundTypePlace;
    private String requestReasonCode;
    private String requestReasonOther;
    private String requestReasonDesc;
    private String departmentReceiveCode;
    private String departmentResponsibleCode;
    private String departmentReceiveName;
    private String departmentResponsibleName;
    private String refundPromptPay;
    private String refundPoOrder;
    private String refundBankAccount;
    private String refundBankCode;
    private String bankName;
    private String refundPoOrderName;
    private String periodStart;
    private String periodEnd;
    private BigDecimal requestAmount;
    private String periodStartMonth;
    private String periodStartYear;
    private String periodEndMonth;
    private String periodEndYear;
    private String section;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;
    private String approveStatus;
    private String splitRefund;
    private String status;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date processDate;
    private String createBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;

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

    public String getRefundPeriod() {
        return refundPeriod;
    }

    public void setRefundPeriod(String refundPeriod) {
        this.refundPeriod = refundPeriod;
    }

    public String getRefundPeriodMonth() {
        return refundPeriodMonth;
    }

    public void setRefundPeriodMonth(String refundPeriodMonth) {
        this.refundPeriodMonth = refundPeriodMonth;
    }

    public String getRefundPeriodYear() {
        return refundPeriodYear;
    }

    public void setRefundPeriodYear(String refundPeriodYear) {
        this.refundPeriodYear = refundPeriodYear;
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

    public BigDecimal getRequestInterestAmount() {
        return requestInterestAmount;
    }

    public void setRequestInterestAmount(BigDecimal requestInterestAmount) {
        this.requestInterestAmount = requestInterestAmount;
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

    public BigDecimal getRefundEmpeAmount() {
        return refundEmpeAmount;
    }

    public void setRefundEmpeAmount(BigDecimal refundEmpeAmount) {
        this.refundEmpeAmount = refundEmpeAmount;
    }

    public BigDecimal getRefundInterestAmount() {
        return refundInterestAmount;
    }

    public void setRefundInterestAmount(BigDecimal refundInterestAmount) {
        this.refundInterestAmount = refundInterestAmount;
    }

    public BigDecimal getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
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

    public String getRequestReasonDesc() {
        return requestReasonDesc;
    }

    public void setRequestReasonDesc(String requestReasonDesc) {
        this.requestReasonDesc = requestReasonDesc;
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

    public String getDepartmentReceiveName() {
        return departmentReceiveName;
    }

    public void setDepartmentReceiveName(String departmentReceiveName) {
        this.departmentReceiveName = departmentReceiveName;
    }

    public String getDepartmentResponsibleName() {
        return departmentResponsibleName;
    }

    public void setDepartmentResponsibleName(String departmentResponsibleName) {
        this.departmentResponsibleName = departmentResponsibleName;
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

    public String getRefundBankCode() {
        return refundBankCode;
    }

    public void setRefundBankCode(String refundBankCode) {
        this.refundBankCode = refundBankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

    public String getSplitRefund() {
        return splitRefund;
    }

    public void setSplitRefund(String splitRefund) {
        this.splitRefund = splitRefund;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
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
}
