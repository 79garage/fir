package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.GetByIdRequest;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbPaybackRequestServiceImpl;

@Component
@RequestScope
public class GetPaybackRefundRequestByIdWsProviderImpl extends WebServiceBase<GetByIdRequest, ResponseBean> {

    @Autowired
    private ConTbPaybackRequestServiceImpl conTbPaybackRequestService;

    @Autowired
    public GetPaybackRefundRequestByIdWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(GetByIdRequest request) throws Exception {

//        ResponseBean response = new ResponseBean();

        if (request == null || (request != null && request.getId() == null)) {
            return new ResponseBean(ResponseStatus.INVALID);
        }

        return null;

    }

    @Override
    protected ResponseBean implement(GetByIdRequest request) throws Exception {
        ResponseBean response = new ResponseBean();
        ConTbPaybackRequestBean conTbPaybackRequestBean = conTbPaybackRequestService.getPaybackRequestById(request.getId(), getUser());
        response.setStatus(ResponseStatus.SUCCESS);
        response.setResult(conTbPaybackRequestBean);
        return response;
    }
}
