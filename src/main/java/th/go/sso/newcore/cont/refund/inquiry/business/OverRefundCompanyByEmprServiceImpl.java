package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.List;
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
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.WebServiceUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbAppraisal33DaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbRequesterCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.OverRefundCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundCompanyByEmprRequest;

@Service
public class OverRefundCompanyByEmprServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(OverRefundCompanyByEmprServiceImpl.class);

    @Autowired
    private TimerHelper timer;

    @Autowired
    private ConMsCompanyDaoImpl conMsCompanyDao;

    @Autowired
    private OverRefundCompanyDaoImpl refundCompanyDao;

    @Autowired
    private ConTbRequesterCompanyDaoImpl conTbRequesterCompanyDao;


    @Autowired
    private ConTbAppraisal33DaoImpl conTbAppraisal33Dao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ConMsCompanyBean searchCompany(SearchOverRefundCompanyByEmprRequest request) throws Exception {
        timer.start("searchCompany");
        String companyNo = request.getCompanyNo();
        String companyBranchCode = request.getCompanyBranchCode();
        String receiveNo = request.getReceiveNo();
        List<ConMsCompanyBean> companyList = conMsCompanyDao.queryCompanyList(companyNo, companyBranchCode, receiveNo);

        ConMsCompanyBean company = new ConMsCompanyBean();
        if (!CollectionUtils.isEmpty(companyList)) {
            ObjectUtils.copyIgnoreNull(company, companyList.get(0));
            log.info("company :");
            log.info(WebServiceUtils.toJson(company));
        }
        company.setDebt(conTbAppraisal33Dao.findDebt(companyNo, company.getCompanyBranchCode()));

        log.debug(timer.endToString("searchCompany"));
        return company;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OverRefundCompanyBean> searchRefundCompany(SearchOverRefundCompanyByEmprRequest request, Paginable paginable) throws Exception {
        timer.start("searchRefundCompany");

        Long companyId = request.getCompanyId();
        String companyNo = request.getCompanyNo();
        String companyBranchCode = request.getCompanyBranchCode();
        String receiveNo = request.getReceiveNo();
        String approveNo = request.getApproveNo();
        String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
        String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();
        String progressStatus = request.getProgressStatus();

        List<OverRefundCompanyBean>  periodList = refundCompanyDao.searchRefundCompany(companyId
                , companyNo
                , companyBranchCode
                , receiveNo
                , approveNo
                , startPeriod
                , endPeriod
                , progressStatus
                , paginable);

        for (OverRefundCompanyBean periodData : periodList) {
//            if (!StringUtils.isEmpty(periodData.getEligibleFlag()) && Objects.equals("T", periodData.getEligibleFlag())) {
                List<OrderBean> orderBean = conTbRequesterCompanyDao.queryOrderNoAndOrderStatus(periodData.getRefundRequestId()
                        , "S".equals(request.getProgressStatus()) ? "W" : request.getProgressStatus()
                        , request.getReceiveNo()
                        , periodData.getPayPeriod());
                orderBean = orderBean.stream().filter(x -> !StringUtils.isEmpty(x.getOrderNo())).distinct().collect(Collectors.toList());
                periodData.setOrderList(orderBean);
//            }
        }
        log.debug(timer.endToString("searchRefundCompany"));
        return periodList;
    }
}