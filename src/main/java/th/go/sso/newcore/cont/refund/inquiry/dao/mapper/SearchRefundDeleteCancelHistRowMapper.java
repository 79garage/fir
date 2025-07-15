package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.SearchDeleteCancelHistoryBean;

public class SearchRefundDeleteCancelHistRowMapper implements RowMapper<SearchDeleteCancelHistoryBean> {

    @Override
    public SearchDeleteCancelHistoryBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        SearchDeleteCancelHistoryBean bean = new SearchDeleteCancelHistoryBean();

        bean.setRefundPeriodId(rs.getLong("REFUND_PERIOD_ID"));
        bean.setRefundRequestId(rs.getLong("REFUND_REQUEST_ID"));
        bean.setReceiveNo(rs.getString("RECEIVE_NO"));
        bean.setRefundPeriod(rs.getString("REFUND_PERIOD"));
        bean.setRequestEmprAmount(rs.getBigDecimal("REQUEST_EMPR_AMOUNT"));
        bean.setRequestEmpeAmount(rs.getBigDecimal("REQUEST_EMPE_AMOUNT"));
        bean.setRequestInterestAmount(rs.getBigDecimal("REQUEST_INTEREST_AMOUNT"));
        bean.setApproveStatus(rs.getString("APPROVE_STATUS"));
        bean.setRefundPeriodStatus(rs.getString("STATUS"));
        bean.setRefundRequestStatus(rs.getString("REQUEST_STATUS"));
        bean.setCountCompany(rs.getInt("COUNT_COMPANY"));
        bean.setCompanyId(rs.getLong("COMPANY_ID"));
        bean.setInsurerId(rs.getLong("INSURER_ID"));
        bean.setOverPaid(rs.getBigDecimal("OVER_PAID"));
        bean.setIdCardNo(rs.getString("ID_CARD_NO"));
        bean.setUpdateBy(rs.getString("UPDATE_BY"));
        bean.setUpdateDate(rs.getDate("UPDATE_DATE"));
        return bean;
    }
}
