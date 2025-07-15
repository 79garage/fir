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
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.business.SearchRefundEmployeeMultipleCompaniesServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundEmployeeNoticeRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchOverRefundEmployeeNoticeResponse;

@Component
@RequestScope
public class SearchOverRefundEmployeeNoticeWsProviderImpl extends WebServiceBase<SearchOverRefundEmployeeNoticeRequest, SearchResponse> {
    private static final Logger log = LoggerFactory.getLogger(SearchOverRefundEmployeeNoticeWsProviderImpl.class);

    @Autowired
    private SearchRefundEmployeeMultipleCompaniesServiceImpl searchRefundEmployeeMultipleCompaniesService;

    @Autowired
    public SearchOverRefundEmployeeNoticeWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchOverRefundEmployeeNoticeRequest request) throws Exception {
        if (StringUtils.isEmpty(request.getStartPeriodMonth()) && StringUtils.isEmpty(request.getStartPeriodYear())) {
            return getResponseMessageByCode("BE0000", "START PERIOD");
        }
        if (StringUtils.isEmpty(request.getEndPeriodMonth()) && StringUtils.isEmpty(request.getEndPeriodYear())) {
            return getResponseMessageByCode("BE0000", "END PERIOD");
        }
        if (StringUtils.isEmpty(request.getDepCode())) {
            return getResponseMessageByCode("BE0000", "DEP CODE");
        }
//        if (StringUtils.isEmpty(request.getDepRegionCode())) {
//            return getResponseMessageByCode("BE0000", "DEP REGION CODE");
//        }
        return null;
    }

    @Override
    protected SearchResponse implement(SearchOverRefundEmployeeNoticeRequest request) throws Exception {
        try {
            SearchOverRefundEmployeeNoticeResponse refundEmployeeNoticeResponse = new SearchOverRefundEmployeeNoticeResponse();
            refundEmployeeNoticeResponse.setRefundEmployeeList(searchRefundEmployeeMultipleCompaniesService.searchEmployeeRefundPeriodsNotice(request
                    , !ObjectUtils.isObjectNull(request.getPaginable()) ? request.getPaginable() : null));

            SearchResponse response = new SearchResponse();
            response.setStatus(ResponseStatus.SUCCESS);
            response.setResult(refundEmployeeNoticeResponse);
            return response;
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            log.error("SearchOverRefundEmployeeNotice : ", e);
            throw e;
        }
//        List<OverRefundEmployeeBean> refundEmployeeList = overRefundEmployeeService.getRefundEmployeeNoticeList(request,request.getPaginable());
//        log.debug("Refund Employee Total : {}", refundEmployeeList.size());
//
//        SearchOverRefundEmployeeNoticeResponse refundEmployeeNoticeResponse = new SearchOverRefundEmployeeNoticeResponse();
//        refundEmployeeNoticeResponse.setRefundEmployeeList(refundEmployeeList);
//
//        SearchResponse response = new SearchResponse();
//        response.setStatus(ResponseStatus.SUCCESS);
//        response.setResult(refundEmployeeNoticeResponse);
//        return response;
    }
}
