package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.dto.SearchResponse;
import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHistoryBean;
import th.go.sso.newcore.cont.refund.inquiry.business.SearchRefundHistoryServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundHistoryRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchRefundHistoryResponse;

@Component
@RequestScope
public class SearchRefundHistoryWsProviderImpl extends WebServiceBase<SearchRefundHistoryRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(SearchRefundHistoryWsProviderImpl.class);

    public SearchRefundHistoryWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Autowired
    private SearchRefundHistoryServiceImpl searchRefundHistoryService;

    @Override
    protected ResponseBean validate(SearchRefundHistoryRequest request) throws Exception {
        if (request == null){
            return getResponseMessageByCode("BS0002");
        }
        if (!StringUtils.isEmpty(request.getReceiveNo())) {
            return null;
        }
        if (StringUtils.isEmpty(request.getSection())) {
            return getResponseMessageByCode("BE0000","section");
        }
        if (NumberUtils.isZeroOrNull(request.getCompanyId()) && StringUtils.isEmpty(request.getIdCardNo())) {
            return getResponseMessageByCode("BS0002");
        }
        if ("0".equals(request.getSection()) && "0".equals(request.getCategory()) && NumberUtils.isZeroOrNull(request.getCompanyId())) {
            return getResponseMessageByCode("BE0000","companyId");
        } else if ("0".equals(request.getSection()) && "1".equals(request.getCategory()) && StringUtils.isEmpty(request.getIdCardNo())) {
            return getResponseMessageByCode("BE0000","idCardNo");
        } else if ("0".equals(request.getSection()) && StringUtils.isEmpty(request.getCategory()) && StringUtils.isEmpty(request.getIdCardNo())) {
            return getResponseMessageByCode("BE0000","idCardNo");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchRefundHistoryRequest request) throws Exception {
        SearchResponse response = new SearchResponse();
        SearchRefundHistoryResponse _response = new SearchRefundHistoryResponse();
        Paginable paging = request.getPaginable();
        try {
            List<RefundHistoryBean> list = searchRefundHistoryService.getRefundHistory(request, request.getPaginable());
            response.setPaginable(paging);
            if (!StringUtils.isEmpty(request.getReceiveNo()) && CollectionUtils.isEmpty(list)) {
                return getResponseMessageByCode("BS0010", request.getReceiveNo());
            }
            if (CollectionUtils.isEmpty(list)) {
                response.setStatus(ResponseStatus.BUSINESS_ERROR);
                response.setResult(list);
//                response.setErrorMsg(getResponseMessageByCode("BS0001").getErrorMsg());
            } else {
                _response.setHistoryList(list);
                response.setResult(_response);
            }
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
