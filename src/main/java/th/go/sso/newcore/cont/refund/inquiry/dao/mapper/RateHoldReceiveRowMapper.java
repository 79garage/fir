package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RateHoldReceiveBean;

public class RateHoldReceiveRowMapper implements RowMapper<RateHoldReceiveBean> {
    @Override
    public RateHoldReceiveBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RateHoldReceiveBean bean = new RateHoldReceiveBean();
        bean.setReceiptNo(rs.getString("RECEIPT_NO"));
        bean.setReceiptName(rs.getString("RECEIPT_NAME"));
        bean.setIdCardNo(rs.getString("ID_CARD_NO"));
        bean.setPayPeriod(rs.getString("PAY_PERIOD"));
        bean.setPayPeriodMonth(rs.getString("PAY_PERIOD_MONTH"));
        bean.setPayPeriodYear(rs.getString("PAY_PERIOD_YEAR"));
        bean.setCntrAmount(rs.getBigDecimal("CNTR_AMOUNT"));
        bean.setReasonCode(rs.getString("REASON_CODE"));
        bean.setRefundCntrRate(rs.getBigDecimal("REFUND_CNTR_RATE"));
        bean.setRefundSicknessRate(rs.getBigDecimal("REFUND_SICKNESS_RATE"));
        bean.setRefundRetirementRate(rs.getBigDecimal("REFUND_RETIREMENT_RATE"));
        bean.setRefundSickness(rs.getBigDecimal("REFUND_SICKNESS"));
        bean.setRefundRetirement(rs.getBigDecimal("REFUND_RETIREMENT"));

        return bean;
    }
}
