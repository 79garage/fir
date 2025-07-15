package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestReceiptBean;

public class RefundRequestReceiptMapper implements RowMapper<RefundRequestReceiptBean>{

    @Override
    public RefundRequestReceiptBean mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException{

        RefundRequestReceiptBean bean = new RefundRequestReceiptBean();

        bean.setReceiveInsurerId(rs.getInt("receive_insurer_id"));
        bean.setReceiptNo(rs.getString("receipt_no"));

        return bean;
    }
    
}
