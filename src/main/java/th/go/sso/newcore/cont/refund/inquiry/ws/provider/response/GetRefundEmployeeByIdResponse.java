package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;

public class GetRefundEmployeeByIdResponse {
    private SplitEmployeeRefundCompanyPeriodBean employeeDetails;

    public SplitEmployeeRefundCompanyPeriodBean getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(SplitEmployeeRefundCompanyPeriodBean employeeDetails) {
        this.employeeDetails = employeeDetails;
    }
}
