package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class RefundChangeEmployeeBranchPeriodBean {

    private String companyBranchCode;
    private String companyNo;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date updateDate;
    private String receiveNo;
    private String refundOperationCode;
    private String refundPeriodEndYear;
    private String refundPeriodEndMonth;
    private String refundPeriodStrYear;
    private String refundPeriodStrMonth;
    private Long refundRequestChangeTypeId;
    private String sourceData;
    private Long refundChangeEmployeeBranchPeriodId;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String createBy;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundSicknessAmountChange;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundRetirementAmountChange;
    private BigDecimal requestEmpeAmount;
    private BigDecimal requestInterestAmount;
    private BigDecimal requestTotalAmount;

    public String getCompanyBranchCode() {
        return companyBranchCode;
    }

    public void setCompanyBranchCode(String companyBranchCode) {
        this.companyBranchCode = companyBranchCode;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public String getRefundPeriodEndYear() {
        return refundPeriodEndYear;
    }

    public void setRefundPeriodEndYear(String refundPeriodEndYear) {
        this.refundPeriodEndYear = refundPeriodEndYear;
    }

    public String getRefundPeriodEndMonth() {
        return refundPeriodEndMonth;
    }

    public void setRefundPeriodEndMonth(String refundPeriodEndMonth) {
        this.refundPeriodEndMonth = refundPeriodEndMonth;
    }

    public String getRefundPeriodStrYear() {
        return refundPeriodStrYear;
    }

    public void setRefundPeriodStrYear(String refundPeriodStrYear) {
        this.refundPeriodStrYear = refundPeriodStrYear;
    }

    public String getRefundPeriodStrMonth() {
        return refundPeriodStrMonth;
    }

    public void setRefundPeriodStrMonth(String refundPeriodStrMonth) {
        this.refundPeriodStrMonth = refundPeriodStrMonth;
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

    public Long getRefundChangeEmployeeBranchPeriodId() {
        return refundChangeEmployeeBranchPeriodId;
    }

    public void setRefundChangeEmployeeBranchPeriodId(Long refundChangeEmployeeBranchPeriodId) {
        this.refundChangeEmployeeBranchPeriodId = refundChangeEmployeeBranchPeriodId;
    }

    public Long getRefundRequestChangeTypeId() {
        return refundRequestChangeTypeId;
    }

    public void setRefundRequestChangeTypeId(Long refundRequestChangeTypeId) {
        this.refundRequestChangeTypeId = refundRequestChangeTypeId;
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
}
