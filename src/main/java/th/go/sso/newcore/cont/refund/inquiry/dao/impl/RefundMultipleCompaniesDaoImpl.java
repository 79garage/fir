package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.constant.type.OrderDirection;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OverRefundEmployeeNoticeRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundEmployeeMultipleCompaniesRowMapper;

@Repository
public class RefundMultipleCompaniesDaoImpl extends DaoBase<Long> {
    public RefundMultipleCompaniesDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }

    public List<OverRefundEmployeeBean> findRefundPeriods(String idCardNo, String receiveNo, String progressStatus, String startPeriod, String endPeriod) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = " SELECT * "+
                " FROM "+
                " ( "+
                " SELECT "+
                " NULL              AS REFUND_REQUEST_ID, "+
                " NULL              AS REFUND_PERIOD_ID, "+
                " CMSI.INSURER_ID, "+
                " CMSI.ID_CARD_NO, "+
                " CTRRC.PAY_PERIOD, "+
                " CTRRC.PAY_PERIOD_YEAR, "+
                " CTRRC.PAY_PERIOD_MONTH, "+
                " '01'              AS REFUND_REASON_CODE, "+
                " NVL( CTRRE.CNTR_AMOUNT, 0 ) - NVL( CTRRE.EMPE_OVERP, 0 ) AS SUM_AMOUNT, "+
//                " NVL( CTRRE.CNTR_AMOUNT, 0 ) - ROUND( ROUND( ROUND( ( ( NVL( CTRRE.CNTR_RATE, 0 ) * "+
//                " CASE WHEN NVL( CTRRE.TOTAL_WAGES, 0 ) >= 15000 THEN 15000 ELSE NVL( CTRRE.TOTAL_WAGES, 0 ) END ) / 100 ), 2), 1), 0) AS OVER_PAID, " +
                " CASE WHEN CTRRE.REFUND_STATUS = 'RR' THEN NVL( CTRRE.EMPE_OVERP, 0 ) ELSE 0 END  AS OVER_PAID, " +
                " NVL( CTRRE.CNTR_RATE, 0 ) AS CNTR_RATE, " +
                " NVL( CTRRE.TOTAL_WAGES, 0 ) AS TOTAL_WAGES, "+
                " CMSC.COMPANY_NO, "+
                " NULL              AS COUNT_COMPANY, "+
                " NULL              AS APPROVE_STATUS, "+
                " NULL              AS APPROVE_NO, "+
                " NULL              AS APPROVE_DATE, "+
                " NULL              AS RECEIPT_NO, "+
                " CTRRE.PAY_DATE              AS PAYMENT_DATE, "+
                " NULL              AS PROGRESS_STATUS, "+
                " NULL              AS NOTICE_STATUS, "+
                " NULL              AS ANNOUNCE_STATUS, "+
                " NULL              AS REVERSE_STATUS, "+
                " NULL              AS REVERSE_REMARK, "+
                " NULL              AS COUNT_ORDER_NO, "+
                " NULL              AS ORDER_NO, "+
                " NULL              AS ORDER_STATUS, "+
                " NULL              AS RECEIVE_NO "+
                " FROM "+
                " CON_TR_RECEIVE_COMPANY CTRRC "+
                " JOIN CON_TR_RECEIVE_EMPLOYEE CTRRE ON CTRRE.RECEIVE_COMPANY_ID = CTRRC.RECEIVE_COMPANY_ID AND CTRRE.STATUS = 'A' "+
                " LEFT JOIN CON_TB_EMPLOYMENT       CTBE ON CTBE.COMPANY_ID = CTRRC.COMPANY_ID AND CTBE.INSURER_ID = CTRRE.INSURER_ID "+
                " AND TRUNC(CTRRE.PAY_DATE) BETWEEN TRUNC(CTBE.START_DATE) AND COALESCE(TRUNC(CTBE.RESIGN_DATE),TRUNC(SYSDATE)) "+
                " JOIN CON_MS_INSURER          CMSI ON CMSI.INSURER_ID = CTBE.INSURER_ID "+
                " JOIN CON_MS_COMPANY          CMSC ON CMSC.COMPANY_ID = CTBE.COMPANY_ID "+
