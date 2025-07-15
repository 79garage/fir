package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class RefundChangeEmployerPeriodBean {

    private String companyBranchCode;
    private String approveStatus;
    private String approveStatusChange;
    private String refundPeriodYear;
    private String refundPeriodMonth;
    private BigDecimal totalWages;
    private BigDecimal totalWagesChange;
    private String receiveNo;
    private String refundOperationCode;
    private BigDecimal refundAmountEmpe;
    private BigDecimal refundAmountEmpeChange;
    private BigDecimal requestAmountEmpe;
    private BigDecimal requestAmountEmpeChange;
    private String idCardNo;
    private Long refundRequestChangeTypeId;
    private String sourceData;
    private Long refundChangeEmployerPeriodId;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String createBy;

    private BigDecimal requestInterestAmount;
    private BigDecimal requestInterestAmountChange;
    private BigDecimal requestTotalAmount;

    private BigDecimal refundSicknessAmount;
    private BigDecimal refundSicknessAmountChange;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundRetirementAmountChange;

    private BigDecimal refundUnemploymentAmount;
    private BigDecimal refundUnemploymentAmountChange;
    private BigDecimal refundRealWages;
    private BigDecimal refundRealWagesChange;

    public BigDecimal getTotalWagesChange() {
        return totalWagesChange;
    }

    public void setTotalWagesChange(BigDecimal totalWagesChange) {
        this.totalWagesChange = totalWagesChange;
    }

    public BigDecimal getRequestInterestAmount() {
        return requestInterestAmount;
    }

    public void setRequestInterestAmount(BigDecimal requestInterestAmount) {
        this.requestInterestAmount = requestInterestAmount;
    }

    public BigDecimal getRequestInterestAmountChange() {
        return requestInterestAmountChange;
    }

    public void setRequestInterestAmountChange(BigDecimal requestInterestAmountChange) {
        this.requestInterestAmountChange = requestInterestAmountChange;
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

    public BigDecimal getRefundRealWages() {
        return refundRealWages;
    }

    public void setRefundRealWages(BigDecimal refundRealWages) {
        this.refundRealWages = refundRealWages;
    }

    public BigDecimal getRefundRealWagesChange() {
        return refundRealWagesChange;
    }

    public void setRefundRealWagesChange(BigDecimal refundRealWagesChange) {
        this.refundRealWagesChange = refundRealWagesChange;
    }

    public Long getRefundChangeEmployerPeriodId() {
        return refundChangeEmployerPeriodId;
    }

    public void setRefundChangeEmployerPeriodId(Long refundChangeEmployerPeriodId) {
        this.refundChangeEmployerPeriodId = refundChangeEmployerPeriodId;
    }

    public String getCompanyBranchCode() {
        return companyBranchCode;
    }

    public void setCompanyBranchCode(String companyBranchCode) {
        this.companyBranchCode = companyBranchCode;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveStatusChange() {
        return approveStatusChange;
    }

    public void setApproveStatusChange(String approveStatusChange) {
        this.approveStatusChange = approveStatusChange;
    }

    public String getRefundPeriodYear() {
        return refundPeriodYear;
    }

    public void setRefundPeriodYear(String refundPeriodYear) {
        this.refundPeriodYear = refundPeriodYear;
    }

    public String getRefundPeriodMonth() {
        return refundPeriodMonth;
    }

    public void setRefundPeriodMonth(String refundPeriodMonth) {
        this.refundPeriodMonth = refundPeriodMonth;
    }

    public BigDecimal getTotalWages() {
        return totalWages;
    }

    public void setTotalWages(BigDecimal totalWages) {
        this.totalWages = totalWages;
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

    public BigDecimal getRefundAmountEmpe() {
        return refundAmountEmpe;
    }

    public void setRefundAmountEmpe(BigDecimal refundAmountEmpe) {
        this.refundAmountEmpe = refundAmountEmpe;
    }

    public BigDecimal getRefundAmountEmpeChange() {
        return refundAmountEmpeChange;
    }

    public void setRefundAmountEmpeChange(BigDecimal refundAmountEmpeChange) {
        this.refundAmountEmpeChange = refundAmountEmpeChange;
    }

    public BigDecimal getRequestAmountEmpe() {
        return requestAmountEmpe;
    }

    public void setRequestAmountEmpe(BigDecimal requestAmountEmpe) {
        this.requestAmountEmpe = requestAmountEmpe;
    }

    public BigDecimal getRequestAmountEmpeChange() {
        return requestAmountEmpeChange;
    }

    public void setRequestAmountEmpeChange(BigDecimal requestAmountEmpeChange) {
        this.requestAmountEmpeChange = requestAmountEmpeChange;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Long getRefundRequestChangeTypeId() {
        return refundRequestChangeTypeId;
    }

    public void setRefundRequestChangeTypeId(Long refundRequestChangeTypeId) {
        this.refundRequestChangeTypeId = refundRequestChangeTypeId;
    }

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
