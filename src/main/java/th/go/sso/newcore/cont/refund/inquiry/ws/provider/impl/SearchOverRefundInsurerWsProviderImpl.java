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
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.business.OverRefundInsurerServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInsurerRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchOverRefundInsurerResponse;

@Component
@RequestScope
public class SearchOverRefundInsurerWsProviderImpl extends WebServiceBase<SearchRefundInsurerRequest, SearchResponse> {

    private static final Logger log = LoggerFactory.getLogger(SearchOverRefundInsurerWsProviderImpl.class);
    @Autowired
    private OverRefundInsurerServiceImpl overRefundInsurerService;

    @Autowired
    public SearchOverRefundInsurerWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRefundInsurerRequest request) throws Exception {
        if (request == null) {
            return getResponseMessageByCode("BS0001");
        }
        if (NumberUtils.isZeroOrNull(request.getInsurerId()) && StringUtils.isEmpty(request.getReceiveNo())) {
            return getResponseMessageByCode("BE0000","InsurerId or ReceiveNo");
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
    protected SearchResponse implement(SearchRefundInsurerRequest request) throws Exception {
        SearchResponse response = new SearchResponse();
        try {
            ConMsInsurerBean insurerBean = overRefundInsurerService.searchInsurer(request);
            if (!StringUtils.isEmpty(request.getReceiveNo()) && ObjectUtils.isObjectNull(insurerBean)){
                response.setStatus(ResponseStatus.BUSINESS_ERROR);
                response.setErrorMsg(getResponseMessageByCode("BS0010", request.getReceiveNo()).getErrorMsg());
                return response;
            } else if (ObjectUtils.isObjectNull(insurerBean)) {
                throw new BusinessException("ไม่พบข้อมูลผู้ประกันตน");
            }
            if (!ObjectUtils.isObjectEmpty(insurerBean)) {
                List<OverRefundInsurerBean> refundInsurerList = overRefundInsurerService.searchRefundInsurerPeriod(request
                        , !ObjectUtils.isObjectNull(request.getPaginable()) ? request.getPaginable() : null);

                SearchOverRefundInsurerResponse insurerResponse = new SearchOverRefundInsurerResponse();
                insurerResponse.setInsurerDetail(insurerBean);
                insurerResponse.setRefundInsurerList(refundInsurerList);

                response.setResult(insurerResponse);
                response.setStatus(ResponseStatus.SUCCESS);
            }
            return response;
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        }catch (Exception e) {
            log.error("SearchOverRefundEmployee : ", e);
            response.setStatus(ResponseStatus.BUSINESS_ERROR);
            response.setErrorMsg(getResponseMessageByCode("BE0001").getErrorMsg());
            return response;
        }
    }
}
