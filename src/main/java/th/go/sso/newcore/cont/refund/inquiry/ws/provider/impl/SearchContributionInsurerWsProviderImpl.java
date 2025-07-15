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
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ContributionInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ContributionInsurerServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchContributionInsurerRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchContributionInsurerResponse;

@Component
@RequestScope
public class SearchContributionInsurerWsProviderImpl extends WebServiceBase<SearchContributionInsurerRequest, ResponseBean> {
    private final static Logger log = LoggerFactory.getLogger(SearchContributionInsurerWsProviderImpl.class);

    @Autowired
    private ContributionInsurerServiceImpl contributionInsurerService;

    @Autowired
    public SearchContributionInsurerWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchContributionInsurerRequest request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BS0001");
        }
        if (NumberUtils.isZeroOrNull(request.getInsurerId()) && StringUtils.isEmpty(request.getReceiveNo())) {
            return getResponseMessageByCode("BE0000","InsurerId or ReceiveNo");
        }
        if (!StringUtils.isEmpty(request.getReceiveNo())) {
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
    protected ResponseBean implement(SearchContributionInsurerRequest request) throws Exception {
        try {
            ConMsInsurerBean insurerBean = contributionInsurerService.searchInsurer(request.getInsurerId(),request.getReceiveNo());
            if (!StringUtils.isEmpty(request.getReceiveNo()) && ObjectUtils.isObjectNull(insurerBean)){
                return getResponseMessageByCode("BS0010", request.getReceiveNo());
            }
            ResponseBean response = new ResponseBean();
            if (!ObjectUtils.isObjectNull(insurerBean)) {
                List<ContributionInsurerBean> conInsurerList = contributionInsurerService.searchContributionInsurerPeriod(request);

                SearchContributionInsurerResponse conInsurerResponse = new SearchContributionInsurerResponse();
                conInsurerResponse.setInsurerDetail(insurerBean);
                conInsurerResponse.setReceiveInsurerList(conInsurerList);

                response.setResult(conInsurerResponse);
                response.setStatus(ResponseStatus.SUCCESS);
            }
            return response;
        } catch (
                BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        }catch (Exception e) {
            log.error("SearchOverRefundEmployee : ", e);
            return getResponseMessageByCode("BE0001");
        }

//        try {
//            ConMsInsurerBean insurerBean = contributionInsurerService.searchInsurer(request.getInsurerId(),request.getReceiveNo());
//            if (!ObjectUtils.isObjectNull(insurerBean)) {
//                List<ContributionInsurerBean> conInsurerList = contributionInsurerService.searchConInsurer(request);
//
//                SearchContributionInsurerResponse conInsurerResponse = new SearchContributionInsurerResponse();
//                conInsurerResponse.setInsurerDetail(insurerBean);
//                conInsurerResponse.setReceiveInsurerList(conInsurerList);
//
//                ResponseBean response = new ResponseBean();
//                response.setResult(conInsurerResponse);
//                response.setStatus(ResponseStatus.SUCCESS);
//                return response;
//            }else {
//                return getResponseMessageByCode("BS0001");
//            }
//        }catch (Exception e){
//            return getResponseMessageByCode("BE0001");
//        }

    }
}
