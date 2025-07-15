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
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.SearchDeleteCancelHistoryBean;
import th.go.sso.newcore.cont.refund.inquiry.business.SearchRefundDeleteCancelHistServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundDeleteCancelHistoryRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchDeleteCancelHistoryResponse;

@Component
@RequestScope
public class SearchRefundDeleteCancelHistWsProviderImpl extends WebServiceBase<SearchRefundDeleteCancelHistoryRequest, SearchResponse> {
    private static final Logger log = LoggerFactory.getLogger(SearchRefundDeleteCancelHistWsProviderImpl.class);

    public SearchRefundDeleteCancelHistWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Autowired
    private SearchRefundDeleteCancelHistServiceImpl deleteCancelHistService;

    @Override
    protected ResponseBean validate(SearchRefundDeleteCancelHistoryRequest request) throws Exception {
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
        if ("0".equals(request.getSection()) && NumberUtils.isZeroOrNull(request.getCompanyId())) {
            return getResponseMessageByCode("BE0000","companyId");
        } else if (!"0".equals(request.getSection()) && StringUtils.isEmpty(request.getIdCardNo())) {
            return getResponseMessageByCode("BE0000","idCardNo");
        }
        return null;
    }

    @Override
    protected SearchResponse implement(SearchRefundDeleteCancelHistoryRequest request) throws Exception {
        SearchResponse response = new SearchResponse();
        SearchDeleteCancelHistoryResponse _response = new SearchDeleteCancelHistoryResponse();
        Paginable paging = request.getPaginable();
        try{
            if (!StringUtils.isEmpty(request.getReceiveNo())) {
                String _section = deleteCancelHistService.getSectionByReceiveNo(request.getReceiveNo());
                if (StringUtils.isEmpty(_section) || !List.of("0", "1", "3").contains(_section)) {
                    log.debug("searchHistoryList by receiveNo not found _section; no: {}, section: {}", request.getReceiveNo(), _section);
                    throw new BusinessException("ไม่พบใบคำขอในระบบ");
                }
                request.setSection(_section);
                _response.setSection(_section);
            }
            _response.setSection(request.getSection());
            List<SearchDeleteCancelHistoryBean> list = deleteCancelHistService.searchHistoryList(request, paging);
            response.setPaginable(paging);
            if (paging != null && NumberUtils.isZeroOrNull(paging.getRowCount())) {
                response.setStatus(ResponseStatus.BUSINESS_ERROR);
                response.setErrorMsg(getResponseMessageByCode("BS0001").getErrorMsg());
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
