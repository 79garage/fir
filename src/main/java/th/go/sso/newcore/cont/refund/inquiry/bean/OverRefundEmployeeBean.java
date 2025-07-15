package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;
import th.go.sso.newcore.cont.common.utils.NumberUtils;

public class OverRefundEmployeeBean {

    private Long refundRequestId;
    private String receiveNo;
    private String idCardNo;
    private String fullName;
    private String recordApproveBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date recordApproveDate;
    private BigDecimal sumAmount;
    private BigDecimal requestInterestAmount;
    private BigDecimal requestTotalAmount; //overPaid
    private BigDecimal requestEmpeAmount;
    private BigDecimal refundTotalAmount;
    private BigDecimal refundEmpeAmount;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundUnemploymentAmount;
    private BigDecimal cntrAmount;
    private String companyNo;
    private int countCompany;
    private Long refundPeriodId;
    private String payPeriod;
    private String approveStatus;
    private String approveNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date approveDate;
    private String receiptNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paymentDate;
    private String progressStatus;
    private String noticeStatus;
    private String announceStatus;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date dateTemp;
    private String companyIds;
    private String receiptIds;
    private String reverseStatus;
    private String reverseRemark;
    private String refundReasonCode;
    private String orderNo;
    private String orderStatus;
    private String orderRemark;
    private String depCode;
    private Integer countOrderNo;
    private BigDecimal refundSicknessRate;
    private BigDecimal refundRetirementRate;
    private BigDecimal refundUnemploymentRate;
    private BigDecimal refundMaternityRate;
    private BigDecimal refundInvalidityRate;
    private BigDecimal refundChildAllowanceRate;
    private BigDecimal refundDeathRate;
    private BigDecimal refundSumRate;
    private Integer rate;
    private BigDecimal totalWages;
    private String disApproveReason;
    private BigDecimal overPaid;
    private Long insurerId;
    private List<OrderBean> orderList;
    private List<RefundReceiptInsurerBean> receiptList;
    private List<SelectedReceiptBean> selectedReceiptList;
    private List<OverRefundEmployeeCompanyBean> companyDetails;

    public OverRefundEmployeeBean(Long refundRequestId
            , String payPeriod
            , String refundReasonCode
            , BigDecimal sumAmount
            , BigDecimal requestTotalAmount
            , String companyNo
            , Integer rate
            , BigDecimal totalWages
            , BigDecimal overPaid
            , Date paymentDate) {
        this.refundRequestId = refundRequestId;
        this.payPeriod = payPeriod;
        this.refundReasonCode = refundReasonCode;
        this.sumAmount = sumAmount;
        this.requestTotalAmount = requestTotalAmount;
        this.companyNo = companyNo;
        this.rate = rate;
        this.totalWages = totalWages;
        this.overPaid = calculateOverPaid();
        this.paymentDate = paymentDate;
    }

    public OverRefundEmployeeBean() {

    }

    public BigDecimal calculateOverPaid() {
        BigDecimal maxTotalWages = BigDecimal.valueOf(15000);
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        return (this.sumAmount.subtract(((NumberUtils.convertZeroIfNull(this.totalWages).compareTo(maxTotalWages) >= 0 ? maxTotalWages : NumberUtils.convertZeroIfNull(this.totalWages) )
                .multiply(BigDecimal.valueOf(this.rate)))
                .divide(oneHundred))).subtract(this.requestTotalAmount);
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
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

    public String getRecordApproveBy() {
        return recordApproveBy;
    }

    public void setRecordApproveBy(String recordApproveBy) {
        this.recordApproveBy = recordApproveBy;
    }

    public Date getRecordApproveDate() {
        return recordApproveDate;
    }

    public void setRecordApproveDate(Date recordApproveDate) {
        this.recordApproveDate = recordApproveDate;
    }

    public BigDecimal getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(BigDecimal sumAmount) {
        this.sumAmount = sumAmount;
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

    public BigDecimal getRequestEmpeAmount() {
        return requestEmpeAmount;
    }

    public void setRequestEmpeAmount(BigDecimal requestEmpeAmount) {
        this.requestEmpeAmount = requestEmpeAmount;
    }

    public BigDecimal getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
    }

    public BigDecimal getRefundEmpeAmount() {
        return refundEmpeAmount;
    }

    public void setRefundEmpeAmount(BigDecimal refundEmpeAmount) {
        this.refundEmpeAmount = refundEmpeAmount;
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

    public BigDecimal getCntrAmount() {
        return cntrAmount;
    }

    public void setCntrAmount(BigDecimal cntrAmount) {
        this.cntrAmount = cntrAmount;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public int getCountCompany() {
        return countCompany;
    }

    public void setCountCompany(int countCompany) {
        this.countCompany = countCompany;
    }

    public Long getRefundPeriodId() {
        return refundPeriodId;
    }

    public void setRefundPeriodId(Long refundPeriodId) {
        this.refundPeriodId = refundPeriodId;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveNo() {
        return approveNo;
    }

    public void setApproveNo(String approveNo) {
        this.approveNo = approveNo;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getAnnounceStatus() {
        return announceStatus;
    }

    public void setAnnounceStatus(String announceStatus) {
        this.announceStatus = announceStatus;
    }

    public Date getDateTemp() {
        return dateTemp;
    }

    public void setDateTemp(Date dateTemp) {
        this.dateTemp = dateTemp;
    }

    public String getReceiptIds() {
        return receiptIds;
    }

    public void setReceiptIds(String receiptIds) {
        this.receiptIds = receiptIds;
    }

    public List<RefundReceiptInsurerBean> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<RefundReceiptInsurerBean> receiptList) {
        this.receiptList = receiptList;
    }

    public List<SelectedReceiptBean> getSelectedReceiptList() {
        return selectedReceiptList;
    }

    public void setSelectedReceiptList(List<SelectedReceiptBean> selectedReceiptList) {
        this.selectedReceiptList = selectedReceiptList;
    }

    public List<OverRefundEmployeeCompanyBean> getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(List<OverRefundEmployeeCompanyBean> companyDetails) {
        this.companyDetails = companyDetails;
    }

    public String getReverseStatus() {
        return reverseStatus;
    }

    public void setReverseStatus(String reverseStatus) {
        this.reverseStatus = reverseStatus;
    }

    public String getReverseRemark() {
        return reverseRemark;
    }

    public void setReverseRemark(String reverseRemark) {
        this.reverseRemark = reverseRemark;
    }

    public String getRefundReasonCode() {
        return refundReasonCode;
    }

    public void setRefundReasonCode(String refundReasonCode) {
        this.refundReasonCode = refundReasonCode;
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

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public Integer getCountOrderNo() {
        return countOrderNo;
    }

    public void setCountOrderNo(Integer countOrderNo) {
        this.countOrderNo = countOrderNo;
    }

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
    }

    public String getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public BigDecimal getTotalWages() {
        return totalWages;
    }

    public void setTotalWages(BigDecimal totalWages) {
        this.totalWages = totalWages;
    }

    public String getDisApproveReason() {
        return disApproveReason;
    }

    public void setDisApproveReason(String disApproveReason) {
        this.disApproveReason = disApproveReason;
    }

    public BigDecimal getOverPaid() {
        return overPaid;
    }

    public void setOverPaid(BigDecimal overPaid) {
        this.overPaid = overPaid;
    }

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }
}
