package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;

@Repository
public class ConTbAppraisal39DaoImpl extends DaoBase<Long> {
    public ConTbAppraisal39DaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_APPRAISAL39";
    }

    @Override
    protected String getPkColumnName() {
        return "APPRAISAL39_ID";
    }

    public int findDebt(Long insurerId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " COUNT(*) "+
                " FROM "+
                " CON_TB_APPRAISAL39 T1 "+
                " LEFT JOIN CON_MS_INSURER     T2 ON ( T2.INSURER_ID = T1.INSURER_ID ) "+
                " WHERE 1 = 1 "+
                " AND (( T1.PAY_STATUS = '2' "+
                " AND T1.INTEREST_AMOUNT > 0 ) "+
                " OR ( T1.PAY_STATUS = '4' )) "+
                " AND T1.INSURER_ID = :insurerId ";

        param.put("insurerId", insurerId);
        return queryForObject(sql, param, int.class);
    }
}
