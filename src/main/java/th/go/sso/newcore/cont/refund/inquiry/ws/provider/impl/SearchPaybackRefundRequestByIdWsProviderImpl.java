package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.business.TransactionPaybackRequestByIdServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackRequestByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchPaybackRequestByIdResponse;

@Component
@RequestScope
public class SearchPaybackRefundRequestByIdWsProviderImpl extends WebServiceBase<SearchPaybackRequestByIdRequest, ResponseBean> {

    private static final Logger log = LoggerFactory.getLogger(SearchOverRefundInsurerWsProviderImpl.class);
    @Autowired
    private TransactionPaybackRequestByIdServiceImpl transactionPaybackRequestByIdService;

    public SearchPaybackRefundRequestByIdWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchPaybackRequestByIdRequest request) throws Exception {
        if (request == null) {
            return getResponseMessageByCode("BS0001");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchPaybackRequestByIdRequest request) throws Exception {
        try {

            ResponseBean response = new ResponseBean();
            SearchPaybackRequestByIdResponse paybackResponse = new SearchPaybackRequestByIdResponse();
            ConTbPaybackRequestBean conTbPaybackRequestBean = transactionPaybackRequestByIdService.getPaybackRequestById(request.getPaybackRequestId(), getUser());

            paybackResponse.setPaybackRequestDetail(conTbPaybackRequestBean);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setResult(paybackResponse);
            return response;

        } catch (Exception e) {
            log.error("Search Refund Insurer ", e);
            return getResponseMessageByCode("BE0001");
        }

    }

}
