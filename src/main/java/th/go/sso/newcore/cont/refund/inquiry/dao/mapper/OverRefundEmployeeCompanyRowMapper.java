package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeCompanyBean;

public class OverRefundEmployeeCompanyRowMapper implements RowMapper<OverRefundEmployeeCompanyBean> {

    @Override
    public OverRefundEmployeeCompanyBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        OverRefundEmployeeCompanyBean bean = new OverRefundEmployeeCompanyBean();

        bean.setPayPeriod(rs.getString("pay_period"));
        bean.setCompanyId(rs.getLong("company_id"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyBranchName(rs.getString("company_branch_name"));
        bean.setCompanyName(rs.getString("company_name"));
        bean.setPayDate(rs.getDate("pay_date"));
        bean.setTotalWages(rs.getBigDecimal("total_wages"));
        bean.setCntrRate(rs.getBigDecimal("cntr_rate"));
        bean.setCntrAmount(rs.getBigDecimal("cntr_amount"));
        bean.setEmpeOverPaid(rs.getBigDecimal("empe_overp"));
        bean.setRegisterDate(rs.getDate("emp_register_date"));
        bean.setEndDate(rs.getDate("end_date"));
        bean.setStatus(rs.getString("insurer_status"));

        return bean;
    }
}
