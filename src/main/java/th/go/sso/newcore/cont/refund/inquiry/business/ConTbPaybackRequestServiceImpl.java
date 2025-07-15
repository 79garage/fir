package th.go.sso.newcore.cont.refund.inquiry.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.dto.IUserInfo;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.CheckPaybackBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestInfoBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRefundRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.PaybackRefundPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbPaybackRequestDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRefundRequestDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchConTbPaybackRequestRequest;

@Service
public class ConTbPaybackRequestServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(ConTbPaybackRequestServiceImpl.class);

    @Autowired
    private TimerHelper timer;
    @Autowired
    private ConTbPaybackRequestDaoImpl conTbPaybackRequestDao;
    @Autowired
    private ConTbRefundRequestDaoImpl conTbRefundRequestDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ConTbPaybackRequestInfoBean> getPaybackRequestByCondition(SearchConTbPaybackRequestRequest request) throws Exception {
        timer.start("getPaybackRequestByCondition");

        List<ConTbPaybackRequestInfoBean> paybackRequestInfoList = conTbPaybackRequestDao.getPaybackRequestByCondition(request);

        log.debug(timer.endToString("getPaybackRequestByCondition"));
        return paybackRequestInfoList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ConTbPaybackRequestBean getPaybackRequestById(Long id, IUserInfo user) throws Exception {
        log.debug("Start getPaybackRequestById id: {} by user: {} ", id, getUserName(user));

//        ConTbPaybackRequestBean bean = conTbPaybackRequestDao.getConTbPaybackRequestByIdOrReceiveNo(id, null);
        ConTbPaybackRequestBean bean = conTbPaybackRequestDao.getConTbPaybackRequestByIdOrReceiveNo(null, null);
        if(bean == null){
            throw new RuntimeException("ไม่พบข้อมูล");
        }
        List<PaybackRefundPeriodBean> paybackRefundPeriodBeanList = conTbPaybackRequestDao.getRefundPeriodByRefundRequestId(bean.getRefundRequestId(), null);
        bean.setRefundPeriodList(paybackRefundPeriodBeanList);

        log.debug("End getPaybackRequestById id {} by user: {} found result: {} periodList: {} ", id, getUserName(user), bean != null, paybackRefundPeriodBeanList != null ? paybackRefundPeriodBeanList.size() : 0);
        return bean;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ConTbPaybackRequestBean getPaybackRequestByReceiveNo(String receiveNo, IUserInfo user) throws Exception {
        timer.start("Start getPaybackRequestByReceiveNo receiveNo: " + receiveNo);

        ConTbRefundRequestBean refundRequestBean = conTbRefundRequestDao.findConTbRefundRequestByReceiveNo(receiveNo);
        log.info("refundRequestId : {}", refundRequestBean.getRefundRequestId());

        // return section >>> 0 = company, 3 = insurer under the company and insurer work multiple companies, 1 = insurer 39
        String separateSection = Objects.equals("0", refundRequestBean.getSection()) && !NumberUtils.isZeroOrNull(refundRequestBean.getCompanyId()) && NumberUtils.isZeroOrNull(refundRequestBean.getInsurerId())
                ? "0"
                : (Objects.equals("0", refundRequestBean.getSection()) && !NumberUtils.isZeroOrNull(refundRequestBean.getInsurerId())) || Objects.equals("3", refundRequestBean.getSection())
                ? "3"
                : Objects.equals("1", refundRequestBean.getSection())
                ? "1"
                : null
        ;
        ConTbPaybackRequestBean bean = conTbPaybackRequestDao.getConTbPaybackRequestByIdOrReceiveNo(receiveNo, separateSection);

        if (!ObjectUtils.isObjectNull(bean)) {
            List<PaybackRefundPeriodBean> paybackRefundPeriodBeanList = new ArrayList<PaybackRefundPeriodBean>();
            if (!NumberUtils.isZeroOrNull(bean.getRefundRequestId())) {
                paybackRefundPeriodBeanList = conTbPaybackRequestDao.getRefundPeriodByRefundRequestId(bean.getRefundRequestId(), separateSection);
//                List<Long> requesterId = paybackRefundPeriodBeanList.stream().map(PaybackRefundPeriodBean::getRequesterId).distinct().collect(Collectors.toList());
//                paybackRefundPeriodBeanList.stream().forEach(items -> items.setRefundTotalAmount(items.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterId.size()),2,2)));
//                List<String> relationGroupList = paybackRefundPeriodBeanList.stream().map(PaybackRefundPeriodBean::getRelationGroup).filter(x -> !ObjectUtils.isObjectNull(x)).collect(Collectors.toList());
//                if (!CollectionUtils.isEmpty(relationGroupList)) {
//                    calPaybackAmount(paybackRefundPeriodBeanList);
//                }
            }
            paybackRefundPeriodBeanList.sort(Comparator.comparing(PaybackRefundPeriodBean::getRequesterId).thenComparing(PaybackRefundPeriodBean::getRefundPeriod));
            bean.setRefundPeriodList(paybackRefundPeriodBeanList);
            bean.setOrderList(conTbPaybackRequestDao.queryOrderByRefundRequestId(bean.getRefundRequestId(), separateSection));
//            bean.setOrderList(conTbRequester39Dao.queryOrderByRefundRequestId(bean.getRefundRequestId()));

            BigDecimal summaryPaybackAmount = BigDecimal.ZERO;
            summaryPaybackAmount.setScale(2, RoundingMode.HALF_UP);
            try {
                summaryPaybackAmount = getSummaryPaybackAmount(paybackRefundPeriodBeanList);
                bean.setPaybackTotalAmount(summaryPaybackAmount);
            }catch (Exception e){
                log.error("getSummaryPaybackAmount: ",e);
                throw new Exception("ข้อมูลในระบบไม่ถูกต้อง กรุณาติดต่อเจ้าหน้าที่");
            }

            bean.setType(List.of("1", "3").contains(separateSection) ? "insurer"
                    : Objects.equals("0", separateSection) ? "company"
                    : null);
        }

        timer.start("End getPaybackRequestByReceiveNo");
        return bean;
    }

//    @Transactional(propagation = Propagation.SUPPORTS)
//    public ConTbPaybackRequestBean getPaybackRequestByReceiveNo(String receiveNo, IUserInfo user) throws Exception {
//        log.debug("Start getPaybackRequestByReceiveNo receiveNo: {} by user: {} ", receiveNo, getUserName(user));
//
//        ConTbPaybackRequestBean bean = conTbPaybackRequestDao.getConTbPaybackRequestByIdOrReceiveNo(null, receiveNo);
//        // Add OrderList
//
//        if(bean != null){
//            List<PaybackRefundPeriodBean> paybackRefundPeriodBeanList = new ArrayList<PaybackRefundPeriodBean>();
//            if (!NumberUtils.isZeroOrNull(bean.getRefundRequestId())) {
//                paybackRefundPeriodBeanList = conTbPaybackRequestDao.getRefundPeriodByRefundRequestId(bean.getRefundRequestId());
////                List<Long> requesterId = paybackRefundPeriodBeanList.stream().map(PaybackRefundPeriodBean::getRequesterId).distinct().collect(Collectors.toList());
////                paybackRefundPeriodBeanList.stream().forEach(items -> items.setRefundTotalAmount(items.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterId.size()),2,2)));
//                List<String> relationGroupList = paybackRefundPeriodBeanList.stream().map(PaybackRefundPeriodBean::getRelationGroup).filter(x -> !ObjectUtils.isObjectNull(x)).collect(Collectors.toList());
//                if (!CollectionUtils.isEmpty(relationGroupList)) {
//                    calPaybackAmount(paybackRefundPeriodBeanList);
//                }
//            }
//            paybackRefundPeriodBeanList.sort(Comparator.comparing(PaybackRefundPeriodBean::getRequesterId).thenComparing(PaybackRefundPeriodBean::getRefundPeriod));
//            bean.setRefundPeriodList(paybackRefundPeriodBeanList);
////            bean.setOrderList(conTbPaybackRequestDao.queryOrderByRefundRequestId(bean.getRefundRequestId()));
////            bean.setOrderList(conTbRequester39Dao.queryOrderByRefundRequestId(bean.getRefundRequestId()));
//
//            BigDecimal summaryPaybackAmount = BigDecimal.ZERO;
//            summaryPaybackAmount.setScale(2, RoundingMode.HALF_UP);
//            try {
//                summaryPaybackAmount = getSummaryPaybackAmount(paybackRefundPeriodBeanList);
//                bean.setPaybackTotalAmount(summaryPaybackAmount);
//            }catch (Exception e){
//                log.error("getSummaryPaybackAmount: ",e);
//                throw new Exception("ข้อมูลในระบบไม่ถูกต้อง กรุณาติดต่อเจ้าหน้าที่");
//            }
//        }
//        return bean;
//    }

    private String getUserName(IUserInfo user) {
        return user != null ? user.getUsername() : "no user login";
    }

    private static BigDecimal getSummaryPaybackAmount(List<PaybackRefundPeriodBean> refundPeriodList) {
        BigDecimal summaryPaybackAmount;
        summaryPaybackAmount = refundPeriodList.stream()
                .map(PaybackRefundPeriodBean::getRefundTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        summaryPaybackAmount = refundPeriodList.stream()
//                .map(bean -> bean.getPaybackAmount() != null ? bean.getPaybackAmount() : bean.getRefundTotalAmount())
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return summaryPaybackAmount;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ConTbPaybackBean> searchPaybackRequestCompanyBatch(Paginable paginable, IUserInfo user, List<Long> paybackRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchPaybackRequestCompanyBatch by user: {} ", userName);

        List<ConTbPaybackBean> refundPaybackBeans = conTbPaybackRequestDao.queryConTbRefundRequestCompanyBatch(paginable, paybackRequestIds);

        log.debug("End searchPaybackRequestCompanyBatch found result : {} ", refundPaybackBeans != null ? refundPaybackBeans.size() : 0);
        return refundPaybackBeans;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ConTbPaybackBean> searchPaybackCompanyBatch(String createBy, Date createDate, Paginable paginable, IUserInfo user, List<Long> paybackRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchPaybackCompanyBatch by user: {} ", userName);

        List<ConTbPaybackBean> paybackCompanyBean = conTbPaybackRequestDao.queryConTbPaybackCompanyBatch(createBy, createDate, paginable, paybackRequestIds);

        log.debug("End searchPaybackCompanyBatch found result : {} ", paybackCompanyBean != null ? paybackCompanyBean.size() : 0);
        return paybackCompanyBean;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ConTbPaybackBean> searchPaybackRequestInsurerBatch(Paginable paginable, IUserInfo user, List<Long> paybackRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchPaybackRequestInsurerBatch by user: {} ", userName);

        List<ConTbPaybackBean> refundPaybackBeans = conTbPaybackRequestDao.queryConTbRefundRequestInsurerBatch(paginable, paybackRequestIds);

        log.debug("End searchPaybackRequestInsurerBatch found result : {} ", refundPaybackBeans != null ? refundPaybackBeans.size() : 0);
        return refundPaybackBeans;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ConTbPaybackBean> searchPaybackInsurerBatch(String createBy, Date createDate, Paginable paginable, IUserInfo user, List<Long> paybackRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchPaybackInsurerBatch by user: {} ", userName);

        List<ConTbPaybackBean> paybackInsurerBean = conTbPaybackRequestDao.queryConTbPaybackInsurerBatch(createBy, createDate, paginable, paybackRequestIds);

        log.debug("End searchPaybackInsurerBatch found result : {} ", paybackInsurerBean != null ? paybackInsurerBean.size() : 0);
        return paybackInsurerBean;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ConTbPaybackBean> searchPaybackRequestEmployeeBatch(Paginable paginable, IUserInfo user, List<Long> paybackRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchPaybackRequestEmployeeBatch by user: {} ", userName);

        List<ConTbPaybackBean> refundPaybackBeans = conTbPaybackRequestDao.queryConTbRefundRequestEmployeeBatch(paginable, paybackRequestIds);

        log.debug("End searchPaybackRequestEmployeeBatch found result : {} ", refundPaybackBeans != null ? refundPaybackBeans.size() : 0);
        return refundPaybackBeans;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ConTbPaybackBean> searchPaybackEmployeeBatch(String createBy, Date createDate, Paginable paginable, IUserInfo user, List<Long> paybackRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchPaybackEmployeeBatch by user: {} ", userName);

        List<ConTbPaybackBean> paybackEmployeeBean = conTbPaybackRequestDao.queryConTbPaybackEmployeeBatch(createBy, createDate, paginable, paybackRequestIds);

        log.debug("End searchPaybackEmployeeBatch found result : {} ", paybackEmployeeBean != null ? paybackEmployeeBean.size() : 0);
        return paybackEmployeeBean;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long isRefundRequestIdExist(String receiveNo) throws Exception{
        return conTbRefundRequestDao.findRefundRequestIdByReceiveNo(receiveNo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CheckPaybackBean> isPaybackRequestNoExist(Long refundRequestId) throws Exception{
        return conTbPaybackRequestDao.findPaybackRequestNoByRefundRequestId(refundRequestId);
    }

//    private void calPaybackAmount(List<PaybackRefundPeriodBean> paybackRefundPeriodBeanList){
//        log.debug("Start calPaybackAmount");
//        long countG1 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().equals("G1")).count();
//        long countG2 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().equals("G2")).count();
//        long countG2G3G4 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().matches("G[234]")).count();
//        long countG3 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().equals("G3")).count();
//        long countG4 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().equals("G4")).count();
//        long countG4G5 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().matches("G[45]")).count();
//        long countG4G6 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().matches("G[46]")).count();
//        long countG4G7 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().matches("G[47]")).count();
//        long countG4G8 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().matches("G[48]")).count();
//        long countG5 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().equals("G5")).count();
//        long countG6 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().equals("G6")).count();
//        long countG7 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().equals("G7")).count();
//        long countG8 = paybackRefundPeriodBeanList.stream().filter(rc -> rc.getRelationGroup().equals("G8")).count();
//        for (PaybackRefundPeriodBean paybackRefundPeriodData : paybackRefundPeriodBeanList){
////            List<Long> requesterIds = paybackRefundPeriodBeanList.stream().map(PaybackRefundPeriodBean::getRequesterId).distinct().collect(Collectors.toList());
//            List<String> periods = paybackRefundPeriodBeanList.stream().map(PaybackRefundPeriodBean::getRefundPeriod).distinct().collect(Collectors.toList());
//            if (countG1 == paybackRefundPeriodBeanList.size()){
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//                paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG1, periods.size(), BigDecimal.ONE));
//            } else if (countG2 == paybackRefundPeriodBeanList.size()){
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//                paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG2, periods.size(), BigDecimal.ONE));
//            } else if (countG2G3G4 == paybackRefundPeriodBeanList.size()) {
//                if ("G2".equals(paybackRefundPeriodData.getRelationGroup())) {
//                    paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG2, periods.size(), BigDecimal.valueOf(2)));
//                } else if ("G3".equals(paybackRefundPeriodData.getRelationGroup())) {
//                    paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG3, periods.size(), BigDecimal.valueOf(2)));
//                } else if ("G4".equals(paybackRefundPeriodData.getRelationGroup())) {
//                    paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG4, periods.size(), BigDecimal.valueOf(2)));
//                }
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//            } else if (countG3 == paybackRefundPeriodBeanList.size()){
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//                paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG3, periods.size(), BigDecimal.ONE));
//            } else if (countG4 == paybackRefundPeriodBeanList.size()){
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//                paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG4, periods.size(), BigDecimal.ONE));
//            } else if (countG4G5 == paybackRefundPeriodBeanList.size()) {
//                if ("G4".equals(paybackRefundPeriodData.getRelationGroup())) {
//                    paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG4, periods.size(), BigDecimal.valueOf(2)));
//                } else if ("G5".equals(paybackRefundPeriodData.getRelationGroup())) {
//                    paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG5, periods.size(), BigDecimal.valueOf(2)));
//                }
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//            } else if (countG4G6 == paybackRefundPeriodBeanList.size()) {
//                if ("G4".equals(paybackRefundPeriodData.getRelationGroup())){
//                    paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().multiply(BigDecimal.valueOf(0.75)));
//                } else if ("G6".equals(paybackRefundPeriodData.getRelationGroup())) {
//                    paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().multiply(BigDecimal.valueOf(0.25)));
//                }
//            } else if (countG4G7 == paybackRefundPeriodBeanList.size()) {
//                if ("G4".equals(paybackRefundPeriodData.getRelationGroup())){
//                    paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().multiply(BigDecimal.valueOf(0.75)));
//                } else if ("G7".equals(paybackRefundPeriodData.getRelationGroup())) {
//                    paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().multiply(BigDecimal.valueOf(0.25)));
//                }
//            } else if (countG4G8 == paybackRefundPeriodBeanList.size()) {
//                if ("G4".equals(paybackRefundPeriodData.getRelationGroup())){
//                    paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().multiply(BigDecimal.valueOf(0.75)));
//                } else if ("G8".equals(paybackRefundPeriodData.getRelationGroup())) {
//                    paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().multiply(BigDecimal.valueOf(0.25)));
//                }
//            } else if (countG5 == paybackRefundPeriodBeanList.size()) {
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//                paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG5, periods.size(), BigDecimal.ONE));
//            } else if (countG6 == paybackRefundPeriodBeanList.size()) {
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//                paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG6, periods.size(), BigDecimal.ONE));
//            } else if (countG7 == paybackRefundPeriodBeanList.size()) {
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//                paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG7, periods.size(), BigDecimal.ONE));
//            } else if (countG8 == paybackRefundPeriodBeanList.size()) {
////                paybackRefundPeriodData.setRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount().divide(BigDecimal.valueOf(requesterIds.size()),2,2));
//                paybackRefundPeriodData.setRefundTotalAmount(calRefundTotalAmount(paybackRefundPeriodData.getRefundTotalAmount(), countG8, periods.size(), BigDecimal.ONE));
//            }
//        }
//        log.debug("End calPaybackAmount");
//    }
//    private BigDecimal calRefundTotalAmount(BigDecimal refundTotalAmount, Long countRelationGroup, int countPeriod, BigDecimal calDivide){
//        BigDecimal realTotalAmount = refundTotalAmount.divide(calDivide, 2, BigDecimal.ROUND_HALF_UP); // ROUND_HALF_UP used for rounding
//        BigDecimal countPerson = BigDecimal.valueOf(countRelationGroup).divide(BigDecimal.valueOf(countPeriod), 2, BigDecimal.ROUND_HALF_UP);
//        return realTotalAmount.divide(countPerson, BigDecimal.ROUND_HALF_UP);
//    }
}
