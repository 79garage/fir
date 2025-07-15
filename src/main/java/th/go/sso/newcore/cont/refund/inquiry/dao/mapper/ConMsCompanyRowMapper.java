package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsCompanyBean;

public class ConMsCompanyRowMapper implements RowMapper<ConMsCompanyBean> {
    @Override
    public ConMsCompanyBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        ConMsCompanyBean bean = new ConMsCompanyBean();
        bean.setCompanyId(rs.getInt(""));
        bean.setCompanyNo(rs.getString(""));
        bean.setCompanyName(rs.getString(""));

        return bean;
    }
}
