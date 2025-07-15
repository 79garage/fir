package th.go.sso.newcore.cont.refund.inquiry.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiptInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiveHoldReceiveBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTrReceiveInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInsurerDetailByIdRequest;

@Service
public class RefundInsurerRequestServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(RefundInsurerRequestServiceImpl.class);

    @Autowired
    private ConMsInsurerDaoImpl conMsInsurerDao;

    @Autowired
    private ConTrReceiveInsurerDaoImpl conTrReceiveInsurerDao;

    @Autowired
    private RefundInsurerSelectedReceiptDaoImpl refundInsurerSelectedReceiptDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RefundInsurerBean searchInsurerDetails(SearchRefundInsurerDetailByIdRequest request) throws Exception {
        return conMsInsurerDao.searchRefundInsurerInfo(request.getInsurerId());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundInsurerPeriodListBean> searchInsurerRefundPeriods(SearchRefundInsurerDetailByIdRequest request, String idCardNo, String accessToken) throws Exception {
        List<RefundInsurerPeriodListBean> receiveList = new ArrayList<>();
        for (RefundReceiveHoldReceiveBean receiveId : request.getReceiveIdList()) {
            RefundInsurerPeriodListBean  receiveData = new RefundInsurerPeriodListBean();
            if (Objects.equals("1", receiveId.getSection())) {
                Long receiveInsurerId = receiveId.getReceiveId();
                receiveData = findReceiveInsurer(receiveInsurerId, idCardNo, request.getMode());
            }
//            else if (Objects.equals("7", receiveId.getSection())) {
//                Long holdReceiveId = receiveId.getReceiveId();
//                receiveData = findHoldReceiveInsurer(holdReceiveId, idCardNo);
//                //TODO : Unable to find rate because no insurerId at hold receive
//            }
//            RateInformationBean rate = findRate(request.getInsurerId(), receiveData.getPayPeriod(), accessToken);
//            mapRate(receiveData, rate);
            receiveList.add(receiveData);
//
//            receiveData.setRefundSumRate(CalculateUtils.sum(List.of(NumberUtils.convertZeroIfNull(receiveData.getRefundDeathRate())
//                    , NumberUtils.convertZeroIfNull(receiveData.getRefundInvalidityRate())
//                    , NumberUtils.convertZeroIfNull(receiveData.getRefundMaternityRate())
//                    , NumberUtils.convertZeroIfNull(receiveData.getRefundRetirementRate())
//                    , NumberUtils.convertZeroIfNull(receiveData.getRefundSicknessRate())
//                    , NumberUtils.convertZeroIfNull(receiveData.getRefundChildAllowanceRate())
//                    , NumberUtils.convertZeroIfNull(receiveData.getRefundUnemploymentRate()))));
        }
        return receiveList;
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

    private RefundInsurerPeriodListBean findReceiveInsurer(Long receiveInsurerId, String idCardNo, String mode) throws Exception {
//        RefundInsurerPeriodListBean receiveData = conTrReceiveInsurerDao.findReceiveInsurerPeriods(receiveInsurerId);
        RefundInsurerPeriodListBean receiveData = new RefundInsurerPeriodListBean();
        if (Objects.equals("over", mode)) {
            receiveData = conTrReceiveInsurerDao.findReceiveInsurerPeriods(receiveInsurerId);
        } else if (Objects.equals("cntr", mode)) {
            receiveData = conTrReceiveInsurerDao.findReceiveInsurerContributionPeriod(receiveInsurerId);
        }
        if (ObjectUtils.isObjectNull(receiveData)) {
            throw new BusinessException("not found period");
        }
        calculateOverAmountAndOverInterest(receiveData);
        findReceiptFromReceive(receiveData);
        return receiveData;
    }

//    private RefundInsurerPeriodListBean findHoldReceiveInsurer(Long holdReceiveId, String idCardNo) throws Exception {
//        RefundInsurerPeriodListBean receiveData = conTbHoldReceiveDao.findHoldReceivePeriod(idCardNo, holdReceiveId);
//        if (ObjectUtils.isObjectNull(receiveData)) {
//            throw new BusinessException("not found period");
//        }
//        findReceiptFromHoldReceive(receiveData);
//        return receiveData;
//    }

//    private void findReceiptFromHoldReceive(RefundInsurerPeriodListBean receiveData) throws Exception {
//        List<RefundReceiptInsurerBean> receipts = refundInsurerSelectedReceiptDao.findReceiptHoldReceive(receiveData.getReceiveInsurerId());
//        log.debug("find receipts : {}", receipts.size());
////        List<SelectedReceiptBean> RefundRequestReceiptList = refundInsurerSelectedRefundRequestReceiptDao.queryRefundRequestReceiptHoldReceive(receiveList.getReceiveInsurerId());
////        log.debug("find data in con_tb_receipt where data exists in con_tb_refund_request_receipt ");
//        receiveData.setReceiptList(receipts);
//        receiveData.setSelectedReceiptList(new ArrayList<>());
//    }

    private void findReceiptFromReceive(RefundInsurerPeriodListBean receiveData) throws Exception {
        List<RefundReceiptInsurerBean> receipts = refundInsurerSelectedReceiptDao.findReceiptReceiveInsurer(receiveData.getReceiveInsurerId());
        log.debug("find receipts : {}", receipts.size());
//        List<SelectedReceiptBean> RefundRequestReceiptList = refundInsurerSelectedRefundRequestReceiptDao.queryRefundRequestReceipt(receiveList.getReceiveInsurerId());
//        log.debug("find data in con_tb_receipt where data exists in con_tb_refund_request_receipt ");
        receiveData.setReceiptList(receipts);
        receiveData.setSelectedReceiptList(new ArrayList<>());
    }

    private void calculateOverAmountAndOverInterest(RefundInsurerPeriodListBean receiveData) {
        if (NumberUtils.isZeroOrNull(receiveData.getRefundRequestId())) {
            BigDecimal calOverPaid = BigDecimal.ZERO;
            BigDecimal calOverInterestPaid = BigDecimal.ZERO;
            if ("02".equals(receiveData.getRefundReasonCode()) || "03".equals(receiveData.getRefundReasonCode()) || "04".equals(receiveData.getRefundReasonCode())
                    || "05".equals(receiveData.getRefundReasonCode()) || "07".equals(receiveData.getRefundReasonCode()) || "08".equals(receiveData.getRefundReasonCode())
                    || "10".equals(receiveData.getRefundReasonCode()) || "11".equals(receiveData.getRefundReasonCode())) {
                //ส่งไม่ครบ9ใน12, ขาดส่ง3เดือน, กลับเป็นม.33, จ่ายก่อนวันที่มีผล, เสียชีวิต, ไม่มีคุณสมบัติ, จ่ายอัตราผิด, จากถังพัก77, ลาออก
                calOverPaid = calRefundTotal(receiveData.getCntrAmount(), receiveData.getRefundAmount());
                calOverInterestPaid = calRefundTotal(receiveData.getInterestPaid(), receiveData.getRefundInterestAmount());
                receiveData.setRequestEmpeAmount(calOverPaid);
                receiveData.setRequestInterestAmount(calOverInterestPaid);
                receiveData.setRequestTotalAmount(calOverPaid.add(calOverInterestPaid));
//                    } else if ("01".equals(receiveList.getRefundReasonCode())) {
//                        //จ่ายซ้ำ
//                        calOverPaid = receiveList.getCntrAmount().subtract(conTbReceiptDao.findTotalAmountByReceiveInsurerId(receiveList.getReceiveInsurerId()));
//                        calOverInterestPaid = receiveList.getInterestPaid().subtract(conTbReceiptDao.findInterestPaidByReceiveInsurerId(receiveList.getReceiveInsurerId()));
//                        receiveList.setRequestEmpeAmount(calOverPaid);
//                        receiveList.setRequestInterestAmount(calOverInterestPaid);
//                        receiveList.setRequestTotalAmount(calOverPaid);
            } else if ("06".equals(receiveData.getRefundReasonCode()) || "01".equals(receiveData.getRefundReasonCode()) || "09".equals(receiveData.getRefundReasonCode())) {
                //จ่ายเกิน, จ่ายซ้ำ
                calOverPaid = calRefundTotal(receiveData.getRequestEmpeAmount(), receiveData.getRefundAmount());
                calOverInterestPaid = calRefundTotal(receiveData.getRequestInterestAmount(), receiveData.getRefundInterestAmount());
                receiveData.setRequestEmpeAmount(calOverPaid);
                receiveData.setRequestInterestAmount(calOverInterestPaid);
                receiveData.setRequestTotalAmount(calOverPaid.add(calOverInterestPaid));
            } else {
                calOverPaid = calRefundTotal(receiveData.getCntrAmount(), receiveData.getRefundAmount());
                calOverInterestPaid = calRefundTotal(receiveData.getInterestPaid(), receiveData.getRefundInterestAmount());
                receiveData.setRequestEmpeAmount(calOverPaid);
                receiveData.setRequestInterestAmount(calOverInterestPaid);
                receiveData.setRequestTotalAmount(calOverPaid.add(calOverInterestPaid));
            }
        }
    }

    private BigDecimal calRefundTotal(BigDecimal amount, BigDecimal refundAmount) {
        amount = NumberUtils.convertZeroIfNull(amount);
        refundAmount = NumberUtils.convertZeroIfNull(refundAmount);
        return amount.subtract(refundAmount);
    }

}
