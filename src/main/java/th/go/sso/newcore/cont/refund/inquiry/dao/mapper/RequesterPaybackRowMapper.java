package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterPaybackBean;

public class RequesterPaybackRowMapper implements RowMapper<RequesterPaybackBean> {
    @Override
    public RequesterPaybackBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RequesterPaybackBean bean = new RequesterPaybackBean();
        bean.setOrderNo(rs.getString("ORDER_NO"));
        bean.setOrderStatus(rs.getString("ORDER_STATUS"));
        bean.setFullName(rs.getString("FULL_NAME"));
        bean.setCompanyNo(rs.getString("COMPANY_NO"));
        bean.setCompanyName(rs.getString("COMPANY_NAME"));
        bean.setCompanyBranchCode(rs.getString("COMPANY_BRANCH_CODE"));
        bean.setCompanyBranchName(rs.getString("COMPANY_BRANCH_NAME"));
        bean.setRefundTotalAmount(rs.getBigDecimal("REFUND_TOTAL_AMOUNT"));
        bean.setReceiptNo(rs.getString("RECEIPT_NO"));
        bean.setPaymentDate(rs.getDate("PAYMENT_DATE"));
        bean.setPeriod(rs.getString("PERIOD"));

        return bean;
    }
}
