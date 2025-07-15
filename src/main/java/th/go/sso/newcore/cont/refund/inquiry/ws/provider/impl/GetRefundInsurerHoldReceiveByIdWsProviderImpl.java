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
import th.go.sso.newcore.cont.refund.inquiry.bean.ButtonTypeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;
import th.go.sso.newcore.cont.refund.inquiry.business.RefundInsurerByIdServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundInsurerHoldReceiveByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.GetRefundInsurerHoldReceiveByIdResponse;

@Component
@RequestScope
public class GetRefundInsurerHoldReceiveByIdWsProviderImpl extends WebServiceBase<GetRefundInsurerHoldReceiveByIdRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(GetRefundInsurerHoldReceiveByIdWsProviderImpl.class);

    @Autowired
    private RefundInsurerByIdServiceImpl refundInsurerByIdService;

    public GetRefundInsurerHoldReceiveByIdWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(GetRefundInsurerHoldReceiveByIdRequest request) throws Exception {
        if (request == null) {
            return getResponseMessageByCode("BE0000", "Request");
        }
        if (NumberUtils.isZeroOrNull(request.getRefundRequestId())) {
            return getResponseMessageByCode("BE0000", "Refund Request Id");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(GetRefundInsurerHoldReceiveByIdRequest request) throws Exception {
        try {
            RefundInsurerBean refundDetail = refundInsurerByIdService.searchRefundHoldReceiveDetails(request.getRefundRequestId());
            if (ObjectUtils.isObjectNull(refundDetail)) {
                throw new BusinessException("not found refund request");
            }
            List<RefundInsurerPeriodListBean> refundPeriods = refundInsurerByIdService.searchRefundHoldReceivePeriods(request.getRefundRequestId()
                    , refundDetail.getProgressStatus());
            if (CollectionUtils.isEmpty(refundPeriods)) {
                throw new BusinessException("not found refund periods");
            }

            ButtonTypeBean buttonBean = refundInsurerByIdService.setTypeButton(refundDetail, refundPeriods, "H");

            GetRefundInsurerHoldReceiveByIdResponse _response = new GetRefundInsurerHoldReceiveByIdResponse();
            _response.setRefundDetails(refundDetail);
            _response.setRefundPeriodList(refundPeriods);
            _response.setEditType(buttonBean);

            ResponseBean response = new ResponseBean();
            response.setResult(_response);
            response.setStatus(ResponseStatus.SUCCESS);
            return response;
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            log.error("GetRefundInsurerHoldReceiveByIdWsProviderImpl error : ", e);
            return getResponseMessageByCode("BE0001");
        }
    }
}
