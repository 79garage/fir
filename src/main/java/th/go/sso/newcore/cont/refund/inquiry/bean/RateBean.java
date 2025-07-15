package th.go.sso.newcore.cont.refund.inquiry.bean;

public class RateBean {
    private RateInformationBean companyRate;
    private RateInformationBean employeeRate;
    private RateInformationBean insurerRate;
    private WageCalculatedBean wageCalculate;
    private RateInformationBean governmentRate;

    public RateInformationBean getCompanyRate() {
        return companyRate;
    }

    public void setCompanyRate(RateInformationBean companyRate) {
        this.companyRate = companyRate;
    }

    public RateInformationBean getEmployeeRate() {
        return employeeRate;
    }

    public void setEmployeeRate(RateInformationBean employeeRate) {
        this.employeeRate = employeeRate;
    }

    public RateInformationBean getInsurerRate() {
        return insurerRate;
    }

    public void setInsurerRate(RateInformationBean insurerRate) {
        this.insurerRate = insurerRate;
    }

    public WageCalculatedBean getWageCalculate() {
        return wageCalculate;
    }

    public void setWageCalculate(WageCalculatedBean wageCalculate) {
        this.wageCalculate = wageCalculate;
    }

    public RateInformationBean getGovernmentRate() {
        return governmentRate;
    }

    public void setGovernmentRate(RateInformationBean governmentRate) {
        this.governmentRate = governmentRate;
    }
}
