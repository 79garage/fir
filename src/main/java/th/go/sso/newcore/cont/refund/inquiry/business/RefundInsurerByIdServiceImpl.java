package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import th.go.sso.newcore.cont.refund.inquiry.bean.ButtonTypeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBranchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiptInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsBankDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRefundRequestDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester39DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTrRefundInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerRequestDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedRefundRequestReceiptDaoImpl;

@Service
public class RefundInsurerByIdServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(RefundInsurerByIdServiceImpl.class);

    @Autowired
    private RefundInsurerDaoImpl refundInsurerDao;

    @Autowired
    private RefundInsurerRequestDaoImpl refundInsurerRequestDao;

    @Autowired
    private RefundInsurerSelectedReceiptDaoImpl refundInsurerSelectedReceiptDao;

    @Autowired
    private RefundInsurerSelectedRefundRequestReceiptDaoImpl refundInsurerSelectedRefundRequestReceiptDao;

    @Autowired
    private ConMsBankDaoImpl conMsBankDao;


    @Autowired
    private ConTbRequester39DaoImpl conTbRequester39Dao;

    @Autowired
    private ConTrRefundInsurerDaoImpl conTrRefundInsurerDao;


    @Autowired
    private ConTbRefundRequestDaoImpl conTbRefundRequestDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RefundInsurerBean searchRefundDetails(Long refundRequestId) throws Exception {
        RefundInsurerBean refundBean = refundInsurerDao.searchRefundRequest(refundRequestId);
        if (ObjectUtils.isObjectNull(refundBean)){
            throw new BusinessException("ไม่พบข้อมูลใบคำขอ");
        }
        getRefundPlaceTypeDetails(refundBean);

        backStepRefundByProgressStatus(refundBean);

        List<ConTbRequesterBean> requester39EligibleList = conTbRequester39Dao.queryConTbRequester39(refundRequestId);
        requester39EligibleList.forEach(list -> list.setRefundPromptPay(list.getPromptPay()));
        refundBean.setRequesterEligibleList(requester39EligibleList);

        List<ConTbRequesterBean> requester39List = conTbRequester39Dao.queryRequester39InRequesterList(refundRequestId);
        requester39List.forEach(list -> list.setRefundPromptPay(list.getPromptPay()));
        refundBean.setRequesterList(requester39List);

        getOrderStatus(refundBean, requester39List, refundRequestId);

        return refundBean;
    }

    /*
    สร้างคำสั่งจ่าย แก้ไขการพิจารฯา ยกเลิกใบคำขอ
    1. ไม่เห็นทุกอัน
    2. เห็นทุกอัน
    3. ไม่เห็น สั่งจ่าย
    4. ไม่อนุมัติ แก้ไขได้
    5. เห็นเฉพาะ สั่งจ่าย ถ้าเป็นเคส สร้างอีกใบคำขอมา
    */
