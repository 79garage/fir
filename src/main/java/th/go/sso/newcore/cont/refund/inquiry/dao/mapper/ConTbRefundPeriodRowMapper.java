package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundPeriodActiveBean;

public class ConTbRefundPeriodRowMapper implements RowMapper<RefundPeriodActiveBean> {
    @Override
    public RefundPeriodActiveBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundPeriodActiveBean bean = new RefundPeriodActiveBean();
        bean.setRefundPeriodId(rs.getLong("refund_period_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setReceiveCompanyId(rs.getLong("receive_company_id"));
        bean.setReceiveEmployeeId(rs.getLong("receive_employee_id"));
        bean.setReceiveInsurerId(rs.getLong("receive_insurer_id"));
        bean.setRefundPeriod(rs.getString("refund_period"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRequestEmprAmount(rs.getBigDecimal("request_empr_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRequestInterestAmount(rs.getBigDecimal("request_interest_amount"));
        bean.setRequestOverPaidAmount(rs.getBigDecimal("request_over_paid_amount"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRefundEmprAmount(rs.getBigDecimal("refund_empr_amount"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setRefundOverPaidAmount(rs.getBigDecimal("refund_over_paid_amount"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setStatus(rs.getString("status"));
        return bean;
    }
}
