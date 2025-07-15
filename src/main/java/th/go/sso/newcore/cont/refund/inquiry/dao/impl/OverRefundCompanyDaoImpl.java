package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.constant.type.OrderDirection;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OverRefundCompanyPeriodRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OverRefundCompanyRowMapper;

@Repository
public class OverRefundCompanyDaoImpl extends DaoBase<Long> {

    public OverRefundCompanyDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TR_RECEIVE_COMPANY";
    }

    @Override
    protected String getPkColumnName() {
        return "RECEIVE_COMPANY_ID";
    }

    public List<OverRefundCompanyBean> searchRefundCompany(Long companyId, String companyNo
            , String companyBranchCode
            , String receiveNo
            , String approveNo
            , String startPeriod
            , String endPeriod
            , String progressStatus
            , Paginable paginable ) throws Exception {
        try {
            final Map<String, Object> param = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append(" T0.* ");
            sql.append(" FROM ");
            sql.append(" ( ");
            sql.append(" SELECT T01.* FROM ");
            sql.append(" ( SELECT ");
            sql.append(" NULL                    AS RECEIVE_NO, ");
            sql.append(" CTRRC.PAY_PERIOD, ");
            sql.append(" CTRRC.PAY_PERIOD_MONTH, ");
            sql.append(" CTRRC.PAY_PERIOD_YEAR, ");
            sql.append(" NULL                    AS REFUND_PERIOD_ID, ");
            sql.append(" CTRRC.COMPANY_ID, ");
            sql.append(" NULL                    AS REFUND_REQUEST_ID, ");
            sql.append(" CTRRC.RECEIVE_COMPANY_ID, ");
            sql.append(" CTRRC.PAY_DATE          AS PAYDATEOFCNTR, ");
            sql.append(" CTRRC.TOTAL_WAGES, ");
            sql.append(" CTRRC.CNTR_EMPE_RATE || ':' || CTRRC.CNTR_EMPE_RATE AS CNTR_RATE, ");
            sql.append(" CTRRC.NUM_OF_EMPLOYEE, ");
            sql.append(" SUM(NVL(CTBCC.CNTR_AMOUNT, 0))                                    AS CNTR_AMOUNT, ");
            sql.append(" SUM(NVL(CTRRC.INTEREST_PAID, 0))                                  AS INTEREST_PAID, ");
            sql.append(" COALESCE(NULLIF(SUM(NVL(CTBCC.EMPR_OVER, 0)), 0), SUM(NVL(CTRRE.EMPR_OVERP, 0))) + SUM(NVL(CTRRE.EMPE_OVERP,0)) + SUM(NVL(CTRRE.EMPR_AMOUNT,0))  AS OVER_PAID, ");
            sql.append(" COALESCE(NULLIF(SUM(NVL(CTBCC.OVER_INTEREST_PAID, 0)), 0), SUM(NVL(CTRRC.OVER_INTEREST_PAID, 0))) AS INTEREST_AMOUNT, ");
            sql.append(" COALESCE(NULLIF(SUM(NVL(CTBCC.EMPR_OVER, 0)), 0), SUM(NVL(CTRRE.EMPR_OVERP, 0))) + SUM(NVL(CTRRE.EMPR_AMOUNT,0)) + ");
            sql.append(" SUM(CTRRE.EMPE_OVERP) + COALESCE(NULLIF(SUM(NVL(CTBCC.OVER_INTEREST_PAID, 0)), 0), SUM(NVL(CTRRC.OVER_INTEREST_PAID, 0))) AS TOTAL_OVER, ");
            sql.append(" SUM(NVL(CTBCC.CNTR_AMOUNT, 0)) + SUM(NVL(CTRRC.INTEREST_PAID, 0)) AS TOTAL_CONTRIBUTION, ");
//            sql.append(" NVL( CTRRC.CNTR_AMOUNT, 0 )                       AS CNTR_AMOUNT, ");
//            sql.append(" NVL( CTRRC.INTEREST_PAID, 0 )                       AS INTEREST_PAID, ");
//            sql.append(" NVL( CTRRC.OVER_PAID, 0 )                       AS OVER_PAID, ");
//            sql.append(" NVL( CTRRC.OVER_INTEREST_PAID, 0 )                       AS INTEREST_AMOUNT, ");
//            sql.append(" NVL( CTRRC.OVER_PAID, 0 ) + NVL( CTRRC.OVER_INTEREST_PAID, 0 )                       AS TOTAL_OVER, ");
//            sql.append(" NVL( CTRRC.CNTR_AMOUNT, 0 ) + NVL( CTRRC.INTEREST_PAID, 0 )                       AS TOTAL_CONTRIBUTION, ");
            sql.append(" NULL                    AS APPROVE_NO, ");
            sql.append(" NULL                    AS APPROVE_DATE, ");
            sql.append(" NULL                    AS RECEIPT_NO, ");
            sql.append(" CTRRC.PAY_DATE                    AS PAYMENT_DATE, ");
            sql.append(" NULL                    AS APPROVE_STATUS, ");
            sql.append(" NULL                    AS PROGRESS_STATUS, ");
            sql.append(" NULL                    AS NOTICE_STATUS, ");
            sql.append(" NULL                    AS ANNOUNCE_STATUS, ");
//            sql.append(" CTRRC.REFUND_REASON_CODE, ");
            sql.append(" CASE WHEN CTRRC.REFUND_REASON_CODE = '04' THEN '06' ELSE CTRRC.REFUND_REASON_CODE END AS REFUND_REASON_CODE, ");
            sql.append(" NULL                    AS REQUEST_REASON_CODE, ");
            sql.append(" NULL                    AS REQUEST_REASON_OTHER, ");
            sql.append(" NULL                    AS REVERSE_STATUS, ");
            sql.append(" NULL                    AS REVERSE_REMARK, ");
            sql.append(" NULL                    AS ORDER_NO, ");
            sql.append(" NULL                    AS ORDER_STATUS, ");
            sql.append(" CMSC.COMPANY_BRANCH_CODE, ");
            sql.append(" CMSC.COMPANY_BRANCH_NAME, ");
            sql.append(" NULL AS ELIGIBLE_FLAG, ");
            sql.append(" SUM(NVL(CTBCC.EMPR_OVER, 0) ) + SUM(NVL(CTRRE.EMPR_AMOUNT, 0) ) AS EMPR_OVER, ");
            sql.append(" SUM(NVL(CTRRE.EMPE_OVERP, 0) ) AS EMPE_OVER, ");
            sql.append(" SUM(NVL(CTBCC.OVER_INTEREST_PAID, 0) ) AS OVER_INTEREST_AMOUNT, ");
            sql.append(" NVL(CTRRC.REFUND_EMPR_AMOUNT, 0) AS REFUND_EMPR_AMOUNT, ");
            sql.append(" NVL(CTRRC.REFUND_EMPE_AMOUNT, 0) AS REFUND_EMPE_AMOUNT, ");
            sql.append(" NVL(CTRRC.REFUND_INTEREST_AMOUNT, 0) AS REFUND_INTEREST_AMOUNT ");
            sql.append(" FROM ");
            sql.append(" CON_TR_RECEIVE_COMPANY CTRRC ");
            sql.append(" LEFT JOIN CON_TB_CONTRIBUTION_COMPANY CTBCC ON CTBCC.PAY_PERIOD = CTRRC.PAY_PERIOD AND CTBCC.COMPANY_ID = CTRRC.COMPANY_ID ");
            sql.append(" LEFT JOIN ( ");
            sql.append(" SELECT ");
            sql.append(" RECEIVE_COMPANY_ID, ");
            sql.append(" RECEIPT_ID, ");
            sql.append(" SUM(NVL(REFUND_AMOUNT, 0)) AS REFUND_AMOUNT, ");
            sql.append(" SUM(NVL(EMPR_OVERP, 0)) AS EMPR_OVERP, ");
            sql.append(" SUM(NVL( CASE WHEN REFUND_REASON_CODE IN('01', '02', '07', '09', '10', '11') THEN CNTR_AMOUNT WHEN REFUND_REASON_CODE IN('06') THEN EMPE_OVERP END, 0)) AS EMPE_OVERP, ");
            sql.append(" SUM(NVL( CASE WHEN REFUND_REASON_CODE IN('01', '02', '07', '09', '10', '11') THEN EMPR_AMOUNT END, 0)) AS EMPR_AMOUNT ");
            sql.append(" FROM ");
            sql.append(" CON_TR_RECEIVE_EMPLOYEE ");
            sql.append(" WHERE 1 = 1 AND STATUS = 'A' ");
            sql.append(" GROUP BY ");
            sql.append(" RECEIVE_COMPANY_ID, ");
            sql.append(" RECEIPT_ID ");
//            sql.append(" REFUND_AMOUNT ");
            sql.append(" ) CTRRE ON ( CTRRE.RECEIVE_COMPANY_ID = CTRRC.RECEIVE_COMPANY_ID AND CTRRE.RECEIPT_ID = CTBCC.RECEIPT_ID ) ");
            sql.append(" LEFT JOIN CON_MS_COMPANY         CMSC ON CMSC.COMPANY_ID = CTRRC.COMPANY_ID ");
            sql.append(" WHERE ");
            sql.append(" 1 = 1 ");
            sql.append(" AND CTRRC.REFUND_REASON_CODE IS NOT NULL ");

            if (!StringUtils.isEmpty(companyNo)){
                sql.append(" AND CMSC.COMPANY_NO = :companyNo ");
            }
            if (!StringUtils.isEmpty(companyBranchCode)) {
                sql.append(" AND CMSC.COMPANY_BRANCH_CODE = :companyBranchCode ");
            }

//            sql.append(" AND NOT EXISTS ( ");
//            sql.append(" SELECT ");
//            sql.append(" * ");
//            sql.append(" FROM ");
//            sql.append(" CON_TB_REFUND_REQUEST NECTBRR ");
//            sql.append(" JOIN CON_TB_REFUND_PERIOD NECTBRP ON NECTBRP.REFUND_REQUEST_ID = NECTBRR.REFUND_REQUEST_ID ");
//            sql.append(" WHERE ");
//            sql.append(" NECTBRP.RECEIVE_COMPANY_ID = CTRRC.RECEIVE_COMPANY_ID ");
//            sql.append(" AND ( NECTBRR.STATUS IS NULL OR NECTBRR.STATUS NOT IN ( 'C', 'D' ) ) ");
//            sql.append(" ) ");
            sql.append(" GROUP BY ");
            sql.append(" CTRRC.PAY_PERIOD, ");
            sql.append(" CTRRC.PAY_PERIOD_MONTH, ");
            sql.append(" CTRRC.PAY_PERIOD_YEAR, ");
            sql.append(" CTRRC.COMPANY_ID, ");
            sql.append(" CTRRC.RECEIVE_COMPANY_ID, ");
            sql.append(" CTRRC.PAY_DATE, ");
            sql.append(" CTRRC.TOTAL_WAGES, ");
            sql.append(" CTRRC.CNTR_EMPE_RATE, ");
            sql.append(" CTRRC.CNTR_EMPE_RATE, ");
            sql.append(" CTRRC.NUM_OF_EMPLOYEE, ");
            sql.append(" CTRRC.REFUND_REASON_CODE, ");
            sql.append(" CMSC.COMPANY_BRANCH_CODE, ");
            sql.append(" CMSC.COMPANY_BRANCH_NAME, ");
            sql.append(" CTRRC.REFUND_EMPR_AMOUNT, ");
            sql.append(" CTRRC.REFUND_EMPE_AMOUNT, ");
            sql.append(" CTRRC.REFUND_INTEREST_AMOUNT ");
            sql.append(" ) T01 ");
            sql.append(" WHERE 1 = 1 ");
            sql.append(" AND ( T01.EMPR_OVER > T01.REFUND_EMPR_AMOUNT ");
            sql.append(" OR T01.EMPE_OVER > T01.REFUND_EMPE_AMOUNT ");
            sql.append(" OR T01.OVER_INTEREST_AMOUNT > T01.REFUND_INTEREST_AMOUNT ) ");

            sql.append(" UNION  ALL ");

            sql.append(" SELECT ");
            sql.append(" REREQ.RECEIVE_NO, ");
            sql.append(" RCOMP.PAY_PERIOD, ");
            sql.append(" RCOMP.PAY_PERIOD_MONTH, ");
            sql.append(" RCOMP.PAY_PERIOD_YEAR, ");
            sql.append(" REPER.REFUND_PERIOD_ID, ");
            sql.append(" RCOMP.COMPANY_ID, ");
            sql.append(" REREQ.REFUND_REQUEST_ID, ");
            sql.append(" RCOMP.RECEIVE_COMPANY_ID, ");
            sql.append(" RCOMP.PAY_DATE            AS PAYDATEOFCNTR, ");
            sql.append(" RCOMP.TOTAL_WAGES, ");
            sql.append(" RCOMP.CNTR_EMPE_RATE || ':' || RCOMP.CNTR_EMPE_RATE   AS CNTR_RATE, ");
            sql.append(" RCOMP.NUM_OF_EMPLOYEE, ");
            sql.append(" NVL( RCOMP.CNTR_AMOUNT, 0 ) AS CNTR_AMOUNT, ");
            sql.append(" COALESCE( NULLIF( REPER.REFUND_INTEREST_AMOUNT, 0 ) , NVL( REPER.REQUEST_INTEREST_AMOUNT, 0 )) AS INTEREST_PAID, ");
//            sql.append(" COALESCE( NULLIF( REPER.REFUND_TOTAL_AMOUNT, 0 ), NVL( REPER.REQUEST_TOTAL_AMOUNT, 0 )) AS OVER_PAID, ");
            sql.append(" COALESCE( NULLIF( REPER.REFUND_EMPR_AMOUNT, 0 ), NVL( REPER.REQUEST_EMPR_AMOUNT, 0 )) + COALESCE( NULLIF( REPER.REFUND_EMPE_AMOUNT, 0 ), NVL( REPER.REQUEST_EMPE_AMOUNT, 0 )) AS OVER_PAID, ");
            sql.append(" COALESCE( NULLIF( REPER.REFUND_INTEREST_AMOUNT, 0 ), NVL( REPER.REQUEST_INTEREST_AMOUNT, 0 )) AS INTEREST_AMOUNT, ");
//            sql.append(" COALESCE( NULLIF( REPER.REFUND_TOTAL_AMOUNT, 0 ), NVL( REPER.REQUEST_TOTAL_AMOUNT, 0 )) + COALESCE( NULLIF( REPER.REFUND_INTEREST_AMOUNT, 0 ), NVL( REPER.REQUEST_INTEREST_AMOUNT, 0 )) AS TOTAL_OVER, ");
            sql.append(" COALESCE( NULLIF( REPER.REFUND_TOTAL_AMOUNT, 0 ), NVL( REPER.REQUEST_TOTAL_AMOUNT, 0 )) AS TOTAL_OVER, ");
            sql.append(" NVL( RCOMP.CNTR_AMOUNT, 0 ) + NVL( RCOMP.INTEREST_PAID, 0 ) AS TOTAL_CONTRIBUTION, ");
            sql.append(" REREQ.APPROVE_NO, ");
            sql.append(" REREQ.APPROVE_DATE, ");
//            sql.append(" REREC.RECEIPT_NO, ");
//            sql.append(" REREC.PAYMENT_DATE, ");
            sql.append(" NULL                      AS RECEIPT_NO, ");
            sql.append(" RCOMP.PAY_DATE                      AS PAYMENT_DATE, ");
            sql.append(" REPER.APPROVE_STATUS, ");
            sql.append(" ( CASE ");
            sql.append(" WHEN RECOMP.PROGRESS_STATUS = '1' OR RECOMP.PROGRESS_STATUS = '0' THEN ");
            sql.append(" ( CASE WHEN REPER.APPROVE_STATUS = '1' OR REPER.APPROVE_STATUS = '0' THEN ");
            sql.append(" REPER.APPROVE_STATUS ");
            sql.append(" ELSE RECOMP.PROGRESS_STATUS END ");
            sql.append(" ) ");
            sql.append(" ELSE RECOMP.PROGRESS_STATUS END ");
            sql.append(" )                         AS PROGRESS_STATUS, ");
            sql.append(" REREQ.NOTICE_STATUS, ");
            sql.append(" REREQ.ANNOUNCE_STATUS, ");
            sql.append(" REREQ.REQUEST_REASON_CODE AS REFUND_REASON_CODE, ");
            sql.append(" REREQ.REQUEST_REASON_CODE, ");
            sql.append(" REREQ.REQUEST_REASON_OTHER, ");
            sql.append(" REREQ.REVERSE_STATUS, ");
            sql.append(" REREQ.REVERSE_REMARK, ");
            sql.append(" NULL                      AS ORDER_NO, ");
            sql.append(" NULL                      AS ORDER_STATUS, ");
            sql.append(" CMSC.COMPANY_BRANCH_CODE, ");
            sql.append(" CMSC.COMPANY_BRANCH_NAME, ");
            sql.append(" REREQ.ELIGIBLE_FLAG, ");
            sql.append(" NULL AS EMPR_OVER, ");
            sql.append(" NULL AS EMPE_OVER, ");
            sql.append(" NULL AS OVER_INTEREST_AMOUNT, ");
            sql.append(" NULL AS REFUND_EMPR_AMOUNT, ");
            sql.append(" NULL AS REFUND_EMPE_AMOUNT, ");
            sql.append(" NULL AS REFUND_INTEREST_AMOUNT ");
            sql.append(" FROM ");
            sql.append(" CON_TB_REFUND_REQUEST  REREQ ");
            sql.append(" LEFT JOIN CON_TB_REFUND_PERIOD   REPER ON ( REPER.REFUND_REQUEST_ID = REREQ.REFUND_REQUEST_ID ) ");
            sql.append(" LEFT JOIN CON_TR_RECEIVE_COMPANY RCOMP ON REPER.RECEIVE_COMPANY_ID = RCOMP.RECEIVE_COMPANY_ID AND RCOMP.STATUS = 'A' ");
            sql.append(" LEFT JOIN CON_TR_REFUND_COMPANY  RECOMP ON ( RECOMP.REFUND_REQUEST_ID = REPER.REFUND_REQUEST_ID ) ");
            sql.append(" LEFT JOIN CON_TB_REFUND_REQUEST  REREQ ON ( REREQ.REFUND_REQUEST_ID = RECOMP.REFUND_REQUEST_ID ) ");
            sql.append(" LEFT JOIN CON_MS_COMPANY         CMSC ON CMSC.COMPANY_ID = RCOMP.COMPANY_ID ");
//            sql.append(" CON_TB_REFUND_PERIOD   REPER ");
//            sql.append(" LEFT JOIN CON_TR_RECEIVE_COMPANY RCOMP ON REPER.RECEIVE_COMPANY_ID = RCOMP.RECEIVE_COMPANY_ID AND RCOMP.STATUS = 'A' ");
//            sql.append(" LEFT JOIN CON_TR_REFUND_COMPANY  RECOMP ON ( RECOMP.REFUND_REQUEST_ID = REPER.REFUND_REQUEST_ID ) ");
//            sql.append(" LEFT JOIN CON_TB_REFUND_REQUEST  REREQ ON ( REREQ.REFUND_REQUEST_ID = RECOMP.REFUND_REQUEST_ID ) ");
////            sql.append(" LEFT JOIN CON_TB_REFUND_RECEIPT  REREC ON ( REREC.REFUND_COMPANY_ID = RECOMP.REFUND_COMPANY_ID ) ");
//            sql.append(" LEFT JOIN CON_MS_COMPANY         CMSC ON CMSC.COMPANY_ID = RCOMP.COMPANY_ID ");
            sql.append(" WHERE ");
            sql.append(" 1 = 1 ");
            sql.append(" AND NVL( RCOMP.REFUND_REASON_CODE, REREQ.REQUEST_REASON_CODE ) IS NOT NULL ");
            sql.append(" AND ( REPER.STATUS != 'D' OR REPER.STATUS IS NULL ) ");

            if (!StringUtils.isEmpty(companyNo)){
                sql.append(" AND CMSC.COMPANY_NO = :companyNo ");
            }
            if (!StringUtils.isEmpty(companyBranchCode)) {
                sql.append(" AND CMSC.COMPANY_BRANCH_CODE = :companyBranchCode ");
            }
            param.put("companyNo", companyNo);
            param.put("companyBranchCode", companyBranchCode);

//            sql.append(" AND ( RECOMP.PROGRESS_STATUS = 'N' OR RECOMP.PROGRESS_STATUS IS NULL ) ");
            sql.append(" AND REREQ.MODE_REFUND = 'O' ");
            sql.append(" ) T0 ");
            sql.append(" WHERE ");
            sql.append(" 1 = 1 ");
            if (!StringUtils.isEmpty(receiveNo)) {
                sql.append(" AND T0.RECEIVE_NO = :receiveNo");
                param.put("receiveNo", receiveNo);
            }

            if (!StringUtils.isEmpty(progressStatus)) {
                switch (progressStatus) {
                    case "N" -> sql.append(" AND ( t0.progress_status = 'N' OR t0.progress_status IS NULL )");
//                    case "W" -> sql.append(" AND t0.progress_status = 'W' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//                            " WHERE ctrc.refund_request_id = t0.refund_request_id AND ctrc.order_status IS NULL ) ");
                    case "W" -> sql.append(" AND t0.progress_status = 'W' ");
                    case "0" -> sql.append(" AND t0.progress_status = '0' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = t0.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = t0.refund_request_id ) ) ");
//                            " OR NOT EXISTS ( SELECT ctbr33.refund_request_id FROM con_tb_requester33 ctbr33 WHERE ctbr33.refund_request_id = t0.refund_request_id ) ) ");
                    case "1" -> sql.append(" AND t0.progress_status = '1' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = t0.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = t0.refund_request_id ) ) ");
//                            " OR NOT EXISTS ( SELECT ctbr33.refund_request_id FROM con_tb_requester33 ctbr33 WHERE ctbr33.refund_request_id = t0.refund_request_id ) ) ");
                    case "S" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = t0.refund_request_id AND ctrc.order_status = 'W' ) ");
                    case "F" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = t0.refund_request_id AND ctrc.order_status = 'F' ) ");
                    case "P" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = t0.refund_request_id AND ctrc.order_status = 'P' ) ");
                    case "T" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = t0.refund_request_id AND ctrc.order_status = 'T' ) ");
                    case "C" -> sql.append(" AND t0.progress_status = 'C' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = t0.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' OR ctrc.order_status = 'F' ) ) " +
                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = t0.refund_request_id ) ) ");
