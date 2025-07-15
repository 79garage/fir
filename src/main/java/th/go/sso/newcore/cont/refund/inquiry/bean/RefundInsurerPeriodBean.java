package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.util.List;

public class RefundInsurerPeriodBean {

	private List<RefundCompanyRequestBean> refundRequestCompanyList;
	private List<RefundReceiptBean> refundReceiptList;
	
	public List<RefundCompanyRequestBean> getRefundRequestCompanyList() {
		return refundRequestCompanyList;
	}
	public void setRefundRequestCompanyList(List<RefundCompanyRequestBean> refundRequestCompanyList) {
		this.refundRequestCompanyList = refundRequestCompanyList;
	}
	public List<RefundReceiptBean> getRefundReceiptList() {
		return refundReceiptList;
	}
	public void setRefundReceiptList(List<RefundReceiptBean> refundReceiptList) {
		this.refundReceiptList = refundReceiptList;
	}
	
}
