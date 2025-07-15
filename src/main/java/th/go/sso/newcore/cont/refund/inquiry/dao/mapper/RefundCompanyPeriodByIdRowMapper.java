package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;

public class RefundCompanyPeriodByIdRowMapper implements RowMapper<RefundCompanyPeriodBean> {

    @Override
    public RefundCompanyPeriodBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RefundCompanyPeriodBean bean = new RefundCompanyPeriodBean();

        bean.setPayPeriod(rs.getString("refund_period"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setReceiveCompanyId(rs.getLong("receive_company_id"));
        bean.setRefundPeriodId(rs.getLong("REFUND_PERIOD_ID"));
        bean.setEmpeAmount(rs.getBigDecimal("empe_amount"));
        bean.setEmprAmount(rs.getBigDecimal("empr_amount"));
        bean.setInterestAmount(rs.getBigDecimal("interest_amount"));
        bean.setTotalAmount(rs.getBigDecimal("total_amount"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRequestEmprAmount(rs.getBigDecimal("request_empr_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRequestInterestAmount(rs.getBigDecimal("request_interest_amount"));
        bean.setRequestOverPaidAmount(rs.getBigDecimal("request_over_paid_amount"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRefundEmprAmount(rs.getBigDecimal("refund_empr_amount"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setRefundOverPaidAmount(rs.getBigDecimal("refund_over_paid_amount"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setApproveStatus(rs.getString("APPROVE_STATUS"));
        bean.setCntrAmount(rs.getBigDecimal("cntr_amount"));
        bean.setInterestPaid(rs.getBigDecimal("interest_paid"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundSicknessRate(rs.getBigDecimal("refund_sickness_rate"));
        bean.setRefundRetirementRate(rs.getBigDecimal("refund_retirement_rate"));
        bean.setRefundUnemploymentRate(rs.getBigDecimal("refund_unemployment_rate"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderStatus(rs.getString("order_status"));
        bean.setOrderRemark(rs.getString("order_remark"));
        bean.setDisApproveReason(rs.getString("disapprove_reason"));
        bean.setPaymentDate(rs.getDate("payment_date"));
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyBranchName(rs.getString("company_branch_name"));

        return bean;
    }
}
