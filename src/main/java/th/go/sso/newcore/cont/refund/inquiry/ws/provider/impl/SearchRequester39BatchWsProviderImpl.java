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
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbRequesterInsurerServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequester39Request;

@Component
@RequestScope
public class SearchRequester39BatchWsProviderImpl extends WebServiceBase<SearchRequester39Request, SearchResponse> {

    @Autowired
    private ConTbRequesterInsurerServiceImpl conTbRequesterInsurerService;

    @Autowired
    public SearchRequester39BatchWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRequester39Request request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }

        return null;
    }

    @Override
    protected SearchResponse implement(SearchRequester39Request request) throws Exception {
        List<ConTbRequesterBean> conTbRequester39List = conTbRequesterInsurerService.searchRequester39Batch(request.getPaginable()
                , getUser()
                , request.getRefundRequestIds()
                , request.getRequesterType());
        SearchResponse response = new SearchResponse();
        response.setResult(conTbRequester39List);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
