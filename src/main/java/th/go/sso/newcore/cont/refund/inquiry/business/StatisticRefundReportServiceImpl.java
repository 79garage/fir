package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReportBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbStatisticRefundReportDaoImpl;

@Service
public class StatisticRefundReportServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(StatisticRefundReportServiceImpl.class);

    @Autowired
    private TimerHelper timer;

    @Autowired
    private ConTbStatisticRefundReportDaoImpl conTbStaticRefundReportDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundReportBean> findRefundReport() throws Exception {
        timer.start("findRefundReport");

        List<RefundReportBean> reportBeforeOneMonths = conTbStaticRefundReportDao.findRefundRequestBeforeOneMonth();
        log.info("reportBeforeOneMonths count : {}", reportBeforeOneMonths.size());
        List<RefundReportBean> reportCurrents = conTbStaticRefundReportDao.findRefundRequestCurrentMonth();
        log.info("reportCurrents count : {}", reportCurrents.size());

        List<RefundReportBean> reportBeans = new ArrayList<>();
        reportBeans.addAll(reportCurrents);
        reportBeans.addAll(reportBeforeOneMonths);
        log.info("count data reports : {}", reportBeans.size());

        timer.endToString("findRefundReport");
        return reportBeans;
    }
}
