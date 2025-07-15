package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.List;

public class SplitEmployeeRefundBean {

    private Long refundRequestId;
    private String receiveNo;
    private Long refundEmployeeId;
    private Long insurerId;
    private String idCardNo;
    private String fullName;
    private String progressStatus;
    private Long refRefundRequestId;
    private String refundTypePlace;
    private String provinceCode;
    private String distCode;
    private String subDistCode;
    private String refundPoOrder;
    private String refundPoOrderName;
    private String refundPromptPay;

    private Long refundBankId;
    private String refundBankCode;
    private Long refundBankBranchId;
    private String refundBankBranchCode;
    private String refundBankAccount;
    private BigDecimal requestEmpeAmount;
    private BigDecimal requestTotalAmount;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundUnemploymentAmount;
    private BigDecimal refundInterestAmount;
    private String refundStatus;
    private List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList;

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

    public Long getRefundEmployeeId() {
        return refundEmployeeId;
    }

    public void setRefundEmployeeId(Long refundEmployeeId) {
        this.refundEmployeeId = refundEmployeeId;
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

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public Long getRefRefundRequestId() {
        return refRefundRequestId;
    }

    public void setRefRefundRequestId(Long refRefundRequestId) {
        this.refRefundRequestId = refRefundRequestId;
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

    public String getRefundBankAccount() {
        return refundBankAccount;
    }

    public void setRefundBankAccount(String refundBankAccount) {
        this.refundBankAccount = refundBankAccount;
    }

    public List<SplitEmployeeRefundCompanyPeriodBean> getSplitEmployeePeriodList() {
        return splitEmployeePeriodList;
    }

    public void setSplitEmployeePeriodList(List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList) {
        this.splitEmployeePeriodList = splitEmployeePeriodList;
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

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundPoOrderName() {
        return refundPoOrderName;
    }

    public void setRefundPoOrderName(String refundPoOrderName) {
        this.refundPoOrderName = refundPoOrderName;
    }
}
