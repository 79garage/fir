package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class SearchDeleteCancelHistoryBean {
    private Long refundPeriodId;
    private Long refundRequestId;
    private String receiveNo;
    private String refundPeriod;
    private BigDecimal requestEmprAmount;
    private BigDecimal requestEmpeAmount;
    private BigDecimal requestInterestAmount;
    private String approveStatus;
    private String refundPeriodStatus;
    private String refundRequestStatus;
    private BigDecimal overPaid;
    private Integer countCompany;
    private Long companyId;
    private Long insurerId;
    private String idCardNo;
    private List<OverRefundEmployeeCompanyBean> companyDetails;
    private String updateBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date updateDate;

    public Long getRefundPeriodId() {
        return refundPeriodId;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public String getRefundPeriod() {
        return refundPeriod;
    }

    public BigDecimal getRequestEmprAmount() {
        return requestEmprAmount;
    }

    public BigDecimal getRequestEmpeAmount() {
        return requestEmpeAmount;
    }

    public BigDecimal getRequestInterestAmount() {
        return requestInterestAmount;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public String getRefundPeriodStatus() {
        return refundPeriodStatus;
    }

    public String getRefundRequestStatus() {
        return refundRequestStatus;
    }

    public BigDecimal getOverPaid() {
        return overPaid;
    }

    public Integer getCountCompany() {
        return countCompany;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Long getInsurerId() {
        return insurerId;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public List<OverRefundEmployeeCompanyBean> getCompanyDetails() {
        return companyDetails;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setRefundPeriodId(Long refundPeriodId) {
        this.refundPeriodId = refundPeriodId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public void setRefundPeriod(String refundPeriod) {
        this.refundPeriod = refundPeriod;
    }

    public void setRequestEmprAmount(BigDecimal requestEmprAmount) {
        this.requestEmprAmount = requestEmprAmount;
    }

    public void setRequestEmpeAmount(BigDecimal requestEmpeAmount) {
        this.requestEmpeAmount = requestEmpeAmount;
    }

    public void setRequestInterestAmount(BigDecimal requestInterestAmount) {
        this.requestInterestAmount = requestInterestAmount;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public void setRefundPeriodStatus(String refundPeriodStatus) {
        this.refundPeriodStatus = refundPeriodStatus;
    }

    public void setRefundRequestStatus(String refundRequestStatus) {
        this.refundRequestStatus = refundRequestStatus;
    }

    public void setOverPaid(BigDecimal overPaid) {
        this.overPaid = overPaid;
    }

    public void setCountCompany(Integer countCompany) {
        this.countCompany = countCompany;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public void setCompanyDetails(List<OverRefundEmployeeCompanyBean> companyDetails) {
        this.companyDetails = companyDetails;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
