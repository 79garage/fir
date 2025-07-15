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
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.business.TransactionSplitEmployeeRefundCompanyServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchSplitEmployeeRefundRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchSplitEmployeeRefundResponse;

@Component
@RequestScope
public class SearchSplitEmployeeRefundWsProviderImpl extends WebServiceBase<SearchSplitEmployeeRefundRequest, ResponseBean> {
    private final static Logger log = LoggerFactory.getLogger(SearchSplitEmployeeRefundWsProviderImpl.class);

    @Autowired
    private TransactionSplitEmployeeRefundCompanyServiceImpl splitEmployeeRefundService;

    @Autowired
    public SearchSplitEmployeeRefundWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchSplitEmployeeRefundRequest request) throws Exception {
        if (request == null){
            return getResponseMessageByCode("BE0012");
        }
        if (NumberUtils.isZeroOrNull(request.getRefundRequestId())) {
            return getResponseMessageByCode("BE0000", "RefundRequestId");
        }
        if (NumberUtils.isZeroOrNull(request.getCompanyId())) {
            return getResponseMessageByCode("BE0000", "CompanyId");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchSplitEmployeeRefundRequest request) throws Exception {
        try {
            SplitEmployeeRefundCompanyRequestBean splitEmployee = splitEmployeeRefundService.searchRefundSplitEmployee(request.getCompanyId(), request.getRefundRequestId());
            if (splitEmployee == null) {
                return getResponseMessageByCode("BS0003");
            }

            String departmentReceiveCode = splitEmployee.getDepartmentReceiveCode();
            String requestType = splitEmployee.getRequestType();
            String requestChannel = splitEmployee.getRequestChannel();
            List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList = splitEmployeeRefundService.searchSplitEmployeePeriod(request
//            List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList = splitEmployeeRefundService.searchRefundSplitEmployeePeriod(request
                    , departmentReceiveCode
                    , requestType
                    , requestChannel
                    , getUser().getAccessToken());
            List<RefundCompanyPeriodBean> companyPeriodList = splitEmployeeRefundService.searchRefundPeriod(request);

            SearchSplitEmployeeRefundResponse _response = new SearchSplitEmployeeRefundResponse();
            _response.setRefundCompanyRequestDetail(splitEmployee);
            _response.setSplitEmployeePeriodList(splitEmployeePeriodList);
            _response.setRefundCompanyPeriodList(companyPeriodList);

            ResponseBean response = new ResponseBean();
            response.setResult(_response);
            response.setStatus(ResponseStatus.SUCCESS);
            return response;
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            log.error("SearchSplitEmployeeRefundWsProviderImpl error : ", e);
            return getResponseMessageByCode("BE0001");
        }

//        ResponseBean response = new ResponseBean();
//        SearchSplitEmployeeRefundResponse splitRefundResponse = new SearchSplitEmployeeRefundResponse();
//        SplitEmployeeRefundCompanyRequestBean splitEmployeeRefund = splitEmployeeRefundService.searchSplitEmployeeRefund(request);
//        if (splitEmployeeRefund == null) {
//            return getResponseMessageByCode("BS0003");
//        }
//        String departmentReceiveCode = splitEmployeeRefund.getDepartmentReceiveCode();
//        String requestType = splitEmployeeRefund.getRequestType();
//        String requestChannel = splitEmployeeRefund.getRequestChannel();
//
//        List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeeRefundPeriodList = splitEmployeeRefundService.searchSplitEmployeePeriod(request,departmentReceiveCode,requestType,requestChannel, getUser().getAccessToken());
//        List<RefundCompanyPeriodBean> companyPeriodList = splitEmployeeRefundService.searchRefundPeriod(request);
//        splitRefundResponse.setRefundCompanyRequestDetail(splitEmployeeRefund);
//        splitRefundResponse.setSplitEmployeePeriodList(splitEmployeeRefundPeriodList);
//        splitRefundResponse.setRefundCompanyPeriodList(companyPeriodList);
//
//        if (!ObjectUtils.isObjectNull(splitEmployeeRefund)) {
//            response.setResult(splitRefundResponse);
//            response.setStatus(ResponseStatus.SUCCESS);
//        } else {
//            response.setStatus(ResponseStatus.ERROR);
//        }
//
//        return response;
    }
}
