package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsInsurerIdCardDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRefundRequestDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester33DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.OverRefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.OverRefundEmployeeNoticeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundEmployeeNoticeRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundEmployeeRequest;

@Service
public class OverRefundEmployeeServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(OverRefundEmployeeServiceImpl.class);
    @Autowired
    private ConMsInsurerIdCardDaoImpl conMsInsurerIdCardDao;
    @Autowired
    private OverRefundEmployeeDaoImpl overRefundEmployeeDao;
    @Autowired
    private OverRefundEmployeeNoticeDaoImpl overRefundEmployeeNoticeDao;
    @Autowired
    private ConTbRefundRequestDaoImpl conTbRefundRequestDao;
    @Autowired
    private ConTbRequester33DaoImpl conTbRequester33Dao;
    @Autowired
    private TimerHelper timer;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ConMsInsurerBean searchInsurer(String idCardNo) throws Exception {
            return conMsInsurerIdCardDao.searchInsurerEmp(idCardNo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public String searchIdCardNoByReceiveNo(String receiveNo) throws Exception{
        return conTbRefundRequestDao.queryInsurerIdByReceiveNo(receiveNo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OverRefundEmployeeBean> searchRefundEmployeePeriod(SearchOverRefundEmployeeRequest request, String idCardNo) throws Exception {
        try {
            timer.start("searchRefundEmployeeList");
            String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
            String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();
//            Integer countCompany = countCompany(request);
//            if (countCompany > 1) {
            List<OverRefundEmployeeBean> refundEmployeeList = overRefundEmployeeDao.searchRefundEmployee(request.getIdCardNo(), request.getReceiveNo(), request.getProgressStatus(), startPeriod, endPeriod);
            if (!CollectionUtils.isEmpty(refundEmployeeList)) {
                for (OverRefundEmployeeBean refundEmployeeData : refundEmployeeList) {
//                        List<OverRefundEmployeeCompanyBean> companyList = overRefundEmployeeDao.searchRefundEmployeeCompany(idCardNo, refundEmployeeData.getPayPeriod());
                    refundEmployeeData.setCompanyDetails(overRefundEmployeeDao.findCompanyDetailsMultipleCompanies(idCardNo, refundEmployeeData.getPayPeriod()));
                    refundEmployeeData.setOrderList(conTbRequester33Dao.queryOrderNoAndOrderStatus(refundEmployeeData.getRefundRequestId(), "S".equals(request.getProgressStatus()) ? "W" : request.getProgressStatus(), request.getReceiveNo(), refundEmployeeData.getPayPeriod()));
                }
            }
//            }
            log.debug(timer.endToString("searchRefundEmployeeList"));
            return refundEmployeeList;
        } catch (Exception e) {
            log.error("searchRefundEmployeeList : ", e);
            throw e;
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer countCompany(SearchOverRefundEmployeeRequest request) throws Exception{
        String startPeriod = String.valueOf(Integer.parseInt(request.getStartPeriodYear()) - 543) + request.getStartPeriodMonth();
        String endPeriod = String.valueOf(Integer.parseInt(request.getEndPeriodYear()) - 543) + request.getEndPeriodMonth();
        return overRefundEmployeeDao.queryCountCompany(request.getIdCardNo(), startPeriod, endPeriod);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OverRefundEmployeeBean> getRefundEmployeeNoticeList(SearchOverRefundEmployeeNoticeRequest request, Paginable paginable) throws Exception{
        try {
            timer.start("getRefundEmployeeNoticeList");
            String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
            String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();
            log.debug("getRefundEmployeeNoticeList:: depCode: {}, depReginCode: {}, ntStatus: {}, sp/ep: {}/{}", request.getDepCode(), request.getDepRegionCode(), request.getAnnounceStatus(), startPeriod, endPeriod);
            List<OverRefundEmployeeBean> refundEmployeeList = overRefundEmployeeNoticeDao.queryRefundNotice(request.getDepCode(), request.getDepRegionCode(), request.getAnnounceStatus(), startPeriod, endPeriod, paginable);
            if (!CollectionUtils.isEmpty(refundEmployeeList)) {
                for (OverRefundEmployeeBean refundEmployeeData : refundEmployeeList) {
//                    List<OverRefundEmployeeCompanyBean> companyList = overRefundEmployeeDao.searchRefundEmployeeCompany(refundEmployeeData.getIdCardNo(), refundEmployeeData.getPayPeriod());
                    refundEmployeeData.setCompanyDetails(overRefundEmployeeDao.findCompanyDetailsMultipleCompanies(refundEmployeeData.getIdCardNo(), refundEmployeeData.getPayPeriod()));
//                    refundEmployeeData.setOrderList(conTbRequester33Dao.queryOrderNoAndOrderStatus(refundEmployeeData.getRefundRequestId()));
                }
            }
            log.debug(timer.endToString("getRefundEmployeeNoticeList"));
            return refundEmployeeList;
        }catch (Exception e){
            log.error("getRefundEmployeeNoticeList error : ",e);
            throw e;
        }
    }
}
