package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundEmployeeBean;

public class RefundEmployeeRowMapper implements RowMapper<RefundEmployeeBean> {

    @Override
    public RefundEmployeeBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RefundEmployeeBean bean = new RefundEmployeeBean();

        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setTotalWages(rs.getBigDecimal("TOTAL_WAGES"));
        bean.setRealWages(rs.getBigDecimal("REAL_WAGES"));
        bean.setCntrAmount(rs.getBigDecimal("CNTR_AMOUNT"));
        bean.setEmpeOverp(rs.getBigDecimal("empe_overp"));
        bean.setReceiveEmployeeId(rs.getLong("receive_employee_id"));
        bean.setInsurerId(rs.getLong("INSURER_ID"));
        bean.setInsurerStatus(rs.getString("INSURER_STATUS"));
        bean.setRefundRealWages(rs.getBigDecimal("REFUND_REAL_WAGES"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setRefundStatus(rs.getString("refund_status"));
        bean.setRefundReasonCode(rs.getString("refund_reason_code"));

        return bean;

    }
}
