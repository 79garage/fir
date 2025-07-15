package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

public class RequesterCompanyBatchBean {
    private Long requesterId;
    private String ssoNo;
    private String orderNo;
    private String orderDocNo;
    private Date orderDocDate;
    private BigDecimal orderMny;
    private Date approveDate;
    private String claimantNo;//companyNo
    private String claimantName;//companyBranchName
    private String branchNo;//companyBranchCode
    private BigDecimal interestMny; //refundInterestAmount
    private BigDecimal payMny;//refundTotalAmount
    private String paymentPlaceType;

    private String bankAcc;
    private String bankCode;
    private String address;
    private String distCode;
    private String distName;
    private String subDistCode;
    private String subDistName;
    private String provinceCode;
    private String provinceName;
    private String zipCode;
    private BigDecimal refundSicknessAmount;
    private BigDecimal refundRetirementAmount;
    private BigDecimal refundUnemploymentAmount;
    private BigDecimal refundSicknessRate;
    private BigDecimal refundRetirementRate;
    private BigDecimal refundUnemploymentRate;
    private BigDecimal refundInterestAmount;
    private String period;
    private String poOrder;
    private String poOrderName;

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public String getSsoNo() {
        return ssoNo;
    }

    public void setSsoNo(String ssoNo) {
        this.ssoNo = ssoNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDocNo() {
        return orderDocNo;
    }

    public void setOrderDocNo(String orderDocNo) {
        this.orderDocNo = orderDocNo;
    }

    public Date getOrderDocDate() {
        return orderDocDate;
    }

    public void setOrderDocDate(Date orderDocDate) {
        this.orderDocDate = orderDocDate;
    }

    public BigDecimal getOrderMny() {
        return orderMny;
    }

    public void setOrderMny(BigDecimal orderMny) {
        this.orderMny = orderMny;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getClaimantNo() {
        return claimantNo;
    }

    public void setClaimantNo(String claimantNo) {
        this.claimantNo = claimantNo;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public BigDecimal getInterestMny() {
        return interestMny;
    }

    public void setInterestMny(BigDecimal interestMny) {
        this.interestMny = interestMny;
    }

    public BigDecimal getPayMny() {
        return payMny;
    }

    public void setPayMny(BigDecimal payMny) {
        this.payMny = payMny;
    }

    public String getPaymentPlaceType() {
        return paymentPlaceType;
    }

    public void setPaymentPlaceType(String paymentPlaceType) {
        this.paymentPlaceType = paymentPlaceType;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public String getSubDistCode() {
        return subDistCode;
    }

    public void setSubDistCode(String subDistCode) {
        this.subDistCode = subDistCode;
    }

    public String getSubDistName() {
        return subDistName;
    }

    public void setSubDistName(String subDistName) {
        this.subDistName = subDistName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public BigDecimal getRefundInterestAmount() {
        return refundInterestAmount;
    }

    public void setRefundInterestAmount(BigDecimal refundInterestAmount) {
        this.refundInterestAmount = refundInterestAmount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPoOrder() {
        return poOrder;
    }

    public void setPoOrder(String poOrder) {
        this.poOrder = poOrder;
    }

    public String getPoOrderName() {
        return poOrderName;
    }

    public void setPoOrderName(String poOrderName) {
        this.poOrderName = poOrderName;
    }
}
