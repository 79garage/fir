package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import th.go.sso.newcore.cont.common.serializer.DateTimeDeserializer;
import th.go.sso.newcore.cont.common.serializer.DateTimeSerializer;

public class RefundHistoryBean {
    private Long refundRequestChangeTypeId;
    private Long refundRequestId;
    private String receiveNo;
    private String refundChangeType;
    private String refundOperationCode;
    private String section;
    private String createBy;
    @JsonSerialize(converter = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private Date createDate;
    private Long refRefundRequestId;
    private String originalReceiveNo;
    private String sourceCode;

    public Long getRefundRequestChangeTypeId() {
        return refundRequestChangeTypeId;
    }

    public void setRefundRequestChangeTypeId(Long refundRequestChangeTypeId) {
        this.refundRequestChangeTypeId = refundRequestChangeTypeId;
    }

    public Long getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Long refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getRefundChangeType() {
        return refundChangeType;
    }

    public void setRefundChangeType(String refundChangeType) {
        this.refundChangeType = refundChangeType;
    }

    public String getRefundOperationCode() {
        return refundOperationCode;
    }

    public void setRefundOperationCode(String refundOperationCode) {
        this.refundOperationCode = refundOperationCode;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getRefRefundRequestId() {
        return refRefundRequestId;
    }

    public void setRefRefundRequestId(Long refRefundRequestId) {
        this.refRefundRequestId = refRefundRequestId;
    }

    public String getOriginalReceiveNo() {
        return originalReceiveNo;
    }

    public void setOriginalReceiveNo(String originalReceiveNo) {
        this.originalReceiveNo = originalReceiveNo;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
