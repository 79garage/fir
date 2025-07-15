package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedReceiptBean;

public class SelectRefundRequestReceiptRowMapper implements RowMapper<SelectedReceiptBean> {
    @Override
    public SelectedReceiptBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        SelectedReceiptBean bean = new SelectedReceiptBean();
        bean.setReceiveCompanyId(rs.getLong("RECEIVE_COMPANY_ID"));
        bean.setReceiveInsurerId(rs.getLong("RECEIVE_INSURER_ID"));
        bean.setReceiveEmployeeId(rs.getLong("RECEIVE_EMPLOYEE_ID"));
        bean.setReceiptNo(rs.getString("RECEIPT_NO"));
        bean.setPaymentDate(rs.getDate("PAYMENT_DATE"));
        bean.setTotalAmount(rs.getBigDecimal("TOTAL_AMOUNT"));

        return bean;
    }
}
