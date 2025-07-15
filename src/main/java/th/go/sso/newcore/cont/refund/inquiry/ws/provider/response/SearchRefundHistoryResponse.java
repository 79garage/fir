package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import java.util.List;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHistoryBean;

public class SearchRefundHistoryResponse {
    private List<RefundHistoryBean> historyList;

    public List<RefundHistoryBean> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<RefundHistoryBean> historyList) {
        this.historyList = historyList;
    }
}
