package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsDepartmentBean;

public class ConMsDepartmentRowMapper implements RowMapper<ConMsDepartmentBean> {

    @Override
    public ConMsDepartmentBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        ConMsDepartmentBean bean = new ConMsDepartmentBean();

        bean.setDepartmentId(rs.getLong("DEPARTMENT_ID"));
        bean.setDepCode(rs.getString("DEP_CODE"));
        bean.setDepName(rs.getString("DEP_NAME"));

        return bean;
    }
}