////                " LEFT JOIN CON_TB_EMPLOYMENT       CTBE ON CTBE.COMPANY_ID = CTRRC.COMPANY_ID AND CTBE.INSURER_ID = CTRRE.INSURER_ID "+
//                " JOIN CON_MS_INSURER          CMSI ON CMSI.INSURER_ID = CTRRE.INSURER_ID "+
//                " JOIN CON_MS_COMPANY          CMSC ON CMSC.COMPANY_ID = CTRRC.COMPANY_ID "+
                " WHERE "+
                " 1 = 1 ";
        if (!StringUtils.isEmpty(idCardNo)) {
            sql += " AND CMSI.ID_CARD_NO = :idCardNo ";
        }
        sql += " AND NOT EXISTS ( "+
                " SELECT * FROM "+
                " CON_TB_REFUND_REQUEST NECTBRR "+
                " JOIN CON_TB_REFUND_PERIOD NECTBRP ON NECTBRP.REFUND_REQUEST_ID = NECTBRR.REFUND_REQUEST_ID "+
                " WHERE "+
                " NECTBRP.EMPLOYEE_ID = CTRRE.INSURER_ID "+
                " AND NECTBRP.REFUND_PERIOD = CTRRC.PAY_PERIOD "+
                " AND (NECTBRP.STATUS IS NULL OR NECTBRP.STATUS NOT IN ( 'C', 'D' )) "+
                " ) "+
                " UNION ALL "+
                " SELECT "+
                " CTBRR.REFUND_REQUEST_ID, "+
                " CTBRP.REFUND_PERIOD_ID, "+
                " CMSI.INSURER_ID, "+
                " CMSI.ID_CARD_NO, "+
                " CTBRP.REFUND_PERIOD       AS PAY_PERIOD, "+
                " CTBRP.REFUND_PERIOD_YEAR  AS PAY_PERIOD_YEAR, "+
                " CTBRP.REFUND_PERIOD_MONTH AS PAY_PERIOD_MONTH, "+
                " CTBRR.REQUEST_REASON_CODE AS REFUND_REASON_CODE, "+
                " SUM( CTRREC.CNTR_AMOUNT ) - SUM( CTRREC.EMPE_OVERP )    AS SUM_AMOUNT, "+
//                " NVL( CTRRE.REQUEST_TOTAL_AMOUNT, NVL( CTRRE.REFUND_TOTAL_AMOUNT, 0 ) ) AS OVER_PAID, " +
                " SUM( CTRREC.CNTR_AMOUNT ) - ( ( CASE WHEN SUM( CTRREC.TOTAL_WAGES ) >= 15000 THEN 15000 ELSE SUM( CTRREC.TOTAL_WAGES ) END * NVL( MAX( CTRREC.CNTR_RATE ), 0 ) ) / 100 ) - SUM( CTRREC.EMPE_OVERP ) AS OVER_PAID, "+
                " NULL                      AS CNTR_RATE, "+
                " NULL                      AS TOTAL_WAGES, "+
                " NULL                      AS COMPANY_NO, "+
                " NULL                      AS COUNT_COMPANY, "+
                " CTBRR.APPROVE_STATUS, "+
                " CTBRR.APPROVE_NO, "+
                " CTBRR.APPROVE_DATE, "+
                " NULL                      AS RECEIPT_NO, "+
                " MAX(CTRREC.PAY_DATE)                      AS PAYMENT_DATE, "+
                " ( CASE WHEN CTRRE.PROGRESS_STATUS = '1' OR CTRRE.PROGRESS_STATUS = '0' THEN "+
                " ( CASE WHEN CTBRP.APPROVE_STATUS = '1' OR CTBRP.APPROVE_STATUS = '0' THEN "+
                " CTBRP.APPROVE_STATUS ELSE CTRRE.PROGRESS_STATUS END "+
                " ) "+
                " ELSE CTRRE.PROGRESS_STATUS END "+
                " )                         AS PROGRESS_STATUS, "+
                " CTBRR.NOTICE_STATUS, "+
                " CTBRR.ANNOUNCE_STATUS, "+
                " CTBRR.REVERSE_STATUS, "+
                " CTBRR.REVERSE_REMARK, "+
                " NULL                      AS COUNT_ORDER_NO, "+
                " NULL                      AS ORDER_NO, "+
                " NULL                      AS ORDER_STATUS, "+
                " CTBRR.RECEIVE_NO "+
                " FROM "+
                " CON_TB_REFUND_REQUEST   CTBRR "+
                " LEFT JOIN CON_TB_REFUND_PERIOD CTBRP ON CTBRR.REFUND_REQUEST_ID = CTBRP.REFUND_REQUEST_ID "+
