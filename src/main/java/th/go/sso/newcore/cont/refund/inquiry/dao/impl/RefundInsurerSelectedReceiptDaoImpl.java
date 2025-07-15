package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiptInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundInsurerSelectedReceiptRowMapper;

@Repository
public class RefundInsurerSelectedReceiptDaoImpl extends DaoBase<Long> {


    public RefundInsurerSelectedReceiptDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<RefundReceiptInsurerBean> findReceiptReceiveInsurer(Long receiveInsurerId) throws Exception {

        try {
            Map<String, Object> param = new HashMap<>();
            String sql = "SELECT " +
                    " ctbr.receipt_no," +
                    " NULL AS receive_company_id," +
                    " ctrri.receive_insurer_id," +
                    " ctbr.payment_date," +
                    " nvl( ctbr.total_amount, 0 )    AS total_amount" +
                    " FROM" +
                    " con_tb_receipt             ctbr" +
                    " LEFT JOIN con_tb_receive_map_insurer ctbrmi ON ( ctbrmi.receipt_id = ctbr.receipt_id )" +
                    " LEFT JOIN con_tr_receive_insurer     ctrri ON ( ctrri.receive_insurer_id = ctbrmi.receive_insurer_id )" +
                    " WHERE 1 = 1" +
                    " AND ctrri.receive_insurer_id = :receiveInsurerId" +
                    " AND ctbr.receipt_status = 'A'" +
//                    " AND NOT EXISTS ( SELECT 1 FROM con_tb_refund_request_receipt ctbrrr WHERE ctbrrr.receipt_id = ctbr.receipt_id AND ctbrrr.STATUS = 'A' )" +
                    " ORDER BY" +
                    " ctbr.receipt_no ASC ";

            param.put("receiveInsurerId",receiveInsurerId);
            return queryForList(sql, param, new RefundInsurerSelectedReceiptRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    public List<RefundReceiptInsurerBean> findReceiptHoldReceive(Long holdReceiveId) throws Exception {

        try {
            Map<String, Object> param = new HashMap<>();
            String sql = " SELECT "+
                    " holrec.receipt_no, "+
                    " NULL AS receive_company_id, "+
                    " NULL AS receive_insurer_id, "+
                    " holrec.receipt_date AS payment_date, "+
                    " nvl( holrec.cntr_amount, 0 ) AS total_amount "+
                    " FROM "+
                    " con_tb_hold_receive holrec "+
                    " WHERE "+
                    " 1 = 1 "+
                    " AND holrec.hold_receive_id = :holdReceiveId "+
                    " ORDER BY "+
                    " holrec.receipt_no ASC ";

            param.put("holdReceiveId",holdReceiveId);

//            sql.append("SELECT recpt.receipt_no,");
//            sql.append(" null asa receive_company_id,");
//            sql.append(" null as receive_insurer_id,");
////            sql.append(" recpt.receive_company_id,");
////            sql.append(" recpt.receive_insurer_id,");
//            sql.append(" recpt.payment_date,");
//            sql.append(" nvl( recpt.total_amount, 0 ) AS total_amount");
//            sql.append(" FROM con_tb_receipt recpt");
//            sql.append(" LEFT JOIN con_tb_hold_receive holrec ON ( holrec.receipt_id = recpt.receipt_id )");
//            sql.append(" WHERE 1 = 1");
//
//
//            if (!NumberUtils.isZeroOrNull(holdReceiveId )) {
//                sql.append(" AND holrec.hold_receive_id = :holdReceiveId ");
//                param.put("holdReceiveId",holdReceiveId);
//            }
//            sql.append(" ORDER BY recpt.receipt_no ASC");
            return queryForList(sql, param, new RefundInsurerSelectedReceiptRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    public List<RefundReceiptInsurerBean> findReceiptReceiveEmployee(List<String> receiptIds) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT t1.receipt_no,");
            sql.append(" null as receive_company_id,");
            sql.append(" null as receive_insurer_id,");
//            sql.append(" t1.receive_company_id,");
//            sql.append(" t1.receive_insurer_id,");
            sql.append(" t1.payment_date,");
            sql.append(" nvl( t1.total_amount, 0 ) AS total_amount");
            sql.append(" FROM con_tb_receipt t1");
            sql.append(" WHERE 1 = 1");

            Map<String, Object> param = new HashMap<>();

            if (!CollectionUtils.isEmpty(receiptIds)) {
                sql.append(" AND t1.receipt_id IN (:receiptIds) ");
                param.put("receiptIds",receiptIds);
            }
            sql.append(" ORDER BY t1.receipt_id ASC");

            return queryForList(sql.toString(), param, new RefundInsurerSelectedReceiptRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    protected String getTableName() {
        return "CON_TB_RECEIPT";
    }

    @Override
    protected String getPkColumnName() {
        return "RECEIPT_ID";
    }
}