//                            " OR NOT EXISTS ( SELECT ctbr33.refund_request_id FROM con_tb_requester33 ctbr33 WHERE ctbr33.refund_request_id = t0.refund_request_id ) ) ");
                }
            }
            if (StringUtils.isEmpty(receiveNo)) {
                sql.append(" AND t0.pay_period_year || t0.pay_period_month BETWEEN :startPeriod AND :endPeriod ");
//            sql.append(" ORDER BY TO_DATE(t0.pay_period, 'MM/YYYY') DESC");
                param.put("startPeriod", startPeriod);
                param.put("endPeriod", endPeriod);
            }

            if (!ObjectUtils.isObjectNull(paginable)) {
                paginable.setOrderBy("TO_DATE(t0.pay_period, 'MM/YYYY') DESC, T0.COMPANY_BRANCH_CODE" );
//                paginable.setOrderDirection(OrderDirection.DESC);
            } else {
                sql.append(" ORDER BY TO_DATE(t0.pay_period, 'MM/YYYY') DESC, T0.COMPANY_BRANCH_CODE ASC");
            }

            return queryForList(sql.toString(), param, paginable, new OverRefundCompanyRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    public List<OverRefundCompanyPeriodBean> queryRefundCompanyPeriod(Long companyId
            , String progressStatus
            , String depCode
            , String startPeriod
            , String endPeriod, Paginable paginable) throws Exception {
        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT refreq.refund_request_id, ");
            sql.append(" refper.refund_period,");
            sql.append(" refper.refund_period_id,");
            sql.append(" mscom.company_id,");
            sql.append(" mscom.company_no,");
            sql.append(" mscom.company_branch_code,");
            sql.append(" mscom.company_name,");
            sql.append(" refreq.receive_no,");
            sql.append(" refreq.receive_date,");
            sql.append(" refcom.refund_empr_amount,");
            sql.append(" refcom.request_total_amount,");
            sql.append(" refcom.refund_total_amount,");
            sql.append(" refreq.approve_no,");
            sql.append(" refreq.approve_date,");
            sql.append(" refper.approve_status,");
//            sql.append(" refrec.receipt_no,");
//            sql.append(" refrec.payment_date,");
            sql.append(" NULL AS receipt_no,");
            sql.append(" NULL AS payment_date,");
            sql.append(" refcom.progress_status,");
            sql.append(" reccom.over_paid,");
//            sql.append(" reqcom.order_status,");
//            sql.append(" reqcom.order_no,");
            sql.append(" NULL AS order_status,");
            sql.append(" NULL AS order_no,");
            sql.append(" refreq.mode_refund,");
            sql.append(" refreq.reverse_status,");
            sql.append(" refreq.reverse_remark,");
            sql.append(" refreq.announce_status,");
            sql.append(" refreq.notice_status");
            sql.append(" FROM con_tb_refund_request refreq");
            sql.append(" LEFT JOIN con_tb_refund_period refper ON (refreq.refund_request_id = refper.refund_request_id  AND ( refper.status != 'D' OR refper.status IS NULL) )");
            sql.append(" LEFT JOIN con_tr_refund_company refcom ON (refcom.refund_request_id = refreq.refund_request_id)");
//            sql.append(" LEFT JOIN con_tb_refund_receipt refrec ON (refrec.refund_company_id = refcom.refund_company_id)");
//            sql.append(" LEFT JOIN con_tb_requester_company reqcom ON (reqcom.refund_request_id = refreq.refund_request_id)");
            sql.append(" LEFT JOIN con_tr_receive_company reccom ON (reccom.receive_company_id = refper.receive_company_id)");
            sql.append(" LEFT JOIN con_ms_company mscom ON (mscom.company_id = refreq.company_id)");
//            sql.append(" LEFT JOIN con_ms_department msdep ON (msdep.dep_code = refreq.department_receive_code)");
            sql.append(" WHERE 1 = 1");
            sql.append(" AND refreq.section = '0' ");
            sql.append(" AND refreq.receive_no IS NOT NULL ");
            sql.append(" AND ( refper.status != 'D' OR refper.status IS NULL )");

            Map<String,Object> param = new HashMap<>();

            if (!NumberUtils.isZeroOrNull(companyId)){
                sql.append(" AND refreq.company_id = :companyId");
                param.put("companyId",companyId);
            }
//            if (!StringUtils.isEmpty(progressStatus)) {
//                if (progressStatus.equals("1") || progressStatus.equals("0")) {
//                    sql.append(" AND refper.approve_status = :progressStatus");
//                    sql.append(" AND refcom.progress_status = :progressStatus");
//                    param.put("progressStatus", progressStatus);
//                } else {
//                    if (progressStatus.equals("N")) {
//                        sql.append(" AND refcom.progress_status = ('N') ");
////                        sql.append(" AND (refcom.progress_status = ('N') OR refcom.progress_status IS NULL )");
//                    } else {
//                        sql.append(" AND refcom.progress_status = :progressStatus");
//                        param.put("progressStatus", progressStatus);
//                    }
//                }
////                if (progressStatus.equals("N") ) {
////                    sql.append(" AND (refcom.progress_status = ('N') OR refcom.progress_status IS NULL )");
////                }
////                if (!progressStatus.equals("N") && !progressStatus.equals("1") && !progressStatus.equals("0")) {
////                    sql.append(" AND refcom.progress_status = :progressStatus");
////                    param.put("progressStatus",progressStatus);
////                }
////                if (progressStatus.equals("1") || progressStatus.equals("0")) {
////                    sql.append(" AND refper.approve_status = :progressStatus");
////                    param.put("progressStatus",progressStatus);
////                }
//            }
            if (!StringUtils.isEmpty(progressStatus)) {
                switch (progressStatus) {
                    case "N" -> sql.append(" AND ( refcom.progress_status = 'N' )");
//                    case "W" -> sql.append(" AND refcom.progress_status = 'W' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//                            " WHERE ctrc.refund_request_id = refcom.refund_request_id AND ctrc.order_status IS NULL ) ");
                    case "W" -> sql.append(" AND refcom.progress_status = 'W' ");
                    case "0" -> sql.append(" AND refcom.progress_status = '0' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = refcom.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = refcom.refund_request_id ) ) ");
                    case "1" -> sql.append(" AND refcom.progress_status = '1' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = refcom.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = refcom.refund_request_id ) ) ");
                    case "S" -> sql.append(" AND refcom.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = refcom.refund_request_id AND ctrc.order_status = 'W' ) ");
                    case "F" -> sql.append(" AND refcom.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = refcom.refund_request_id AND ctrc.order_status = 'F' ) ");
                    case "P" -> sql.append(" AND refcom.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = refcom.refund_request_id AND ctrc.order_status = 'P' ) ");
                    case "T" -> sql.append(" AND refcom.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = refcom.refund_request_id AND ctrc.order_status = 'T' ) ");
                    case "C" -> sql.append(" AND refcom.progress_status = 'C' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
                            " WHERE ctrc.refund_request_id = refcom.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' OR ctrc.order_status = 'F' ) ) " +
                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = refcom.refund_request_id ) ) ");
                }
            }
//            if (!StringUtils.isEmpty(depCode)){
//                sql.append(" AND msdep.dep_code = :depCode");
//                param.put("depCode",depCode);
//            }
            sql.append(" AND reccom.pay_period_year || reccom.pay_period_month  BETWEEN :startPeriod AND :endPeriod");
            param.put("startPeriod",startPeriod);
            param.put("endPeriod",endPeriod);
//            sql.append(" ORDER BY TO_DATE(refper.refund_period, 'MM/YYYY') DESC");

            if (!ObjectUtils.isObjectNull(paginable)) {
                paginable.setPrimaryKey("refund_request_id");
                paginable.setOrderBy("TO_DATE(refper.refund_period, 'MM/YYYY')");
                paginable.setOrderDirection(OrderDirection.DESC);
            } else {
                sql.append(" ORDER BY TO_DATE(refper.refund_period, 'MM/YYYY') DESC");
            }

            return queryForList(sql.toString(), param, paginable, new OverRefundCompanyPeriodRowMapper());
        }catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    public List<OverRefundCompanyPeriodBean> queryRefundCompanyPeriodByReceive(String receiveNo) throws Exception{
        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT refreq.refund_request_id, ");
            sql.append(" refper.refund_period,");
            sql.append(" refper.refund_period_id,");
            sql.append(" mscom.company_id,");
            sql.append(" mscom.company_no,");
            sql.append(" mscom.company_branch_code,");
            sql.append(" mscom.company_name,");
            sql.append(" refreq.receive_no,");
            sql.append(" refreq.receive_date,");
            sql.append(" refcom.refund_empr_amount,");
            sql.append(" refcom.request_total_amount,");
            sql.append(" refcom.refund_total_amount,");
            sql.append(" refreq.approve_no,");
            sql.append(" refreq.approve_date,");
            sql.append(" refper.approve_status,");
            sql.append(" NULL AS receipt_no,");
            sql.append(" NULL AS payment_date,");
            sql.append(" refcom.progress_status,");
            sql.append(" 0 AS over_paid,");
            sql.append(" NULL AS order_status,");
            sql.append(" NULL AS order_no,");
            sql.append(" refreq.mode_refund,");
            sql.append(" refreq.reverse_status,");
            sql.append(" refreq.reverse_remark,");
            sql.append(" refreq.announce_status,");
            sql.append(" refreq.notice_status");
            sql.append(" FROM con_tb_refund_period refper");
            sql.append(" LEFT JOIN con_tb_refund_request refreq ON (refreq.refund_request_id = refper.refund_request_id   AND ( refper.status != 'D' OR refper.status IS NULL) )");
            sql.append(" LEFT JOIN con_tr_refund_company refcom ON (refcom.refund_request_id = refreq.refund_request_id)");
//            sql.append(" LEFT JOIN con_tr_refund_company refcom ON (refcom.refund_request_id = refreq.refund_request_id)");
//            sql.append(" LEFT JOIN con_tb_refund_receipt refrec ON (refrec.refund_company_id = refcom.refund_company_id)");
//            sql.append(" LEFT JOIN con_tb_requester_company reqcom ON (reqcom.refund_request_id = refreq.refund_request_id)");
            sql.append(" LEFT JOIN con_ms_company mscom ON (mscom.company_id = refreq.company_id)");
            sql.append(" LEFT JOIN con_ms_department msdep ON (msdep.dep_code = refreq.department_receive_code)");
//            sql.append(" LEFT JOIN con_ms_department msdep ON (msdep.department_id = refreq.department_receive_id)");
            sql.append(" WHERE 1 = 1");
            sql.append(" AND refreq.section = '0' ");

            Map<String,Object> param = new HashMap<>();

            if (!StringUtils.isEmpty((receiveNo))){
                sql.append(" AND refreq.receive_no = :receiveNo");
                param.put("receiveNo",receiveNo);
            }

            return queryForList(sql.toString(), param, new OverRefundCompanyPeriodRowMapper());
        }catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
