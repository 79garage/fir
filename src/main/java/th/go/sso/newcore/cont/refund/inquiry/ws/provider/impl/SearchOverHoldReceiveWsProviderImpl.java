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
import th.go.sso.newcore.cont.common.dto.SearchResponse;
import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHoldReceiveBean;
import th.go.sso.newcore.cont.refund.inquiry.business.TransactionRefundInsurerServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverHoldReceiveRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchOverHoldReceiveResponse;

@Component
@RequestScope
public class SearchOverHoldReceiveWsProviderImpl extends WebServiceBase<SearchOverHoldReceiveRequest, SearchResponse> {
    private static final Logger log = LoggerFactory.getLogger(SearchOverHoldReceiveWsProviderImpl.class);

    @Autowired
    private TransactionRefundInsurerServiceImpl transactionRefundInsurerService;

    public SearchOverHoldReceiveWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchOverHoldReceiveRequest request) throws Exception {
        if (request == null) {
            return getResponseMessageByCode("BE0012");
        }
        if (!StringUtils.isEmpty(request.getReceiveNo())) {
            request.setReceiveNo(request.getReceiveNo().toUpperCase());
            request.setStartPeriodMonth(null);
            request.setStartPeriodYear(null);
            request.setEndPeriodMonth(null);
            request.setEndPeriodYear(null);
            return null;
        }
//        else if (StringUtils.isEmpty(request.getReceiptNo()) && StringUtils.isEmpty(request.getFullName()) && StringUtils.isEmpty(request.getIdCardNo())) {
//            return getResponseMessageByCode("BE0000", "เลขที่ใบเสร็จ เลขที่บัตรประชาชน หรือชื่อ - นามสกุล");
//        }
        if (StringUtils.isEmpty(request.getProgressStatus())) {
            return getResponseMessageByCode("BE0000", "Progress Status");
        }
        if (StringUtils.isEmpty(request.getStartPeriodMonth())) {
            return getResponseMessageByCode("BE0000", "Start Period Month");
        }
        if (StringUtils.isEmpty(request.getStartPeriodYear())) {
            return getResponseMessageByCode("BE0000", "Start Period Year");
        }
        if (StringUtils.isEmpty(request.getEndPeriodMonth())) {
            return getResponseMessageByCode("BE0000", "End Period Month");
        }
        if (StringUtils.isEmpty(request.getEndPeriodYear())) {
            return getResponseMessageByCode("BE0000", "End Period Year");
        }
        return null;
    }

    @Override
    protected SearchResponse implement(SearchOverHoldReceiveRequest request) throws Exception {
        SearchResponse response = new SearchResponse();
        try {
//            RefundInfoBean insurerInfo = transactionRefundInsurerService.searchRefundInsurer(request);
//            if (!StringUtils.isEmpty(request.getReceiveNo()) && ObjectUtils.isObjectNull(insurerInfo)) {
//                response.setStatus(ResponseStatus.BUSINESS_ERROR);
//                response.setErrorMsg(getResponseMessageByCode("BS0010", request.getReceiveNo()).getErrorMsg());
//                return response;
//            }
//            if (!ObjectUtils.isObjectNull(insurerInfo)) {
                List<RefundHoldReceiveBean> holdReceiveList = transactionRefundInsurerService.searchHoldReceiveList(request
                        , !ObjectUtils.isObjectNull(request.getPaginable()) ? request.getPaginable() : null);

                SearchOverHoldReceiveResponse _response = new SearchOverHoldReceiveResponse();
//                _response.setRefundDetails(insurerInfo);
                _response.setRefundPeriodList(holdReceiveList);

                response.setStatus(ResponseStatus.SUCCESS);
                response.setResult(_response);
//            }
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            log.error("SearchOverHoldReceiveWsProviderImpl error : ", e);
            response.setStatus(ResponseStatus.BUSINESS_ERROR);
            response.setErrorMsg(getResponseMessageByCode("BE0001").getErrorMsg());
            return response;
        }
        return response;
    }
}
