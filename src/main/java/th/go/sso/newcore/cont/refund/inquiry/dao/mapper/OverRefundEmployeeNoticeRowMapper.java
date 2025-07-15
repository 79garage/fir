package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;

public class OverRefundEmployeeNoticeRowMapper implements RowMapper<OverRefundEmployeeBean> {
    @Override
    public OverRefundEmployeeBean mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        OverRefundEmployeeBean bean = new OverRefundEmployeeBean();

        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setPayPeriod(rs.getString("pay_period"));
        bean.setRefundReasonCode(rs.getString("refund_reason_code"));
        bean.setSumAmount(rs.getBigDecimal("sum_amount"));
        bean.setRequestTotalAmount(rs.getBigDecimal("over_paid"));
        bean.setOverPaid(rs.getBigDecimal("over_paid"));
        bean.setCountCompany(rs.getInt("count_company"));
        bean.setAnnounceStatus(rs.getString("announce_status"));
        bean.setDepCode(rs.getString("dep_code"));
        bean.setTotalWages(rs.getBigDecimal("sum_total_wages"));

        return bean;
    }
}
