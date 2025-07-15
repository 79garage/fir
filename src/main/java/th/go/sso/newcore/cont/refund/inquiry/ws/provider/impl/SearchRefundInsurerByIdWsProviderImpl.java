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
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInsurerByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchRefundInsurerByIdResponse;

@Component
@RequestScope
public class SearchRefundInsurerByIdWsProviderImpl extends WebServiceBase<SearchRefundInsurerByIdRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(SearchRefundInsurerByIdWsProviderImpl.class);

    @Autowired
    private RefundInsurerByIdServiceImpl refundInsurerByIdService;

    public SearchRefundInsurerByIdWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRefundInsurerByIdRequest request) throws Exception {
        if (request == null) {
            return getResponseMessageByCode("BE0000", "Request");
        }
        if (NumberUtils.isZeroOrNull(request.getRefundRequestId())) {
            return getResponseMessageByCode("BE0000", "Refund Request Id");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchRefundInsurerByIdRequest request) throws Exception {
        try {
            RefundInsurerBean refundDetail = refundInsurerByIdService.searchRefundDetails(request.getRefundRequestId());
            if (ObjectUtils.isObjectNull(refundDetail)) {
                throw new BusinessException("not found refund request");
            }
            List<RefundInsurerPeriodListBean> refundPeriods = refundInsurerByIdService.searchRefundPeriods(request.getRefundRequestId()
                    , refundDetail.getInsurerId()
                    , getUser().getAccessToken());
            if (CollectionUtils.isEmpty(refundPeriods)) {
                throw new BusinessException("not found refund periods");
            }

            ButtonTypeBean buttonBean = refundInsurerByIdService.setTypeButton(refundDetail, refundPeriods, "R");

            SearchRefundInsurerByIdResponse result = new SearchRefundInsurerByIdResponse();
            result.setRefundInsurerRequestDetail(refundDetail);
            result.setRefundInsurerPeriodList(refundPeriods);
            result.setEditType(buttonBean);

            ResponseBean response = new ResponseBean();
            response.setResult(result);
            response.setStatus(ResponseStatus.SUCCESS);
            return response;
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            log.error("SearchRefundInsurerByIdWsProviderImpl error : ", e);
            return getResponseMessageByCode("BE0001");
        }

//        ResponseBean response = new ResponseBean();
//        try {
//            SearchRefundInsurerByIdResponse refundInsurerList = refundInsurerByIdService.getInsurerByRefundRequestId(request, getUser().getAccessToken());
//            if (ObjectUtils.isObjectEmpty(refundInsurerList)) {
//                response = getResponseMessageByCode("BS0003");
//            } else {
//                response.setResult(refundInsurerList);
//                response.setStatus(ResponseStatus.SUCCESS);
//            }
//        }catch (BusinessException be){
//            response.setErrorMsg(be.getMessage());
//            response.setStatus(ResponseStatus.ERROR);
//        } catch (Exception e) {
//            return getResponseMessageByCode("BE0001");
//        }
//        return response;
    }
}
