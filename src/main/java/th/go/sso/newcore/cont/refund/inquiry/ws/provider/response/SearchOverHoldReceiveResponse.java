package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHoldReceiveBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInfoBean;

public class SearchOverHoldReceiveResponse {
    private RefundInfoBean refundDetails;
    private List<RefundHoldReceiveBean> refundPeriodList;

    public RefundInfoBean getRefundDetails() {
        return refundDetails;
    }

    public void setRefundDetails(RefundInfoBean refundDetails) {
        this.refundDetails = refundDetails;
    }

    public List<RefundHoldReceiveBean> getRefundPeriodList() {
        return refundPeriodList;
    }

    public void setRefundPeriodList(List<RefundHoldReceiveBean> refundPeriodList) {
        this.refundPeriodList = refundPeriodList;
    }
}
