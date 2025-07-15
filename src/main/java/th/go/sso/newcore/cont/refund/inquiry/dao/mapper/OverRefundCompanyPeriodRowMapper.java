package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundCompanyPeriodBean;

public class OverRefundCompanyPeriodRowMapper implements RowMapper<OverRefundCompanyPeriodBean> {

    @Override
    public OverRefundCompanyPeriodBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        OverRefundCompanyPeriodBean bean = new OverRefundCompanyPeriodBean();

        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setRefundPeriod(rs.getString("refund_period"));
        bean.setCompanyId(rs.getLong("company_id"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyName(rs.getString("company_name"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setReceiveDate(rs.getDate("receive_date"));
        bean.setRefundEmprAmount(rs.getBigDecimal("refund_empr_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setApproveNo(rs.getString("approve_no"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setPaymentDate(rs.getDate("payment_date"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setOverPaid(rs.getBigDecimal("over_paid"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderStatus(rs.getString("order_status"));
        bean.setModeRefund(rs.getString("mode_refund"));
        bean.setReverseStatus(rs.getString("reverse_status"));
        bean.setReverseRemark(rs.getString("reverse_remark"));
        bean.setNoticeStatus(rs.getString("notice_status"));
        bean.setRefundPeriodId(rs.getLong("refund_period_id"));
        bean.setAnnounceStatus(rs.getString("announce_status"));

        return bean;
    }
}
