package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

import th.go.sso.newcore.cont.common.utils.CalculateUtils;

public class RateInformationBean {
    private BigDecimal sickness;
    private BigDecimal maternity;
    private BigDecimal invalidity;
    private BigDecimal death;
    private BigDecimal retirement;
    private BigDecimal childAllowance;
    private BigDecimal unemployment;

    private BigDecimal sicknessAmount;
    private BigDecimal maternityAmount;
    private BigDecimal invalidityAmount;
    private BigDecimal deathAmount;
    private BigDecimal retirementAmount;
    private BigDecimal childAllowanceAmount;
    private BigDecimal unemploymentAmount;

    @JsonIgnore
    private String acrossCountry;

    public BigDecimal getSumRate() {
        return CalculateUtils.sum(Arrays.asList(sickness, maternity, invalidity, death, retirement, childAllowance, unemployment));
    }

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

    public BigDecimal getSicknessAmount() {
        return sicknessAmount;
    }

    public void setSicknessAmount(BigDecimal sicknessAmount) {
        this.sicknessAmount = sicknessAmount;
    }

    public BigDecimal getMaternityAmount() {
        return maternityAmount;
    }

    public void setMaternityAmount(BigDecimal maternityAmount) {
        this.maternityAmount = maternityAmount;
    }

    public BigDecimal getInvalidityAmount() {
        return invalidityAmount;
    }

    public void setInvalidityAmount(BigDecimal invalidityAmount) {
        this.invalidityAmount = invalidityAmount;
    }

    public BigDecimal getDeathAmount() {
        return deathAmount;
    }

    public void setDeathAmount(BigDecimal deathAmount) {
        this.deathAmount = deathAmount;
    }

    public BigDecimal getRetirementAmount() {
        return retirementAmount;
    }

    public void setRetirementAmount(BigDecimal retirementAmount) {
        this.retirementAmount = retirementAmount;
    }

    public BigDecimal getChildAllowanceAmount() {
        return childAllowanceAmount;
    }

    public void setChildAllowanceAmount(BigDecimal childAllowanceAmount) {
        this.childAllowanceAmount = childAllowanceAmount;
    }

    public BigDecimal getUnemploymentAmount() {
        return unemploymentAmount;
    }

    public void setUnemploymentAmount(BigDecimal unemploymentAmount) {
        this.unemploymentAmount = unemploymentAmount;
    }

    public String getAcrossCountry() {
        return acrossCountry;
    }

    public void setAcrossCountry(String acrossCountry) {
        this.acrossCountry = acrossCountry;
    }
}
