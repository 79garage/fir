package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import java.util.List;

import th.go.sso.newcore.cont.common.dto.RequestBean;

public class GetRefundInsurerMultipleCompanyInfoByPeriodsRequest extends RequestBean {

	private String idCardNo;
	private List<String> periods;

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public List<String> getPeriods() {
		return periods;
	}

	public void setPeriods(List<String> periods) {
		this.periods = periods;
	}
}
