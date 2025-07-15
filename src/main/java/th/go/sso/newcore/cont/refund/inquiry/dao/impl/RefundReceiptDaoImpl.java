package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundReceiptRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundRequestReceiptMapper;

@Repository
public class RefundReceiptDaoImpl extends DaoBase<Long> {


    public RefundReceiptDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_RECEIPT";
    }

    @Override
    protected String getPkColumnName() {
        return "RECEIPT_ID";
    }

    public List<RefundReceiptBean> searchRefundReceipt(Long receiveCompanyId) throws Exception {
        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT recpt.receipt_id,");
            sql.append(" remap.receive_company_id,");
//            sql.append(" recpt.receive_company_id,");
            sql.append(" recpt.payment_date,");
            sql.append(" nvl(recpt.total_amount, 0) AS total_amount,");
            sql.append(" recpt.receipt_no");
            sql.append(" FROM con_tb_receipt recpt");
            sql.append(" JOIN con_tb_receive_map_company remap ON (remap.receipt_id = recpt.receipt_id) ");
            sql.append(" WHERE 1 = 1");

            Map<String, Object> param = new HashMap<>();

            if (!NumberUtils.isZeroOrNull(receiveCompanyId)) {
                sql.append(" AND remap.receive_company_id = :receiveCompanyId");
                param.put("receiveCompanyId", receiveCompanyId);
            }

            return queryForList(sql.toString(), param, new RefundReceiptRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public List<RefundRequestReceiptBean> searchRefundReceiptInsurer(Long receiveInsurerId) throws Exception{
        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT recpt.receive_insurer_id,");
            sql.append(" recpt.receipt_no");
            sql.append(" FROM con_tb_receipt recpt");
            sql.append(" WHERE 1 = 1");

            Map<String, Object> param = new HashMap<>();

            if(!NumberUtils.isZeroOrNull(receiveInsurerId)){
                sql.append(" AND recpt.receive_insurer_id = :receiveInsurerId");
                param.put("receiveInsurerId", receiveInsurerId);
            }

            return queryForList(sql.toString(), param, new RefundRequestReceiptMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    public List<RefundReceiptBean> findReceipt(Long receiveCompanyId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = """
                SELECT
                    ctbr.receipt_no,
                    ctrrc.receive_company_id,
                    ctbr.payment_date,
                    nvl( ctbr.total_amount, 0 )    AS total_amount
                FROM
                    con_tb_receipt             ctbr
                    LEFT JOIN con_tb_receive_map_company ctbrmc ON ( ctbrmc.receipt_id = ctbr.receipt_id )
                    LEFT JOIN con_tr_receive_company     ctrrc ON ( ctrrc.receive_company_id = ctbrmc.receive_company_id )
                WHERE 1 = 1
                    AND ctrrc.receive_company_id = :receiveCompanyId
                    AND ctbr.receipt_status = 'A'
                    -- AND NOT EXISTS ( SELECT 1 FROM con_tb_refund_request_receipt ctbrrr WHERE ctbrrr.receipt_id = ctbr.receipt_id AND ctbrrr.status = 'A' )
                ORDER BY ctbr.receipt_no ASC
                """;
        param.put("receiveCompanyId", receiveCompanyId);
        return queryForList(sql, param, new RefundReceiptRowMapper());
    }
}
