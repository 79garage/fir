package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBranchBean;

public class ConMsBankBranchRowMapper implements RowMapper<ConMsBankBranchBean> {

    @Override
    public ConMsBankBranchBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        ConMsBankBranchBean bean = new ConMsBankBranchBean();

        bean.setBankId(rs.getInt("bank_id"));
        bean.setBankBranchCode(rs.getString("bank_branch_code"));
        bean.setBankBranchId(rs.getInt("bank_branch_id"));
        bean.setBankBranchName(rs.getString("bank_branch_name"));
        bean.setBankBranchIdentifier(rs.getString("bank_branch_identifier"));

        return bean;
    }
}
