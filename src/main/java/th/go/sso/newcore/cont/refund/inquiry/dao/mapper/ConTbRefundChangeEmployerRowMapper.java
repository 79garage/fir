package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployerBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTbRefundChangeEmployerRowMapper implements RowMapper<RefundChangeEmployerBean> {
    @Override
    public RefundChangeEmployerBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundChangeEmployerBean bean = new RefundChangeEmployerBean();
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setApproveStatusChange(rs.getString("approve_status_change"));
        bean.setRefundBankBranchCode(rs.getString("refund_bank_branch_code"));
        bean.setRefundBankBranchCodeChange(rs.getString("refund_bank_branch_code_change"));
        bean.setRefundBankCode(rs.getString("refund_bank_code"));
        bean.setRefundBankCodeChange(rs.getString("refund_bank_code_change"));
        bean.setDepartmentReceiveCode(rs.getString("department_receive_code"));
        bean.setDepartmentReceiveCodeChange(rs.getString("department_receive_code_change"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setRefundBankAccountChange(rs.getString("refund_bank_account_change"));
        bean.setUpdateDate(rs.getDate("update_date"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setApproveDateChange(rs.getDate("approve_date_change"));
        bean.setNoticeDate(rs.getDate("notice_date"));
        bean.setNoticeDateChange(rs.getDate("notice_date_change"));
        bean.setNoticeNo(rs.getString("notice_no"));
        bean.setNoticeNoChange(rs.getString("notice_no_change"));
        bean.setReceiveDate(rs.getDate("receive_date"));
        bean.setReceiveDateChange(rs.getDate("receive_date_change"));
        bean.setRefundAmountEmpe(rs.getBigDecimal("refund_amount_empe"));
        bean.setRefundAmountEmpr(rs.getBigDecimal("refund_amount_empr"));
        bean.setRefundAmountEmpeChange(rs.getBigDecimal("refund_amount_empe_change"));
        bean.setRefundAmountEmprChange(rs.getBigDecimal("refund_amount_empr_change"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setRefundAmountInt(rs.getBigDecimal("refund_amount_int"));
        bean.setRefundAmountIntChange(rs.getBigDecimal("refund_amount_int_change"));
        bean.setRefundAmountOldage(rs.getBigDecimal("refund_amount_oldage"));
        bean.setRefundAmountOldageChange(rs.getBigDecimal("refund_amount_oldage_change"));
        bean.setRefundAmountOver(rs.getBigDecimal("refund_amount_over"));
        bean.setRefundAmountOverChange(rs.getBigDecimal("refund_amount_over_change"));
        bean.setRefundAmountSick(rs.getBigDecimal("refund_amount_sick"));
        bean.setRefundAmountSickChange(rs.getBigDecimal("refund_amount_sick_change"));
        bean.setRefundAmountUnemp(rs.getBigDecimal("refund_amount_unemp"));
        bean.setRefundAmountUnempChange(rs.getBigDecimal("refund_amount_unemp_change"));
        bean.setRefundTypePlaceChange(rs.getString("refund_type_place_change"));
        bean.setReplyDate(rs.getDate("reply_date"));
        bean.setReplyDateChange(rs.getDate("reply_date_change"));
        bean.setRequestAmountEmpe(rs.getBigDecimal("request_amount_empe"));
        bean.setRequestAmountEmpr(rs.getBigDecimal("request_amount_empr"));
        bean.setRequestAmountEmpeChange(rs.getBigDecimal("request_amount_empe_change"));
        bean.setRequestAmountEmprChange(rs.getBigDecimal("request_amount_empr_change"));
        bean.setRequestReasonCode(rs.getString("request_reason_code"));
        bean.setRequestReasonCodeChange(rs.getString("request_reason_code_change"));
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setSourceData(rs.getString("source_data"));
        bean.setRefundChangeEmployerId(rs.getLong("refund_change_employer_id"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setCreateBy(rs.getString("create_by"));
        bean.setRefundPoOrder(rs.getString("refund_po_order"));
        bean.setRefundPoOrderName(rs.getString("refund_po_order_name"));
        bean.setRefundPromptPay(rs.getString("refund_prompt_pay"));
        bean.setRefundPoOrderChange(rs.getString("refund_po_order_change"));
        bean.setRefundPoOrderNameChange(rs.getString("refund_po_order_name_change"));
        bean.setRefundPromptPayChange(rs.getString("refund_prompt_pay_change"));
        bean.setPeriodEndMonth(rs.getString("period_end_month"));
        bean.setPeriodEndMonthChange(rs.getString("period_end_month_change"));
        bean.setPeriodEndYear(rs.getString("period_end_year"));
        bean.setPeriodEndYearChange(rs.getString("period_end_year_change"));
        bean.setPeriodStrMonth(rs.getString("period_str_month"));
        bean.setPeriodStrMonthChange(rs.getString("period_str_month_change"));
        bean.setPeriodStrYear(rs.getString("period_str_year"));
        bean.setPeriodStrYearChange(rs.getString("period_str_year_change"));
        bean.setRequestReasonOther(rs.getString("request_reason_other"));
        bean.setRequestReasonOtherChange(rs.getString("request_reason_other_change"));
        bean.setRefundRemark(rs.getString("refund_remark"));
        bean.setRefundRemarkChange(rs.getString("refund_remark_change"));
        bean.setRefundRequestAmount(rs.getBigDecimal("refund_request_amount"));
        bean.setRefundRequestAmountChange(rs.getBigDecimal("refund_request_amount_change"));
        return bean;
    }
}
