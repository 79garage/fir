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
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.business.TransactionRefundCompanyByIdServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundCompanyByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchRefundCompanyByIdResponse;

@Component
@RequestScope
public class SearchRefundCompanyByIdWsProviderImpl extends WebServiceBase<SearchRefundCompanyByIdRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(SearchRefundCompanyByIdWsProviderImpl.class);

    @Autowired
    private TransactionRefundCompanyByIdServiceImpl transactionRefundCompanyChangeService;

    public SearchRefundCompanyByIdWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRefundCompanyByIdRequest request) throws Exception {
        if (request == null){
            return getResponseMessageByCode("BE0012");
        }
        if (NumberUtils.isZeroOrNull(request.getCompanyId())) {
            return getResponseMessageByCode("BE0000","CompanyId");
        }
        if (StringUtils.isEmpty(request.getMode())) {
            return getResponseMessageByCode("BE0000","Mode");
        }
        if (NumberUtils.isZeroOrNull(request.getRefundRequestId())) {
            return getResponseMessageByCode("BE0000","RefundRequestId");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchRefundCompanyByIdRequest request) throws Exception {

        try {
            RefundCompanyRequestBean checkRefundCompany = transactionRefundCompanyChangeService.checkRefundCompany(request.getCompanyId(), request.getRefundRequestId());
            if (checkRefundCompany == null) {
                return getResponseMessageByCode("BS0003");
            }
            RefundCompanyRequestBean refundCompany = transactionRefundCompanyChangeService.searchRefundChange(checkRefundCompany);
            List<RefundCompanyPeriodBean> refundCompanyPeriodList = transactionRefundCompanyChangeService.searchRefundPeriodChange(request.getCompanyId(), request.getRefundRequestId(), request.getMode(), refundCompany, getUser().getAccessToken());
            List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeeList = transactionRefundCompanyChangeService.searchSplitEmployeeRefundPeriod(request.getRefundRequestId());

            SearchRefundCompanyByIdResponse res = new SearchRefundCompanyByIdResponse();
            res.setRefundCompanyRequestDetail(refundCompany);
            res.setRefundCompanyPeriodList(refundCompanyPeriodList);
            res.setSplitEmployeePeriodList(splitEmployeeList);

            ResponseBean response = new ResponseBean();
            response.setResult(res);
            response.setStatus(ResponseStatus.SUCCESS);

            return response;
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            log.error("SearchRefundInsurerRequestImpl error : ", e);
            return getResponseMessageByCode("BE0001");
        }
    }
}
