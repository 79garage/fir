package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ContributionInsurerBean;

public class SearchContributionInsurerResponse {

    private ConMsInsurerBean insurerDetail;

    private List<ContributionInsurerBean> receiveInsurerList;

    public ConMsInsurerBean getInsurerDetail() {
        return insurerDetail;
    }

    public void setInsurerDetail(ConMsInsurerBean insurerDetail) {
        this.insurerDetail = insurerDetail;
    }

    public List<ContributionInsurerBean> getReceiveInsurerList() {
        return receiveInsurerList;
    }

    public void setReceiveInsurerList(List<ContributionInsurerBean> receiveInsurerList) {
        this.receiveInsurerList = receiveInsurerList;
    }
}
