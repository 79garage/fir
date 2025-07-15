package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestInfoBean;


public class ConTbPaybackRequestInfoRowMapper implements RowMapper<ConTbPaybackRequestInfoBean> {

	@Override
	public ConTbPaybackRequestInfoBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

		ConTbPaybackRequestInfoBean conTbPaybackRequestInfoBean = new ConTbPaybackRequestInfoBean();
		conTbPaybackRequestInfoBean.setReceiveNo(rs.getString("receive_no"));
		conTbPaybackRequestInfoBean.setPaybackRequestNo(rs.getString("payback_request_no"));
		conTbPaybackRequestInfoBean.setIdCardNo(rs.getString("id_card_no"));
		conTbPaybackRequestInfoBean.setFullName(rs.getString("full_name"));
		conTbPaybackRequestInfoBean.setPaybackRequestDate(rs.getDate("payback_request_date"));
		conTbPaybackRequestInfoBean.setPaybackTotalAmount(rs.getBigDecimal("payback_total_amount"));
		conTbPaybackRequestInfoBean.setStatus(rs.getString("status"));
		conTbPaybackRequestInfoBean.setPaybackApproveStatus(rs.getString("payback_approve_status"));
		conTbPaybackRequestInfoBean.setCompanyNo(rs.getString("company_no"));
		conTbPaybackRequestInfoBean.setCompanyName(rs.getString("company_name"));
		conTbPaybackRequestInfoBean.setCompanyBranchCode(rs.getString("company_branch_code"));
		conTbPaybackRequestInfoBean.setCompanyBranchName(rs.getString("company_branch_name"));
		conTbPaybackRequestInfoBean.setPaybackRequestId(rs.getLong("payback_request_id"));
		conTbPaybackRequestInfoBean.setPaybackStatus(rs.getString("payback_status"));

		return conTbPaybackRequestInfoBean;
	}
}
