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
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.business.payback.PaybackM39Service;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchM39PaybackListRequest;

@Component
@RequestScope
public class SearchM39PaybackListWsProviderImpl extends WebServiceBase<SearchM39PaybackListRequest, SearchResponse> {
	private static final Logger log = LoggerFactory.getLogger(SearchM39PaybackListWsProviderImpl.class);

	@Autowired
	private TimerHelper timer;
	@Autowired
	private PaybackM39Service paybackM39Service;

	public SearchM39PaybackListWsProviderImpl(MessageHandler messageHandler) {
		super(messageHandler);
	}

	@Override
	protected ResponseBean validate(SearchM39PaybackListRequest request) throws Exception {
		if (request == null) {
			log.debug("VALIDATE FAILED: request null.");
			return getResponseMessageByCode("BE0006");
		}
		if (StringUtils.isEmpty(request.getIdCardNo()) && StringUtils.isEmpty(request.getReceiveNo()) && StringUtils.isEmpty(request.getPaybackNo())) {
			log.debug("VALIDATE FAILED: request values null.");
			return getResponseMessageByCode("BE0006");
		}
		return null;
	}

	@Override
	protected SearchResponse implement(SearchM39PaybackListRequest request) throws Exception {
		timer.start("SearchM39PaybackListWsProviderImpl");
		SearchResponse response = new SearchResponse();
		if (request.getPaginable() == null) {
			Paginable paginable = new Paginable();
			paginable.setCurrentPage(1);
			paginable.setRowPerPage(100L);
			request.setPaginable(paginable);
		}
		response.setResult(paybackM39Service.searchInsurerPaybackList(request, request.getPaginable()));
		response.setStatus(ResponseStatus.SUCCESS);
		log.debug(timer.endToString("SearchM39PaybackListWsProviderImpl"));
		return response;
	}
}
