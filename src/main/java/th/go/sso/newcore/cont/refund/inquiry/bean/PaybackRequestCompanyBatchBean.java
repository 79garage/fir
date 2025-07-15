package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

public class PaybackRequestCompanyBatchBean {
    private Long paybackRequestId;
    private Long refundRequestId;
    private Long companyId;
    private Long insurerId;
    private BigDecimal paybackTotalAmount;
    private String paybackRequestNo;
    private Date paybackRequestDate;
    private String paybackRequestType;
    private String paybackApproveStatus;
    private Date paybackApproveDate;
    private String paybackReceiveStatus;
    private Date paybackReceiveDate;
    private String section;
    private String status;
    private String remark;
    private String reversStatus;
    private String paybackCause;
    private Long payback40Id;
    private BigDecimal receiveTotalAmount;
    private Date paybackDateline;
    private BigDecimal increaseTotalAmount;
    private String depCode;
    private Long refundReceiptId;
    private String backCode;

    public Long getPaybackRequestId() {
        return paybackRequestId;
    }

    public void setPaybackRequestId(Long paybackRequestId) {
        this.paybackRequestId = paybackRequestId;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public BigDecimal getPaybackTotalAmount() {
        return paybackTotalAmount;
    }

    public void setPaybackTotalAmount(BigDecimal paybackTotalAmount) {
        this.paybackTotalAmount = paybackTotalAmount;
    }

    public String getPaybackRequestNo() {
        return paybackRequestNo;
    }

    public void setPaybackRequestNo(String paybackRequestNo) {
        this.paybackRequestNo = paybackRequestNo;
    }

    public Date getPaybackRequestDate() {
        return paybackRequestDate;
    }

    public void setPaybackRequestDate(Date paybackRequestDate) {
        this.paybackRequestDate = paybackRequestDate;
    }

    public String getPaybackRequestType() {
        return paybackRequestType;
    }

    public void setPaybackRequestType(String paybackRequestType) {
        this.paybackRequestType = paybackRequestType;
    }

    public String getPaybackApproveStatus() {
        return paybackApproveStatus;
    }

    public void setPaybackApproveStatus(String paybackApproveStatus) {
        this.paybackApproveStatus = paybackApproveStatus;
    }

    public Date getPaybackApproveDate() {
        return paybackApproveDate;
    }

    public void setPaybackApproveDate(Date paybackApproveDate) {
        this.paybackApproveDate = paybackApproveDate;
    }

    public String getPaybackReceiveStatus() {
        return paybackReceiveStatus;
    }

    public void setPaybackReceiveStatus(String paybackReceiveStatus) {
        this.paybackReceiveStatus = paybackReceiveStatus;
    }

    public Date getPaybackReceiveDate() {
        return paybackReceiveDate;
    }

    public void setPaybackReceiveDate(Date paybackReceiveDate) {
        this.paybackReceiveDate = paybackReceiveDate;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReversStatus() {
        return reversStatus;
    }

    public void setReversStatus(String reversStatus) {
        this.reversStatus = reversStatus;
    }

    public String getPaybackCause() {
        return paybackCause;
    }

    public void setPaybackCause(String paybackCause) {
        this.paybackCause = paybackCause;
    }

    public Long getPayback40Id() {
        return payback40Id;
    }

    public void setPayback40Id(Long payback40Id) {
        this.payback40Id = payback40Id;
    }

    public BigDecimal getReceiveTotalAmount() {
        return receiveTotalAmount;
    }

    public void setReceiveTotalAmount(BigDecimal receiveTotalAmount) {
        this.receiveTotalAmount = receiveTotalAmount;
    }

    public Date getPaybackDateline() {
        return paybackDateline;
    }

    public void setPaybackDateline(Date paybackDateline) {
        this.paybackDateline = paybackDateline;
    }

    public BigDecimal getIncreaseTotalAmount() {
        return increaseTotalAmount;
    }

    public void setIncreaseTotalAmount(BigDecimal increaseTotalAmount) {
        this.increaseTotalAmount = increaseTotalAmount;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public Long getRefundReceiptId() {
        return refundReceiptId;
    }

    public void setRefundReceiptId(Long refundReceiptId) {
        this.refundReceiptId = refundReceiptId;
    }

    public String getBackCode() {
        return backCode;
    }

    public void setBackCode(String backCode) {
        this.backCode = backCode;
    }
}
