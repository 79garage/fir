package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;

public class ConMsInsurerRowMapper implements RowMapper<ConMsInsurerBean> {
    @Override
    public ConMsInsurerBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        ConMsInsurerBean bean = new ConMsInsurerBean();
        bean.setInsurerId(rs.getLong("insurer_id"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setInsurerStatus(rs.getString("insurer_status"));
        bean.setSection(rs.getString("section"));
        bean.setDepCode(rs.getString("dep_code"));
        bean.setDepName(rs.getString("dep_name"));
        return bean;
    }
}
