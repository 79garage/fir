package th.go.sso.newcore.cont.refund.inquiry.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.dto.IUserInfo;
import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.PaybackRefundPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbPaybackRequestDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.PaybackRequestDaoImpl;

@Service
public class TransactionPaybackRequestByIdServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(TransactionPaybackRequestByIdServiceImpl.class);

    @Autowired
    private PaybackRequestDaoImpl paybackRequestDao;
    @Autowired
    private ConTbPaybackRequestDaoImpl conTbPaybackRequestDao;
    @Autowired
    private TimerHelper timer;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ConTbPaybackRequestBean getPaybackRequestById(Long paybackRequestId, IUserInfo user) throws Exception {

        timer.start("getPaybackRequestById");
        log.debug("Start getPaybackRequestById id: {} by user: {} ", paybackRequestId, user);

        ConTbPaybackRequestBean paybackRequest = paybackRequestDao.getConTbPaybackRequestById(paybackRequestId);
        if(paybackRequest == null){
            throw new RuntimeException("ไม่พบข้อมูล");
        }
        String separateSection = Objects.equals("0", paybackRequest.getSection()) && !NumberUtils.isZeroOrNull(paybackRequest.getCompanyId()) && NumberUtils.isZeroOrNull(paybackRequest.getInsurerId())
                ? "0"
                : (Objects.equals("0", paybackRequest.getSection()) && !NumberUtils.isZeroOrNull(paybackRequest.getInsurerId())) || Objects.equals("3", paybackRequest.getSection())
                ? "3"
                : Objects.equals("1", paybackRequest.getSection())
                ? "1"
                : null
                ;
//        paybackRequest.setOrderList(conTbPaybackRequestDao.queryOrderNoPayback(paybackRequest.getPaybackRequestId()));
        paybackRequest.setOrderList(conTbPaybackRequestDao.queryOrderByRefundRequestId(paybackRequest.getRefundRequestId(), separateSection));

        List<PaybackRefundPeriodBean> paybackRefundPeriodBeanList = paybackRequestDao.getRefundPeriodByRefundRequestId(paybackRequest.getPaybackRequestId());
        paybackRequest.setRefundPeriodList(paybackRefundPeriodBeanList);
        //sum refund total amount from payback period list
//        paybackRequest.setRefundTotalAmount(totalRefundAmount(paybackRefundPeriodBeanList));

        log.debug("End getPaybackRequestById id {} by user: {} found result: {} periodList: {} ", paybackRequestId, user, paybackRequest != null, paybackRefundPeriodBeanList != null ? paybackRefundPeriodBeanList.size() : 0);

        log.debug(timer.endToString("getPaybackRequestById"));
        return paybackRequest;
    }

    public BigDecimal totalRefundAmount(List<PaybackRefundPeriodBean> periods) {
        return periods.stream()
                .map(PaybackRefundPeriodBean::getRefundTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
