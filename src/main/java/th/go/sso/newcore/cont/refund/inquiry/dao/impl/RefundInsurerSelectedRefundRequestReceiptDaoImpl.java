package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundInsurerSelectedRefundRequestReceiptRowMapper;

@Repository
public class RefundInsurerSelectedRefundRequestReceiptDaoImpl extends DaoBase<Long> {


    public RefundInsurerSelectedRefundRequestReceiptDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<SelectedReceiptBean> findRefundRequestReceipt(Long refundPeriodId) throws Exception  {

        try {
            Map<String, Object> param = new HashMap<>();
            String sql = "SELECT" +
                    " ctbrrr.receipt_no," +
                    " NULL AS receive_company_id," +
                    " NULL AS receive_insurer_id," +
                    " ctbrrr.payment_date," +
                    " nvl( ctbrrr.total_amount, 0 )    AS total_amount" +
                    " FROM" +
                    " con_tb_refund_request_receipt ctbrrr" +
                    " WHERE 1 = 1" +
                    " AND ctbrrr.refund_period_id = :refundPeriodId" +
                    " AND ctbrrr.STATUS = 'A' " +
                    " ORDER BY" +
                    " ctbrrr.receipt_no ASC ";
            param.put("refundPeriodId",refundPeriodId);

            return queryForList(sql, param, new RefundInsurerSelectedRefundRequestReceiptRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
//    public List<SelectedReceiptBean> queryRefundRequestReceipt(Long receiveInsurerId) throws Exception  {
//
//        try {
//            Map<String, Object> param = new HashMap<>();
//            String sql = " SELECT "+
//                    " recpt.receipt_no, "+
//                    " NULL AS receive_company_id, "+
//                    " recins.receive_insurer_id, "+
//                    " recpt.payment_date, "+
//                    " nvl( recpt.total_amount, 0 ) AS total_amount "+
//                    " FROM "+
//                    " con_tb_receipt             recpt "+
//                    " LEFT JOIN con_tb_receive_map_insurer recmap ON ( recmap.receipt_id = recpt.receipt_id ) "+
//                    " LEFT JOIN con_tr_receive_insurer     recins ON ( recins.receive_insurer_id = recmap.receive_insurer_id ) "+
//                    " WHERE "+
//                    " EXISTS ( SELECT NULL FROM con_tb_refund_request_receipt refrecpt WHERE recpt.receipt_no = refrecpt.receipt_no ) "+
//                    " AND recins.receive_insurer_id = :receiveInsurerId "+
//                    " ORDER BY "+
//                    " recpt.receipt_no ASC ";
//
//            param.put("receiveInsurerId",receiveInsurerId);
//
//            return queryForList(sql, param, new RefundInsurerSelectedRefundRequestReceiptRowMapper());
//        } catch (EmptyResultDataAccessException ex) {
//            return null;
//        }
//    }
    public List<SelectedReceiptBean> findRefundRequestReceiptHoldReceive(Long holdReceiveId) throws Exception  {

        try {
            Map<String, Object> param = new HashMap<>();
            String sql = " SELECT "+
                    " HOLREC.RECEIPT_NO, "+
                    " NULL                   AS RECEIVE_COMPANY_ID, "+
                    " HOLREC.HOLD_RECEIVE_ID AS RECEIVE_INSURER_ID, "+
                    " HOLREC.RECEIPT_DATE    AS PAYMENT_DATE, "+
                    " NVL( HOLREC.CNTR_AMOUNT, 0 )                      AS TOTAL_AMOUNT "+
                    " FROM "+
                    " CON_TB_HOLD_RECEIVE HOLREC "+
                    " WHERE "+
                    " EXISTS ( SELECT RECEIPT_ID "+
                    " FROM CON_TB_REFUND_REQUEST_RECEIPT REFRECPT "+
                    " WHERE HOLREC.RECEIPT_NO = REFRECPT.RECEIPT_NO AND REFRECPT.STATUS = 'A' "+
                    " ) "+
                    " AND HOLREC.HOLD_RECEIVE_ID = :holdReceiveId "+
                    " ORDER BY "+
                    " HOLREC.RECEIPT_NO ASC ";

            param.put("holdReceiveId",holdReceiveId);
            return queryForList(sql, param, new RefundInsurerSelectedRefundRequestReceiptRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    public List<SelectedReceiptBean> findReceiptRefundRequestReceipt(List<String> receiptIds) throws Exception  {

        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT recpt.receipt_no,");
            sql.append(" null as receive_company_id,");
            sql.append(" null as receive_insurer_id,");
//            sql.append(" recpt.receive_company_id,");
//            sql.append(" recpt.receive_insurer_id,");
            sql.append(" recpt.payment_date,");
            sql.append(" nvl( recpt.total_amount, 0 ) AS total_amount");
            sql.append(" FROM con_tb_receipt recpt");
            sql.append(" WHERE");
            sql.append(" EXISTS (SELECT NULL FROM con_tb_refund_request_receipt refrecpt WHERE recpt.receipt_no = refrecpt.receipt_no)");

            Map<String, Object> param = new HashMap<>();

            if (!CollectionUtils.isEmpty(receiptIds)) {
                sql.append(" AND recpt.receipt_id in (:receiptIds) ");
                param.put("receiptIds",receiptIds);
            }
            sql.append(" ORDER BY recpt.receipt_no ASC");
            return queryForList(sql.toString(), param, new RefundInsurerSelectedRefundRequestReceiptRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "con_tb_receipt";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }
}
