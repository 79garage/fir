package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.EmployeeDetailBean;

public class EmployeeDetailRowMapper implements RowMapper<EmployeeDetailBean> {
    @Override
    public EmployeeDetailBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        EmployeeDetailBean bean = new EmployeeDetailBean();

        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setCntrAmont(rs.getBigDecimal("CNTR_AMOUNT"));
        bean.setEmpeOverp(rs.getBigDecimal("empe_overp"));
        bean.setReceiveEmployeeId(rs.getLong("receive_employee_id"));
        bean.setInsurerId(rs.getLong("INSURER_ID"));
        bean.setRefundReasonCode(rs.getString("refund_reason_code"));
        return bean;
    }
}
