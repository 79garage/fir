package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;

public class RefundEmpPeriodReceiptIdRowMapper implements RowMapper<OverRefundEmployeeBean> {
    @Override
    public OverRefundEmployeeBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        OverRefundEmployeeBean bean = new OverRefundEmployeeBean();
        bean.setReceiptIds(rs.getString("receipt_id"));
        return bean;
    }
}
