package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.EmployeeDetailBean;

public class SearchEmployeeDetailResponse {

    private List<EmployeeDetailBean> employeeList;

    public List<EmployeeDetailBean> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDetailBean> employeeList) {
        this.employeeList = employeeList;
    }
}
