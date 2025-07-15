package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundBean;

public class SplitEmployeeRefundListAggRowMapper implements RowMapper<SplitEmployeeRefundBean> {

    @Override
    public SplitEmployeeRefundBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        SplitEmployeeRefundBean bean = new SplitEmployeeRefundBean();

        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setRefRefundRequestId(rs.getLong("ref_refund_request_id"));
        bean.setInsurerId(rs.getLong("insurer_id"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setRefundPoOrder(rs.getString("refund_po_order"));
        bean.setRefundPoOrderName(rs.getString("refund_po_order_name"));
        bean.setRefundPromptPay(rs.getString("refund_prompt_pay"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setRefundBankBranchId(rs.getLong("refund_bank_branch_id") == 0 ? null : rs.getLong("refund_bank_branch_id"));
        bean.setRefundBankId(rs.getLong("refund_bank_id") == 0 ? null : rs.getLong("refund_bank_id"));
        bean.setDistCode(rs.getString("DIST_CODE"));
        bean.setSubDistCode(rs.getString("SUBDIST_CODE"));
        bean.setProvinceCode(rs.getString("PROVINCE_CODE"));
        bean.setRefundBankCode(rs.getString("bank_code"));
        bean.setRefundBankBranchCode(rs.getString("bank_branch_code"));
        bean.setRefundStatus(rs.getString("refund_status"));

        return bean;
    }
}
