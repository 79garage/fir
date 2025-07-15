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
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterInsurerBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbRequesterInsurerServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequesterInsurerBatchRequest;

@Component
@RequestScope
public class SearchRequesterInsurerBatchWsProviderImpl extends WebServiceBase<SearchRequesterInsurerBatchRequest, SearchResponse> {

    @Autowired
    private ConTbRequesterInsurerServiceImpl conTbRequesterInsurerService;

    @Autowired
    public SearchRequesterInsurerBatchWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRequesterInsurerBatchRequest request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }

        return null;
    }

    @Override
    protected SearchResponse implement(SearchRequesterInsurerBatchRequest request) throws Exception {
        List<RequesterInsurerBatchBean> requesterInsurerBatchList = conTbRequesterInsurerService.searchRequestInsurerBatch(request.getCreateBy()
                , request.getCreateDate(), request.getPaginable(), getUser(), request.getRequesterIds());
        SearchResponse response = new SearchResponse();
        response.setResult(requesterInsurerBatchList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
