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
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OverRefundInsurerRowMapper;

@Repository
public class OverRefundInsurerDaoImpl extends DaoBase<Long> {

    public OverRefundInsurerDaoImpl(DataSource dataSource) {
        super(dataSource);
    }
    public List<OverRefundInsurerBean> queryRefundInsurerPeriod(Long insurerId
            , String progressStatus
            , String startPeriod
            , String endPeriod
            , String receiveNo
            , Paginable paginable) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();
            Map<String, Object> param = new HashMap<>();
            sql.append("SELECT * FROM ( ");
            if (StringUtils.isEmpty(receiveNo)) {
                sql.append(" SELECT ");
                sql.append(" reins.receive_insurer_id AS receive_id,");
                sql.append(" NULL AS refund_request_id,");
                sql.append(" NULL AS receive_no,");
                sql.append(" reins.pay_period,");
                sql.append(" reins.pay_period_month,");
                sql.append(" reins.pay_period_year,");
                sql.append(" reins.refund_reason_code AS refund_reason_code,");
                sql.append(" nvl( reins.cntr_amount, 0 ) AS cntr_amount,");
                sql.append(" nvl( reins.interest_paid, 0 ) AS interest_paid,");
                sql.append(" nvl( reins.empe_overp, 0 ) AS empe_overp,");
                sql.append(" nvl( reins.refund_amount, 0 ) AS refund_amount,");/////
                sql.append(" nvl( reins.refund_interest_amount, 0 ) AS refund_interest_amount,");/////
                sql.append(" nvl( reins.empe_overp, 0 ) - nvl( reins.refund_amount, 0 ) AS total_over_paid,");/////
//                sql.append(" nvl( reins.over_interest_paid, 0 ) AS over_interest_paid,");
                sql.append(" nvl( reins.over_interest_paid, 0 )  AS over_interest_paid,");
                sql.append(" NULL AS approve_no,");
                sql.append(" NULL AS approve_date,");
                sql.append(" reins.pay_date AS payment_date,");
                sql.append(" NULL AS reverse_status,");
                sql.append(" NULL AS reverse_remark,");
                sql.append(" NULL AS receipt_no,");
                sql.append(" NULL AS progress_status,");
                sql.append(" NULL AS approve_status,");
                sql.append(" NULL AS notice_status,");
                sql.append(" NULL AS announce_status,");
                sql.append(" NULL AS count_order_no,");
                sql.append(" NULL AS order_no,");
                sql.append(" NULL AS order_status,");
                sql.append(" NULL AS refund_period_id,");
                sql.append(" 1 AS section");
                sql.append(" FROM");
                sql.append(" con_tr_receive_insurer reins");
//                sql.append(" JOIN con_tb_employment emp ON reins.insurer_id = emp.insurer_id ");
//                sql.append(" AND emp.section = '1' AND reins.pay_date BETWEEN emp.start_date AND coalesce( emp.resign_date, sysdate ) ");
                sql.append(" WHERE 1 = 1");
                sql.append(" AND reins.status = 'A'");
                sql.append(" AND reins.refund_reason_code IS NOT NULL ");
                sql.append(" AND ( CASE WHEN reins.refund_reason_code = '06' OR reins.refund_reason_code = '09' OR reins.refund_reason_code = '01' OR reins.refund_reason_code = '00' THEN nvl( reins.empe_overp, 0 ) ");
                sql.append(" ELSE nvl( reins.cntr_amount, 0 ) ");
                sql.append(" END > nvl( reins.refund_amount, 0 )");
                sql.append(" OR CASE WHEN reins.refund_reason_code = '06' OR reins.refund_reason_code = '09' OR reins.refund_reason_code = '01' OR reins.refund_reason_code = '00' THEN nvl( reins.over_interest_paid, 0 ) ");
                sql.append(" ELSE nvl( reins.interest_paid, 0 ) ");
                sql.append(" END > nvl( reins.refund_interest_amount, 0 ) )");
                if (!NumberUtils.isZeroOrNull(insurerId)) {
                    sql.append(" AND reins.insurer_id = :insurerId");
                    param.put("insurerId", insurerId);
                }
//                sql.append(" UNION ALL");
//
//                sql.append(" SELECT");
//                sql.append(" horec.hold_receive_id AS receive_id, ");
//                sql.append(" NULL                  AS refund_request_id, ");
//                sql.append(" horec.pay_period, ");
//                sql.append(" horec.pay_period_month, ");
//                sql.append(" horec.pay_period_year, ");
//                sql.append(" horec.refund_reason_code, ");
//                sql.append(" nvl( horec.cntr_amount, 0 ) AS cntr_amount, ");
//                sql.append(" nvl( horec.interest_amount, 0 ) AS interest_paid, ");
//                sql.append(" nvl( horec.cntr_amount, 0 ) AS empe_overp, ");
//                sql.append(" nvl( horec.interest_amount, 0 ) AS refund_interest_amount, ");
//                sql.append(" nvl( horec.cntr_amount, 0 ) AS refund_amount, ");
//                sql.append(" nvl( horec.cntr_amount, 0 ) AS total_over_paid, ");
//                sql.append(" nvl( horec.interest_amount, 0 ) AS over_interest_paid, ");
//                sql.append(" NULL AS approve_no, ");
//                sql.append(" NULL AS approve_date, ");
//                sql.append(" horec.receipt_date AS payment_date, ");
//                sql.append(" NULL AS reverse_status, ");
//                sql.append(" NULL AS reverse_remark, ");
//                sql.append(" NULL AS receipt_no, ");
//                sql.append(" NULL AS progress_status, ");
//                sql.append(" NULL AS approve_status, ");
//                sql.append(" NULL AS notice_status, ");
//                sql.append(" NULL AS announce_status, ");
//                sql.append(" NULL AS count_order_no, ");
//                sql.append(" NULL AS order_no, ");
//                sql.append(" NULL AS order_status, ");
//                sql.append(" NULL AS refund_period_id, ");
//                sql.append(" 7 AS section ");
//                sql.append(" FROM");
//                sql.append(" con_tb_hold_receive   horec");
//                sql.append(" JOIN con_ms_insurer        msins ON ( msins.id_card_no = horec.id_card_no )");
//                sql.append(" WHERE 1 = 1");
//                sql.append(" AND ( horec.cntr_amount > 0 OR horec.cntr_amount IS NOT NULL )");
//                sql.append(" AND horec.flag_status = 'X'");
//                sql.append(" AND horec.reason_code = '1'");
//                sql.append(" AND horec.refund_reason_code IS NOT NULL");
//                sql.append(" AND NOT EXISTS ( SELECT * FROM con_tb_refund_period WHERE hold_receive_id = horec.hold_receive_id AND status NOT IN ('C', 'D') )");
//                if (!NumberUtils.isZeroOrNull(insurerId)){
//                    sql.append(" AND msins.insurer_id = :insurerId");
//                    param.put("insurerId", insurerId);
//                }
//                if (!StringUtils.isEmpty(receiveNo)){
//                    sql.append("  AND rereq.receive_no = :receiveNo");
//                    param.put("receiveNo",receiveNo);
//                }

                sql.append(" UNION ALL");
            }
            sql.append(" SELECT ");
            sql.append(" * ");
            sql.append(" FROM ");
            sql.append(" ( ");
            sql.append(" SELECT ");
            sql.append(" reins.receive_insurer_id         AS receive_id, ");
            sql.append(" rereq.refund_request_id, ");
            sql.append(" rereq.receive_no, ");
            sql.append(" reper.refund_period              AS pay_period, ");
            sql.append(" reper.refund_period_month        AS pay_period_month, ");
            sql.append(" reper.refund_period_year         AS pay_period_year, ");
            sql.append(" rereq.request_reason_code        AS refund_reason_code, ");
            sql.append(" coalesce(reins.cntr_amount, 0)   AS cntr_amount, ");
            sql.append(" coalesce(reins.interest_paid, 0) AS interest_paid, ");
            sql.append(" coalesce(nullif(nvl(reper.request_empe_amount, 0), ");
            sql.append(" 0), ");
            sql.append(" nullif(nvl(reper.refund_empe_amount, 0), ");
            sql.append(" 0))              AS empe_overp, ");
            sql.append(" coalesce(nullif(nvl(reper.request_empe_amount, 0), ");
            sql.append(" 0), ");
            sql.append(" nullif(nvl(reper.refund_empe_amount, 0), ");
            sql.append(" 0))              AS refund_amount, ");
            sql.append(" coalesce(nullif(nvl(reper.request_interest_amount, 0), ");
            sql.append(" 0), ");
            sql.append(" nullif(nvl(reper.refund_interest_amount, 0), ");
            sql.append(" 0))              AS refund_interest_amount, ");
            sql.append(" coalesce(nullif(nvl(reper.request_empe_amount, 0), ");
            sql.append(" 0), ");
            sql.append(" nullif(nvl(reper.refund_empe_amount, 0), ");
            sql.append(" 0))              AS total_over_paid, ");
            sql.append(" coalesce(nullif(nvl(reper.request_interest_amount, 0), ");
            sql.append(" 0), ");
            sql.append(" nullif(nvl(reper.refund_interest_amount, 0), ");
            sql.append(" 0))              AS over_interest_paid, ");
            sql.append(" rereq.approve_no, ");
            sql.append(" rereq.approve_date, ");
            sql.append(" reins.pay_date                             AS payment_date, ");
            sql.append(" rereq.reverse_status, ");
            sql.append(" rereq.reverse_remark, ");
            sql.append(" NULL                             AS receipt_no, ");
            sql.append(" ( ");
            sql.append(" CASE ");
            sql.append(" WHEN refins.progress_status = '1' ");
            sql.append(" OR refins.progress_status = '0' THEN ");
            sql.append(" ( ");
            sql.append(" CASE ");
            sql.append(" WHEN reper.approve_status = '1' ");
            sql.append(" OR reper.approve_status = '0' THEN ");
            sql.append(" reper.approve_status ");
            sql.append(" ELSE ");
            sql.append(" refins.progress_status ");
            sql.append(" END ");
            sql.append(" ) ");
            sql.append(" ELSE ");
            sql.append(" refins.progress_status ");
            sql.append(" END ");
            sql.append(" )                                AS progress_status, ");
            sql.append(" reper.approve_status, ");
            sql.append(" rereq.notice_status, ");
            sql.append(" rereq.announce_status, ");
            sql.append(" NULL                             AS count_order_no, ");
            sql.append(" NULL                             AS order_no, ");
            sql.append(" NULL                             AS order_status, ");
            sql.append(" reper.refund_period_id, ");
            sql.append(" 1                                AS section ");
            sql.append(" FROM ");
            sql.append(" con_tb_refund_request  rereq");
            sql.append(" LEFT JOIN con_tb_refund_period   reper ON reper.refund_request_id = rereq.refund_request_id");
            sql.append(" LEFT JOIN    con_tr_receive_insurer reins ON reins.receive_insurer_id = reper.receive_insurer_id AND reins.status = 'A'");
            sql.append(" LEFT JOIN con_tr_refund_insurer  refins ON ( refins.refund_request_id = rereq.refund_request_id )");
            sql.append(" LEFT JOIN con_ms_insurer         cmsi ON cmsi.insurer_id = reins.insurer_id");
//            sql.append(" con_tr_receive_insurer reins ");
//            sql.append(" LEFT JOIN con_tb_refund_period   reper ON reins.receive_insurer_id = reper.receive_insurer_id ");
//            sql.append(" AND reins.status = 'A' ");
//            sql.append(" LEFT JOIN con_tr_refund_insurer  refins ON ( refins.refund_request_id = reper.refund_request_id ) ");
//            sql.append(" LEFT JOIN con_tb_refund_request  rereq ON ( rereq.refund_request_id = refins.refund_request_id ) ");
//            sql.append(" LEFT JOIN con_ms_insurer         cmsi ON cmsi.insurer_id = reins.insurer_id ");
            sql.append(" WHERE ");
            sql.append(" 1 = 1 ");
            if (!NumberUtils.isZeroOrNull(insurerId)){
                sql.append(" AND cmsi.insurer_id = :insurerId");
                param.put("insurerId", insurerId);
            }
            if (!StringUtils.isEmpty(receiveNo)){
                sql.append("  AND rereq.receive_no = :receiveNo");
                param.put("receiveNo",receiveNo);
            }
//            sql.append(" AND cmsi.insurer_id = :insurerId ");
            sql.append(" AND ( refins.progress_status IS NOT NULL ");
            sql.append(" OR ( refins.progress_status IS NULL ");
            sql.append(" AND rereq.announce_status = 'N' ) ) ");
            sql.append(" AND nvl(reins.refund_reason_code, rereq.request_reason_code) IS NOT NULL ");
            sql.append(" AND ( reper.status != 'D' ");
            sql.append(" OR reper.status IS NULL ) ");
//            sql.append(" UNION ALL ");
//            sql.append(" SELECT ");
//            sql.append(" ctbhr.hold_receive_id              AS receive_id, ");
//            sql.append(" rereq.refund_request_id, ");
//            sql.append(" reper.refund_period                AS pay_period, ");
//            sql.append(" reper.refund_period_month          AS pay_period_month, ");
//            sql.append(" reper.refund_period_year           AS pay_period_year, ");
//            sql.append(" rereq.request_reason_code          AS refund_reason_code, ");
//            sql.append(" coalesce(ctbhr.cntr_amount, 0)     AS cntr_amount, ");
//            sql.append(" coalesce(ctbhr.interest_amount, 0) AS interest_paid, ");
//            sql.append(" coalesce(nullif(nvl(reper.request_empe_amount, 0), ");
//            sql.append(" 0), ");
//            sql.append(" nullif(nvl(reper.refund_empe_amount, 0), ");
//            sql.append(" 0))                AS empe_overp, ");
//            sql.append(" coalesce(nullif(nvl(reper.request_empe_amount, 0), ");
//            sql.append(" 0), ");
//            sql.append(" nullif(nvl(reper.refund_empe_amount, 0), ");
//            sql.append(" 0))                AS refund_amount, ");
//            sql.append(" coalesce(nullif(nvl(reper.request_interest_amount, 0), ");
//            sql.append(" 0), ");
//            sql.append(" nullif(nvl(reper.refund_interest_amount, 0), ");
//            sql.append(" 0))                AS refund_interest_amount, ");
//            sql.append(" coalesce(nullif(nvl(reper.request_empe_amount, 0), ");
//            sql.append(" 0), ");
//            sql.append(" nullif(nvl(reper.refund_empe_amount, 0), ");
//            sql.append(" 0))                AS total_over_paid, ");
//            sql.append(" coalesce(nullif(nvl(reper.request_interest_amount, 0), ");
//            sql.append(" 0), ");
//            sql.append(" nullif(nvl(reper.refund_interest_amount, 0), ");
//            sql.append(" 0))                AS over_interest_paid, ");
//            sql.append(" rereq.approve_no, ");
//            sql.append(" rereq.approve_date, ");
//            sql.append(" ctbhr.receipt_date                               AS payment_date, ");
//            sql.append(" rereq.reverse_status, ");
//            sql.append(" rereq.reverse_remark, ");
//            sql.append(" NULL                               AS receipt_no, ");
//            sql.append(" ( ");
//            sql.append(" CASE ");
//            sql.append(" WHEN refins.progress_status = '1' ");
//            sql.append(" OR refins.progress_status = '0' THEN ");
//            sql.append(" ( ");
//            sql.append(" CASE ");
//            sql.append(" WHEN reper.approve_status = '1' ");
//            sql.append(" OR reper.approve_status = '0' THEN ");
//            sql.append(" reper.approve_status ");
//            sql.append(" ELSE ");
//            sql.append(" refins.progress_status ");
//            sql.append(" END ");
//            sql.append(" ) ");
//            sql.append(" ELSE ");
//            sql.append(" refins.progress_status ");
//            sql.append(" END ");
//            sql.append(" )                                  AS progress_status, ");
//            sql.append(" reper.approve_status, ");
//            sql.append(" rereq.notice_status, ");
//            sql.append(" rereq.announce_status, ");
//            sql.append(" NULL                               AS count_order_no, ");
//            sql.append(" NULL                               AS order_no, ");
//            sql.append(" NULL                               AS order_status, ");
//            sql.append(" reper.refund_period_id, ");
//            sql.append(" 1                                  AS section ");
//            sql.append(" FROM ");
//            sql.append(" con_tb_hold_receive   ctbhr ");
//            sql.append(" LEFT JOIN con_tb_refund_period  reper ON ctbhr.hold_receive_id = reper.hold_receive_id ");
//            sql.append(" LEFT JOIN con_tr_refund_insurer refins ON ( refins.refund_request_id = reper.refund_request_id ) ");
//            sql.append(" LEFT JOIN con_tb_refund_request rereq ON ( rereq.refund_request_id = refins.refund_request_id ) ");
//            sql.append(" LEFT JOIN con_ms_insurer        cmsi ON cmsi.sso_no = ctbhr.id_card_no ");
//            sql.append(" WHERE ");
//            sql.append(" 1 = 1 ");
//            if (!NumberUtils.isZeroOrNull(insurerId)){
//                sql.append(" AND cmsi.insurer_id = :insurerId");
//                param.put("insurerId", insurerId);
//            }
//            if (!StringUtils.isEmpty(receiveNo)){
//                sql.append("  AND rereq.receive_no = :receiveNo");
//                param.put("receiveNo",receiveNo);
//            }
////            sql.append(" AND cmsi.insurer_id = :insurerId ");
//            sql.append(" AND ( refins.progress_status IS NOT NULL ");
//            sql.append(" OR ( refins.progress_status IS NULL ");
//            sql.append(" AND rereq.announce_status = 'N' ) ) ");
//            sql.append(" AND nvl(ctbhr.refund_reason_code, rereq.request_reason_code) IS NOT NULL ");
//            sql.append(" AND ( reper.status != 'D' ");
//            sql.append(" OR reper.status IS NULL ) ");
            sql.append(" ) t00 ");
            sql.append(" ) t0 ");
//            sql.append(" SELECT");
//            sql.append(" coalesce(reins.receive_insurer_id, ctbhr.hold_receive_id) AS receive_id, ");
//            sql.append(" rereq.refund_request_id, ");
//            sql.append(" reper.refund_period AS pay_period, ");
//            sql.append(" reper.refund_period_month AS pay_period_month, ");
//            sql.append(" reper.refund_period_year AS pay_period_year, ");
////            sql.append(" coalesce(reins.refund_reason_code, ctbhr.refund_reason_code, rereq.request_reason_code, '00') AS refund_reason_code, ");
//            sql.append(" rereq.request_reason_code AS refund_reason_code, ");
//            sql.append(" coalesce(reins.cntr_amount, ctbhr.cntr_amount, 0) AS cntr_amount, ");
//            sql.append(" coalesce(reins.interest_paid, ctbhr.interest_amount, 0) AS interest_paid, ");
//            sql.append(" coalesce( nullif( nvl( reper.request_empe_amount, 0 ), 0 ), nullif( nvl( reper.refund_empe_amount, 0 ), 0 ) ) AS empe_overp, ");
//            sql.append(" coalesce( nullif( nvl( reper.request_empe_amount, 0 ), 0 ), nullif( nvl( reper.refund_empe_amount, 0 ), 0 ) ) AS refund_amount, ");
//            sql.append(" coalesce( nullif( nvl( reper.request_interest_amount, 0 ), 0 ), nullif( nvl( reper.refund_interest_amount, 0 ), 0 ) ) AS refund_interest_amount, ");
//            sql.append(" coalesce( nullif( nvl( reper.request_empe_amount, 0 ), 0 ), nullif( nvl( reper.refund_empe_amount, 0 ), 0 ) ) AS total_over_paid, ");
//            sql.append(" coalesce( nullif( nvl( reper.request_interest_amount, 0 ), 0 ), nullif( nvl( reper.refund_interest_amount, 0 ), 0 ) ) AS over_interest_paid, ");
//            sql.append(" rereq.approve_no, ");
//            sql.append(" rereq.approve_date, ");
//            sql.append(" NULL                     AS payment_date, ");
//            sql.append(" rereq.reverse_status, ");
//            sql.append(" rereq.reverse_remark, ");
//            sql.append(" NULL                     AS receipt_no, ");
//            sql.append(" ( CASE WHEN refins.progress_status = '1' OR refins.progress_status = '0' THEN");
//            sql.append("  ( CASE WHEN reper.approve_status = '1' OR reper.approve_status = '0' THEN");
//            sql.append("   reper.approve_status ELSE refins.progress_status END ) ELSE refins.progress_status END ) AS progress_status,");
//            sql.append(" reper.approve_status, ");
//            sql.append(" rereq.notice_status, ");
//            sql.append(" rereq.announce_status, ");
//            sql.append(" NULL                     AS count_order_no, ");
//            sql.append(" NULL                     AS order_no, ");
//            sql.append(" NULL                     AS order_status, ");
//            sql.append(" reper.refund_period_id, ");
//            sql.append(" 1                        AS section ");
//            sql.append(" FROM ");
//            sql.append(" con_tb_refund_period   reper ");
//            sql.append(" LEFT JOIN con_tr_receive_insurer reins ON reins.receive_insurer_id = reper.receive_insurer_id AND reins.status = 'A' ");
//            sql.append(" LEFT JOIN con_tb_hold_receive ctbhr ON ctbhr.hold_receive_id = reper.hold_receive_id ");
//            sql.append(" LEFT JOIN con_tr_refund_insurer  refins ON ( refins.refund_request_id = reper.refund_request_id ) ");
//            sql.append(" LEFT JOIN con_tb_refund_request  rereq ON ( rereq.refund_request_id = refins.refund_request_id ) ");
//            sql.append(" LEFT JOIN con_ms_insurer cmsi ON cmsi.insurer_id = reins.insurer_id OR cmsi.id_card_no = ctbhr.id_card_no ");
//            sql.append(" WHERE ");
//            sql.append(" 1 = 1 ");
//            sql.append(" AND ( refins.progress_status IS NOT NULL OR ( refins.progress_status IS NULL AND rereq.announce_status = 'N' ) ) ");
//            sql.append(" AND nvl( reins.refund_reason_code, nvl(ctbhr.refund_reason_code, rereq.request_reason_code )) IS NOT NULL ");
//            sql.append(" AND ( reper.status != 'D' OR reper.status IS NULL ) ");
////            sql.append(" AND reins.status = 'A' ");
//            if (!NumberUtils.isZeroOrNull(insurerId)){
//                sql.append(" AND cmsi.insurer_id = :insurerId");
//                param.put("insurerId", insurerId);
//            }
//            if (!StringUtils.isEmpty(receiveNo)){
//                sql.append("  AND rereq.receive_no = :receiveNo");
//                param.put("receiveNo",receiveNo);
//            }
//            sql.append(" ) t0");
            sql.append(" WHERE 1 = 1");
            sql.append(" AND t0.refund_reason_code IS NOT NULL ");
            if (!StringUtils.isEmpty(progressStatus)) {
                switch (progressStatus) {
                    case "N" -> sql.append(" AND ( t0.progress_status = 'N' OR t0.progress_status IS NULL )");
//                    case "W" -> sql.append(" AND t0.progress_status = 'W' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
//                            " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status IS NULL ) ");
                    case "W" -> sql.append(" AND t0.progress_status = 'W' ");
                    case "0" -> sql.append(" AND t0.progress_status = '0' AND ( EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = t0.refund_request_id AND ( ctr39.order_status IS NULL OR ctr39.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39 WHERE ctr39.refund_request_id = t0.refund_request_id ) ) ");
                    case "1" -> sql.append(" AND t0.progress_status = '1' AND ( EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = t0.refund_request_id AND ( ctr39.order_status IS NULL OR ctr39.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39 WHERE ctr39.refund_request_id = t0.refund_request_id ) ) ");
                    case "S" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status = 'W' ) ");
                    case "F" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status = 'F' ) ");
                    case "P" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status = 'P' ) ");
                    case "T" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status = 'T' ) ");
                    case "C" -> sql.append(" AND t0.progress_status = 'C' AND ( EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                            " WHERE ctr39.refund_request_id = t0.refund_request_id AND ( ctr39.order_status IS NULL OR ctr39.order_status = 'C' OR ctr39.order_status = 'F' ) ) " +
                            " OR NOT EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39 WHERE ctr39.refund_request_id = t0.refund_request_id ) ) ");
                }
            }
            if (StringUtils.isEmpty(receiveNo)) {
                if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
                    sql.append(" AND pay_period_year || pay_period_month BETWEEN :startPeriod AND :endPeriod");
                    param.put("startPeriod", startPeriod);
                    param.put("endPeriod", endPeriod);
                }
            }
//            sql.append(" ORDER BY to_date(pay_period, 'MM/YYYY') DESC");

            if (!ObjectUtils.isObjectNull(paginable)) {
                paginable.setPrimaryKey("RECEIVE_ID");
                paginable.setOrderBy("to_date(pay_period, 'MM/YYYY')");
                paginable.setOrderDirection(OrderDirection.DESC);
            } else {
                sql.append(" ORDER BY to_date(pay_period, 'MM/YYYY') DESC");
            }

//            return queryForList(sql.toString(), param, paginable, new OverRefundInsurerRowMapper());
            return queryForList(sql.toString(), param, new OverRefundInsurerRowMapper());
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
