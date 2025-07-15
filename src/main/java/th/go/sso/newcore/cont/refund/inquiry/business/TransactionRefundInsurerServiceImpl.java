package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHoldReceiveBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInfoBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiptInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbHoldReceiveDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRefundRequestDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester39DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverHoldReceiveRequest;

import static th.go.sso.newcore.cont.refund.inquiry.constant.ConstantsCode.PENDING_APPROVAL;
import static th.go.sso.newcore.cont.refund.inquiry.constant.ConstantsCode.WAITING_PAYMENT;

@Service
public class TransactionRefundInsurerServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(TransactionRefundInsurerServiceImpl.class);

    @Autowired
    private ConTbHoldReceiveDaoImpl conTbHoldReceiveDao;

    @Autowired
    private ConTbRefundRequestDaoImpl conTbRefundRequestDao;

    @Autowired
    private RefundInsurerSelectedReceiptDaoImpl refundInsurerSelectedReceiptDao;

    @Autowired
    private ConTbRequester39DaoImpl conTbRequester39Dao;

    @Transactional
    public RefundInfoBean searchRefundInsurer(SearchOverHoldReceiveRequest request) throws Exception {
        log.info("Start searchRefundInsurer");
        RefundInfoBean bean = new RefundInfoBean();
        if (!StringUtils.isEmpty(request.getReceiveNo())) {
            bean = conTbRefundRequestDao.findRefundInfoDetail(request.getReceiveNo());
            log.info("find hold receive info by receiveNo: {}", request.getReceiveNo());
            return bean;
        }

        bean = conTbHoldReceiveDao.findRefundInfoDetail(request.getReceiptNo()
                , request.getIdCardNo()
                , request.getFullName());
        if (ObjectUtils.isObjectNull(bean)) {
            throw new BusinessException("ไม่พบข้อมูลถังพัก");
        }
        log.info("find hold receive info by receiptNo: {}, idCardNo: {}, fullName: {}", request.getReceiptNo(), request.getIdCardNo(), request.getFullName());

        log.info("End searchRefundInsurer");
        return bean;
    }

    @Transactional
    public List<RefundHoldReceiveBean> searchHoldReceiveList(SearchOverHoldReceiveRequest request, Paginable paginable) throws Exception {
        log.info("Start searchHoldReceiveList");

        List<RefundHoldReceiveBean> beans = conTbRefundRequestDao.findRefundRequestHoldReceive(request);
        for (RefundHoldReceiveBean period : beans) {
            List<OrderBean> orderBean = conTbRequester39Dao.queryOrderNoAndOrderStatus(period.getRefundRequestId()
                    , WAITING_PAYMENT.equals(request.getProgressStatus()) ? PENDING_APPROVAL : request.getProgressStatus()
                    , request.getReceiveNo()
                    , period.getPayPeriod());
            orderBean = orderBean.stream().filter(x -> !StringUtils.isEmpty(x.getOrderNo())).distinct().collect(Collectors.toList());
            period.setOrderList(!CollectionUtils.isEmpty(orderBean) ? orderBean : new ArrayList<>());
        }

        log.info("End searchHoldReceiveList");
        return !CollectionUtils.isEmpty(beans) ? beans : new ArrayList<>();
    }

    @Transactional
    public RefundInsurerBean searchHoldReceiveInfo(List<Long> holdReceiveId) throws Exception {
//        log.info("Start searchHoldReceiveInfo");
//
//        RefundInsurerBean bean = conTbHoldReceiveDao.findHoldReceiveInfo(holdReceiveId);
//        if (ObjectUtils.isObjectNull(bean)) {
//            log.info("Not Found Hold Receive Info by HoldReceiveId: {}", holdReceiveId);
//            throw new BusinessException("ไม่พบข้อมูลถังพัก");
//        }
//
//        log.info("End searchHoldReceiveInfo");
//        return bean;
        return null;
    }

    @Transactional
    public List<RefundInsurerPeriodListBean> searchHoldReceivePeriods(List<Long> holdReceiveId) throws Exception {
        log.info("Start searchHoldReceivePeriods");

        List<RefundInsurerPeriodListBean> beans = conTbHoldReceiveDao.findHoldReceivePeriodList(holdReceiveId);
        if (ObjectUtils.isObjectNull(beans)) {
            log.info("Not Found Hold Receive Period by HoldReceiveId: {}", holdReceiveId);
            throw new BusinessException("ไม่พบข้อมูลถังพัก");
        }
        for (RefundInsurerPeriodListBean period : beans) {
            List<RefundReceiptInsurerBean> receipts = refundInsurerSelectedReceiptDao.findReceiptHoldReceive(period.getReceiveInsurerId());
            log.debug("find receipts : {}", receipts.size());
//        List<SelectedReceiptBean> RefundRequestReceiptList = refundInsurerSelectedRefundRequestReceiptDao.queryRefundRequestReceiptHoldReceive(receiveList.getReceiveInsurerId());
//        log.debug("find data in con_tb_receipt where data exists in con_tb_refund_request_receipt ");
            period.setReceiptList(receipts);
            period.setSelectedReceiptList(new ArrayList<>());
        }
        log.info("End searchHoldReceivePeriods");
        return beans;
    }
}
