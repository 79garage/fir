package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;

public class OrderRowMapper implements RowMapper<OrderBean> {
    @Override
    public OrderBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        OrderBean bean = new OrderBean();
        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderStatus(rs.getString("order_status"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setPaymentDate(rs.getDate("payment_date"));
        return bean;
    }
}
