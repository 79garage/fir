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
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiveHoldReceiveBean;
import th.go.sso.newcore.cont.refund.inquiry.business.RefundInsurerRequestServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInsurerDetailByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchRefundInsurerResponse;

@Component
@RequestScope
public class SearchRefundInsurerRequestImpl extends WebServiceBase<SearchRefundInsurerDetailByIdRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(SearchRefundInsurerRequestImpl.class);

    @Autowired
    private RefundInsurerRequestServiceImpl refundInsurerRequestService;

    
    public SearchRefundInsurerRequestImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRefundInsurerDetailByIdRequest request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BE0000", "Request");
        }
        if (NumberUtils.isZeroOrNull(request.getInsurerId())) {
            return getResponseMessageByCode("BE0000", "Insurer Id");
        }
        for (RefundReceiveHoldReceiveBean receiveHold : request.getReceiveIdList()){
            if (StringUtils.isEmpty(receiveHold.getSection())){
                return getResponseMessageByCode("BE0000", "Section");
            }
            if (NumberUtils.isZeroOrNull(receiveHold.getReceiveId())){
                if ("1".equals(receiveHold.getSection())){
                    return getResponseMessageByCode("BE0000", "Receive Insurer Id");
                } else if ("7".equals(receiveHold.getSection())) {
                    return getResponseMessageByCode("BE0000", "Hold Receive Id");
                }
            }
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchRefundInsurerDetailByIdRequest request) throws Exception {
        try {
            RefundInsurerBean refundDetail = refundInsurerRequestService.searchInsurerDetails(request);
            if (ObjectUtils.isObjectNull(refundDetail)) {
                throw new BusinessException("ไม่พบข้อมูลผู้ประกันตน");
            }
            List<RefundInsurerPeriodListBean> refundPeriods = refundInsurerRequestService.searchInsurerRefundPeriods(request
                    , refundDetail.getIdCardNo()
                    , getUser().getAccessToken());
            if (CollectionUtils.isEmpty(refundPeriods)) {
                throw new BusinessException("not found refund periods");
            }

            SearchRefundInsurerResponse result = new SearchRefundInsurerResponse();
            result.setRefundInsurerRequestDetail(refundDetail);
            result.setRefundInsurerPeriodList(refundPeriods);

            ResponseBean response = new ResponseBean();
            response.setResult(result);
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
