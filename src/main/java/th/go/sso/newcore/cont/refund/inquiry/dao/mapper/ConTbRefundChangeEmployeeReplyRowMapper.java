package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeReplyBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTbRefundChangeEmployeeReplyRowMapper implements RowMapper<RefundChangeEmployeeReplyBean> {
    @Override
    public RefundChangeEmployeeReplyBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundChangeEmployeeReplyBean bean = new RefundChangeEmployeeReplyBean();
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setApproveStatusChange(rs.getString("approve_status_change"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setApproveDateChange(rs.getDate("approve_date_change"));
        bean.setNoticeDate(rs.getDate("notice_date"));
        bean.setNoticeDateChange(rs.getDate("notice_date_change"));
        bean.setNoticeNo(rs.getString("notice_no"));
        bean.setNoticeNoChange(rs.getString("notice_no_change"));
        bean.setRefundPeriodEndYear(rs.getString("refund_period_end_year"));
        bean.setRefundPeriodEndMonth(rs.getString("refund_period_end_month"));
        bean.setRefundPeriodStrYear(rs.getString("refund_period_str_year"));
        bean.setRefundPeriodStrMonth(rs.getString("refund_period_str_month"));
        bean.setRefundPeriodEndYearChange(rs.getString("refund_period_end_year_change"));
        bean.setRefundPeriodEndMonthChange(rs.getString("refund_period_end_month_change"));
        bean.setRefundPeriodStrYearChange(rs.getString("refund_period_str_year_change"));
        bean.setRefundPeriodStrMonthChange(rs.getString("refund_period_str_month_change"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRefundEmpeAmountChange(rs.getBigDecimal("refund_empe_amount_change"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setRefundInterestAmountChange(rs.getBigDecimal("refund_interest_amount_change"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundRetirementAmountChange(rs.getBigDecimal("refund_retirement_amount_change"));
        bean.setRequestOverPaidAmount(rs.getBigDecimal("request_over_paid_amount"));
        bean.setRequestOverPaidAmountChange(rs.getBigDecimal("request_over_paid_amount_change"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundSicknessAmountChange(rs.getBigDecimal("refund_sickness_amount_change"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundUnemploymentAmountChange(rs.getBigDecimal("refund_unemployment_amount_change"));
        bean.setReplyDate(rs.getDate("reply_date"));
        bean.setReplyDateChange(rs.getDate("reply_date_change"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setRefundRequestChangeTypeId(rs.getLong("refund_request_change_type_id"));
        bean.setSourceData(rs.getString("source_data"));
        bean.setRefundChangeEmployeeReplyId(rs.getLong("refund_change_employee_reply_id"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setCreateBy(rs.getString("create_by"));
        return bean;
    }
}
