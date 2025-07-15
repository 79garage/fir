package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConMsInsurerRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.MasterInsurerRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundInsurerInfoRowMapper;

@Repository
public class ConMsInsurerDaoImpl extends DaoBase<Long> {

    public ConMsInsurerDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_MS_INSURER";
    }

    @Override
    protected String getPkColumnName() {
        return "INSURER_ID";
    }

    public ConMsInsurerBean findInsurerDetails(String idCardNo, String receiveNo) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " * "+
                " FROM "+
                " CON_MS_INSURER CMSI " +
                " LEFT JOIN CON_MS_DEPARTMENT CMSD ON CMSD.DEP_CODE = CMSI.DEP_CODE "+
                " WHERE "+
                " 1 = 1 ";
//                " INSURER_STATUS != 'E' ";
        if(!StringUtils.isEmpty(idCardNo)) {
            sql += " AND ID_CARD_NO = :idCardNo ";
        } else if (!StringUtils.isEmpty(receiveNo)) {
            sql += " AND ID_CARD_NO = "+
                    " ( "+
                    " SELECT "+
                    " CMS.ID_CARD_NO "+
                    " FROM "+
                    " CON_MS_INSURER CMS "+
                    " JOIN CON_TB_REFUND_REQUEST CTBRR ON CMS.INSURER_ID = CTBRR.INSURER_ID "+
                    " WHERE "+
                    " CTBRR.RECEIVE_NO = :receiveNo "+
                    " ) ";
        }

        param.put("idCardNo", idCardNo);
        param.put("receiveNo", receiveNo);
        return queryForObject(sql, param, new ConMsInsurerRowMapper());
    }

    public ConMsInsurerBean searchInsurer(Long insurerId, String receiveNo) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT msins.id_card_no,");
            sql.append(" msins.insurer_id,");
            sql.append(" msins.full_name,");
            sql.append(" msins.insurer_status,");
            sql.append(" msins.section,");
            sql.append(" msins.dep_code,");
            sql.append(" cmsd.dep_name");
            sql.append(" FROM CON_MS_INSURER msins");
            sql.append(" LEFT JOIN CON_MS_DEPARTMENT cmsd ON msins.dep_code = cmsd.dep_code AND cmsd.dep_status = 'A' ");

            if (!StringUtils.isEmpty(receiveNo)) {
                sql.append(" LEFT JOIN con_tb_refund_request req ON (msins.insurer_id = req.insurer_id) ");
            }
            sql.append(" WHERE 1 = 1");
            sql.append(" AND msins.insurer_status NOT IN ('F','E','W')");

            Map<String, Object> param = new HashMap<>();

            if (!NumberUtils.isZeroOrNull(insurerId)) {
                sql.append(" AND msins.insurer_id = :insurerId");
                param.put("insurerId", insurerId);
            }
            if (!StringUtils.isEmpty(receiveNo)) {
                sql.append(" AND req.receive_no = :receiveNo");
                param.put("receiveNo",receiveNo);
            }
            return queryForObject(sql.toString(), param, new MasterInsurerRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public RefundInsurerBean searchRefundInsurerInfo(Long insurerId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " INS.INSURER_ID, "+
                " INS.ID_CARD_NO, "+
                " INS.TITLE_CODE, "+
                " INS.FIRST_NAME, "+
                " INS.LAST_NAME, "+
                " INS.FULL_NAME, "+
                " INS.INSURER_STATUS, "+
                " INS.ADDRESS, "+
                " LOC.SUBDIST_CODE, "+
//                " LOC.SUBDIST_NAME, "+
				" LOC.LOC_NAME_REP AS SUBDIST_NAME, "+ // แก้ไขชั่วคราว
                " LOC.DIST_CODE, "+
                " LOC.DIST_NAME, "+
                " LOC.PROV_CODE AS PROVINCE_CODE, "+
                " LOC.PROV_NAME AS PROVINCE_NAME, "+
                " LOC.LOC_NAME, " +
                " LOC.LOC_NAME_REP, " +
                " LOC.ZIP_CODE, "+
                " INS.TELEPHONE, "+
                " INS.MOO, "+
                " INS.SOI, "+
                " INS.ROAD, "+
                " INS.VILLAGE, "+
                " INS.MOBILE, "+
                " INS.EMAIL, "+
                " INS.CREATE_BY, "+
                " INS.CREATE_DATE, "+
                " INS.SECTION, "+
                " INS.DEP_CODE "+
                " FROM "+
                " CON_MS_INSURER  INS "+
                " LEFT JOIN CON_MS_LOCATION LOC ON LOC.SUBDIST_CODE = INS.SUBDIST_CODE "+
                " WHERE "+
                " 1 = 1 ";
        if (!NumberUtils.isZeroOrNull(insurerId)) {
            sql += (" AND INS.INSURER_ID = :insurerId");
            param.put("insurerId", insurerId);
        }
        return queryForObject(sql, param, new RefundInsurerInfoRowMapper());
    }
}
