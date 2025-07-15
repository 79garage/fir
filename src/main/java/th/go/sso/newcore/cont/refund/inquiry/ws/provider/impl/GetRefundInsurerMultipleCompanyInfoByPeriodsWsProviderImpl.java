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
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.business.OverRefundEmployeePeriodsServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundInsurerMultipleCompanyInfoByPeriodsRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.GetRefundInsurerMultipleCompanyInfoByPeriodsResponse;

@Component
@RequestScope
public class GetRefundInsurerMultipleCompanyInfoByPeriodsWsProviderImpl extends WebServiceBase<GetRefundInsurerMultipleCompanyInfoByPeriodsRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(GetRefundInsurerMultipleCompanyInfoByPeriodsWsProviderImpl.class);
    @Autowired
    private OverRefundEmployeePeriodsServiceImpl overRefundEmployeeIdsService;

    @Autowired
    public GetRefundInsurerMultipleCompanyInfoByPeriodsWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(GetRefundInsurerMultipleCompanyInfoByPeriodsRequest request) throws Exception {

//        int countCompany = overRefundEmployeeIdsService.getCountCompany(request.getIdCardNo());
//        if (countCompany < 2) {
//            return getResponseMessageByCode("BS0001");
//        }
        if (StringUtils.isEmpty(request.getIdCardNo())) {
            return getResponseMessageByCode("BE0000", "เลขประจำตัวประชาชน");
        }
        if (CollectionUtils.isEmpty(request.getPeriods())) {
            return getResponseMessageByCode("BE0000", "งวด เดือน/ปี");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(GetRefundInsurerMultipleCompanyInfoByPeriodsRequest request) throws Exception {
        try {
            RefundInsurerBean refundDetail = overRefundEmployeeIdsService.searchEmployeeDetails(request.getIdCardNo());
            if (ObjectUtils.isObjectNull(refundDetail)) {
                throw new BusinessException("ไม่พบข้อมูลผู้ประกันตน");
            }
            List<OverRefundEmployeeBean> refundPeriods = overRefundEmployeeIdsService.searchEmployeeRefundPeriods(request
                    , getUser().getAccessToken());
            if (CollectionUtils.isEmpty(refundPeriods)) {
                throw new BusinessException("not found refund periods");
            }

            GetRefundInsurerMultipleCompanyInfoByPeriodsResponse _response = new GetRefundInsurerMultipleCompanyInfoByPeriodsResponse();
            _response.setRefundInsurerRequestDetail(refundDetail);
            _response.setRefundInsurerPeriodList(refundPeriods);

            ResponseBean response = new ResponseBean();
            response.setResult(_response);
            response.setStatus(ResponseStatus.SUCCESS);
            return response;
        } catch (Exception e) {
            log.error("GetRefundInsurerMultipleCompanyInfoByPeriodsWsProviderImpl error : ", e);
            return getResponseMessageByCode("BE0001");
        }
    }
}
