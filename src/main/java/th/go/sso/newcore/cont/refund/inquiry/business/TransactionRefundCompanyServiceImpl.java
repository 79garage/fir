package th.go.sso.newcore.cont.refund.inquiry.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.CalculateUtils;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.WebServiceUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.CompanyRateBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsDepartmentBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.EmployeeRateBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverPaidBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RateInformationBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsDepartmentDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbContributionCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundCompanyPeriodDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.SelectedEmployeeExistsDaoImpl;

@Service
public class TransactionRefundCompanyServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(TransactionRefundCompanyServiceImpl.class);
    private BigDecimal zero = BigDecimal.ZERO;

    @Autowired
    private RefundCompanyDaoImpl refundCompanyDao;

    @Autowired
    private RefundCompanyPeriodDaoImpl refundCompanyPeriodDao;

    @Autowired
    private RefundEmployeeDaoImpl refundEmployeeDao;

    @Autowired
    private RefundReceiptDaoImpl refundReceiptDao;

    @Autowired
    private ConMsDepartmentDaoImpl conMsDepartmentDao;

    @Autowired
    private SelectedEmployeeExistsDaoImpl selectedEmployeeExistsDao;

    @Autowired
    private ConTbContributionCompanyDaoImpl conTbContributionCompanyDao;

    @Autowired
    private CalRateServiceImpl calRateService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RefundCompanyRequestBean searchCompanyDetail(Long companyId) throws Exception {
        RefundCompanyRequestBean companyDetail = refundCompanyDao.searchRefundCompanyInfo(companyId);
        if (ObjectUtils.isObjectNull(companyDetail)) {
            throw new BusinessException("ไม่พบข้อมูลสถานประกอบการ");
        }
        if (!StringUtils.isEmpty(companyDetail.getSsoBranchCode())) {
            ConMsDepartmentBean department = conMsDepartmentDao.findDepartment(companyDetail.getSsoBranchCode());
            companyDetail.setDepartmentResponsibleCode(department.getDepCode());
        }
        companyDetail.setSection("0");
        return companyDetail;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundCompanyPeriodBean> searchCompanyRefundPeriods(Long companyId, List<Long> receiveCompanyId, String mode, RefundCompanyRequestBean refundDetail, String accessToken) throws Exception {
        List<OverPaidBean> sumContributions = conTbContributionCompanyDao.findEmpeOver(receiveCompanyId);
        if (CollectionUtils.isEmpty(sumContributions)) {
            throw new BusinessException("ไม่พบรายการทำการขอคืนเงิน");
        }

        List<OverPaidBean> overPaidPart1Part2 = sumContributions.stream().filter(con -> con.getEmpeOver().compareTo(zero) > 0).collect(Collectors.toList());
        List<OverPaidBean> notExistsOverPaidPart2 = sumContributions.stream().filter(con -> con.getEmpeOver().compareTo(zero) == 0).collect(Collectors.toList());

        List<RefundCompanyPeriodBean> periods = new ArrayList<>();
        if (!CollectionUtils.isEmpty(overPaidPart1Part2)) {
            log.info("overPaidPart1Part2 : {}", WebServiceUtils.toJson(overPaidPart1Part2));
            List<Long> receiveCompanyIds = overPaidPart1Part2.stream().map(OverPaidBean::getReceiveCompanyId).collect(Collectors.toList());
            List<RefundCompanyPeriodBean> receivePeriods = refundCompanyPeriodDao.findReceiveCompanyPeriod(receiveCompanyIds , companyId);
            log.info("overPaidPart1Part2 result : {}", WebServiceUtils.toJson(receivePeriods));
            periods.addAll(receivePeriods);
        }
        if (!CollectionUtils.isEmpty(notExistsOverPaidPart2)) {
            log.info("notExistsOverPaidPart2 : {}", WebServiceUtils.toJson(notExistsOverPaidPart2));
            List<Long> receiveCompanyIds = notExistsOverPaidPart2.stream().map(OverPaidBean::getReceiveCompanyId).collect(Collectors.toList());
            List<RefundCompanyPeriodBean> receivePeriods = refundCompanyPeriodDao.findReceiveCompanyPeriodOverPart1(receiveCompanyIds);
            log.info("notExistsOverPaidPart2 result : {}", WebServiceUtils.toJson(receivePeriods));
            periods.addAll(receivePeriods);
        }

//        List<RefundCompanyPeriodBean> periods = refundCompanyPeriodDao.findReceiveCompanyPeriod(receiveCompanyId , companyId);

        BigDecimal emprTotal = BigDecimal.ZERO;
        BigDecimal emprInterestTotal = BigDecimal.ZERO;
        BigDecimal empeTotal = BigDecimal.ZERO;
        for (RefundCompanyPeriodBean period : periods) {
            log.info("RequestEmprAmount: {}, RequestInterestAmount : {}, RequestEmpeAmount : {}", period.getRequestEmprAmount(), period.getRequestInterestAmount(), period.getRequestEmpeAmount());
            period.setRequestTotalAmount(CalculateUtils.sum(List.of(NumberUtils.convertZeroIfNull(period.getRequestEmprAmount())
                    , NumberUtils.convertZeroIfNull(period.getRequestInterestAmount())
                    , NumberUtils.convertZeroIfNull(period.getRequestEmpeAmount()))));

            emprTotal = emprTotal.add(NumberUtils.convertZeroIfNull(period.getRequestEmprAmount()));
            emprInterestTotal =  emprInterestTotal.add(period.getRequestInterestAmount());
            empeTotal = empeTotal.add(NumberUtils.convertZeroIfNull(period.getRequestEmpeAmount()));

            List<RefundEmployeeBean> receiveEmployeeList = findReceiveEmployee(period.getReceiveCompanyId(), mode);
            log.debug("Query ReceiveEmployeeList Success");
            period.setRefundEmployeeList(receiveEmployeeList);

            List<SelectedEmployeeBean> selectedEmployeeList  = selectedEmployeeExistsDao.findSelectedEmployee(period.getRefundPeriodId());
            log.debug("Query SelectedEmployeeList Success");
            period.setSelectedEmployeeList(selectedEmployeeList);

            List<RefundReceiptBean> receiptList = refundReceiptDao.findReceipt(period.getReceiveCompanyId());
//            List<RefundReceiptBean> receiptList = refundReceiptDao.searchRefundReceipt(period.getReceiveCompanyId());
            log.debug("Query RefundReceiptList Success");
            period.setReceiptList(receiptList);

//            List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.searchReceiptExists(period.getReceiveCompanyId());
//            log.debug("Query SelectedReceiptList Success");
//            period.setSelectedReceiptList(selectedReceiptList);
            period.setSelectedReceiptList(new ArrayList<>());

//            RateInformationBean companyRate = findRateCompany(companyId, period.getPayPeriod(), accessToken);
//            RateInformationBean employeeRate = findRateEmployee(companyId, period.getPayPeriod(), accessToken);
//            //map rate period company
//            mapRate(period, companyRate);
////            mapRate(period, employeeRate);
//            CompanyRateBean companyRate1 = new CompanyRateBean();
//            EmployeeRateBean employeeRate1 = new EmployeeRateBean();
//            ObjectUtils.copyIgnoreNull(companyRate1, companyRate);
//            ObjectUtils.copyIgnoreNull(employeeRate1, employeeRate);
//
//            period.setCompanyRate(companyRate1);
//            period.setEmployeeRate(employeeRate1);
        }
        mapRefundDetail(refundDetail, emprTotal, emprInterestTotal, empeTotal);

        return periods;
    }

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

    private void mapRate(RefundCompanyPeriodBean receiveData, RateInformationBean rate) {
        receiveData.setRefundSicknessRate(rate.getSickness());
        receiveData.setRefundRetirementRate(rate.getRetirement());
        receiveData.setRefundUnemploymentRate(rate.getUnemployment());
        receiveData.setRefundMaternityRate(rate.getMaternity());
        receiveData.setRefundInvalidityRate(rate.getInvalidity());
        receiveData.setRefundChildAllowanceRate(rate.getChildAllowance());
        receiveData.setRefundDeathRate(rate.getDeath());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundEmployeeBean> findReceiveEmployee(Long receiveCompanyId, String mode) throws Exception {
        List<RefundEmployeeBean> employeeList = List.of("over", "Over").contains(mode) ? refundEmployeeDao.searchRefundEmployeeOver(receiveCompanyId)
                : List.of("cntr", "Cntr").contains(mode) ? refundEmployeeDao.searchRefundEmployee(receiveCompanyId)
                : new ArrayList<>();
        employeeList.forEach(employee -> {
//            if (List.of("R", "D").contains(employee.getInsurerStatus()) && !StringUtils.isEmpty(employee.getInsurerStatus())) {
//                employee.setEmpeOverp(employee.getCntrAmont());
//            }
            if (List.of("01", "02", "07", "09", "10", "11").contains(employee.getRefundReasonCode()) && !StringUtils.isEmpty(employee.getRefundReasonCode())) {
                employee.setEmpeOverp(employee.getCntrAmount());
            }
        });
        return employeeList;
    }

    private void mapRefundDetail(RefundCompanyRequestBean refundDetail, BigDecimal emprTotal, BigDecimal emprInterestTotal, BigDecimal empeTotal) {
        refundDetail.setRequestEmprAmount(emprTotal);
        refundDetail.setRequestInterestAmount(emprInterestTotal);
        refundDetail.setRequestEmpeAmount(empeTotal);
        refundDetail.setRequestTotalEmprAmount(emprTotal.add(emprInterestTotal));
        refundDetail.setRequestTotalEmpeAmount(empeTotal);
        refundDetail.setRequestTotalAmount(refundDetail.getRequestTotalEmprAmount().add(refundDetail.getRequestTotalEmpeAmount()));
    }

}

