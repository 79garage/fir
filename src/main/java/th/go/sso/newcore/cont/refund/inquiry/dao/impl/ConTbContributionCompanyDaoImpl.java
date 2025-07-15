package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverPaidBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OverPaidEmployeeRowMapper;

@Repository
public class ConTbContributionCompanyDaoImpl extends DaoBase<Long> {
    public ConTbContributionCompanyDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_CONTRIBUTION_COMPANY";
    }

    @Override
    protected String getPkColumnName() {
        return "CONTRIBUTION_COMPANY_ID";
    }

    public List<OverPaidBean> findEmpeOver(List<Long> receiveCompanyId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " TR.RECEIVE_COMPANY_ID, "+
                " SUM(TC.EMPE_OVER) AS EMPE_OVER "+
                " FROM "+
                " CON_TB_CONTRIBUTION_COMPANY TC "+
                " LEFT JOIN CON_TR_RECEIVE_COMPANY      TR ON TR.COMPANY_ID = TC.COMPANY_ID AND TR.PAY_PERIOD = TC.PAY_PERIOD "+
                " WHERE "+
                " 1 = 1 "+
                " AND TR.RECEIVE_COMPANY_ID IN ( :receiveCompanyId ) " +
                " GROUP BY TR.RECEIVE_COMPANY_ID ";
        param.put("receiveCompanyId", receiveCompanyId);
        return queryForList(sql, param, new OverPaidEmployeeRowMapper());
    }
}
