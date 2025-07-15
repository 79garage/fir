package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import th.go.sso.newcore.cont.common.utils.CalculateUtils;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.WebServiceUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBranchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RateInformationBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsBankDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRefundRequestDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester33DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequesterCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTrRefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundCompanyPeriodDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedRefundRequestReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.SelectedEmployeeExistsDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.SplitEmployeeRefundDaoImpl;


@Service
public class TransactionRefundCompanyByIdServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(TransactionRefundCompanyByIdServiceImpl.class);

    @Autowired
    private RefundCompanyDaoImpl refundCompanyDao;
    @Autowired
    private RefundCompanyPeriodDaoImpl refundCompanyPeriodDao;
    @Autowired
    private RefundEmployeeDaoImpl refundEmployeeDao;
    @Autowired
    private RefundReceiptDaoImpl refundReceiptDao;
    @Autowired
    private ConMsBankDaoImpl conMsBankDao;
    @Autowired
    private SelectedEmployeeExistsDaoImpl selectedEmployeeExistsDao;
    @Autowired
    private SplitEmployeeRefundDaoImpl splitEmployeeRefundDao;
    @Autowired
    private ConTbRefundRequestDaoImpl conTbRefundRequestDao;
    @Autowired
    private ConTbRequester33DaoImpl conTbRequester33Dao;
    @Autowired
    private ConTrRefundEmployeeDaoImpl conTrRefundEmployeeDaoImpl;
    @Autowired
    private ConTbRequesterCompanyDaoImpl conTbRequesterCompanyDao;
    @Autowired
    private CalRateServiceImpl calRateService;
    @Autowired
    private RefundInsurerSelectedRefundRequestReceiptDaoImpl selectedRefundReceiptDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RefundCompanyRequestBean checkRefundCompany(Long companyId, Long refundRequestId) throws Exception {
        return refundCompanyDao.searchRefundCompanyChange(companyId, refundRequestId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public RefundCompanyRequestBean searchRefundChange(RefundCompanyRequestBean refundCompany) throws Exception{

        refundCompany.setRequestTotalEmprAmount(NumberUtils.convertZeroIfNull(refundCompany.getRequestEmprAmount()).add(NumberUtils.convertZeroIfNull(refundCompany.getRequestInterestAmount())));
        refundCompany.setRequestTotalEmpeAmount(refundCompany.getRefundTotalEmpeAmount());
        refundCompany.setRefundTotalEmprAmount(NumberUtils.convertZeroIfNull(refundCompany.getRefundEmprAmount()).add(NumberUtils.convertZeroIfNull(refundCompany.getRefundInterestAmount())));
        refundCompany.setRefundTotalEmpeAmount(refundCompany.getRefundEmpeAmount());

//        if (!NumberUtils.isZeroOrNull(refundCompany.getDepartmentResponsibleId())) {
//            ConMsDepartmentBean department = conMsDepartmentDao.searchDepName(refundCompany.getDepartmentResponsibleId());
////            refundCompany.setSsoBranchCode(department.getDepCode());
//            log.info("Set SsoBranchCode Success");
//        }

        refundCompany.setDepartmentReceiveCode(refundCompany.getDepartmentReceiveCode());
//        if (!NumberUtils.isZeroOrNull(refundCompany.getDepartmentReceiveId())) {
//            ConMsDepartmentBean department = conMsDepartmentDao.searchDepName(refundCompany.getDepartmentReceiveId());
//            refundCompany.setDepartmentReceiveCode(department.getDepCode());
//            log.info("Set DepartmentReceiveCode Success");
//        }

        if (!NumberUtils.isZeroOrNull( refundCompany.getRefundBankId())) {
            ConMsBankBean bank = conMsBankDao.searchBank( refundCompany.getRefundBankId());
            if (!ObjectUtils.isObjectNull(bank)) {
                refundCompany.setRefundBankCode(bank.getBankCode());
            }
            log.info("Set RefundBankCode Success");
        }

        if (!NumberUtils.isZeroOrNull(refundCompany.getRefundBankBranchId())) {
            ConMsBankBranchBean bankBranch = conMsBankDao.searchBankBranch(refundCompany.getRefundBankBranchId());
            if (!ObjectUtils.isObjectNull(bankBranch)) {
                refundCompany.setRefundBankBranchCode(bankBranch.getBankBranchCode());
            }
            log.info("Set RefundBankBranchCode Success");
        }
        Long refundRequestId = refundCompany.getRefundRequestId();

        List<ConTbRequesterBean> requester33EligibleList = conTbRequester33Dao.queryConTbRequester33(refundRequestId);
        requester33EligibleList.forEach(list -> list.setRefundPromptPay(list.getPromptPay()));
        refundCompany.setRequesterEligibleList(requester33EligibleList);

        List<ConTbRequesterBean> requester33List = conTbRequester33Dao.queryRequester33InRequesterList(refundRequestId);
        requester33List.forEach(list -> list.setRefundPromptPay(list.getPromptPay()));
        refundCompany.setRequesterList(requester33List);
        log.info("query requesterList33");

//        if (!CollectionUtils.isEmpty(requesterList33)) {
//            String checkProgressStatus = conTrRefundEmployeeDaoImpl.findProgressStatusByRefundRequestId(refundRequestId);
//            if ("P".equals(checkProgressStatus)){
//                refundCompany.setOrderStatus(checkProgressStatus);
//            }else {
//                for (ConTbRequesterBean requester33 : requesterList33) {
//                    if ("P".equals(requester33.getOrderStatus())) {
//                        refundCompany.setOrderStatus(requester33.getOrderStatus());
//                        refundCompany.setOrderRemark(requester33.getOrderRemark());
//                    } else if (!"P".equals(requester33.getOrderStatus()) && !"P".equals(refundCompany.getOrderStatus())) {
//                        refundCompany.setOrderStatus(requester33.getOrderStatus());
//                        refundCompany.setOrderRemark(requester33.getOrderRemark());
//                    }
//                }
//            }
//        }

        List<ConTbRequesterCompanyBean> requesterCompanyList = conTbRequesterCompanyDao.queryConTbRequesterCompany(refundRequestId);
        if (!CollectionUtils.isEmpty(requesterCompanyList)) {
            String checkProgressStatus = conTrRefundEmployeeDaoImpl.findProgressStatusByRefundRequestId(refundRequestId);
            if ("P".equals(checkProgressStatus)){
                refundCompany.setOrderStatus(checkProgressStatus);
            }else {
                for (ConTbRequesterCompanyBean requesterCompany : requesterCompanyList) {
                    if ("P".equals(requesterCompany.getOrderStatus())) {
                        refundCompany.setOrderStatus(requesterCompany.getOrderStatus());
                        refundCompany.setOrderRemark(requesterCompany.getOrderRemark());
                    } else if (!"P".equals(requesterCompany.getOrderStatus()) && !"P".equals(refundCompany.getOrderStatus())) {
                        refundCompany.setOrderStatus(requesterCompany.getOrderStatus());
                        refundCompany.setOrderRemark(requesterCompany.getOrderRemark());
                    }
                }
            }
        }
        refundCompany.setEligible(!StringUtils.isEmpty(refundCompany.getEligibleFlag()) && Objects.equals("T", refundCompany.getEligibleFlag())
                ? Boolean.TRUE : Boolean.FALSE);
        return refundCompany;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundCompanyPeriodBean> searchRefundPeriodChange(Long companyId, Long refundRequestId, String mode, RefundCompanyRequestBean companyDetails, String accessToken) throws Exception {

        List<RefundCompanyPeriodBean> periods = refundCompanyPeriodDao.searchRefundRequestId(refundRequestId, companyId);
        log.info("#################### periods : {}", periods.size());

        for (RefundCompanyPeriodBean period : periods) {

            List<RefundEmployeeBean> refundEmployeeList = searchEmployee(period.getReceiveCompanyId(), mode);
            log.info("Query RefundEmployeeList Success");
            period.setRefundEmployeeList(refundEmployeeList);

            List<SelectedEmployeeBean> selectedEmployeeList = selectedEmployeeExistsDao.findSelectedEmployee(period.getRefundPeriodId());
            log.info("Query SelectedEmployeeList Success");
            period.setSelectedEmployeeList(selectedEmployeeList);

            List<RefundReceiptBean> receiptList = refundReceiptDao.findReceipt(period.getReceiveCompanyId());
//            List<RefundReceiptBean> receiptList = refundReceiptDao.searchRefundReceipt(period.getReceiveCompanyId());
            log.info("Query RefundReceiptList Success");
            period.setReceiptList(receiptList);

            List<SelectedReceiptBean> selectedReceiptList = selectedRefundReceiptDao.findRefundRequestReceipt(period.getRefundPeriodId());
//            List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.searchReceiptExists(period.getReceiveCompanyId());
            log.info("Query SelectedReceiptList Success");
            period.setSelectedReceiptList(selectedReceiptList);

            // find rate employee and map rate period employee
            RateInformationBean employeeRate = findRateEmployee(companyId, period.getPayPeriod(), accessToken);
            mapEmployeeRate(period, employeeRate);

            if (NumberUtils.isZeroOrNull(period.getRefundSicknessRate())
                    && NumberUtils.isZeroOrNull(period.getRefundRetirementRate())
                    && NumberUtils.isZeroOrNull(period.getRefundUnemploymentRate())) {

                // find rate employee and map rate period company
                RateInformationBean companyRate = findRateCompany(companyId, period.getPayPeriod(), accessToken);
                mapCompanyRate(period, companyRate);

//                CompanyRateBean companyRate1 = new CompanyRateBean();
//                EmployeeRateBean employeeRate1 = new EmployeeRateBean();
//                ObjectUtils.copyIgnoreNull(companyRate1, companyRate);
//                ObjectUtils.copyIgnoreNull(employeeRate1, employeeRate);
//
//                period.setCompanyRate(companyRate1);
//                period.setEmployeeRate(employeeRate1);
            }
            period.setRefundSumRate(CalculateUtils.sum(List.of(NumberUtils.convertZeroIfNull(period.getRefundDeathRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundInvalidityRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundMaternityRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundRetirementRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundSicknessRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundChildAllowanceRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundUnemploymentRate()))));

            period.setEmpeSumRate(CalculateUtils.sum(List.of(NumberUtils.convertZeroIfNull(period.getEmpeSicknessRate())
                    , NumberUtils.convertZeroIfNull(period.getEmpeRetirementRate())
                    , NumberUtils.convertZeroIfNull(period.getEmpeUnemploymentRate()))));
        }
        return periods;
    }

//    @Transactional(propagation = Propagation.SUPPORTS)
//    public List<RefundEmployeeBean> searchEmployee(Long receiveCompanyId,  String mode, Long refundPeriodId) throws Exception {
//        List<RefundEmployeeBean> refundEmployeeList = new ArrayList<RefundEmployeeBean>();
//        if (mode.equals("over") || mode.equals("Over")) {
////            refundEmployeeList = refundEmployeeDao.searchOverRefundEmployee(refundPeriodId);
//            refundEmployeeList = refundEmployeeDao.searchRefundEmployeeOver(receiveCompanyId);
//        }
//        if (mode.equals("cntr") || mode.equals("Cntr")) {
//            refundEmployeeList = refundEmployeeDao.searchRefundEmployee(receiveCompanyId);
//        }
//        return refundEmployeeList;
//    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundEmployeeBean> searchEmployee(Long receiveCompanyId, String mode) throws Exception {
        List<RefundEmployeeBean> employeeList = List.of("over", "Over").contains(mode) ? refundEmployeeDao.searchRefundEmployeeOver(receiveCompanyId)
                : List.of("cntr", "Cntr").contains(mode) ? refundEmployeeDao.searchRefundEmployee(receiveCompanyId)
                : new ArrayList<>();

        log.info("employeeList : {}", WebServiceUtils.toJson(employeeList));
        if (!CollectionUtils.isEmpty(employeeList)) {
            employeeList.forEach(employee -> {
                try {
                    log.info("employeeList : {}", WebServiceUtils.toJson(employee));
                    if (!StringUtils.isEmpty(employee.getInsurerStatus()) && List.of("R", "D").contains(employee.getInsurerStatus())) {
                        employee.setEmpeOverp(employee.getCntrAmount());
                    }
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return employeeList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SplitEmployeeRefundCompanyPeriodBean> searchSplitEmployeeRefundPeriod(Long refundRequestId) throws Exception {

        List<SplitEmployeeRefundBean> splitEmployeeRefundList = conTbRefundRequestDao.searchConTbRefundRequestSplitEmployee(refundRequestId);
        List<SplitEmployeeRefundCompanyPeriodBean> periodList = splitEmployeeRefundDao.getSplitEmployeeRefund(refundRequestId,null);
        log.info("searchSplitEmployeePeriod");

        for (SplitEmployeeRefundCompanyPeriodBean employeePeriod :periodList){
            SplitEmployeeRefundBean splitEmployee = splitEmployeeRefundList.stream()
                    .filter(rec -> employeePeriod.getInsurerId().equals(rec.getInsurerId())).findAny().orElse(null);
            log.info("filter InsurerId");
            if (splitEmployee != null){
                log.info("filterSplitEmployee isNotNull");
                employeePeriod.setRefundRequestId(splitEmployee.getRefundRequestId());
                employeePeriod.setRefundEmployeeId(splitEmployee.getRefundEmployeeId());
                employeePeriod.setReceiveNo(splitEmployee.getReceiveNo());
                employeePeriod.setRefundTypePlace(splitEmployee.getRefundTypePlace());
                employeePeriod.setRefundPoOrder(splitEmployee.getRefundPoOrder());
                employeePeriod.setRefundPromptPay(splitEmployee.getRefundPromptPay());
                employeePeriod.setRefundBankId(splitEmployee.getRefundBankId());
                employeePeriod.setRefundBankBranchId(splitEmployee.getRefundBankBranchId());
                employeePeriod.setRefundBankBranchCode(splitEmployee.getRefundBankBranchCode());
                employeePeriod.setRefundBankAccount(splitEmployee.getRefundBankAccount());
                employeePeriod.setDistCode(splitEmployee.getDistCode());
                employeePeriod.setSubDistCode(splitEmployee.getSubDistCode());
                employeePeriod.setProvinceCode(splitEmployee.getProvinceCode());
                log.info("setValue employeePeriod success");
            }
        }
        return periodList;
    }

//    private BigDecimal calSeparate(BigDecimal rate, BigDecimal sumRate, BigDecimal requestTotalAmount) {
//        return (rate
//                .divide(sumRate, 5, BigDecimal.ROUND_HALF_UP)
//                .multiply(requestTotalAmount)).setScale(2,BigDecimal.ROUND_HALF_UP);
//    }
//
//    @Transactional(propagation = Propagation.SUPPORTS)
//    public CompanyRateBean searchCompanyRate(String payPeriod) throws Exception {
//
//        InformationConditionBean informationCondition = new InformationConditionBean();
//        informationCondition.setGroupCode("COMPANY_RATE");
//        informationCondition.setSysCode("M33");
//        informationCondition.setSysDate(payPeriod);
//        CompanyRateBean companyRate = conTbInformationDao.searchCompanyRate(informationCondition);
//
//        return companyRate;
//    }
//
//    @Transactional(propagation = Propagation.SUPPORTS)
//    public EmployeeRateBean searchEmployeeRate(String payPeriod) throws Exception{
//
//        InformationConditionBean informationCondition = new InformationConditionBean();
//        informationCondition.setGroupCode("EMPLOYEE_RATE");
//        informationCondition.setSysCode("M33");
//        informationCondition.setSysDate(payPeriod);
//        EmployeeRateBean employeeRate = conTbInformationDao.searchEmployeeRate(informationCondition);
//
//        return employeeRate;
//    }

    private RateInformationBean findRateCompany(Long companyId, String payPeriod, String accessToken) throws Exception {
        return calRateService.getRateCompany(companyId
                , payPeriod
                , null
                , accessToken);
    }

    private RateInformationBean findRateEmployee(Long companyId, String payPeriod, String accessToken) throws Exception {
        return calRateService.getRateEmployee(companyId
                , payPeriod
                , null
                , accessToken);
    }

    private void mapCompanyRate(RefundCompanyPeriodBean receiveData, RateInformationBean rate) {
        receiveData.setRefundSicknessRate(rate.getSickness());
        receiveData.setRefundRetirementRate(rate.getRetirement());
        receiveData.setRefundUnemploymentRate(rate.getUnemployment());
        receiveData.setRefundMaternityRate(rate.getMaternity());
        receiveData.setRefundInvalidityRate(rate.getInvalidity());
        receiveData.setRefundChildAllowanceRate(rate.getChildAllowance());
        receiveData.setRefundDeathRate(rate.getDeath());
    }

    private void mapEmployeeRate(RefundCompanyPeriodBean receiveData, RateInformationBean rate) {
        receiveData.setEmpeSicknessRate(rate.getSickness());
        receiveData.setEmpeRetirementRate(rate.getRetirement());
        receiveData.setEmpeUnemploymentRate(rate.getUnemployment());
    }
}

