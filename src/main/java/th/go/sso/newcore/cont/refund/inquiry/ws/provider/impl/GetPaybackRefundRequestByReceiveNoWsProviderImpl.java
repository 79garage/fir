package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.CheckPaybackBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbPaybackRequestServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundRequestByReceiveNoRequest;

@Component
@RequestScope
public class GetPaybackRefundRequestByReceiveNoWsProviderImpl extends WebServiceBase<GetRefundRequestByReceiveNoRequest, ResponseBean> {

    @Autowired
    private ConTbPaybackRequestServiceImpl conTbPaybackRequestService;

    @Autowired
    public GetPaybackRefundRequestByReceiveNoWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(GetRefundRequestByReceiveNoRequest request) throws Exception {

        if (StringUtils.isEmpty(request.getReceiveNo())){
            return getResponseMessageByCode("BE0000", "ReceiveNo");
        }
        Long refundRequestId = conTbPaybackRequestService.isRefundRequestIdExist(request.getReceiveNo());
        if (NumberUtils.isZeroOrNull(refundRequestId)){
            return getResponseMessageByCode("BS0021", request.getReceiveNo());
        }else {
            List<CheckPaybackBean> checkPaybackList = conTbPaybackRequestService.isPaybackRequestNoExist(refundRequestId);
//            if (!CollectionUtils.isEmpty(paybackRequestNo) && paybackRequestNo.size() != 0){
//                return getResponseMessageByCode("BS0022", paybackRequestNo.toString());
//            }
            List<CheckPaybackBean> filterApproveStatusList = checkPaybackList.stream()
                    .filter(status -> status.getPaybackApproveStatus() != null && status.getPaybackApproveStatus().equals("C"))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(checkPaybackList)){
                if (!CollectionUtils.isEmpty(filterApproveStatusList)){
                    return null;
                }else {
                    checkPaybackList.stream().map(CheckPaybackBean::getPaybackRequestNo).collect(Collectors.toList());
//                    return getResponseMessageByCode("BS0022", paybackRequestNoList.toString());
                }
            }
        }
        return null;
    }

    @Override
    protected ResponseBean implement(GetRefundRequestByReceiveNoRequest request) throws Exception {
        ConTbPaybackRequestBean conTbPaybackRequestBean = conTbPaybackRequestService.getPaybackRequestByReceiveNo(request.getReceiveNo(), getUser());
        ResponseBean response = new ResponseBean();
        if (conTbPaybackRequestBean != null){
            response.setStatus(ResponseStatus.SUCCESS);
            response.setResult(conTbPaybackRequestBean);
        }else {
            response = getResponseMessageByCode("BS0003");
        }
        return response;
    }
}
