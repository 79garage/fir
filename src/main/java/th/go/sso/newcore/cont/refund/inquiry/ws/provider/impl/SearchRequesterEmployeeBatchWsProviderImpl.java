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
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterEmployeeBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbRequesterEmployeeServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequesterEmployeeBatchRequest;

@Component
@RequestScope
public class SearchRequesterEmployeeBatchWsProviderImpl extends WebServiceBase<SearchRequesterEmployeeBatchRequest, SearchResponse> {

    @Autowired
    private ConTbRequesterEmployeeServiceImpl conTbRequesterEmployeeService;

    @Autowired
    public SearchRequesterEmployeeBatchWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRequesterEmployeeBatchRequest request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }

        return null;
    }

    @Override
    protected SearchResponse implement(SearchRequesterEmployeeBatchRequest request) throws Exception {
        List<RequesterEmployeeBatchBean> requesterEmployeeBatchList = conTbRequesterEmployeeService.searchRequestEmployeeBatch(request.getCreateBy()
                , request.getCreateDate(), request.getPaginable(), getUser(), request.getRequesterIds());
        SearchResponse response = new SearchResponse();
        response.setResult(requesterEmployeeBatchList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
