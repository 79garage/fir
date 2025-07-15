package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConMsInsurerPeriodIdsRowmapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.MasterInsurerIdCardRowMapper;

@Repository
public class ConMsInsurerIdCardDaoImpl extends DaoBase<Long> {

    public ConMsInsurerIdCardDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public ConMsInsurerBean searchInsurerEmp(String idCardNo) throws Exception{

        try {

            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT msins.id_card_no,");
            sql.append(" msins.full_name,");
            sql.append(" msins.insurer_status,");
            sql.append(" msins.section,");
            sql.append(" msins.dep_code");
            sql.append(" FROM CON_MS_INSURER msins");
            sql.append(" where 1=1");
//            sql.append(" and msins.insurer_status IN ('A','D','R')");
            sql.append(" AND msins.insurer_status != 'E'");

            Map<String, Object> param = new HashMap<>();

            if (!StringUtils.isEmpty(idCardNo)){
                sql.append(" AND msins.id_card_no = :idCardNo");
                param.put("idCardNo", idCardNo);
            }
            sql.append(" GROUP BY msins.id_card_no,msins.full_name,msins.insurer_status,msins.section,msins.dep_code");

            return queryForObject(sql.toString(), param, new MasterInsurerIdCardRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    public RefundInsurerBean findEmployeeDetail(String idCardNo) throws Exception{
        Map<String, Object> param = new HashMap<>();
        String sql = "SELECT " +
                " ID_CARD_NO, " +
                " FULL_NAME, " +
                " INSURER_STATUS, " +
                " DEP_CODE, " +
                " SECTION, " +
                " INSURER_ID " +
                " FROM CON_MS_INSURER" +
                " WHERE 1 = 1 " +
                " AND INSURER_STATUS IN ('A','D','R') " +
                " AND ID_CARD_NO = :idCardNo " +
                " GROUP BY " +
                " ID_CARD_NO, " +
                " FULL_NAME, " +
                " INSURER_STATUS, " +
                " DEP_CODE, " +
                " SECTION, " +
                " INSURER_ID ";
        param.put("idCardNo", idCardNo);
        return queryForObject(sql.toString(), param, new ConMsInsurerPeriodIdsRowmapper());

//        try {
//
//            final StringBuilder sql = new StringBuilder();
//
//            sql.append("SELECT");
//            sql.append(" msins.id_card_no,");
//            sql.append(" msins.full_name,");
//            sql.append(" msins.insurer_status,");
//            sql.append(" msins.dep_code,");
//            sql.append(" msins.section");
//            sql.append(" FROM CON_MS_INSURER msins");
//            sql.append(" where 1=1");
//            sql.append(" and msins.insurer_status IN ('A','D','R')");
//
//            Map<String, Object> param = new HashMap<>();
//
//            if (!StringUtils.isEmpty(idCardNo)){
//                sql.append(" AND msins.id_card_no = :idCardNo");
//                param.put("idCardNo", idCardNo);
//            }
//            sql.append(" GROUP BY msins.id_card_no,msins.full_name,msins.insurer_status,msins.dep_code,msins.section");
//
//            return queryForObject(sql.toString(), param, new ConMsInsurerPeriodIdsRowmapper());
//        } catch (EmptyResultDataAccessException ex) {
//            return null;
//        }
    }

    @Override
    protected String getTableName() {
        return "CON_MS_INSURER";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }
}
