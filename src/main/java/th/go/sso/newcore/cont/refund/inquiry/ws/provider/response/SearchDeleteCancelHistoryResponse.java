package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.SearchDeleteCancelHistoryBean;

public class SearchDeleteCancelHistoryResponse {
    private List<SearchDeleteCancelHistoryBean> historyList;
    private String section;

    public String getSection() {
        return section;
    }

    public List<SearchDeleteCancelHistoryBean> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<SearchDeleteCancelHistoryBean> historyList) {
        this.historyList = historyList;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
