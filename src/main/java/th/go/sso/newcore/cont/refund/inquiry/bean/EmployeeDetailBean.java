package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;

public class EmployeeDetailBean {

    private Long receiveEmployeeId;
    private Long insurerId;
    private String idCardNo;
    private String fullName;
    private BigDecimal cntrAmont;
    private BigDecimal empeOverp;
    private String refundReasonCode;

    public Long getReceiveEmployeeId() {
        return receiveEmployeeId;
    }

    public void setReceiveEmployeeId(Long receiveEmployeeId) {
        this.receiveEmployeeId = receiveEmployeeId;
    }

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
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

    public BigDecimal getCntrAmont() {
        return cntrAmont;
    }

    public void setCntrAmont(BigDecimal cntrAmont) {
        this.cntrAmont = cntrAmont;
    }

    public BigDecimal getEmpeOverp() {
        return empeOverp;
    }

    public void setEmpeOverp(BigDecimal empeOverp) {
        this.empeOverp = empeOverp;
    }

    public String getRefundReasonCode() {
        return refundReasonCode;
    }

    public void setRefundReasonCode(String refundReasonCode) {
        this.refundReasonCode = refundReasonCode;
    }
}
