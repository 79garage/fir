package th.go.sso.newcore.cont.refund.inquiry.business;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.DateTimeUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.WebServiceUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester33DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.OverRefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundMultipleCompaniesDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundEmployeeNoticeRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundEmployeeRequest;

@Service
public class SearchRefundEmployeeMultipleCompaniesServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(SearchRefundEmployeeMultipleCompaniesServiceImpl.class);
    final static private BigDecimal zero = BigDecimal.ZERO;
    final static private BigDecimal maxTotalWages = BigDecimal.valueOf(15000);
    final static private BigDecimal oneHundred = BigDecimal.valueOf(100);

    @Autowired
    private TimerHelper timer;

    @Autowired
    private ConMsInsurerDaoImpl conMsInsurerDao;

    @Autowired
    private RefundMultipleCompaniesDaoImpl refundMultipleCompaniesDao;

    @Autowired
    private OverRefundEmployeeDaoImpl overRefundEmployeeDao;

    @Autowired
    private ConTbRequester33DaoImpl conTbRequester33Dao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ConMsInsurerBean searchEmployeeDetails(String idCardNo, String receiveNo) throws Exception {
        return conMsInsurerDao.findInsurerDetails(idCardNo, receiveNo);
    }

    public List<OverRefundEmployeeBean> searchEmployeeRefundPeriods(SearchOverRefundEmployeeRequest request, String idCardNo) throws Exception {
        timer.start("searchEmployeeRefundPeriods");
        String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
        String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();
        List<OverRefundEmployeeBean> periodList = refundMultipleCompaniesDao.findRefundPeriods(request.getIdCardNo()
                , request.getReceiveNo()
                , request.getProgressStatus()
                , startPeriod
                , endPeriod);
        if (CollectionUtils.isEmpty(periodList)) {
            log.debug(timer.endToString("Not found period data refund employee multiple company"));
            return new ArrayList<>();
        }

        //data after save refund request
        List<OverRefundEmployeeBean> existsRefundRequest = periodList.stream().filter(x -> !NumberUtils.isZeroOrNull(x.getRefundRequestId())).collect(Collectors.toList());
        //Set overPaid = requestTotalAmount case existsRefundRequest
        existsRefundRequest.forEach(x -> x.setOverPaid(x.getRequestTotalAmount()));

        //Grouping and sum amount, before save refund request
        List<OverRefundEmployeeBean> notExistsRefundRequest = distinctRefundWithCompanyCount(periodList.stream().filter(x -> NumberUtils.isZeroOrNull(x.getRefundRequestId())).collect(Collectors.toList()));
//        log.info("------------------------------------------");

        //find companies details and data order pay have refund request
        findCompaniesAndRequester(existsRefundRequest, request, idCardNo, true);
        //find companies details and data order pay does not exists refund request
        findCompaniesAndRequester(notExistsRefundRequest, request, idCardNo, false);

        List<OverRefundEmployeeBean> totalRefund = new ArrayList<>();
        totalRefund.addAll(notExistsRefundRequest);
        totalRefund.addAll(existsRefundRequest);

        //sort by pay period DESC
        sortByRefundPeriods(totalRefund);

//        //find companies details and data order pay
//        findCompaniesAndRequester(totalRefund, request, idCardNo);
        timer.endToString("searchEmployeeRefundPeriods");
        return totalRefund;
    }

    public List<OverRefundEmployeeBean> searchEmployeeRefundPeriodsNotice(SearchOverRefundEmployeeNoticeRequest request, Paginable paginable) throws Exception {
        timer.start("searchEmployeeRefundPeriodsNotice");
        String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
        String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();
        List<OverRefundEmployeeBean> periodList = refundMultipleCompaniesDao.findRefundPeriodsNotice(request.getDepCode()
                , request.getDepRegionCode()
                , request.getAnnounceStatus()
                , startPeriod
                , endPeriod
                , paginable);
        if (CollectionUtils.isEmpty(periodList)) {
            log.debug(timer.endToString("Not found period data refund employee multiple company"));
            return new ArrayList<>();
        }

        // sort by pay period DESC
        sortByRefundPeriods(periodList);

        //find companies details and data order pay
        findCompanies(periodList);

        // filter refund_total_amount more than zero
        periodList = checkRequestTotalAmount(periodList);
        timer.endToString("searchEmployeeRefundPeriodsNotice");
        return periodList;
    }

    private Date convertStringToDate(String date) throws ParseException {
        return DateTimeUtils.parseThaiDateToDate("01/" + date);
    }

    private void sortByRefundPeriods(List<OverRefundEmployeeBean> periods) {
        periods.sort((o1, o2) -> {
            try {
                return convertStringToDate(o2.getPayPeriod()).compareTo(convertStringToDate(o1.getPayPeriod()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

//    private void findCompaniesAndRequester(List<OverRefundEmployeeBean> periods, SearchOverRefundEmployeeRequest request, String idCardNo) {
//        periods.forEach(period -> {
//            try {
//                List<OverRefundEmployeeCompanyBean> companies = overRefundEmployeeDao.findCompanyDetailsMultipleCompanies(idCardNo, period.getPayPeriod());
//                period.setCompanyDetails(companies);
//                period.setCountCompany(companies.size());
//                period.setOrderList(conTbRequester33Dao.queryOrderNoAndOrderStatus(period.getRefundRequestId()
//                        , "S".equals(request.getProgressStatus()) ? "W" : request.getProgressStatus()
//                        , request.getReceiveNo()
//                        , period.getPayPeriod()));
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }

    private int countDistinctCompany(List<OverRefundEmployeeCompanyBean> companies) {
        List<String> companyNoList = companies.stream().map(OverRefundEmployeeCompanyBean::getCompanyNo).distinct().collect(Collectors.toList());
        return companyNoList.size();
    }

    private void findCompaniesAndRequester(List<OverRefundEmployeeBean> periods, SearchOverRefundEmployeeRequest request, String idCardNo, boolean type) {
        if (type) {
            periods.forEach(period -> {
                try {
                    List<OverRefundEmployeeCompanyBean> companies = overRefundEmployeeDao.findCompanyDetailsMultipleCompaniesExistsRefundRequest(period.getRefundPeriodId());
                    log.info("Company List : ");
                    log.info(WebServiceUtils.toJson(companies));
                    period.setCompanyDetails(companies);
//                    period.setCountCompany(companies.size());
                    period.setCountCompany(countDistinctCompany(companies));
                    period.setOrderList(conTbRequester33Dao.queryOrderNoAndOrderStatus(period.getRefundRequestId()
                            , "S".equals(request.getProgressStatus()) ? "W" : request.getProgressStatus()
                            , request.getReceiveNo()
                            , period.getPayPeriod()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            periods.forEach(period -> {
                try {
                    List<OverRefundEmployeeCompanyBean> companies = overRefundEmployeeDao.findCompanyDetailsMultipleCompanies(idCardNo, period.getPayPeriod());
                    period.setCompanyDetails(companies);
//                    period.setCountCompany(companies.size());
                    period.setCountCompany(countDistinctCompany(companies));
                    period.setOrderList(new ArrayList<>());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void findCompanies(List<OverRefundEmployeeBean> periods) {
        periods.forEach(period -> {
            try {
                List<OverRefundEmployeeCompanyBean> companies = overRefundEmployeeDao.findCompanyDetailsMultipleCompanies(period.getIdCardNo(), period.getPayPeriod());
                period.setCompanyDetails(companies);
                period.setCountCompany(countDistinctCompany(companies));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

//    private List<OverRefundEmployeeBean> distinctRefundWithCompanyCount(List<OverRefundEmployeeBean> periods) {
//        return periods.stream()
//                .collect(Collectors.groupingBy(
//                        refund -> Arrays.asList(refund.getRefundRequestId(), refund.getPayPeriod()),  // Grouping
//                        Collectors.collectingAndThen(
//                                Collectors.reducing((p1, p2) -> new OverRefundEmployeeBean(
//                                        p1.getRefundRequestId(),
//                                        p1.getPayPeriod(),
//                                        p1.getRefundReasonCode(),
////                                        (p1.getSumAmount().add(p2.getSumAmount())).compareTo(fixedAmount750) >= 0 ? fixedAmount750 : p1.getSumAmount().add(p2.getSumAmount()),
//                                        p1.getSumAmount().add(p2.getSumAmount()),
////                                        (p1.getRequestTotalAmount().add(p2.getRequestTotalAmount())).add(((p1.getSumAmount().add(p2.getSumAmount())).subtract(fixedAmount750)).compareTo(zero) > 0 ? (p1.getSumAmount().add(p2.getSumAmount())).subtract(fixedAmount750) : zero),
////                                        (p1.getRequestTotalAmount().add(p2.getRequestTotalAmount())).add(((p1.getSumAmount().add(p2.getSumAmount()))
////                                                .subtract(((p1.getTotalWages().add(p2.getTotalWages()).compareTo(BigDecimal.valueOf(15000)) >= 0 ? BigDecimal.valueOf(15000) : p1.getTotalWages().add(p2.getTotalWages()))
////                                                        .multiply(BigDecimal.valueOf(Math.max(p1.getRate(), p2.getRate())))).divide(BigDecimal.valueOf(100))))),
//                                        calOverPaid(NumberUtils.convertZeroIfNull(p1.getRequestTotalAmount()).add(NumberUtils.convertZeroIfNull(p2.getRequestTotalAmount()))
//                                                , NumberUtils.convertZeroIfNull(p1.getSumAmount()).add(NumberUtils.convertZeroIfNull(p2.getSumAmount()))
//                                                , NumberUtils.convertZeroIfNull(p1.getTotalWages()).add(NumberUtils.convertZeroIfNull(p2.getTotalWages()))
//                                                , Math.max(p1.getRate(), p2.getRate())),
////                                        p1.getSumAmount().add(p2.getSumAmount()),
////                                        p1.getRequestTotalAmount().add(p2.getRequestTotalAmount()),
//                                        p1.getCompanyNo(),
//                                        Math.max(p1.getRate(), p2.getRate()),
//                                        NumberUtils.convertZeroIfNull(p1.getTotalWages()).add(NumberUtils.convertZeroIfNull(p2.getTotalWages()))
//                                )),
//                                p -> {
//                                    OverRefundEmployeeBean company = p.get();
//                                    // Count distinct companies
//                                    Set<String> uniqueCompanies = periods.stream()
//                                            .filter(ppl -> ppl.getPayPeriod().equals(company.getPayPeriod()))
//                                            .map(OverRefundEmployeeBean::getCompanyNo)
//                                            .collect(Collectors.toSet());
//                                    // Set the company count as the number of unique companies
//                                    company.setCountCompany(uniqueCompanies.size());
//                                    return company;
//                                }
//                        )
//                ))
//                .values()
//                .stream()
//                .filter(x -> x.getCountCompany() > 1 && x.getRequestTotalAmount().compareTo(zero) > 0 && NumberUtils.convertZeroIfNull(x.getTotalWages()).compareTo(maxTotalWages) >= 0)
//                .collect(Collectors.toList());
//    }

    private List<OverRefundEmployeeBean> distinctRefundWithCompanyCount(List<OverRefundEmployeeBean> periods) {
        return periods.stream()
                .collect(Collectors.groupingBy(
                        refund -> Arrays.asList(refund.getRefundRequestId(), refund.getPayPeriod()),  // Grouping
                        Collectors.collectingAndThen(
                                Collectors.reducing((p1, p2) -> {
//                                    log.info("------------------------------------------");
//                                    log.info("RefundRequestId : {}", p1.getRefundRequestId());
//                                    log.info("PayPeriod : {}", p1.getPayPeriod());
//                                    log.info("RefundReasonCode : {}", p1.getRefundReasonCode());
//                                    log.info("P1.SumAmount : {}, P2.SumAmount : {}, SUM : {}", p1.getSumAmount(), p2.getSumAmount(), p1.getSumAmount().add(p2.getSumAmount()));
//                                    log.info("P1.RequestTotalAmount : {}, P2.RequestTotalAmount : {}, RequestTotalAmountSUM : {}", p1.getRequestTotalAmount(), p2.getRequestTotalAmount(), p1.getRequestTotalAmount().add(p2.getRequestTotalAmount()));
//                                    log.info("P1.TotalWages : {}, P2.TotalWages : {}, TotalWagesSUM : {}", p1.getTotalWages(), p2.getTotalWages(), p1.getTotalWages().add(p2.getTotalWages()));
//                                    log.info("P1.Rate : {}, P2.Rate : {}, MAXRate : {}", p1.getRate(), p1.getRate(), Math.max(p1.getRate(), p2.getRate()));
//                                    log.info("P1.CompanyNo : {}", p1.getCompanyNo());
                                    BigDecimal sumAmount = p1.getSumAmount().add(p2.getSumAmount());
                                    BigDecimal requestTotalAmount = NumberUtils.convertZeroIfNull(p1.getRequestTotalAmount()).add(NumberUtils.convertZeroIfNull(p2.getRequestTotalAmount()));
                                    Integer maxRate = Math.max(p1.getRate(), p2.getRate());
                                    BigDecimal totalWages = NumberUtils.convertZeroIfNull(p1.getTotalWages()).add(NumberUtils.convertZeroIfNull(p2.getTotalWages()));
                                    BigDecimal calOverPaid = calOverPaid(requestTotalAmount, sumAmount, totalWages, maxRate);
                                    Date maxPaymentDate = p1.getPaymentDate().compareTo(p2.getPaymentDate()) > 0 ? p1.getPaymentDate() : p2.getPaymentDate();

                                    return new OverRefundEmployeeBean(
                                            p1.getRefundRequestId(),
                                            p1.getPayPeriod(),
                                            p1.getRefundReasonCode(),
                                            sumAmount,
                                            requestTotalAmount,
                                            p1.getCompanyNo(),
                                            maxRate,
                                            totalWages,
                                            calOverPaid,
                                            maxPaymentDate
                                );
                                }),
                                p -> {
                                    OverRefundEmployeeBean company = p.get();
                                    // Count distinct companies
                                    Set<String> uniqueCompanies = periods.stream()
                                            .filter(ppl -> ppl.getPayPeriod().equals(company.getPayPeriod()))
                                            .map(OverRefundEmployeeBean::getCompanyNo)
                                            .collect(Collectors.toSet());
                                    log.info("CompanyNo List distinct : {}, count : {}", uniqueCompanies, uniqueCompanies.size());
                                    // Set the company count as the number of unique companies
                                    company.setCountCompany(uniqueCompanies.size());
                                    return company;
                                }
                        )
                ))
                .values()
                .stream()
                .filter(x -> x.getCountCompany() > 1 && x.getOverPaid().compareTo(zero) > 0 && NumberUtils.convertZeroIfNull(x.getTotalWages()).compareTo(maxTotalWages) >= 0)
                .collect(Collectors.toList());
    }

    private List<OverRefundEmployeeBean> checkRequestTotalAmount(List<OverRefundEmployeeBean> periods) {
        return periods.stream().filter(x -> x.getCountCompany() > 1 && x.getRequestTotalAmount().compareTo(BigDecimal.ZERO) > 0 && x.getTotalWages().compareTo(maxTotalWages) >= 0 ).collect(Collectors.toList());
    }

    private BigDecimal calOverPaid(BigDecimal requestTotalAmount, BigDecimal sumAmount, BigDecimal totalWages, Integer maxRate) {
//        log.info("RequestTotalAmount : {}, SumAmount : {}, TotalWages : {}, MaxRate : {}", requestTotalAmount, sumAmount, totalWages, maxRate);
        BigDecimal overPaid = (sumAmount.subtract(((NumberUtils.convertZeroIfNull(totalWages).compareTo(maxTotalWages) >= 0 ? maxTotalWages : NumberUtils.convertZeroIfNull(totalWages) )
                .multiply(BigDecimal.valueOf(maxRate)))
                .divide(oneHundred))).subtract(requestTotalAmount);
//        log.info("OverPaid : {}", overPaid);
        return overPaid;
    }
}
