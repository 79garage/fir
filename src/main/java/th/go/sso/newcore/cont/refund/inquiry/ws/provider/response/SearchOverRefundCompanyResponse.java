package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundCompanyPeriodBean;

public class SearchOverRefundCompanyResponse {
    private List<OverRefundCompanyPeriodBean> refundCompanyPeriodList;

    public List<OverRefundCompanyPeriodBean> getRefundCompanyPeriodList() {
        return refundCompanyPeriodList;
    }

    public void setRefundCompanyPeriodList(List<OverRefundCompanyPeriodBean> refundCompanyPeriodList) {
        this.refundCompanyPeriodList = refundCompanyPeriodList;
    }
}
