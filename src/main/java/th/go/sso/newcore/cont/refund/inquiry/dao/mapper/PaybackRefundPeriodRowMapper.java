package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.PaybackRefundPeriodBean;


public class PaybackRefundPeriodRowMapper implements RowMapper<PaybackRefundPeriodBean> {

	@Override
	public PaybackRefundPeriodBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

		PaybackRefundPeriodBean paybackRefundPeriodBean = new PaybackRefundPeriodBean();
		paybackRefundPeriodBean.setRefundPeriodId(getNullableLong(rs,"refund_period_id"));
		paybackRefundPeriodBean.setRefundRequestId(getNullableLong(rs,"refund_request_id"));
		paybackRefundPeriodBean.setRefundCompanyId(getNullableLong(rs,"refund_company_id"));
		paybackRefundPeriodBean.setRefundEmployeeId(getNullableLong(rs,"refund_employee_id"));
		paybackRefundPeriodBean.setRefundInsurerId(getNullableLong(rs,"refund_insurer_id"));
		paybackRefundPeriodBean.setRefundPeriod(rs.getString("refund_period"));
		paybackRefundPeriodBean.setApproveDate(rs.getDate("approve_date"));
		paybackRefundPeriodBean.setReceiptNo(rs.getString("receipt_no"));
		paybackRefundPeriodBean.setPaymentDate(rs.getDate("payment_date"));
		paybackRefundPeriodBean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
		paybackRefundPeriodBean.setPaybackAmount(rs.getBigDecimal("payback_amount"));
		paybackRefundPeriodBean.setOrderNo(rs.getString("order_no"));
		paybackRefundPeriodBean.setOrderStatus(rs.getString("order_status"));
		paybackRefundPeriodBean.setRequesterId(rs.getLong("requester_id"));
		paybackRefundPeriodBean.setFullName(rs.getString("full_name"));
		paybackRefundPeriodBean.setCompanyBranchName(rs.getString("company_branch_name"));
		paybackRefundPeriodBean.setPaybackPeriodId(rs.getLong("payback_period_id"));
		paybackRefundPeriodBean.setRelationGroup(rs.getString("relation_group"));
		paybackRefundPeriodBean.setPaybackStatus(rs.getString("payback_status"));
		paybackRefundPeriodBean.setPaybackOrderNo(rs.getString("payback_order_no"));
		return paybackRefundPeriodBean;
	}

	private Long getNullableLong(ResultSet rs, String columnName) throws SQLException {
		Long value = rs.getLong(columnName);
		return rs.wasNull() ? null : value;
	}
}
