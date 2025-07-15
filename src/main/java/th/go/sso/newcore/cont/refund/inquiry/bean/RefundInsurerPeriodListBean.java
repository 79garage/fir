package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class RefundInsurerPeriodListBean {

    private Long refundPeriodId;
    private Long refundRequestId;
    private Long receiveCompanyId;
    private Long receiveEmployeeId;
    private Long receiveInsurerId;
    private Long holdReceiveId;
    private String payPeriod;
    private String recordApproveBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date recordApproveDate;

    //update from conTrRefundPeriod
    private BigDecimal empeAmount;
    private BigDecimal emprAmount;
    private BigDecimal interestAmount;
    private BigDecimal totalAmount;
    private BigDecimal requestEmprAmount;
    private BigDecimal requestEmpeAmount;
    private BigDecimal requestTotalAmount;
    private BigDecimal requestInterestAmount;
    private BigDecimal requestOverPaidAmount;
    private BigDecimal refundEmprAmount;
    private BigDecimal refundEmpeAmount;
    private BigDecimal refundTotalAmount;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundUnemploymentAmount;
    private BigDecimal refundInterestAmount;
    private BigDecimal refundOverPaidAmount;
    private BigDecimal cntrAmount;
    private BigDecimal interestPaid;
    private String progressStatus;
    private String section;
    private String approveStatus;
    private String orderNo;
    private String orderStatus;
    private String orderRemark;
    private String refundReasonCode;
    private BigDecimal refundAmount;
    private Long insurerId;
    private BigDecimal refundSicknessRate;
    private BigDecimal refundRetirementRate;
    private BigDecimal refundUnemploymentRate;
    private BigDecimal refundMaternityRate;
    private BigDecimal refundInvalidityRate;
    private BigDecimal refundChildAllowanceRate;
    private BigDecimal refundDeathRate;
    private BigDecimal refundSumRate;
    private String disApproveReason;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paymentDate;
    private String fullName;
    private String idCardNo;
    private String receiptNo;
    private List<RefundReceiptInsurerBean> receiptList;
    private List<SelectedReceiptBean> selectedReceiptList;

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

    public Long getReceiveEmployeeId() {
        return receiveEmployeeId;
    }

    public void setReceiveEmployeeId(Long receiveEmployeeId) {
        this.receiveEmployeeId = receiveEmployeeId;
    }

    public Long getReceiveInsurerId() {
        return receiveInsurerId;
    }

    public void setReceiveInsurerId(Long receiveInsurerId) {
        this.receiveInsurerId = receiveInsurerId;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
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

    public BigDecimal getRequestEmprAmount() {
        return requestEmprAmount;
    }

    public void setRequestEmprAmount(BigDecimal requestEmprAmount) {
        this.requestEmprAmount = requestEmprAmount;
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

    public BigDecimal getRefundEmprAmount() {
        return refundEmprAmount;
    }

    public void setRefundEmprAmount(BigDecimal refundEmprAmount) {
        this.refundEmprAmount = refundEmprAmount;
    }

    public BigDecimal getRefundEmpeAmount() {
        return refundEmpeAmount;
    }

    public void setRefundEmpeAmount(BigDecimal refundEmpeAmount) {
        this.refundEmpeAmount = refundEmpeAmount;
    }

    public BigDecimal getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(BigDecimal refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

    public Long getHoldReceiveId() {
        return holdReceiveId;
    }

    public void setHoldReceiveId(Long holdReceiveId) {
        this.holdReceiveId = holdReceiveId;
    }

    public String getRefundReasonCode() {
        return refundReasonCode;
    }

    public void setRefundReasonCode(String refundReasonCode) {
        this.refundReasonCode = refundReasonCode;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
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

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
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

    public String getDisApproveReason() {
        return disApproveReason;
    }

    public void setDisApproveReason(String disApproveReason) {
        this.disApproveReason = disApproveReason;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }
}