//                " CON_TB_REFUND_PERIOD CTBRP "+
//                " JOIN CON_TB_REFUND_REQUEST   CTBRR ON CTBRR.REFUND_REQUEST_ID = CTBRP.REFUND_REQUEST_ID "+
                " JOIN CON_TR_REFUND_EMPLOYEE  CTRRE ON CTRRE.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID "+
                " LEFT JOIN CON_TR_RECEIVE_EMPLOYEE CTRREC ON CTRREC.INSURER_ID = CTBRP.EMPLOYEE_ID AND CTRREC.PAY_PERIOD = CTBRP.REFUND_PERIOD "+
                " LEFT JOIN CON_TR_RECEIVE_COMPANY  CTRRC ON CTRRC.RECEIVE_COMPANY_ID = CTRREC.RECEIVE_COMPANY_ID "+
                " JOIN CON_MS_INSURER          CMSI ON CMSI.INSURER_ID = CTBRR.INSURER_ID "+
                " WHERE "+
                " 1 = 1 "+
                " AND CTBRR.SECTION = '3' " +
                " AND ( CTBRP.STATUS != 'D' OR CTBRP.STATUS IS NULL )";

        if(!StringUtils.isEmpty(idCardNo)) {
            sql += " AND CMSI.ID_CARD_NO = :idCardNo ";
        }
        sql += " GROUP BY "+
                " CTBRR.REFUND_REQUEST_ID, "+
                " CTBRP.REFUND_PERIOD_ID, "+
                " CMSI.INSURER_ID, "+
                " CMSI.ID_CARD_NO, "+
                " CTBRP.REFUND_PERIOD, "+
                " CTBRP.REFUND_PERIOD_YEAR, "+
                " CTBRP.REFUND_PERIOD_MONTH, "+
                " CTBRR.REQUEST_REASON_CODE, "+
                " CTRRE.REQUEST_TOTAL_AMOUNT, "+
                " CTRRE.REFUND_TOTAL_AMOUNT, "+
                " CTBRR.APPROVE_STATUS, "+
                " CTBRR.APPROVE_NO, "+
                " CTBRR.APPROVE_DATE, "+
                " CTRRE.PROGRESS_STATUS, "+
                " CTBRP.APPROVE_STATUS, "+
                " CTBRR.NOTICE_STATUS, "+
                " CTBRR.ANNOUNCE_STATUS, "+
                " CTBRR.REVERSE_STATUS, "+
                " CTBRR.REVERSE_REMARK, "+
                " CTBRR.RECEIVE_NO "+
