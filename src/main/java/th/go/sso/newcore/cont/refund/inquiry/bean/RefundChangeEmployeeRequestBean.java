package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class RefundChangeEmployeeRequestBean {

    private String approveStatus;
    private String refundBankBranchCode;
    private String refundBankCode;
    private String departmentReceiveCode;
    private String refundBankAccount;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date noticeDate;
    private String noticeNo;
    private String receiveNo;
    private String periodEndYear;
    private String periodEndMonth;
    private String periodStrYear;
    private String periodStrMonth;
    private String refundOperationCode;
    private String refundPeriodEndYear;
    private String refundPeriodStrYear;
    private BigDecimal refundEmpeAmount;
    private String refundTypePlace;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date replyDate;
    private BigDecimal requestAmount;
    private String requestReasonCode;
    private String idCardNo;
    private Long refundRequestChangeTypeId;
    private String sourceData;
    private Long refundChangeEmployeeRequestId;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String createBy;
    private String refundPeriodEndMonth;
    private String refundPeriodStrMonth;

    public Long getRefundChangeEmployeeRequestId() {
        return refundChangeEmployeeRequestId;
    }

    public void setRefundChangeEmployeeRequestId(Long refundChangeEmployeeRequestId) {
        this.refundChangeEmployeeRequestId = refundChangeEmployeeRequestId;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getRefundBankBranchCode() {
        return refundBankBranchCode;
    }

    public void setRefundBankBranchCode(String refundBankBranchCode) {
        this.refundBankBranchCode = refundBankBranchCode;
    }

    public String getRefundBankCode() {
        return refundBankCode;
    }

    public void setRefundBankCode(String refundBankCode) {
        this.refundBankCode = refundBankCode;
    }

    public String getDepartmentReceiveCode() {
        return departmentReceiveCode;
    }

    public void setDepartmentReceiveCode(String departmentReceiveCode) {
        this.departmentReceiveCode = departmentReceiveCode;
    }

    public String getRefundBankAccount() {
        return refundBankAccount;
    }

    public void setRefundBankAccount(String refundBankAccount) {
        this.refundBankAccount = refundBankAccount;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
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

    public String getRefundPeriodStrYear() {
        return refundPeriodStrYear;
    }

    public void setRefundPeriodStrYear(String refundPeriodStrYear) {
        this.refundPeriodStrYear = refundPeriodStrYear;
    }

    public BigDecimal getRefundEmpeAmount() {
        return refundEmpeAmount;
    }

    public void setRefundEmpeAmount(BigDecimal refundEmpeAmount) {
        this.refundEmpeAmount = refundEmpeAmount;
    }

    public String getRefundTypePlace() {
        return refundTypePlace;
    }

    public void setRefundTypePlace(String refundTypePlace) {
        this.refundTypePlace = refundTypePlace;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getRequestReasonCode() {
        return requestReasonCode;
    }

    public void setRequestReasonCode(String requestReasonCode) {
        this.requestReasonCode = requestReasonCode;
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

    public String getRefundPeriodEndMonth() {
        return refundPeriodEndMonth;
    }

    public void setRefundPeriodEndMonth(String refundPeriodEndMonth) {
        this.refundPeriodEndMonth = refundPeriodEndMonth;
    }

    public String getRefundPeriodStrMonth() {
        return refundPeriodStrMonth;
    }

    public void setRefundPeriodStrMonth(String refundPeriodStrMonth) {
        this.refundPeriodStrMonth = refundPeriodStrMonth;
    }
}
