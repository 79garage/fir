package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;

@Repository
public class ConTrRefundEmployeeDaoImpl extends DaoBase<Long> {
    public ConTrRefundEmployeeDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TR_REFUND_EMPLOYEE";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_EMPLOYEE_ID";
    }

    public String findProgressStatusByRefundRequestId(Long refundRequestId) throws Exception {
        final String sql = """
                SELECT CTRI.PROGRESS_STATUS FROM CON_TR_REFUND_EMPLOYEE CTRI WHERE 1 = 1 AND CTRI.REFUND_REQUEST_ID = :refundRequestId
                """;
        Map<String, Object> param = new HashMap<>(1);
        param.put("refundRequestId", refundRequestId);

        return queryForObject(sql, param, String.class);
    }
}
