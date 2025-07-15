package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;

public class RefundInsurerRowMapper implements RowMapper<RefundInsurerBean> {

    @Override
    public RefundInsurerBean mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {

        RefundInsurerBean bean = new RefundInsurerBean();

        bean.setInsurerId(rs.getLong("insurer_id"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setInsurerStatus(rs.getString("insurer_status"));
        bean.setDepName(rs.getString("dep_fullname"));
        bean.setDepartmentReceiveCode(rs.getString("dep_code"));
        bean.setSsoBranchCode(rs.getString("dep_resp_code"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setReceiveDate(rs.getDate("receive_date"));
        bean.setDepartmentReceiveCode(rs.getString("department_receive_code"));
        bean.setDepartmentResponsibleCode(rs.getString("department_responsible_code"));
//        bean.setDepartmentReceiveId(rs.getLong("department_receive_id"));
//        bean.setDepartmentResponsibleId(rs.getLong("department_responsible_id"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setRequestReasonCode(rs.getString("request_reason_code"));
        bean.setRequestReasonOther(rs.getString("request_reason_other"));
        bean.setRefundBankId(rs.getLong("refund_bank_id"));
        bean.setRefundBankBranchId(rs.getLong("refund_bank_branch_id"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setRefundPromptPay(rs.getString("refund_prompt_pay"));
        bean.setApproveNo(rs.getString("approve_no"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setNoticeStatus(rs.getString("notice_status"));
        bean.setAnnounceStatus(rs.getString("announce_status"));
        bean.setRefundRemark(rs.getString("refund_remark"));
        bean.setCreateBy(rs.getString("create_by"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setUpdateBy(rs.getString("update_by"));
        bean.setUpdateDate(rs.getDate("update_date"));
        bean.setRequestType(rs.getString("request_type"));
        bean.setRequestChannel(rs.getString("request_channel"));

        bean.setRefundPoOrder(rs.getString("refund_po_order"));
        bean.setRefundTransferBank(rs.getString("refund_transfer_bank"));
        bean.setRefundTransferDate(rs.getDate("refund_transfer_date"));
        bean.setNoticeNo(rs.getString("notice_no"));
        bean.setNoticeDate(rs.getDate("notice_date"));
        bean.setNoticeArDate(rs.getDate("notice_ar_date"));
        bean.setPayDate(rs.getDate("pay_date"));
        bean.setApproveStatus(rs.getString("approve_status"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setSection(rs.getString("SECTION"));
        bean.setDisapproveReason(rs.getString("disapprove_reason"));

        bean.setRequestEmpeAmount(rs.getBigDecimal("request_empe_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRequestInterestAmount(rs.getBigDecimal("request_interest_amount"));
        bean.setRefundEmpeAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("REFUND_SICKNESS_AMOUNT"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("REFUND_RETIREMENT_AMOUNT"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("REFUND_UNEMPLOYMENT_AMOUNT"));
        bean.setReverseRemark(rs.getString("REVERSE_REMARK"));
        bean.setReverseStatus(rs.getString("reverse_status"));
        bean.setAnnounceArDate(rs.getDate("announce_ar_date"));
        bean.setAnnounceNo(rs.getString("announce_no"));
        bean.setAnnounceDate(rs.getDate("announce_date"));
        bean.setReplyDate(rs.getDate("reply_date"));
        bean.setRecordApproveDate(rs.getDate("record_approve_date"));
        bean.setRecordApproveBy(rs.getString("record_approve_by"));
        bean.setAnnounceCreateBy(rs.getString("announce_create_by"));
        bean.setAnnounceCreateDate(rs.getDate("announce_create_date"));
        bean.setAnnounceArCreateBy(rs.getString("announce_ar_create_by"));
        bean.setAnnounceArCreateDate(rs.getDate("announce_ar_create_date"));
        bean.setRefundPoOrderName(rs.getString("refund_po_order_name"));
        bean.setPeriodStart(rs.getString("PERIOD_START"));
        bean.setPeriodEnd(rs.getString("PERIOD_END"));
        bean.setRequestAmount(rs.getBigDecimal("REQUEST_AMOUNT"));

        bean.setIdCardNoRef(rs.getString("ID_CARD_NO_REF"));
        bean.setTitleCode(rs.getString("TITLE_CODE"));
        bean.setFirstName(rs.getString("FIRST_NAME"));
        bean.setLastName(rs.getString("LAST_NAME"));
        bean.setFullNameRef(rs.getString("FULL_NAME_REF"));
        bean.setAddress(rs.getString("ADDRESS"));
        bean.setVillage(rs.getString("VILLAGE"));
        bean.setMoo(rs.getString("MOO"));
        bean.setSoi(rs.getString("SOI"));
        bean.setRoad(rs.getString("ROAD"));
        bean.setProvinceCode(rs.getString("PROVINCE_CODE"));
        bean.setProvinceName(rs.getString("PROVINCE_NAME"));
        bean.setDistCode(rs.getString("DIST_CODE"));
        bean.setDistName(rs.getString("DIST_NAME"));
        bean.setSubdistCode(rs.getString("SUBDIST_CODE"));
        bean.setSubdistName(rs.getString("SUBDIST_NAME"));
        bean.setZipCode(rs.getString("ZIP_CODE"));
        bean.setTelephone(rs.getString("TELEPHONE"));
        bean.setMobile(rs.getString("MOBILE"));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setRequesterType(rs.getString("REQUESTER_TYPE"));

        return bean;

    }
}
