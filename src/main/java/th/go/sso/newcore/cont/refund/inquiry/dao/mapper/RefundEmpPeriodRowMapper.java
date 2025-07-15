package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;

public class RefundEmpPeriodRowMapper implements RowMapper<OverRefundEmployeeBean> {

    @Override
    public OverRefundEmployeeBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        OverRefundEmployeeBean bean = new OverRefundEmployeeBean();
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setPayPeriod(rs.getString("refund_period"));
        bean.setRefundPeriodId(rs.getLong("refund_period_id"));
        bean.setRecordApproveBy(rs.getString("record_approve_by"));
        bean.setRecordApproveDate(rs.getDate("record_approve_date"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setRequestInterestAmount(rs.getBigDecimal("request_interest_amount"));
        bean.setCntrAmount(rs.getBigDecimal("cntr_amount"));
        bean.setProgressStatus(rs.getString("progress_status"));

        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderStatus(rs.getString("order_status"));
        bean.setOrderRemark(rs.getString("order_remark"));
        bean.setRefundSicknessRate(rs.getBigDecimal("refund_sickness_rate"));
        bean.setRefundRetirementRate(rs.getBigDecimal("refund_retirement_rate"));
        bean.setRefundUnemploymentRate(rs.getBigDecimal("refund_unemployment_rate"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setDisApproveReason(rs.getString("disapprove_reason"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setInsurerId(rs.getLong("insurer_id"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setPaymentDate(rs.getDate("payment_date"));

        return bean;

    }
}
