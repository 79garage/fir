package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class ConTbHoldReceiveBean {
    private Long holdReceiveId;
    private Long bankId;
    private Long bankBranchId;
    private String bankAccount;
    private String receiptNo;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date receiptDate;
    private String receiptName;
    private String companyNo;
    private String companyBranchNo;
    private String idCardNo;
    private String payPeriod;
    private String payPeriodMonth;
    private String payPeriodYear;
    private String numOfBranch;
    private String numOfEmployee;
    private String receiptChequeNo;
    private BigDecimal cntrAmount;
    private String flagStatus;
    private String flagPayType;
    private BigDecimal interestAmount;
    private String reasonCode;
    private String refundReceiptNo;
    private String section;
    private String refundReasonCode;
    private Long receiptId;
    private String depCode;

    public Long getHoldReceiveId() {
        return holdReceiveId;
    }

    public void setHoldReceiveId(Long holdReceiveId) {
        this.holdReceiveId = holdReceiveId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(Long bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyBranchNo() {
        return companyBranchNo;
    }

    public void setCompanyBranchNo(String companyBranchNo) {
        this.companyBranchNo = companyBranchNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public String getPayPeriodMonth() {
        return payPeriodMonth;
    }

    public void setPayPeriodMonth(String payPeriodMonth) {
        this.payPeriodMonth = payPeriodMonth;
    }

    public String getPayPeriodYear() {
        return payPeriodYear;
    }

    public void setPayPeriodYear(String payPeriodYear) {
        this.payPeriodYear = payPeriodYear;
    }

    public String getNumOfBranch() {
        return numOfBranch;
    }

    public void setNumOfBranch(String numOfBranch) {
        this.numOfBranch = numOfBranch;
    }

    public String getNumOfEmployee() {
        return numOfEmployee;
    }

    public void setNumOfEmployee(String numOfEmployee) {
        this.numOfEmployee = numOfEmployee;
    }

    public String getReceiptChequeNo() {
        return receiptChequeNo;
    }

    public void setReceiptChequeNo(String receiptChequeNo) {
        this.receiptChequeNo = receiptChequeNo;
    }

    public BigDecimal getCntrAmount() {
        return cntrAmount;
    }

    public void setCntrAmount(BigDecimal cntrAmount) {
        this.cntrAmount = cntrAmount;
    }

    public String getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(String flagStatus) {
        this.flagStatus = flagStatus;
    }

    public String getFlagPayType() {
        return flagPayType;
    }

    public void setFlagPayType(String flagPayType) {
        this.flagPayType = flagPayType;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getRefundReceiptNo() {
        return refundReceiptNo;
    }

    public void setRefundReceiptNo(String refundReceiptNo) {
        this.refundReceiptNo = refundReceiptNo;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRefundReasonCode() {
        return refundReasonCode;
    }

    public void setRefundReasonCode(String refundReasonCode) {
        this.refundReasonCode = refundReasonCode;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }
}
