package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTbRefundChangeEmployeeRowMapper implements RowMapper<RefundChangeEmployeeBean> {
    @Override
    public RefundChangeEmployeeBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundChangeEmployeeBean bean = new RefundChangeEmployeeBean();
        bean.setRefundBankBranchCode(rs.getString("refund_bank_branch_code"));
        bean.setRefundBankBranchCodeChange(rs.getString("refund_bank_branch_code_change"));
        bean.setRefundBankCode(rs.getString("refund_bank_code"));
        bean.setRefundBankCodeChange(rs.getString("refund_bank_code_change"));
        bean.setDepartmentReceiveCode(rs.getString("department_receive_code"));
        bean.setDepartmentReceiveCodeChange(rs.getString("department_receive_code_change"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setRefundBankAccountChange(rs.getString("refund_bank_account_change"));
        bean.setSection(rs.getString("section"));
        bean.setSectionChange(rs.getString("section_change"));
        bean.setReceiveDate(rs.getDate("receive_date"));
        bean.setReceiveDateChange(rs.getDate("receive_date_change"));
        bean.setReceiveNoChange(rs.getString("receive_no_change"));
        bean.setPeriodEndYear(rs.getString("period_end_year"));
        bean.setPeriodEndMonth(rs.getString("period_end_month"));
        bean.setPeriodStrYear(rs.getString("period_str_year"));
        bean.setPeriodStrMonth(rs.getString("period_str_month"));
        bean.setPeriodEndYearChange(rs.getString("period_end_year_change"));
        bean.setPeriodEndMonthChange(rs.getString("period_end_month_change"));
        bean.setPeriodStrYearChange(rs.getString("period_str_year_change"));
        bean.setPeriodStrMonthChange(rs.getString("period_str_month_change"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setRefundTypePlaceChange(rs.getString("refund_type_place_change"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRequestEmpeAmountChange(rs.getBigDecimal("request_empe_amount_change"));
        bean.setRequestReasonCode(rs.getString("request_reason_code"));
        bean.setRequestReasonCodeChange(rs.getString("request_reason_code_change"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setSourceData(rs.getString("source_data"));
        bean.setRefundRequestChangeEmployeeId(rs.getLong("refund_request_change_employee_id"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setCreateBy(rs.getString("create_by"));
        bean.setRequestReasonOther(rs.getString("request_reason_other"));
        bean.setRequestReasonOtherChange(rs.getString("request_reason_other_change"));
        bean.setRefundRemark(rs.getString("refund_remark"));
        bean.setRefundRemarkChange(rs.getString("refund_remark_change"));
        bean.setRefundPoOrder(rs.getString("refund_po_order"));
        bean.setRefundPoOrderName(rs.getString("refund_po_order_name"));
        bean.setRefundPromptPay(rs.getString("refund_prompt_pay"));
        bean.setRefundPoOrderChange(rs.getString("refund_po_order_change"));
        bean.setRefundPoOrderNameChange(rs.getString("refund_po_order_name_change"));
        bean.setRefundPromptPayChange(rs.getString("refund_prompt_pay_change"));
        return bean;
    }
}
