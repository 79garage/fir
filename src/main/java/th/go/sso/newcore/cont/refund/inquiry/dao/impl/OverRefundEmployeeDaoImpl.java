package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.GetOverRefundEmployeePeriodsRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OverRefundEmpDetailsRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OverRefundEmployeeCompanyRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OverRefundEmployeeRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundEmpPeriodReceiptIdRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundEmpPeriodRowMapper;

@Repository
public class OverRefundEmployeeDaoImpl extends DaoBase<Long> {
    private static final Logger log = LoggerFactory.getLogger(OverRefundEmployeeDaoImpl.class);

    public OverRefundEmployeeDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<OverRefundEmployeeBean> searchRefundEmployee(String idCardNo, String receiveNo, String progressStatus,
                                                             String startPeriod, String endPeriod) throws Exception {
        try {
            final StringBuilder sql = new StringBuilder();
            final Map<String, Object> param = new HashMap<>();

//            sql.append("SELECT ");
//            sql.append(" t2.refund_request_id,t1.insurer_id,t1.id_card_no,t1.pay_period,t1.pay_period_year,t1.pay_period_month,t1.sum_amount,t1.over_paid,");
//            sql.append(" t1.count_company,t3.approve_no,t3.approve_date,t5.receipt_no,t5.payment_date,t4.progress_status,t3.notice_status,");
//            sql.append(" t3.announce_status,t3.reverse_status,t1.date_temp");
//            sql.append(" FROM ");
//            sql.append(" (SELECT DISTINCT msins.insurer_id,msins.id_card_no,recomp.pay_period,recomp.pay_period_year,recomp.pay_period_month,");
//            sql.append(" SUM(recemp.cntr_amount) AS sum_amount,SUM(recemp.cntr_amount) - 750 AS over_paid,COUNT(recomp.company_id) AS count_company,");
//            sql.append(" TO_DATE(to_char(recomp.pay_period_month || '/' || TO_NUMBER(recomp.pay_period_year - 543)), 'MM/YYYY') AS date_temp");
//            sql.append(" FROM con_tr_receive_company  recomp");
//            sql.append(" LEFT OUTER JOIN con_tr_receive_employee recemp ON ( recomp.receive_company_id = recemp.receive_company_id )");
//            sql.append(" LEFT OUTER JOIN con_tb_employment emp ON ( emp.company_id = recomp.company_id AND emp.insurer_id = recemp.insurer_id )");
//            sql.append(" LEFT OUTER JOIN con_ms_insurer msins ON ( msins.insurer_id = emp.insurer_id )");
//            sql.append(" WHERE 1 = 1");
//            sql.append(" GROUP BY msins.insurer_id,msins.id_card_no,recomp.pay_period,recomp.pay_period_year,recomp.pay_period_month");
//            sql.append(" HAVING SUM(recemp.cntr_amount) > 750 AND COUNT(recomp.company_id) > 1");
//            sql.append(" ORDER BY date_temp DESC");
//            sql.append(" ) t1");
//            sql.append(" LEFT OUTER JOIN con_tb_refund_period   t2 ON ( t2.refund_period = t1.pay_period AND t2.employee_id = t1.insurer_id )");
//            sql.append(" LEFT OUTER JOIN con_tb_refund_request  t3 ON ( t2.refund_request_id = t3.refund_request_id )");
//            sql.append(" LEFT OUTER JOIN con_tr_refund_employee t4 ON ( t4.refund_request_id = t3.refund_request_id )");
//            sql.append(" LEFT OUTER JOIN con_tb_refund_receipt  t5 ON ( t5.refund_employee_id = t4.refund_employee_id )");
//            sql.append(" WHERE 1 = 1");
            sql.append("SELECT * FROM ");
            sql.append(" (");
            sql.append(" SELECT t2.refund_request_id,");
            sql.append(" t1.insurer_id,");
            sql.append(" t1.id_card_no,");
            sql.append(" t1.pay_period,");
            sql.append(" t1.pay_period_year,");
            sql.append(" t1.pay_period_month,");
            sql.append(" t1.refund_reason_code,");
            sql.append(" t1.sum_amount,");
            sql.append(" t1.over_paid,");
            sql.append(" t1.count_company,");
            sql.append(" t2.approve_status,");
            sql.append(" t3.approve_no,");
            sql.append(" t3.approve_date,");
            sql.append(" NULL AS receipt_no,");
//            sql.append(" LISTAGG(DISTINCT t5.receipt_no, ',') WITHIN GROUP( ORDER BY t5.receipt_no ) AS receipt_no,");
//            sql.append(" t5.receipt_no,");
            sql.append(" NULL AS payment_date,");
//            sql.append(" t5.payment_date,");
//            sql.append(" CASE WHEN t2.approve_status = '1' OR t2.approve_status = '0' THEN t2.approve_status ELSE t4.progress_status END AS progress_status,");
            sql.append(" (CASE WHEN t4.progress_status = '1' OR t4.progress_status = '0' THEN ");
            sql.append(" (CASE WHEN t2.approve_status = '1' OR t2.approve_status = '0' THEN t2.approve_status ELSE t4.progress_status END)");
            sql.append(" ELSE t4.progress_status END) AS progress_status,");
            sql.append(" t3.notice_status,");
            sql.append(" t3.announce_status,");
            sql.append(" t3.reverse_status,");
            sql.append(" t3.reverse_remark,");
            sql.append(" NULL AS count_order_no,");
//            sql.append(" COUNT(DISTINCT req33.order_no) AS count_order_no,");
            sql.append(" NULL AS order_no,");
//            sql.append(" LISTAGG(DISTINCT req33.order_no, ',') WITHIN GROUP( ORDER BY req33.order_no ) AS order_no,");
            sql.append(" NULL AS order_status,");
//            sql.append(" LISTAGG(DISTINCT req33.order_status, ',') WITHIN GROUP( ORDER BY req33.order_status ) AS order_status,");
            sql.append(" t3.receive_no,");
            sql.append(" t1.date_temp");
            sql.append(" FROM");
            sql.append(" (");
            sql.append(" SELECT DISTINCT");
            sql.append(" msins.insurer_id,");
            sql.append(" msins.id_card_no,");
            sql.append(" recomp.pay_period,");
            sql.append(" recomp.pay_period_year,");
            sql.append(" recomp.pay_period_month,");
            sql.append(" '01' as refund_reason_code,");
            sql.append(" SUM(recemp.cntr_amount) AS sum_amount,");
            sql.append(" SUM(recemp.cntr_amount) - ((MAX(recemp.cntr_rate) * CASE WHEN SUM( recemp.total_wages) >= 15000 THEN 15000 ELSE SUM( recemp.total_wages) END) / 100) AS over_paid,");
            sql.append(" COUNT(DISTINCT mscom.company_no) AS count_company,");
            sql.append(" TO_DATE(to_char(recomp.pay_period_month || '/' || TO_NUMBER(recomp.pay_period_year - 543)), 'MM/YYYY') AS date_temp");
            sql.append(" FROM con_tr_receive_company  recomp");
            sql.append(" LEFT OUTER JOIN con_tr_receive_employee recemp ON ( recomp.receive_company_id = recemp.receive_company_id )");
            sql.append(" LEFT OUTER JOIN con_tb_employment emp ON ( emp.company_id = recomp.company_id AND emp.insurer_id = recemp.insurer_id )");
            sql.append(" LEFT OUTER JOIN con_ms_insurer msins ON ( msins.insurer_id = emp.insurer_id )");
            sql.append(" LEFT OUTER JOIN con_ms_company mscom ON ( mscom.company_id = recomp.company_id )");
            sql.append(" WHERE 1 = 1");
            sql.append(" AND recemp.status = 'A'");
            sql.append(" GROUP BY");
            sql.append(" msins.insurer_id,");
            sql.append(" msins.id_card_no,");
            sql.append(" recomp.pay_period,");
            sql.append(" recomp.pay_period_year,");
            sql.append(" recomp.pay_period_month");
            sql.append(" HAVING SUM(recemp.cntr_amount) > ((MAX(recemp.cntr_rate) * CASE WHEN MAX( recemp.total_wages) >= 15000 THEN 15000 ELSE MAX( recemp.total_wages) END) / 100) AND COUNT(DISTINCT mscom.company_no) > 1");
            sql.append(" AND SUM(recemp.total_wages) > 15000");
            sql.append(" ORDER BY date_temp DESC");
            sql.append(" ) t1");
            sql.append(" LEFT JOIN con_tb_refund_period   t2 ON (t2.refund_period = t1.pay_period AND t2.employee_id = t1.insurer_id AND ((t2.STATUS != 'D') OR t2.STATUS IS NULL))");
            sql.append(" LEFT JOIN con_tb_refund_request  t3 ON (t2.refund_request_id = t3.refund_request_id)");
            sql.append(" LEFT JOIN con_tr_refund_employee t4 ON (t4.refund_request_id = t3.refund_request_id)");
//            sql.append(" LEFT JOIN con_tb_refund_receipt  t5 ON (t5.refund_employee_id = t4.refund_employee_id)");
//            sql.append(" LEFT JOIN con_tb_requester33 req33 ON (req33.refund_request_id = t3.refund_request_id)");
            sql.append(" WHERE 1 = 1");
            sql.append(" GROUP BY t2.refund_request_id, t1.insurer_id, t1.id_card_no, t1.pay_period, t1.pay_period_year, ");
            sql.append(" t1.pay_period_month, t1.refund_reason_code, t1.sum_amount, t1.over_paid, t1.count_company, ");
            sql.append(" t2.approve_status, t3.approve_no, t3.approve_date, ");
//            sql.append(" t5.payment_date, ");
            sql.append(" t4.progress_status, t2.approve_status,");
            sql.append(" t3.notice_status, t3.announce_status, t3.reverse_status, t3.reverse_remark, t3.receive_no,t1.date_temp");
            sql.append(" ) t0");
            sql.append(" WHERE 1 = 1");
            sql.append(" ");

            if (!StringUtils.isEmpty(idCardNo)) {
                sql.append(" AND t0.id_card_no = :idCardNo");
                param.put("idCardNo", idCardNo);

                sql.append(" AND t0.pay_period_year || t0.pay_period_month BETWEEN :startPeriod AND :endPeriod");
                param.put("startPeriod",startPeriod);
                param.put("endPeriod",endPeriod);
            }
            if (!StringUtils.isEmpty(receiveNo)){
                sql.append(" AND t0.receive_no = :receiveNo");
                param.put("receiveNo", receiveNo);
            }
//            if (!StringUtils.isEmpty(progressStatus)) {
//                if (progressStatus.equals("1") || progressStatus.equals("0")) {
//                    sql.append(" AND t0.approve_status = :progressStatus");
//                    sql.append(" AND t0.progress_status = :progressStatus");
//                    param.put("progressStatus", progressStatus);
//                }else {
//                    if (progressStatus.equals("N")) {
//                        sql.append(" AND (t0.progress_status = ('N') OR t0.progress_status IS NULL )");
//                    }else {
//                        sql.append(" AND t0.progress_status = :progressStatus");
//                        param.put("progressStatus", progressStatus);
//                    }
//                }
//            }
//            if (!StringUtils.isEmpty(progressStatus)) {
//                switch (progressStatus) {
//                    case "N" -> sql.append(" AND (progress_status = ('N') OR progress_status IS NULL )");
//                    case "W" -> sql.append(" AND progress_status = 'W' AND order_status IS NULL ");
//                    case "0" -> sql.append(" AND progress_status = '0' AND approve_status = '0' AND (order_status LIKE '%C%' OR order_status IS NULL) ");
//                    case "1" -> sql.append(" AND progress_status = '1' AND approve_status = '1' AND (order_status LIKE '%C%' OR order_status IS NULL) ");
//                    case "S" -> sql.append(" AND progress_status = '1' AND order_status LIKE '%W%' ");
//                    case "F" -> sql.append(" AND progress_status = '1' AND order_status LIKE '%F%' ");
//                    case "P" -> sql.append(" AND progress_status = '1' AND order_status LIKE '%P%' ");
//                    case "T" -> sql.append(" AND progress_status = '1' AND order_status LIKE '%T%' ");
//                    case "C" -> sql.append(" AND progress_status = 'C' AND (order_status LIKE '%C%' OR order_status IS NULL) ");
//                }
//            }
            if (!StringUtils.isEmpty(progressStatus)) {
                switch (progressStatus) {
                    case "N" -> sql.append(" AND ( t0.progress_status = 'N' OR t0.progress_status IS NULL )");
//                    case "W" -> sql.append(" AND t0.progress_status = 'W' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
//                            " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status IS NULL ) ");
                    case "W" -> sql.append(" AND t0.progress_status = 'W' ");
                    case "0" -> sql.append(" AND t0.progress_status = '0' AND ( EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                            " WHERE ctr33.refund_request_id = t0.refund_request_id AND ( ctr33.order_status IS NULL OR ctr33.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33 WHERE ctr33.refund_request_id = t0.refund_request_id ) ) ");
                    case "1" -> sql.append(" AND t0.progress_status = '1' AND ( EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                            " WHERE ctr33.refund_request_id = t0.refund_request_id AND ( ctr33.order_status IS NULL OR ctr33.order_status = 'C' ) ) " +
                            " OR NOT EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33 WHERE ctr33.refund_request_id = t0.refund_request_id ) ) ");
                    case "S" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                            " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status = 'W' ) ");
                    case "F" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                            " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status = 'F' ) ");
                    case "P" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                            " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status = 'P' ) ");
                    case "T" -> sql.append(" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                            " WHERE ctr33.refund_request_id = t0.refund_request_id AND ctr33.order_status = 'T' ) ");
                    case "C" -> sql.append(" AND t0.progress_status = 'C' AND ( EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33" +
                            " WHERE ctr33.refund_request_id = t0.refund_request_id AND ( ctr33.order_status IS NULL OR ctr33.order_status = 'C' OR ctr33.order_status = 'F' ) ) " +
                            " OR NOT EXISTS ( SELECT ctr33.refund_request_id FROM con_tb_requester33 ctr33 WHERE ctr33.refund_request_id = t0.refund_request_id ) ) ");
                }
            }
            sql.append(" ORDER BY t0.date_temp DESC");

            log.debug("SQL : {}",sql);
            return queryForList(sql.toString(), param, new OverRefundEmployeeRowMapper());

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public List<OverRefundEmployeeCompanyBean> findCompanyDetailsMultipleCompanies(String idCardNo, String payPeriod) throws Exception {
        try {
            final StringBuilder sql = new StringBuilder();
            Map<String, Object> param = new HashMap<>();

            sql.append("SELECT trreemp.pay_period, ");
            sql.append("mscom.company_id, ");
            sql.append("mscom.company_no, ");
            sql.append("mscom.company_branch_code, ");
            sql.append("mscom.company_branch_name, ");
            sql.append("mscom.company_name, ");
            sql.append("trreemp.pay_date, ");
            sql.append("trreemp.total_wages, ");
//            sql.append("MAX(trreemp.cntr_rate)   AS cntr_rate, ");
//            sql.append("SUM(trreemp.cntr_amount) AS cntr_amount, ");
            sql.append("trreemp.cntr_rate, ");
            sql.append("trreemp.cntr_amount, ");
            sql.append("trreemp.empe_overp, ");
//            sql.append("msins.emp_register_date, ");
//            sql.append("msins.end_date, ");
            sql.append("ctbe.start_date AS emp_register_date, ");
            sql.append("ctbe.resign_date AS end_date, ");
            sql.append("msins.insurer_status ");
            sql.append("FROM con_tr_receive_employee trreemp ");
            sql.append("LEFT JOIN con_tr_receive_company trrecom ON (trrecom.receive_company_id = trreemp.receive_company_id) ");
            sql.append("LEFT JOIN con_tb_employment ctbe ON ctbe.company_id = trrecom.company_id AND ctbe.insurer_id = trreemp.insurer_id ");
            sql.append("AND TRUNC(trreemp.PAY_DATE) BETWEEN TRUNC(ctbe.START_DATE) AND COALESCE(TRUNC(ctbe.RESIGN_DATE),TRUNC(SYSDATE)) ");
            sql.append("LEFT JOIN con_ms_insurer msins ON (msins.insurer_id = ctbe.insurer_id) ");
            sql.append("LEFT JOIN con_ms_company mscom ON (mscom.company_id = ctbe.company_id) ");
//            sql.append("LEFT OUTER JOIN con_tb_employment emp ON ( emp.insurer_id = msins.insurer_id and emp.company_id = mscom.company_id) ");
            sql.append("WHERE 1 = 1 ");
//            sql.append("AND (msins.insurer_status = 'A' OR msins.insurer_status = 'R' OR msins.insurer_status = 'D') ");
//            sql.append("AND msins.end_date IS NULL ");
            sql.append("AND trreemp.status = 'A' ");

            if (!StringUtils.isEmpty(idCardNo)) {
                sql.append("AND msins.id_card_no = :idCardNo ");
                param.put("idCardNo", idCardNo);
            }
            if (!StringUtils.isEmpty(payPeriod)) {
                sql.append("AND trreemp.pay_period = :payPeriod ");
                param.put("payPeriod", payPeriod);

            }
            //TODO comment check payDate range startDate and resignDate
            sql.append(" AND trunc(trreemp.pay_date) BETWEEN trunc(ctbe.start_date) AND coalesce(trunc(ctbe.resign_date), trunc(sysdate))");

//            sql.append(" GROUP BY ");
//            sql.append(" trreemp.pay_period, ");
//            sql.append(" mscom.company_id, ");
//            sql.append(" mscom.company_no, ");
//            sql.append(" mscom.company_branch_code, ");
//            sql.append(" mscom.company_branch_name, ");
//            sql.append(" mscom.company_name, ");
//            sql.append(" trreemp.pay_date, ");
//            sql.append(" trreemp.total_wages, ");
//            sql.append(" trreemp.empe_overp, ");
//            sql.append(" msins.emp_register_date, ");
//            sql.append(" msins.end_date, ");
//            sql.append(" msins.insurer_status ");

            return queryForList(sql.toString(), param, new OverRefundEmployeeCompanyRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return Arrays.asList();
        }
    }

    public List<OverRefundEmployeeCompanyBean> findCompanyDetailsMultipleCompaniesExistsRefundRequest(Long refundPeriodId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " CTBRP.REFUND_PERIOD AS PAY_PERIOD, "+
//                " CTBRR.INSURER_ID, "+
//                " CTRRC.RECEIVE_COMPANY_ID, "+
//                " CTRRE.RECEIVE_EMPLOYEE_ID, "+
                " CMSC.COMPANY_ID, "+
                " CMSC.COMPANY_NO, "+
                " CMSC.COMPANY_BRANCH_CODE, "+
                " CMSC.COMPANY_BRANCH_NAME, "+
                " CMSC.COMPANY_NAME, "+
                " CTRRE.PAY_DATE, "+
                " CTRRE.TOTAL_WAGES, "+
                " CTRRE.CNTR_RATE, "+
                " CTRRE.CNTR_AMOUNT, "+
                " CTRRE.EMPE_OVERP, "+
                " CTBE.START_DATE  AS EMP_REGISTER_DATE, "+
                " CTBE.RESIGN_DATE AS END_DATE, "+
                " CMSI.INSURER_STATUS "+
                " FROM "+
                " CON_TB_REFUND_REQUEST CTBRR "+
                " JOIN CON_TB_REFUND_PERIOD          CTBRP ON CTBRP.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID "+
                " JOIN CON_TB_REFUND_REQUEST_COMPANY CTBRRC ON CTBRRC.REFUND_PERIOD_ID = CTBRP.REFUND_PERIOD_ID "+
                " LEFT JOIN CON_TR_RECEIVE_COMPANY CTRRC ON CTRRC.COMPANY_ID = CTBRRC.COMPANY_ID AND CTRRC.PAY_PERIOD = CTBRP.REFUND_PERIOD "+
                " LEFT JOIN CON_TR_RECEIVE_EMPLOYEE CTRRE ON CTRRE.RECEIVE_COMPANY_ID = CTRRC.RECEIVE_COMPANY_ID AND CTRRE.INSURER_ID = CTBRR.INSURER_ID AND CTRRE.STATUS = 'A' "+
                " LEFT JOIN CON_TB_EMPLOYMENT             CTBE ON CTBE.COMPANY_ID = CTBRRC.COMPANY_ID "+
                " AND CTBE.INSURER_ID = CTBRR.INSURER_ID "+
                " AND TRUNC(CTRRE.PAY_DATE) BETWEEN TRUNC(CTBE.START_DATE) AND COALESCE(TRUNC(CTBE.RESIGN_DATE),TRUNC(SYSDATE)) "+
                " LEFT JOIN CON_MS_INSURER                CMSI ON CMSI.INSURER_ID = CTBE.INSURER_ID "+
                " LEFT JOIN CON_MS_COMPANY                CMSC ON CMSC.COMPANY_ID = CTBE.COMPANY_ID "+
                " WHERE "+
                " CTBRP.REFUND_PERIOD_ID = :refundPeriodId ";
        param.put("refundPeriodId", refundPeriodId);
        return queryForList(sql, param, new OverRefundEmployeeCompanyRowMapper());
    }

    public List<OverRefundEmployeeBean> findReceiveEmployeeMultipleCompaniesPeriods(String idCardNo, List<String> period) throws Exception {
        try {
            final StringBuilder sql = new StringBuilder();
            final Map<String, Object> param = new HashMap<>();

            sql.append("SELECT recemp.pay_period,");
            sql.append(" MAX(recemp.pay_date) AS payment_date,");
            sql.append(" SUM(recemp.cntr_amount) AS sum_cntr_amount,");
//            sql.append(" SUM(recemp.cntr_amount) - 750 AS over_paid,");
            sql.append(" SUM(recemp.cntr_amount) - ( ( MAX(recemp.cntr_rate) * ");
            sql.append(" CASE WHEN SUM(recemp.total_wages) >= 15000 THEN 15000 ELSE SUM(recemp.total_wages) END ) / 100 ) - SUM(nvl(recemp.empe_overp, 0)) ");
//            sql.append(" - CASE WHEN recemp.refund_status = 'RR' THEN nvl( SUM(recemp.empe_overp), 0 ) ELSE 0 END");
            sql.append(" AS over_paid,");
//            sql.append(" COUNT(DISTINCT mscom.company_no) AS count_company,");
//            sql.append(" refreq.approve_no,");
//            sql.append(" refreq.approve_date,");
//            sql.append(" refrec.receipt_no,");
//            sql.append(" refrec.payment_date,");
//            sql.append(" refemp.progress_status,");
//            sql.append(" refreq.notice_status,");
//            sql.append(" refreq.announce_status,");
            sql.append(" LISTAGG(recemp.receipt_id,',') WITHIN GROUP (ORDER BY recemp.receipt_id) as receipt_id");
//            sql.append(" TO_DATE(to_char(recomp.pay_period_month || '/' || TO_NUMBER(recomp.pay_period_year - 543)), 'MM/YYYY') AS date_temp");
            sql.append(" FROM con_tr_receive_employee recemp");
            sql.append(" LEFT JOIN con_tr_receive_company recomp ON ( recomp.receive_company_id = recemp.receive_company_id )");
//            sql.append(" LEFT JOIN con_tb_employment emp ON ( emp.company_id = recomp.company_id AND emp.insurer_id = recemp.insurer_id )");
//            sql.append(" LEFT JOIN con_ms_insurer msins ON ( msins.insurer_id = emp.insurer_id )");
//            sql.append(" LEFT JOIN con_ms_company mscom ON ( mscom.company_id = recomp.company_id )");
            sql.append(" LEFT JOIN con_ms_insurer msins ON ( msins.insurer_id = recemp.insurer_id )");
            sql.append(" LEFT JOIN con_ms_company mscom ON ( mscom.company_id = recomp.company_id )");
            sql.append(" WHERE 1 = 1");
            sql.append(" AND recemp.status = 'A'");

            if (!StringUtils.isEmpty(idCardNo)) {
                sql.append(" AND msins.id_card_no = :idCardNo");
                param.put("idCardNo", idCardNo);
            }

            if (!ObjectUtils.isObjectEmpty(period)) {
                sql.append(" AND recemp.pay_period in (:period)");
                param.put("period", period);
            }

            sql.append(" GROUP BY recemp.pay_period");
//            sql.append(" ,recemp.refund_status ");

//            sql.append(" refreq.approve_no,");
//            sql.append(" refreq.approve_date,");
//            sql.append(" refrec.receipt_no,");
//            sql.append(" refrec.payment_date,");
//            sql.append(" refemp.progress_status,");
//            sql.append(" refreq.notice_status,");
//            sql.append(" refreq.announce_status,");
//            sql.append(" TO_DATE(TO_CHAR(recomp.pay_period_month || '/' || TO_NUMBER(recomp.pay_period_year - 543)), 'MM/YYYY')");
//            sql.append(" HAVING SUM(recemp.cntr_amount) > 750");

            sql.append(" HAVING SUM(recemp.cntr_amount) > ( ( MAX(recemp.cntr_rate) * ");
            sql.append(" CASE WHEN MAX(recemp.total_wages) >= 15000 THEN 15000 ELSE MAX(recemp.total_wages) END ) / 100 ) ");
            sql.append(" AND COUNT(DISTINCT mscom.company_no) > 1 ");
            sql.append(" AND SUM(recemp.total_wages) > 15000 ");
            sql.append(" ORDER BY to_date(recemp.pay_period, 'MM/YYYY') DESC");
            return queryForList(sql.toString(), param, new GetOverRefundEmployeePeriodsRowMapper());

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Integer getCountCompany(String idCardNo) throws Exception{
        try {
            final StringBuilder sql = new StringBuilder();
            Map<String, Object> param = new HashMap<>();
            sql.append("SELECT COUNT(t1.company_id) FROM con_tb_employment t1 join con_ms_insurer t2 on t2.insurer_id = t1.insurer_id WHERE 1=1 AND t2.id_card_no = :idCardNo");
            param.put("idCardNo", idCardNo);

            return queryForObject(sql.toString(), param, Integer.class);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public RefundInsurerBean searchRefundEmpDetails(Long refundRequestId) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT msins.insurer_id,");
            sql.append(" msins.id_card_no,");
            sql.append(" msins.full_name,");
            sql.append(" msins.insurer_status,");
            sql.append(" msdep.dep_code || ' ' || msdep.dep_name AS dep_fullname,");
            sql.append(" msdep.dep_code,");
//            sql.append(" msdepResp.dep_code AS dep_resp_code,");
            sql.append(" msins.dep_code AS dep_resp_code,");
            sql.append(" refreq.refund_request_id,");
            sql.append(" refreq.receive_no,");
            sql.append(" refreq.receive_date,");
            sql.append(" refreq.department_receive_code,");
            sql.append(" refreq.department_responsible_code,");
//            sql.append(" refreq.department_receive_id,");
//            sql.append(" refreq.department_responsible_id,");
            sql.append(" refreq.refund_type_place,");
            sql.append(" refreq.request_reason_code,");
            sql.append(" refreq.request_reason_other,");
            sql.append(" refreq.refund_bank_id,");
            sql.append(" refreq.refund_bank_branch_id,");
            sql.append(" refreq.refund_bank_account,");
            sql.append(" refreq.refund_prompt_pay,");
            sql.append(" refreq.approve_no,");
            sql.append(" refreq.approve_date,");
            sql.append(" refreq.notice_status,");
            sql.append(" refreq.announce_status,");
            sql.append(" refreq.refund_remark,");
            sql.append(" refreq.create_by,");
            sql.append(" refreq.create_date,");
            sql.append(" refreq.update_by,");
            sql.append(" refreq.update_date,");
            sql.append(" refreq.request_type,");
            sql.append(" refreq.request_channel,");
            sql.append(" refreq.refund_po_order,");
            sql.append(" refreq.refund_transfer_bank,");
            sql.append(" refreq.refund_transfer_date,");
            sql.append(" refreq.notice_no,");
            sql.append(" refreq.notice_date,");
            sql.append(" refreq.notice_ar_date,");
            sql.append(" refreq.pay_date,");
            sql.append(" refreq.approve_status,");
            sql.append(" refemp.progress_status,");
            sql.append(" nvl (refemp.request_empe_amount,0) As request_empe_amount, ");
            sql.append(" nvl (refemp.request_total_amount,0) As request_total_amount, ");
            sql.append(" nvl (refemp.request_interest_amount,0) As request_interest_amount, ");
            sql.append(" nvl (refemp.request_over_paid_amount,0) As request_over_paid_amount, ");
            sql.append(" nvl (refemp.refund_empe_amount,0) As refund_empe_amount, ");
            sql.append(" nvl (refemp.refund_interest_amount,0) As refund_interest_amount, ");
            sql.append(" nvl (refemp.refund_over_paid_amount,0) As refund_over_paid_amount, ");
            sql.append(" nvl (refemp.refund_total_amount,0) As refund_total_amount, ");
            sql.append(" refreq.SECTION,");
            sql.append(" refreq.disapprove_reason,");
            sql.append(" refreq.record_approve_by,");
            sql.append(" refreq.announce_no,");
            sql.append(" refreq.announce_date,");
            sql.append(" refreq.announce_ar_date,");
            sql.append(" refreq.announce_ar_create_by,");
            sql.append(" refreq.announce_ar_create_date,");
            sql.append(" refreq.notice_ar_date,");
            sql.append(" refemp.REFUND_SICKNESS_AMOUNT,");
            sql.append(" refemp.REFUND_RETIREMENT_AMOUNT,");
            sql.append(" refemp.REFUND_UNEMPLOYMENT_AMOUNT,");
            sql.append(" refreq.REVERSE_REMARK,");
            sql.append(" refreq.reverse_status,");
            sql.append(" refreq.DIST_CODE,");
            sql.append(" refreq.SUBDIST_CODE,");
            sql.append(" refreq.PROVINCE_CODE,");
            sql.append(" msloc.dist_name,");
            sql.append(" msloc.subdist_name,");
            sql.append(" msloc.prov_name,");
            sql.append(" refreq.reply_date,");
            sql.append(" refreq.record_approve_date,");
            sql.append(" refreq.refund_po_order_name,");
            sql.append(" refreq.PERIOD_START,");
            sql.append(" refreq.PERIOD_END,");
            sql.append(" refreq.REQUEST_AMOUNT");
//            sql.append(" req33.ORDER_STATUS,");
//            sql.append(" req33.order_remark");
            sql.append(" FROM CON_MS_INSURER msins");
            sql.append(" LEFT OUTER JOIN con_tb_refund_request refreq ON ( msins.insurer_id = refreq.insurer_id )");
            sql.append(" LEFT OUTER JOIN con_tr_refund_employee refemp ON ( refemp.refund_request_id = refreq.refund_request_id)");
//            sql.append(" LEFT OUTER JOIN con_tb_requester33 req33 ON ( req33.refund_request_id = refreq.refund_request_id )");
            sql.append(" LEFT OUTER JOIN con_ms_department msdep ON (msdep.dep_code = refreq.department_receive_code)");
//            sql.append(" LEFT OUTER JOIN con_ms_department msdep ON (msdep.department_id = refreq.department_receive_id)");
            sql.append(" LEFT OUTER JOIN con_ms_department msdepResp ON (msdepResp.dep_code = refreq.department_responsible_code)");
//            sql.append(" LEFT OUTER JOIN con_ms_department msdepResp ON (msdepResp.department_id = refreq.department_responsible_id)");
            sql.append(" LEFT JOIN con_ms_location msloc ON (refreq.dist_code = msloc.dist_code AND refreq.subdist_code = msloc.subdist_code AND  refreq.province_code = msloc.prov_code)");
            sql.append(" where 1=1");

            Map<String, Object> param = new HashMap<>();

            sql.append(" AND refreq.refund_request_id = :refundRequestId");
            param.put("refundRequestId", refundRequestId);

            return queryForObject(sql.toString(), param, new OverRefundEmpDetailsRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public List<OverRefundEmployeeBean> findRefundMultiCompanyPeriods(Long refundRequestId) throws Exception {

        final Map<String, Object> param = new HashMap<>();
        String sql = """
                SELECT
                    msins.id_card_no,
                    refreq.refund_request_id,
                    refreq.insurer_id,
                    refper.refund_period,
                    refper.refund_period_id,
                    refreq.record_approve_by,
                    refreq.record_approve_date,
                    nvl( refper.request_empe_amount, 0 ) AS request_empe_amount,
                    nvl( refper.request_total_amount, 0 ) AS request_total_amount,
                    nvl( refper.refund_empe_amount, 0 ) AS refund_empe_amount,
                    nvl( refper.refund_total_amount, 0 ) AS refund_total_amount,
                    nvl( refper.request_interest_amount, 0 ) AS request_interest_amount,
                    nvl( refper.cntr_amount, 0 ) AS cntr_amount,
                    refemp.progress_status,
                    --LISTAGG(DISTINCT ctb33.order_no, ',') WITHIN GROUP( ORDER BY ctb33.order_no ) AS order_no,
                    --LISTAGG(DISTINCT ctb33.order_status, ',') WITHIN GROUP( ORDER BY ctb33.order_status ) AS order_status,
                    --LISTAGG(DISTINCT ctb33.order_remark, ',') WITHIN GROUP( ORDER BY ctb33.order_remark ) AS order_remark,
                    ctb33.order_no,
                    ctb33.order_status,
                    ctb33.order_remark,
                    refper.refund_sickness_rate,
                    refper.refund_retirement_rate,
                    refper.refund_unemployment_rate,
                    refper.refund_sickness_amount,
                    refper.refund_retirement_amount,
                    refper.refund_unemployment_amount,
                    refper.disapprove_reason,
                    refper.approve_status,
                    ctbrr.receipt_no,
                    ctbrr.payment_date
                FROM
                    con_tb_refund_request  refreq
                    LEFT JOIN con_tr_refund_employee refemp ON ( refemp.refund_request_id = refreq.refund_request_id )
                    LEFT JOIN con_tb_refund_period   refper ON ( refper.refund_request_id = refreq.refund_request_id
                                                               AND ( ( refper.status != 'D' 
                                                               --AND refper.status != 'C' 
                                                               ) OR refper.status IS NULL ) 
                                                               )
                    LEFT JOIN con_ms_insurer         msins ON ( msins.insurer_id = refreq.insurer_id )
                    --LEFT JOIN con_tb_requester33     ctb33 ON ctb33.refund_request_id = refreq.refund_request_id
                    LEFT JOIN con_tb_requester33     ctb33 ON ctb33.period = refper.refund_period AND ctb33.refund_request_id = refreq.refund_request_id AND ctb33.requester_type = '1'
                    LEFT JOIN con_tb_refund_receipt  ctbrr ON ctbrr.refund_receipt_id = ctb33.refund_receipt_id
                WHERE
                    1 = 1
                    AND refreq.refund_request_id = :refundRequestId
                """;
//            sql += """
//                    GROUP BY
//                        msins.id_card_no,
//                        refreq.refund_request_id,
//                        refper.refund_period,
//                        refper.refund_period_id,
//                        refreq.record_approve_by,
//                        refreq.record_approve_date,
//                        refper.request_empe_amount,
//                        refper.request_total_amount,
//                        refper.refund_empe_amount,
//                        refper.refund_total_amount,
//                        refper.request_interest_amount,
//                        refper.cntr_amount,
//                        refemp.progress_status,
//                        refper.refund_sickness_rate,
//                        refper.refund_retirement_rate,
//                        refper.refund_unemployment_rate,
//                        refper.refund_sickness_amount,
//                        refper.refund_retirement_amount,
//                        refper.refund_unemployment_amount
//                    """;

        param.put("refundRequestId", refundRequestId);

        return queryForList(sql, param, new RefundEmpPeriodRowMapper());
    }
    public List<OverRefundEmployeeBean> searchRefundEmpPeriodReceipt(String idCardNo, String period) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT LISTAGG( DISTINCT recemp.receipt_id, ',') WITHIN GROUP( ORDER BY recemp.receipt_id ) AS receipt_id ");
            sql.append(" FROM con_tr_receive_employee recemp");
            sql.append(" LEFT OUTER JOIN con_tr_receive_company  recomp ON ( recomp.receive_company_id = recemp.receive_company_id )");
            sql.append(" LEFT OUTER JOIN con_ms_insurer          msins ON ( msins.insurer_id = recemp.insurer_id )");
            sql.append(" LEFT OUTER JOIN con_tb_refund_request   refreq ON ( refreq.insurer_id = recemp.insurer_id )");
            sql.append(" WHERE 1 = 1");

            Map<String, Object> param = new HashMap<>();

            if (!StringUtils.isEmpty(idCardNo)){
                sql.append(" AND msins.id_card_no = :idCardNo");
                param.put("idCardNo", idCardNo);
            }
            if (!StringUtils.isEmpty(period)){
                sql.append(" AND recemp.pay_period IN ( :period )");
                param.put("period", period);
            }

            return queryForList(sql.toString(), param, new RefundEmpPeriodReceiptIdRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    public Integer queryCountCompany(String idCardNo, String startPeriod, String endPeriod) throws Exception{
        try {
            final String sql = "SELECT COUNT(DISTINCT t3.company_no) as count_company FROM con_tb_employment t1 " +
                    " INNER JOIN con_ms_insurer t2 ON t2.insurer_id = t1.insurer_id " +
                    " INNER JOIN con_ms_company t3 ON t3.company_id = t1.company_id " +
                    " WHERE 1 = 1 AND t2.id_card_no = :idCardNo " +
                    " AND ( ( to_char(t1.start_date, 'YYYYMM') BETWEEN :startPeriod AND :endPeriod AND t1.end_date IS NULL ) " +
                    " OR ( to_char(t1.start_date, 'YYYYMM') BETWEEN :startPeriod AND :endPeriod AND to_char(t1.end_date, 'YYYYMM') BETWEEN :startPeriod AND :endPeriod ) )";
            final Map<String,Object> param = new HashMap<>(1);
            param.put("idCardNo",idCardNo);
            param.put("startPeriod",startPeriod);
            param.put("endPeriod",endPeriod);

            return queryForObject(sql,param,Integer.class);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "con_tr_receive_employee";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }
}
