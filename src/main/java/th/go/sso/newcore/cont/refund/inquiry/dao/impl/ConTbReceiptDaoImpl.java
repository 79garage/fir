package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;

@Repository
public class ConTbReceiptDaoImpl extends DaoBase<Long> {
    public ConTbReceiptDaoImpl(DataSource dataSource) {
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

    public BigDecimal findTotalAmountAndInterestPaid(Long receiveInsurerId) throws Exception {
        try {
            final Map<String, Object> param = new HashMap<>();
            String sql = """
                    SELECT
                     t0.total_amount + t0.interest_paid AS sum_total
                     FROM
                     ( SELECT
                        ctrri.cntr_amount,
                        nvl( ctbr.total_amount, 0 ) AS total_amount,
                        nvl( ctbr.interest_paid, 0 ) AS interest_paid
                     FROM
                        con_tb_receipt             ctbr
                        LEFT JOIN con_tb_receive_map_insurer ctbrmi ON ctbrmi.receipt_id = ctbr.receipt_id
                        LEFT JOIN con_tr_receive_insurer     ctrri ON ctrri.receive_insurer_id = ctbrmi.receive_insurer_id
                     WHERE 1 = 1
                        AND ctrri.receive_insurer_id = :receiveInsurerId
                        AND ctbrmi.status = 'A'
                        AND ctbrmi.receipt_1_st_id IS NULL
                     GROUP BY
                        ctrri.cntr_amount,
                        ctbr.total_amount,
                        ctbr.interest_paid
                     ) t0
                    """;
            param.put("receiveInsurerId", receiveInsurerId);
            return queryForObject(sql, param, BigDecimal.class);
        }catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public BigDecimal findTotalAmountByReceiveInsurerId(Long receiveInsurerId) throws Exception {
        try {
            final Map<String, Object> param = new HashMap<>();
            String sql = """
                    SELECT
                     t0.total_amount
                     FROM
                     ( SELECT
                        ctrri.cntr_amount,
                        nvl( ctbr.total_amount, 0 ) AS total_amount,
                        nvl( ctbr.interest_paid, 0 ) AS interest_paid
                     FROM
                        con_tb_receipt             ctbr
                        LEFT JOIN con_tb_receive_map_insurer ctbrmi ON ctbrmi.receipt_id = ctbr.receipt_id
                        LEFT JOIN con_tr_receive_insurer     ctrri ON ctrri.receive_insurer_id = ctbrmi.receive_insurer_id
                     WHERE 1 = 1
                        AND ctrri.receive_insurer_id = :receiveInsurerId
                        AND ctbrmi.status = 'A'
                        AND ctbrmi.receipt_1_st_id IS NULL
                     GROUP BY
                        ctrri.cntr_amount,
                        ctbr.total_amount,
                        ctbr.interest_paid
                     ) t0
                    """;
            param.put("receiveInsurerId", receiveInsurerId);
            return queryForObject(sql, param, BigDecimal.class);
        }catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public BigDecimal findInterestPaidByReceiveInsurerId(Long receiveInsurerId) throws Exception {
        try {
            final Map<String, Object> param = new HashMap<>();
            String sql = """
                    SELECT
                     t0.interest_paid
                     FROM
                     ( SELECT
                        ctrri.cntr_amount,
                        nvl( ctbr.total_amount, 0 ) AS total_amount,
                        nvl( ctbr.interest_paid, 0 ) AS interest_paid
                     FROM
                        con_tb_receipt             ctbr
                        LEFT JOIN con_tb_receive_map_insurer ctbrmi ON ctbrmi.receipt_id = ctbr.receipt_id
                        LEFT JOIN con_tr_receive_insurer     ctrri ON ctrri.receive_insurer_id = ctbrmi.receive_insurer_id
                     WHERE 1 = 1
                        AND ctrri.receive_insurer_id = :receiveInsurerId
                        AND ctbrmi.status = 'A'
                        AND ctbrmi.receipt_1_st_id IS NULL
                     GROUP BY
                        ctrri.cntr_amount,
                        ctbr.total_amount,
                        ctbr.interest_paid
                     ) t0
                    """;
            param.put("receiveInsurerId", receiveInsurerId);
            return queryForObject(sql, param, BigDecimal.class);
        }catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
