package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyRequestBean;

public class RefundCompanyByIdRowMapper implements RowMapper<RefundCompanyRequestBean> {

    @Override
    public RefundCompanyRequestBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RefundCompanyRequestBean bean = new RefundCompanyRequestBean();

        bean.setSsoBranchCode(rs.getString("sso_branch_code"));
        bean.setSsoBranchName(rs.getString("sso_branch_name"));
        bean.setSsoBranchDescription(rs.getString("ssoBranchDescription"));
        bean.setDepartmentReceiveCode(rs.getString("department_receive_code"));
        bean.setDepartmentResponsibleCode(rs.getString("DEPARTMENT_RESPONSIBLE_CODE"));
//        bean.setDepartmentResponsibleId(rs.getLong("DEPARTMENT_RESPONSIBLE_ID"));
//        bean.setDepartmentReceiveId(rs.getLong("department_receive_id"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setReceiveDate(rs.getDate("receive_date"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setCompanyName(rs.getString("company_name"));
        bean.setCompanyDescription(rs.getString("companyDescription"));
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setCompanyBranchName(rs.getString("company_branch_name"));
        bean.setCompanyBranchDescription(rs.getString("companyBranchDescription"));
        bean.setBusinessType(rs.getString("business_type"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setCreateBy(rs.getString("CREATE_BY"));
        bean.setCreateDate(rs.getDate("CREATE_DATE"));
        bean.setUpdateDate(rs.getDate("UPDATE_DATE"));
        bean.setUpdateBy(rs.getString("UPDATE_BY"));
        bean.setRequestType(rs.getString("request_type"));
        bean.setRequestReasonCode(rs.getString("request_reason_code"));
        bean.setRequestReasonOther(rs.getString("request_reason_other"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setRefundBankBranchId(rs.getLong("refund_bank_branch_id"));
        bean.setRefundBankId(rs.getLong("refund_bank_id"));
        bean.setRefundPoOrder(rs.getString("refund_po_order"));
        bean.setRefundPromptPay(rs.getString("refund_prompt_pay"));
        bean.setRefundRemark(rs.getString("refund_remark"));
        bean.setRefundTransferBank(rs.getString("refund_transfer_bank"));
        bean.setRefundTransferDate(rs.getDate("refund_transfer_date"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setNoticeNo(rs.getString("notice_no"));
        bean.setNoticeDate(rs.getDate("notice_date"));
        bean.setNoticeStatus(rs.getString("notice_status"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setApproveNo(rs.getString("approve_no"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setRefundRequestId(rs.getLong("REFUND_REQUEST_ID"));
        bean.setRequestChannel(rs.getString("REQUEST_CHANNEL"));
        bean.setDisapproveReason(rs.getString("DISAPPROVE_REASON"));
        bean.setReplyDate(rs.getDate("reply_date"));
        bean.setNoticeArDate(rs.getDate("notice_ar_date"));
        bean.setAnnounceNo(rs.getString("announce_no"));
        bean.setAnnounceStatus(rs.getString("announce_status"));
        bean.setAnnounceDate(rs.getDate("announce_date"));
        bean.setAnnounceArDate(rs.getDate("announce_ar_date"));
        bean.setAnnounceArCreateBy(rs.getString("announce_ar_create_by"));
        bean.setAnnounceArCreateDate(rs.getDate("announce_ar_create_date"));
        bean.setRecordApproveBy(rs.getString("record_approve_by"));
        bean.setRecordApproveDate(rs.getDate("record_approve_date"));
        bean.setSplitRefund(rs.getString("SPLIT_REFUND"));
        bean.setSection(rs.getString("SECTION"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRequestEmprAmount(rs.getBigDecimal("request_empr_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRequestInterestAmount(rs.getBigDecimal("request_interest_amount"));
        bean.setRequestOverPaidAmount(rs.getBigDecimal("request_over_paid_amount"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRefundEmprAmount(rs.getBigDecimal("refund_empr_amount"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setRefundOverPaidAmount(rs.getBigDecimal("refund_over_paid_amount"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setReverseRemark(rs.getString("reverse_remark"));
        bean.setDistCode(rs.getString("DIST_CODE"));
        bean.setSubDistCode(rs.getString("SUBDIST_CODE"));
        bean.setProvinceCode(rs.getString("PROVINCE_CODE"));
        bean.setReverseStatus(rs.getString("REVERSE_STATUS"));
        bean.setDistName(rs.getString("dist_name"));
        bean.setSubDistName(rs.getString("subdist_name"));
        bean.setProvinceName(rs.getString("prov_name"));
        bean.setOrderRemark(rs.getString("order_remark"));
        bean.setOrderStatus(rs.getString("ORDER_STATUS"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setEligibleFlag(rs.getString("ELIGIBLE_FLAG"));
        bean.setRefundPoOrderName(rs.getString("refund_po_order_name"));
        bean.setPeriodStart(rs.getString("period_start"));
        bean.setPeriodEnd(rs.getString("period_end"));
        bean.setRequestAmount(rs.getBigDecimal("request_amount"));

        return bean;
    }
}
