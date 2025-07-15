package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundPeriodActiveAndInactiveBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.business.RefundDeleteCancelHistByIdServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.RefundDeleteCancelHistoryByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.RefundDeleteCancelHistoryByIdResponse;

@Component
@RequestScope
public class RefundDeleteCancelHistByIdWsProviderImpl extends WebServiceBase<RefundDeleteCancelHistoryByIdRequest, ResponseBean> {

    @Autowired
    private RefundDeleteCancelHistByIdServiceImpl refundDeleteCancelHistByIdService;

    public RefundDeleteCancelHistByIdWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(RefundDeleteCancelHistoryByIdRequest request) throws Exception {
        return null;
    }

    @Override
    protected ResponseBean implement(RefundDeleteCancelHistoryByIdRequest request) throws Exception {
        ResponseBean response = new ResponseBean();
        RefundDeleteCancelHistoryByIdResponse res = new RefundDeleteCancelHistoryByIdResponse();
        RefundRequestBean refundRequest = refundDeleteCancelHistByIdService.searchRefundRequestDetail(request.getRefundRequestId());
        RefundPeriodActiveAndInactiveBean activeAndInactive = refundDeleteCancelHistByIdService.mapRefundPeriodActiveAndInactive(request.getRefundRequestId());

        res.setRefundRequestDetail(refundRequest);
        res.setRefundPeriodActiveList(activeAndInactive.getRefundPeriodActiveList());
        res.setRefundPeriodInactiveList(activeAndInactive.getRefundPeriodInactiveList());
        response.setResult(res);

        return response;
    }
}
