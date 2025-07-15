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
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterCompanyBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbRequesterCompanyServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequesterCompanyBatchRequest;

@Component
@RequestScope
public class SearchRequesterCompanyBatchWsProviderImpl extends WebServiceBase<SearchRequesterCompanyBatchRequest, SearchResponse> {

    @Autowired
    private ConTbRequesterCompanyServiceImpl conTbRequesterCompanyService;

    @Autowired
    public SearchRequesterCompanyBatchWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRequesterCompanyBatchRequest request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }

        return null;
    }

    @Override
    protected SearchResponse implement(SearchRequesterCompanyBatchRequest request) throws Exception {
        List<RequesterCompanyBatchBean> requesterCompanyBatchList = conTbRequesterCompanyService.searchRequestCompanyBatch(request.getCreateBy()
                , request.getCreateDate(), request.getPaginable(), getUser(), request.getRequesterIds());
        SearchResponse response = new SearchResponse();
        response.setResult(requesterCompanyBatchList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
