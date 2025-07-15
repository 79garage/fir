package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedReceiptBean;

public class ConTbReceiptExistsRowMapper implements RowMapper<SelectedReceiptBean> {

    @Override
    public SelectedReceiptBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        SelectedReceiptBean bean = new SelectedReceiptBean();

        bean.setReceiveCompanyId(rs.getLong("receive_company_id"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setPaymentDate(rs.getDate("payment_date"));
        bean.setTotalAmount(rs.getBigDecimal("total_amount"));

        return bean;
    }
}
