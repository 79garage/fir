package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;

public class RefundEmployeeMultipleCompaniesRowMapper implements RowMapper<OverRefundEmployeeBean> {

    @Override
    public OverRefundEmployeeBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        OverRefundEmployeeBean bean = new OverRefundEmployeeBean();

        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setPayPeriod(rs.getString("pay_period"));
        bean.setSumAmount(rs.getBigDecimal("sum_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("over_paid"));
        bean.setRate(rs.getInt("cntr_rate"));
        bean.setTotalWages(rs.getBigDecimal("TOTAL_WAGES"));
        bean.setCompanyNo(rs.getString("COMPANY_NO"));
        bean.setCountCompany(rs.getInt("count_company"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setApproveNo(rs.getString("approve_no"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setPaymentDate(rs.getDate("payment_date"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setNoticeStatus(rs.getString("notice_status"));
        bean.setAnnounceStatus(rs.getString("announce_status"));
        bean.setReverseStatus(rs.getString("REVERSE_STATUS"));
        bean.setReverseRemark(rs.getString("reverse_remark"));
        bean.setRefundReasonCode(rs.getString("refund_reason_code"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderStatus(rs.getString("order_status"));
        bean.setCountOrderNo(rs.getInt("count_order_no"));
        bean.setRefundPeriodId(rs.getLong("refund_period_id"));

        return bean;
    }
}
