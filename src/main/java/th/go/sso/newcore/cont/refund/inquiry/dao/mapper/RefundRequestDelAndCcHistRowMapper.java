package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundRequestDelAndCcHistRowMapper implements RowMapper<RefundRequestBean> {
    @Override
    public RefundRequestBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RefundRequestBean bean = new RefundRequestBean();
        bean.setCompanyId(rs.getLong("company_id"));
        bean.setCompanyDescription(rs.getString("company"));
        bean.setCompanyBranchDescription(rs.getString("branch"));
        bean.setInsurerId(rs.getLong("insurer_id"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setInsurerStatus(rs.getString("insurer_status"));

        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setDepartmentResponsibleCode(rs.getString("department_responsible_code"));
        bean.setDepartmentReceiveCode(rs.getString("department_receive_code"));
//        bean.setDepartmentResponsibleId(rs.getLong("department_responsible_id"));
//        bean.setDepartmentReceiveId(rs.getLong("department_receive_id"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setReceiveDate(rs.getDate("receive_date"));
        bean.setRequestType(rs.getString("request_type"));
        bean.setRequestReasonCode(rs.getString("request_reason_code"));
        bean.setRequestReasonOther(rs.getString("request_reason_other"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setRefundBankBranchId(rs.getLong("refund_bank_branch_id"));
        bean.setRefundBankId(rs.getLong("refund_bank_id"));
        bean.setRefundPoOrder(rs.getString("refund_po_order"));
        bean.setRefundPromptPay(rs.getString("refund_prompt_pay"));
        bean.setRefundRemark(rs.getString("refund_remark"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setSection(rs.getString("SECTION"));
        bean.setDistCode(rs.getString("DIST_CODE"));
        bean.setSubDistCode(rs.getString("SUBDIST_CODE"));
        bean.setProvinceCode(rs.getString("PROVINCE_CODE"));
        bean.setRequestChannel(rs.getString("REQUEST_CHANNEL"));
        bean.setStatus(rs.getString("status"));
        bean.setRefundChangeType(rs.getString("refund_change_type"));
        bean.setRefundOperationCode(rs.getString("refund_operation_code"));
        bean.setReverseRemark(rs.getString("reverse_remark"));
        bean.setRefRefundRequestId(getNullableLong(rs, "ref_refund_request_id"));

        return bean;
    }

    private Long getNullableLong(ResultSet rs, String columnName) throws SQLException {
        Long value = rs.getLong(columnName);
        return rs.wasNull() ? null : value;
    }
}
