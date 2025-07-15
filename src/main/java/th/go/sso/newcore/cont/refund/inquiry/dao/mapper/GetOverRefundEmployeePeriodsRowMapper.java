package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;

public class GetOverRefundEmployeePeriodsRowMapper implements RowMapper<OverRefundEmployeeBean> {
    @Override
    public OverRefundEmployeeBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        OverRefundEmployeeBean bean = new OverRefundEmployeeBean();
        bean.setPayPeriod(rs.getString("pay_period"));
        bean.setCntrAmount(rs.getBigDecimal("sum_cntr_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("over_paid"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("over_paid"));
//        bean.setCountCompany(rs.getInt("count_company"));
//        bean.setApproveNo(rs.getString("approve_no"));
//        bean.setApproveDate(rs.getDate("approve_date"));
//        bean.setReceiptNo(rs.getString("receipt_no"));
//        bean.setPaymentDate(rs.getDate("payment_date"));
//        bean.setProgressStatus(rs.getString("progress_status"));
//        bean.setNoticeStatus(rs.getString("notice_status"));
//        bean.setAnnounceStatus(rs.getString("announce_status"));
//        bean.setDateTemp(rs.getDate("date_temp"));
        bean.setReceiptIds(rs.getString("receipt_id"));
        bean.setPaymentDate(rs.getDate("payment_date"));

        return bean;
    }
}
