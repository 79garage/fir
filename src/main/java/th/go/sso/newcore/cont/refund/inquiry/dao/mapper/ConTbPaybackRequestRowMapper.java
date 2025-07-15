package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestBean;


public class ConTbPaybackRequestRowMapper implements RowMapper<ConTbPaybackRequestBean> {

	@Override
	public ConTbPaybackRequestBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
		ConTbPaybackRequestBean conTbPaybackRequestBean = new ConTbPaybackRequestBean();
		conTbPaybackRequestBean.setPaybackRequestId(getNullableLong(rs, "payback_request_id"));
		conTbPaybackRequestBean.setRefundRequestId(getNullableLong(rs,"refund_request_id"));
		conTbPaybackRequestBean.setCompanyId(getNullableLong(rs,"company_id"));
		conTbPaybackRequestBean.setInsurerId(getNullableLong(rs,"insurer_id"));
		conTbPaybackRequestBean.setPaybackTotalAmount(rs.getBigDecimal("payback_total_amount"));
		conTbPaybackRequestBean.setPaybackRequestNo(rs.getString("payback_request_no"));
		conTbPaybackRequestBean.setPaybackRequestDate(rs.getDate("payback_request_date"));
		conTbPaybackRequestBean.setPaybackRequestType(rs.getString("payback_request_type"));
		conTbPaybackRequestBean.setPaybackApproveStatus(rs.getString("payback_approve_status"));
		conTbPaybackRequestBean.setPaybackApproveDate(rs.getDate("payback_approve_date"));
		conTbPaybackRequestBean.setPaybackReceiveStatus(rs.getString("payback_receive_status"));
		conTbPaybackRequestBean.setPaybackReceiveDate(rs.getDate("payback_receive_date"));
		conTbPaybackRequestBean.setReverseStatus(rs.getString("reverse_status"));
		conTbPaybackRequestBean.setPaybackCause(rs.getString("payback_cause"));
		conTbPaybackRequestBean.setSection(rs.getString("section"));
		conTbPaybackRequestBean.setStatus(rs.getString("status"));
		conTbPaybackRequestBean.setRemark(rs.getString("remark"));
		conTbPaybackRequestBean.setManageBy(rs.getString("manage_by"));
		conTbPaybackRequestBean.setCreateBy(rs.getString("create_by"));
		conTbPaybackRequestBean.setCreateDate(rs.getDate("create_date"));
		conTbPaybackRequestBean.setUpdateBy(rs.getString("update_by"));
		conTbPaybackRequestBean.setUpdateDate(rs.getDate("update_date"));
		conTbPaybackRequestBean.setReceiveNo(rs.getString("receive_no"));
		conTbPaybackRequestBean.setFullName(rs.getString("full_name"));
		conTbPaybackRequestBean.setIdCardNo(rs.getString("id_card_no"));
		conTbPaybackRequestBean.setRequestReasonCode(rs.getString("request_reason_code"));
		conTbPaybackRequestBean.setRequestReasonOther(rs.getString("request_reason_other"));
		conTbPaybackRequestBean.setApproveDate(rs.getDate("approve_date"));
		conTbPaybackRequestBean.setReceiptNo(rs.getString("receipt_no"));
		conTbPaybackRequestBean.setPaymentDate(rs.getDate("payment_date"));
		conTbPaybackRequestBean.setTotalAmount(rs.getBigDecimal("total_amount"));
		conTbPaybackRequestBean.setCompanyNo(rs.getString("company_no"));
		conTbPaybackRequestBean.setCompanyName(rs.getString("company_name"));
		conTbPaybackRequestBean.setCompanyBranchCode(rs.getString("company_branch_code"));
		conTbPaybackRequestBean.setCompanyBranchName(rs.getString("company_branch_name"));
		conTbPaybackRequestBean.setRequestType(rs.getString("request_type"));
		conTbPaybackRequestBean.setRequestChannel(rs.getString("request_channel"));
		conTbPaybackRequestBean.setDepCode(rs.getString("dep_code"));
		conTbPaybackRequestBean.setOrderNo(rs.getString("order_no"));
		conTbPaybackRequestBean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
		conTbPaybackRequestBean.setAllStatus(rs.getString("all_status"));
		conTbPaybackRequestBean.setPaybackStatus(rs.getString("payback_status"));
		conTbPaybackRequestBean.setSplitRefund(rs.getString("split_refund"));
		return conTbPaybackRequestBean;
	}

	private Long getNullableLong(ResultSet rs, String columnName) throws SQLException {
		Long value = rs.getLong(columnName);
		return rs.wasNull() ? null : value;
	}
}
