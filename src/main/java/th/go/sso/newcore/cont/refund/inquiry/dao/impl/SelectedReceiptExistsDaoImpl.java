package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbReceiptExistsRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ReceiptExistsMultipleRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.SelectRefundRequestReceiptRowMapper;

@Repository
public class SelectedReceiptExistsDaoImpl extends DaoBase<Long> {

    public SelectedReceiptExistsDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<SelectedReceiptBean> searchReceiptExists(Long receiveCompanyId) throws Exception {

        try {

            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT  remap.receive_company_id,");
            sql.append(" recpt.payment_date,");
            sql.append(" nvl(recpt.total_amount, 0) AS total_amount,");
            sql.append(" recpt.receipt_no");
            sql.append(" FROM con_tb_receipt recpt");
            sql.append(" JOIN con_tb_receive_map_company remap ON (remap.receipt_id = recpt.receipt_id)");
            sql.append(" JOIN con_tb_refund_period refpe ON (refpe.receive_company_id = remap.receive_company_id)");
            sql.append(" WHERE");
            sql.append(" EXISTS ( SELECT NULL FROM con_tb_refund_request_receipt refrecpt  ");
            sql.append(" WHERE 1 = 1 AND recpt.receipt_id = refrecpt.receipt_id AND refpe.refund_period_id = refrecpt.refund_period_id ) AND ( refpe.status = 'A' OR refpe.status IS NULL ) ");

            Map<String, Object> param = new HashMap<>();

            if (!NumberUtils.isZeroOrNull(receiveCompanyId )) {
                sql.append(" AND remap.receive_company_id = :receiveCompanyId ");
                param.put("receiveCompanyId",receiveCompanyId);
            }

            sql.append(" ORDER BY remap.receive_company_id ASC");

            return queryForList(sql.toString(), param, new ConTbReceiptExistsRowMapper());

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    public List<SelectedReceiptBean> searchReceiptExistsMultiple(Long refundPeriodId) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();

            sql.append(" SELECT * FROM con_tb_refund_request_receipt ");
            sql.append(" WHERE 1 = 1 ");

            Map<String, Object> param = new HashMap<>();

            if (!NumberUtils.isZeroOrNull(refundPeriodId )) {
                sql.append(" AND refund_period_id = :refundPeriodId ");
                param.put("refundPeriodId",refundPeriodId);
            }

            sql.append(" ORDER BY receipt_id ASC");

            return queryForList(sql.toString(), param, new ReceiptExistsMultipleRowMapper());

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    public List<SelectedReceiptBean> queryRefundReceiptByRefundPeriodId(Long refundPeriodId) throws Exception {
        try {
            Map<String, Object> param = new HashMap<>();
            String sql = "SELECT " +
                    " CTBRP.RECEIVE_COMPANY_ID, CTBRP.RECEIVE_EMPLOYEE_ID, CTBRP.RECEIVE_INSURER_ID, " +
                    " CTBRRR.PAYMENT_DATE, " +
                    " NVL( CTBRRR.TOTAL_AMOUNT, 0 ) AS TOTAL_AMOUNT, " +
                    " CTBRRR.RECEIPT_NO " +
                    " FROM " +
                    " CON_TB_REFUND_REQUEST_RECEIPT CTBRRR " +
                    " JOIN CON_TB_REFUND_PERIOD       CTBRP ON ( CTBRP.REFUND_PERIOD_ID = CTBRRR.REFUND_PERIOD_ID ) " +
                    " WHERE 1 = 1 " +
                    " AND CTBRRR.REFUND_PERIOD_ID = :refundPeriodId " +
                    " ORDER BY CTBRRR.REFUND_REQUEST_RECEIPT_ID ASC ";

            param.put("refundPeriodId", refundPeriodId);
            return queryForList(sql, param, new SelectRefundRequestReceiptRowMapper());
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
