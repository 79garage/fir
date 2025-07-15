package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.dto.IUserInfo;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestCompanyBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestEmployeeBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestInsurerBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRefundRequestDaoImpl;

@Service
public class ConTbRefundRequestServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(ConTbRefundRequestServiceImpl.class);

    @Autowired
    private ConTbRefundRequestDaoImpl conTbRefundRequestDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundRequestCompanyBatchBean> searchRefundCompanyRequestBatch(Paginable paginable, IUserInfo user, List<Long> refundRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchRefundCompanyRequestBatch by user: {} ", userName);

        List<RefundRequestCompanyBatchBean> refundRequestBeans = conTbRefundRequestDao.queryConTbRefundRequestCompanyBatch(paginable, refundRequestIds);
//        for (RefundRequestCompanyBatchBean refundData : refundRequestBeans) {
//            Date datePayPeriod = DateTimeUtils.parseThaiDateToDate("01/" + refundData.getPeriod());
//            String payPeriod = DateTimeUtils.formatDate(datePayPeriod, "dd MMM YYYY");
//
//            InformationConditionBean informationCondition = new InformationConditionBean();
//            informationCondition.setGroupCode("COMPANY_RATE");
//            informationCondition.setSysCode("M33");
//            informationCondition.setSysDate(payPeriod);
//            InsurerRateBean rate = conTbInformationDao.searchInsurerRate(informationCondition);
//
//            refundData.setRefundSicknessRate(rate.getSickness());
//            refundData.setRefundRetirementRate(rate.getRetirement());
//            refundData.setRefundUnemploymentRate(rate.getUnemployment());
//        }

        log.debug("End searchRefundCompanyRequestBatch found result : {} ", refundRequestBeans != null ? refundRequestBeans.size() : 0);
        return refundRequestBeans;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundRequestEmployeeBatchBean> searchRefundEmployeeRequestBatch(Paginable paginable, IUserInfo user, List<Long> refundRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchRefundEmployeeRequestBatch by user: {} ", userName);

        List<RefundRequestEmployeeBatchBean> refundRequestBeans = conTbRefundRequestDao.queryConTbRefundRequestEmployeeBatch(paginable, refundRequestIds);
//        for (RefundRequestEmployeeBatchBean refundData : refundRequestBeans) {
//            Date datePayPeriod = DateTimeUtils.parseThaiDateToDate("01/" + refundData.getPeriod());
//            String payPeriod = DateTimeUtils.formatDate(datePayPeriod, "dd MMM YYYY");
//
//            InformationConditionBean informationCondition = new InformationConditionBean();
//            informationCondition.setGroupCode("EMPLOYEE_RATE");
//            informationCondition.setSysCode("M33");
//            informationCondition.setSysDate(payPeriod);
//            InsurerRateBean rate = conTbInformationDao.searchInsurerRate(informationCondition);
//
//            refundData.setRefundSicknessRate(rate.getSickness());
//            refundData.setRefundRetirementRate(rate.getRetirement());
//            refundData.setRefundUnemploymentRate(rate.getUnemployment());
//        }

        log.debug("End searchRefundEmployeeRequestBatch found result : {} ", refundRequestBeans != null ? refundRequestBeans.size() : 0);
        return refundRequestBeans;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundRequestInsurerBatchBean> searchRefundInsurerRequestBatch(Paginable paginable, IUserInfo user, List<Long> refundRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchRefundInsurerRequestBatch by user: {} ", userName);

        List<RefundRequestInsurerBatchBean> refundRequestBeans = conTbRefundRequestDao.queryConTbRefundRequestInsurerBatch(paginable, refundRequestIds);
//        for (RefundRequestInsurerBatchBean refundData : refundRequestBeans) {
//            Date datePayPeriod = DateTimeUtils.parseThaiDateToDate("01/" + refundData.getPeriod());
//            String payPeriod = DateTimeUtils.formatDate(datePayPeriod, "dd MMM YYYY");
//
//            InformationConditionBean informationCondition = new InformationConditionBean();
//            informationCondition.setGroupCode("INSURER_RATE");
//            informationCondition.setSysCode("M39");
//            informationCondition.setSysDate(payPeriod);
//            InsurerRateBean insurerRate = conTbInformationDao.searchInsurerRate(informationCondition);
//
//            refundData.setRefundSicknessRate(insurerRate.getSickness());
//            refundData.setRefundRetirementRate(insurerRate.getRetirement());
//            refundData.setRefundUnemploymentRate(insurerRate.getUnemployment());
//        }

        log.debug("End searchRefundInsurerRequestBatch found result : {} ", refundRequestBeans != null ? refundRequestBeans.size() : 0);
        return refundRequestBeans;
    }

    private String getUserName(IUserInfo user) {
        return user != null ? user.getUsername() : "no user login";
    }
    
}
