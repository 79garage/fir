package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;


import th.go.sso.newcore.cont.common.dto.RequestBean;

public class GetRefundRequestByReceiveNoRequest extends RequestBean {

    private String receiveNo;

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }
}
