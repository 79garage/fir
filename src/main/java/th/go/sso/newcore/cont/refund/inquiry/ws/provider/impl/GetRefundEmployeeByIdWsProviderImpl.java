package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.business.GetRefundEmployeeByIdServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundEmployeeByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.GetRefundEmployeeByIdResponse;

@Component
@RequestScope
public class GetRefundEmployeeByIdWsProviderImpl extends WebServiceBase<GetRefundEmployeeByIdRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(GetRefundEmployeeByIdWsProviderImpl.class);

    @Autowired
    private GetRefundEmployeeByIdServiceImpl getRefundEmployeeByIdService;

    public GetRefundEmployeeByIdWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(GetRefundEmployeeByIdRequest request) throws Exception {
        if (NumberUtils.isZeroOrNull(request.getRefundRequestId())) {
            return getResponseMessageByCode("BE0000", "RefundRequestId");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(GetRefundEmployeeByIdRequest request) throws Exception {
        ResponseBean response = new ResponseBean(ResponseStatus.SUCCESS);
        try {
            SplitEmployeeRefundCompanyPeriodBean employeeBean = getRefundEmployeeByIdService.employeeSplitDetails(request.getRefundRequestId());

            GetRefundEmployeeByIdResponse _response = new GetRefundEmployeeByIdResponse();
            _response.setEmployeeDetails(employeeBean);

            response.setResult(_response);

        } catch (BusinessException be) {
            log.debug("BusinessException: {}", be.getMessage());
            response.setErrorMsg(be.getMessage());
            response.setStatus(ResponseStatus.BUSINESS_ERROR);
        } catch (Exception e) {
            log.error("GetRefundEmployeeByIdWsProviderImpl error : ", e);
            return getResponseMessageByCode("BE0001");
        }
        return response;
    }
}
