package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConMsInsurerBean {

    private Long insurerId;
    private String idCardNo;
    private String fullName;
    private String insurerStatus;
    private String section;
    private String depCode;
    private String depName;
    private boolean isDebt;

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

    public String getInsurerStatus() {
        return insurerStatus;
    }

    public void setInsurerStatus(String insurerStatus) {
        this.insurerStatus = insurerStatus;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @JsonProperty("isDebt")
    public boolean isDebt() {
        return isDebt;
    }

    public void setDebt(boolean debt) {
        this.isDebt = debt;
    }
}