//                " CTRREC.PAY_DATE "+
                " ) T0 "+
                " WHERE "+
                " 1 = 1 ";
        if (!StringUtils.isEmpty(idCardNo)) {
            sql += " AND t0.id_card_no = :idCardNo ";
            param.put("idCardNo", idCardNo);

            sql += " AND T0.PAY_PERIOD_YEAR || T0.PAY_PERIOD_MONTH BETWEEN :startPeriod AND :endPeriod ";
            param.put("startPeriod", startPeriod);
            param.put("endPeriod", endPeriod);
        } else if (!StringUtils.isEmpty(receiveNo)) {
            sql += " AND t0.receive_no = :receiveNo ";
            param.put("receiveNo", receiveNo);
        }

        if (!StringUtils.isEmpty(progressStatus)) {
            switch (progressStatus) {
                case "N" -> sql += " AND ( t0.progress_status = 'N' OR t0.progress_status IS NULL )";
//                    case "W" -> sql.append(" AND t0.progress_status = 'W' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
//                            " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status IS NULL ) ");
                case "W" -> sql += (" AND t0.progress_status = 'W' ");
                case "0" -> sql += (" AND t0.progress_status = '0' AND ( EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                        " WHERE ctr33.refund_request_id = t0.refund_request_id AND ( ctr33.order_status IS NULL OR ctr33.order_status = 'C' ) ) " +
                        " OR NOT EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33 WHERE ctr33.refund_request_id = t0.refund_request_id ) ) ");
                case "1" -> sql += (" AND t0.progress_status = '1' AND ( EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                        " WHERE ctr33.refund_request_id = t0.refund_request_id AND ( ctr33.order_status IS NULL OR ctr33.order_status = 'C' ) ) " +
                        " OR NOT EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33 WHERE ctr33.refund_request_id = t0.refund_request_id ) ) ");
                case "S" -> sql += (" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                        " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status = 'W' ) ");
                case "F" -> sql += (" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                        " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status = 'F' ) ");
                case "P" -> sql += (" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                        " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status = 'P' ) ");
                case "T" -> sql += (" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                        " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status = 'T' ) ");
                case "C" -> sql += (" AND t0.progress_status = 'C' AND ( EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                        " WHERE ctr33.refund_request_id = t0.refund_request_id AND ( ctr33.order_status IS NULL OR ctr33.order_status = 'C' OR ctr33.order_status = 'F' ) ) " +
                        " OR NOT EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33 WHERE ctr33.refund_request_id = t0.refund_request_id ) ) ");
            }
        }
        return queryForList(sql, param, new RefundEmployeeMultipleCompaniesRowMapper());
    }

    public List<OverRefundEmployeeBean> findRefundPeriodsNotice(String depCode, String depRegionCode, String noticeStatus, String startPeriod, String endPeriod, Paginable paginable) throws Exception{
        try {
//            if (paginable != null) {
//                paginable.setPrimaryKey("ID_CARD_NO");
//                paginable.setOrderBy("TO_DATE(t2.pay_period, 'MM/YYYY') DESC, t3.id_card_no");
//            }
            Map<String, Object> param = new HashMap<>();
            String sql = " SELECT   T000.* FROM " +
                    " ( " +
                    " SELECT /*+ PARALLEL USE_HASH_AGGREGATION */ "+
                    " T00.ID_CARD_NO, "+
                    " T00.FULL_NAME, "+
                    " T00.PAY_PERIOD, "+
                    " T00.ANNOUNCE_STATUS, "+
                    " T00.REFUND_REASON_CODE, "+
                    " T00.SUM_TOTAL_WAGES, "+
                    " T00.FIX_SUM, "+
                    " T00.SUM_AMOUNT - T00.OVER_PAID AS SUM_AMOUNT, " +
                    " T00.SUM_AMOUNT - ( (CASE WHEN T00.SUM_TOTAL_WAGES >= 15000 THEN 15000 ELSE T00.SUM_TOTAL_WAGES END * T00.MAX_CNTR_RATE) / 100 ) - T00.OVER_PAID AS OVER_PAID, "+
//                    " ( T00.SUM_AMOUNT - T00.FIX_SUM ) + T00.OVER_PAID AS OVER_PAID, "+
                    " T00.OVER_PAID                                    AS OVER_PAID1, "+
                    " T00.COUNT_COMPANY, " +
                    " T00.DEP_CODE "+
                    " FROM "+
                    " ( "+
                    " SELECT /*+ USE_HASH_AGGREGATION */ "+
                    " T0.ID_CARD_NO, "+
                    " T0.FULL_NAME, "+
                    " T0.PAY_PERIOD, "+
                    " T0.ANNOUNCE_STATUS, "+
                    " T0.REFUND_REASON_CODE, "+
                    " SUM(T0.TOTAL_WAGES)           AS SUM_TOTAL_WAGES, "+
                    " CASE WHEN SUM(T0.SUM_AMOUNT) > 750 THEN 750 ELSE SUM(T0.SUM_AMOUNT) END AS FIX_SUM, "+
                    " SUM(T0.SUM_AMOUNT)            AS SUM_AMOUNT, "+
                    " SUM(T0.OVER_PAID)             AS OVER_PAID, "+
                    " COUNT(DISTINCT T0.COMPANY_NO) AS COUNT_COMPANY, " +
                    " T0.DEP_CODE, "+
                    " MAX(T0.CNTR_RATE) AS MAX_CNTR_RATE "+
                    " FROM "+
                    " ( "+
                    " SELECT "+
                    " CMSI.SSO_NO       AS ID_CARD_NO, "+
                    " CMSI.FULL_NAME, "+
                    " CTRRC.PAY_PERIOD, "+
                    " NULL              AS ANNOUNCE_STATUS, "+
                    " '01'              AS REFUND_REASON_CODE, "+
                    " CTRRE.TOTAL_WAGES, "+
                    " CTRRE.CNTR_AMOUNT AS SUM_AMOUNT, "+
                    " CTRRE.EMPE_OVERP  AS OVER_PAID, "+
                    " CMSC.COMPANY_NO, "+
                    " CMSI.DEP_CODE, "+
                    " CTRRE.CNTR_RATE "+
                    " FROM "+
                    " CON_TR_RECEIVE_EMPLOYEE CTRRE "+
                    " JOIN CON_TR_RECEIVE_COMPANY CTRRC ON CTRRE.RECEIVE_COMPANY_ID = CTRRC.RECEIVE_COMPANY_ID "+
                    " LEFT JOIN CON_TB_EMPLOYMENT       CTBE ON CTBE.COMPANY_ID = CTRRC.COMPANY_ID "+
                    " AND CTBE.INSURER_ID = CTRRE.INSURER_ID "+
                    " AND TRUNC(CTRRE.PAY_DATE) BETWEEN TRUNC(CTBE.START_DATE) AND COALESCE(TRUNC(CTBE.RESIGN_DATE),TRUNC(SYSDATE)) "+
                    " JOIN CON_MS_INSURER          CMSI ON CMSI.INSURER_ID = CTBE.INSURER_ID "+
                    " JOIN CON_MS_COMPANY          CMSC ON CMSC.COMPANY_ID = CTBE.COMPANY_ID "+
//                    " JOIN CON_MS_INSURER         CMSI ON CMSI.INSURER_ID = CTRRE.INSURER_ID "+
//                    " JOIN CON_MS_COMPANY         CMSC ON CMSC.COMPANY_ID = CTRRC.COMPANY_ID "+
                    " AND CMSC.SSO_BRANCH_CODE = CMSI.DEP_CODE "+
                    " WHERE "+
                    " 1 = 1 "+
                    " AND CMSI.DEP_CODE = :depCode "+
                    " AND CTRRE.STATUS = 'A' "+
                    " AND CMSI.INSURER_STATUS != 'E' ";
//                    " SELECT "+
//                    " CMSI.ID_CARD_NO, "+
//                    " CMSI.FULL_NAME, "+
//                    " CTRRC.PAY_PERIOD, "+
//                    " NULL              AS ANNOUNCE_STATUS, "+
//                    " '01'              AS REFUND_REASON_CODE, "+
//                    " CTRRE.TOTAL_WAGES, "+
//                    " CTRRE.CNTR_AMOUNT AS SUM_AMOUNT, "+
//                    " CTRRE.EMPE_OVERP AS OVER_PAID, "+
////                    " CTRRE.CNTR_AMOUNT - ( ( CTRRE.CNTR_RATE * "+
////                    " CASE WHEN CTRRE.TOTAL_WAGES >= 15000 THEN 15000 ELSE CTRRE.TOTAL_WAGES END ) / 100 ) AS OVER_PAID, "+
//                    " CMSC.COMPANY_NO, " +
//                    " CMSI.DEP_CODE, "+
//                    " CTRRE.CNTR_RATE "+
//                    " FROM "+
//                    " CON_TR_RECEIVE_COMPANY CTRRC "+
//                    " JOIN CON_TR_RECEIVE_EMPLOYEE CTRRE ON CTRRE.RECEIVE_COMPANY_ID = CTRRC.RECEIVE_COMPANY_ID AND CTRRE.STATUS = 'A' "+
////                    " LEFT JOIN CON_TB_EMPLOYMENT       CTBE ON CTBE.INSURER_ID = CTRRE.INSURER_ID AND CTBE.COMPANY_ID = CTRRC.COMPANY_ID "+
////                    " JOIN CON_MS_INSURER          CMSI ON CMSI.INSURER_ID = CTBE.INSURER_ID AND CMSI.INSURER_STATUS != 'E' "+
////                    " JOIN CON_MS_COMPANY          CMSC ON CMSC.COMPANY_ID = CTBE.COMPANY_ID "+
//                    " JOIN CON_MS_INSURER          CMSI ON CMSI.INSURER_ID = CTRRE.INSURER_ID AND CMSI.INSURER_STATUS != 'E' "+
//                    " JOIN CON_MS_COMPANY          CMSC ON CMSC.COMPANY_ID = CTRRC.COMPANY_ID "+
//                    " WHERE "+
//                    " 1 = 1 "+
//                    " AND CMSI.DEP_CODE = :depCode ";
            if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
                sql += " AND CTRRC.PAY_PERIOD_YEAR || CTRRC.PAY_PERIOD_MONTH BETWEEN :startPeriod AND :endPeriod ";
                param.put("startPeriod", startPeriod);
                param.put("endPeriod", endPeriod);
            }
            sql += " AND NOT EXISTS ( "+
                    " SELECT "+
                    " NECTBRR.REFUND_REQUEST_ID "+
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
                    " T0.ANNOUNCE_STATUS, "+
                    " T0.REFUND_REASON_CODE, " +
                    " T0.DEP_CODE "+
                    " ) T00 "+
                    " WHERE "+
                    " 1 = 1 "+
                    " AND T00.SUM_TOTAL_WAGES >= 15000 "+
                    " AND COUNT_COMPANY > 1 "+
                    " UNION ALL "+
                    " SELECT /*+ PARALLEL USE_HASH_AGGREGATION */ "+
                    " CMSI.ID_CARD_NO, "+
                    " CMSI.FULL_NAME, "+
                    " CTBRP.REFUND_PERIOD       AS PAY_PERIOD, "+
                    " CTBRR.ANNOUNCE_STATUS, "+
                    " CTBRR.REQUEST_REASON_CODE AS REFUND_REASON_CODE, "+
                    " SUM(CTRREM.TOTAL_WAGES)    AS SUM_TOTAL_WAGES, "+
                    " NULL                      AS FIX_SUM, "+
                    " SUM(CTRREM.CNTR_AMOUNT)    AS SUM_AMOUNT, "+
                    " NVL( CTRRE.REQUEST_TOTAL_AMOUNT, NVL( CTRRE.REFUND_TOTAL_AMOUNT, 0 ) ) AS OVER_PAID, "+
                    " NULL                      AS OVER_PAID1, "+
                    " NULL                      AS COUNT_COMPANY, " +
                    " CMSI.DEP_CODE "+
                    " FROM "+
                    " CON_TB_REFUND_PERIOD CTBRP "+
                    " JOIN CON_TB_REFUND_REQUEST   CTBRR ON CTBRR.REFUND_REQUEST_ID = CTBRP.REFUND_REQUEST_ID "+
                    " JOIN CON_TR_REFUND_EMPLOYEE  CTRRE ON CTRRE.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID "+
                    " LEFT JOIN CON_TR_RECEIVE_EMPLOYEE CTRREM ON CTRREM.INSURER_ID = CTBRP.EMPLOYEE_ID AND CTRREM.PAY_PERIOD = CTBRP.REFUND_PERIOD "+
                    " LEFT JOIN CON_TR_RECEIVE_COMPANY  CTRRC ON CTRRC.RECEIVE_COMPANY_ID = CTRREM.RECEIVE_COMPANY_ID "+
                    " JOIN CON_MS_INSURER          CMSI ON CMSI.INSURER_ID = CTBRR.INSURER_ID "+
                    " WHERE "+
                    " 1 = 1 "+
                    " AND CTBRR.SECTION = '3' "+
                    " AND CTBRR.ANNOUNCE_STATUS = 'N' "+
                    " AND CMSI.DEP_CODE = :depCode "+
                    " AND ( CTBRR.STATUS IS NULL OR CTBRR.STATUS IN ( 'A' ) ) ";
//                    " AND ( CTBRR.STATUS != 'D' OR CTBRR.STATUS IS NULL )";
            if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
                sql += " AND CTBRP.REFUND_PERIOD_YEAR || CTBRP.REFUND_PERIOD_MONTH BETWEEN :startPeriod AND :endPeriod ";
                param.put("startPeriod", startPeriod);
                param.put("endPeriod", endPeriod);
            }
            sql += " GROUP BY "+
                    " CMSI.ID_CARD_NO, "+
                    " CMSI.FULL_NAME, "+
                    " CTBRP.REFUND_PERIOD, "+
                    " CTBRR.ANNOUNCE_STATUS, "+
                    " CTBRR.REQUEST_REASON_CODE, "+
                    " CTRRE.REQUEST_TOTAL_AMOUNT, "+
                    " CTRRE.REFUND_TOTAL_AMOUNT, "+
                    " CMSI.DEP_CODE "+
                    " ) T000 "+
                    " WHERE 1 = 1 ";
            if (!StringUtils.isEmpty(noticeStatus)) {
                if (Objects.equals("N", noticeStatus)) {
                    sql += " AND t000.announce_status = 'N' ";
                } else if (Objects.equals("0", noticeStatus)) {
                    sql += " AND t000.announce_status IS NULL ";
                }
            }

            if (!ObjectUtils.isObjectNull(paginable)) {
                paginable.setOrderBy("TO_DATE(t000.pay_period, 'MM/YYYY')");
                paginable.setOrderDirection(OrderDirection.DESC);
                paginable.setPrimaryKey("ID_CARD_NO");
            } else {
                sql += " ORDER BY TO_DATE(t000.pay_period, 'MM/YYYY') DESC";
            }
            param.put("depCode", depCode);

            return queryForList(sql, param, paginable, new OverRefundEmployeeNoticeRowMapper());
        }catch (EmptyResultDataAccessException ex){
            return Arrays.asList();
        }
    }
}
