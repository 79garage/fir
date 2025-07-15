package th.go.sso.newcore.cont.refund.inquiry.bean;

import java.util.Date;

import th.go.sso.newcore.cont.common.dto.AuditBean;
import th.go.sso.newcore.cont.common.dto.IdentityInfo;

public class ConTbInformationBean extends AuditBean implements IdentityInfo {

    private Long informationId;
    private String subject;
    private String value1;
    private String value2;
    private String value3;
    private String groupCode;
    private String sysCode;
    private String remark;
    private Date startDate;
    private Date endDate;

    @Override
    public Long getId() {
        return informationId;
    }

    @Override
    public void setId(Long id) {
        this.informationId = id;
    }

    public Long getInformationId() {
        return informationId;
    }

    public void setInformationId(Long informationId) {
        this.informationId = informationId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
