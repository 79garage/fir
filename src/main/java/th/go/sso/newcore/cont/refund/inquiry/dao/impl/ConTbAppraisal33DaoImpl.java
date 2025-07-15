package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;

@Repository
public class ConTbAppraisal33DaoImpl extends DaoBase<Long> {
    public ConTbAppraisal33DaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_APPRAISAL33";
    }

    @Override
    protected String getPkColumnName() {
        return "APPRAISAL33_ID";
    }

    public boolean findDebt(String companyNo, String companyBranchCode) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " COUNT(*) "+
                " FROM "+
                " CON_TB_APPRAISAL33 T1 "+
                " LEFT JOIN CON_MS_COMPANY T2 ON (T2.COMPANY_ID = T1.COMPANY_ID) "+
                " WHERE "+
                " T1.PAY_STATUS IN ( '2', '3' ) "+
                " AND T1.STATUS = 'A' "+
                " AND T1.INTEREST_AMOUNT > 0 "+
                " AND T2.COMPANY_NO = :companyNo "+
                " AND T2.COMPANY_BRANCH_CODE = :companyBranchCode ";

        param.put("companyNo", companyNo);
        param.put("companyBranchCode", companyBranchCode);
        return queryForObject(sql, param, boolean.class);
    }
}
