package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyRequestBean;

public class SearchRefundCompanyResponse {

    private RefundCompanyRequestBean refundCompanyRequestDetail;
    private List<RefundCompanyPeriodBean> refundCompanyPeriodList;

    public RefundCompanyRequestBean getRefundCompanyRequestDetail() {
        return refundCompanyRequestDetail;
    }

    public void setRefundCompanyRequestDetail(RefundCompanyRequestBean refundCompanyRequestDetail) {
        this.refundCompanyRequestDetail = refundCompanyRequestDetail;
    }

    public List<RefundCompanyPeriodBean> getRefundCompanyPeriodList() {
        return refundCompanyPeriodList;
    }

    public void setRefundCompanyPeriodList(List<RefundCompanyPeriodBean> refundCompanyPeriodList) {
        this.refundCompanyPeriodList = refundCompanyPeriodList;
    }
}
