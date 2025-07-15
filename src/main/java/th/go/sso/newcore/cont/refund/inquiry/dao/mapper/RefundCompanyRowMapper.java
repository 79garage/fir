package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyRequestBean;

public class RefundCompanyRowMapper implements RowMapper<RefundCompanyRequestBean> {

    @Override
    public RefundCompanyRequestBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RefundCompanyRequestBean bean = new RefundCompanyRequestBean();

        bean.setSsoBranchCode(rs.getString("sso_branch_code"));
        bean.setSsoBranchName(rs.getString("sso_branch_name"));
//        bean.setSsoBranchDescription(rs.getString("ssoBranchDescription"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setCompanyName(rs.getString("company_name"));
        bean.setCompanyBranchDescription(rs.getString("companyBranchDescription"));
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyDescription(rs.getString("companyDescription"));
        bean.setCompanyBranchName(rs.getString("company_branch_name"));
        bean.setBusinessType(rs.getString("business_type"));
//        bean.setDepartmentResponsibleCode("sso_branch_code");
//        bean.setRefundRequestId(rs.getInt("refund_request_id"));
//        bean.setReceiveNo(rs.getString("receive_no"));
//        bean.setRequestType(rs.getString("request_type"));
//        bean.setNoticeNo(rs.getString("notice_no"));
//        bean.setNoticeDate(rs.getDate("notice_date"));
//        bean.setNoticeStatus(rs.getString("notice_status"));


        return bean;
    }
}
