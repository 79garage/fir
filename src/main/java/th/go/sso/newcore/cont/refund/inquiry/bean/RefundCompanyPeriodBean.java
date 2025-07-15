package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class RefundCompanyPeriodBean {
    
    private String payPeriod;
    private Long refundPeriodId;
    private Long refundRequestId;
    private Long receiveCompanyId;
    private BigDecimal cntrAmount;
    private BigDecimal interestPaid;
    private BigDecimal empeAmount;
    private BigDecimal emprAmount;
    private BigDecimal interestAmount;
    private BigDecimal totalAmount;
    private BigDecimal requestEmpeAmount = BigDecimal.ZERO;
    private BigDecimal requestEmprAmount = BigDecimal.ZERO;
    private BigDecimal requestTotalAmount = BigDecimal.ZERO;
    private BigDecimal requestInterestAmount = BigDecimal.ZERO;
    private BigDecimal requestOverPaidAmount = BigDecimal.ZERO;
    private BigDecimal refundEmpeAmount = BigDecimal.ZERO;
    private BigDecimal refundEmprAmount = BigDecimal.ZERO;
    private BigDecimal refundInterestAmount;
    private BigDecimal refundOverPaidAmount = BigDecimal.ZERO;
    private BigDecimal refundTotalAmount = BigDecimal.ZERO;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundUnemploymentAmount;
    private String progressStatus;
    private String approveStatus;
    private String mode;
    private BigDecimal refundSicknessRate;
    private BigDecimal refundRetirementRate;
    private BigDecimal refundUnemploymentRate;
    private BigDecimal refundMaternityRate;
    private BigDecimal refundInvalidityRate;
    private BigDecimal refundChildAllowanceRate;
    private BigDecimal refundDeathRate;
    private BigDecimal refundSumRate;
    private String orderNo;
    private String orderStatus;
    private String orderRemark;
    private String disApproveReason;
    private BigDecimal empeSicknessRate;
    private BigDecimal empeRetirementRate;
    private BigDecimal empeUnemploymentRate;
    private BigDecimal empeSumRate;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paymentDate;
    private String companyBranchCode;
    private String companyBranchName;
    private List<RefundEmployeeBean> refundEmployeeList;
    private List<SelectedEmployeeBean> selectedEmployeeList;
    private List<RefundReceiptBean> receiptList;
    private List<SelectedReceiptBean> selectedReceiptList;
    private CompanyRateBean companyRate;
    private EmployeeRateBean employeeRate;

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public Long getRefundPeriodId() {
        return refundPeriodId;
    }

    public void setRefundPeriodId(Long refundPeriodId) {
        this.refundPeriodId = refundPeriodId;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public Long getReceiveCompanyId() {
        return receiveCompanyId;
    }

    public void setReceiveCompanyId(Long receiveCompanyId) {
        this.receiveCompanyId = receiveCompanyId;
    }

    public BigDecimal getCntrAmount() {
        return cntrAmount;
    }

    public void setCntrAmount(BigDecimal cntrAmount) {
        this.cntrAmount = cntrAmount;
    }

    public BigDecimal getInterestPaid() {
        return interestPaid;
    }

    public void setInterestPaid(BigDecimal interestPaid) {
        this.interestPaid = interestPaid;
    }

    public BigDecimal getEmpeAmount() {
        return empeAmount;
    }

    public void setEmpeAmount(BigDecimal empeAmount) {
        this.empeAmount = empeAmount;
    }

    public BigDecimal getEmprAmount() {
        return emprAmount;
    }

    public void setEmprAmount(BigDecimal emprAmount) {
        this.emprAmount = emprAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getRequestEmpeAmount() {
        return requestEmpeAmount;
    }

    public void setRequestEmpeAmount(BigDecimal requestEmpeAmount) {
        this.requestEmpeAmount = requestEmpeAmount;
    }

    public BigDecimal getRequestEmprAmount() {
        return requestEmprAmount;
    }

    public void setRequestEmprAmount(BigDecimal requestEmprAmount) {
        this.requestEmprAmount = requestEmprAmount;
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

    public BigDecimal getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<RefundEmployeeBean> getRefundEmployeeList() {
        return refundEmployeeList;
    }

    public void setRefundEmployeeList(List<RefundEmployeeBean> refundEmployeeList) {
        this.refundEmployeeList = refundEmployeeList;
    }

    public List<SelectedEmployeeBean> getSelectedEmployeeList() {
        return selectedEmployeeList;
    }

    public void setSelectedEmployeeList(List<SelectedEmployeeBean> selectedEmployeeList) {
        this.selectedEmployeeList = selectedEmployeeList;
    }

    public List<RefundReceiptBean> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<RefundReceiptBean> receiptList) {
        this.receiptList = receiptList;
    }

    public List<SelectedReceiptBean> getSelectedReceiptList() {
        return selectedReceiptList;
    }

    public void setSelectedReceiptList(List<SelectedReceiptBean> selectedReceiptList) {
        this.selectedReceiptList = selectedReceiptList;
    }

    public CompanyRateBean getCompanyRate() {
        return companyRate;
    }

    public void setCompanyRate(CompanyRateBean companyRate) {
        this.companyRate = companyRate;
    }

    public EmployeeRateBean getEmployeeRate() {
        return employeeRate;
    }

    public void setEmployeeRate(EmployeeRateBean employeeRate) {
        this.employeeRate = employeeRate;
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

    public BigDecimal getRefundMaternityRate() {
        return refundMaternityRate;
    }

    public void setRefundMaternityRate(BigDecimal refundMaternityRate) {
        this.refundMaternityRate = refundMaternityRate;
    }

    public BigDecimal getRefundInvalidityRate() {
        return refundInvalidityRate;
    }

    public void setRefundInvalidityRate(BigDecimal refundInvalidityRate) {
        this.refundInvalidityRate = refundInvalidityRate;
    }

    public BigDecimal getRefundChildAllowanceRate() {
        return refundChildAllowanceRate;
    }

    public void setRefundChildAllowanceRate(BigDecimal refundChildAllowanceRate) {
        this.refundChildAllowanceRate = refundChildAllowanceRate;
    }

    public BigDecimal getRefundDeathRate() {
        return refundDeathRate;
    }

    public void setRefundDeathRate(BigDecimal refundDeathRate) {
        this.refundDeathRate = refundDeathRate;
    }

    public BigDecimal getRefundSumRate() {
        return refundSumRate;
    }

    public void setRefundSumRate(BigDecimal refundSumRate) {
        this.refundSumRate = refundSumRate;
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

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getDisApproveReason() {
        return disApproveReason;
    }

    public void setDisApproveReason(String disApproveReason) {
        this.disApproveReason = disApproveReason;
    }

    public BigDecimal getEmpeSicknessRate() {
        return empeSicknessRate;
    }

    public void setEmpeSicknessRate(BigDecimal empeSicknessRate) {
        this.empeSicknessRate = empeSicknessRate;
    }

    public BigDecimal getEmpeRetirementRate() {
        return empeRetirementRate;
    }

    public void setEmpeRetirementRate(BigDecimal empeRetirementRate) {
        this.empeRetirementRate = empeRetirementRate;
    }

    public BigDecimal getEmpeUnemploymentRate() {
        return empeUnemploymentRate;
    }

    public void setEmpeUnemploymentRate(BigDecimal empeUnemploymentRate) {
        this.empeUnemploymentRate = empeUnemploymentRate;
    }

    public BigDecimal getEmpeSumRate() {
        return empeSumRate;
    }

    public void setEmpeSumRate(BigDecimal empeSumRate) {
        this.empeSumRate = empeSumRate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
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
}
