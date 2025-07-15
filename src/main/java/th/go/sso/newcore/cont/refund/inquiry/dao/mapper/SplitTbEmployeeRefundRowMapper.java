package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;

public class SplitTbEmployeeRefundRowMapper implements RowMapper<SplitEmployeeRefundCompanyPeriodBean> {

    @Override
    public SplitEmployeeRefundCompanyPeriodBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        SplitEmployeeRefundCompanyPeriodBean bean = new SplitEmployeeRefundCompanyPeriodBean();

        bean.setRefRefundRequestId(rs.getLong("REFUND_REQUEST_ID"));
        bean.setReceiveEmployeeId(rs.getLong("RECEIVE_EMPLOYEE_ID"));
        bean.setPayPeriod(rs.getString("PAY_PERIOD"));
        bean.setInsurerId(rs.getLong("INSURER_ID"));
        bean.setIdCardNo(rs.getString("ID_CARD_NO"));
        bean.setFullName(rs.getString("FULL_NAME"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("EMPE_OVER"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("REFUND_EMPE_AMOUNT"));
        bean.setRequestTotalAmount(rs.getBigDecimal("EMPE_OVER"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("REFUND_EMPE_SICKNESS"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("REFUND_EMPE_UNEMPLOYMENT"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("REFUND_EMPE_RETIREMENT"));
        bean.setRefundInterestAmount(rs.getBigDecimal("REFUND_INTEREST_AMOUNT"));
        bean.setRefundSicknessRate(rs.getBigDecimal("REAL_EMPE_SICKNESS_RATE"));
        bean.setRefundRetirementRate(rs.getBigDecimal("REAL_EMPE_RETIREMENT_RATE"));
        bean.setRefundUnemploymentRate(rs.getBigDecimal("REAL_EMPE_UNEMPLOYMENT_RATE"));
        bean.setEmpeSumRate(rs.getBigDecimal("REAL_EMPE_CNTR_RATE"));
        bean.setProgressStatus(rs.getString("PROGRESS_STATUS"));
        bean.setRefundStatus(rs.getString("REFUND_STATUS"));
        bean.setCompanyNo(rs.getString("COMPANY_NO"));
        bean.setCompanyName(rs.getString("COMPANY_NAME"));
        bean.setCompanyBranchCode(rs.getString("COMPANY_BRANCH_CODE"));
        bean.setCompanyBranchName(rs.getString("COMPANY_BRANCH_NAME"));

        return bean;
    }
}
