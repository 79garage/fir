package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundInsurerRequestRowMapper;

@Repository
public class RefundInsurerRequestDaoImpl extends DaoBase<Long> {

    public RefundInsurerRequestDaoImpl(DataSource dataSource) {
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

    public List<RefundInsurerPeriodListBean> findRefundInsurerPeriods(Long refundRequestId) throws Exception {
        try {
            final Map<String, Object> param = new HashMap<>();
            String sql = """
                    SELECT
                        refper.receive_insurer_id,
                        refper.hold_receive_id,
                        refper.refund_period_id,
                        refper.refund_period,
                        refreq.refund_request_id,
                        NULL AS FULL_NAME,
                        NULL AS ID_CARD_NO,
                        refreq.record_approve_by,
                        refreq.record_approve_date,
                        reins.cntr_amount,
                        --coalesce( reins.cntr_amount, ctbhr.cntr_amount, 0 ) AS cntr_amount,
                        reins.interest_paid,
                        --coalesce( reins.interest_paid, ctbhr.interest_amount, 0 ) AS interest_paid,
                        nvl( refper.request_empe_amount, 0 ) AS request_empe_amount,
                        nvl( refper.request_total_amount, 0 ) AS request_total_amount,
                        nvl( refper.request_interest_amount, 0 ) AS request_interest_amount,
                        nvl( refper.refund_empe_amount, 0 ) AS refund_empe_amount,
                        nvl( refper.refund_total_amount, 0 ) AS refund_total_amount,
                        nvl( refper.refund_interest_amount, 0 ) AS refund_interest_amount,
                        refins.progress_status,
                        refper.approve_status,
                        --LISTAGG(DISTINCT ctb39.order_no, ',') WITHIN GROUP( ORDER BY ctb39.order_no ) AS order_no,
                        --LISTAGG(DISTINCT ctb39.order_status, ',') WITHIN GROUP( ORDER BY ctb39.order_status ) AS order_status,
                        --LISTAGG(DISTINCT ctb39.order_remark, ',') WITHIN GROUP( ORDER BY ctb39.order_remark ) AS order_remark,
                        ctb39.order_no,
                        ctb39.order_status,
                        ctb39.order_remark,
                        refper.refund_sickness_rate,
                        refper.refund_retirement_rate,
                        refper.refund_unemployment_rate,
                        refper.refund_sickness_amount,
                        refper.refund_retirement_amount,
                        refper.refund_unemployment_amount,
                        refper.disapprove_reason,
                        ctbrr.payment_date,
                        ctbrr.receipt_no
                        --nvl( reins.pay_date, ctbhr.receipt_date ) AS payment_date
                    FROM
                        con_tb_refund_request  refreq
                        LEFT JOIN con_tr_refund_insurer  refins ON ( refins.refund_request_id = refreq.refund_request_id )
                        LEFT JOIN con_tb_refund_period   refper ON ( refper.refund_request_id = refreq.refund_request_id ) AND ( refper.status != 'D' OR refper.status IS NULL )
                        LEFT JOIN con_tr_receive_insurer reins ON ( reins.receive_insurer_id = refper.receive_insurer_id )
                        --LEFT JOIN con_tb_hold_receive ctbhr ON ctbhr.hold_receive_id = refper.hold_receive_id
                        --LEFT JOIN con_tb_requester39     ctb39 ON ctb39.refund_request_id = refreq.refund_request_id
                        LEFT JOIN con_tb_requester39     ctb39 ON ctb39.period = refper.refund_period AND ctb39.refund_request_id = refreq.refund_request_id AND ctb39.requester_type = '1'
                        LEFT JOIN con_tb_refund_receipt ctbrr ON ctbrr.refund_receipt_id = ctb39.refund_receipt_id
                    WHERE
                        1 = 1
                        AND refper.receive_insurer_id IS NOT NULL
                        AND refreq.refund_request_id = :refundRequestId
                    """;
//            sql += """
//                    GROUP BY
//                        refper.receive_insurer_id,
//                        refper.hold_receive_id,
//                        refper.refund_period_id,
//                        refper.refund_period,
//                        refreq.refund_request_id,
//                        refreq.record_approve_by,
//                        refreq.record_approve_date,
//                        reins.cntr_amount,
//                        reins.interest_paid,
//                        refper.request_empe_amount,
//                        refper.request_total_amount,
//                        refper.request_interest_amount,
//                        refper.refund_empe_amount,
//                        refper.refund_total_amount,
//                        refper.refund_interest_amount,
//                        refins.progress_status,
//                        refper.approve_status,
//                        ctbhr.cntr_amount,
//                        ctbhr.interest_amount,
//                        refper.refund_sickness_rate,
//                        refper.refund_retirement_rate,
//                        refper.refund_unemployment_rate,
//                        refper.refund_sickness_amount,
//                        refper.refund_retirement_amount,
//                        refper.refund_unemployment_amount
//                    """;
            param.put("refundRequestId", refundRequestId);

            return queryForList(sql, param, new RefundInsurerRequestRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public List<RefundInsurerPeriodListBean> findRefundInsurerHoldReceivePeriods(Long refundRequestId) throws Exception {
        try {
            Map<String, Object> param = new HashMap<>();
            String sql = " SELECT "+
                    " CTBRP.RECEIVE_INSURER_ID, "+
                    " CTBRP.HOLD_RECEIVE_ID, "+
                    " CTBRP.REFUND_PERIOD_ID, "+
                    " CTBRP.REFUND_PERIOD, "+
                    " CTBRR.REFUND_REQUEST_ID, "+
                    " CTBHR.RECEIPT_NAME AS FULL_NAME, "+
                    " CTBHR.ID_CARD_NO, "+
                    " CTBRR.RECORD_APPROVE_BY, "+
                    " CTBRR.RECORD_APPROVE_DATE, "+
                    " COALESCE( CTBHR.CNTR_AMOUNT, 0 )                  AS CNTR_AMOUNT, "+
                    " COALESCE( CTBHR.INTEREST_AMOUNT, 0 )                  AS INTEREST_PAID, "+
                    " NVL( CTBRP.REQUEST_EMPE_AMOUNT, 0 )                  AS REQUEST_EMPE_AMOUNT, "+
                    " NVL( CTBRP.REQUEST_TOTAL_AMOUNT, 0 )                  AS REQUEST_TOTAL_AMOUNT, "+
                    " NVL( CTBRP.REQUEST_INTEREST_AMOUNT, 0 )                  AS REQUEST_INTEREST_AMOUNT, "+
                    " NVL( CTBRP.REFUND_EMPE_AMOUNT, 0 )                  AS REFUND_EMPE_AMOUNT, "+
                    " NVL( CTBRP.REFUND_TOTAL_AMOUNT, 0 )                  AS REFUND_TOTAL_AMOUNT, "+
                    " NVL( CTBRP.REFUND_INTEREST_AMOUNT, 0 )                  AS REFUND_INTEREST_AMOUNT, "+
                    " CTRRI.PROGRESS_STATUS, "+
                    " CTBRP.APPROVE_STATUS, "+
                    " CTBR39.ORDER_NO, "+
                    " CTBR39.ORDER_STATUS, "+
                    " CTBR39.ORDER_REMARK, "+
                    " CTBRP.REFUND_SICKNESS_RATE, "+
                    " CTBRP.REFUND_RETIREMENT_RATE, "+
                    " CTBRP.REFUND_UNEMPLOYMENT_RATE, "+
                    " CTBRP.REFUND_SICKNESS_AMOUNT, "+
                    " CTBRP.REFUND_RETIREMENT_AMOUNT, "+
                    " CTBRP.REFUND_UNEMPLOYMENT_AMOUNT, "+
                    " CTBRP.DISAPPROVE_REASON, "+
                    " CTBHR.RECEIPT_DATE AS PAYMENT_DATE, "+
                    " NULL AS RECEIPT_NO "+
                    " FROM "+
                    " CON_TB_REFUND_REQUEST CTBRR "+
                    " LEFT JOIN CON_TR_REFUND_INSURER CTRRI ON ( CTRRI.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID ) "+
                    " LEFT JOIN CON_TB_REFUND_PERIOD  CTBRP ON ( CTBRP.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID ) "+
                    " AND ( CTBRP.STATUS != 'D' OR CTBRP.STATUS IS NULL ) "+
                    " LEFT JOIN CON_TB_HOLD_RECEIVE   CTBHR ON CTBHR.HOLD_RECEIVE_ID = CTBRP.HOLD_RECEIVE_ID "+
                    " LEFT JOIN CON_TB_REQUESTER39    CTBR39 ON CTBR39.PERIOD = CTBRP.REFUND_PERIOD "+
                    " AND CTBR39.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID "+
                    " JOIN CON_TB_REFUND_REQUEST_RECEIPT CTBRRR ON CTBRRR.REFUND_PERIOD_ID = CTBRP.REFUND_PERIOD_ID AND CTBRRR.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID AND CTBRRR.REQUESTER_ID = CTBR39.REQUESTER_ID "+
                    " WHERE "+
                    " 1 = 1 "+
                    " AND CTBRP.HOLD_RECEIVE_ID IS NOT NULL "+
                    " AND CTBRR.REFUND_REQUEST_ID = :refundRequestId ";
            param.put("refundRequestId", refundRequestId);

            return queryForList(sql, param, new RefundInsurerRequestRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
