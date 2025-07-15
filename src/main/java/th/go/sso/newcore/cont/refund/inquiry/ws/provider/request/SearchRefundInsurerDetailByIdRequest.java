package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import java.util.List;

import th.go.sso.newcore.cont.common.dto.RequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiveHoldReceiveBean;

public class SearchRefundInsurerDetailByIdRequest extends RequestBean{

    private Long insurerId;
    private List<RefundReceiveHoldReceiveBean> receiveIdList; // 1 = con_tr_receive_insurer, 7 = con_tb_hold_receive
    private String mode;

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long insurerId) {
        this.insurerId = insurerId;
    }

    public List<RefundReceiveHoldReceiveBean> getReceiveIdList() {
        return receiveIdList;
    }

    public void setReceiveIdList(List<RefundReceiveHoldReceiveBean> receiveIdList) {
        this.receiveIdList = receiveIdList;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
