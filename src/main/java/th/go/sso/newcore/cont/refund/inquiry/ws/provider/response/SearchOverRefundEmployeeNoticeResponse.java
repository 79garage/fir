package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;

public class SearchOverRefundEmployeeNoticeResponse {
    private List<OverRefundEmployeeBean> refundEmployeeList;

    public List<OverRefundEmployeeBean> getRefundEmployeeList() {
        return refundEmployeeList;
    }

    public void setRefundEmployeeList(List<OverRefundEmployeeBean> refundEmployeeList) {
        this.refundEmployeeList = refundEmployeeList;
    }
}
