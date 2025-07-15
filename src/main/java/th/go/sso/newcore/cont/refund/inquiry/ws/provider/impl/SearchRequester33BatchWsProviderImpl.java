package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.dto.SearchResponse;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbRequesterEmployeeServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequester33Request;

@Component
@RequestScope
public class SearchRequester33BatchWsProviderImpl extends WebServiceBase<SearchRequester33Request, SearchResponse> {

    @Autowired
    private ConTbRequesterEmployeeServiceImpl conTbRequesterEmployeeService;

    @Autowired
    public SearchRequester33BatchWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRequester33Request request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }

        return null;
    }

    @Override
    protected SearchResponse implement(SearchRequester33Request request) throws Exception {
        List<ConTbRequesterBean> conTbRequester33List = conTbRequesterEmployeeService.searchRequester33Batch(request.getPaginable()
                , getUser()
                , request.getRefundRequestIds());
        SearchResponse response = new SearchResponse();
        response.setResult(conTbRequester33List);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
