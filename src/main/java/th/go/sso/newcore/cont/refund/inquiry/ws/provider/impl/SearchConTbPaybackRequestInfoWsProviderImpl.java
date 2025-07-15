package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbPaybackRequestServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchConTbPaybackRequestRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchConTbPaybackRequestResponse;

@Component
@RequestScope
public class SearchConTbPaybackRequestInfoWsProviderImpl extends WebServiceBase<SearchConTbPaybackRequestRequest, ResponseBean> {

	@Autowired
	private ConTbPaybackRequestServiceImpl conTbPaybackRequestService;

	@Autowired
	public SearchConTbPaybackRequestInfoWsProviderImpl(MessageHandler messageHandler) {
		super(messageHandler);
	}

	@Override
	protected ResponseBean validate(SearchConTbPaybackRequestRequest request) throws Exception {

		if (request == null) {
			return getResponseMessageByCode("BE0006");
		}
		// if (StringUtils.isEmpty(request.getSection())) {
		// getResponseMessageByCode("BE0000", "section");
		// } else {
		// if ("0".equals(request.getSection())) {
		// if (StringUtils.isEmpty(request.getCategory())) {
		// return getResponseMessageByCode("BE0000", "category");
		// } else if (!StringUtils.isEmpty(request.getCategory())) {
		// if ("0".equals(request.getCategory())) {
		// if (NumberUtils.isZeroOrNull(request.getCompanyId())) {
		// return getResponseMessageByCode("BE0000", "company id");
		// } else if (StringUtils.isEmpty(request.getPaybackApproveStatus())) {
		// return getResponseMessageByCode("BE0000", "payback approve status");
		// }
		// } else if ("1".equals(request.getCategory())) {
		// if (StringUtils.isEmpty(request.getIdCardNo())) {
		// return getResponseMessageByCode("BE0000", "id card no");
		// } else if (StringUtils.isEmpty(request.getPaybackApproveStatus())) {
		// return getResponseMessageByCode("BE0000", "payback approve status");
		// }
		// }
		// } else {
		// if (StringUtils.isEmpty(request.getPaybackRequestNo())) {
		// return getResponseMessageByCode("BE0000", "payback request no");
		// }
		// }
		// } else if ("1".equals(request.getSection())) {
		// if (StringUtils.isEmpty(request.getIdCardNo())) {
		// return getResponseMessageByCode("BE0000", "id card no");
		// } else if (StringUtils.isEmpty(request.getPaybackApproveStatus())) {
		// return getResponseMessageByCode("BE0000", "payback approve status");
		// }
		// if (StringUtils.isEmpty(request.getPaybackRequestNo())) {
		// return getResponseMessageByCode("BE0000", "payback request no");
		// }
		// }
		// }
		return null;
	}

	@Override
	protected ResponseBean implement(SearchConTbPaybackRequestRequest request) throws Exception {

		SearchConTbPaybackRequestResponse response = new SearchConTbPaybackRequestResponse();
		response.setResult(conTbPaybackRequestService.getPaybackRequestByCondition(request));
		response.setStatus(ResponseStatus.SUCCESS);
		return response;
		// List<ConTbPaybackRequestInfoBean> paybackRequestInfoList =
		// conTbPaybackRequestService.getPaybackRequestByCondition(request);
		// if (!CollectionUtils.isEmpty(paybackRequestInfoList)) {
		// SearchConTbPaybackRequestResponse response = new
		// SearchConTbPaybackRequestResponse();
		// response.setResult(paybackRequestInfoList);
		// response.setStatus(ResponseStatus.SUCCESS);
		// return response;
		// } else {
		// return getResponseMessageByCode("BS0001");
		// }
	}
}
