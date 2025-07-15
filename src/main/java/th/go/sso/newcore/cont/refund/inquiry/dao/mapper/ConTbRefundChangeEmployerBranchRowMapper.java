package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployerBranchBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTbRefundChangeEmployerBranchRowMapper implements RowMapper<RefundChangeEmployerBranchBean> {
    @Override
    public RefundChangeEmployerBranchBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundChangeEmployerBranchBean bean = new RefundChangeEmployerBranchBean();
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setApproveStatusChange(rs.getString("approve_status_change"));
        bean.setRefundPeriodYear(rs.getString("refund_period_year"));
        bean.setRefundPeriodMonth(rs.getString("refund_period_month"));
        bean.setRefundAmountEmpe(rs.getBigDecimal("refund_amount_empe"));
        bean.setRefundAmountEmpr(rs.getBigDecimal("refund_amount_empr"));
        bean.setRefundAmountEmpeChange(rs.getBigDecimal("refund_amount_empe_change"));
        bean.setRefundAmountEmprChange(rs.getBigDecimal("refund_amount_empr_change"));
        bean.setRequestAmountEmpe(rs.getBigDecimal("request_amount_empe"));
        bean.setRequestAmountEmpr(rs.getBigDecimal("request_amount_empr"));
        bean.setRequestAmountEmpeChange(rs.getBigDecimal("request_amount_empe_change"));
        bean.setRequestAmountEmprChange(rs.getBigDecimal("request_amount_empr_change"));
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setSourceData(rs.getString("source_data"));
        bean.setRefundChangeEmployerBranchId(rs.getLong("refund_change_employer_branch_id"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setCreateBy(rs.getString("create_by"));
        return bean;
    }
}
