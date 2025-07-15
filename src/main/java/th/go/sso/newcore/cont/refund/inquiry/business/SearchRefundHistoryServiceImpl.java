package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHistoryBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRefundRequestChangeTypeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundHistoryRequest;

@Service
public class SearchRefundHistoryServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(SearchRefundHistoryServiceImpl.class);

    @Autowired
    private ConTbRefundRequestChangeTypeDaoImpl conTbRefundRequestChangeTypeDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundHistoryBean> getRefundHistory(SearchRefundHistoryRequest request, Paginable paginable) throws Exception {
        log.debug("START getRefundHistory receiveNo: {}", request.getReceiveNo());
        String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
        String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();
        return conTbRefundRequestChangeTypeDao.queryRefundHistory(request, startPeriod, endPeriod, paginable);
    }
}
