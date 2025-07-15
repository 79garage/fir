package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHoldReceiveBean;

public class RefundHoldReceiveRowMapper implements RowMapper<RefundHoldReceiveBean> {
    @Override
    public RefundHoldReceiveBean mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {

        RefundHoldReceiveBean bean = new RefundHoldReceiveBean();
        bean.setRefundRequestId(rs.getLong("REFUND_REQUEST_ID"));
        bean.setHoldReceiveId(rs.getLong("HOLD_RECEIVE_ID"));
        bean.setFullName(rs.getString("FULL_NAME"));
        bean.setIdCardNo(rs.getString("ID_CARD_NO"));
        bean.setPayPeriod(rs.getString("PAY_PERIOD"));
        bean.setPayPeriodMonth(rs.getString("PAY_PERIOD_MONTH"));
        bean.setPayPeriodYear(rs.getString("PAY_PERIOD_YEAR"));
        bean.setCntrAmount(rs.getBigDecimal("CNTR_AMOUNT"));
        bean.setInterestAmount(rs.getBigDecimal("INTEREST_AMOUNT"));
        bean.setInterestPaid(rs.getBigDecimal("OVER_INTEREST_PAID"));
        bean.setTotalCntr(rs.getBigDecimal("TOTAL_CNTR"));
        bean.setTotalContribution(rs.getBigDecimal("TOTAL_CNTR"));
        bean.setReceiptNo(rs.getString("RECEIPT_NO"));
        bean.setPaymentDate(rs.getDate("PAYMENT_DATE"));
        bean.setSection(rs.getString("SECTION"));
        bean.setOverPaid(rs.getBigDecimal("OVER_PAID"));
        bean.setOverInterestPaid(rs.getBigDecimal("OVER_INTEREST_PAID"));
        bean.setTotalOver(rs.getBigDecimal("TOTAL_OVER"));
        bean.setProgressStatus(rs.getString("PROGRESS_STATUS"));
        bean.setRefundReasonCode(rs.getString("REFUND_REASON_CODE"));
        bean.setReverseStatus(rs.getString("REVERSE_STATUS"));
        bean.setReverseRemark(rs.getString("REVERSE_REMARK"));
        bean.setReceiveNo(rs.getString("RECEIVE_NO"));
        bean.setApproveDate(rs.getDate("APPROVE_DATE"));

        return bean;
    }
}
