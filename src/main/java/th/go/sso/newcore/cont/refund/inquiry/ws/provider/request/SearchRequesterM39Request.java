package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchRequesterM39Request extends SearchRequest {

	private Long requesterId;

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

}
