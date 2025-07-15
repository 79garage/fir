package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.PaybackRefundPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbPaybackRequestRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.PaybackRefundPeriodRowMapper;

@Repository
public class PaybackRequestDaoImpl extends DaoBase<ConTbPaybackRequestBean> {

    public PaybackRequestDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public ConTbPaybackRequestBean getConTbPaybackRequestById(Long paybackRequestId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " CTBPR.PAYBACK_REQUEST_ID, "+
                " CTBRR.REFUND_REQUEST_ID, "+
                " CTBRR.COMPANY_ID, "+
                " CTBRR.INSURER_ID, "+
                " CTBRR.RECEIVE_NO, "+
                " CTBRR.SECTION, "+
                " CTBRR.REQUEST_REASON_CODE, "+
                " CTBRR.REQUEST_REASON_OTHER, "+
                " CTBRR.APPROVE_DATE, "+
                " CTBRR.REQUEST_TYPE, "+
                " CTBRR.REQUEST_CHANNEL, "+
                " CTBPR.PAYBACK_TOTAL_AMOUNT, "+
                " CTBPR.PAYBACK_REQUEST_NO, "+
                " CTBPR.PAYBACK_REQUEST_DATE, "+
                " CTBPR.PAYBACK_REQUEST_TYPE, "+
                " CTBPR.PAYBACK_APPROVE_STATUS, "+
                " CTBPR.PAYBACK_APPROVE_DATE, "+
                " CTBPR.PAYBACK_RECEIVE_STATUS, "+
                " CTBPR.PAYBACK_RECEIVE_DATE, "+
                " CTBPR.REVERSE_STATUS, "+
                " CTBPR.PAYBACK_CAUSE, "+
                " CTBPR.STATUS, "+
                " CTBPR.REMARK, "+
                " CTBPR.MANAGE_BY, "+
                " CTBPR.CREATE_BY, "+
                " CTBPR.CREATE_DATE, "+
                " CTBPR.UPDATE_BY, "+
                " CTBPR.UPDATE_DATE, "+
                " CMSI.FULL_NAME, "+
                " CMSI.ID_CARD_NO, "+
                " CMSC.COMPANY_NO, "+
                " CMSC.COMPANY_NAME, "+
                " CMSC.COMPANY_BRANCH_CODE, "+
                " CMSC.COMPANY_BRANCH_NAME, "+
                " CTRRC.REFUND_COMPANY_ID, "+
                " CTRRE.REFUND_EMPLOYEE_ID, "+
                " NULL AS DEP_CODE, "+
                " NVL( CTRRC.REFUND_TOTAL_AMOUNT, NVL( CTRRE.REFUND_TOTAL_AMOUNT, CTRRI.REFUND_TOTAL_AMOUNT ) )    AS REFUND_TOTAL_AMOUNT, "+
                " NVL( CMSI.INSURER_STATUS, CMSC.COMPANY_STATUS )    AS ALL_STATUS, "+
                " NULL AS ORDER_NO, "+
                " NULL AS RECEIPT_NO, "+
                " NULL AS PAYMENT_DATE, "+
                " NULL AS TOTAL_AMOUNT, "+
                " CTBPR.PAYBACK_STATUS, "+
                " CTBRR.SPLIT_REFUND "+
                " FROM "+
                " CON_TB_REFUND_REQUEST  CTBRR "+
                " LEFT JOIN CON_TB_PAYBACK_REQUEST CTBPR ON ( CTBPR.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID ) "+
                " LEFT JOIN CON_MS_INSURER         CMSI ON ( CMSI.INSURER_ID = CTBRR.INSURER_ID ) "+
                " LEFT JOIN CON_MS_COMPANY         CMSC ON ( CMSC.COMPANY_ID = CTBRR.COMPANY_ID ) "+
                " LEFT JOIN CON_TR_REFUND_COMPANY  CTRRC ON ( CTRRC.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID ) "+
                " LEFT JOIN CON_TR_REFUND_EMPLOYEE CTRRE ON ( CTRRE.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID ) "+
                " LEFT JOIN CON_TR_REFUND_INSURER  CTRRI ON ( CTRRI.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID ) "+
                " WHERE "+
                " 1 = 1 "+
                " AND CTBPR.PAYBACK_REQUEST_ID = :paybackRequestId ";
//        final String sql = """
//                SELECT
//                    payreq.payback_request_id,
//                    refreq.refund_request_id,
//                    refreq.company_id,
//                    refreq.insurer_id,
//                    refreq.receive_no,
//                    refreq.section,
//                    refreq.request_reason_code,
//                    refreq.request_reason_other,
//                    refreq.approve_date,
//                    refreq.request_type,
//                    refreq.request_channel,
//                    payreq.payback_total_amount,
//                    payreq.payback_request_no,
//                    payreq.payback_request_date,
//                    payreq.payback_request_type,
//                    payreq.payback_approve_status,
//                    payreq.payback_approve_date,
//                    payreq.payback_receive_status,
//                    payreq.payback_receive_date,
//                    payreq.reverse_status,
//                    payreq.payback_cause,
//                    payreq.status,
//                    payreq.remark,
//                    payreq.manage_by,
//                    payreq.create_by,
//                    payreq.create_date,
//                    payreq.update_by,
//                    payreq.update_date,
//                    msins.full_name,
//                    msins.id_card_no,
//                    mscomp.company_no,
//                    mscomp.company_name,
//                    mscomp.company_branch_code,
//                    mscomp.company_branch_name,
//                    refcomp.refund_company_id,
//                    refempe.refund_employee_id,
//                    msdep.dep_code,
//                    -- SUM(nvl( reqcom.refund_total_amount, nvl( req33.refund_total_amount, req39.refund_total_amount ) ) ) AS refund_total_amount,
//                    NULL AS refund_total_amount,
//                    nvl( msins.insurer_status, mscomp.company_status ) AS all_status,
//                    LISTAGG(DISTINCT nvl( reqcom.order_no, nvl( req33.order_no, req39.order_no ) ), ',') WITHIN GROUP(
//                    ORDER BY reqcom.order_no, req33.order_no, req39.order_no ) AS order_no,
//                    LISTAGG(DISTINCT recei.receipt_no, ',') WITHIN GROUP( ORDER BY recei.receipt_no ) AS receipt_no,
//                    nvl( refrec.payment_date, nvl( refrec2.payment_date, refrec3.payment_date ) ) AS payment_date,
//                    nvl( refrec.total_amount, nvl( refrec2.total_amount, refrec3.total_amount ) ) AS total_amount,
//                    payreq.payback_status,
//                    refreq.split_refund
//                FROM
//                    con_tb_refund_request         refreq
//                    LEFT JOIN con_tb_payback_request        payreq ON ( payreq.refund_request_id = refreq.refund_request_id )
//                    LEFT JOIN con_ms_insurer                msins ON ( msins.insurer_id = refreq.insurer_id )
//                    LEFT JOIN con_ms_company                mscomp ON ( mscomp.company_id = refreq.company_id )
//                    LEFT JOIN con_tr_refund_company         refcomp ON ( refcomp.refund_request_id = refreq.refund_request_id )
//                    LEFT JOIN con_tr_refund_employee        refempe ON ( refempe.refund_request_id = refreq.refund_request_id )
//                    LEFT JOIN con_tr_refund_insurer         refins ON ( refins.refund_request_id = refreq.refund_request_id )
//                    LEFT JOIN con_tb_refund_receipt         refrec ON ( refrec.refund_company_id = refcomp.refund_company_id )
//                    LEFT JOIN con_tb_refund_receipt         refrec2 ON ( refrec2.refund_employee_id = refempe.refund_employee_id )
//                    LEFT JOIN con_tb_refund_receipt         refrec3 ON ( refrec3.refund_insurer_id = refins.refund_insurer_id )
//                    LEFT JOIN con_ms_department             msdep ON ( msdep.dep_code = refreq.department_receive_code )
//                    LEFT JOIN con_tb_requester_company      reqcom ON reqcom.refund_request_id = refreq.refund_request_id
//                    LEFT JOIN con_tb_requester33            req33 ON req33.refund_request_id = refreq.refund_request_id
//                    LEFT JOIN con_tb_requester39            req39 ON req39.refund_request_id = refreq.refund_request_id
//                    LEFT JOIN con_tb_refund_period          refper ON refper.refund_request_id = refreq.refund_request_id
//                    LEFT JOIN con_tb_refund_request_receipt rerere ON rerere.refund_period_id = refper.refund_period_id
//                    LEFT JOIN con_tb_receipt                recei ON recei.receipt_id = rerere.receipt_id
//                WHERE
//                    1 = 1
//                    AND payreq.payback_request_id = :paybackRequestId
//                GROUP BY
//                    payreq.payback_request_id, refreq.refund_request_id, refreq.company_id, refreq.insurer_id, refreq.receive_no,
//                    refreq.section, refreq.request_reason_code, refreq.request_reason_other, refreq.approve_date, refreq.request_type,
//                    refreq.request_channel, payreq.payback_total_amount, payreq.payback_request_no, payreq.payback_request_date,
//                    payreq.payback_request_type, payreq.payback_approve_status, payreq.payback_approve_date, payreq.payback_receive_status,
//                    payreq.payback_receive_date, payreq.reverse_status, payreq.payback_cause, payreq.status, payreq.remark,
//                    payreq.manage_by, payreq.create_by, payreq.create_date, payreq.update_by, payreq.update_date,
//                    msins.full_name, msins.id_card_no, mscomp.company_no, mscomp.company_name, mscomp.company_branch_code,
//                    mscomp.company_branch_name, refcomp.refund_company_id, refempe.refund_employee_id, msdep.dep_code,
//                    -- reqcom.refund_total_amount, req33.refund_total_amount, req39.refund_total_amount,
//                    msins.insurer_status, mscomp.company_status, refrec.payment_date, refrec2.payment_date, refrec3.payment_date, refrec.total_amount,
//                    refrec2.total_amount, refrec3.total_amount, payreq.payback_status, refreq.split_refund
//                """;
        param.put("paybackRequestId", paybackRequestId);

        return queryForObject(sql.toString(), param, new ConTbPaybackRequestRowMapper());
    }

