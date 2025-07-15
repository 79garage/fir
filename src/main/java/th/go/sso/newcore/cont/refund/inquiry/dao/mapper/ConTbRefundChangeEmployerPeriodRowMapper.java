package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployerPeriodBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTbRefundChangeEmployerPeriodRowMapper implements RowMapper<RefundChangeEmployerPeriodBean> {
    @Override
    public RefundChangeEmployerPeriodBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundChangeEmployerPeriodBean bean = new RefundChangeEmployerPeriodBean();
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setSourceData(rs.getString("source_data"));
        bean.setRefundChangeEmployerPeriodId(rs.getLong("refund_change_employer_period_id"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setCreateBy(rs.getString("create_by"));
        bean.setRefundPeriodYear(rs.getString("refund_period_year"));
        bean.setRefundPeriodMonth(rs.getString("refund_period_month"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundSicknessAmountChange(rs.getBigDecimal("refund_sickness_amount_change"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundRetirementAmountChange(rs.getBigDecimal("refund_retirement_amount_change"));
        bean.setRequestInterestAmount(rs.getBigDecimal("request_interest_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundUnemploymentAmountChange(rs.getBigDecimal("refund_unemployment_amount_change"));
        bean.setRefundRealWages(rs.getBigDecimal("refund_real_wages"));
        bean.setRefundRealWagesChange(rs.getBigDecimal("refund_real_wages_change"));
        bean.setRequestInterestAmountChange(rs.getBigDecimal("request_interest_amount_change"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setApproveStatusChange(rs.getString("approve_status_change"));
        return bean;
    }
}
