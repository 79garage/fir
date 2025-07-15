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
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.business.TransactionRefundCompanyServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundCompanyRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchRefundCompanyResponse;

@Component
@RequestScope
public class SearchRefundCompanyWsProviderImpl extends WebServiceBase<SearchRefundCompanyRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(SearchRefundCompanyWsProviderImpl.class);

    @Autowired
    private TransactionRefundCompanyServiceImpl transactionRefundCompanyService;


    @Autowired
    public SearchRefundCompanyWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRefundCompanyRequest request) throws Exception {

        if (request == null){
            return getResponseMessageByCode("BE0012");
        }
        if (NumberUtils.isZeroOrNull(request.getCompanyId())) {
            return getResponseMessageByCode("BE0000","CompanyId");
        }
        if (CollectionUtils.isEmpty(request.getReceiveCompanyId())) {
            return getResponseMessageByCode("BE0000","ReceiveCompanyId");
        }
        if (StringUtils.isEmpty(request.getMode())) {
            return getResponseMessageByCode("BE0000","Mode");
        }

        return null;
    }

    @Override
    protected ResponseBean implement(SearchRefundCompanyRequest request) throws Exception {
        try {
            RefundCompanyRequestBean refundDetail = transactionRefundCompanyService.searchCompanyDetail(request.getCompanyId());
            if (ObjectUtils.isObjectNull(refundDetail)) {
                throw new BusinessException("ไม่พบข้อมูลสถานประกอบการ");
            }
            List<RefundCompanyPeriodBean> refundPeriods = transactionRefundCompanyService.searchCompanyRefundPeriods(request.getCompanyId()
                    , request.getReceiveCompanyId()
                    , request.getMode()
                    , refundDetail
                    , getUser().getAccessToken());
            if (CollectionUtils.isEmpty(refundPeriods)) {
                throw new BusinessException("not found refund periods");
            }
            SearchRefundCompanyResponse _response = new SearchRefundCompanyResponse();
            _response.setRefundCompanyRequestDetail(refundDetail);
            _response.setRefundCompanyPeriodList(refundPeriods);

            ResponseBean response = new ResponseBean();
            response.setResult(_response);
            response.setStatus(ResponseStatus.SUCCESS);
            return response;

        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            log.error("SearchRefundInsurerRequestImpl error : ", e);
            return getResponseMessageByCode("BE0001");
        }

//        ResponseBean response = new ResponseBean();
//        SearchRefundCompanyResponse res = new SearchRefundCompanyResponse();
//
//        RefundCompanyRequestBean refundCompany = transactionRefundCompanyService.searchRefund(request);
//        if (ObjectUtils.isObjectNull(refundCompany) || ObjectUtils.isObjectEmpty(refundCompany)) {
//            return getResponseMessageByCode("BS0003");
//        }
//        List<RefundCompanyPeriodBean> refundCompanyPeriodList = transactionRefundCompanyService.searchRefundPeriod(request,refundCompany);
//
//        res.setRefundCompanyRequestDetail(refundCompany);
//        res.setRefundCompanyPeriodList(refundCompanyPeriodList);
//
//        response.setResult(res);
//        response.setStatus(ResponseStatus.SUCCESS);
//
//        return response;

    }
}
