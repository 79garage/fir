package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRefundRequestBean;

public class ConTbRefundRequestRowMapper implements RowMapper<ConTbRefundRequestBean> {
    @Override
    public ConTbRefundRequestBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        ConTbRefundRequestBean bean = new ConTbRefundRequestBean();

        bean.setRefundRequestId(rs.getLong("REFUND_REQUEST_ID"));
        bean.setCompanyId(rs.getLong("COMPANY_ID"));
        bean.setInsurerId(rs.getLong("INSURER_ID"));
        bean.setRefundBankId(rs.getLong("REFUND_BANK_ID"));
        bean.setRefundBankBranchId(rs.getLong("REFUND_BANK_BRANCH_ID"));
        bean.setReceiveNo(rs.getString("RECEIVE_NO"));
        bean.setReceiveDate(rs.getDate("RECEIVE_DATE"));
        bean.setNoticeNo(rs.getString("NOTICE_NO"));
        bean.setNoticeDate(rs.getDate("NOTICE_DATE"));
        bean.setPayDate(rs.getDate("PAY_DATE"));
        bean.setRefundPromptPay(rs.getString("REFUND_PROMPT_PAY"));
        bean.setRefundPoOrder(rs.getString("REFUND_PO_ORDER"));
        bean.setRefundBankAccount(rs.getString("REFUND_BANK_ACCOUNT"));
        bean.setRefundTypePlace(rs.getString("REFUND_TYPE_PLACE"));
        bean.setRefundTransferBank(rs.getString("REFUND_TRANSFER_BANK"));
        bean.setRefundTransferDate(rs.getDate("REFUND_TRANSFER_DATE"));
        bean.setRequestReasonCode(rs.getString("REQUEST_REASON_CODE"));
        bean.setRequestReasonOther(rs.getString("REQUEST_REASON_OTHER"));
        bean.setApproveNo(rs.getString("APPROVE_NO"));
        bean.setApproveDate(rs.getDate("APPROVE_DATE"));
        bean.setApproveStatus(rs.getString("APPROVE_STATUS"));
        bean.setNoticeStatus(rs.getString("NOTICE_STATUS"));
        bean.setAnnounceStatus(rs.getString("ANNOUNCE_STATUS"));
        bean.setSection(rs.getString("SECTION"));
        bean.setRefundRemark(rs.getString("REFUND_REMARK"));
        bean.setCreateBy(rs.getString("CREATE_BY"));
        bean.setCreateDate(rs.getDate("CREATE_DATE"));
        bean.setUpdateBy(rs.getString("UPDATE_BY"));
        bean.setUpdateDate(rs.getDate("UPDATE_DATE"));
        bean.setRequestType(rs.getString("REQUEST_TYPE"));
        bean.setStatus(rs.getString("STATUS"));

        return bean;
    }
}
