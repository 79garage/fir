package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ReceiveCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequesterCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTrReceiveCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchContributionCompanyByEmprRequest;

@Service
public class ContributionCompanyByEmprServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(ContributionCompanyByEmprServiceImpl.class);
    @Autowired
    private TimerHelper timer;
    @Autowired
    private ConMsCompanyDaoImpl conMsCompanyDao;
    @Autowired
    private ConTrReceiveCompanyDaoImpl conTrReceiveCompanyDao;
    @Autowired
    private ConTbRequesterCompanyDaoImpl conTbRequesterCompanyDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ConMsCompanyBean searchCompany(SearchContributionCompanyByEmprRequest request) throws Exception {
        timer.start("searchCompany");
        String companyNo = request.getCompanyNo();
        String companyBranchCode = request.getCompanyBranchCode();
        String receiveNo = request.getReceiveNo();
        List<ConMsCompanyBean> companyList = conMsCompanyDao.queryCompanyList(companyNo, companyBranchCode, receiveNo);
        ConMsCompanyBean company = new ConMsCompanyBean();
        if (!CollectionUtils.isEmpty(companyList)) {
            ObjectUtils.copyIgnoreNull(company, companyList.get(0));
//            if (companyList.size() > 1) {
//                company.setCompanyBranchCode(null);
//                company.setCompanyBranchName(null);
//                company.setCompanyBranchDescription(null);
//            }
        }
        log.debug(timer.endToString("searchCompany"));
        return company;
//        log.debug("searchConMsCompany");
//        Long companyId = request.getCompanyId();
//        String receiveNo = request.getReceiveNo();
//        String approveNo = request.getApproveNo();
//        String companyBranchCode = request.getCompanyBranchCode();
//
//        return conMsCompanyDao.searchCompany(companyId, companyBranchCode, receiveNo, approveNo);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ReceiveCompanyBean> searchReceiveCompany(SearchContributionCompanyByEmprRequest request) throws Exception {

        Long companyId = request.getCompanyId();
        String companyNo = request.getCompanyNo();
        String receiveNo = request.getReceiveNo();
        String companyBranchCode = request.getCompanyBranchCode();
        String progressStatus = request.getProgressStatus();
        String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
        String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();

        List<ReceiveCompanyBean> periodList = conTrReceiveCompanyDao.searchReceiveCompany(companyId, companyNo, receiveNo, companyBranchCode, startPeriod, endPeriod, progressStatus);
        for (ReceiveCompanyBean periodData : periodList) {
            List<OrderBean> orderBean = conTbRequesterCompanyDao.queryOrderNoAndOrderStatus(periodData.getRefundRequestId()
                    , "S".equals(request.getProgressStatus()) ? "W" : request.getProgressStatus()
                    , request.getReceiveNo()
                    , periodData.getPayPeriod());
            orderBean = orderBean.stream().filter(x -> !StringUtils.isEmpty(x.getOrderNo())).distinct().collect(Collectors.toList());
            periodData.setOrderList(orderBean);
        }
        log.debug("searchReceiveCompany");

        return periodList;
    }
}
