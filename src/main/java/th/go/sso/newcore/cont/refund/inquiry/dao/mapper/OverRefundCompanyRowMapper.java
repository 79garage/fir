package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundCompanyBean;

public class OverRefundCompanyRowMapper implements RowMapper<OverRefundCompanyBean> {

    @Override
    public OverRefundCompanyBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        OverRefundCompanyBean bean = new OverRefundCompanyBean();

        bean.setPayPeriod(rs.getString("pay_period"));
        bean.setPayPeriodMonth(rs.getString("pay_period_month"));
        bean.setPayPeriodYear(rs.getString("pay_period_year"));
        bean.setReceiveCompanyId(rs.getLong("receive_company_id"));
        bean.setCompanyId(rs.getLong("company_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setPayDateOfCntr(rs.getDate("PayDateOfCntr"));
        bean.setPaymentDate(rs.getDate("PAYMENT_DATE"));
        bean.setTotalWages(rs.getBigDecimal("total_wages"));
        bean.setCntrRate(rs.getString("cntr_rate"));
        bean.setNumOfEmployee(rs.getInt("num_of_employee"));
        bean.setCntrAmount(rs.getBigDecimal("cntr_amount"));
        bean.setInterestPaid(rs.getBigDecimal("interest_paid"));
        bean.setOverPaid(rs.getBigDecimal("over_paid"));
        bean.setInterestAmount(rs.getBigDecimal("interest_amount"));
        bean.setApproveNo(rs.getString("approve_no"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setPayDate(rs.getDate("payment_date"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setNoticeStatus(rs.getString("notice_status"));
        bean.setAnnounceStatus(rs.getString("announce_status"));
        bean.setRequestReasonCode(rs.getString("REQUEST_REASON_CODE"));
        bean.setRequestReasonOther(rs.getString("REQUEST_REASON_OTHER"));
        bean.setRefundReasonCode(rs.getString("REFUND_REASON_CODE"));
        bean.setReverseStatus(rs.getString("REVERSE_STATUS"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setReverseRemark(rs.getString("reverse_remark"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderStatus(rs.getString("order_status"));
        bean.setTotalContribution(rs.getBigDecimal("total_contribution"));
        bean.setTotalOver(rs.getBigDecimal("total_over"));
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyBranchName(rs.getString("company_branch_name"));
        bean.setEligibleFlag(rs.getString("ELIGIBLE_FLAG"));
        bean.setRefundPeriodId(rs.getLong("refund_period_id"));

        return bean;
    }

}
