package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsLocationBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConMsLocationRowMapper;

@Repository
public class ConMsLocationDaoImpl extends DaoBase<Long> {
    private static final Logger log = LoggerFactory.getLogger(ConMsLocationDaoImpl.class);

    public ConMsLocationDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_MS_LOCATION";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }

    public ConMsLocationBean queryLocation(String subDistCode) throws Exception {
        try {
            final String sql = "SELECT PROV_CODE,PROV_NAME,DIST_CODE,DIST_NAME,SUBDIST_CODE,SUBDIST_NAME FROM CON_MS_LOCATION WHERE 1 = 1 AND SUBDIST_CODE = :subDistCode";
            final Map<String, Object> param = new HashMap<>(1);
            param.put("subDistCode", subDistCode);
            return queryForObject(sql, param, new ConMsLocationRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            log.error("ConMsLocationDaoImpl queryLocation error : ", ex);
            return null;
        }
    }
}
