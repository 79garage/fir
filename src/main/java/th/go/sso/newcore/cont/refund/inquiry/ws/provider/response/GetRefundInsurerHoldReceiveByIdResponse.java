package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.ButtonTypeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;

public class GetRefundInsurerHoldReceiveByIdResponse {
    private RefundInsurerBean refundDetails;
    private List<RefundInsurerPeriodListBean> refundPeriodList;
    private ButtonTypeBean editType;

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

    public ButtonTypeBean getEditType() {
        return editType;
    }

    public void setEditType(ButtonTypeBean editType) {
        this.editType = editType;
    }
}
