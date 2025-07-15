package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;

public class CompanyRateBean {

    private BigDecimal sickness;
    private BigDecimal maternity;
    private BigDecimal invalidity;
    private BigDecimal death;
    private BigDecimal retirement;
    private BigDecimal childAllowance;
    private BigDecimal unemployment;

    public BigDecimal getSickness() {
        return sickness;
    }

    public void setSickness(BigDecimal sickness) {
        this.sickness = sickness;
    }

    public BigDecimal getMaternity() {
        return maternity;
    }

    public void setMaternity(BigDecimal maternity) {
        this.maternity = maternity;
    }

    public BigDecimal getInvalidity() {
        return invalidity;
    }

    public void setInvalidity(BigDecimal invalidity) {
        this.invalidity = invalidity;
    }

    public BigDecimal getDeath() {
        return death;
    }

    public void setDeath(BigDecimal death) {
        this.death = death;
    }

    public BigDecimal getRetirement() {
        return retirement;
    }

    public void setRetirement(BigDecimal retirement) {
        this.retirement = retirement;
    }

    public BigDecimal getChildAllowance() {
        return childAllowance;
    }

    public void setChildAllowance(BigDecimal childAllowance) {
        this.childAllowance = childAllowance;
    }

    public BigDecimal getUnemployment() {
        return unemployment;
    }

    public void setUnemployment(BigDecimal unemployment) {
        this.unemployment = unemployment;
    }
}
