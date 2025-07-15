package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.List;

public class RefundPeriodActiveBean {

    private String refundPeriod;
    private Long refundPeriodId;
    private Long refundRequestId;
    private Long receiveCompanyId;
    private Long receiveEmployeeId;
    private Long receiveInsurerId;
//    private BigDecimal cntrAmount;
//    private BigDecimal interestPaid;
//    private BigDecimal empeAmount;
//    private BigDecimal emprAmount;
//    private BigDecimal interestAmount;
//    private BigDecimal totalAmount;
    private BigDecimal requestEmpeAmount;
    private BigDecimal requestEmprAmount;
    private BigDecimal requestTotalAmount;
    private BigDecimal requestInterestAmount;
    private BigDecimal requestOverPaidAmount;
    private BigDecimal refundEmpeAmount;
    private BigDecimal refundEmprAmount;
    private BigDecimal refundInterestAmount;
    private BigDecimal refundOverPaidAmount;
    private BigDecimal refundTotalAmount;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundUnemploymentAmount;
    private String status;
    private List<SelectedEmployeeBean> selectedEmployeeList;
    private List<SelectedReceiptBean> selectedReceiptList;

    public String getRefundPeriod() {
        return refundPeriod;
    }

    public void setRefundPeriod(String refundPeriod) {
        this.refundPeriod = refundPeriod;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SelectedEmployeeBean> getSelectedEmployeeList() {
        return selectedEmployeeList;
    }

    public void setSelectedEmployeeList(List<SelectedEmployeeBean> selectedEmployeeList) {
        this.selectedEmployeeList = selectedEmployeeList;
    }

    public List<SelectedReceiptBean> getSelectedReceiptList() {
        return selectedReceiptList;
    }

    public void setSelectedReceiptList(List<SelectedReceiptBean> selectedReceiptList) {
        this.selectedReceiptList = selectedReceiptList;
    }
}
