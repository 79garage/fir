package th.go.sso.newcore.cont.refund.inquiry.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.sso.newcore.cont.common.utils.CalculateUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RateBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RateInformationBean;
import th.go.sso.newcore.cont.refund.inquiry.ws.requestor.impl.SearchRateImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.requestor.request.SearchCompanyRateRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.requestor.request.SearchInsurerRateRequest;

@Service
public class CalRateServiceImpl {

    @Autowired
    private SearchRateImpl searchRate;

    public RateInformationBean getRateInsurer(Long insurerId, String payPeriod, BigDecimal requestTotalAmount, String accessToken) throws Exception {
        SearchInsurerRateRequest masterRateRequest = new SearchInsurerRateRequest();
        masterRateRequest.setInsurerId(insurerId);
        masterRateRequest.setPeriod(payPeriod);
        RateBean rate = searchRate.submitRateInsurer(masterRateRequest, accessToken);
        RateInformationBean insurerRate = new RateInformationBean();
        insurerRate = rate.getInsurerRate();
        RateInformationBean rateCalAmount = new RateInformationBean();
        ObjectUtils.copyIgnoreNull(rateCalAmount, insurerRate);
        if (requestTotalAmount != null) {
            rateCalAmount = calculateSeparateAmount(insurerRate, requestTotalAmount);
        }
        return rateCalAmount;
//        return rate.getInsurerRate();
    }

    public RateInformationBean getRateCompany(Long companyId, String payPeriod, BigDecimal requestTotalAmount, String accessToken) throws Exception {
        SearchCompanyRateRequest masterRateRequest = new SearchCompanyRateRequest();
        masterRateRequest.setCompanyId(companyId);
        masterRateRequest.setPeriod(payPeriod);
        RateBean rate = searchRate.submitRateCompany(masterRateRequest, accessToken);
        RateInformationBean companyRate = new RateInformationBean();
        companyRate = rate.getCompanyRate();
        RateInformationBean rateCalAmount = new RateInformationBean();
        ObjectUtils.copyIgnoreNull(rateCalAmount, companyRate);
        if (requestTotalAmount != null) {
            rateCalAmount = calculateSeparateAmount(companyRate, requestTotalAmount);
        }
        return rateCalAmount;
//        return rate.getCompanyRate();
    }

    public RateInformationBean getRateEmployee(Long companyId, String payPeriod, BigDecimal requestTotalAmount, String accessToken) throws Exception {
        SearchCompanyRateRequest masterRateRequest = new SearchCompanyRateRequest();
        masterRateRequest.setCompanyId(companyId);
        masterRateRequest.setPeriod(payPeriod);
        RateBean rate = searchRate.submitRateCompany(masterRateRequest, accessToken);
        RateInformationBean employeeRate = new RateInformationBean();
        employeeRate = rate.getEmployeeRate();
        RateInformationBean rateCalAmount = new RateInformationBean();
        ObjectUtils.copyIgnoreNull(rateCalAmount, employeeRate);
        if (requestTotalAmount != null) {
            rateCalAmount = calculateSeparateAmount(employeeRate, requestTotalAmount);
        }
        return rateCalAmount;
//        return rate.getEmployeeRate();
    }

    private RateInformationBean calculateSeparateAmount(RateInformationBean rate, BigDecimal requestTotalAmount) {
        BigDecimal sicknessRate = BigDecimal.ZERO;
        BigDecimal retirementRate = BigDecimal.ZERO;
        BigDecimal unemploymentRate = BigDecimal.ZERO;
//        BigDecimal sumRate = BigDecimal.ZERO;
//        sumRate = CalculateUtils.sum(List.of(rate.getRetirement()
//                , NumberUtils.convertZeroIfNull(rate.getUnemployment())
//                , rate.getSickness()
//                , rate.getMaternity()
//                , rate.getInvalidity()
//                , rate.getDeath()
//                , rate.getChildAllowance()));
        sicknessRate = CalculateUtils.sum(List.of(rate.getSickness()
                , rate.getMaternity()
                , rate.getInvalidity()
                , rate.getDeath()));
        retirementRate = CalculateUtils.sum(List.of(rate.getRetirement()
                , rate.getChildAllowance()));
        unemploymentRate = NumberUtils.convertZeroIfNull(rate.getUnemployment());

        RateInformationBean rateCalAmount = new RateInformationBean();
        rateCalAmount.setSicknessAmount(calSeparate(sicknessRate, rate.getSumRate(), requestTotalAmount));
        rateCalAmount.setRetirementAmount(calSeparate(retirementRate, rate.getSumRate(), requestTotalAmount));
        rateCalAmount.setUnemploymentAmount(calSeparate(unemploymentRate, rate.getSumRate(), requestTotalAmount));
        rateCalAmount.setSickness(sicknessRate);
        rateCalAmount.setRetirement(retirementRate);
        rateCalAmount.setUnemployment(unemploymentRate);
        return rateCalAmount;
    }

    private BigDecimal calSeparate(BigDecimal rate, BigDecimal sumRate, BigDecimal requestTotalAmount) {
        return (rate
                .divide(sumRate, 5, RoundingMode.HALF_UP)
                .multiply(requestTotalAmount)).setScale(2,RoundingMode.HALF_UP);
    }
}
