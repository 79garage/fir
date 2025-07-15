package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundPeriodActiveBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestBean;

public class RefundDeleteCancelHistoryByIdResponse {

    private RefundRequestBean refundRequestDetail;
    private List<RefundPeriodActiveBean> refundPeriodActiveList;
    private List<RefundPeriodActiveBean> refundPeriodInactiveList;

    public RefundRequestBean getRefundRequestDetail() {
        return refundRequestDetail;
    }

    public void setRefundRequestDetail(RefundRequestBean refundRequestDetail) {
        this.refundRequestDetail = refundRequestDetail;
    }

    public List<RefundPeriodActiveBean> getRefundPeriodActiveList() {
        return refundPeriodActiveList;
    }

    public void setRefundPeriodActiveList(List<RefundPeriodActiveBean> refundPeriodActiveList) {
        this.refundPeriodActiveList = refundPeriodActiveList;
    }

    public List<RefundPeriodActiveBean> getRefundPeriodInactiveList() {
        return refundPeriodInactiveList;
    }

    public void setRefundPeriodInactiveList(List<RefundPeriodActiveBean> refundPeriodInactiveList) {
        this.refundPeriodInactiveList = refundPeriodInactiveList;
    }
}
