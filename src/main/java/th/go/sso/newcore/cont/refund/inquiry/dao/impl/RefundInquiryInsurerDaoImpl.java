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
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInquiryInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundInquiryInsurerRowMapper;

@Repository
public class RefundInquiryInsurerDaoImpl extends DaoBase<Long> {

    public RefundInquiryInsurerDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<RefundInquiryInsurerBean> searchRefundInquiryInsurer(String idCardNo
            , String section
            , String depCode
            , String progressStatus
            , String startPeriod
            , String endPeriod
            , Paginable paginable) throws Exception {

        try {final StringBuilder sql = new StringBuilder();
            final Map<String, Object> param = new HashMap<>();
            if (!StringUtils.isEmpty(section) && "1".equals(section)) {
                sql.append("SELECT cmsi.insurer_id,");
                sql.append(" rereq.refund_request_id,");
                sql.append(" reper.refund_period,");
                sql.append(" reper.refund_period_id,");
                sql.append(" cmsi.id_card_no,");
                sql.append(" cmsi.full_name,");
                sql.append(" rereq.section,");
                sql.append(" rereq.receive_no,");
                sql.append(" rereq.receive_date,");
                sql.append(" rereq.approve_no,");
                sql.append(" rereq.approve_date,");
//                sql.append(" rerec.receipt_no,");
//                sql.append(" rerec.payment_date,");
//                sql.append(" cmsd.dep_code,");
                sql.append(" NULL AS receipt_no,");
                sql.append(" NULL AS payment_date,");
                sql.append(" rereq.department_receive_code AS dep_code,");
//                sql.append(" rereq.mode_refund,");
                sql.append(" refins.refund_empe_amount,");
                sql.append(" refins.request_total_amount,");
                sql.append(" refins.refund_total_amount,");
                sql.append(" ( CASE WHEN refins.progress_status = '1' OR refins.progress_status = '0' THEN");
                sql.append(" ( CASE WHEN reper.approve_status = '1' OR reper.approve_status = '0' THEN");
                sql.append(" reper.approve_status ELSE refins.progress_status END ) ELSE refins.progress_status END ) AS progress_status,");
                sql.append(" reins.empe_overp,");
                sql.append(" rereq.mode_refund,");
                sql.append(" rereq.ref_refund_request_id,");
                sql.append(" rereq.company_id,");
                sql.append(" rereq.reverse_status,");
                sql.append(" rereq.reverse_remark,");
                sql.append(" rereq.announce_status,");
                sql.append(" rereq.notice_status");
                sql.append(" FROM con_tr_receive_insurer reins");
                sql.append(" LEFT JOIN con_tb_refund_period   reper ON ( reper.receive_insurer_id = reins.receive_insurer_id AND ( reper.status != 'D' OR reper.status IS NULL) )");
                sql.append(" LEFT JOIN con_tr_refund_insurer  refins ON ( refins.refund_request_id = reper.refund_request_id )");
                sql.append(" LEFT JOIN con_tb_refund_request  rereq ON ( rereq.refund_request_id = refins.refund_request_id )");
//                sql.append(" LEFT JOIN con_tb_refund_receipt  rerec ON ( rerec.refund_insurer_id = refins.refund_insurer_id )");
//                sql.append(" LEFT JOIN con_tb_requester39     req39 ON ( req39.refund_request_id = rereq.refund_request_id )");
                sql.append(" LEFT JOIN con_ms_insurer         cmsi ON ( cmsi.insurer_id = rereq.insurer_id )");
//                sql.append(" LEFT JOIN con_ms_department      cmsd ON ( cmsd.dep_code = rereq.department_receive_code )");
                sql.append(" WHERE 1 = 1");
                sql.append(" AND reins.status = 'A'");
                sql.append(" AND ( reper.status != 'D' OR reper.status IS NULL )");
//                sql.append(" AND nvl( reins.refund_reason_code, rereq.request_reason_code ) IS NOT NULL");
                sql.append(" AND rereq.receive_no IS NOT NULL");
                if (!StringUtils.isEmpty(idCardNo)){
                    sql.append(" AND nvl(cmsi.sso_no, cmsi.id_card_no) = :idCardNo");
                    param.put("idCardNo", idCardNo);
                }
                if (!StringUtils.isEmpty(section)){
                    sql.append(" AND cmsi.section = :section");
                    param.put("section", section);
                }
                if (!StringUtils.isEmpty(depCode)){
                    sql.append(" AND cmsd.dep_code = :depCode");
                    param.put("depCode", depCode);
                }
                if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
                    sql.append(" AND reper.refund_period_year || reper.refund_period_month BETWEEN :startPeriod AND :endPeriod ");
                    param.put("startPeriod", startPeriod);
                    param.put("endPeriod", endPeriod);
                }
                if (!StringUtils.isEmpty(progressStatus)) {
                    switch (progressStatus) {
                        case "N" -> sql.append(" AND ( refins.progress_status = 'N' )");
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
//                sql.append(" GROUP BY");
//                sql.append(" cmsi.insurer_id, rereq.refund_request_id, reper.refund_period, reper.refund_period_id, cmsi.id_card_no, cmsi.full_name, rereq.section,");
//                sql.append(" rereq.receive_no, rereq.receive_date, rereq.approve_no, rereq.approve_date, rerec.receipt_no, rerec.payment_date,");
//                sql.append(" cmsd.dep_code, rereq.mode_refund, refins.refund_empe_amount, refins.request_total_amount, refins.refund_total_amount,");
//                sql.append(" refins.progress_status, reper.approve_status, reins.empe_overp, reins.pay_period,rereq.mode_refund, rereq.ref_refund_request_id,");
//                sql.append(" rereq.company_id, rereq.reverse_status, rereq.reverse_remark");
//                sql.append(" ORDER BY TO_DATE(reper.refund_period, 'MM/YYYY') DESC");
            } else if (!StringUtils.isEmpty(section) && "0".equals(section)) {
                sql.append("SELECT cmsi.insurer_id,");
                sql.append(" rereq.refund_request_id,");
                sql.append(" reper.refund_period,");
                sql.append(" reper.refund_period_id,");
                sql.append(" cmsi.id_card_no,");
                sql.append(" cmsi.full_name,");
                sql.append(" rereq.section,");
                sql.append(" rereq.receive_no,");
                sql.append(" rereq.receive_date,");
                sql.append(" rereq.approve_no,");
                sql.append(" rereq.approve_date,");
//                sql.append(" rerec.receipt_no,");
//                sql.append(" rerec.payment_date,");
//                sql.append(" cmsd.dep_code,");
                sql.append(" NULL AS receipt_no,");
                sql.append(" NULL AS payment_date,");
                sql.append(" rereq.department_receive_code AS dep_code,");
//                sql.append(" rereq.mode_refund,");
                sql.append(" refemp.refund_empe_amount,");
                sql.append(" refemp.request_total_amount,");
                sql.append(" refemp.refund_total_amount,");
                sql.append(" ( CASE WHEN refemp.progress_status = '1' OR refemp.progress_status = '0' THEN");
                sql.append(" ( CASE WHEN reper.approve_status = '1' OR reper.approve_status = '0' THEN");
                sql.append(" reper.approve_status ELSE refemp.progress_status END ) ELSE refemp.progress_status END ) AS progress_status,");
                sql.append(" NULL AS empe_overp,");
                sql.append(" rereq.mode_refund,");
                sql.append(" rereq.ref_refund_request_id,");
                sql.append(" rereq.company_id,");
                sql.append(" rereq.reverse_status,");
                sql.append(" rereq.reverse_remark,");
                sql.append(" rereq.announce_status,");
                sql.append(" rereq.notice_status");
//                sql.append(" reemp.empe_overp");
                sql.append(" FROM con_tb_refund_period   reper");
//                sql.append(" FROM con_tr_receive_employee reemp");
//                sql.append(" LEFT JOIN con_tb_refund_period   reper ON ( reper.receive_employee_id = reemp.receive_employee_id )");
                sql.append(" LEFT JOIN con_tr_refund_employee  refemp ON ( refemp.refund_request_id = reper.refund_request_id )");
                sql.append(" LEFT JOIN con_tb_refund_request  rereq ON ( rereq.refund_request_id = refemp.refund_request_id )");
//                sql.append(" LEFT JOIN con_tb_refund_receipt  rerec ON ( rerec.refund_employee_id = refemp.refund_employee_id )");
//                sql.append(" LEFT JOIN con_tb_requester33     req33 ON ( req33.refund_request_id = rereq.refund_request_id )");
                sql.append(" LEFT JOIN con_ms_insurer         cmsi ON ( cmsi.insurer_id = rereq.insurer_id )");
//                sql.append(" LEFT JOIN con_ms_department      cmsd ON ( cmsd.dep_code = rereq.department_receive_code )");
                sql.append(" WHERE 1 = 1");
//                sql.append(" AND reemp.status = 'A'");
//                sql.append(" AND rereq.request_reason_code IS NOT NULL");
                sql.append(" AND rereq.receive_no IS NOT NULL");
                sql.append(" AND ( reper.status != 'D' OR reper.status IS NULL )");
                if (!StringUtils.isEmpty(idCardNo)){
                    sql.append(" AND nvl(cmsi.sso_no, cmsi.id_card_no) = :idCardNo");
                    param.put("idCardNo", idCardNo);
                }
                if (!StringUtils.isEmpty(section)){
                    sql.append(" AND cmsi.section = :section");
                    param.put("section", section);
                }
                if (!StringUtils.isEmpty(depCode)){
                    sql.append(" AND cmsd.dep_code = :depCode");
                    param.put("depCode", depCode);
                }
                if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
                    sql.append(" AND reper.refund_period_year || reper.refund_period_month BETWEEN :startPeriod AND :endPeriod ");
                    param.put("startPeriod", startPeriod);
                    param.put("endPeriod", endPeriod);
                }
                if (!StringUtils.isEmpty(progressStatus)) {
                    switch (progressStatus) {
                        case "N" -> sql.append(" AND ( refemp.progress_status = 'N' )");
//                    case "W" -> sql.append(" AND refemp.progress_status = 'W' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
//                            " WHERE ctr33.refund_request_id = refemp.refund_request_id AND ctr33.order_status IS NULL ) ");
                        case "W" -> sql.append(" AND refemp.progress_status = 'W' ");
                        case "0" -> sql.append(" AND refemp.progress_status = '0' AND ( EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                                " WHERE ctr33.refund_request_id = refemp.refund_request_id AND ( ctr33.order_status IS NULL OR ctr33.order_status = 'C' ) ) " +
                                " OR NOT EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33 WHERE ctr33.refund_request_id = refemp.refund_request_id ) ) ");
                        case "1" -> sql.append(" AND refemp.progress_status = '1' AND ( EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                                " WHERE ctr33.refund_request_id = refemp.refund_request_id AND ( ctr33.order_status IS NULL OR ctr33.order_status = 'C' ) ) " +
                                " OR NOT EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33 WHERE ctr33.refund_request_id = refemp.refund_request_id ) ) ");
                        case "S" -> sql.append(" AND refemp.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                                " WHERE ctr33.refund_request_id = refemp.refund_request_id AND ctr33.order_status = 'W' ) ");
                        case "F" -> sql.append(" AND refemp.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                                " WHERE ctr33.refund_request_id = refemp.refund_request_id AND ctr33.order_status = 'F' ) ");
                        case "P" -> sql.append(" AND refemp.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                                " WHERE ctr33.refund_request_id = refemp.refund_request_id AND ctr33.order_status = 'P' ) ");
                        case "T" -> sql.append(" AND refemp.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                                " WHERE ctr33.refund_request_id = refemp.refund_request_id AND ctr33.order_status = 'T' ) ");
                        case "C" -> sql.append(" AND refemp.progress_status = 'C' AND ( EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                                " WHERE ctr33.refund_request_id = refemp.refund_request_id AND ( ctr33.order_status IS NULL OR ctr33.order_status = 'C' OR ctr33.order_status = 'F' ) ) " +
                                " OR NOT EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33 WHERE ctr33.refund_request_id = refemp.refund_request_id ) ) ");
                    }
                }
//                sql.append(" GROUP BY");
//                sql.append(" cmsi.insurer_id, rereq.refund_request_id, reper.refund_period, reper.refund_period_id, cmsi.id_card_no, cmsi.full_name, rereq.section,");
//                sql.append(" rereq.receive_no, rereq.receive_date, rereq.approve_no, rereq.approve_date, rerec.receipt_no, rerec.payment_date,");
//                sql.append(" cmsd.dep_code, rereq.mode_refund, refemp.refund_empe_amount, refemp.request_total_amount, refemp.refund_total_amount,");
//                sql.append(" refemp.progress_status, reper.approve_status, rereq.ref_refund_request_id,");
//                sql.append(" rereq.company_id, rereq.reverse_status, rereq.reverse_remark");
//                sql.append(" ORDER BY TO_DATE(reper.refund_period, 'MM/YYYY') DESC");
            }
