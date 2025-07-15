package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class ConTbPaybackRequestInfoBean {

    private Long paybackRequestId;

    private String receiveNo;
    private String paybackRequestNo;
    private String idCardNo;
    private String fullName;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paybackRequestDate;
    private BigDecimal paybackTotalAmount;
    private String status;
    private String paybackApproveStatus;
    private String companyNo;
    private String companyName;
    private String companyBranchCode;
    private String companyBranchName;
    private String paybackStatus;

    public Long getPaybackRequestId() {
        return paybackRequestId;
    }

    public void setPaybackRequestId(Long paybackRequestId) {
        this.paybackRequestId = paybackRequestId;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getPaybackRequestNo() {
        return paybackRequestNo;
    }

    public void setPaybackRequestNo(String paybackRequestNo) {
        this.paybackRequestNo = paybackRequestNo;
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

    public Date getPaybackRequestDate() {
        return paybackRequestDate;
    }

    public void setPaybackRequestDate(Date paybackRequestDate) {
        this.paybackRequestDate = paybackRequestDate;
    }

    public BigDecimal getPaybackTotalAmount() {
        return paybackTotalAmount;
    }

    public void setPaybackTotalAmount(BigDecimal paybackTotalAmount) {
        this.paybackTotalAmount = paybackTotalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaybackApproveStatus() {
        return paybackApproveStatus;
    }

    public void setPaybackApproveStatus(String paybackApproveStatus) {
        this.paybackApproveStatus = paybackApproveStatus;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyBranchCode() {
        return companyBranchCode;
    }

    public void setCompanyBranchCode(String companyBranchCode) {
        this.companyBranchCode = companyBranchCode;
    }

    public String getCompanyBranchName() {
        return companyBranchName;
    }

    public void setCompanyBranchName(String companyBranchName) {
        this.companyBranchName = companyBranchName;
    }

    public String getPaybackStatus() {
        return paybackStatus;
    }

    public void setPaybackStatus(String paybackStatus) {
        this.paybackStatus = paybackStatus;
    }
}
