package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsLocationBean;

public class ConMsLocationRowMapper implements RowMapper<ConMsLocationBean> {
    @Override
    public ConMsLocationBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        ConMsLocationBean bean = new ConMsLocationBean();
        bean.setProvCode(rs.getString("PROV_CODE"));
        bean.setProvName(rs.getString("PROV_NAME"));
        bean.setDistCode(rs.getString("DIST_CODE"));
        bean.setDistName(rs.getString("DIST_NAME"));
        bean.setSubDistCode(rs.getString("SUBDIST_CODE"));
        bean.setSubDistName(rs.getString("SUBDIST_NAME"));

        return bean;
    }
}
