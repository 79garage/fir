package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;

import java.sql.ResultSet;import org.springframework.lang.NonNull;
import java.sql.SQLException;

public class ReceiveInsurerPeriodRowMapper implements RowMapper<RefundInsurerPeriodListBean>{

    @Override
    public RefundInsurerPeriodListBean mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException{

        RefundInsurerPeriodListBean bean = new RefundInsurerPeriodListBean();

        bean.setPayPeriod(rs.getString("PAY_PERIOD"));
        bean.setCntrAmount(rs.getBigDecimal("CNTR_AMOUNT"));
        bean.setInterestPaid(rs.getBigDecimal("INTEREST_PAID"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("EMPE_OVERP"));
        bean.setRequestInterestAmount(rs.getBigDecimal("OVER_INTEREST_PAID"));
        bean.setRequestTotalAmount(NumberUtils.convertZeroIfNull(bean.getRequestEmpeAmount()).add(NumberUtils.convertZeroIfNull(bean.getRequestInterestAmount())));
        bean.setReceiveInsurerId(rs.getLong("RECEIVE_INSURER_ID"));
        bean.setSection(rs.getString("SECTION"));
        bean.setRefundReasonCode(rs.getString("REFUND_REASON_CODE"));
        bean.setRefundAmount(rs.getBigDecimal("REFUND_AMOUNT"));
        bean.setRefundInterestAmount(rs.getBigDecimal("REFUND_INTEREST_AMOUNT"));
        bean.setInsurerId(rs.getLong("INSURER_ID"));
        bean.setPaymentDate(rs.getDate("PAYMENT_DATE"));
        bean.setRefundSicknessRate(rs.getBigDecimal("REAL_SICKNESS_RATE"));
        bean.setRefundRetirementRate(rs.getBigDecimal("REAL_RETIREMENT_RATE"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("REFUND_SICKNESS"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("REFUND_RETIREMENT"));
        bean.setRefundSumRate(rs.getBigDecimal("REAL_CNTR_RATE"));
        bean.setFullName(rs.getString("FULL_NAME"));
        bean.setIdCardNo(rs.getString("ID_CARD_NO"));

        return bean;
    }
    
}
