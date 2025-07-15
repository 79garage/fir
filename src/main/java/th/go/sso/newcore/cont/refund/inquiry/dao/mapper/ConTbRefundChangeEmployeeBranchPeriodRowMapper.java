package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeBranchPeriodBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTbRefundChangeEmployeeBranchPeriodRowMapper implements RowMapper<RefundChangeEmployeeBranchPeriodBean> {
    @Override
    public RefundChangeEmployeeBranchPeriodBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundChangeEmployeeBranchPeriodBean bean = new RefundChangeEmployeeBranchPeriodBean();
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setRefundPeriodEndYear(rs.getString("refund_period_end_year"));
        bean.setRefundPeriodEndMonth(rs.getString("refund_period_end_month"));
        bean.setRefundPeriodStrYear(rs.getString("refund_period_str_year"));
        bean.setRefundPeriodStrMonth(rs.getString("refund_period_str_month"));
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setSourceData(rs.getString("source_data"));
        bean.setRefundChangeEmployeeBranchPeriodId(rs.getLong("refund_change_employee_branch_period_id"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setCreateBy(rs.getString("create_by"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundSicknessAmountChange(rs.getBigDecimal("refund_sickness_amount_change"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundRetirementAmountChange(rs.getBigDecimal("refund_retirement_amount_change"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRequestInterestAmount(rs.getBigDecimal("request_interest_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        return bean;
    }
}
