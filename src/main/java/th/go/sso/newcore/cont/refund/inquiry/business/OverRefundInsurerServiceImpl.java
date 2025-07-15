package th.go.sso.newcore.cont.refund.inquiry.business;

import java.math.BigDecimal;
import java.math.MathContext;
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

import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbAppraisal39DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester39DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.OverRefundInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInsurerRequest;

@Service
public class OverRefundInsurerServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(OverRefundInsurerServiceImpl.class);
    @Autowired
    private TimerHelper timer;

    @Autowired
    private ConMsInsurerDaoImpl conMsInsurerDao;

    @Autowired
    private OverRefundInsurerDaoImpl overRefundInsurerDao;

    @Autowired
    private ConTbRequester39DaoImpl conTbRequester39Dao;

    @Autowired
    private ConTbAppraisal39DaoImpl conTbAppraisal39Dao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ConMsInsurerBean searchInsurer(SearchRefundInsurerRequest request) throws Exception {
        ConMsInsurerBean insurer = conMsInsurerDao.searchInsurer(request.getInsurerId(),request.getReceiveNo());
        if (ObjectUtils.isObjectNull(insurer)) {
            return insurer;
        }
        int countDebt = conTbAppraisal39Dao.findDebt(request.getInsurerId());
        insurer.setDebt(!NumberUtils.isZeroOrNull(countDebt));
        return insurer;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OverRefundInsurerBean> searchRefundInsurerPeriod(SearchRefundInsurerRequest request, Paginable paginable) throws Exception {
        timer.start("searchRefundInsurerPeriod");
        String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
        String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();

        List<OverRefundInsurerBean> periodList = overRefundInsurerDao.queryRefundInsurerPeriod(request.getInsurerId()
                , request.getProgressStatus()
                , startPeriod, endPeriod
                , request.getReceiveNo()
                , paginable);
        log.debug("periodList Total : {}", CollectionUtils.isEmpty(periodList) ? null : periodList.size());

        for (OverRefundInsurerBean periodData : periodList) {
            BigDecimal cntrAmount = NumberUtils.convertZeroIfNull(periodData.getCntrAmount());
            BigDecimal interestPaid = NumberUtils.convertZeroIfNull(periodData.getInterestPaid());
            BigDecimal empeOverp = NumberUtils.convertZeroIfNull(periodData.getEmpeOverp());
            BigDecimal overInterestPaid = NumberUtils.convertZeroIfNull(periodData.getOverInterestPaid());

//            if (!NumberUtils.isZeroOrNull(periodData.getRefundRequestId()) || !StringUtils.isEmpty(request.getReceiveNo())) {
                List<OrderBean> orderBean = conTbRequester39Dao.queryOrderNoAndOrderStatus(periodData.getRefundRequestId()
                        , "S".equals(request.getProgressStatus()) ? "W" : request.getProgressStatus()
                        , request.getReceiveNo()
                        , periodData.getPayPeriod());
                orderBean = orderBean.stream().filter(x -> !StringUtils.isEmpty(x.getOrderNo())).distinct().collect(Collectors.toList());
                periodData.setOrderList(!CollectionUtils.isEmpty(orderBean) ? orderBean : new ArrayList<>());
//            }

            periodData.setTotalContribution(cntrAmount.add(interestPaid));

            MathContext m = new MathContext(0); // 0 precision
            BigDecimal totalOverp = (empeOverp.add(overInterestPaid)).round(m);
            periodData.setTotalOver(totalOverp);
            
            if (NumberUtils.isZeroOrNull(periodData.getRefundRequestId())) {
                if ("02".equals(periodData.getRefundReasonCode()) || "03".equals(periodData.getRefundReasonCode()) || "04".equals(periodData.getRefundReasonCode())
                        || "05".equals(periodData.getRefundReasonCode()) || "07".equals(periodData.getRefundReasonCode()) || "08".equals(periodData.getRefundReasonCode())
                        || "11".equals(periodData.getRefundReasonCode())) {
                    //ส่งไม่ครบ9ใน12, ขาดส่ง3เดือน, กลับเป็นม.33, จ่ายก่อนวันที่มีผล, เสียชีวิต, ไม่มีคุณสมบัติ, จากถังพัก77, ลาออก
                    periodData.setEmpeOverp(cntrAmount.subtract(NumberUtils.convertZeroIfNull(periodData.getRefundAmount())));
                    periodData.setOverInterestPaid(interestPaid.subtract(NumberUtils.convertZeroIfNull(periodData.getRefundInterestAmount())));
//                        periodData.setOverInterestPaid(overInterestPaid.subtract(NumberUtils.convertZeroIfNull(periodData.getRefundInterestAmount())));
                    periodData.setTotalOverPaid(cntrAmount.subtract(NumberUtils.convertZeroIfNull(periodData.getRefundAmount())));
//                    } else if ("01".equals(periodData.getRefundReasonCode())) {
//                        //จ่ายซ้ำ
//                        BigDecimal calTotalAmount = conTbReceiptDao.findTotalAmountByReceiveInsurerId(periodData.getReceiveInsurerId());
//                        BigDecimal calInterestPaid = conTbReceiptDao.findInterestPaidByReceiveInsurerId(periodData.getReceiveInsurerId());
//                        periodData.setEmpeOverp(cntrAmount.subtract(NumberUtils.convertZeroIfNull(calTotalAmount)));
//                        periodData.setOverInterestPaid(overInterestPaid.subtract(NumberUtils.convertZeroIfNull(calInterestPaid)));
//                        periodData.setTotalOverPaid(cntrAmount.subtract(NumberUtils.convertZeroIfNull(calTotalAmount)));
                } else if ("06".equals(periodData.getRefundReasonCode()) || "01".equals(periodData.getRefundReasonCode()) || "09".equals(periodData.getRefundReasonCode())) {
                    //จ่ายเกิน, จ่ายซ้ำ, จ่ายอัตราผิด
                    periodData.setRefundReasonCode(Objects.equals("09", periodData.getRefundReasonCode()) ? "06" : periodData.getRefundReasonCode());
                    periodData.setEmpeOverp(empeOverp.subtract(NumberUtils.convertZeroIfNull(periodData.getRefundAmount())));
                    periodData.setOverInterestPaid(overInterestPaid.subtract(NumberUtils.convertZeroIfNull(periodData.getRefundInterestAmount())));
                    periodData.setTotalOverPaid(empeOverp.subtract(NumberUtils.convertZeroIfNull(periodData.getRefundAmount())));
                }
            }
        }
        log.debug(timer.endToString("searchRefundInsurerPeriod"));
        return periodList;

    }
}
