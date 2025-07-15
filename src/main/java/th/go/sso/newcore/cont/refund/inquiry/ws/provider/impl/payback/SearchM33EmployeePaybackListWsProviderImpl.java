package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.payback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.dto.SearchResponse;
import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.business.payback.PaybackM33Service;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchM33PaybackListRequest;

@Component
@RequestScope
public class SearchM33EmployeePaybackListWsProviderImpl extends WebServiceBase<SearchM33PaybackListRequest, SearchResponse> {
	private static final Logger log = LoggerFactory.getLogger(SearchM33CompanyPaybackListWsProviderImpl.class);

	@Autowired
	private TimerHelper timer;
	@Autowired
	private PaybackM33Service paybackM33Service;

	public SearchM33EmployeePaybackListWsProviderImpl(MessageHandler messageHandler) {
		super(messageHandler);
	}

	@Override
	protected ResponseBean validate(SearchM33PaybackListRequest request) throws Exception {
		if (request == null) {
			log.debug("VALIDATE FAILED: request null.");
			return getResponseMessageByCode("BE0006");
		}
		if (NumberUtils.isZeroOrNull(request.getCompanyId()) && StringUtils.isEmpty(request.getIdCardNo()) && StringUtils.isEmpty(request.getReceiveNo()) && StringUtils.isEmpty(request.getPaybackNo())) {
			log.debug("VALIDATE FAILED: request values null.");
			return getResponseMessageByCode("BE0006");
		}
		return null;
	}

	@Override
	protected SearchResponse implement(SearchM33PaybackListRequest request) throws Exception {
		timer.start("SearchM33EmployeePaybackListWsProviderImpl");
		SearchResponse response = new SearchResponse();
		if (request.getPaginable() == null) {
			Paginable paginable = new Paginable();
			paginable.setCurrentPage(1);
			paginable.setRowPerPage(100L);
			request.setPaginable(paginable);
		}
		response.setResult(paybackM33Service.searchEmployeePaybackList(request, request.getPaginable()));
		response.setStatus(ResponseStatus.SUCCESS);
		log.debug(timer.endToString("SearchM33EmployeePaybackListWsProviderImpl"));
		return response;
	}
}
