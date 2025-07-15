package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRefundRequestChangeBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundRequestChangeRowMapper;

@Repository
public class ConTbRefundRequestChangeDaoImpl extends DaoBase<Long> {
    public ConTbRefundRequestChangeDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_REQUEST_CHANGE";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_REQUEST_CHANGE_ID";
    }

    public ConTbRefundRequestChangeBean queryConTbRefundRequestChange(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "SELECT * FROM CON_TB_REFUND_REQUEST_CHANGE WHERE 1 = 1 AND REFUND_REQUEST_CHANGE_TYPE_ID = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param,new ConTbRefundRequestChangeRowMapper());
    }
}
