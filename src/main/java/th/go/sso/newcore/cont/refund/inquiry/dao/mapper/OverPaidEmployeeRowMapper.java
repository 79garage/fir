package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverPaidBean;

public class OverPaidEmployeeRowMapper implements RowMapper<OverPaidBean> {
    @Override
    public OverPaidBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        OverPaidBean bean = new OverPaidBean();
        bean.setReceiveCompanyId(rs.getLong("RECEIVE_COMPANY_ID"));
        bean.setEmpeOver(rs.getBigDecimal("EMPE_OVER"));
        return bean;
    }
}
