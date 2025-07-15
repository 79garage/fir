package th.go.sso.newcore.cont.refund.inquiry.business;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ContributionInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequester39DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ContributionInsurerDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchContributionInsurerRequest;

@Service
public class ContributionInsurerServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(ContributionInsurerServiceImpl.class);

    @Autowired
    private ConMsInsurerDaoImpl conMsInsurerDao;
    @Autowired
    private ContributionInsurerDaoImpl contributionInsurerDao;
    @Autowired
    private ConTbRequester39DaoImpl conTbRequester39Dao;
    @Autowired
    private TimerHelper timer;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ConMsInsurerBean searchInsurer(Long insurerId, String receiveNo) throws Exception {
        return conMsInsurerDao.searchInsurer(insurerId,receiveNo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ContributionInsurerBean> searchContributionInsurerPeriod(SearchContributionInsurerRequest request) throws Exception {
        timer.start("searchContributionInsurerPeriod");
        Long insurerId = request.getInsurerId();
        String progressStatus = request.getProgressStatus();
        String startPeriod = request.getStartPeriodYear() + request.getEndPeriodMonth();
        String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();

        List<ContributionInsurerBean> periodList = contributionInsurerDao.searchConInsurer(insurerId, progressStatus, startPeriod, endPeriod, request.getReceiveNo());
        log.debug("periodList Total " + periodList.size());

        for (ContributionInsurerBean periodData : periodList) {
            BigDecimal cntrAmount = NumberUtils.convertZeroIfNull(periodData.getCntrAmount());
            BigDecimal interestPaid = NumberUtils.convertZeroIfNull(periodData.getInterestPaid());
            BigDecimal cntrInterest = (cntrAmount.add(interestPaid));
            periodData.setOrderList(conTbRequester39Dao.queryOrderNoAndOrderStatus(periodData.getRefundRequestId()
                    , "S".equals(request.getProgressStatus()) ? "W" : request.getProgressStatus()
                    , request.getReceiveNo()
                    , periodData.getPayPeriod()));
            periodData.setTotalContribution(cntrInterest);
        }
//            periodList.forEach(periodData -> {
//                BigDecimal cntrAmount = NumberUtils.convertZeroIfNull(periodData.getCntrAmount());
//                BigDecimal interestPaid = NumberUtils.convertZeroIfNull(periodData.getInterestPaid());
//                BigDecimal cntrInterest = (cntrAmount.add(interestPaid));
//                periodData.setTotalContribution(cntrInterest);
//            });

        log.debug(timer.endToString("searchContributionInsurerPeriod"));
        return periodList;
    }
}
