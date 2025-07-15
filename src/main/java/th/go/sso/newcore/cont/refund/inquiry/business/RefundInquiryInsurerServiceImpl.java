package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInquiryInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester33DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester39DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInquiryInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInquiryInsurerRequest;

@Service
public class RefundInquiryInsurerServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(RefundInquiryInsurerServiceImpl.class);

    @Autowired
    private TimerHelper timer;

    @Autowired
    private RefundInquiryInsurerDaoImpl refundInquiryInsurerDao;

    @Autowired
    private ConTbRequester33DaoImpl conTbRequester33Dao;

    @Autowired
    private ConTbRequester39DaoImpl conTbRequester39Dao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundInquiryInsurerBean> searchRefundInsurerPeriods(SearchRefundInquiryInsurerRequest request, Paginable paginable) throws Exception {
        timer.start("searchRefundInsurerPeriods");
        List<RefundInquiryInsurerBean> periods = new ArrayList<RefundInquiryInsurerBean>();
        if (!StringUtils.isEmpty(request.getIdCardNo()) && !StringUtils.isEmpty(request.getSection()) && !StringUtils.isEmpty(request.getProgressStatus())) {
            String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
            String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();

            periods = refundInquiryInsurerDao.searchRefundInquiryInsurer(request.getIdCardNo()
                    , request.getSection()
                    , request.getDepCode()
                    , request.getProgressStatus()
                    , startPeriod
                    , endPeriod
                    , paginable);
            log.debug("find by idCardNo periods total: {}", CollectionUtils.isEmpty(periods) ? 0 : periods.size());
        } else if (!StringUtils.isEmpty(request.getReceiveNo())) {
            String receiveNo = request.getReceiveNo();
//                String sectionReceiveNo = request.getReceiveNo().substring(6, 10);

            periods = refundInquiryInsurerDao.searchRefundInquiryInsurerByReceive(receiveNo, null, request.getSection());
            log.debug("find by receiveNo periods total: {}", CollectionUtils.isEmpty(periods) ? 0 : periods.size());
        }
        for (RefundInquiryInsurerBean period : periods) {
            if ("N".equals(period.getProgressStatus()) || "W".equals(period.getProgressStatus())) {
                period.setRefundTotalAmount(null);
            }
            List<OrderBean> orderList = new ArrayList<>();
            orderList = findRequester(period, request.getReceiveNo());
            period.setOrderList(orderList);
        }
        timer.endToString("searchRefundInsurerPeriods");
        return periods;

    }

    private List<OrderBean> findRequester(RefundInquiryInsurerBean period, String receiveNo) throws Exception {
        return Objects.equals("1", period.getSection())
                ? conTbRequester39Dao.queryOrderNoAndOrderStatus(period.getRefundRequestId(), period.getProgressStatus(), receiveNo, period.getRefundPeriod())
                : conTbRequester33Dao.queryOrderNoAndOrderStatus(period.getRefundRequestId(), period.getProgressStatus(), receiveNo, period.getRefundPeriod());
    }
}
