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
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.business.OverRefundEmployeePeriodServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundInsurerMultipleCompanyInfoByPeriodRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.GetRefundInsurerMultipleCompanyInfoByPeriodResponse;

@Component
@RequestScope
public class GetRefundInsurerMultipleCompanyInfoByPeriodWsProviderImpl extends WebServiceBase<GetRefundInsurerMultipleCompanyInfoByPeriodRequest, ResponseBean> {

    private static final Logger log = LoggerFactory.getLogger(GetRefundInsurerMultipleCompanyInfoByPeriodWsProviderImpl.class);
    @Autowired
    private OverRefundEmployeePeriodServiceImpl overRefundEmployeePeriodService;

    @Autowired
    public GetRefundInsurerMultipleCompanyInfoByPeriodWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(GetRefundInsurerMultipleCompanyInfoByPeriodRequest request) throws Exception {

        if (NumberUtils.isZeroOrNull(request.getRefundRequestId())) {
            return getResponseMessageByCode("BE0000", "RefundRequestId");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(GetRefundInsurerMultipleCompanyInfoByPeriodRequest request) throws Exception {

        try {
            RefundInsurerBean refundDetail = overRefundEmployeePeriodService.searchRefundDetails(request.getRefundRequestId());
            if (ObjectUtils.isObjectNull(refundDetail)) {
                throw new BusinessException("not found refund request");
            }
            List<OverRefundEmployeeBean> refundPeriods = overRefundEmployeePeriodService.searchRefundPeriods(request.getRefundRequestId()
                    , refundDetail
                    , getUser().getAccessToken());
            if (CollectionUtils.isEmpty(refundPeriods)) {
                throw new BusinessException("not found refund periods");
            }

            GetRefundInsurerMultipleCompanyInfoByPeriodResponse _response = new GetRefundInsurerMultipleCompanyInfoByPeriodResponse();
            _response.setRefundInsurerRequestDetail(refundDetail);
            _response.setRefundInsurerPeriodList(refundPeriods);

            ResponseBean response = new ResponseBean();
            response.setResult(_response);
            response.setStatus(ResponseStatus.SUCCESS);
            return response;
        } catch (Exception e) {
            log.error("GetRefundInsurerMultipleCompanyInfoByPeriodWsProviderImpl error : ", e);
            return getResponseMessageByCode("BE0001");
        }
    }
}
