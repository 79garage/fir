package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbHoldReceiveBean;

public class ConTbHoldReceiveRowMapper implements RowMapper<ConTbHoldReceiveBean> {
    @Override
    public ConTbHoldReceiveBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        ConTbHoldReceiveBean bean = new ConTbHoldReceiveBean();
        bean.setHoldReceiveId(rs.getLong("HOLD_RECEIVE_ID"));
        bean.setBankId(rs.getLong("BANK_ID"));
        bean.setBankBranchId(rs.getLong("BANK_BRANCH_ID"));
        bean.setBankAccount(rs.getString("BANK_ACCOUNT"));
        bean.setReceiptNo(rs.getString("RECEIPT_NO"));
        bean.setReceiptDate(rs.getDate("RECEIPT_DATE"));
        bean.setReceiptName(rs.getString("RECEIPT_NAME"));
        bean.setCompanyNo(rs.getString("COMPANY_NO"));
        bean.setCompanyBranchNo(rs.getString("COMPANY_BRANCH_CODE"));
        bean.setIdCardNo(rs.getString("ID_CARD_NO"));
        bean.setPayPeriod(rs.getString("PAY_PERIOD"));
        bean.setPayPeriodMonth(rs.getString("PAY_PERIOD_MONTH"));
        bean.setPayPeriodYear(rs.getString("PAY_PERIOD_YEAR"));
        bean.setNumOfBranch(rs.getString("NUM_OF_BRANCH"));
        bean.setNumOfEmployee(rs.getString("NUM_OF_EMPLOYEE"));
        bean.setReceiptChequeNo(rs.getString("RECEIPT_CHEQUE_NO"));
        bean.setCntrAmount(rs.getBigDecimal("CNTR_AMOUNT"));
        bean.setFlagStatus(rs.getString("FLAG_STATUS"));
        bean.setFlagPayType(rs.getString("FLAG_PAY_TYPE"));
        bean.setInterestAmount(rs.getBigDecimal("INTEREST_AMOUNT"));
        bean.setReasonCode(rs.getString("REASON_CODE"));
        bean.setRefundReceiptNo(rs.getString("REFUND_RECEIPT_NO"));
        bean.setSection(rs.getString("SECTION"));
        bean.setRefundReasonCode(rs.getString("REFUND_REASON_CODE"));
        bean.setReceiptId(rs.getLong("RECEIPT_ID"));
        bean.setDepCode(rs.getString("DEP_CODE"));

        return bean;
    }
}