//    refundType : H = HoldReceive, R = ReceiveInsurer
    public ButtonTypeBean setTypeButton(RefundInsurerBean bean, List<RefundInsurerPeriodListBean> periods, String refundType) throws Exception {
        ButtonTypeBean buttonBean = new ButtonTypeBean();
//        Date currentDate = DateTimeUtils.getEndDate(DateTimeUtils.getDateTime());
//        Date approveDate = null;
//        if (bean.getApproveDate() != null) {
//            approveDate = DateTimeUtils.getEndDate(bean.getApproveDate());
//        }
        List<Long> receiveInsurerIds = periods.stream().map(RefundInsurerPeriodListBean::getReceiveInsurerId).collect(Collectors.toList());
        String receiveNo = conTbRefundRequestDao.findReceiveNoByReceiveInsurerIds(receiveInsurerIds);

        if (!StringUtils.isEmpty(bean.getOrderStatus())
                || (!StringUtils.isEmpty(bean.getProgressStatus()) && List.of("C", "D").contains(bean.getProgressStatus()))) {
//        if ((bean.getApproveDate() != null && !Objects.equals(currentDate, approveDate))
//                || !StringUtils.isEmpty(bean.getOrderStatus())
//                || (!StringUtils.isEmpty(bean.getProgressStatus()) && List.of("C", "D").contains(bean.getProgressStatus()))) {
            buttonBean.setEdit(false);
            buttonBean.setCancel(false);
            buttonBean.setSendRequest(false);
//        } else if (!StringUtils.isEmpty(bean.getRefundTypePlace()) && Objects.equals("4" ,bean.getRefundTypePlace())) {
//            buttonBean.setEdit(true);
//            buttonBean.setCancel(true);
//            buttonBean.setSendRequest(false);
        } else if (!StringUtils.isEmpty(receiveNo) && !StringUtils.isEmpty(bean.getReceiveNo()) && !Objects.equals(receiveNo, bean.getReceiveNo()) && Objects.equals("R", refundType)) {
            buttonBean.setEdit(false);
            buttonBean.setCancel(false);
            buttonBean.setSendRequest(true);
        } else if (Objects.equals("0", bean.getApproveStatus()) && Objects.equals("0", bean.getProgressStatus())) {
            buttonBean.setEdit(true);
            buttonBean.setCancel(false);
            buttonBean.setSendRequest(false);
        } else {
            buttonBean.setEdit(true);
            buttonBean.setCancel(true);
            buttonBean.setSendRequest(true);
        }
        return buttonBean;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundInsurerPeriodListBean> searchRefundPeriods(Long refundRequestId, Long insurerId, String accessToken) throws Exception {
        List<RefundInsurerPeriodListBean> periods = refundInsurerRequestDao.findRefundInsurerPeriods(refundRequestId);

        for (RefundInsurerPeriodListBean period : periods) {

            List<RefundReceiptInsurerBean> receiptList = new ArrayList<RefundReceiptInsurerBean>();
            List<SelectedReceiptBean> refundRequestReceiptList = new ArrayList<SelectedReceiptBean>();

            if (!NumberUtils.isZeroOrNull(period.getReceiveInsurerId())) {
                Long receiveInsurerId = period.getReceiveInsurerId();
                period.setReceiveInsurerId(receiveInsurerId);
                receiptList = refundInsurerSelectedReceiptDao.findReceiptReceiveInsurer(receiveInsurerId);
                refundRequestReceiptList = refundInsurerSelectedRefundRequestReceiptDao.findRefundRequestReceipt(period.getRefundPeriodId());
            }
//            else if (!NumberUtils.isZeroOrNull(period.getHoldReceiveId())) {
//                Long holdReceiveId = period.getHoldReceiveId();
//                period.setHoldReceiveId(holdReceiveId);
//                receiptList = refundInsurerSelectedReceiptDao.findReceiptHoldReceive(holdReceiveId);
//                refundRequestReceiptList = refundInsurerSelectedRefundRequestReceiptDao.findRefundRequestReceiptHoldReceive(holdReceiveId);
//            }
//            if (NumberUtils.isZeroOrNull(period.getRefundSicknessRate())
//                    && NumberUtils.isZeroOrNull(period.getRefundRetirementRate())
//                    && NumberUtils.isZeroOrNull(period.getRefundUnemploymentRate())) {
//                RateInformationBean rate = findRate(insurerId, period.getPayPeriod(), accessToken);
//                mapRate(period, rate);
//            }
            period.setRefundSumRate(CalculateUtils.sum(List.of(NumberUtils.convertZeroIfNull(period.getRefundDeathRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundInvalidityRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundMaternityRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundRetirementRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundSicknessRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundChildAllowanceRate())
                    , NumberUtils.convertZeroIfNull(period.getRefundUnemploymentRate()))));

            period.setReceiptList(receiptList);
            period.setSelectedReceiptList(refundRequestReceiptList);
        }
        return periods;
    }

    private void backStepRefundByProgressStatus(RefundInsurerBean refundBean) {
        if ("W".equals(refundBean.getProgressStatus())) {
            refundBean.setRefundSicknessAmount(null);
            refundBean.setRefundRetirementAmount(null);
//                refundInsurerBean.setRefundInterestAmount(null);
            }
    }

    private void getRefundPlaceTypeDetails(RefundInsurerBean refundBean) throws Exception {
        if (!StringUtils.isEmpty(refundBean.getRefundTypePlace())) {
            if (List.of("4", "6").contains(refundBean.getRefundTypePlace())
                    && !NumberUtils.isZeroOrNull(refundBean.getRefundBankId())) {
                ConMsBankBean bank = conMsBankDao.searchBank(refundBean.getRefundBankId());
                refundBean.setRefundBankCode(bank.getBankCode());
                if (!NumberUtils.isZeroOrNull(refundBean.getRefundBankBranchId())) {
                    ConMsBankBranchBean bankBranch = conMsBankDao.searchBankBranch(refundBean.getRefundBankBranchId());
                    refundBean.setRefundBankBranchCode(bankBranch.getBankBranchCode());
                }
            }
        }
    }

    private void getOrderStatus(RefundInsurerBean refundBean, List<ConTbRequesterBean> requester39List, Long refundRequestId) throws Exception {
        if (!CollectionUtils.isEmpty(requester39List)) {
            String checkProgressStatus = conTrRefundInsurerDao.findProgressStatusByRefundRequestId(refundRequestId);
            if ("P".equals(checkProgressStatus)){
                refundBean.setOrderStatus(checkProgressStatus);
            }else {
                for (ConTbRequesterBean requester39 : requester39List) {
                    if ("P".equals(requester39.getOrderStatus())) {
                        refundBean.setOrderStatus(requester39.getOrderStatus());
                        refundBean.setOrderRemark(requester39.getOrderRemark());
                    } else if (!"P".equals(requester39.getOrderStatus()) && !"P".equals(refundBean.getOrderStatus())) {
                        refundBean.setOrderStatus(requester39.getOrderStatus());
                        refundBean.setOrderRemark(requester39.getOrderRemark());
                    }
                }
            }
        }
    }

//    private RateInformationBean findRate(Long insurerId, String payPeriod, String accessToken) throws Exception {
//        return calRateService.getRateInsurer(insurerId
//                , payPeriod
//                , null
//                , accessToken);
//    }
//
//    private void mapRate(RefundInsurerPeriodListBean receiveData, RateInformationBean rate) {
//        receiveData.setRefundSicknessRate(rate.getSickness());
//        receiveData.setRefundRetirementRate(rate.getRetirement());
//        receiveData.setRefundUnemploymentRate(rate.getUnemployment());
//        receiveData.setRefundMaternityRate(rate.getMaternity());
//        receiveData.setRefundInvalidityRate(rate.getInvalidity());
//        receiveData.setRefundChildAllowanceRate(rate.getChildAllowance());
//        receiveData.setRefundDeathRate(rate.getDeath());
//    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public RefundInsurerBean searchRefundHoldReceiveDetails(Long refundRequestId) throws Exception {
        log.info("Start searchRefundInfo hold receive");
        RefundInsurerBean refundBean = refundInsurerDao.findRefundRequestInsurer(refundRequestId);
        if (ObjectUtils.isObjectNull(refundBean)){
            throw new BusinessException("ไม่พบข้อมูลใบคำขอ");
        }
        getRefundPlaceTypeDetails(refundBean);

        backStepRefundByProgressStatus(refundBean);

        List<ConTbRequesterBean> requester39List = conTbRequester39Dao.queryRequester39InRequesterList(refundRequestId);
        requester39List.forEach(list -> list.setRefundPromptPay(list.getPromptPay()));
        refundBean.setRequesterList(requester39List);

        getOrderStatus(refundBean, requester39List, refundRequestId);

        log.info("End searchRefundInfo hold receive");
        return refundBean;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundInsurerPeriodListBean> searchRefundHoldReceivePeriods(Long refundRequestId, String progressStatus) throws Exception {
        log.info("Start searchRefundHoldReceivePeriods hold receive");
        List<RefundInsurerPeriodListBean> periods = refundInsurerRequestDao.findRefundInsurerHoldReceivePeriods(refundRequestId);

        for (RefundInsurerPeriodListBean period : periods) {

            if (!NumberUtils.isZeroOrNull(period.getHoldReceiveId())) {
                Long holdReceiveId = period.getHoldReceiveId();
                period.setHoldReceiveId(holdReceiveId);
                List<RefundReceiptInsurerBean> receipts = refundInsurerSelectedReceiptDao.findReceiptHoldReceive(holdReceiveId);
                List<SelectedReceiptBean> refundRequestReceipts = refundInsurerSelectedRefundRequestReceiptDao.findRefundRequestReceiptHoldReceive(holdReceiveId);

//                if (!StringUtils.isEmpty(progressStatus) && List.of("W").contains(progressStatus)) {
//                    RateHoldReceiveBean rate = conTbHoldReceiveDao.findRateHoldReceive(period.getHoldReceiveId());
//                    log.info("hold receive rate: {} ", WebServiceUtils.toJson(rate));
//
//                    period.setRefundSicknessAmount(rate.getRefundSickness());
//                    period.setRefundRetirementAmount(rate.getRefundRetirement());
//                    period.setRefundSicknessRate(rate.getRefundSicknessRate());
//                    period.setRefundRetirementRate(rate.getRefundRetirementRate());
//                    period.setRefundSumRate(rate.getRefundCntrRate());
//                }
                period.setReceiptList(receipts);
                period.setSelectedReceiptList(refundRequestReceipts);
            }
        }
        log.info("End searchRefundHoldReceivePeriods hold receive");
        return periods;
    }
}
