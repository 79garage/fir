package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeIdentifierBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTbRefundChangeEmployeeIdentifierRowMapper implements RowMapper<RefundChangeEmployeeIdentifierBean> {
    @Override
    public RefundChangeEmployeeIdentifierBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundChangeEmployeeIdentifierBean bean = new RefundChangeEmployeeIdentifierBean();
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setIdCardNoChange(rs.getString("id_card_no_change"));
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setSourceData(rs.getString("source_data"));
        bean.setRefundChangeEmployeeIdentifierId(rs.getLong("refund_change_employee_identifier_id"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setCreateBy(rs.getString("create_by"));
        return bean;
    }
}
