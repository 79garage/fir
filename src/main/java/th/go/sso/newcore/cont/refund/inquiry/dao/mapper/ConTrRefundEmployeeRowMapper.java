package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundBean;

public class ConTrRefundEmployeeRowMapper implements RowMapper<SplitEmployeeRefundBean> {
    @Override
    public SplitEmployeeRefundBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        SplitEmployeeRefundBean bean = new SplitEmployeeRefundBean();
        bean.setRefundEmployeeId(rs.getLong("REFUND_EMPLOYEE_ID"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        return bean;
    }
}
