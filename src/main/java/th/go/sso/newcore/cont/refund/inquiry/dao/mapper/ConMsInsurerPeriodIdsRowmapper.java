package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;

public class ConMsInsurerPeriodIdsRowmapper implements RowMapper<RefundInsurerBean> {
    @Override
    public RefundInsurerBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RefundInsurerBean bean = new RefundInsurerBean();

        bean.setIdCardNo(rs.getString("ID_CARD_NO"));
        bean.setFullName(rs.getString("FULL_NAME"));
        bean.setInsurerStatus(rs.getString("INSURER_STATUS"));
        bean.setDepCode(rs.getString("DEP_CODE"));
        bean.setDepartmentResponsibleCode(rs.getString("DEP_CODE"));
        bean.setSection(rs.getString("SECTION"));
        bean.setInsurerId(rs.getLong("INSURER_ID"));

        return bean;
    }
}
