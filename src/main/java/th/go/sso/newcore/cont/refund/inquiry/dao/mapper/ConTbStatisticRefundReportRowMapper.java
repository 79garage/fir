package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReportBean;

public class ConTbStatisticRefundReportRowMapper implements RowMapper<RefundReportBean> {
    @Override
    public RefundReportBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundReportBean bean = new RefundReportBean();

        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setReceiveDate(rs.getDate("receive_date"));
        bean.setCompanyId(rs.getLong("company_id"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setCompanyName(rs.getString("company_name"));
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyBranchName(rs.getString("company_branch_name"));
        bean.setInsurerId(rs.getLong("insurer_id"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setFirstName(rs.getString("first_name"));
        bean.setLastName(rs.getString("last_name"));
        bean.setRefundPeriod(rs.getString("refund_period"));
        bean.setRefundPeriodMonth(rs.getString("refund_period_month"));
        bean.setRefundPeriodYear(rs.getString("refund_period_year"));
        bean.setRequestEmprAmount(rs.getBigDecimal("request_empr_amount"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRequestInterestAmount(rs.getBigDecimal("request_interest_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRefundEmprAmount(rs.getBigDecimal("refund_empr_amount"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setRequestReasonCode(rs.getString("request_reason_code"));
        bean.setRequestReasonOther(rs.getString("request_reason_other"));
        bean.setRequestReasonDesc(rs.getString("request_reason_desc"));
        bean.setDepartmentReceiveCode(rs.getString("department_receive_code"));
        bean.setDepartmentResponsibleCode(rs.getString("department_responsible_code"));
        bean.setDepartmentReceiveName(rs.getString("department_receive_name"));
        bean.setDepartmentResponsibleName(rs.getString("department_responsible_name"));
        bean.setRefundPromptPay(rs.getString("refund_prompt_pay"));
        bean.setRefundPoOrder(rs.getString("refund_po_order"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setRefundBankCode(rs.getString("refund_bank_code"));
        bean.setBankName(rs.getString("bank_name"));
        bean.setRefundPoOrderName(rs.getString("refund_po_order_name"));
        bean.setPeriodStart(rs.getString("period_start"));
        bean.setPeriodEnd(rs.getString("period_end"));
        bean.setRequestAmount(rs.getBigDecimal("request_amount"));
        bean.setPeriodStartMonth(rs.getString("period_start_month"));
        bean.setPeriodStartYear(rs.getString("period_start_year"));
        bean.setPeriodEndMonth(rs.getString("period_end_month"));
        bean.setPeriodEndYear(rs.getString("period_end_year"));
        bean.setSection(rs.getString("section"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setSplitRefund(rs.getString("split_refund"));
        bean.setStatus(rs.getString("status"));
        bean.setProcessDate(rs.getDate("process_date"));
        bean.setCreateBy(rs.getString("create_by"));
        bean.setCreateDate(rs.getDate("create_date"));

        return bean;
    }
}
