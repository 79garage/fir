package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;

public class SearchRefundCompanyByIdResponse {

    private RefundCompanyRequestBean refundCompanyRequestDetail;
    private List<RefundCompanyPeriodBean> refundCompanyPeriodList;
    private List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList;

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

    public List<SplitEmployeeRefundCompanyPeriodBean> getSplitEmployeePeriodList() {
        return splitEmployeePeriodList;
    }

    public void setSplitEmployeePeriodList(List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList) {
        this.splitEmployeePeriodList = splitEmployeePeriodList;
    }
}
