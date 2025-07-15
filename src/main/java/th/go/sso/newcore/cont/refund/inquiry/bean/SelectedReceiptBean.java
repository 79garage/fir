package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class SelectedReceiptBean {

    private Long receiveCompanyId;
    private Long receiveInsurerId;
    private Long receiveEmployeeId;
    private String receiptNo;
    private BigDecimal totalAmount;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date paymentDate;

    public Long getReceiveCompanyId() {
        return receiveCompanyId;
    }

    public void setReceiveCompanyId(Long receiveCompanyId) {
        this.receiveCompanyId = receiveCompanyId;
    }

    public Long getReceiveInsurerId() {
        return receiveInsurerId;
    }

    public void setReceiveInsurerId(Long receiveInsurerId) {
        this.receiveInsurerId = receiveInsurerId;
    }

    public Long getReceiveEmployeeId() {
        return receiveEmployeeId;
    }

    public void setReceiveEmployeeId(Long receiveEmployeeId) {
        this.receiveEmployeeId = receiveEmployeeId;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
