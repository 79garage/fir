package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestBean;

public class SearchPaybackRequestByIdResponse {

    private ConTbPaybackRequestBean paybackRequestDetail;

    public ConTbPaybackRequestBean getPaybackRequestDetail() {
        return paybackRequestDetail;
    }

    public void setPaybackRequestDetail(ConTbPaybackRequestBean paybackRequestDetail) {
        this.paybackRequestDetail = paybackRequestDetail;
    }
}
