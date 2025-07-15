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
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInquiryInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.business.RefundInquiryInsurerServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInquiryInsurerRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchRefundInquiryInsurerResponse;

@Component
@RequestScope
public class SearchRefundInquiryInsurerWsProviderImpl extends WebServiceBase<SearchRefundInquiryInsurerRequest, SearchResponse>{
    private static final Logger log = LoggerFactory.getLogger(SearchRefundInquiryInsurerWsProviderImpl.class);

    @Autowired
    private RefundInquiryInsurerServiceImpl refundInquiryInsurerService;

    @Autowired
    public SearchRefundInquiryInsurerWsProviderImpl(MessageHandler messageHandler){
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRefundInquiryInsurerRequest request) throws Exception{
        if(request == null){
            return getResponseMessageByCode("BS0002");
        }
        if (!StringUtils.isEmpty(request.getReceiveNo())) {
            request.setReceiveNo(request.getReceiveNo().toUpperCase());
            request.setStartPeriodMonth(null);
            request.setStartPeriodYear(null);
            request.setEndPeriodMonth(null);
            request.setEndPeriodYear(null);
            return null;
        }
        if (StringUtils.isEmpty(request.getProgressStatus())){
            return getResponseMessageByCode("BE0000","Progress Status");
        }
        if (StringUtils.isEmpty(request.getStartPeriodMonth())){
            return getResponseMessageByCode("BE0000","Start Period Month");
        }
        if (StringUtils.isEmpty(request.getStartPeriodYear())){
            return getResponseMessageByCode("BE0000","Start Period Year");
        }
        if (StringUtils.isEmpty(request.getEndPeriodMonth())){
            return getResponseMessageByCode("BE0000","End Period Month");
        }
        if (StringUtils.isEmpty(request.getEndPeriodYear())){
            return getResponseMessageByCode("BE0000","End Period Year");
        }
        return null;
    }

    @Override
    protected SearchResponse implement(SearchRefundInquiryInsurerRequest request) throws Exception {
        SearchResponse response = new SearchResponse();
        try {
            List<RefundInquiryInsurerBean> refundPeriodList = refundInquiryInsurerService.searchRefundInsurerPeriods(request
                    , !ObjectUtils.isObjectNull(request.getPaginable()) ? request.getPaginable() : null);
            if (CollectionUtils.isEmpty(refundPeriodList) && !StringUtils.isEmpty(request.getReceiveNo())) {
                response.setStatus(ResponseStatus.BUSINESS_ERROR);
                response.setErrorMsg(getResponseMessageByCode("BS0010", request.getReceiveNo()).getErrorMsg());
                return response;
//                return getResponseMessageByCode("BS0010", request.getReceiveNo());
            }

            SearchRefundInquiryInsurerResponse _response = new SearchRefundInquiryInsurerResponse();
            _response.setRefundInsurerList(refundPeriodList);

//            ResponseBean response = new ResponseBean();
            response.setResult(_response);
            response.setStatus(ResponseStatus.SUCCESS);

            return response;
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            log.error("searchRefundInsurerPeriods error : ", e);
            response.setStatus(ResponseStatus.BUSINESS_ERROR);
            response.setErrorMsg(getResponseMessageByCode("BE0001").getErrorMsg());
            return response;
//            return getResponseMessageByCode("BE0001");
        }

//        ResponseBean response = new ResponseBean();
//        List<RefundInquiryInsurerBean> refundInquiryInsurerList = refundInquiryInsurerService.searchRefundInsurerPeriods(request);
//
//        if (CollectionUtils.isEmpty(refundInquiryInsurerList) && !StringUtils.isEmpty(request.getReceiveNo())) {
//            return getResponseMessageByCode("BS0010", request.getReceiveNo());
//        }
//        SearchRefundInquiryInsurerResponse refundInsurerResponse = new SearchRefundInquiryInsurerResponse();
//        refundInsurerResponse.setRefundInsurerList(refundInquiryInsurerList);
//
//        response.setResult(refundInsurerResponse);
//        response.setStatus(ResponseStatus.SUCCESS);
//        return response;
    }
}
