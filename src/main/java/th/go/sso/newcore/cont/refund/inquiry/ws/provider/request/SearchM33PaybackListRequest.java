package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchM33PaybackListRequest extends SearchRequest {

	private Long companyId;
	private String idCardNo;
	private String paybackNo;
	private String receiveNo;
	private String status;

	public Long getCompanyId() {
		return companyId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public String getPaybackNo() {
		return paybackNo;
	}

	public String getReceiveNo() {
		return receiveNo;
	}

	public String getStatus() {
		return status;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public void setPaybackNo(String paybackNo) {
		this.paybackNo = paybackNo;
	}

	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
