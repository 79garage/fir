package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;

public class RefundCompanyPeriodRowMapper implements RowMapper<RefundCompanyPeriodBean> {

    @Override
    public RefundCompanyPeriodBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RefundCompanyPeriodBean bean = new RefundCompanyPeriodBean();

        bean.setReceiveCompanyId(rs.getLong("RECEIVE_COMPANY_ID"));
        bean.setPayPeriod(rs.getString("PAY_PERIOD"));
        bean.setCntrAmount(rs.getBigDecimal("CNTR_AMOUNT"));
        bean.setPaymentDate(rs.getDate("PAYMENT_DATE"));
        bean.setInterestPaid(rs.getBigDecimal("INTEREST_PAID"));
        bean.setRequestEmprAmount(rs.getBigDecimal("EMPR_OVER"));
        bean.setRequestInterestAmount(rs.getBigDecimal("OVER_INTEREST_PAID"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("EMPE_OVER"));
        bean.setProgressStatus(rs.getString("PROGRESS_STATUS"));
        bean.setRefundSicknessRate(rs.getBigDecimal("REAL_EMPR_SICKNESS_RATE"));
        bean.setRefundRetirementRate(rs.getBigDecimal("REAL_EMPR_RETIREMENT_RATE"));
        bean.setRefundUnemploymentRate(rs.getBigDecimal("REAL_EMPR_UNEMPLOYMENT_RATE"));
        bean.setRefundSumRate(rs.getBigDecimal("REAL_EMPR_CNTR_RATE"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("SUM_REFUND_SICKNESS"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("SUM_REFUND_RETIREMENT"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("SUM_REFUND_UNEMPLOYMENT"));
        bean.setCompanyBranchCode(rs.getString("COMPANY_BRANCH_CODE"));
        bean.setCompanyBranchName(rs.getString("COMPANY_BRANCH_NAME"));

        return bean;
    }
}
