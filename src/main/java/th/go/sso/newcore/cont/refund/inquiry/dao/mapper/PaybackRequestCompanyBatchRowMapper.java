package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.PaybackRequestCompanyBatchBean;

public class PaybackRequestCompanyBatchRowMapper implements RowMapper<PaybackRequestCompanyBatchBean> {
    @Override
    public PaybackRequestCompanyBatchBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        PaybackRequestCompanyBatchBean bean = new PaybackRequestCompanyBatchBean();

        bean.setPaybackRequestId(rs.getLong("payback_request_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setCompanyId(rs.getLong("company_id"));
        bean.setInsurerId(rs.getLong("insurer_id"));
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
        bean.setReversStatus(rs.getString("reverse_status"));
        bean.setPaybackCause(rs.getString("payback_cause"));
        bean.setPayback40Id(rs.getLong("payback40_id"));
        bean.setReceiveTotalAmount(rs.getBigDecimal("receive_total_amount"));
        bean.setPaybackDateline(rs.getDate("payback_dateline"));
        bean.setIncreaseTotalAmount(rs.getBigDecimal("increase_total_amount"));
        bean.setDepCode(rs.getString("dep_code"));
        bean.setRefundReceiptId(rs.getLong("refund_receipt_id"));
        bean.setBackCode(rs.getString("bank_code"));

        return bean;
    }
}
