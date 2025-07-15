package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundInsurerBean;

public class OverRefundInsurerRowMapper implements RowMapper<OverRefundInsurerBean> {

    @Override
    public OverRefundInsurerBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        OverRefundInsurerBean bean = new OverRefundInsurerBean();

        bean.setReceiveInsurerId(rs.getLong("receive_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setPayPeriod(rs.getString("pay_period"));
        bean.setCntrAmount(rs.getBigDecimal("cntr_amount"));
        bean.setInterestPaid(rs.getBigDecimal("interest_paid"));
        bean.setEmpeOverp(rs.getBigDecimal("empe_overp"));
        bean.setOverInterestPaid(rs.getBigDecimal("OVER_INTEREST_PAID"));
        bean.setApproveNo(rs.getString("approve_no"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setPayDate(rs.getDate("payment_date"));
        bean.setPaymentDate(rs.getDate("payment_date"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setNoticeStatus(rs.getString("notice_status"));
        bean.setAnnounceStatus(rs.getString("announce_status"));
        bean.setRefundReasonCode(rs.getString("refund_reason_code"));
        bean.setPayPeriodMonth(rs.getString("pay_period_month"));
        bean.setPayPeriodYear(rs.getString("pay_period_year"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setSection(rs.getString("section"));
        bean.setReverseStatus(rs.getString("reverse_status"));
        bean.setReverseRemark(rs.getString("reverse_remark"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderStatus(rs.getString("order_status"));
        bean.setCountOrderNo(rs.getInt("count_order_no"));
        bean.setRefundAmount(rs.getBigDecimal("refund_amount"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setTotalOverPaid(rs.getBigDecimal("total_over_paid"));
        bean.setRefundPeriodId(rs.getLong("refund_period_id"));

        return bean;
    }
}
