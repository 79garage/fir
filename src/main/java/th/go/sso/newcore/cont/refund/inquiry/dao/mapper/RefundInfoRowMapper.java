package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInfoBean;

public class RefundInfoRowMapper implements RowMapper<RefundInfoBean> {
    @Override
    public RefundInfoBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RefundInfoBean bean = new RefundInfoBean();
        bean.setFullName(rs.getString("FULL_NAME"));
        bean.setIdCardNo(rs.getString("ID_CARD_NO"));
        bean.setDepCode(rs.getString("DEP_CODE"));
        bean.setSection(rs.getString("SECTION"));

        return bean;
    }
}
