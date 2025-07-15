package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeRequestBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTbRefundChangeEmployeeRequestRowMapper implements RowMapper<RefundChangeEmployeeRequestBean> {
    @Override
    public RefundChangeEmployeeRequestBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundChangeEmployeeRequestBean bean = new RefundChangeEmployeeRequestBean();
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setRefundBankBranchCode(rs.getString("refund_bank_branch_code"));
        bean.setRefundBankCode(rs.getString("refund_bank_code"));
        bean.setDepartmentReceiveCode(rs.getString("department_receive_code"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setNoticeDate(rs.getDate("notice_date"));
        bean.setNoticeNo(rs.getString("notice_no"));
        bean.setPeriodEndYear(rs.getString("period_end_year"));
        bean.setPeriodEndMonth(rs.getString("period_end_month"));
        bean.setPeriodStrYear(rs.getString("period_str_year"));
        bean.setPeriodStrMonth(rs.getString("period_str_month"));
        bean.setRefundPeriodEndYear(rs.getString("refund_period_end_year"));
        bean.setRefundPeriodStrYear(rs.getString("refund_period_str_year"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setReplyDate(rs.getDate("reply_date"));
        bean.setRequestAmount(rs.getBigDecimal("request_amount"));
        bean.setRequestReasonCode(rs.getString("request_reason_code"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setSourceData(rs.getString("source_data"));
        bean.setRefundChangeEmployeeRequestId(rs.getLong("refund_change_employee_request_id"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setCreateBy(rs.getString("create_by"));
        bean.setRefundPeriodEndMonth(rs.getString("refund_period_end_month"));
        bean.setRefundPeriodStrMonth(rs.getString("refund_period_str_month"));
        return bean;
    }
}
