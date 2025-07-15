package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsCompanyBean;


public class MasterCompanyRowMapper implements RowMapper<ConMsCompanyBean> {

    @Override
    public ConMsCompanyBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        ConMsCompanyBean bean = new ConMsCompanyBean();

        bean.setCompanyId(rs.getInt("company_id"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setCompanyName(rs.getString("company_name"));
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyBranchName(rs.getString("company_branch_name"));
        bean.setCompanyBranchDescription(rs.getString("companyBranchDescription"));
        bean.setSsoBranchCode(rs.getString("sso_branch_code"));
        bean.setSsoBranchName(rs.getString("sso_branch_name"));
        bean.setSsoBranchDescription(rs.getString("ssoBranchDescription"));
        bean.setDepCode(rs.getString("sso_branch_code"));
        bean.setBusinessType(rs.getString("business_type"));

        return bean;
    }
}
