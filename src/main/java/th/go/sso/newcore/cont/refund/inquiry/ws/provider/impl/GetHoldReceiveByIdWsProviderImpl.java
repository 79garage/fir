package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;
import th.go.sso.newcore.cont.refund.inquiry.business.TransactionRefundInsurerServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetHoldReceiveByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.GetHoldReceiveByIdResponse;

@Component
@RequestScope
public class GetHoldReceiveByIdWsProviderImpl extends WebServiceBase<GetHoldReceiveByIdRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(GetHoldReceiveByIdWsProviderImpl.class);

    @Autowired
    private TransactionRefundInsurerServiceImpl transactionRefundInsurerService;

    public GetHoldReceiveByIdWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(GetHoldReceiveByIdRequest request) throws Exception {
        if (request == null) {
            return getResponseMessageByCode("BE0012");
        }
        if (CollectionUtils.isEmpty(request.getHoldReceiveId())) {
            return getResponseMessageByCode("BE0000", "HoldReceiveIds");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(GetHoldReceiveByIdRequest request) throws Exception {
        ResponseBean response = new ResponseBean();
        try {
            RefundInsurerBean holdReceiveInfo = transactionRefundInsurerService.searchHoldReceiveInfo(request.getHoldReceiveId());
//            if (!ObjectUtils.isObjectNull(holdReceiveInfo)) {
                List<RefundInsurerPeriodListBean> holdReceivePeriods = transactionRefundInsurerService.searchHoldReceivePeriods(request.getHoldReceiveId());

                GetHoldReceiveByIdResponse _response = new GetHoldReceiveByIdResponse();
                _response.setRefundDetails(holdReceiveInfo);
                _response.setRefundPeriodList(holdReceivePeriods);

                response.setStatus(ResponseStatus.SUCCESS);
                response.setResult(_response);
//            }
            return response;
        }  catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            log.error("GetHoldReceiveByIdWsProviderImpl error : ", e);
            response.setStatus(ResponseStatus.BUSINESS_ERROR);
            response.setErrorMsg(getResponseMessageByCode("BE0001").getErrorMsg());
            return response;
        }
    }
}
