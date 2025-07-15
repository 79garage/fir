package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundPeriodActiveAndInactiveBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundPeriodActiveBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRefundPeriodDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRefundRequestDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester33DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester39DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedRefundRequestReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.SelectedEmployeeExistsDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.SelectedReceiptExistsDaoImpl;

@Service
public class RefundDeleteCancelHistByIdServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(RefundDeleteCancelHistByIdServiceImpl.class);

    @Autowired
    private ConTbRefundRequestDaoImpl conTbRefundRequestDao;
    @Autowired
    private ConTbRequester39DaoImpl conTbRequester39Dao;
    @Autowired
    private ConTbRequester33DaoImpl conTbRequester33Dao;
    @Autowired
    private ConTbRefundPeriodDaoImpl conTbRefundPeriodDao;
    @Autowired
    private SelectedEmployeeExistsDaoImpl selectedEmployeeExistsDao;
    @Autowired
    private SelectedReceiptExistsDaoImpl conTbReceiptExistsDao;
    @Autowired
    private RefundCompanyDaoImpl refundCompanyDao;
    @Autowired
    private RefundInsurerDaoImpl refundInsurerDao;
    @Autowired
    private RefundEmployeeDaoImpl refundEmployeeDao;
    @Autowired
    private RefundInsurerSelectedRefundRequestReceiptDaoImpl selectedInsurerExistsDao;

    public RefundRequestBean searchRefundRequestDetail(Long refundRequestId) throws Exception {
        log.debug(" Start searchRefundRequestDetail refundRequestId : {}",refundRequestId);
        RefundRequestBean bean = conTbRefundRequestDao.searchConTbRefundRequestByRefundRequestId(refundRequestId, null);
        if (!StringUtils.isEmpty(bean.getSection())){
            if (bean.getSection().equals("0")) {
                String progressStatus = refundCompanyDao.getProgressStatusByRefundRequestId(refundRequestId);
                bean.setProgressStatus(progressStatus);
            } else if (bean.getSection().equals("1")) {
                    log.debug("Section : 1 ");
                    List<ConTbRequesterBean> requester39List = conTbRequester39Dao.queryConTbRequester39(refundRequestId);
                    log.debug("queryConTbRequester39 : {}",requester39List.size());
                    bean.setRequesterList(requester39List);
                    String progressStatus = refundInsurerDao.getProgressStatusByRefundRequestId(refundRequestId);
                    bean.setProgressStatus(progressStatus);
            } else if (bean.getSection().equals("3")) {
                    log.debug("Section : 3 ");
                    List<ConTbRequesterBean> requesterMultipleList = conTbRequester33Dao.queryConTbRequester33(refundRequestId);
                    log.debug("queryConTbRequesterMultiple 33 : {}",requesterMultipleList.size());
                    bean.setRequesterList(requesterMultipleList);
                    String progressStatus = refundEmployeeDao.getProgressStatusByRefundRequestId(refundRequestId);
                    bean.setProgressStatus(progressStatus);
            }
        }
        log.debug(" End searchRefundRequestDetail refundRequestId : {}",refundRequestId);
        return bean;
    }
    public RefundPeriodActiveAndInactiveBean mapRefundPeriodActiveAndInactive(Long refundRequestId) throws Exception {
        log.debug(" Start mapRefundPeriodActiveAndInactive");
        RefundPeriodActiveAndInactiveBean bean = new RefundPeriodActiveAndInactiveBean();
        List<RefundPeriodActiveBean> activeList = new ArrayList<>();
        List<RefundPeriodActiveBean> inactiveList = new ArrayList<>();
        log.debug("Start searchRefundPeriod");
        List<RefundPeriodActiveBean> refundPeriodList = searchRefundPeriod(refundRequestId);
        log.debug("End searchRefundPeriod");
        for (RefundPeriodActiveBean refundPeriod : refundPeriodList){
            if (StringUtils.isEmpty(refundPeriod.getStatus())) {
                if (!NumberUtils.isZeroOrNull(refundPeriod.getReceiveCompanyId())) {
                    log.debug(" ReceiveCompanyId not empty");
                    List<SelectedEmployeeBean> selectedEmployeeList = selectedEmployeeExistsDao.findSelectedEmployee(refundPeriod.getRefundPeriodId());
                    log.debug("Query SelectedEmployeeList Success");
                    List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.searchReceiptExists(refundPeriod.getReceiveCompanyId());
                    log.debug("Query SelectedReceiptList Success");
                   refundPeriod.setSelectedEmployeeList(selectedEmployeeList);
                   refundPeriod.setSelectedReceiptList(selectedReceiptList);
                    log.debug("setSelected 33");
                } else if (!NumberUtils.isZeroOrNull(refundPeriod.getReceiveInsurerId())) {
                    List<SelectedReceiptBean> selectedReceiptList = selectedInsurerExistsDao.findRefundRequestReceipt(refundPeriod.getReceiveInsurerId());
                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
                    log.debug("setSelectedReceipt 39");
                } else {
                    List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.searchReceiptExistsMultiple(refundPeriod.getRefundPeriodId());
                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
                    log.debug("setSelectedReceiptMultiple");
                }
                List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.queryRefundReceiptByRefundPeriodId(refundPeriod.getRefundPeriodId());
                refundPeriod.setSelectedReceiptList(selectedReceiptList);
                activeList.add(refundPeriod);
                log.debug("add active");
            } else if (refundPeriod.getStatus().equals("D") || refundPeriod.getStatus().equals("C")) {
                if (!NumberUtils.isZeroOrNull(refundPeriod.getRefundPeriodId())) {
                    if (!NumberUtils.isZeroOrNull(refundPeriod.getReceiveCompanyId())) {
                        log.debug(" ReceiveCompanyId not empty");
                        List<SelectedEmployeeBean> selectedEmployeeList = selectedEmployeeExistsDao.findSelectedEmployee(refundPeriod.getRefundPeriodId());
                        log.debug("Query SelectedEmployeeList Success");
                        refundPeriod.setSelectedEmployeeList(selectedEmployeeList);
                    }
                    List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.queryRefundReceiptByRefundPeriodId(refundPeriod.getRefundPeriodId());
                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
                }
//                if (!NumberUtils.isZeroOrNull(refundPeriod.getReceiveCompanyId())) {
//                    log.debug(" ReceiveCompanyId not empty");
//                    List<SelectedEmployeeBean> selectedEmployeeList = selectedEmployeeExistsDao.getSelectedEmployee(refundPeriod.getRefundPeriodId());
//                    log.debug("Query SelectedEmployeeList Success");
//                    List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.searchReceiptExists(refundPeriod.getReceiveCompanyId());
//                    log.debug("Query SelectedReceiptList Success");
//                    refundPeriod.setSelectedEmployeeList(selectedEmployeeList);
//                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
//                    log.debug("setSelected 33");
//
//                } else if (!NumberUtils.isZeroOrNull(refundPeriod.getReceiveInsurerId())) {
//                    List<SelectedReceiptBean> selectedReceiptList = selectedInsurerExistsDao.queryRefundRequestReceipt(refundPeriod.getReceiveInsurerId());
//                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
//                    log.debug("setSelectedReceipt 39");
//                } else {
//                    List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.searchReceiptExistsMultiple(refundPeriod.getRefundPeriodId());
//                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
//                    log.debug("setSelectedReceiptMultiple");
//                }
                inactiveList.add(refundPeriod);
                log.debug("add inActive");
            }
        }
        bean.setRefundPeriodActiveList(activeList);
        bean.setRefundPeriodInactiveList(inactiveList);

        return bean;
    }

    private List<RefundPeriodActiveBean> searchRefundPeriod(Long refundRequestId) throws Exception {
        return conTbRefundPeriodDao.searchRefundPeriod(refundRequestId);
    }
}
