package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OverRefundEmployeeNoticeRowMapper;

@Repository
public class OverRefundEmployeeNoticeDaoImpl extends DaoBase<Long> {
    public OverRefundEmployeeNoticeDaoImpl(DataSource dataSource) {
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

    public List<OverRefundEmployeeBean> queryRefundNotice(String depCode, String depRegionCode, String noticeStatus, String startPeriod, String endPeriod, Paginable paginable) throws Exception{
        try {
            if (paginable != null) {
                paginable.setPrimaryKey("ID_CARD_NO");
                paginable.setOrderBy("TO_DATE(t2.pay_period, 'MM/YYYY') DESC, t3.id_card_no");
            }
            Map<String, Object> param = new HashMap<>();
            String sql = " SELECT T000.* FROM " +
                    " ( " +
                    " SELECT "+
                    " T00.ID_CARD_NO, "+
                    " T00.FULL_NAME, "+
                    " T00.PAY_PERIOD, "+
                    " T00.NOTICE_STATUS, "+
                    " T00.REFUND_REASON_CODE, "+
                    " T00.SUM_TOTAL_WAGES, "+
                    " T00.FIX_SUM, "+
                    " T00.SUM_AMOUNT, " +
                    " ( T00.SUM_AMOUNT - T00.FIX_SUM ) + T00.OVER_PAID AS OVER_PAID, "+
                    " T00.OVER_PAID                                    AS OVER_PAID1, "+
                    " T00.COUNT_COMPANY "+
                    " FROM "+
                    " ( "+
                    " SELECT "+
                    " T0.ID_CARD_NO, "+
                    " T0.FULL_NAME, "+
                    " T0.PAY_PERIOD, "+
                    " T0.NOTICE_STATUS, "+
                    " T0.REFUND_REASON_CODE, "+
                    " SUM(T0.TOTAL_WAGES)           AS SUM_TOTAL_WAGES, "+
                    " CASE WHEN SUM(T0.SUM_AMOUNT) > 750 THEN 750 ELSE SUM(T0.SUM_AMOUNT) END AS FIX_SUM, "+
                    " SUM(T0.SUM_AMOUNT)            AS SUM_AMOUNT, "+
                    " SUM(T0.OVER_PAID)             AS OVER_PAID, "+
                    " COUNT(DISTINCT T0.COMPANY_NO) AS COUNT_COMPANY "+
                    " FROM "+
                    " ( "+
                    " SELECT "+
                    " CMSI.ID_CARD_NO, "+
                    " CMSI.FULL_NAME, "+
                    " CTRRC.PAY_PERIOD, "+
                    " NULL              AS NOTICE_STATUS, "+
                    " '01'              AS REFUND_REASON_CODE, "+
                    " CTRRE.TOTAL_WAGES, "+
                    " CTRRE.CNTR_AMOUNT AS SUM_AMOUNT, "+
                    " CTRRE.CNTR_AMOUNT - ( ( CTRRE.CNTR_RATE * "+
                    " CASE WHEN CTRRE.TOTAL_WAGES >= 15000 THEN 15000 ELSE CTRRE.TOTAL_WAGES END ) / 100 ) AS OVER_PAID, "+
                    " CMSC.COMPANY_NO "+
                    " FROM "+
                    " CON_TR_RECEIVE_COMPANY CTRRC "+
                    " JOIN CON_TR_RECEIVE_EMPLOYEE CTRRE ON CTRRE.RECEIVE_COMPANY_ID = CTRRC.RECEIVE_COMPANY_ID AND CTRRE.STATUS = 'A' "+
                    " LEFT JOIN CON_TB_EMPLOYMENT       CTBE ON CTBE.INSURER_ID = CTRRE.INSURER_ID AND CTBE.COMPANY_ID = CTRRC.COMPANY_ID "+
                    " JOIN CON_MS_INSURER          CMSI ON CMSI.INSURER_ID = CTBE.INSURER_ID AND CMSI.INSURER_STATUS != 'E' "+
                    " JOIN CON_MS_COMPANY          CMSC ON CMSC.COMPANY_ID = CTBE.COMPANY_ID "+
                    " WHERE "+
                    " 1 = 1 "+
                    " AND CMSI.DEP_CODE = :depCode ";
            if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
                sql += " AND CTRRC.PAY_PERIOD_YEAR || CTRRC.PAY_PERIOD_MONTH BETWEEN :startPeriod AND :endPeriod ";
                param.put("startPeriod", startPeriod);
                param.put("endPeriod", endPeriod);
            }
                    sql += " AND NOT EXISTS ( "+
                    " SELECT "+
                    " * "+
                    " FROM "+
                    " CON_TB_REFUND_REQUEST NECTBRR "+
                    " JOIN CON_TB_REFUND_PERIOD NECTBRP ON NECTBRP.REFUND_REQUEST_ID = NECTBRR.REFUND_REQUEST_ID "+
                    " WHERE "+
                    " NECTBRP.EMPLOYEE_ID = CTRRE.INSURER_ID "+
                    " AND NECTBRP.REFUND_PERIOD = CTRRC.PAY_PERIOD "+
                    " AND ( NECTBRR.STATUS IS NULL OR NECTBRR.STATUS IN ( 'A' ) ) "+
                    " ) "+
                    " ) T0 "+
                    " WHERE "+
                    " 1 = 1 "+
                    " GROUP BY "+
                    " T0.ID_CARD_NO, "+
                    " T0.FULL_NAME, "+
                    " T0.PAY_PERIOD, "+
                    " T0.NOTICE_STATUS, "+
                    " T0.REFUND_REASON_CODE "+
                    " ) T00 "+
                    " WHERE "+
                    " 1 = 1 "+
                    " AND T00.SUM_TOTAL_WAGES >= 15000 "+
                    " AND COUNT_COMPANY > 1 "+
                    " UNION ALL "+
                    " SELECT "+
                    " CMSI.ID_CARD_NO, "+
                    " CMSI.FULL_NAME, "+
                    " CTBRP.REFUND_PERIOD       AS PAY_PERIOD, "+
                    " CTBRR.NOTICE_STATUS, "+
                    " CTBRR.REQUEST_REASON_CODE AS REFUND_REASON_CODE, "+
                    " SUM(CTRRE.TOTAL_WAGES)    AS SUM_TOTAL_WAGES, "+
                    " NULL                      AS FIX_SUM, "+
                    " SUM(CTRRE.CNTR_AMOUNT)    AS SUM_AMOUNT, "+
                    " NVL( CTRRE.REQUEST_TOTAL_AMOUNT, NVL( CTRRE.REFUND_TOTAL_AMOUNT, 0 ) ) AS OVER_PAID, "+
                    " NULL                      AS OVER_PAID1, "+
                    " NULL                      AS COUNT_COMPANY "+
                    " FROM "+
                    " CON_TB_REFUND_PERIOD CTBRP "+
                    " JOIN CON_TB_REFUND_REQUEST   CTBRR ON CTBRR.REFUND_REQUEST_ID = CTBRP.REFUND_REQUEST_ID "+
                    " JOIN CON_TR_REFUND_EMPLOYEE  CTRRE ON CTRRE.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID "+
                    " LEFT JOIN CON_TR_RECEIVE_EMPLOYEE CTRRE ON CTRRE.INSURER_ID = CTBRP.EMPLOYEE_ID AND CTRRE.PAY_PERIOD = CTBRP.REFUND_PERIOD "+
                    " LEFT JOIN CON_TR_RECEIVE_COMPANY  CTRRC ON CTRRC.RECEIVE_COMPANY_ID = CTRRE.RECEIVE_COMPANY_ID "+
                    " JOIN CON_MS_INSURER          CMSI ON CMSI.INSURER_ID = CTBRR.INSURER_ID "+
                    " WHERE "+
                    " 1 = 1 "+
                    " AND CTBRR.SECTION = '3' "+
                    " AND CTBRR.NOTICE_STATUS = 'N' "+
                    " AND CMSI.DEP_CODE = :depCode "+
                    " AND ( CTBRR.STATUS IS NULL OR CTBRR.STATUS IN ( 'A' ) ) ";
            if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
                sql += " AND CTBRP.REFUND_PERIOD_YEAR || CTBRP.REFUND_PERIOD_MONTH BETWEEN :startPeriod AND :endPeriod ";
                param.put("startPeriod", startPeriod);
                param.put("endPeriod", endPeriod);
            }
                    sql += " GROUP BY "+
                    " CMSI.ID_CARD_NO, "+
                    " CMSI.FULL_NAME, "+
                    " CTBRP.REFUND_PERIOD, "+
                    " CTBRR.NOTICE_STATUS, "+
                    " CTBRR.REQUEST_REASON_CODE, "+
                    " CTRRE.REQUEST_TOTAL_AMOUNT, "+
                    " CTRRE.REFUND_TOTAL_AMOUNT " +
                    " ) T000 " +
                    " WHERE 1 = 1 ";
            if (!StringUtils.isEmpty(noticeStatus)) {
                if (Objects.equals("N", noticeStatus)) {
                    sql += " AND t000.notice_status = 'N' ";
                } else if (Objects.equals("0", noticeStatus)) {
                    sql += " AND t000.notice_status IS NULL ";
                }
            }

            param.put("depCode", depCode);

            return queryForList(sql, param, paginable, new OverRefundEmployeeNoticeRowMapper());
        }catch (EmptyResultDataAccessException ex){
            return Arrays.asList();
        }
    }

