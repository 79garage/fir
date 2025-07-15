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
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbPaybackRequestServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackInsurerBatchRequest;

@Component
@RequestScope
public class SearchPaybackInsurerBatchWsProviderImpl extends WebServiceBase<SearchPaybackInsurerBatchRequest, SearchResponse> {
    @Autowired
    private ConTbPaybackRequestServiceImpl conTbPaybackRequestService;
    @Autowired
    public SearchPaybackInsurerBatchWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchPaybackInsurerBatchRequest request) throws Exception {
        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }
        return null;
    }

    @Override
    protected SearchResponse implement(SearchPaybackInsurerBatchRequest request) throws Exception {
        List<ConTbPaybackBean> paybackInsurerList = conTbPaybackRequestService.searchPaybackInsurerBatch(request.getCreateBy()
                , request.getCreateDate()
                , request.getPaginable()
                , getUser()
                , request.getPaybackRequestIds());
        SearchResponse response = new SearchResponse();
        response.setResult(paybackInsurerList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
