package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class RefundChangeEmployeeBean {

    private String refundBankBranchCode;
    private String refundBankBranchCodeChange;
    private String refundBankCode;
    private String refundBankCodeChange;
    private String departmentReceiveCode;
    private String departmentReceiveCodeChange;
    private String refundBankAccount;
    private String refundBankAccountChange;
    private String section;
    private String sectionChange;
    private String numSeq;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDate;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDateChange;
    private String receiveNo;
    private String receiveNoChange;
    private String periodEndYear;
    private String periodEndMonth;
    private String periodStrYear;
    private String periodStrMonth;
    private String periodEndYearChange;
    private String periodEndMonthChange;
    private String periodStrYearChange;
    private String periodStrMonthChange;
    private String refundOperationCode;
    private String refundTypePlace;
    private String refundTypePlaceChange;
    private BigDecimal requestEmpeAmount;
    private BigDecimal requestEmpeAmountChange;
    private String requestReasonCode;
    private String requestReasonCodeChange;
    private String idCardNo;
    private Long refundRequestChangeTypeId;
    private String sourceData;
    private Long refundRequestChangeEmployeeId;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String createBy;

    private String requestReasonOther;
    private String requestReasonOtherChange;
    private String refundRemark;
    private String refundPoOrder;
    private String refundPoOrderName;
    private String refundPromptPay;
    private String refundPoOrderChange;
    private String refundPoOrderNameChange;
    private String refundPromptPayChange;

    private String periodStart;
    private String periodEnd;
    private String refundRemarkChange;

    public String getRefundBankBranchCode() {
        return refundBankBranchCode;
    }

    public void setRefundBankBranchCode(String refundBankBranchCode) {
        this.refundBankBranchCode = refundBankBranchCode;
    }

    public String getRefundBankBranchCodeChange() {
        return refundBankBranchCodeChange;
    }

    public void setRefundBankBranchCodeChange(String refundBankBranchCodeChange) {
        this.refundBankBranchCodeChange = refundBankBranchCodeChange;
    }

    public String getRefundBankCode() {
        return refundBankCode;
    }

    public void setRefundBankCode(String refundBankCode) {
        this.refundBankCode = refundBankCode;
    }

    public String getRefundBankCodeChange() {
        return refundBankCodeChange;
    }

    public void setRefundBankCodeChange(String refundBankCodeChange) {
        this.refundBankCodeChange = refundBankCodeChange;
    }

    public String getDepartmentReceiveCode() {
        return departmentReceiveCode;
    }

    public void setDepartmentReceiveCode(String departmentReceiveCode) {
        this.departmentReceiveCode = departmentReceiveCode;
    }

    public String getDepartmentReceiveCodeChange() {
        return departmentReceiveCodeChange;
    }

    public void setDepartmentReceiveCodeChange(String departmentReceiveCodeChange) {
        this.departmentReceiveCodeChange = departmentReceiveCodeChange;
    }

    public String getRefundBankAccount() {
        return refundBankAccount;
    }

    public void setRefundBankAccount(String refundBankAccount) {
        this.refundBankAccount = refundBankAccount;
    }

    public String getRefundBankAccountChange() {
        return refundBankAccountChange;
    }

    public void setRefundBankAccountChange(String refundBankAccountChange) {
        this.refundBankAccountChange = refundBankAccountChange;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSectionChange() {
        return sectionChange;
    }

    public void setSectionChange(String sectionChange) {
        this.sectionChange = sectionChange;
    }

    public String getNumSeq() {
        return numSeq;
    }

    public void setNumSeq(String numSeq) {
        this.numSeq = numSeq;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getReceiveDateChange() {
        return receiveDateChange;
    }

    public void setReceiveDateChange(Date receiveDateChange) {
        this.receiveDateChange = receiveDateChange;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getReceiveNoChange() {
        return receiveNoChange;
    }

    public void setReceiveNoChange(String receiveNoChange) {
        this.receiveNoChange = receiveNoChange;
    }

    public String getPeriodEndYear() {
        return periodEndYear;
    }

    public void setPeriodEndYear(String periodEndYear) {
        this.periodEndYear = periodEndYear;
    }

    public String getPeriodEndMonth() {
        return periodEndMonth;
    }

    public void setPeriodEndMonth(String periodEndMonth) {
        this.periodEndMonth = periodEndMonth;
    }

    public String getPeriodStrYear() {
        return periodStrYear;
    }

    public void setPeriodStrYear(String periodStrYear) {
        this.periodStrYear = periodStrYear;
    }

    public String getPeriodStrMonth() {
        return periodStrMonth;
    }

    public void setPeriodStrMonth(String periodStrMonth) {
        this.periodStrMonth = periodStrMonth;
    }

    public String getPeriodEndYearChange() {
        return periodEndYearChange;
    }

    public void setPeriodEndYearChange(String periodEndYearChange) {
        this.periodEndYearChange = periodEndYearChange;
    }

    public String getPeriodEndMonthChange() {
        return periodEndMonthChange;
    }

    public void setPeriodEndMonthChange(String periodEndMonthChange) {
        this.periodEndMonthChange = periodEndMonthChange;
    }

    public String getPeriodStrYearChange() {
        return periodStrYearChange;
    }

    public void setPeriodStrYearChange(String periodStrYearChange) {
        this.periodStrYearChange = periodStrYearChange;
    }

    public String getPeriodStrMonthChange() {
        return periodStrMonthChange;
    }

    public void setPeriodStrMonthChange(String periodStrMonthChange) {
        this.periodStrMonthChange = periodStrMonthChange;
    }

    public String getRefundOperationCode() {
        return refundOperationCode;
    }

    public void setRefundOperationCode(String refundOperationCode) {
        this.refundOperationCode = refundOperationCode;
    }

    public String getRefundTypePlace() {
        return refundTypePlace;
    }

    public void setRefundTypePlace(String refundTypePlace) {
        this.refundTypePlace = refundTypePlace;
    }

    public String getRefundTypePlaceChange() {
        return refundTypePlaceChange;
    }

    public void setRefundTypePlaceChange(String refundTypePlaceChange) {
        this.refundTypePlaceChange = refundTypePlaceChange;
    }

    public BigDecimal getRequestEmpeAmount() {
        return requestEmpeAmount;
    }

    public void setRequestEmpeAmount(BigDecimal requestEmpeAmount) {
        this.requestEmpeAmount = requestEmpeAmount;
    }

    public BigDecimal getRequestEmpeAmountChange() {
        return requestEmpeAmountChange;
    }

    public void setRequestEmpeAmountChange(BigDecimal requestEmpeAmountChange) {
        this.requestEmpeAmountChange = requestEmpeAmountChange;
    }

    public String getRequestReasonCode() {
        return requestReasonCode;
    }

    public void setRequestReasonCode(String requestReasonCode) {
        this.requestReasonCode = requestReasonCode;
    }

    public String getRequestReasonCodeChange() {
        return requestReasonCodeChange;
    }

    public void setRequestReasonCodeChange(String requestReasonCodeChange) {
        this.requestReasonCodeChange = requestReasonCodeChange;
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

    public Long getRefundRequestChangeEmployeeId() {
        return refundRequestChangeEmployeeId;
    }

    public void setRefundRequestChangeEmployeeId(Long refundRequestChangeEmployeeId) {
        this.refundRequestChangeEmployeeId = refundRequestChangeEmployeeId;
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

    public String getRequestReasonOther() {
        return requestReasonOther;
    }

    public void setRequestReasonOther(String requestReasonOther) {
        this.requestReasonOther = requestReasonOther;
    }

    public String getRequestReasonOtherChange() {
        return requestReasonOtherChange;
    }

    public void setRequestReasonOtherChange(String requestReasonOtherChange) {
        this.requestReasonOtherChange = requestReasonOtherChange;
    }

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
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

    public String getRefundPoOrderChange() {
        return refundPoOrderChange;
    }

    public void setRefundPoOrderChange(String refundPoOrderChange) {
        this.refundPoOrderChange = refundPoOrderChange;
    }

    public String getRefundPoOrderNameChange() {
        return refundPoOrderNameChange;
    }

    public void setRefundPoOrderNameChange(String refundPoOrderNameChange) {
        this.refundPoOrderNameChange = refundPoOrderNameChange;
    }

    public String getRefundPromptPayChange() {
        return refundPromptPayChange;
    }

    public void setRefundPromptPayChange(String refundPromptPayChange) {
        this.refundPromptPayChange = refundPromptPayChange;
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

    public String getRefundRemarkChange() {
        return refundRemarkChange;
    }

    public void setRefundRemarkChange(String refundRemarkChange) {
        this.refundRemarkChange = refundRemarkChange;
    }
}
