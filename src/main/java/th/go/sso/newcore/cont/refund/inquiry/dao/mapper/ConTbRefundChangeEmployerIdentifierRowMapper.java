package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployerIdentifierBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTbRefundChangeEmployerIdentifierRowMapper implements RowMapper<RefundChangeEmployerIdentifierBean> {
    @Override
    public RefundChangeEmployerIdentifierBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundChangeEmployerIdentifierBean bean = new RefundChangeEmployerIdentifierBean();
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setIdCardNoChange(rs.getString("id_card_no_change"));
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setSourceData(rs.getString("source_data"));
        bean.setRefundChangeEmployerIdentifierId(rs.getLong("refund_change_employer_identifier_id"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setCreateBy(rs.getString("create_by"));
        return bean;
    }
}
