package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHistoryBean;

public class ConTbRefundRequestChangeTypeRowMapper implements RowMapper<RefundHistoryBean> {
    @Override
    public RefundHistoryBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundHistoryBean bean = new RefundHistoryBean();
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setRefundChangeType(rs.getString("refund_change_type"));
        bean.setRefundOperationCode(rs.getString("refund_operation_code"));
        bean.setCreateBy(rs.getString("create_by"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setOriginalReceiveNo(rs.getString("original_receive_no"));
        bean.setRefRefundRequestId(getNullableLong(rs, "ref_refund_request_id"));
        bean.setSourceCode(rs.getString("source_data"));
        bean.setSection(rs.getString("section"));
        return bean;
    }

    private Long getNullableLong(ResultSet rs, String columnName) throws SQLException {
        Long value = rs.getLong(columnName);
        return rs.wasNull() ? null : value;
    }
}
