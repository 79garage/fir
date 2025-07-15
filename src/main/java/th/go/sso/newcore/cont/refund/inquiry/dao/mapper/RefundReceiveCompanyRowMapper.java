package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ReceiveCompanyBean;

public class RefundReceiveCompanyRowMapper implements RowMapper<ReceiveCompanyBean> {

    @Override
    public ReceiveCompanyBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        ReceiveCompanyBean bean = new ReceiveCompanyBean();

        bean.setPayPeriod(rs.getString("PAY_PERIOD"));
        bean.setPayPeriodMonth(rs.getString("PAY_PERIOD_MONTH"));
        bean.setPayPeriodYear(rs.getString("PAY_PERIOD_YEAR"));
        bean.setRefundRequestId(rs.getLong("REFUND_REQUEST_ID"));
        bean.setReceiveCompanyId(rs.getLong("RECEIVE_COMPANY_ID"));
        bean.setPayDateOfCntr(rs.getDate("PAYDATEOFCNTR"));
        bean.setCntrRate(rs.getString("CNTR_RATE"));
        bean.setNumOfEmployee(rs.getInt("NUM_OF_EMPLOYEE"));
        bean.setTotalWages(rs.getBigDecimal("TOTAL_WAGES"));
        bean.setEmprCntr(rs.getBigDecimal("EMPR_CNTR"));
        bean.setEmpeCntr(rs.getBigDecimal("EMPE_CNTR"));
        bean.setCntrAmount(rs.getBigDecimal("CNTR_AMOUNT"));
        bean.setApproveNo(rs.getString("APPROVE_NO"));
        bean.setApproveDate(rs.getDate("APPROVE_DATE"));
        bean.setReceiptNo(rs.getString("RECEIPT_NO"));
        bean.setPayDate(rs.getDate("PAYMENT_DATE"));
        bean.setProgressStatus(rs.getString("PROGRESS_STATUS"));
        bean.setNoticeStatus(rs.getString("NOTICE_STATUS"));
        bean.setAnnounceStatus(rs.getString("ANNOUNCE_STATUS"));
        bean.setReverseStatus(rs.getString("REVERSE_STATUS"));
        bean.setApproveStatus(rs.getString("APPROVE_STATUS"));
        bean.setReverseRemark(rs.getString("REVERSE_REMARK"));
        bean.setOrderNo(rs.getString("ORDER_NO"));
        bean.setOrderStatus(rs.getString("ORDER_STATUS"));
        bean.setCompanyId(rs.getLong("COMPANY_ID"));
        bean.setCompanyBranchCode(rs.getString("COMPANY_BRANCH_CODE"));
        bean.setCompanyBranchName(rs.getString("COMPANY_BRANCH_NAME"));

        return bean;
    }
}
