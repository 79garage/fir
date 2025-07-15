package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackBean;

public class ConTbPaybackEmployeeRowMapper implements RowMapper<ConTbPaybackBean> {
    @Override
    public ConTbPaybackBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        ConTbPaybackBean bean = new ConTbPaybackBean();
        bean.setPaybackRequestId(rs.getLong("payback_request_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setCompanyId(rs.getLong("company_id"));
        bean.setPaybackTotalAmount(rs.getBigDecimal("payback_total_amount"));
        bean.setPaybackRequestNo(rs.getString("payback_request_no"));
        bean.setPaybackRequestDate(rs.getDate("payback_request_date"));
        bean.setPaybackRequestType(rs.getString("payback_request_type"));
        bean.setPaybackApproveStatus(rs.getString("payback_approve_status"));
        bean.setPaybackApproveDate(rs.getDate("payback_approve_date"));
        bean.setPaybackReceiveStatus(rs.getString("payback_receive_status"));
        bean.setPaybackReceiveDate(rs.getDate("payback_receive_date"));
        bean.setSection(rs.getString("section"));
        bean.setStatus(rs.getString("status"));
        bean.setRemark(rs.getString("remark"));
        bean.setManageBy(rs.getString("manage_by"));
        bean.setCreateBy(rs.getString("create_by"));
        bean.setCreateDate(rs.getDate("create_date"));
        bean.setUpdateBy(rs.getString("update_by"));
        bean.setUpdateDate(rs.getDate("update_date"));
        bean.setReverseStatus(rs.getString("reverse_status"));
        bean.setPaybackCause(rs.getString("payback_cause"));
        bean.setReceiveTotalAmount(rs.getBigDecimal("receive_total_amount"));
        bean.setDepCode(rs.getString("dep_code"));
        bean.setPaybackStatus(rs.getString("payback_status"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setPaybackOrderNo(rs.getString("payback_order_no"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setCompanyName(rs.getString("company_name"));
        bean.setCompanyNo(rs.getString("company_no"));
        bean.setCompanyBranchCode(rs.getString("company_branch_code"));
        bean.setRequesterCompanyId(rs.getLong("requester_company_id"));
        bean.setRequester33Id(rs.getLong("requester33_id"));
        bean.setRequester39Id(rs.getLong("requester39_id"));
        return bean;
    }
}
