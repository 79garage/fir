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
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.EmployeeDetailBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTrReceiveEmployeeServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchEmployeeDetailRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchEmployeeDetailResponse;

@Component
@RequestScope
public class ConTrReceiveEmployeeWsProviderImpl extends WebServiceBase<SearchEmployeeDetailRequest, ResponseBean> {
    private final static Logger log = LoggerFactory.getLogger(ConTrReceiveEmployeeWsProviderImpl.class);

    @Autowired
    private ConTrReceiveEmployeeServiceImpl conTrReceiveEmployeeService;
    public ConTrReceiveEmployeeWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchEmployeeDetailRequest request) throws Exception {

        if (request == null){
            return getResponseMessageByCode("BE0012");
        }
        if (NumberUtils.isZeroOrNull(request.getCompanyId())) {
            return getResponseMessageByCode("BE0000","CompanyId");
        }
        if (NumberUtils.isZeroOrNull(request.getReceiveCompanyId())) {
            return getResponseMessageByCode("BE0000","ReceiveCompanyId");
        }

        return null;
    }

    @Override
    protected ResponseBean implement(SearchEmployeeDetailRequest request) throws Exception {

        try {
            List<EmployeeDetailBean> employeeList = conTrReceiveEmployeeService.searchReceiveEmployee(request.getCompanyId(),request.getReceiveCompanyId());
            SearchEmployeeDetailResponse _response = new SearchEmployeeDetailResponse();
            _response.setEmployeeList(employeeList);

            ResponseBean response = new ResponseBean();
            response.setResult(_response);
            response.setStatus(ResponseStatus.SUCCESS);
            return response;
        } catch (Exception e) {
            log.error("SearchOverRefundEmployee : ", e);
            return getResponseMessageByCode("BE0001");
        }


//        ResponseBean response = new ResponseBean();
//        SearchEmployeeDetailResponse res = new SearchEmployeeDetailResponse();
//
//        List<EmployeeDetailBean> employeeList = conTrReceiveEmployeeService.searchReceiveEmployee(request.getCompanyId(),request.getReceiveCompanyId());
//        if (!CollectionUtils.isEmpty(employeeList)) {
//            res.setEmployeeList(employeeList);
//            response.setResult(res);
//            response.setStatus(ResponseStatus.SUCCESS);
//        }
//
//        return response;
    }
}
