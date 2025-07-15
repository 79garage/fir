package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ReceiveCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ContributionCompanyByEmprServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchContributionCompanyByEmprRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchContributionCompanyByEmprResponse;

@Component
@RequestScope
public class SearchContributionCompanyByEmprWsProviderImpl extends WebServiceBase<SearchContributionCompanyByEmprRequest, ResponseBean> {

    @Autowired
    private ContributionCompanyByEmprServiceImpl contributionCompanyByEmprService;

    @Autowired
    public SearchContributionCompanyByEmprWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchContributionCompanyByEmprRequest request) throws Exception {
        ResponseBean test = new ResponseBean();
        if (request == null){
            test.setStatus(ResponseStatus.FAIL);
            return test;
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchContributionCompanyByEmprRequest request) throws Exception {
        ResponseBean response = new ResponseBean();
        SearchContributionCompanyByEmprResponse contributionCompanyByEmprResponse = new SearchContributionCompanyByEmprResponse();

        ConMsCompanyBean companyDetail = contributionCompanyByEmprService.searchCompany(request);
        List<ReceiveCompanyBean> receiveCompanyList = contributionCompanyByEmprService.searchReceiveCompany(request);
        contributionCompanyByEmprResponse.setCompanyDetail(companyDetail);
        contributionCompanyByEmprResponse.setReceiveCompanyList(receiveCompanyList);

        if (!ObjectUtils.isObjectNull(companyDetail) || !receiveCompanyList.isEmpty() ) {
            response.setResult(contributionCompanyByEmprResponse);
            response.setStatus(ResponseStatus.SUCCESS);
            if (!StringUtils.isEmpty(request.getReceiveNo()) && !ObjectUtils.isObjectNull(companyDetail) && receiveCompanyList.isEmpty() ) {
                return getResponseMessageByCode("BS0010", request.getReceiveNo());
            }
        }
        else if (!StringUtils.isEmpty(request.getReceiveNo())) {
            return getResponseMessageByCode("BS0010", request.getReceiveNo());
        }
        else {
            return getResponseMessageByCode("BS0001");
        }
        return response;
    }
}
