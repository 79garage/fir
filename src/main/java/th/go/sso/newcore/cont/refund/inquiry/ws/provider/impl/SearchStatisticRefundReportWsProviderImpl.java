package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReportBean;
import th.go.sso.newcore.cont.refund.inquiry.business.StatisticRefundReportServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchStatisticRefundReportRequest;

@Component
@RequestScope
public class SearchStatisticRefundReportWsProviderImpl extends WebServiceBase<SearchStatisticRefundReportRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(SearchStatisticRefundReportWsProviderImpl.class);

    @Autowired
    private StatisticRefundReportServiceImpl staticRefundReportService;

    public SearchStatisticRefundReportWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchStatisticRefundReportRequest request) throws Exception {
        return null;
    }

    @Override
    protected ResponseBean implement(SearchStatisticRefundReportRequest request) throws Exception {
        try {
            List<RefundReportBean> reports = staticRefundReportService.findRefundReport();

            ResponseBean response = new ResponseBean();
            response.setResult(reports);
            response.setStatus(ResponseStatus.SUCCESS);

            return response;
        } catch (Exception ex) {
            log.error("SearchStaticRefundReportWsProviderImpl", ex);
            return getResponseMessageByCode("BE0001");
        }
    }
}
