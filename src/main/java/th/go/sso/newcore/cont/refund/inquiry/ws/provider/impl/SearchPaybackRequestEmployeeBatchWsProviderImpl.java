package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbPaybackRequestServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackRequestEmployeeBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchPaybackRequestCompanyBatchResponse;

@Component
@RequestScope
public class SearchPaybackRequestEmployeeBatchWsProviderImpl extends WebServiceBase<SearchPaybackRequestEmployeeBatchRequest, ResponseBean> {
    @Autowired
    public SearchPaybackRequestEmployeeBatchWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }
    @Autowired
    private ConTbPaybackRequestServiceImpl conTbPaybackRequestService;

    @Override
    protected ResponseBean validate(SearchPaybackRequestEmployeeBatchRequest request) throws Exception {
        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchPaybackRequestEmployeeBatchRequest request) throws Exception {
        List<ConTbPaybackBean> paybackRequestEmployeeBatchList = conTbPaybackRequestService.searchPaybackRequestEmployeeBatch(request.getPaginable(), getUser(), request.getPaybackRequestIds());
        SearchPaybackRequestCompanyBatchResponse response = new SearchPaybackRequestCompanyBatchResponse();
        response.setResult(paybackRequestEmployeeBatchList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
