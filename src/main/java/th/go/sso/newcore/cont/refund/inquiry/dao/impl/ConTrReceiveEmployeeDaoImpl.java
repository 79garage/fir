package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;

@Repository
public class ConTrReceiveEmployeeDaoImpl extends DaoBase<Long> {
    public ConTrReceiveEmployeeDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TR_RECEIVE_EMPLOYEE";
    }

    @Override
    protected String getPkColumnName() {
        return "RECEIVE_EMPLOYEE_ID";
    }

    public Date findPaymentDate(Long insurerId, String payPeriod) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = """
                SELECT MAX(PAY_DATE) AS PAYMENT_DATE FROM CON_TR_RECEIVE_EMPLOYEE WHERE INSURER_ID = :insurerId AND PAY_PERIOD = :payPeriod
                """;
        param.put("insurerId", insurerId);
        param.put("payPeriod", payPeriod);
        return queryForObject(sql, param, Date.class);
    }
}
