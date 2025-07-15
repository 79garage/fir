package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ReceiveCompanyBean;

public class SearchContributionCompanyByEmprResponse {

    private ConMsCompanyBean companyDetail;
    private List<ReceiveCompanyBean> receiveCompanyList;

    public ConMsCompanyBean getCompanyDetail() {
        return companyDetail;
    }

    public void setCompanyDetail(ConMsCompanyBean companyDetail) {
        this.companyDetail = companyDetail;
    }

    public List<ReceiveCompanyBean> getReceiveCompanyList() {
        return receiveCompanyList;
    }

    public void setReceiveCompanyList(List<ReceiveCompanyBean> receiveCompanyList) {
        this.receiveCompanyList = receiveCompanyList;
    }
}
