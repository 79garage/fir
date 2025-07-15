package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class RefundChangeEmployeeReplyBean {

    private String approveStatus;
    private String approveStatusChange;

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
    private String receiveNo;
    private String refundOperationCode;
    private String refundPeriodEndYear;
    private String refundPeriodEndMonth;
    private String refundPeriodStrYear;
    private String refundPeriodStrMonth;
    private String refundPeriodEndYearChange;
    private String refundPeriodEndMonthChange;
    private String refundPeriodStrYearChange;
    private String refundPeriodStrMonthChange;
    private BigDecimal refundEmpeAmount;
    private BigDecimal refundEmpeAmountChange;
    private BigDecimal refundInterestAmount;
    private BigDecimal refundInterestAmountChange;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundRetirementAmountChange;
    private BigDecimal requestOverPaidAmount;
    private BigDecimal requestOverPaidAmountChange;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundSicknessAmountChange;
    private BigDecimal refundUnemploymentAmount;
    private BigDecimal refundUnemploymentAmountChange;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date replyDate;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date replyDateChange;
    private String idCardNo;
    private Long refundRequestChangeTypeId;
    private String sourceData;
    private Long refundChangeEmployeeReplyId;

    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private String createBy;

    public Long getRefundChangeEmployeeReplyId() {
        return refundChangeEmployeeReplyId;
    }

    public void setRefundChangeEmployeeReplyId(Long refundChangeEmployeeReplyId) {
        this.refundChangeEmployeeReplyId = refundChangeEmployeeReplyId;
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

    public String getRefundPeriodEndYearChange() {
        return refundPeriodEndYearChange;
    }

    public void setRefundPeriodEndYearChange(String refundPeriodEndYearChange) {
        this.refundPeriodEndYearChange = refundPeriodEndYearChange;
    }

    public String getRefundPeriodEndMonthChange() {
        return refundPeriodEndMonthChange;
    }

    public void setRefundPeriodEndMonthChange(String refundPeriodEndMonthChange) {
        this.refundPeriodEndMonthChange = refundPeriodEndMonthChange;
    }

    public String getRefundPeriodStrYearChange() {
        return refundPeriodStrYearChange;
    }

    public void setRefundPeriodStrYearChange(String refundPeriodStrYearChange) {
        this.refundPeriodStrYearChange = refundPeriodStrYearChange;
    }

    public String getRefundPeriodStrMonthChange() {
        return refundPeriodStrMonthChange;
    }

    public void setRefundPeriodStrMonthChange(String refundPeriodStrMonthChange) {
        this.refundPeriodStrMonthChange = refundPeriodStrMonthChange;
    }

    public BigDecimal getRefundEmpeAmount() {
        return refundEmpeAmount;
    }

    public void setRefundEmpeAmount(BigDecimal refundEmpeAmount) {
        this.refundEmpeAmount = refundEmpeAmount;
    }

    public BigDecimal getRefundEmpeAmountChange() {
        return refundEmpeAmountChange;
    }

    public void setRefundEmpeAmountChange(BigDecimal refundEmpeAmountChange) {
        this.refundEmpeAmountChange = refundEmpeAmountChange;
    }

    public BigDecimal getRefundInterestAmount() {
        return refundInterestAmount;
    }

    public void setRefundInterestAmount(BigDecimal refundInterestAmount) {
        this.refundInterestAmount = refundInterestAmount;
    }

    public BigDecimal getRefundInterestAmountChange() {
        return refundInterestAmountChange;
    }

    public void setRefundInterestAmountChange(BigDecimal refundInterestAmountChange) {
        this.refundInterestAmountChange = refundInterestAmountChange;
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

    public BigDecimal getRequestOverPaidAmount() {
        return requestOverPaidAmount;
    }

    public void setRequestOverPaidAmount(BigDecimal requestOverPaidAmount) {
        this.requestOverPaidAmount = requestOverPaidAmount;
    }

    public BigDecimal getRequestOverPaidAmountChange() {
        return requestOverPaidAmountChange;
    }

    public void setRequestOverPaidAmountChange(BigDecimal requestOverPaidAmountChange) {
        this.requestOverPaidAmountChange = requestOverPaidAmountChange;
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
