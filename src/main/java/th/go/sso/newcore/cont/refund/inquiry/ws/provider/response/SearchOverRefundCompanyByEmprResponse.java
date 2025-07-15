package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundCompanyBean;

public class SearchOverRefundCompanyByEmprResponse {

    private ConMsCompanyBean companyDetail;
    private List<OverRefundCompanyBean> refundCompanyList;

    public ConMsCompanyBean getCompanyDetail() {
        return companyDetail;
    }

    public void setCompanyDetail(ConMsCompanyBean companyDetail) {
        this.companyDetail = companyDetail;
    }

    public List<OverRefundCompanyBean> getRefundCompanyList() {
        return refundCompanyList;
    }

    public void setRefundCompanyList(List<OverRefundCompanyBean> refundCompanyList) {
        this.refundCompanyList = refundCompanyList;
    }
}
