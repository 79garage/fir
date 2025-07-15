package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedEmployeeBean;

public class SelectedEmployeeRowMapper implements RowMapper<SelectedEmployeeBean> {

    @Override
    public SelectedEmployeeBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        SelectedEmployeeBean bean = new SelectedEmployeeBean();

        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setTotalWages(rs.getBigDecimal("TOTAL_WAGES"));
        bean.setRealWages(rs.getBigDecimal("REAL_WAGES"));
        bean.setCntrAmount(rs.getBigDecimal("CNTR_AMOUNT"));
        bean.setEmpeOverp(rs.getBigDecimal("REQUEST_TOTAL_AMOUNT"));
//        bean.setEmpeOverp(rs.getBigDecimal("request_empe_amount"));
        bean.setReceiveEmployeeId(rs.getLong("receive_employee_id"));
        bean.setInsurerId(rs.getLong("INSURER_ID"));
        bean.setInsurerStatus(rs.getString("INSURER_STATUS"));
        bean.setRefundRealWages(rs.getBigDecimal("REFUND_REAL_WAGES"));
        bean.setRefundStatus(rs.getString("REFUND_STATUS"));
        bean.setRequestTotalAmount(rs.getBigDecimal("REQUEST_TOTAL_AMOUNT"));
        bean.setRefundTotalAmount(rs.getBigDecimal("REFUND_TOTAL_AMOUNT"));

        return bean;
    }
}
