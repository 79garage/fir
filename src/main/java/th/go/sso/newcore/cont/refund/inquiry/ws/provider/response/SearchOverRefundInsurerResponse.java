package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundInsurerBean;

public class SearchOverRefundInsurerResponse {

    private ConMsInsurerBean insurerDetail;
    private List<OverRefundInsurerBean> refundInsurerList;

    public ConMsInsurerBean getInsurerDetail() {
        return insurerDetail;
    }

    public void setInsurerDetail(ConMsInsurerBean insurerDetail) {
        this.insurerDetail = insurerDetail;
    }

    public List<OverRefundInsurerBean> getRefundInsurerList() {
        return refundInsurerList;
    }

    public void setRefundInsurerList(List<OverRefundInsurerBean> refundInsurerList) {
        this.refundInsurerList = refundInsurerList;
    }
}
