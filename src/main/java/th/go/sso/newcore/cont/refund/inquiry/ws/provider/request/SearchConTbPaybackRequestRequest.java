package th.go.sso.newcore.cont.refund.inquiry.ws.provider.request;

import th.go.sso.newcore.cont.common.dto.SearchRequest;

public class SearchConTbPaybackRequestRequest extends SearchRequest {

    private String category; //ประเภทผู้ใข้งาน
    private String section;
    private String paybackApproveStatus;
    private String paybackRequestNo;
    private Long companyId;
    private String companyNo;
    private String idCardNo;
//    private String paybackRequestNo;
//    private String idCardNo;
//    private Long companyId;
//    private String status;
//    private String paybackApproveStatus;
//    private String section;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPaybackApproveStatus() {
        return paybackApproveStatus;
    }

    public void setPaybackApproveStatus(String paybackApproveStatus) {
        this.paybackApproveStatus = paybackApproveStatus;
    }

    public String getPaybackRequestNo() {
        return paybackRequestNo;
    }

    public void setPaybackRequestNo(String paybackRequestNo) {
        this.paybackRequestNo = paybackRequestNo;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
}