//    public List<OverRefundEmployeeBean> queryRefundNotice(String depCode, String depRegionCode, String noticeStatus, String startPeriod, String endPeriod, Paginable paginable) throws Exception{
//        try {
//            StringBuilder sql = new StringBuilder();
//            Map<String, Object> param = new HashMap<>();
//            if (paginable != null) {
//                paginable.setPrimaryKey("ID_CARD_NO");
//                paginable.setOrderBy("TO_DATE(t2.pay_period, 'MM/YYYY') DESC, t3.id_card_no");
//            }
//            sql.append("SELECT t3.id_card_no,t3.full_name,t2.pay_period,t7.notice_status, ");
////            sql.append(" t1.refund_reason_code,");
//            sql.append(" '01' AS refund_reason_code,");
//            sql.append(" SUM(t1.cntr_amount) AS sum_amount, ");
////            sql.append(" SUM(t1.cntr_amount) - 750 AS over_paid,");
//            sql.append(" SUM(t1.cntr_amount) - ( ( MAX(t1.cntr_rate) * ");
//            sql.append(" CASE WHEN SUM(t1.total_wages) >= 15000 THEN 15000 ELSE SUM(t1.total_wages) END ) / 100 ) AS over_paid, ");
//            sql.append(" COUNT(DISTINCT t4.company_no) AS count_company, ");
//            sql.append(" t3.dep_code ");
//            sql.append(" FROM con_tr_receive_employee t1");
//            sql.append(" JOIN con_tr_receive_company  t2 ON t2.receive_company_id = t1.receive_company_id");
////            sql.append(" LEFT JOIN con_tb_employment t5 ON t5.insurer_id = t1.insurer_id AND t5.company_id = t2.company_id");
////            sql.append(" LEFT JOIN con_ms_insurer t3 ON t3.insurer_id = t5.insurer_id");
////            sql.append(" LEFT JOIN con_ms_company t4 ON t4.company_id = t5.company_id");
//            sql.append(" JOIN con_ms_insurer t3 ON t3.insurer_id = t1.insurer_id AND t3.insurer_status != 'E'");
//            sql.append(" JOIN con_ms_company t4 ON t4.company_id = t2.company_id");
//            sql.append(" LEFT JOIN con_tb_refund_period t6 ON t6.refund_period = t2.pay_period AND t6.employee_id = t3.insurer_id");
//            sql.append(" LEFT JOIN con_tb_refund_request t7 ON t7.refund_request_id = t6.refund_request_id");
//            sql.append(" LEFT JOIN con_tr_refund_employee t8 ON t8.refund_request_id = t7.refund_request_id");
////            sql.append(" LEFT JOIN con_ms_department t9 ON t9.dep_code = t3.dep_code");
//            sql.append(" WHERE 1 = 1 AND t8.progress_status IS NULL AND t1.status = 'A'");
//
//            if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)){
//                sql.append(" AND t2.pay_period_year || t2.pay_period_month BETWEEN :startPeriod AND :endPeriod ");
//                param.put("startPeriod", startPeriod);
//                param.put("endPeriod", endPeriod);
//            }
////            if (!StringUtils.isEmpty(depRegionCode)){
////                sql.append(" AND t9.dep_regioncode = :depRegionCode");
////                param.put("depRegionCode", depRegionCode);
////            }
//            if (!StringUtils.isEmpty(noticeStatus)){
//                if ("N".equals(noticeStatus)){
//                    sql.append(" AND t7.notice_status = 'N'");
//                }else if ("0".equals(noticeStatus)){
//                    sql.append(" AND t7.notice_status IS NULL");
//                }
//            }
//            if (!StringUtils.isEmpty(depCode)){
////                sql.append(" AND EXISTS (SELECT ex.insurer_id FROM con_tb_employment ex WHERE 1 = 1 AND ex.dep_code = :depCode AND t1.insurer_id = ex.insurer_id)");
//                sql.append(" AND t3.dep_code = :depCode ");
//                param.put("depCode", depCode);
//            }
////            else {
////                sql.append(" AND EXISTS (SELECT ex.insurer_id FROM con_tb_employment ex WHERE 1 = 1 AND ex.dep_code = t5.dep_code AND t1.insurer_id = ex.insurer_id)");
////            }
//            sql.append(" GROUP BY t3.id_card_no,t3.full_name,t2.pay_period,t1.refund_reason_code,t7.notice_status,t3.dep_code ");
////            sql.append(" HAVING SUM(t1.cntr_amount) > 750 AND COUNT(DISTINCT t4.company_no) > 1");
//            sql.append(" HAVING");
//            sql.append(" SUM(t1.cntr_amount) > ( ( MAX(t1.cntr_rate) * CASE WHEN SUM(t1.total_wages) >= 15000 THEN 15000 ELSE SUM(t1.total_wages) END ) / 100 )");
//            sql.append(" AND COUNT(DISTINCT t4.company_no) > 1");
//            sql.append(" AND SUM(t1.total_wages) > 15000");
//            sql.append(" ORDER BY t3.id_card_no, TO_DATE(t2.pay_period, 'MM/YYYY') DESC ");
//            sql.append(" FETCH FIRST 100 ROWS ONLY ");
//
//            return queryForList(sql.toString(), param, paginable, new OverRefundEmployeeNoticeRowMapper());
//        }catch (EmptyResultDataAccessException ex){
//            return null;
//        }
//    }
}
