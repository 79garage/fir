package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.util.List;

public class RefundPeriodActiveAndInactiveBean {

    private List<RefundPeriodActiveBean> refundPeriodActiveList;
    private List<RefundPeriodActiveBean> refundPeriodInactiveList;

    public List<RefundPeriodActiveBean> getRefundPeriodActiveList() {
        return refundPeriodActiveList;
    }

    public List<RefundPeriodActiveBean> getRefundPeriodInactiveList() {
        return refundPeriodInactiveList;
    }

    public void setRefundPeriodActiveList(List<RefundPeriodActiveBean> refundPeriodActiveList) {
        this.refundPeriodActiveList = refundPeriodActiveList;
    }

    public void setRefundPeriodInactiveList(List<RefundPeriodActiveBean> refundPeriodInactiveList) {
        this.refundPeriodInactiveList = refundPeriodInactiveList;
    }
}
