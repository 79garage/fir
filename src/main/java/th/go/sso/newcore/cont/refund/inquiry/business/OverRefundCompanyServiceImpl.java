package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.ArrayList;
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
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequesterCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.OverRefundCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundCompanyByEmprRequest;

@Service
public class OverRefundCompanyServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(OverRefundCompanyServiceImpl.class);

    @Autowired
    private TimerHelper timer;

    @Autowired
    private OverRefundCompanyDaoImpl overRefundCompanyDao;

    @Autowired
    private ConTbRequesterCompanyDaoImpl conTbRequesterCompanyDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OverRefundCompanyPeriodBean> searchRefundCompanyPeriods(SearchOverRefundCompanyByEmprRequest request, Paginable paginable) throws Exception{
        timer.start("searchRefundCompanyPeriods");
        List<OverRefundCompanyPeriodBean> periods = new ArrayList<OverRefundCompanyPeriodBean>();
        if (!NumberUtils.isZeroOrNull(request.getCompanyId()) && !StringUtils.isEmpty(request.getProgressStatus())) {
            String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
            String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();

            periods = overRefundCompanyDao.queryRefundCompanyPeriod(request.getCompanyId()
                    , request.getProgressStatus()
                    , request.getDepCode()
                    , startPeriod
                    , endPeriod
                    , paginable);
            log.debug("find by idCardNo periods total: {}", CollectionUtils.isEmpty(periods) ? 0 : periods.size());

        } else if (!StringUtils.isEmpty(request.getReceiveNo())) {
            periods = overRefundCompanyDao.queryRefundCompanyPeriodByReceive(request.getReceiveNo());
            log.debug("find by receiveNo periods total: {}", CollectionUtils.isEmpty(periods) ? 0 : periods.size());
        }
        for (OverRefundCompanyPeriodBean period : periods) {
            if ("N".equals(period.getProgressStatus()) || "W".equals(period.getProgressStatus())) {
                period.setRefundTotalAmount(null);
            }
            List<OrderBean> orderList = new ArrayList<>();
            orderList = findRequester(period, request.getReceiveNo());
            period.setOrderList(orderList);
        }
        timer.endToString("searchRefundCompanyPeriods");
        return periods;
    }

    private List<OrderBean> findRequester(OverRefundCompanyPeriodBean period, String receiveNo) throws Exception {
        return conTbRequesterCompanyDao.queryOrderNoAndOrderStatus(period.getRefundRequestId()
                , period.getProgressStatus()
                , receiveNo
                , period.getRefundPeriod());
    }
}