//            final StringBuilder sql = new StringBuilder();
//
//            sql.append("SELECT mi.insurer_id,");
//            sql.append(" rr.refund_request_id,");
//            sql.append(" rp.refund_period,");
//            sql.append(" mi.id_card_no,");
//            sql.append(" mi.full_name,");
//            sql.append(" mi.section,");
//            sql.append(" rr.receive_no,");
//            sql.append(" rr.receive_date,");
//            sql.append(" rr.approve_no,");
//            sql.append(" rr.approve_date,");
//            sql.append(" rre.receipt_no,");
//            sql.append(" rre.payment_date,");
//            sql.append(" md.dep_code,");
//            sql.append(" to_date(to_char(rp.REFUND_PERIOD_MONTH || '/' || to_number(rp.REFUND_PERIOD_YEAR - 543)), 'MM/YYYY') AS date_temp");
//            if (!StringUtils.isEmpty(section) && "0".equals(section)) {
//                sql.append(" ,");
//                sql.append(" remp.refund_empe_amount,");
//                sql.append(" remp.request_total_amount,");
//                sql.append(" remp.refund_total_amount,");
////                sql.append(" remp.progress_status,");
//                sql.append(" (CASE WHEN remp.progress_status = '1' OR remp.progress_status = '0' THEN");
//                sql.append(" (CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN  rp.approve_status ELSE remp.progress_status END)");
//                sql.append(" ELSE remp.progress_status END) AS progress_status,");
////                sql.append(" CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN rp.approve_status ELSE remp.progress_status END AS progress_status,");
//                sql.append(" rem.empe_overp");
//            } else if (!StringUtils.isEmpty(section) && "1".equals(section)) {
//                sql.append(" ,");
//                sql.append(" rei.refund_empe_amount,");
//                sql.append(" rei.request_total_amount,");
//                sql.append(" rei.refund_total_amount,");
////                sql.append(" rei.progress_status,");
//                sql.append(" (CASE WHEN rei.progress_status = '1' OR rei.progress_status = '0' THEN");
//                sql.append(" (CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN  rp.approve_status ELSE rei.progress_status END)");
//                sql.append(" ELSE rei.progress_status END) AS progress_status,");
//                sql.append(" CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN rp.approve_status ELSE rei.progress_status END AS progress_status,");
//                sql.append(" rin.empe_overp");
//            }
//            sql.append(" FROM con_tb_refund_period  rp");
//            sql.append(" LEFT OUTER JOIN con_tb_refund_request rr ON ( rr.refund_request_id = rp.refund_request_id )");
//            if (!StringUtils.isEmpty(section) && "0".equals(section)) {
//                sql.append(" LEFT OUTER JOIN con_tr_refund_employee remp ON ( remp.refund_request_id = rr.refund_request_id )");
//                sql.append(" LEFT OUTER JOIN con_tb_refund_receipt rre ON (rre.REFUND_EMPLOYEE_ID = remp.REFUND_EMPLOYEE_ID)");
//                sql.append(" LEFT OUTER JOIN con_tr_receive_employee rem ON ( rem.receive_employee_id = rp.receive_employee_id )");
//            } else if (!StringUtils.isEmpty(section) && "1".equals(section)) {
//                sql.append(" LEFT OUTER JOIN con_tr_refund_insurer rei ON ( rei.refund_request_id = rr.refund_request_id )");
//                sql.append(" LEFT OUTER JOIN con_tb_refund_receipt rre ON ( rre.refund_insurer_id = rei.refund_insurer_id )");
//                sql.append(" LEFT OUTER JOIN con_tr_receive_insurer rin ON ( rin.receive_insurer_id = rp.receive_insurer_id )");
//            }
//            sql.append(" LEFT OUTER JOIN con_ms_insurer mi ON (mi.insurer_id = rr.insurer_id)");
//            sql.append(" LEFT JOIN con_ms_department md ON (md.dep_code = rr.department_receive_code)");
////            sql.append(" LEFT JOIN con_ms_department md ON (md.department_id = rr.department_receive_id)");
//            sql.append(" WHERE 1=1");
//
//            Map<String, Object> param = new HashMap<>();
//
//            if (!StringUtils.isEmpty(idCardNo)) {
//                sql.append(" AND mi.id_card_no = :idCardNo");
//                param.put("idCardNo", idCardNo);
//            }
//
//            if (!StringUtils.isEmpty(section)) {
//                sql.append(" AND mi.section = :section");
//                param.put("section", section);
//            }
//
//            if (!StringUtils.isEmpty(depCode)) {
//                sql.append(" AND md.dep_code = :depCode");
//                param.put("depCode", depCode);
//            }
//
//            if (!StringUtils.isEmpty(progressStatus) && "0".equals(section)) {
//                if (!StringUtils.isEmpty(progressStatus)) {
//                    if (progressStatus.equals("1") || progressStatus.equals("0")) {
//                        sql.append(" AND rp.approve_status = :progressStatus");
//                        sql.append(" AND remp.progress_status = :progressStatus");
//                        param.put("progressStatus", progressStatus);
//                    } else {
//                        if (progressStatus.equals("N")) {
//                            sql.append(" AND (remp.progress_status = ('N') )");
//                        } else {
//                            sql.append(" AND remp.progress_status = :progressStatus");
//                            param.put("progressStatus", progressStatus);
//                        }
//                    }
//                }
//
//            } else if (!StringUtils.isEmpty(progressStatus) && "1".equals(section)) {
//                if (!StringUtils.isEmpty(progressStatus)) {
//                    if (progressStatus.equals("1") || progressStatus.equals("0")) {
//                        sql.append(" AND rp.approve_status = :progressStatus");
//                        sql.append(" AND rei.progress_status = :progressStatus");
//                        param.put("progressStatus", progressStatus);
//                    } else {
//                        if (progressStatus.equals("N")) {
//                            sql.append(" AND (rei.progress_status = ('N') )");
//                        } else {
//                            sql.append(" AND rei.progress_status = :progressStatus");
//                            param.put("progressStatus", progressStatus);
//                        }
//                    }
//                }
//            }
//            sql.append(" AND rp.refund_period_year || rp.refund_period_month BETWEEN :startPeriod AND :endPeriod ");
//            param.put("startPeriod", startPeriod);
//            param.put("endPeriod", endPeriod);
//            sql.append(" ORDER BY date_temp DESC");

            if (!ObjectUtils.isObjectNull(paginable)) {
                paginable.setPrimaryKey("refund_request_id");
                paginable.setOrderBy("TO_DATE(reper.refund_period, 'MM/YYYY')");
                paginable.setOrderDirection(OrderDirection.DESC);
            } else {
                sql.append(" ORDER BY TO_DATE(reper.refund_period, 'MM/YYYY') DESC");
            }

            return queryForList(sql.toString(), param, paginable, new RefundInquiryInsurerRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public List<RefundInquiryInsurerBean> searchRefundInquiryInsurerByReceive(String receiveNo, String sectionReceiveNo, String section) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT mi.insurer_id,");
            sql.append(" rr.refund_request_id,");
            sql.append(" rp.refund_period,");
            sql.append(" mi.id_card_no,");
            sql.append(" mi.full_name,");
            sql.append(" rr.section,");
            sql.append(" rr.receive_no,");
            sql.append(" rr.receive_date,");
            sql.append(" rr.approve_no,");
            sql.append(" rr.approve_date,");
            sql.append(" md.dep_code,");
            sql.append(" rr.mode_refund,");
            sql.append(" rr.ref_refund_request_id,");
            sql.append(" NULL AS date_temp,");
            sql.append(" rr.company_id,");
            sql.append(" rr.reverse_status,");
            sql.append(" rr.reverse_remark,");
            sql.append(" rr.notice_status,");
            sql.append(" rr.announce_status,");
            sql.append(" rp.refund_period_id");
//            sql.append(" to_date(to_char(rp.REFUND_PERIOD_MONTH || '/' || to_number(rp.REFUND_PERIOD_YEAR - 543)), 'MM/YYYY') AS date_temp");
//            if (!StringUtils.isEmpty(sectionReceiveNo) && "RF02".equals(sectionReceiveNo)) {
//                sql.append(" ,");
//                sql.append(" rre.receipt_no,");
//                sql.append(" rre.payment_date,");
//                sql.append(" remp.refund_empe_amount,");
//                sql.append(" remp.request_total_amount,");
//                sql.append(" remp.refund_total_amount,");
////                sql.append(" remp.progress_status");
//                sql.append(" (CASE WHEN remp.progress_status = '1' OR remp.progress_status = '0' THEN");
//                sql.append(" (CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN  rp.approve_status ELSE remp.progress_status END)");
//                sql.append(" ELSE remp.progress_status END) AS progress_status");
////                sql.append(" CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN rp.approve_status ELSE remp.progress_status END AS progress_status");
//            } else if (!StringUtils.isEmpty(sectionReceiveNo) && "RF03".equals(sectionReceiveNo)) {
//                sql.append(" ,");
//                sql.append(" rre.receipt_no,");
//                sql.append(" rre.payment_date,");
//                sql.append(" rei.refund_empe_amount,");
//                sql.append(" rei.request_total_amount,");
//                sql.append(" rei.refund_total_amount,");
////                sql.append(" rei.progress_status");
//                sql.append(" (CASE WHEN rei.progress_status = '1' OR rei.progress_status = '0' THEN");
//                sql.append(" (CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN  rp.approve_status ELSE rei.progress_status END)");
//                sql.append(" ELSE rei.progress_status END) AS progress_status");
////                sql.append(" CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN rp.approve_status ELSE rei.progress_status END AS progress_status");
//            } else {
                if (StringUtils.hasText(section)) {
                    if (("0").equals(section)) {
                        sql.append(" ,");
                        sql.append(" NULL AS receipt_no,");
                        sql.append(" NULL AS payment_date,");
                        sql.append(" remp.refund_empe_amount,");
                        sql.append(" remp.request_total_amount,");
                        sql.append(" remp.refund_total_amount,");
//                sql.append(" remp.progress_status");
                        sql.append(" (CASE WHEN remp.progress_status = '1' OR remp.progress_status = '0' THEN");
                        sql.append(" (CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN  rp.approve_status ELSE remp.progress_status END)");
                        sql.append(" ELSE remp.progress_status END) AS progress_status");
                    } else if (("1").equals(section)) {
                        sql.append(" ,");
                        sql.append(" NULL AS receipt_no,");
                        sql.append(" NULL AS payment_date,");
                        sql.append(" rei.refund_empe_amount,");
                        sql.append(" rei.request_total_amount,");
                        sql.append(" rei.refund_total_amount,");
//                sql.append(" rei.progress_status");
                        sql.append(" (CASE WHEN rei.progress_status = '1' OR rei.progress_status = '0' THEN");
                        sql.append(" (CASE WHEN rp.approve_status = '1' OR rp.approve_status = '0' THEN  rp.approve_status ELSE rei.progress_status END)");
                        sql.append(" ELSE rei.progress_status END) AS progress_status");
                    }
                }


//            }

            sql.append(" FROM con_tb_refund_request rr");
            sql.append(" LEFT JOIN con_tb_refund_period  rp ON ( rr.refund_request_id = rp.refund_request_id AND ( rp.status != 'D' OR rp.status IS NULL) )");
//            if (!StringUtils.isEmpty(sectionReceiveNo) && "RF02".equals(sectionReceiveNo)) {
//                sql.append(" LEFT JOIN con_tr_refund_employee remp ON ( remp.refund_request_id = rr.refund_request_id )");
//                sql.append(" LEFT OUTER JOIN con_tr_refund_employee remp ON ( remp.refund_request_id = rr.refund_request_id )");
//                sql.append(" LEFT JOIN con_tb_refund_receipt rre ON (rre.REFUND_EMPLOYEE_ID = remp.REFUND_EMPLOYEE_ID)");
//            } else if (!StringUtils.isEmpty(sectionReceiveNo) && "RF03".equals(sectionReceiveNo)) {
//                sql.append(" LEFT JOIN con_tr_refund_insurer rei ON ( rei.refund_request_id = rr.refund_request_id )");
//                sql.append(" LEFT OUTER JOIN con_tr_refund_insurer rei ON ( rei.refund_request_id = rr.refund_request_id )");
//                sql.append(" LEFT JOIN con_tb_refund_receipt rre ON (rre.refund_insurer_id = rei.refund_insurer_id)");
//            } else {
                if (StringUtils.hasText(section)) {
                    if (("0").equals(section)) {
                        sql.append(" LEFT JOIN con_tr_refund_employee remp ON ( remp.refund_request_id = rr.refund_request_id )");
//                        sql.append(" LEFT JOIN con_tb_refund_receipt rre ON (rre.REFUND_EMPLOYEE_ID = remp.REFUND_EMPLOYEE_ID)");
                    } else if (("1").equals(section)) {
                        sql.append(" LEFT JOIN con_tr_refund_insurer rei ON ( rei.refund_request_id = rr.refund_request_id )");
//                        sql.append(" LEFT JOIN con_tb_refund_receipt rre ON (rre.refund_insurer_id = rei.refund_insurer_id)");
                    }
                }
//            }
            sql.append(" LEFT JOIN con_ms_insurer mi ON (mi.insurer_id = rr.insurer_id OR rp.employee_id = mi.insurer_id )");
            sql.append(" LEFT JOIN con_ms_department md ON (md.dep_code = rr.department_receive_code)");
//            sql.append(" LEFT JOIN con_ms_department md ON (md.department_id = rr.department_receive_id)");
            sql.append(" WHERE 1=1");
            Map<String, Object> param = new HashMap<>();

            if (StringUtils.hasText(section)) {
                if ("0".equals(section)) {
                    sql.append(" AND (rr.section = '0' OR rr.section = '3')");
                    param.put("section",section);
                } else if ("1".equals(section)) {
//                    sql.append(" AND rp.receive_insurer_id IS NOT NULL");
                    sql.append(" AND rr.section = :section");
                    param.put("section",section);
                }
            }
//            if (StringUtils.hasText(section)) {
//                sql.append(" AND rr.section = :section");
//                param.put("section",section);
//            }
            if (!StringUtils.isEmpty(receiveNo)) {
                sql.append(" AND rr.receive_no = :receiveNo");
                param.put("receiveNo", receiveNo);
            }

            return queryForList(sql.toString(), param, new RefundInquiryInsurerRowMapper());

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "con_tb_refund_period";
    }

    @Override
    protected String getPkColumnName() {
        return "refund_period_id";
    }
}
