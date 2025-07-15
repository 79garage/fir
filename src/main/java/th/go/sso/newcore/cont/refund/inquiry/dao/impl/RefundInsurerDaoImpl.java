package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundInsurerRowMapper;

@Repository
public class RefundInsurerDaoImpl extends DaoBase<Long> {

    public RefundInsurerDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_REQUEST";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_REQUEST_ID";
    }

    public RefundInsurerBean searchRefundRequest(Long refundRequestId) throws Exception {

        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " CMSC.INSURER_ID, "+
                " CMSC.ID_CARD_NO, "+
                " CMSC.FULL_NAME, "+
                " CMSC.INSURER_STATUS, "+
                " CMSD.DEP_CODE || ' ' || CMSD.DEP_NAME  AS DEP_FULLNAME, "+
                " CMSD.DEP_CODE, "+
                " CMSDRESP.DEP_CODE AS DEP_RESP_CODE, "+
                " CTBRR.REFUND_REQUEST_ID, "+
                " CTBRR.RECEIVE_NO, "+
                " CTBRR.RECEIVE_DATE, "+
                " CTBRR.DEPARTMENT_RECEIVE_CODE, "+
                " CTBRR.DEPARTMENT_RESPONSIBLE_CODE, "+
                " CTBRR.REFUND_TYPE_PLACE, "+
                " CTBRR.REQUEST_REASON_CODE, "+
                " CTBRR.REQUEST_REASON_OTHER, "+
                " CTBRR.REFUND_BANK_ID, "+
                " CTBRR.REFUND_BANK_BRANCH_ID, "+
                " CTBRR.REFUND_BANK_ACCOUNT, "+
                " CTBRR.REFUND_PROMPT_PAY, "+
                " CTBRR.APPROVE_NO, "+
                " CTBRR.APPROVE_DATE, "+
                " CTBRR.NOTICE_STATUS, "+
                " CTBRR.ANNOUNCE_STATUS, "+
                " CTBRR.REFUND_REMARK, "+
                " CTBRR.CREATE_BY, "+
                " CTBRR.CREATE_DATE, "+
                " CTBRR.UPDATE_BY, "+
                " CTBRR.UPDATE_DATE, "+
                " CTBRR.REQUEST_TYPE, "+
                " CTBRR.REQUEST_CHANNEL, "+
                " CTBRR.REFUND_PO_ORDER, "+
                " CTBRR.REFUND_TRANSFER_BANK, "+
                " CTBRR.REFUND_TRANSFER_DATE, "+
                " CTBRR.NOTICE_NO, "+
                " CTBRR.NOTICE_DATE, "+
                " CTBRR.NOTICE_AR_DATE, "+
                " CTBRR.PAY_DATE, "+
                " CTBRR.APPROVE_STATUS, "+
                " CTRRI.PROGRESS_STATUS, "+
                " NVL( CTRRI.REQUEST_EMPE_AMOUNT, 0 ) AS REQUEST_EMPE_AMOUNT, "+
                " NVL( CTRRI.REQUEST_TOTAL_AMOUNT, 0 ) AS REQUEST_TOTAL_AMOUNT, "+
                " NVL( CTRRI.REQUEST_INTEREST_AMOUNT, 0 ) AS REQUEST_INTEREST_AMOUNT, "+
                " NVL( CTRRI.REQUEST_OVER_PAID_AMOUNT, 0 ) AS REQUEST_OVER_PAID_AMOUNT, "+
                " NVL( CTRRI.REFUND_EMPE_AMOUNT, 0 ) AS REFUND_EMPE_AMOUNT, "+
                " CTRRI.REFUND_INTEREST_AMOUNT, "+
                " NVL( CTRRI.REFUND_OVER_PAID_AMOUNT, 0 ) AS REFUND_OVER_PAID_AMOUNT, "+
                " NVL( CTRRI.REFUND_TOTAL_AMOUNT, 0 ) AS REFUND_TOTAL_AMOUNT, "+
                " CTRRI.REFUND_SICKNESS_AMOUNT, "+
                " CTRRI.REFUND_RETIREMENT_AMOUNT, "+
                " CTRRI.REFUND_UNEMPLOYMENT_AMOUNT, "+
                " CTBRR.REVERSE_REMARK, "+
                " CTBRR.REVERSE_STATUS, "+
                " CTBRR.SECTION, "+
//                " CTBRR.DIST_CODE, "+
//                " CTBRR.SUBDIST_CODE, "+
//                " CTBRR.PROVINCE_CODE, "+
//                " CMSL.DIST_NAME, "+
//                " CMSL.SUBDIST_NAME, "+
//                " CMSL.PROV_NAME, "+
                " CTBRR.DISAPPROVE_REASON, "+
                " CTBRR.ANNOUNCE_AR_DATE, "+
                " CTBRR.ANNOUNCE_AR_CREATE_BY, "+
                " CTBRR.ANNOUNCE_AR_CREATE_DATE, "+
                " CTBRR.ANNOUNCE_NO, "+
                " CTBRR.ANNOUNCE_DATE, "+
                " CTBRR.ANNOUNCE_CREATE_BY, "+
                " CTBRR.ANNOUNCE_CREATE_DATE, "+
                " CTBRR.REPLY_DATE, "+
                " CTBRR.RECORD_APPROVE_DATE, "+
                " CTBRR.RECORD_APPROVE_BY, "+
                " CTBRR.REFUND_PO_ORDER_NAME, " +
                " CTBRR.PERIOD_START, "+
                " CTBRR.PERIOD_END, "+
                " CTBRR.REQUEST_AMOUNT, "+
                " NULL                              AS ID_CARD_NO_REF, "+
                " NULL                              AS TITLE_CODE, "+
                " NULL                              AS FIRST_NAME, "+
                " NULL                              AS LAST_NAME, "+
                " NULL                              AS FULL_NAME_REF, "+
                " NULL                              AS ADDRESS, "+
                " NULL                              AS VILLAGE, "+
                " NULL                              AS MOO, "+
                " NULL                              AS SOI, "+
                " NULL                              AS ROAD, "+
                " NULL                              AS PROVINCE_CODE, "+
                " NULL                              AS PROVINCE_NAME, "+
                " NULL                              AS DIST_CODE, "+
                " NULL                              AS DIST_NAME, "+
                " NULL                              AS SUBDIST_CODE, "+
                " NULL                              AS SUBDIST_NAME, "+
                " NULL                              AS ZIP_CODE, "+
                " NULL                              AS TELEPHONE, "+
                " NULL                              AS MOBILE, "+
                " NULL                              AS EMAIL, "+
                " NULL                              AS REQUESTER_TYPE "+
                " FROM "+
                " CON_MS_INSURER CMSC "+
                " LEFT JOIN CON_TB_REFUND_REQUEST CTBRR ON ( CMSC.INSURER_ID = CTBRR.INSURER_ID ) "+
                " LEFT JOIN CON_TR_REFUND_INSURER CTRRI ON ( CTRRI.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID ) "+
                " LEFT JOIN CON_MS_DEPARTMENT     CMSD ON ( CMSD.DEP_CODE = CTBRR.DEPARTMENT_RECEIVE_CODE ) "+
                " LEFT JOIN CON_MS_DEPARTMENT     CMSDRESP ON ( CMSDRESP.DEP_CODE = CMSC.DEP_CODE ) "+
                " LEFT JOIN CON_MS_LOCATION       CMSL ON ( CTBRR.DIST_CODE = CMSL.DIST_CODE "+
                " AND CTBRR.SUBDIST_CODE = CMSL.SUBDIST_CODE "+
                " AND CTBRR.PROVINCE_CODE = CMSL.PROV_CODE ) "+
                " WHERE "+
                " 1 = 1 "+
                " AND CTBRR.REFUND_REQUEST_ID = :refundRequestId ";
        param.put("refundRequestId", refundRequestId);
        return queryForObject(sql, param, new RefundInsurerRowMapper());
    }

    public RefundInsurerBean findRefundRequestInsurer(Long refundRequestId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " NULL                              AS INSURER_ID, "+
//                " CTBHR.ID_CARD_NO, "+
//                " CTBHR.RECEIPT_NAME                AS FULL_NAME, "+
                " NULL AS ID_CARD_NO, "+
                " NULL AS FULL_NAME, "+
                " NULL                              AS INSURER_STATUS, "+
                " NULL                              AS DEP_FULLNAME, "+
                " CTBRR.DEPARTMENT_RECEIVE_CODE     AS DEP_CODE, "+
                " CTBRR.DEPARTMENT_RESPONSIBLE_CODE AS DEP_RESP_CODE, "+
                " CTBRR.REFUND_REQUEST_ID, "+
                " CTBRR.RECEIVE_NO, "+
                " CTBRR.RECEIVE_DATE, "+
                " CTBRR.DEPARTMENT_RECEIVE_CODE, "+
                " CTBRR.DEPARTMENT_RESPONSIBLE_CODE, "+
                " CTBRR.REFUND_TYPE_PLACE, "+
                " CTBRR.REQUEST_REASON_CODE, "+
                " CTBRR.REQUEST_REASON_OTHER, "+
                " CTBRR.REFUND_BANK_ID, "+
                " CTBRR.REFUND_BANK_BRANCH_ID, "+
                " CTBRR.REFUND_BANK_ACCOUNT, "+
                " CTBRR.REFUND_PROMPT_PAY, "+
                " CTBRR.APPROVE_NO, "+
                " CTBRR.APPROVE_DATE, "+
                " CTBRR.NOTICE_STATUS, "+
                " CTBRR.ANNOUNCE_STATUS, "+
                " CTBRR.REFUND_REMARK, "+
                " CTBRR.CREATE_BY, "+
                " CTBRR.CREATE_DATE, "+
                " CTBRR.UPDATE_BY, "+
                " CTBRR.UPDATE_DATE, "+
                " CTBRR.REQUEST_TYPE, "+
                " CTBRR.REQUEST_CHANNEL, "+
                " CTBRR.REFUND_PO_ORDER, "+
                " CTBRR.REFUND_TRANSFER_BANK, "+
                " CTBRR.REFUND_TRANSFER_DATE, "+
                " CTBRR.NOTICE_NO, "+
                " CTBRR.NOTICE_DATE, "+
                " CTBRR.NOTICE_AR_DATE, "+
                " CTBRR.PAY_DATE, "+
                " CTBRR.APPROVE_STATUS, "+
                " CTRRI.PROGRESS_STATUS, "+
                " NVL( CTRRI.REQUEST_EMPE_AMOUNT, 0 ) AS REQUEST_EMPE_AMOUNT, "+
                " NVL( CTRRI.REQUEST_TOTAL_AMOUNT, 0 ) AS REQUEST_TOTAL_AMOUNT, "+
                " NVL( CTRRI.REQUEST_INTEREST_AMOUNT, 0 ) AS REQUEST_INTEREST_AMOUNT, "+
                " NVL( CTRRI.REQUEST_OVER_PAID_AMOUNT, 0 ) AS REQUEST_OVER_PAID_AMOUNT, "+
                " NVL( CTRRI.REFUND_EMPE_AMOUNT, 0 ) AS REFUND_EMPE_AMOUNT, "+
                " CTRRI.REFUND_INTEREST_AMOUNT, "+
                " NVL( CTRRI.REFUND_OVER_PAID_AMOUNT, 0 ) AS REFUND_OVER_PAID_AMOUNT, "+
                " NVL( CTRRI.REFUND_TOTAL_AMOUNT, 0 ) AS REFUND_TOTAL_AMOUNT, "+
                " CTRRI.REFUND_SICKNESS_AMOUNT, "+
                " CTRRI.REFUND_RETIREMENT_AMOUNT, "+
                " CTRRI.REFUND_UNEMPLOYMENT_AMOUNT, "+
                " CTBRR.REVERSE_REMARK, "+
                " CTBRR.REVERSE_STATUS, "+
                " CTBRR.SECTION, "+
//                " CTBRR.DIST_CODE, "+
//                " CTBRR.SUBDIST_CODE, "+
//                " CTBRR.PROVINCE_CODE, "+
//                " CMSL.DIST_NAME, "+
//                " CMSL.SUBDIST_NAME, "+
//                " CMSL.PROV_NAME, "+
                " CTBRR.DISAPPROVE_REASON, "+
                " CTBRR.ANNOUNCE_AR_DATE, "+
                " CTBRR.ANNOUNCE_AR_CREATE_BY, "+
                " CTBRR.ANNOUNCE_AR_CREATE_DATE, "+
                " CTBRR.ANNOUNCE_NO, "+
                " CTBRR.ANNOUNCE_DATE, "+
                " CTBRR.ANNOUNCE_CREATE_BY, "+
                " CTBRR.ANNOUNCE_CREATE_DATE, "+
                " CTBRR.REPLY_DATE, "+
                " CTBRR.RECORD_APPROVE_DATE, "+
                " CTBRR.RECORD_APPROVE_BY, "+
                " CTBRR.REFUND_PO_ORDER_NAME, "+
                " CTBRR.PERIOD_START, "+
                " CTBRR.PERIOD_END, "+
                " CTBRR.REQUEST_AMOUNT, "+
                " CTBR39.ID_CARD_NO AS ID_CARD_NO_REF, "+
                " CTBR39.TITLE_CODE, "+
                " CTBR39.FIRST_NAME, "+
                " CTBR39.LAST_NAME, "+
                " CTBR39.FULL_NAME AS FULL_NAME_REF, "+
                " CTBR39.ADDRESS, "+
                " CTBR39.VILLAGE, "+
                " CTBR39.MOO, "+
                " CTBR39.SOI, "+
                " CTBR39.ROAD, "+
                " CTBR39.PROVINCE_CODE, "+
                " CTBR39.PROVINCE_NAME, "+
                " CTBR39.DIST_CODE, "+
                " CTBR39.DIST_NAME, "+
                " CTBR39.SUBDIST_CODE, "+
                " CTBR39.SUBDIST_NAME, "+
                " CTBR39.ZIP_CODE, "+
                " CTBR39.TELEPHONE, "+
                " CTBR39.MOBILE, "+
                " CTBR39.EMAIL, "+
                " CTBR39.REQUESTER_TYPE "+
                " FROM "+
                " CON_TB_REFUND_REQUEST CTBRR "+
                " LEFT JOIN CON_TB_REFUND_PERIOD  CTBRP ON CTBRP.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID "+
                " LEFT JOIN CON_TR_REFUND_INSURER CTRRI ON CTRRI.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID "+
                " LEFT JOIN CON_TB_REQUESTER39    CTBR39 ON CTBR39.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID AND CTBR39.PERIOD = CTBRP.REFUND_PERIOD "+
//                " LEFT JOIN CON_TB_HOLD_RECEIVE   CTBHR ON CTBHR.HOLD_RECEIVE_ID = CTBRP.HOLD_RECEIVE_ID "+
//                " LEFT JOIN CON_MS_LOCATION       CMSL ON ( CTBRR.DIST_CODE = CMSL.DIST_CODE "+
//                " AND CTBRR.SUBDIST_CODE = CMSL.SUBDIST_CODE AND CTBRR.PROVINCE_CODE = CMSL.PROV_CODE ) "+
                " WHERE "+
                " 1 = 1 "+
                " AND CTBRR.REFUND_REQUEST_ID = :refundRequestId "+
                " GROUP BY "+
                " CTBRR.DEPARTMENT_RECEIVE_CODE, "+
                " CTBRR.DEPARTMENT_RESPONSIBLE_CODE, "+
                " CTBRR.REFUND_REQUEST_ID, "+
                " CTBRR.RECEIVE_NO, "+
                " CTBRR.RECEIVE_DATE, "+
                " CTBRR.DEPARTMENT_RECEIVE_CODE, "+
                " CTBRR.DEPARTMENT_RESPONSIBLE_CODE, "+
                " CTBRR.REFUND_TYPE_PLACE, "+
                " CTBRR.REQUEST_REASON_CODE, "+
                " CTBRR.REQUEST_REASON_OTHER, "+
                " CTBRR.REFUND_BANK_ID, "+
                " CTBRR.REFUND_BANK_BRANCH_ID, "+
                " CTBRR.REFUND_BANK_ACCOUNT, "+
                " CTBRR.REFUND_PROMPT_PAY, "+
                " CTBRR.APPROVE_NO, "+
                " CTBRR.APPROVE_DATE, "+
                " CTBRR.NOTICE_STATUS, "+
                " CTBRR.ANNOUNCE_STATUS, "+
                " CTBRR.REFUND_REMARK, "+
                " CTBRR.CREATE_BY, "+
                " CTBRR.CREATE_DATE, "+
                " CTBRR.UPDATE_BY, "+
                " CTBRR.UPDATE_DATE, "+
                " CTBRR.REQUEST_TYPE, "+
                " CTBRR.REQUEST_CHANNEL, "+
                " CTBRR.REFUND_PO_ORDER, "+
                " CTBRR.REFUND_TRANSFER_BANK, "+
                " CTBRR.REFUND_TRANSFER_DATE, "+
                " CTBRR.NOTICE_NO, "+
                " CTBRR.NOTICE_DATE, "+
                " CTBRR.NOTICE_AR_DATE, "+
                " CTBRR.PAY_DATE, "+
                " CTBRR.APPROVE_STATUS, "+
                " CTRRI.PROGRESS_STATUS, "+
                " CTRRI.REQUEST_EMPE_AMOUNT, "+
                " CTRRI.REQUEST_TOTAL_AMOUNT, "+
                " CTRRI.REQUEST_INTEREST_AMOUNT, "+
                " CTRRI.REQUEST_OVER_PAID_AMOUNT, "+
                " CTRRI.REFUND_EMPE_AMOUNT, "+
                " CTRRI.REFUND_INTEREST_AMOUNT, "+
                " CTRRI.REFUND_OVER_PAID_AMOUNT, "+
                " CTRRI.REFUND_TOTAL_AMOUNT, "+
                " CTRRI.REFUND_SICKNESS_AMOUNT, "+
                " CTRRI.REFUND_RETIREMENT_AMOUNT, "+
                " CTRRI.REFUND_UNEMPLOYMENT_AMOUNT, "+
                " CTBRR.REVERSE_REMARK, "+
                " CTBRR.REVERSE_STATUS, "+
                " CTBRR.SECTION, "+
                " CTBRR.DISAPPROVE_REASON, "+
                " CTBRR.ANNOUNCE_AR_DATE, "+
                " CTBRR.ANNOUNCE_AR_CREATE_BY, "+
                " CTBRR.ANNOUNCE_AR_CREATE_DATE, "+
                " CTBRR.ANNOUNCE_NO, "+
                " CTBRR.ANNOUNCE_DATE, "+
                " CTBRR.ANNOUNCE_CREATE_BY, "+
                " CTBRR.ANNOUNCE_CREATE_DATE, "+
                " CTBRR.REPLY_DATE, "+
                " CTBRR.RECORD_APPROVE_DATE, "+
                " CTBRR.RECORD_APPROVE_BY, "+
                " CTBRR.REFUND_PO_ORDER_NAME, "+
                " CTBRR.PERIOD_START, "+
                " CTBRR.PERIOD_END, "+
                " CTBRR.REQUEST_AMOUNT, "+
                " CTBR39.ID_CARD_NO, "+
                " CTBR39.TITLE_CODE, "+
                " CTBR39.FIRST_NAME, "+
                " CTBR39.LAST_NAME, "+
                " CTBR39.FULL_NAME, "+
                " CTBR39.ADDRESS, "+
                " CTBR39.VILLAGE, "+
                " CTBR39.MOO, "+
                " CTBR39.SOI, "+
                " CTBR39.ROAD, "+
                " CTBR39.PROVINCE_CODE, "+
                " CTBR39.PROVINCE_NAME, "+
                " CTBR39.DIST_CODE, "+
                " CTBR39.DIST_NAME, "+
                " CTBR39.SUBDIST_CODE, "+
                " CTBR39.SUBDIST_NAME, "+
                " CTBR39.ZIP_CODE, "+
                " CTBR39.TELEPHONE, "+
                " CTBR39.MOBILE, "+
                " CTBR39.EMAIL, "+
                " CTBR39.REQUESTER_TYPE ";
        param.put("refundRequestId", refundRequestId);
        return queryForObject(sql, param, new RefundInsurerRowMapper());
    }

    public String getProgressStatusByRefundRequestId(Long refundRequestId) throws Exception {
        final String sql = "SELECT progress_status FROM con_tr_refund_insurer WHERE refund_request_id = :refundRequestId";
        final Map<String, Object> param = new HashMap<>();
        param.put("refundRequestId", refundRequestId);
        return queryForObject(sql, param, String.class);
    }
}
