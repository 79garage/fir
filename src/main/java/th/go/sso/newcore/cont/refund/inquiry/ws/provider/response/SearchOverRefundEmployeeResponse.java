package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;

public class SearchOverRefundEmployeeResponse {

    private ConMsInsurerBean insurerDetail;
    private List<OverRefundEmployeeBean> refundEmployeeList;

    public ConMsInsurerBean getInsurerDetail() {
        return insurerDetail;
    }

    public void setInsurerDetail(ConMsInsurerBean insurerDetail) {
        this.insurerDetail = insurerDetail;
    }

    public List<OverRefundEmployeeBean> getRefundEmployeeList() {
        return refundEmployeeList;
    }

    public void setRefundEmployeeList(List<OverRefundEmployeeBean> refundEmployeeList) {
        this.refundEmployeeList = refundEmployeeList;
    }

}
