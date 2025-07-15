package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ContributionInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ContributionInsurerRowMapper;

@Repository
public class ContributionInsurerDaoImpl extends DaoBase<Long> {

    public ContributionInsurerDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<ContributionInsurerBean> searchConInsurer(Long insurerId, String progressStatus, String startPeriod, String endPeriod, String receiveNo) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();
            final Map<String, Object> param = new HashMap<>();
            sql.append(" SELECT ");
            sql.append(" reins.receive_insurer_id, ");
            sql.append(" rereq.refund_request_id, ");
            sql.append(" reins.pay_period, ");
            sql.append(" nvl( reins.cntr_amount, 0 ) AS cntr_amount, ");
            sql.append(" nvl( reins.interest_paid, 0 ) AS interest_paid, ");
            sql.append(" nvl( reins.empe_overp, 0 ) AS empe_overp, ");
            sql.append(" nvl( reins.over_interest_paid, 0 ) AS over_interest_paid, ");
            sql.append(" rereq.approve_no, ");
            sql.append(" rereq.approve_date, ");
            sql.append(" rereq.pay_date, ");
            sql.append(" rereq.reverse_status, ");
            sql.append(" rereq.reverse_remark, ");
            sql.append(" ( CASE WHEN refins.progress_status = '1' OR refins.progress_status = '0' THEN ");
            sql.append(" ( CASE WHEN reper.approve_status = '1' OR reper.approve_status = '0' THEN ");
            sql.append(" reper.approve_status ELSE refins.progress_status END ) ");
            sql.append(" ELSE refins.progress_status END ) AS progress_status, ");
            sql.append(" rereq.notice_status, ");
            sql.append(" rereq.announce_status, ");
            sql.append(" 1 AS section ");
            sql.append(" FROM ");
            sql.append(" con_tr_receive_insurer reins ");
            sql.append(" LEFT JOIN con_tb_refund_period   reper ON ( reper.receive_insurer_id = reins.receive_insurer_id ");
            sql.append(" AND ( reper.status != 'D' OR reper.status IS NULL ) ) ");
            sql.append(" LEFT JOIN con_tr_refund_insurer  refins ON ( refins.refund_request_id = reper.refund_request_id ) ");
            sql.append(" LEFT JOIN con_tb_refund_request  rereq ON ( rereq.refund_request_id = refins.refund_request_id ) ");
            sql.append(" WHERE 1 = 1 ");
            sql.append(" AND reins.status = 'A' ");
            sql.append(" AND reins.refund_reason_code IS NULL ");
            if (!NumberUtils.isZeroOrNull(insurerId)) {
                sql.append(" AND reins.insurer_id = :insurerId");
                param.put("insurerId",insurerId);

                sql.append(" AND reins.pay_period_year || reins.pay_period_month BETWEEN :startPeriod AND :endPeriod");
                param.put("startPeriod", startPeriod);
                param.put("endPeriod", endPeriod);
            }
            if (!StringUtils.isEmpty(receiveNo)){
                sql.append("  AND rereq.receive_no = :receiveNo");
                param.put("receiveNo",receiveNo);
            }
            if (!StringUtils.isEmpty(progressStatus)) {
                switch (progressStatus) {
                    case "N" -> sql.append(" AND ( refins.progress_status = 'N' OR refins.progress_status IS NULL )");
//                    case "W" -> sql.append(" AND refins.progress_status = 'W' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
//                            " WHERE ctr39.refund_request_id = refins.refund_request_id AND ctr39.order_status IS NULL ) ");
                    case "W" -> sql.append(" AND refins.progress_status = 'W' ");
                    case "0" -> sql.append(" AND refins.progress_status = '0' AND ( EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = refins.refund_request_id AND ( ctr39.order_status IS NULL OR ctr39.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39 WHERE ctr39.refund_request_id = refins.refund_request_id ) ) ");
                    case "1" -> sql.append(" AND refins.progress_status = '1' AND ( EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = refins.refund_request_id AND ( ctr39.order_status IS NULL OR ctr39.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39 WHERE ctr39.refund_request_id = refins.refund_request_id ) ) ");
                    case "S" -> sql.append(" AND refins.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = refins.refund_request_id AND ctr39.order_status = 'W' ) ");
                    case "F" -> sql.append(" AND refins.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = refins.refund_request_id AND ctr39.order_status = 'F' ) ");
                    case "P" -> sql.append(" AND refins.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = refins.refund_request_id AND ctr39.order_status = 'P' ) ");
                    case "T" -> sql.append(" AND refins.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = refins.refund_request_id AND ctr39.order_status = 'T' ) ");
                    case "C" -> sql.append(" AND refins.progress_status = 'C' AND ( EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = refins.refund_request_id AND ( ctr39.order_status IS NULL OR ctr39.order_status = 'C' OR ctr39.order_status = 'F' ) ) " +
                            " OR NOT EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39 WHERE ctr39.refund_request_id = refins.refund_request_id ) ) ");
                }
            }
            sql.append(" ORDER BY TO_DATE(reins.pay_period, 'MM/YYYY') DESC ");


//            sql.append("SELECT reins.receive_insurer_id,");
//            sql.append(" rereq.refund_request_id,");
//            sql.append(" reins.pay_period,");
//            sql.append(" nvl(reins.cntr_amount,0) As cntr_amount,");
//            sql.append(" nvl(reins.interest_paid,0) As interest_paid,");
//            sql.append(" nvl(reins.empe_overp,0) As empe_overp,");
//            sql.append(" nvl(reins.over_interest_paid,0) As over_interest_paid,");
//            sql.append(" rereq.approve_no,");
//            sql.append(" rereq.approve_date,");
////            sql.append(" rerec.receipt_no,");
//            sql.append(" rereq.pay_date,");
//            sql.append(" rereq.reverse_status,");
//            sql.append(" rereq.reverse_remark,");
////            sql.append(" refins.progress_status,");
////            sql.append(" CASE WHEN reper.approve_status = '1' OR reper.approve_status = '0' THEN reper.approve_status ELSE refins.progress_status END AS progress_status,");
//            sql.append(" (CASE WHEN refins.progress_status = '1' OR refins.progress_status = '0' THEN ");
//            sql.append(" (CASE WHEN reper.approve_status = '1' OR reper.approve_status = '0' THEN reper.approve_status ELSE refins.progress_status END)");
//            sql.append(" ELSE refins.progress_status END) AS progress_status,");
//            sql.append(" rereq.notice_status,");
//            sql.append(" rereq.announce_status,");
//            sql.append(" LISTAGG(DISTINCT rerec.receipt_no, ', ') WITHIN GROUP( ORDER BY rerec.receipt_no ) AS receipt_no,");
//            sql.append(" LISTAGG(req39.order_no, ',') WITHIN GROUP( ORDER BY req39.order_no ) AS order_no,");
//            sql.append(" LISTAGG(req39.order_status, ',') WITHIN GROUP( ORDER BY req39.order_status ) AS order_status,");
//            sql.append(" 1 as section");
//            sql.append(" FROM con_tr_receive_insurer reins");
//            sql.append(" LEFT JOIN con_tb_refund_period   reper ON (reper.receive_insurer_id = reins.receive_insurer_id AND ((reper.STATUS != 'D' AND reper.STATUS != 'C') OR reper.STATUS IS NULL))");
//            sql.append(" LEFT JOIN con_tr_refund_insurer  refins ON (refins.refund_request_id = reper.refund_request_id)");
//            sql.append(" LEFT JOIN con_tb_refund_request  rereq ON (rereq.refund_request_id = refins.refund_request_id)");
//            sql.append(" LEFT JOIN con_tb_refund_receipt  rerec ON (rerec.refund_insurer_id = refins.refund_insurer_id)");
//            sql.append(" LEFT JOIN con_tb_requester39     req39 ON ( req39.refund_request_id = rereq.refund_request_id )");
//            sql.append(" WHERE (reins.empe_overp = 0 OR reins.empe_overp IS NULL)");
//
//            if (!NumberUtils.isZeroOrNull(insurerId)) {
//                sql.append(" AND reins.insurer_id = :insurerId");
//                param.put("insurerId",insurerId);
//
//                sql.append(" AND reins.pay_period_year || reins.pay_period_month BETWEEN :startPeriod AND :endPeriod");
//                param.put("startPeriod", startPeriod);
//                param.put("endPeriod", endPeriod);
//            }
//            if (!StringUtils.isEmpty(receiveNo)){
//                sql.append("  AND rereq.receive_no = :receiveNo");
//                param.put("receiveNo",receiveNo);
//            }
////            if (!StringUtils.isEmpty(progressStatus)) {
////
////                if (progressStatus.equals("N")) {
////                    sql.append(" AND (refins.progress_status = ('N') OR refins.progress_status IS NULL )");
////                }
////                if (!progressStatus.equals("N") && !progressStatus.equals("1") && !progressStatus.equals("0")) {
////
////                    sql.append(" AND refins.progress_status = :progressStatus");
////                    param.put("progressStatus", progressStatus);
////                }
////                if (progressStatus.equals("1") || progressStatus.equals("0")) {
////
////                    sql.append(" AND reper.approve_status = :progressStatus");
////                    param.put("progressStatus", progressStatus);
////                }
////                if (progressStatus.equals("P")){
////                    sql.append(" AND refins.progress_status = :progressStatus");
////                    param.put("progressStatus", progressStatus);
////                }
////            }
////            if (!StringUtils.isEmpty(progressStatus)) {
////                if (progressStatus.equals("1") || progressStatus.equals("0")) {
////                    sql.append(" AND reper.approve_status = :progressStatus");
////                    sql.append(" AND refins.progress_status = :progressStatus");
////                    param.put("progressStatus", progressStatus);
////                }else {
////                    if (progressStatus.equals("N")) {
////                        sql.append(" AND (refins.progress_status = ('N') OR refins.progress_status IS NULL )");
////                    }else {
////                        sql.append(" AND refins.progress_status = :progressStatus");
////                        param.put("progressStatus", progressStatus);
////                    }
////                }
////            }
//            if (!StringUtils.isEmpty(progressStatus)) {
//                switch (progressStatus) {
//                    case "N" -> sql.append(" AND (refins.progress_status = ('N') OR refins.progress_status IS NULL )");
//                    case "W" -> sql.append(" AND refins.progress_status = 'W' AND req39.order_status IS NULL ");
//                    case "0" -> sql.append(" AND refins.progress_status = '0' AND reper.approve_status = '0' AND (req39.order_status LIKE '%C%' OR req39.order_status IS NULL) ");
//                    case "1" -> sql.append(" AND refins.progress_status = '1' AND reper.approve_status = '1' AND (req39.order_status LIKE '%C%' OR req39.order_status IS NULL) ");
//                    case "S" -> sql.append(" AND refins.progress_status = '1' AND req39.order_status LIKE '%W%' ");
//                    case "F" -> sql.append(" AND refins.progress_status = '1' AND req39.order_status LIKE '%F%' ");
//                    case "P" -> sql.append(" AND refins.progress_status = '1' AND req39.order_status LIKE '%P%' ");
//                    case "T" -> sql.append(" AND refins.progress_status = '1' AND req39.order_status LIKE '%T%' ");
//                    case "C" -> sql.append(" AND refins.progress_status = 'C' AND (req39.order_status LIKE '%C%' OR req39.order_status IS NULL) ");
//                }
//            }
//
//            sql.append(" GROUP BY reins.receive_insurer_id, rereq.refund_request_id, reins.pay_period, reins.cntr_amount, reins.interest_paid, reins.empe_overp,");
//            sql.append(" reins.over_interest_paid, rereq.approve_no, rereq.approve_date, rerec.receipt_no, rereq.pay_date, rereq.reverse_status,");
//            sql.append(" rereq.reverse_remark, refins.progress_status, reper.approve_status, rereq.notice_status, rereq.announce_status, 1");
//            sql.append(" ORDER BY to_date(reins.pay_period, 'MM/YYYY') DESC");

            return queryForList(sql.toString(), param, new ContributionInsurerRowMapper());

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "CON_TR_RECEIVE_INSURER";
    }

    @Override
    protected String getPkColumnName() {
        return "RECEIVE_INSURER_ID";
    }
}
