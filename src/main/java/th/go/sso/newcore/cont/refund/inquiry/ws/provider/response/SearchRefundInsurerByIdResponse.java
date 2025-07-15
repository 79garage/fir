package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.ButtonTypeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;

public class SearchRefundInsurerByIdResponse {
    private RefundInsurerBean refundInsurerRequestDetail;
    private List<RefundInsurerPeriodListBean> refundInsurerPeriodList;
    private ButtonTypeBean editType;

    public RefundInsurerBean getRefundInsurerRequestDetail() {
        return refundInsurerRequestDetail;
    }

    public void setRefundInsurerRequestDetail(RefundInsurerBean refundInsurerRequestDetail) {
        this.refundInsurerRequestDetail = refundInsurerRequestDetail;
    }

    public List<RefundInsurerPeriodListBean> getRefundInsurerPeriodList() {
        return refundInsurerPeriodList;
    }

    public void setRefundInsurerPeriodList(List<RefundInsurerPeriodListBean> refundInsurerPeriodList) {
        this.refundInsurerPeriodList = refundInsurerPeriodList;
    }

    public ButtonTypeBean getEditType() {
        return editType;
    }

    public void setEditType(ButtonTypeBean editType) {
        this.editType = editType;
    }
}
