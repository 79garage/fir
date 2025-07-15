package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;

public class SplitEmployeeRefundPeriodRowMapper implements RowMapper<SplitEmployeeRefundCompanyPeriodBean> {

    @Override
    public SplitEmployeeRefundCompanyPeriodBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        SplitEmployeeRefundCompanyPeriodBean bean = new SplitEmployeeRefundCompanyPeriodBean();

        bean.setPayPeriod(rs.getString("PAY_PERIOD"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setRefRefundRequestId(rs.getLong("ref_refund_request_id"));
        bean.setReceiveEmployeeId(rs.getLong("RECEIVE_EMPLOYEE_ID"));
        bean.setRefundEmployeeId(rs.getLong("refund_employee_id"));
        bean.setInsurerId(rs.getLong("insurer_id"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setOrderRemark(rs.getString("ORDER_REMARK"));
        bean.setOrderStatus(rs.getString("ORDER_STATUS"));
        bean.setRequesterId(rs.getLong("REQUESTER_ID"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setRefundSicknessRate(rs.getBigDecimal("refund_sickness_rate"));
        bean.setRefundRetirementRate(rs.getBigDecimal("refund_retirement_rate"));
        bean.setRefundUnemploymentRate(rs.getBigDecimal("refund_unemployment_rate"));
        bean.setPeriodStart(rs.getString("PERIOD_START"));
        bean.setPeriodEnd(rs.getString("PERIOD_END"));
        bean.setCompanyNo(rs.getString("COMPANY_NO"));
        bean.setCompanyName(rs.getString("COMPANY_NAME"));
        bean.setCompanyBranchCode(rs.getString("COMPANY_BRANCH_CODE"));
        bean.setCompanyBranchName(rs.getString("COMPANY_BRANCH_NAME"));

        return bean;
    }
}
