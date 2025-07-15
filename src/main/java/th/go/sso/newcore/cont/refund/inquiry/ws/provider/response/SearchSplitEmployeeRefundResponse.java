package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyRequestBean;

public class SearchSplitEmployeeRefundResponse {

    private SplitEmployeeRefundCompanyRequestBean refundCompanyRequestDetail;
    private List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList;
    private List<RefundCompanyPeriodBean> refundCompanyPeriodList;

    public SplitEmployeeRefundCompanyRequestBean getRefundCompanyRequestDetail() {
        return refundCompanyRequestDetail;
    }

    public void setRefundCompanyRequestDetail(SplitEmployeeRefundCompanyRequestBean refundCompanyRequestDetail) {
        this.refundCompanyRequestDetail = refundCompanyRequestDetail;
    }

    public List<SplitEmployeeRefundCompanyPeriodBean> getSplitEmployeePeriodList() {
        return splitEmployeePeriodList;
    }

    public void setSplitEmployeePeriodList(List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList) {
        this.splitEmployeePeriodList = splitEmployeePeriodList;
    }

    public List<RefundCompanyPeriodBean> getRefundCompanyPeriodList() {
        return refundCompanyPeriodList;
    }

    public void setRefundCompanyPeriodList(List<RefundCompanyPeriodBean> refundCompanyPeriodList) {
        this.refundCompanyPeriodList = refundCompanyPeriodList;
    }
}
