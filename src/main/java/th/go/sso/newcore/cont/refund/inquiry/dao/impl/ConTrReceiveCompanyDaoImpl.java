package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ReceiveCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundReceiveCompanyRowMapper;

@Repository
public class ConTrReceiveCompanyDaoImpl extends DaoBase<Long> {

    public ConTrReceiveCompanyDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<ReceiveCompanyBean> searchReceiveCompany(Long companyId, String companyNo, String receiveNo, String companyBranchCode, String startPeriod, String endPeriod, String progressStatus) throws Exception {
        try {
            Map<String, Object> param = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append(" T0.* ");
            sql.append(" FROM ");
            sql.append(" ( ");
            sql.append(" SELECT ");
            sql.append(" CTRRC.PAY_PERIOD, ");
            sql.append(" CTRRC.PAY_PERIOD_MONTH, ");
            sql.append(" CTRRC.PAY_PERIOD_YEAR, ");
            sql.append(" NULL AS RECEIVE_NO, ");
            sql.append(" CTRRC.COMPANY_ID, ");
            sql.append(" NULL                    AS REFUND_REQUEST_ID, ");
            sql.append(" CTRRC.RECEIVE_COMPANY_ID, ");
            sql.append(" CTRRC.PAY_DATE          AS PAYDATEOFCNTR, ");
            sql.append(" CTRRC.TOTAL_WAGES, ");
            sql.append(" CTRRC.CNTR_EMPE_RATE || ':' || CTRRC.CNTR_EMPE_RATE AS CNTR_RATE, ");
            sql.append(" CTRRC.NUM_OF_EMPLOYEE, ");
            sql.append(" NVL( CTRRC.CNTR_AMOUNT, 0 )                       AS CNTR_AMOUNT, ");
            sql.append(" NVL( CTRRC.EMPR_CNTR, 0 )                       AS EMPR_CNTR, ");
            sql.append(" NVL( CTRRC.EMPE_CNTR, 0 )                       AS EMPE_CNTR, ");
            sql.append(" NULL                    AS APPROVE_NO, ");
            sql.append(" NULL                    AS APPROVE_DATE, ");
            sql.append(" NULL                    AS RECEIPT_NO, ");
            sql.append(" NULL                    AS PAYMENT_DATE, ");
            sql.append(" NULL                    AS APPROVE_STATUS, ");
            sql.append(" NULL                    AS PROGRESS_STATUS, ");
            sql.append(" NULL                    AS NOTICE_STATUS, ");
            sql.append(" NULL                    AS ANNOUNCE_STATUS, ");
            sql.append(" NULL                    AS REVERSE_STATUS, ");
            sql.append(" NULL                    AS REVERSE_REMARK, ");
            sql.append(" NULL                    AS ORDER_NO, ");
            sql.append(" NULL                    AS ORDER_STATUS, ");
            sql.append(" CMSC.COMPANY_BRANCH_CODE, ");
            sql.append(" CMSC.COMPANY_BRANCH_NAME ");
            sql.append(" FROM ");
            sql.append(" CON_TR_RECEIVE_COMPANY CTRRC ");
            sql.append(" LEFT JOIN CON_MS_COMPANY         CMSC ON CMSC.COMPANY_ID = CTRRC.COMPANY_ID ");
            sql.append(" WHERE ");
            sql.append(" 1 = 1 ");
            sql.append(" AND CTRRC.REFUND_REASON_CODE IS NULL ");
//            sql.append(" AND CTRRC.PAY_STATUS = '0' ");
            sql.append(" AND ( CTRRC.OVER_PAID = 0 OR CTRRC.OVER_PAID IS NULL ) ");
            if (!StringUtils.isEmpty(companyNo)){
                sql.append(" AND CMSC.COMPANY_NO = :companyNo ");
            }
//            if (!StringUtils.isEmpty(companyBranchCode)) {
//                sql.append(" AND CMSC.COMPANY_BRANCH_CODE = :companyBranchCode ");
//            }
            sql.append(" AND NOT EXISTS ( ");
            sql.append(" SELECT * ");
            sql.append(" FROM CON_TB_REFUND_REQUEST NECTBRR ");
            sql.append(" JOIN CON_TB_REFUND_PERIOD NECTBRP ON NECTBRP.REFUND_REQUEST_ID = NECTBRR.REFUND_REQUEST_ID ");
            sql.append(" WHERE ");
            sql.append(" NECTBRP.RECEIVE_COMPANY_ID = CTRRC.RECEIVE_COMPANY_ID ");
            sql.append(" AND ( NECTBRR.STATUS IS NULL OR NECTBRR.STATUS NOT IN ( 'C', 'D' ) ) ");
            sql.append(" ) ");

            sql.append(" UNION  ALL ");
            sql.append(" SELECT ");
            sql.append(" RCOMP.PAY_PERIOD, ");
            sql.append(" RCOMP.PAY_PERIOD_MONTH, ");
            sql.append(" RCOMP.PAY_PERIOD_YEAR, ");
            sql.append(" REREQ.RECEIVE_NO, ");
            sql.append(" RCOMP.COMPANY_ID, ");
            sql.append(" REREQ.REFUND_REQUEST_ID, ");
            sql.append(" RCOMP.RECEIVE_COMPANY_ID, ");
            sql.append(" RCOMP.PAY_DATE            AS PAYDATEOFCNTR, ");
            sql.append(" RCOMP.TOTAL_WAGES, ");
            sql.append(" RCOMP.CNTR_EMPE_RATE || ':' || RCOMP.CNTR_EMPE_RATE   AS CNTR_RATE, ");
            sql.append(" RCOMP.NUM_OF_EMPLOYEE, ");
            sql.append(" NVL( RCOMP.CNTR_AMOUNT, 0 )                       AS CNTR_AMOUNT, ");
            sql.append(" NVL( RCOMP.EMPR_CNTR, 0 )                       AS EMPR_CNTR, ");
            sql.append(" NVL( RCOMP.EMPE_CNTR, 0 )                       AS EMPE_CNTR, ");
//            sql.append(" NVL( REPER.REFUND_TOTAL_AMOUNT, NVL(REPER.REQUEST_TOTAL_AMOUNT, 0 ))                       AS CNTR_AMOUNT, ");
//            sql.append(" NVL( REPER.REFUND_EMPR_AMOUNT, NVL(REPER.REQUEST_EMPR_AMOUNT, 0 ))                       AS EMPR_CNTR, ");
//            sql.append(" NVL( REPER.REFUND_EMPE_AMOUNT, NVL(REPER.REQUEST_EMPE_AMOUNT, 0 ))                       AS EMPE_CNTR, ");
            sql.append(" REREQ.APPROVE_NO, ");
            sql.append(" REREQ.APPROVE_DATE, ");
            sql.append(" REREC.RECEIPT_NO, ");
            sql.append(" REREC.PAYMENT_DATE, ");
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
            sql.append(" REREQ.REVERSE_STATUS, ");
            sql.append(" REREQ.REVERSE_REMARK, ");
            sql.append(" NULL                      AS ORDER_NO, ");
            sql.append(" NULL                      AS ORDER_STATUS, ");
            sql.append(" CMSC.COMPANY_BRANCH_CODE, ");
            sql.append(" CMSC.COMPANY_BRANCH_NAME ");
            sql.append(" FROM ");
            sql.append(" CON_TB_REFUND_PERIOD   REPER ");
            sql.append(" LEFT JOIN CON_TR_RECEIVE_COMPANY RCOMP ON REPER.RECEIVE_COMPANY_ID = RCOMP.RECEIVE_COMPANY_ID AND RCOMP.STATUS = 'A' ");
            sql.append(" LEFT JOIN CON_TR_REFUND_COMPANY  RECOMP ON ( RECOMP.REFUND_REQUEST_ID = REPER.REFUND_REQUEST_ID ) ");
            sql.append(" LEFT JOIN CON_TB_REFUND_REQUEST  REREQ ON ( REREQ.REFUND_REQUEST_ID = RECOMP.REFUND_REQUEST_ID ) ");
            sql.append(" LEFT JOIN CON_TB_REFUND_RECEIPT  REREC ON ( REREC.REFUND_COMPANY_ID = RECOMP.REFUND_COMPANY_ID ) ");
            sql.append(" LEFT JOIN CON_MS_COMPANY         CMSC ON CMSC.COMPANY_ID = RCOMP.COMPANY_ID ");
            sql.append(" WHERE ");
            sql.append(" 1 = 1 ");
            sql.append(" AND ( REPER.STATUS != 'D' OR REPER.STATUS IS NULL ) ");

            if (!StringUtils.isEmpty(companyNo)){
                sql.append(" AND CMSC.COMPANY_NO = :companyNo ");
            }
//            if (!StringUtils.isEmpty(companyBranchCode)) {
//                sql.append(" AND CMSC.COMPANY_BRANCH_CODE = :companyBranchCode ");
//            }
            param.put("companyNo", companyNo);
//            param.put("companyBranchCode", companyBranchCode);

            sql.append(" AND REREQ.MODE_REFUND = 'M' ");
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
            sql.append(" AND t0.pay_period_year || t0.pay_period_month BETWEEN :startPeriod AND :endPeriod ");
            sql.append(" ORDER BY TO_DATE(t0.pay_period, 'MM/YYYY') DESC");
            param.put("startPeriod", startPeriod);
            param.put("endPeriod", endPeriod);


//            sql.append("SELECT rcomp.pay_period,");
//            sql.append(" rereq.refund_request_id,");
//            sql.append(" rcomp.receive_company_id,");
//            sql.append(" rcomp.pay_date AS PayDateOfCntr,");
//            sql.append(" rcomp.cntr_rate,");
//            sql.append(" rcomp.num_of_employee,");
//            sql.append(" rcomp.total_wages,");
//            sql.append(" rcomp.empr_cntr,");
//            sql.append(" rcomp.empe_cntr,");
//            sql.append(" rcomp.cntr_amount,");
//            sql.append(" rereq.approve_no,");
//            sql.append(" rereq.approve_date,");
//            sql.append(" reper.approve_status,");
//            sql.append(" rerec.receipt_no,");
//            sql.append(" rereq.pay_date,");
//            sql.append(" (CASE WHEN recomp.progress_status = '1' OR recomp.progress_status = '0' THEN ");
//            sql.append(" (CASE WHEN reper.approve_status = '1' OR reper.approve_status = '0' THEN reper.approve_status ELSE recomp.progress_status END)");
//            sql.append(" ELSE recomp.progress_status END) AS progress_status,");
//            sql.append(" rereq.notice_status,");
//            sql.append(" rereq.announce_status,");
//            sql.append(" rereq.REVERSE_STATUS,");
//            sql.append(" rereq.reverse_remark,");
//            sql.append(" reqcom.order_no,reqcom.order_status,");
//            sql.append(" cmsc.company_branch_code, cmsc.company_branch_name");
//            sql.append(" FROM con_tr_receive_company rcomp");
//            sql.append(" LEFT JOIN con_tb_refund_period reper ON (reper.receive_company_id = rcomp.receive_company_id AND ((reper.STATUS != 'D' AND reper.STATUS != 'C') OR reper.STATUS IS NULL))");
//            sql.append(" LEFT JOIN con_tr_refund_company recomp ON (recomp.refund_request_id = reper.refund_request_id)");
//            sql.append(" LEFT JOIN con_tb_refund_request rereq ON (rereq.refund_request_id = recomp.refund_request_id)");
//            sql.append(" LEFT JOIN con_tb_refund_receipt rerec ON (rerec.refund_company_id = recomp.refund_company_id)");
//            sql.append(" LEFT JOIN con_tb_requester_company reqcom ON (reqcom.refund_request_id = rereq.refund_request_id)");
//            sql.append(" LEFT JOIN con_ms_company cmsc ON cmsc.company_id = rcomp.company_id");
//            sql.append(" RIGHT JOIN con_tr_receive_employee emp ON (emp.receive_company_id = rcomp.receive_company_id AND emp.status = 'A')");
//            sql.append(" WHERE (rcomp.over_paid = 0 OR rcomp.over_paid IS NULL)");
//            sql.append(" AND rcomp.refund_reason_code IS NULL ");
//
////            if (!NumberUtils.isZeroOrNull(companyId)){
////                sql.append(" AND rcomp.company_id = :companyId");
////                param.put("companyId", companyId);
////            }
//            if (!StringUtils.isEmpty(companyNo)){
//                sql.append(" AND cmsc.company_no = :companyNo");
//                param.put("companyNo", companyNo);
//            }
//            if (!StringUtils.isEmpty(receiveNo)){
//                sql.append(" AND rereq.receive_no = :receiveNo");
//                param.put("receiveNo", receiveNo);
//            }
//            if (!StringUtils.isEmpty(approveNo)){
//                sql.append(" AND rereq.approve_no = :approveNo");
//                param.put("approveNo", approveNo);
//            }
////            if (!StringUtils.isEmpty(progressStatus)) {
////                switch (progressStatus) {
////                    case "N" -> sql.append(" AND (recomp.progress_status = ('N') OR recomp.progress_status IS NULL )");
////                    case "W" -> sql.append(" AND recomp.progress_status = 'W' AND reqcom.order_status IS NULL ");
////                    case "0" -> sql.append(" AND recomp.progress_status = '0' AND reper.approve_status = '0' AND (reqcom.order_status LIKE '%C%' OR reqcom.order_status IS NULL) ");
////                    case "1" -> sql.append(" AND recomp.progress_status = '1' AND reper.approve_status = '1' AND (reqcom.order_status LIKE '%C%' OR reqcom.order_status IS NULL) ");
////                    case "S" -> sql.append(" AND recomp.progress_status = '1' AND reqcom.order_status LIKE '%W%' ");
////                    case "F" -> sql.append(" AND recomp.progress_status = '1' AND reqcom.order_status LIKE '%F%' ");
////                    case "P" -> sql.append(" AND recomp.progress_status = '1' AND reqcom.order_status LIKE '%P%' ");
////                    case "T" -> sql.append(" AND recomp.progress_status = '1' AND reqcom.order_status LIKE '%T%' ");
////                    case "C" -> sql.append(" AND recomp.progress_status = 'C' AND (reqcom.order_status LIKE '%C%' OR reqcom.order_status IS NULL) ");
////                }
////            }
////            if (!StringUtils.isEmpty(progressStatus)) {
////                if (progressStatus.equals("1") || progressStatus.equals("0")) {
////                    sql.append(" AND reper.approve_status = :progressStatus");
////                    sql.append(" AND recomp.progress_status = :progressStatus");
////                    param.put("progressStatus", progressStatus);
////                }else {
////                    if (progressStatus.equals("N")) {
////                        sql.append(" AND (recomp.progress_status = 'N' OR recomp.progress_status IS NULL)");
////                    }else {
////                        sql.append(" AND recomp.progress_status = :progressStatus");
////                        param.put("progressStatus", progressStatus);
////                    }
////                }
////            }
////            if (!StringUtils.isEmpty(progressStatus)) {
////                switch (progressStatus) {
////                    case "N" -> sql.append(" AND ( recomp.progress_status = 'N' OR recomp.progress_status IS NULL )");
//////                    case "W" -> sql.append(" AND recomp.progress_status = 'W' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//////                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status IS NULL ) ");
////                    case "W" -> sql.append(" AND recomp.progress_status = 'W' ");
////                    case "0" -> sql.append(" AND recomp.progress_status = '0' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
////                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' ) ) " +
////                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = recomp.refund_request_id ) ) ");
////                    case "1" -> sql.append(" AND recomp.progress_status = '1' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
////                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' ) ) " +
////                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = recomp.refund_request_id ) ) ");
////                    case "S" -> sql.append(" AND recomp.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
////                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status = 'W' ) ");
////                    case "F" -> sql.append(" AND recomp.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
////                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status = 'F' ) ");
////                    case "P" -> sql.append(" AND recomp.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
////                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status = 'P' ) ");
////                    case "T" -> sql.append(" AND recomp.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
////                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status = 'T' ) ");
////                    case "C" -> sql.append(" AND recomp.progress_status = 'C' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
////                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' OR ctrc.order_status = 'F' ) ) " +
////                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = recomp.refund_request_id ) ) ");
////                }
////            }
//            if (!StringUtils.isEmpty(progressStatus)) {
//                switch (progressStatus) {
//                    case "N" -> sql.append(" AND ( recomp.progress_status = 'N' OR recomp.progress_status IS NULL )");
////                    case "W" -> sql.append(" AND recomp.progress_status = 'W' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
////                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status IS NULL ) ");
//                    case "W" -> sql.append(" AND recomp.progress_status = 'W' ");
//                    case "0" -> sql.append(" AND recomp.progress_status = '0' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' ) ) " +
//                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = recomp.refund_request_id )" +
//                            " OR NOT EXISTS ( SELECT ctbr33.refund_request_id FROM con_tb_requester33 ctbr33 WHERE ctbr33.refund_request_id = recomp.refund_request_id ) ) ");
//                    case "1" -> sql.append(" AND recomp.progress_status = '1' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' ) ) " +
//                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = recomp.refund_request_id )" +
//                            " OR NOT EXISTS ( SELECT ctbr33.refund_request_id FROM con_tb_requester33 ctbr33 WHERE ctbr33.refund_request_id = recomp.refund_request_id ) ) ");
//                    case "S" -> sql.append(" AND recomp.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status = 'W' ) ");
//                    case "F" -> sql.append(" AND recomp.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status = 'F' ) ");
//                    case "P" -> sql.append(" AND recomp.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status = 'P' ) ");
//                    case "T" -> sql.append(" AND recomp.progress_status = '1' AND EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ctrc.order_status = 'T' ) ");
//                    case "C" -> sql.append(" AND recomp.progress_status = 'C' AND ( EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc" +
//                            " WHERE ctrc.refund_request_id = recomp.refund_request_id AND ( ctrc.order_status IS NULL OR ctrc.order_status = 'C' OR ctrc.order_status = 'F' ) ) " +
//                            " OR NOT EXISTS ( SELECT ctrc.refund_request_id FROM con_tb_requester_company ctrc WHERE ctrc.refund_request_id = recomp.refund_request_id )" +
//                            " OR NOT EXISTS ( SELECT ctbr33.refund_request_id FROM con_tb_requester33 ctbr33 WHERE ctbr33.refund_request_id = recomp.refund_request_id ) ) ");
//                }
//            }
//            sql.append(" AND rcomp.pay_period_year || rcomp.pay_period_month BETWEEN :startPeriod AND :endPeriod ");
//            param.put("startPeriod",startPeriod);
//            param.put("endPeriod",endPeriod);
//            sql.append("""
//                     GROUP BY
//                        rcomp.pay_period,
//                        rereq.refund_request_id,
//                        rcomp.receive_company_id,
//                        rcomp.pay_date,
//                        rcomp.cntr_rate,
//                        rcomp.num_of_employee,
//                        rcomp.total_wages,
//                        rcomp.empr_cntr,
//                        rcomp.empe_cntr,
//                        rcomp.cntr_amount,
//                        rereq.approve_no,
//                        rereq.approve_date,
//                        reper.approve_status,
//                        rerec.receipt_no,
//                        rereq.pay_date,
//                        recomp.progress_status,
//                        rereq.notice_status,
//                        rereq.announce_status,
//                        rereq.reverse_status,
//                        rereq.reverse_remark,
//                        reqcom.order_no,
//                        reqcom.order_status,
//                        cmsc.company_branch_code, cmsc.company_branch_name
//                    """);
//            sql.append(" ORDER BY TO_DATE(rcomp.pay_period, 'MM/YYYY') DESC");

            return queryForList(sql.toString(), param, new RefundReceiveCompanyRowMapper());
        }  catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    protected String getTableName() {
        return "con_tr_receive_company";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }
}
