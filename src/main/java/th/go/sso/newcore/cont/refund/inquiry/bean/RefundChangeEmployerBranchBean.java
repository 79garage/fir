package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class RefundChangeEmployerBranchBean {

    private String companyBranchCode;
    private String approveStatus;
    private String approveStatusChange;
    private String refundPeriodYear;
    private String refundPeriodMonth;
    private String receiveNo;
    private String refundOperationCode;
    private BigDecimal refundAmountEmpe;
    private BigDecimal refundAmountEmpr;
    private BigDecimal refundAmountEmpeChange;
    private BigDecimal refundAmountEmprChange;
    private BigDecimal requestAmountEmpe;
    private BigDecimal requestAmountEmpr;
    private BigDecimal requestAmountEmpeChange;
    private BigDecimal requestAmountEmprChange;
    private Long refundRequestChangeTypeId;
    private String sourceData;
    private Long refundChangeEmployerBranchId;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String createBy;

    public Long getRefundChangeEmployerBranchId() {
        return refundChangeEmployerBranchId;
    }

    public void setRefundChangeEmployerBranchId(Long refundChangeEmployerBranchId) {
        this.refundChangeEmployerBranchId = refundChangeEmployerBranchId;
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

    public BigDecimal getRefundAmountEmpr() {
        return refundAmountEmpr;
    }

    public void setRefundAmountEmpr(BigDecimal refundAmountEmpr) {
        this.refundAmountEmpr = refundAmountEmpr;
    }

    public BigDecimal getRefundAmountEmpeChange() {
        return refundAmountEmpeChange;
    }

    public void setRefundAmountEmpeChange(BigDecimal refundAmountEmpeChange) {
        this.refundAmountEmpeChange = refundAmountEmpeChange;
    }

    public BigDecimal getRefundAmountEmprChange() {
        return refundAmountEmprChange;
    }

    public void setRefundAmountEmprChange(BigDecimal refundAmountEmprChange) {
        this.refundAmountEmprChange = refundAmountEmprChange;
    }

    public BigDecimal getRequestAmountEmpe() {
        return requestAmountEmpe;
    }

    public void setRequestAmountEmpe(BigDecimal requestAmountEmpe) {
        this.requestAmountEmpe = requestAmountEmpe;
    }

    public BigDecimal getRequestAmountEmpr() {
        return requestAmountEmpr;
    }

    public void setRequestAmountEmpr(BigDecimal requestAmountEmpr) {
        this.requestAmountEmpr = requestAmountEmpr;
    }

    public BigDecimal getRequestAmountEmpeChange() {
        return requestAmountEmpeChange;
    }

    public void setRequestAmountEmpeChange(BigDecimal requestAmountEmpeChange) {
        this.requestAmountEmpeChange = requestAmountEmpeChange;
    }

    public BigDecimal getRequestAmountEmprChange() {
        return requestAmountEmprChange;
    }

    public void setRequestAmountEmprChange(BigDecimal requestAmountEmprChange) {
        this.requestAmountEmprChange = requestAmountEmprChange;
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
