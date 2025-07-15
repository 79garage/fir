package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.dto.SearchResponse;
import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.business.SearchRefundHistoryByIdServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundHistoryByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchRefundHistoryByIdResponse;

@Component
@RequestScope
public class SearchRefundHistoryByIdWsProviderImpl extends WebServiceBase<SearchRefundHistoryByIdRequest, ResponseBean> {
    private final static Logger log = LoggerFactory.getLogger(SearchRefundHistoryByIdWsProviderImpl.class);

    @Autowired
    private SearchRefundHistoryByIdServiceImpl searchRefundHistoryByIdService;

    public SearchRefundHistoryByIdWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRefundHistoryByIdRequest request) throws Exception {
        if (request == null){
            return getResponseMessageByCode("BS0002");
        }
        if (NumberUtils.isZeroOrNull(request.getRefundRequestChangeTypeId())) {
            return getResponseMessageByCode("BE0000","refundRequestChangeTypeId");
        }
        if (NumberUtils.isZeroOrNull(request.getRefundRequestId()) && StringUtils.isEmpty(request.getSourceCode())) {
            return getResponseMessageByCode("BE0000","refundRequestId");
        }
        if (StringUtils.isEmpty(request.getRefundChangeType())) {
            return getResponseMessageByCode("BE0000","refundChangeType");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchRefundHistoryByIdRequest request) throws Exception {
        SearchResponse response = new SearchResponse();
        try {
            RefundRequestBean refundDetail = searchRefundHistoryByIdService.getRefundDetail(request.getRefundRequestId(), request.getRefundRequestChangeTypeId());
            if (ObjectUtils.isObjectNull(refundDetail) && StringUtils.isEmpty(request.getSourceCode())) {
                throw new BusinessException("ไม่พบใบคำขอ");
            }
            SearchRefundHistoryByIdResponse historyResponse = new SearchRefundHistoryByIdResponse();
            historyResponse = searchRefundHistoryByIdService.getRefundHistoryById(request);
            historyResponse.setRefundRequestDetail(refundDetail);
            response.setResult(historyResponse);
        } catch (BusinessException exception) {
            log.debug("BusinessException:: {}", exception.getMessage());
            response.setStatus(ResponseStatus.BUSINESS_ERROR);
            response.setErrorMsg(exception.getMessage());
        } catch (Exception error) {
            log.error("Exception implement::", error);
            response.setStatus(ResponseStatus.ERROR);
            response.setErrorMsg(getResponseMessageByCode("BE0001").getErrorMsg());
        }
        return response;
    }
}
