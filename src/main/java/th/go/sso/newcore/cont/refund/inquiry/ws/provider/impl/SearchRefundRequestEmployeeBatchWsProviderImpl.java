package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestEmployeeBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbRefundRequestServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundRequestBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchRefundRequestBatchResponse;

@Component
@RequestScope
public class SearchRefundRequestEmployeeBatchWsProviderImpl extends WebServiceBase<SearchRefundRequestBatchRequest, SearchRefundRequestBatchResponse> {

    @Autowired
    private ConTbRefundRequestServiceImpl conTbRefundRequestService;

    @Autowired
    public SearchRefundRequestEmployeeBatchWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRefundRequestBatchRequest request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }

        return null;
    }

    @Override
    protected SearchRefundRequestBatchResponse implement(SearchRefundRequestBatchRequest request) throws Exception {
        List<RefundRequestEmployeeBatchBean> refundRequestEmployeeBatchList = conTbRefundRequestService.searchRefundEmployeeRequestBatch(request.getPaginable(), getUser(), request.getRefundRequestIds());
        SearchRefundRequestBatchResponse response = new SearchRefundRequestBatchResponse();
        response.setResult(refundRequestEmployeeBatchList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
