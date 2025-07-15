package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;

public class GetRefundInsurerMultipleCompanyInfoByPeriodResponse {

	private RefundInsurerBean refundInsurerRequestDetail;
	private List<OverRefundEmployeeBean> refundInsurerPeriodList;

	public RefundInsurerBean getRefundInsurerRequestDetail() {
		return refundInsurerRequestDetail;
	}

	public void setRefundInsurerRequestDetail(RefundInsurerBean refundInsurerRequestDetail) {
		this.refundInsurerRequestDetail = refundInsurerRequestDetail;
	}

	public List<OverRefundEmployeeBean> getRefundInsurerPeriodList() {
		return refundInsurerPeriodList;
	}

	public void setRefundInsurerPeriodList(List<OverRefundEmployeeBean> refundInsurerPeriodList) {
		this.refundInsurerPeriodList = refundInsurerPeriodList;
	}
}
