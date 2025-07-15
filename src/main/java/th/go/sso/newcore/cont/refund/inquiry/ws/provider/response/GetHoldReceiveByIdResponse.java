package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;

public class GetHoldReceiveByIdResponse {
    private RefundInsurerBean refundDetails;
    private List<RefundInsurerPeriodListBean> refundPeriodList;

    public RefundInsurerBean getRefundDetails() {
        return refundDetails;
    }

    public void setRefundDetails(RefundInsurerBean refundDetails) {
        this.refundDetails = refundDetails;
    }

    public List<RefundInsurerPeriodListBean> getRefundPeriodList() {
        return refundPeriodList;
    }

    public void setRefundPeriodList(List<RefundInsurerPeriodListBean> refundPeriodList) {
        this.refundPeriodList = refundPeriodList;
    }
}
