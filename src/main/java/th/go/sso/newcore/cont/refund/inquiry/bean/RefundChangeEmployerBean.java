package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class RefundChangeEmployerBean {

    private String companyBranchCode;
    private String companyNo;
    private String approveStatus;
    private String approveStatusChange;
    private String refundBankBranchCode;
    private String refundBankBranchCodeChange;
    private String refundBankCode;
    private String refundBankCodeChange;
    private String departmentReceiveCode;
    private String departmentReceiveCodeChange;
    private String refundBankAccount;
    private String refundBankAccountChange;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date updateDate;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDateChange;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date noticeDate;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date noticeDateChange;
    private String noticeNo;
    private String noticeNoChange;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDate;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiveDateChange;
    private String receiveNo;
    private String refundOperationCode;
    private BigDecimal refundAmountEmpe;
    private BigDecimal refundAmountEmpr;
    private BigDecimal refundAmountEmpeChange;
    private BigDecimal refundAmountEmprChange;
    private String refundTypePlace;
    private BigDecimal refundAmountInt;
    private BigDecimal refundAmountIntChange;
    private BigDecimal refundAmountOldage;
    private BigDecimal refundAmountOldageChange;
    private BigDecimal refundAmountOver;
    private BigDecimal refundAmountOverChange;
    private BigDecimal refundAmountSick;
    private BigDecimal refundAmountSickChange;
    private BigDecimal refundAmountUnemp;
    private BigDecimal refundAmountUnempChange;
    private String refundTypePlaceChange;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date replyDate;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date replyDateChange;
    private BigDecimal requestAmountEmpe;
    private BigDecimal requestAmountEmpr;
    private BigDecimal requestAmountEmpeChange;
    private BigDecimal requestAmountEmprChange;
    private String requestReasonCode;
    private String requestReasonCodeChange;
    private Long refundRequestChangeTypeId;
    private String sourceData;
    private Long refundChangeEmployerId;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String createBy;

    private String periodEndYear;
    private String periodEndMonth;
    private String periodStrYear;
    private String periodStrMonth;
    private String periodEndYearChange;
    private String periodEndMonthChange;
    private String periodStrYearChange;
    private String periodStrMonthChange;
    private String requestReasonOther;
    private String requestReasonOtherChange;
    private String refundRemark;
    private String refundRemarkChange;
    private String refundPoOrder;
    private String refundPoOrderChange;
    private String refundPoOrderName;
    private String refundPoOrderNameChange;
    private String refundPromptPay;
    private String refundPromptPayChange;
    private BigDecimal refundRequestAmount;
    private BigDecimal refundRequestAmountChange;

    private Boolean isEmployeePeriod;

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

    public String getRefundRemarkChange() {
        return refundRemarkChange;
    }

    public void setRefundRemarkChange(String refundRemarkChange) {
        this.refundRemarkChange = refundRemarkChange;
    }

    public String getRefundPoOrder() {
        return refundPoOrder;
    }

    public void setRefundPoOrder(String refundPoOrder) {
        this.refundPoOrder = refundPoOrder;
    }

    public String getRefundPoOrderChange() {
        return refundPoOrderChange;
    }

    public void setRefundPoOrderChange(String refundPoOrderChange) {
        this.refundPoOrderChange = refundPoOrderChange;
    }

    public String getRefundPoOrderName() {
        return refundPoOrderName;
    }

    public void setRefundPoOrderName(String refundPoOrderName) {
        this.refundPoOrderName = refundPoOrderName;
    }

    public String getRefundPoOrderNameChange() {
        return refundPoOrderNameChange;
    }

    public void setRefundPoOrderNameChange(String refundPoOrderNameChange) {
        this.refundPoOrderNameChange = refundPoOrderNameChange;
    }

    public String getRefundPromptPay() {
        return refundPromptPay;
    }

    public void setRefundPromptPay(String refundPromptPay) {
        this.refundPromptPay = refundPromptPay;
    }

    public String getRefundPromptPayChange() {
        return refundPromptPayChange;
    }

    public void setRefundPromptPayChange(String refundPromptPayChange) {
        this.refundPromptPayChange = refundPromptPayChange;
    }

    public BigDecimal getRefundRequestAmount() {
        return refundRequestAmount;
    }

    public void setRefundRequestAmount(BigDecimal refundRequestAmount) {
        this.refundRequestAmount = refundRequestAmount;
    }

    public BigDecimal getRefundRequestAmountChange() {
        return refundRequestAmountChange;
    }

    public void setRefundRequestAmountChange(BigDecimal refundRequestAmountChange) {
        this.refundRequestAmountChange = refundRequestAmountChange;
    }

    public Long getRefundChangeEmployerId() {
        return refundChangeEmployerId;
    }

    public void setRefundChangeEmployerId(Long refundChangeEmployerId) {
        this.refundChangeEmployerId = refundChangeEmployerId;
    }

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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getApproveDateChange() {
        return approveDateChange;
    }

    public void setApproveDateChange(Date approveDateChange) {
        this.approveDateChange = approveDateChange;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Date getNoticeDateChange() {
        return noticeDateChange;
    }

    public void setNoticeDateChange(Date noticeDateChange) {
        this.noticeDateChange = noticeDateChange;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getNoticeNoChange() {
        return noticeNoChange;
    }

    public void setNoticeNoChange(String noticeNoChange) {
        this.noticeNoChange = noticeNoChange;
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

    public String getRefundTypePlace() {
        return refundTypePlace;
    }

    public void setRefundTypePlace(String refundTypePlace) {
        this.refundTypePlace = refundTypePlace;
    }

    public BigDecimal getRefundAmountInt() {
        return refundAmountInt;
    }

    public void setRefundAmountInt(BigDecimal refundAmountInt) {
        this.refundAmountInt = refundAmountInt;
    }

    public BigDecimal getRefundAmountIntChange() {
        return refundAmountIntChange;
    }

    public void setRefundAmountIntChange(BigDecimal refundAmountIntChange) {
        this.refundAmountIntChange = refundAmountIntChange;
    }

    public BigDecimal getRefundAmountOldage() {
        return refundAmountOldage;
    }

    public void setRefundAmountOldage(BigDecimal refundAmountOldage) {
        this.refundAmountOldage = refundAmountOldage;
    }

    public BigDecimal getRefundAmountOldageChange() {
        return refundAmountOldageChange;
    }

    public void setRefundAmountOldageChange(BigDecimal refundAmountOldageChange) {
        this.refundAmountOldageChange = refundAmountOldageChange;
    }

    public BigDecimal getRefundAmountOver() {
        return refundAmountOver;
    }

    public void setRefundAmountOver(BigDecimal refundAmountOver) {
        this.refundAmountOver = refundAmountOver;
    }

    public BigDecimal getRefundAmountOverChange() {
        return refundAmountOverChange;
    }

    public void setRefundAmountOverChange(BigDecimal refundAmountOverChange) {
        this.refundAmountOverChange = refundAmountOverChange;
    }

    public BigDecimal getRefundAmountSick() {
        return refundAmountSick;
    }

    public void setRefundAmountSick(BigDecimal refundAmountSick) {
        this.refundAmountSick = refundAmountSick;
    }

    public BigDecimal getRefundAmountSickChange() {
        return refundAmountSickChange;
    }

    public void setRefundAmountSickChange(BigDecimal refundAmountSickChange) {
        this.refundAmountSickChange = refundAmountSickChange;
    }

    public BigDecimal getRefundAmountUnemp() {
        return refundAmountUnemp;
    }

    public void setRefundAmountUnemp(BigDecimal refundAmountUnemp) {
        this.refundAmountUnemp = refundAmountUnemp;
    }

    public BigDecimal getRefundAmountUnempChange() {
        return refundAmountUnempChange;
    }

    public void setRefundAmountUnempChange(BigDecimal refundAmountUnempChange) {
        this.refundAmountUnempChange = refundAmountUnempChange;
    }

    public String getRefundTypePlaceChange() {
        return refundTypePlaceChange;
    }

    public void setRefundTypePlaceChange(String refundTypePlaceChange) {
        this.refundTypePlaceChange = refundTypePlaceChange;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public Date getReplyDateChange() {
        return replyDateChange;
    }

    public void setReplyDateChange(Date replyDateChange) {
        this.replyDateChange = replyDateChange;
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

    public Boolean getEmployeePeriod() {
        return isEmployeePeriod;
    }

    public void setEmployeePeriod(Boolean employeePeriod) {
        isEmployeePeriod = employeePeriod;
    }
}
