package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundPeriodActiveBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundPeriodRowMapper;

@Repository
public class ConTbRefundPeriodDaoImpl extends DaoBase<Long> {
    public ConTbRefundPeriodDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<RefundPeriodActiveBean> searchRefundPeriod(Long refundRequestId) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM con_tb_refund_period rp ");
        sql.append(" WHERE 1 = 1 ");
        Map<String, Object> param = new HashMap<>();

        if (!NumberUtils.isZeroOrNull(refundRequestId)) {
            sql.append("  AND rp.refund_request_id = :refundRequestId");
            param.put("refundRequestId",refundRequestId);
        }

        return queryForList(sql.toString(), param, new ConTbRefundPeriodRowMapper());
    }

    @Override
    protected String getTableName() {
        return "con_tb_refund_period";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_PERIOD_ID";
    }
}
