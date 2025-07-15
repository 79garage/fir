package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.CheckPaybackBean;

public class CheckPaybackRowMapper implements RowMapper<CheckPaybackBean> {
    @Override
    public CheckPaybackBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        CheckPaybackBean bean = new CheckPaybackBean();
        bean.setPaybackRequestNo(rs.getString("PAYBACK_REQUEST_NO"));
        bean.setPaybackApproveStatus(rs.getString("PAYBACK_APPROVE_STATUS"));
        return bean;
    }
}
