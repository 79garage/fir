package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;import org.springframework.lang.NonNull;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInquiryInsurerBean;

public class RefundInquiryInsurerRowMapper implements RowMapper<RefundInquiryInsurerBean>{
    
    @Override
    public RefundInquiryInsurerBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException{

        RefundInquiryInsurerBean bean = new RefundInquiryInsurerBean();

        bean.setInsurerId(rs.getLong("insurer_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setRefundPeriod(rs.getString("refund_period"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setSection(rs.getString("section"));
        bean.setReceiveNo(rs.getString("receive_no"));
        bean.setReceiveDate(rs.getDate("receive_date"));
        bean.setRefundEmprAmount(rs.getBigDecimal("refund_empe_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("request_total_amount"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setApproveNo(rs.getString("approve_no"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setPaymentDate(rs.getDate("payment_date"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setModeRefund(rs.getString("mode_refund"));
        bean.setRefRefundRequestId(rs.getLong("ref_refund_request_id"));
        bean.setCompanyId(rs.getLong("company_id"));
        bean.setReverseStatus(rs.getString("reverse_status"));
        bean.setReverseRemark(rs.getString("reverse_remark"));
        bean.setNoticeStatus(rs.getString("notice_status"));
        bean.setRefundPeriodId(rs.getLong("refund_period_id"));
        bean.setAnnounceStatus(rs.getString("announce_status"));

        return bean;
    }
}
