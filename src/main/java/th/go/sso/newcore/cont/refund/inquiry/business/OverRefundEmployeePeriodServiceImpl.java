package th.go.sso.newcore.cont.refund.inquiry.business;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.CalculateUtils;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.DateTimeUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBranchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RateInformationBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiptInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsBankDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester33DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTrReceiveEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTrRefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.OverRefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedRefundRequestReceiptDaoImpl;

@Service
public class OverRefundEmployeePeriodServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(OverRefundEmployeePeriodServiceImpl.class);

    @Autowired
    private TimerHelper timer;

    @Autowired
    private OverRefundEmployeeDaoImpl overRefundEmployeeDao;

    @Autowired
    private RefundInsurerSelectedReceiptDaoImpl refundInsurerSelectedReceiptDao;

    @Autowired
    private RefundInsurerSelectedRefundRequestReceiptDaoImpl refundInsurerSelectedRefundRequestReceiptDao;

    @Autowired
    private ConMsBankDaoImpl conMsBankDao;

    @Autowired
    private ConTbRequester33DaoImpl conTbRequester33Dao;

    @Autowired
    private ConTrRefundEmployeeDaoImpl conTrRefundEmployeeDao;


    @Autowired
    private CalRateServiceImpl calRateService;

    @Autowired
    private ConTrReceiveEmployeeDaoImpl conTrReceiveEmployeeDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RefundInsurerBean searchRefundDetails(Long refundRequestId) throws Exception {
        timer.start("searchRefundDetails");
        RefundInsurerBean refundInsurer = overRefundEmployeeDao.searchRefundEmpDetails(refundRequestId);

        Long bankId = refundInsurer.getRefundBankId();
        Long bankBranchId = refundInsurer.getRefundBankBranchId();
        if (!NumberUtils.isZeroOrNull(bankId)) {
            ConMsBankBean bank = conMsBankDao.searchBank(bankId);
            refundInsurer.setRefundBankCode(bank.getBankCode());
            log.debug("searchBank");
        }
        if (!NumberUtils.isZeroOrNull(bankBranchId)) {
            ConMsBankBranchBean bankBranch = conMsBankDao.searchBankBranch(bankBranchId);
            refundInsurer.setRefundBankBranchCode(bankBranch.getBankBranchCode());
            log.debug("searchBankBranch");
        }

        List<ConTbRequesterBean> requester33EligibleList = conTbRequester33Dao.queryConTbRequester33(refundRequestId);
        requester33EligibleList.forEach(list -> list.setRefundPromptPay(list.getPromptPay()));
        refundInsurer.setRequesterEligibleList(requester33EligibleList);

        List<ConTbRequesterBean> requester33List = conTbRequester33Dao.queryRequester33InRequesterList(refundRequestId);
        requester33List.forEach(list -> list.setRefundPromptPay(list.getPromptPay()));
        refundInsurer.setRequesterList(requester33List);
        log.debug("query requesterList33");

        if (!CollectionUtils.isEmpty(requester33EligibleList)) {
            String checkProgressStatus = conTrRefundEmployeeDao.findProgressStatusByRefundRequestId(refundRequestId);
            if ("P".equals(checkProgressStatus)){
                refundInsurer.setOrderStatus(checkProgressStatus);
            }else {
                for (ConTbRequesterBean requester33 : requester33EligibleList) {
                    if ("P".equals(requester33.getOrderStatus())) {
                        refundInsurer.setOrderStatus(requester33.getOrderStatus());
                        refundInsurer.setOrderRemark(requester33.getOrderRemark());
                    } else if (!"P".equals(requester33.getOrderStatus()) && !"P".equals(refundInsurer.getOrderStatus())) {
                        refundInsurer.setOrderStatus(requester33.getOrderStatus());
                        refundInsurer.setOrderRemark(requester33.getOrderRemark());
                    }
                }
            }
        }
        timer.endToString("searchRefundDetails");
        return refundInsurer;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OverRefundEmployeeBean> searchRefundPeriods(Long refundRequestId, RefundInsurerBean employeeDetail, String accessToken) throws Exception {
        timer.start("searchRefundPeriods");
        List<OverRefundEmployeeBean> periodList = overRefundEmployeeDao.findRefundMultiCompanyPeriods(refundRequestId);

        // sorted
        periodList.sort((p1, p2) -> {
            try {
                Date date1 = DateTimeUtils.parseThaiDateToDate("01/" + p1.getPayPeriod());
                Date date2 = DateTimeUtils.parseThaiDateToDate("01/" + p2.getPayPeriod());
                return date2.compareTo(date1); // Descending order
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        for (OverRefundEmployeeBean periodData : periodList) {
            Date paymentDate = conTrReceiveEmployeeDao.findPaymentDate(periodData.getInsurerId(), periodData.getPayPeriod());

            List<OverRefundEmployeeBean> receiptId = new ArrayList<>();
            receiptId = overRefundEmployeeDao.searchRefundEmpPeriodReceipt(periodData.getIdCardNo(), periodData.getPayPeriod());
            String[] receiptIds = receiptId.get(0).getReceiptIds().split(",");
            List<RefundReceiptInsurerBean> receiptList = refundInsurerSelectedReceiptDao.findReceiptReceiveEmployee(List.of(receiptIds));
            List<SelectedReceiptBean> refundRequestReceiptList = refundInsurerSelectedRefundRequestReceiptDao.findReceiptRefundRequestReceipt(List.of(receiptIds));
//            List<OverRefundEmployeeCompanyBean> companyList = overRefundEmployeeDao.findCompanyDetailsMultipleCompanies(periodData.getIdCardNo(), periodData.getPayPeriod());
            List<OverRefundEmployeeCompanyBean> companyList = overRefundEmployeeDao.findCompanyDetailsMultipleCompaniesExistsRefundRequest(periodData.getRefundPeriodId());

            Long companyId = companyList.stream()
                    .max(Comparator.comparing(OverRefundEmployeeCompanyBean::getCntrRate))
                    .map(OverRefundEmployeeCompanyBean::getCompanyId)
                    .get();
            if (NumberUtils.isZeroOrNull(periodData.getRefundSicknessRate()) && NumberUtils.isZeroOrNull(periodData.getRefundRetirementRate()) && NumberUtils.isZeroOrNull(periodData.getRefundUnemploymentRate())) {
                RateInformationBean rate = findRate(companyId, periodData.getPayPeriod(), accessToken);
                mapRate(periodData, rate);
            }
            periodData.setRefundSumRate(CalculateUtils.sum(List.of(NumberUtils.convertZeroIfNull(periodData.getRefundDeathRate())
                    , NumberUtils.convertZeroIfNull(periodData.getRefundInvalidityRate())
                    , NumberUtils.convertZeroIfNull(periodData.getRefundMaternityRate())
                    , NumberUtils.convertZeroIfNull(periodData.getRefundRetirementRate())
                    , NumberUtils.convertZeroIfNull(periodData.getRefundSicknessRate())
                    , NumberUtils.convertZeroIfNull(periodData.getRefundChildAllowanceRate())
                    , NumberUtils.convertZeroIfNull(periodData.getRefundUnemploymentRate()))));

            periodData.setPaymentDate(paymentDate);
            periodData.setCountCompany(companyList.size());
            periodData.setReceiptList(receiptList);
            periodData.setSelectedReceiptList(refundRequestReceiptList);
            periodData.setCompanyDetails(companyList);
        }
        timer.endToString("searchRefundPeriods");
        return periodList;
    }

    private RateInformationBean findRate(Long companyId, String payPeriod, String accessToken) throws Exception {
        return calRateService.getRateEmployee(companyId
                , payPeriod
                , null
                , accessToken);
    }

    private void mapRate(OverRefundEmployeeBean receiveData, RateInformationBean rate) {
        receiveData.setRefundSicknessRate(rate.getSickness());
        receiveData.setRefundRetirementRate(rate.getRetirement());
        receiveData.setRefundUnemploymentRate(rate.getUnemployment());
        receiveData.setRefundMaternityRate(rate.getMaternity());
        receiveData.setRefundInvalidityRate(rate.getInvalidity());
        receiveData.setRefundChildAllowanceRate(rate.getChildAllowance());
        receiveData.setRefundDeathRate(rate.getDeath());
    }

//    private BigDecimal calSeparate(BigDecimal rate, BigDecimal sumRate, BigDecimal requestTotalAmount) {
//        return (rate
//                .divide(sumRate, 5, BigDecimal.ROUND_HALF_UP)
//                .multiply(requestTotalAmount)).setScale(2,BigDecimal.ROUND_HALF_UP);
//    }
}
