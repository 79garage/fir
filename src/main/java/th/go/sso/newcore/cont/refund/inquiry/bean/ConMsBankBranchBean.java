package th.go.sso.newcore.cont.refund.inquiry.bean;

public class ConMsBankBranchBean {

    private Integer bankBranchId;
    private Integer bankId;
    private String bankBranchCode;
    private String bankBranchIdentifier;
    private String bankBranchName;

    public Integer getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(Integer bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getBankBranchIdentifier() {
        return bankBranchIdentifier;
    }

    public void setBankBranchIdentifier(String bankBranchIdentifier) {
        this.bankBranchIdentifier = bankBranchIdentifier;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }
}
