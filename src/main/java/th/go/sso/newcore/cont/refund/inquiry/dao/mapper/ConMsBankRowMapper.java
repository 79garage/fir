package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBean;

public class ConMsBankRowMapper implements RowMapper<ConMsBankBean> {

    @Override
    public ConMsBankBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        ConMsBankBean bean = new ConMsBankBean();

        bean.setBankId(rs.getLong("bank_id"));
        bean.setBankCode(rs.getString("bank_code"));
        bean.setBankName(rs.getString("bank_name"));

        return bean;

    }
}
