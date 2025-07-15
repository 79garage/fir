package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInquiryInsurerBean;

public class SearchRefundInquiryInsurerResponse {
    
    private List<RefundInquiryInsurerBean> refundInsurerList;

    public List<RefundInquiryInsurerBean> getRefundInsurerList() {
        return refundInsurerList;
    }

    public void setRefundInsurerList(List<RefundInquiryInsurerBean> refundInsurerList) {
        this.refundInsurerList = refundInsurerList;
    }

    
}
