package th.go.sso.newcore.cont.refund.inquiry.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConMsCompanyBean {

    private Integer companyId;
    private String companyNo;
    private String companyName;
    private String companyBranchCode;
    private String companyBranchName;
    private String companyBranchDescription;
    private String ssoBranchCode;
    private String ssoBranchName;
    private String ssoBranchDescription;
    private String depCode;
    private String businessType;
    private boolean isDebt;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public String getCompanyBranchDescription() {
        return companyBranchDescription;
    }

    public void setCompanyBranchDescription(String companyBranchDescription) {
        this.companyBranchDescription = companyBranchDescription;
    }

    public String getSsoBranchCode() {
        return ssoBranchCode;
    }

    public void setSsoBranchCode(String ssoBranchCode) {
        this.ssoBranchCode = ssoBranchCode;
    }

    public String getSsoBranchName() {
        return ssoBranchName;
    }

    public void setSsoBranchName(String ssoBranchName) {
        this.ssoBranchName = ssoBranchName;
    }

    public String getSsoBranchDescription() {
        return ssoBranchDescription;
    }

    public void setSsoBranchDescription(String ssoBranchDescription) {
        this.ssoBranchDescription = ssoBranchDescription;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @JsonProperty("isDebt")
    public boolean isDebt() {
        return isDebt;
    }

    public void setDebt(boolean debt) {
        isDebt = debt;
    }
}
