package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ContributionInsurerBean;

public class ContributionInsurerRowMapper implements RowMapper<ContributionInsurerBean> {

    @Override
    public ContributionInsurerBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        ContributionInsurerBean bean = new ContributionInsurerBean();

        bean.setReceiveInsurerId(rs.getLong("receive_insurer_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setPayPeriod(rs.getString("pay_period"));
        bean.setCntrAmount(rs.getBigDecimal("cntr_amount"));
        bean.setInterestPaid(rs.getBigDecimal("interest_paid"));

        bean.setEmpeOverp(rs.getBigDecimal("empe_overp"));
        bean.setOverInterestPaid(rs.getBigDecimal("over_interest_paid"));

        bean.setApproveNo(rs.getString("approve_no"));
        bean.setApproveDate(rs.getDate("approve_date"));
//        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setPayDate(rs.getDate("pay_date"));
        bean.setProgressStatus(rs.getString("progress_status"));
        bean.setNoticeStatus(rs.getString("notice_status"));
        bean.setAnnounceStatus(rs.getString("announce_status"));
        bean.setSection(rs.getString("section"));
        bean.setReverseStatus(rs.getString("reverse_status"));
        bean.setReverseRemark(rs.getString("reverse_remark"));

        return bean;
    }
}