    public List<PaybackRefundPeriodBean> getRefundPeriodByRefundRequestId(Long paybackRequestId) throws Exception {

        final Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " CTBRP.REFUND_PERIOD_ID, "+
                " CTBRP.REFUND_PERIOD, "+
                " CTBPP.PAYBACK_AMOUNT                          AS REFUND_TOTAL_AMOUNT, "+
                " CTBPP.PAYBACK_AMOUNT, "+
                " CTBRR.REFUND_REQUEST_ID, "+
                " CTBRR.APPROVE_DATE, "+
                " NULL                                          AS REFUND_COMPANY_ID, "+
                " NULL                                          AS REFUND_EMPLOYEE_ID, "+
                " NULL                                          AS REFUND_INSURER_ID, "+
                " CTBPRE.RECEIPT_NO, "+
                " CTBPRE.PAYMENT_DATE, "+
                " NULL                                          AS ORDER_NO, "+
                " NULL                                          AS ORDER_STATUS, "+
                " NULL                                          AS REQUESTER_ID, "+
                " NVL(CTBR33.FULL_NAME, "+
                " NVL(CTBR39.FULL_NAME, CMSC.COMPANY_NAME)) AS FULL_NAME, "+
                " CTBPP.PAYBACK_PERIOD_ID, "+
                " NULL                                          AS RELATION_GROUP, "+
                " CTBPR.PAYBACK_STATUS, "+
                " CTBPR.PAYBACK_ORDER_NO, "+
                " NULL                                          AS COMPANY_BRANCH_NAME "+
                " FROM "+
                " CON_TB_PAYBACK_PERIOD CTBPP "+
                " JOIN CON_TB_REFUND_PERIOD     CTBRP ON CTBRP.REFUND_PERIOD_ID = CTBPP.REFUND_PERIOD_ID "+
                " JOIN CON_TB_PAYBACK_REQUEST   CTBPR ON CTBPR.PAYBACK_REQUEST_ID = CTBPP.PAYBACK_REQUEST_ID "+
                " JOIN CON_TB_REFUND_REQUEST    CTBRR ON CTBRR.REFUND_REQUEST_ID = CTBPR.REFUND_REQUEST_ID "+
                " LEFT JOIN CON_TR_PAYBACK_COMPANY   CTRPC ON CTRPC.PAYBACK_REQUEST_ID = CTBPR.PAYBACK_REQUEST_ID "+
                " LEFT JOIN CON_TR_PAYBACK_EMPLOYEE  CTRPE ON CTRPE.PAYBACK_REQUEST_ID = CTBPR.PAYBACK_REQUEST_ID "+
                " LEFT JOIN CON_TR_PAYBACK_INSURER39 CTRPI ON CTRPI.PAYBACK_REQUEST_ID = CTBPR.PAYBACK_REQUEST_ID "+
                " LEFT JOIN CON_TB_PAYBACK_RECEIPT   CTBPRE ON CTBPRE.PAYBACK_COMPANY_ID = CTRPC.PAYBACK_COMPANY_ID "+
                " OR CTBPRE.PAYBACK_EMPLOYEE_ID = CTRPE.PAYBACK_EMPLOYEE_ID "+
                " OR CTBPRE.PAYBACK_INSURER_ID = CTRPI.PAYBACK_INSURER39_ID "+
                " LEFT JOIN CON_TB_REQUESTER_COMPANY CTBRC ON CTBRC.REQUESTER_ID = CTBPR.REQUESTER_COMPANY_ID "+
                " LEFT JOIN CON_TB_REQUESTER33       CTBR33 ON CTBR33.REQUESTER_ID = CTBPR.REQUESTER33_ID "+
                " LEFT JOIN CON_TB_REQUESTER39       CTBR39 ON CTBR39.REQUESTER_ID = CTBPR.REQUESTER39_ID "+
                " LEFT JOIN CON_MS_INSURER           CMSI ON ( CMSI.INSURER_ID = CTBRR.INSURER_ID ) "+
                " LEFT JOIN CON_MS_COMPANY           CMSC ON ( CMSC.COMPANY_ID = CTBRR.COMPANY_ID ) "+
                " WHERE "+
                " 1 = 1 "+
                " AND CTBPP.PAYBACK_REQUEST_ID = :paybackRequestId ";
        param.put("paybackRequestId", paybackRequestId);
//        final String sql = """
//                SELECT
//                    ctbrp.refund_period_id,
//                    ctbrp.refund_period,
//                    --ctbrp.refund_total_amount,
//                    ctbpp.payback_amount AS refund_total_amount,
//                    ctbpp.payback_amount,
//                    ctbrr.refund_request_id,
//                    ctbrr.approve_date,
//                    ctrrc.refund_company_id,
//                    ctrre.refund_employee_id,
//                    ctrri.refund_insurer_id,
//                    LISTAGG(DISTINCT nvl( receipt1.receipt_no, nvl( receipt2.receipt_no, receipt3.receipt_no ) ), ',') WITHIN GROUP(
//                    ORDER BY receipt1.receipt_no, receipt2.receipt_no, receipt3.receipt_no ) AS receipt_no,
//                    --LISTAGG(DISTINCT nvl( receipt1.payment_date, nvl( receipt2.payment_date, receipt3.payment_date ) ), ',') WITHIN GROUP(
//                    --ORDER BY receipt1.payment_date, receipt2.payment_date, receipt3.payment_date ) AS payment_date,
//                    nvl( receipt1.payment_date, nvl( receipt2.payment_date, receipt3.payment_date ) ) AS payment_date,
//                    NULL AS order_no,
//                    NULL AS order_status,
//                    NULL AS requester_id,
//                    nvl(ctbr33.full_name, nvl(ctbr39.full_name, cmsc.company_name)) AS full_name,
//                    ctbpp.payback_period_id,
//                    null AS relation_group,
//                    ctbpr.payback_status,
//                    ctbpr.payback_order_no,
//                    NULL AS company_branch_name
//                FROM
//                    con_tb_payback_period ctbpp
//                    JOIN con_tb_refund_period   ctbrp ON ctbrp.refund_period_id = ctbpp.refund_period_id
//                    JOIN con_tb_payback_request ctbpr ON ctbpr.payback_request_id = ctbpp.payback_request_id
//                    JOIN con_tb_refund_request  ctbrr ON ctbrr.refund_request_id = ctbpr.refund_request_id
//                    LEFT JOIN con_tr_refund_company  ctrrc ON ( ctrrc.refund_request_id = ctbrr.refund_request_id )
//                    LEFT JOIN con_tr_refund_employee ctrre ON ( ctrre.refund_request_id = ctbrr.refund_request_id )
//                    LEFT JOIN con_tr_refund_insurer  ctrri ON ( ctrri.refund_request_id = ctbrr.refund_request_id )
//                    LEFT JOIN con_tb_refund_receipt  receipt1 ON ( receipt1.refund_company_id = ctrrc.refund_company_id )
//                    LEFT JOIN con_tb_refund_receipt  receipt2 ON ( receipt2.refund_employee_id = ctrre.refund_employee_id )
//                    LEFT JOIN con_tb_refund_receipt  receipt3 ON ( receipt3.refund_insurer_id = ctrri.refund_insurer_id )
//                    LEFT JOIN con_tb_requester_company      ctbrc ON ctbrc.requester_id = ctbpr.requester_company_id
//                    LEFT JOIN con_tb_requester33            ctbr33 ON ctbr33.requester_id = ctbpr.requester33_id
//                    LEFT JOIN con_tb_requester39            ctbr39 ON ctbr39.requester_id = ctbpr.requester39_id
//                    LEFT JOIN con_ms_insurer cmsi ON ( cmsi.insurer_id = ctbrr.insurer_id )
//                    LEFT JOIN con_ms_company cmsc ON ( cmsc.company_id = ctbrr.company_id )
//                WHERE
//                    1 = 1
//                    AND ctbpp.payback_request_id = :paybackRequestId
//                    --AND ctbpp.status = 'A'
//                GROUP BY
//                    ctbrp.refund_period_id,
//                    ctbrp.refund_period,
//                    ctbrp.refund_total_amount,
//                    ctbpp.payback_amount,
//                    ctbrr.refund_request_id,
//                    ctbrr.approve_date,
//                    ctrrc.refund_company_id,
//                    ctrre.refund_employee_id,
//                    ctrri.refund_insurer_id,
//                    receipt1.payment_date,
//                    receipt3.payment_date,
//                    receipt3.payment_date,
//                    ctbpp.payback_period_id,
//                    ctbr33.full_name,
//                    ctbr39.full_name,
//                    cmsc.company_name,
//                    receipt1.payment_date,
//                    receipt2.payment_date,
//                    receipt3.payment_date,
//                    ctbpp.payback_period_id,
//                    ctbpr.payback_status,
//                    ctbpr.payback_order_no
//                ORDER BY ctbpp.payback_period_id ASC
//                """;
//        param.put("paybackRequestId", paybackRequestId);
        return queryForList(sql.toString(), param, new PaybackRefundPeriodRowMapper());
    }
    @Override
    protected String getTableName() {
        return "con_tb_payback_request";
    }

    @Override
    protected String getPkColumnName() {
        return "payback_request_id";
    }
}
