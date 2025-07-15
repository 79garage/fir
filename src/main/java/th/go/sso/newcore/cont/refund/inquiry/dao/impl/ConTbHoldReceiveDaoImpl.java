package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbHoldReceiveBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RateHoldReceiveBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInfoBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbHoldReceiveRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTrReceiveInsurerRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RateHoldReceiveRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ReceiveInsurerPeriodRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundInfoRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundInsurerInfoRowMapper;

@Repository
public class ConTbHoldReceiveDaoImpl extends DaoBase<Long> {
    public ConTbHoldReceiveDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_HOLD_RECEIVE";
    }

    @Override
    protected String getPkColumnName() {
        return "HOLD_RECEIVE_ID";
    }

    public RefundInsurerPeriodListBean findHoldReceivePeriod(String idCardNo, Long holdReceiveId) throws Exception {
        try {
            final Map<String, Object> param = new HashMap<>();
            String sql = " SELECT " +
                    " T1.PAY_PERIOD, "+
                    " NVL(T1.CNTR_AMOUNT, 0) AS CNTR_AMOUNT, "+
                    " NVL(T1.INTEREST_AMOUNT, 0) AS INTEREST_PAID, "+
                    " NVL(T1.CNTR_AMOUNT, 0) AS EMPE_OVERP, "+
                    " 0 AS OVER_INTEREST_PAID, "+
                    " T1.HOLD_RECEIVE_ID AS RECEIVE_INSURER_ID, "+
                    " T1.REFUND_REASON_CODE, "+
                    " NULL AS REFUND_AMOUNT, "+
                    " NULL AS REFUND_INTEREST_AMOUNT, "+
                    " 7 AS SECTION, "+
                    " T1.RECEIPT_DATE AS PAYMENT_DATE, "+
                    " NULL AS INSURER_ID "+
                    " FROM CON_TB_HOLD_RECEIVE T1 "+
                    " WHERE 1 = 1 ";
            if (!StringUtils.isEmpty(idCardNo)) {
                sql += " AND T1.ID_CARD_NO = :idCardNo ";
            }
            if (!NumberUtils.isZeroOrNull(holdReceiveId)) {
                sql += " AND T1.HOLD_RECEIVE_ID = :holdReceiveId ";
            }
            param.put("idCardNo", idCardNo);
            param.put("holdReceiveId", holdReceiveId);
            return queryForObject(sql, param, new ConTrReceiveInsurerRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public RefundInfoBean findRefundInfoDetail(String receiptNo, String idCardNo, String fullName) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = "SELECT RECEIPT_NAME AS FULL_NAME, ID_CARD_NO, SECTION, DEP_CODE FROM CON_TB_HOLD_RECEIVE WHERE SECTION = '1' ";
        if (!StringUtils.isEmpty(receiptNo)) {
            sql += " AND RECEIPT_NO = :receiptNo";
        }
        if (!StringUtils.isEmpty(idCardNo)) {
            sql += " AND ID_CARD_NO = :idCardNo";
        }
        if (!StringUtils.isEmpty(fullName)) {
            sql += " AND RECEIPT_NAME = :fullName";
        }
        param.put("receiptNo", receiptNo);
        param.put("idCardNo", idCardNo);
        param.put("fullName", fullName);
        return queryForObject(sql, param, new RefundInfoRowMapper());
    }

    public List<ConTbHoldReceiveBean> findHoldReceiveList(String receiptNo, String idCardNo, String fullName) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = "SELECT * FROM CON_TB_HOLD_RECEIVE WHERE SECTION = '1' ";
        if (!StringUtils.isEmpty(receiptNo)) {
            sql += " AND RECEIPT_NO = :receiptNo";
        }
        if (!StringUtils.isEmpty(idCardNo)) {
            sql += " AND ID_CARD_NO = :idCardNo";
        }
        if (!StringUtils.isEmpty(fullName)) {
            sql += " AND FULL_NAME = :fullName";
        }
        param.put("receiptNo", receiptNo);
        param.put("idCardNo", idCardNo);
        param.put("fullName", fullName);
        return queryForList(sql, param, new ConTbHoldReceiveRowMapper());
    }

    public RefundInsurerBean findHoldReceiveInfo(Long holdReceiveId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = "SELECT " +
                " ID_CARD_NO, " +
                " NULL AS INSURER_ID, " +
                " NULL AS TITLE_CODE, "+
                " NULL AS FIRST_NAME, "+
                " NULL AS LAST_NAME, "+
                " RECEIPT_NAME AS FULL_NAME, " +
                " NULL AS INSURER_STATUS, " +
                " NULL AS CREATE_BY, " +
                " NULL AS CREATE_DATE, " +
                " NULL AS ADDRESS, "+
                " NULL AS DIST_CODE, "+
                " NULL AS DIST_NAME, "+
                " NULL AS SUBDIST_CODE, "+
                " NULL AS SUBDIST_NAME, "+
                " NULL AS PROVINCE_CODE, "+
                " NULL AS PROVINCE_NAME, "+
                " NULL AS ZIP_CODE, "+
                " NULL AS TELEPHONE, "+
                " NULL AS MOO, "+
                " NULL AS SOI, "+
                " NULL AS ROAD, "+
                " NULL AS VILLAGE, "+
                " NULL AS MOBILE, "+
                " NULL AS EMAIL, "+
                " DEP_CODE, " +
                " SECTION " +
                " FROM CON_TB_HOLD_RECEIVE " +
                " WHERE SECTION = '1' AND HOLD_RECEIVE_ID = :holdReceiveId";
        param.put("holdReceiveId", holdReceiveId);
        return queryForObject(sql, param, new RefundInsurerInfoRowMapper());
    }

    public List<RefundInsurerPeriodListBean> findHoldReceivePeriodList(List<Long> holdReceiveId) throws Exception {
        try {
            Map<String, Object> param = new HashMap<>();
            String sql = " WITH HOLD_RECEIVE AS ( "+
                    " SELECT "+
                    " HOLD_RECEIVE_ID, "+
                    " PAY_PERIOD, "+
                    " PAY_PERIOD_MONTH, "+
                    " PAY_PERIOD_YEAR, "+
                    " REFUND_REASON_CODE, "+
                    " SECTION, "+
                    " RECEIPT_NO, "+
                    " RECEIPT_NAME, "+
                    " RECEIPT_DATE AS PAYMENT_DATE, "+
                    " ID_CARD_NO, "+
                    " CNTR_AMOUNT, "+
                    " INTEREST_AMOUNT AS INTEREST_PAID, "+
                    " CNTR_AMOUNT AS EMPE_OVERP, "+
                    " INTEREST_AMOUNT AS OVER_INTEREST_PAID, "+
                    " FLAG_STATUS || REASON_CODE AS REASON_CODE, "+
                    " SUBSTR( DEP_CODE, 0, 2 )                          AS PROVINCE_CODE, "+
                    " TO_DATE((PAY_PERIOD_YEAR - 543) || PAY_PERIOD_MONTH || '01', 'YYYYMMDD')       AS PERIOD_DATE "+
                    " FROM "+
                    " CON_TB_HOLD_RECEIVE "+
                    " WHERE "+
                    " 1 = 1 "+
                    " AND SECTION = '1' "+
                    " AND FLAG_STATUS = 'X' "+
                    " AND REASON_CODE = '1' "+
//                " AND RECEIPT_NO = :receiptNo "+
                    " AND HOLD_RECEIVE_ID IN (:holdReceiveId) "+
                    " ), CNTR_RATE AS ( "+
                    " SELECT "+
                    " I.START_DATE, "+
                    " I.END_DATE, "+
                    " E1.VALUE1                       AS TOTAL_WAGES, "+
                    " MAX(E3.VALUE1)                  AS SICKNESS_RATE, "+
                    " MAX(E2.VALUE1)                  AS RETIREMENT_RATE, "+
                    " MAX(E3.VALUE1) + MAX(E2.VALUE1) AS CNTR_RATE, "+
                    " ROUND( E1.VALUE1 *((MAX(E3.VALUE1) / 100) +(MAX(E2.VALUE1) / 100)), 0 )                               AS CAL_AMOUNT, "+
                    " I.IMFO_FLAG, "+
                    " I.CONFIG_RATE_GROUP, "+
                    " I.ACROSS_COUNTRY, "+
                    " CTAA.PROVINCE_CODE "+
                    " FROM "+
                    " CON_TB_INFORMATION      I "+
                    " LEFT JOIN HOLD_RECEIVE            HR ON I.START_DATE <= HR.PERIOD_DATE "+
                    " AND ( I.END_DATE IS NULL "+
                    " OR I.END_DATE >= HR.PERIOD_DATE ) "+
                    " LEFT JOIN CON_TB_INFORMATION      E1 ON E1.SUBJECT = 'TOTAL_WAGE' "+
                    " AND E1.GROUP_CODE = 'INSURER_WAGE' "+
                    " AND E1.SYS_CODE = I.SYS_CODE "+
                    " AND E1.START_DATE <= I.START_DATE "+
                    " LEFT JOIN CON_TB_INFORMATION      E2 ON E2.SUBJECT = 'RETIREMENT' "+
                    " AND E2.GROUP_CODE = 'INSURER_RATE' "+
                    " AND E2.SYS_CODE = I.SYS_CODE "+
                    " AND E2.START_DATE = I.START_DATE "+
                    " LEFT JOIN CON_TB_INFORMATION      E3 ON E3.SUBJECT = 'SICKNESS' "+
                    " AND E3.GROUP_CODE = 'INSURER_RATE' "+
                    " AND E3.SYS_CODE = I.SYS_CODE "+
                    " AND E3.START_DATE = I.START_DATE "+
                    " LEFT JOIN CON_TB_APPLICABLE_AREAS CTAA ON CTAA.CONFIG_RATE_GROUP = I.CONFIG_RATE_GROUP "+
                    " WHERE "+
                    " I.SYS_CODE = 'M39' "+
                    " AND I.GROUP_CODE = 'INSURER_RATE' "+
                    " AND I.STATUS = 'A' "+
                    " AND I.START_DATE <= HR.PERIOD_DATE "+
                    " AND ( I.END_DATE IS NULL "+
                    " OR I.END_DATE >= HR.PERIOD_DATE ) "+
                    " GROUP BY "+
                    " I.START_DATE, "+
                    " I.END_DATE, "+
                    " E1.VALUE1, "+
                    " I.IMFO_FLAG, "+
                    " I.CONFIG_RATE_GROUP, "+
                    " I.ACROSS_COUNTRY, "+
                    " CTAA.PROVINCE_CODE "+
                    " ), REFUND_RATE AS ( "+
                    " SELECT "+
                    " HR.HOLD_RECEIVE_ID, "+
                    " HR.RECEIPT_NO, "+
                    " HR.RECEIPT_NAME, "+
                    " HR.PAYMENT_DATE, "+
                    " HR.REFUND_REASON_CODE, "+
                    " HR.SECTION, "+
                    " HR.ID_CARD_NO, "+
                    " HR.PAY_PERIOD, "+
                    " HR.PAY_PERIOD_MONTH, "+
                    " HR.PAY_PERIOD_YEAR, "+
                    " HR.CNTR_AMOUNT, "+
                    " HR.INTEREST_PAID, "+
                    " HR.EMPE_OVERP, "+
                    " HR.OVER_INTEREST_PAID, "+
                    " HR.REASON_CODE, "+
                    " COALESCE( CNTR.CNTR_RATE, CNTR1.CNTR_RATE, 9 ) AS REFUND_CNTR_RATE, "+
                    " COALESCE( CNTR.SICKNESS_RATE, CNTR1.SICKNESS_RATE, '3' ) AS REFUND_SICKNESS_RATE, "+
                    " COALESCE( CNTR.RETIREMENT_RATE, CNTR1.RETIREMENT_RATE, '6' ) AS REFUND_RETIREMENT_RATE "+
                    " FROM "+
                    " HOLD_RECEIVE HR "+
                    " LEFT JOIN CNTR_RATE    CNTR ON CNTR.START_DATE <= HR.PERIOD_DATE AND ( CNTR.END_DATE IS NULL OR CNTR.END_DATE >= HR.PERIOD_DATE ) "+
                    " AND CNTR.PROVINCE_CODE = HR.PROVINCE_CODE AND CNTR.ACROSS_COUNTRY = '1' AND HR.CNTR_AMOUNT = CNTR.CAL_AMOUNT "+
                    " LEFT JOIN CNTR_RATE    CNTR1 ON CNTR1.START_DATE <= HR.PERIOD_DATE AND ( CNTR1.END_DATE IS NULL OR CNTR1.END_DATE >= HR.PERIOD_DATE ) "+
                    " AND CNTR1.ACROSS_COUNTRY = '0' AND HR.CNTR_AMOUNT = CNTR1.CAL_AMOUNT "+
                    " ) "+
                    " SELECT "+
                    " HOLD_RECEIVE_ID AS RECEIVE_INSURER_ID, "+
                    " RECEIPT_NO, "+
                    " RECEIPT_NAME AS FULL_NAME, "+
                    " PAYMENT_DATE, "+
                    " REFUND_REASON_CODE, "+
                    " SECTION, "+
                    " ID_CARD_NO, "+
                    " PAY_PERIOD, "+
                    " PAY_PERIOD_MONTH, "+
                    " PAY_PERIOD_YEAR, "+
                    " NULL AS INSURER_ID, "+
                    " NULL AS REFUND_AMOUNT, "+
                    " NULL AS REFUND_INTEREST_AMOUNT, "+
                    " CNTR_AMOUNT, "+
                    " INTEREST_PAID, "+
                    " EMPE_OVERP, "+
                    " OVER_INTEREST_PAID, "+
                    " REASON_CODE, "+
                    " REFUND_CNTR_RATE AS REAL_CNTR_RATE, "+
                    " REFUND_SICKNESS_RATE AS REAL_SICKNESS_RATE, "+
                    " REFUND_RETIREMENT_RATE AS REAL_RETIREMENT_RATE, "+
                    " ROUND( CNTR_AMOUNT *(REFUND_SICKNESS_RATE / REFUND_CNTR_RATE), 2 ) AS REFUND_SICKNESS, "+
                    " ROUND( CNTR_AMOUNT *(REFUND_RETIREMENT_RATE / REFUND_CNTR_RATE), 2 ) AS REFUND_RETIREMENT "+
                    " FROM "+
                    " REFUND_RATE ";
            param.put("holdReceiveId", holdReceiveId);
            return queryForList(sql, param, new ReceiveInsurerPeriodRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public RateHoldReceiveBean findRateHoldReceive(Long holdReceiveId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " WITH HOLD_RECEIVE AS ( "+
                " SELECT "+
                " HOLD_RECEIVE_ID, "+
                " PAY_PERIOD, "+
                " PAY_PERIOD_MONTH, "+
                " PAY_PERIOD_YEAR, "+
                " REFUND_REASON_CODE, "+
                " SECTION, "+
                " RECEIPT_NO, "+
                " RECEIPT_NAME, "+
                " RECEIPT_DATE AS PAYMENT_DATE, "+
                " ID_CARD_NO, "+
                " CNTR_AMOUNT, "+
                " INTEREST_AMOUNT AS INTEREST_PAID, "+
                " CNTR_AMOUNT AS EMPE_OVERP, "+
                " INTEREST_AMOUNT AS OVER_INTEREST_PAID, "+
                " FLAG_STATUS || REASON_CODE AS REASON_CODE, "+
                " SUBSTR( DEP_CODE, 0, 2 )                          AS PROVINCE_CODE, "+
                " TO_DATE((PAY_PERIOD_YEAR - 543) || PAY_PERIOD_MONTH || '01', 'YYYYMMDD')       AS PERIOD_DATE "+
                " FROM "+
                " CON_TB_HOLD_RECEIVE "+
                " WHERE "+
                " 1 = 1 "+
                " AND SECTION = '1' "+
                " AND FLAG_STATUS = 'X' "+
                " AND REASON_CODE = '1' "+
//                " AND RECEIPT_NO = :receiptNo "+
                " AND HOLD_RECEIVE_ID = :holdReceiveId "+
                " ), CNTR_RATE AS ( "+
                " SELECT "+
                " I.START_DATE, "+
                " I.END_DATE, "+
                " E1.VALUE1                       AS TOTAL_WAGES, "+
                " MAX(E3.VALUE1)                  AS SICKNESS_RATE, "+
                " MAX(E2.VALUE1)                  AS RETIREMENT_RATE, "+
                " MAX(E3.VALUE1) + MAX(E2.VALUE1) AS CNTR_RATE, "+
                " ROUND( E1.VALUE1 *((MAX(E3.VALUE1) / 100) +(MAX(E2.VALUE1) / 100)), 0 )                               AS CAL_AMOUNT, "+
                " I.IMFO_FLAG, "+
                " I.CONFIG_RATE_GROUP, "+
                " I.ACROSS_COUNTRY, "+
                " CTAA.PROVINCE_CODE "+
                " FROM "+
                " CON_TB_INFORMATION      I "+
                " LEFT JOIN HOLD_RECEIVE            HR ON I.START_DATE <= HR.PERIOD_DATE "+
                " AND ( I.END_DATE IS NULL "+
                " OR I.END_DATE >= HR.PERIOD_DATE ) "+
                " LEFT JOIN CON_TB_INFORMATION      E1 ON E1.SUBJECT = 'TOTAL_WAGE' "+
                " AND E1.GROUP_CODE = 'INSURER_WAGE' "+
                " AND E1.SYS_CODE = I.SYS_CODE "+
                " AND E1.START_DATE <= I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION      E2 ON E2.SUBJECT = 'RETIREMENT' "+
                " AND E2.GROUP_CODE = 'INSURER_RATE' "+
                " AND E2.SYS_CODE = I.SYS_CODE "+
                " AND E2.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION      E3 ON E3.SUBJECT = 'SICKNESS' "+
                " AND E3.GROUP_CODE = 'INSURER_RATE' "+
                " AND E3.SYS_CODE = I.SYS_CODE "+
                " AND E3.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_APPLICABLE_AREAS CTAA ON CTAA.CONFIG_RATE_GROUP = I.CONFIG_RATE_GROUP "+
                " WHERE "+
                " I.SYS_CODE = 'M39' "+
                " AND I.GROUP_CODE = 'INSURER_RATE' "+
                " AND I.STATUS = 'A' "+
                " AND I.START_DATE <= HR.PERIOD_DATE "+
                " AND ( I.END_DATE IS NULL "+
                " OR I.END_DATE >= HR.PERIOD_DATE ) "+
                " GROUP BY "+
                " I.START_DATE, "+
                " I.END_DATE, "+
                " E1.VALUE1, "+
                " I.IMFO_FLAG, "+
                " I.CONFIG_RATE_GROUP, "+
                " I.ACROSS_COUNTRY, "+
                " CTAA.PROVINCE_CODE "+
                " ), REFUND_RATE AS ( "+
                " SELECT "+
                " HR.HOLD_RECEIVE_ID, "+
                " HR.RECEIPT_NO, "+
                " HR.RECEIPT_NAME, "+
                " HR.PAYMENT_DATE, "+
                " HR.REFUND_REASON_CODE, "+
                " HR.SECTION, "+
                " HR.ID_CARD_NO, "+
                " HR.PAY_PERIOD, "+
                " HR.PAY_PERIOD_MONTH, "+
                " HR.PAY_PERIOD_YEAR, "+
                " HR.CNTR_AMOUNT, "+
                " HR.INTEREST_PAID, "+
                " HR.EMPE_OVERP, "+
                " HR.OVER_INTEREST_PAID, "+
                " HR.REASON_CODE, "+
                " COALESCE( CNTR.CNTR_RATE, CNTR1.CNTR_RATE, 9 ) AS REFUND_CNTR_RATE, "+
                " COALESCE( CNTR.SICKNESS_RATE, CNTR1.SICKNESS_RATE, '3' ) AS REFUND_SICKNESS_RATE, "+
                " COALESCE( CNTR.RETIREMENT_RATE, CNTR1.RETIREMENT_RATE, '6' ) AS REFUND_RETIREMENT_RATE "+
                " FROM "+
                " HOLD_RECEIVE HR "+
                " LEFT JOIN CNTR_RATE    CNTR ON CNTR.START_DATE <= HR.PERIOD_DATE AND ( CNTR.END_DATE IS NULL OR CNTR.END_DATE >= HR.PERIOD_DATE ) "+
                " AND CNTR.PROVINCE_CODE = HR.PROVINCE_CODE AND CNTR.ACROSS_COUNTRY = '1' AND HR.CNTR_AMOUNT = CNTR.CAL_AMOUNT "+
                " LEFT JOIN CNTR_RATE    CNTR1 ON CNTR1.START_DATE <= HR.PERIOD_DATE AND ( CNTR1.END_DATE IS NULL OR CNTR1.END_DATE >= HR.PERIOD_DATE ) "+
                " AND CNTR1.ACROSS_COUNTRY = '0' AND HR.CNTR_AMOUNT = CNTR1.CAL_AMOUNT "+
                " ) "+
                " SELECT "+
                " HOLD_RECEIVE_ID AS RECEIVE_INSURER_ID, "+
                " RECEIPT_NO, "+
                " RECEIPT_NAME, "+
                " PAYMENT_DATE, "+
                " REFUND_REASON_CODE, "+
                " SECTION, "+
                " ID_CARD_NO, "+
                " PAY_PERIOD, "+
                " PAY_PERIOD_MONTH, "+
                " PAY_PERIOD_YEAR, "+
                " NULL AS INSURER_ID, "+
                " NULL AS REFUND_AMOUNT, "+
                " NULL AS REFUND_INTEREST_AMOUNT, "+
                " CNTR_AMOUNT, "+
                " INTEREST_PAID, "+
                " EMPE_OVERP, "+
                " OVER_INTEREST_PAID, "+
                " REASON_CODE, "+
                " REFUND_CNTR_RATE, "+
                " REFUND_SICKNESS_RATE, "+
                " REFUND_RETIREMENT_RATE, "+
                " ROUND( CNTR_AMOUNT *(REFUND_SICKNESS_RATE / REFUND_CNTR_RATE), 2 ) AS REFUND_SICKNESS, "+
                " ROUND( CNTR_AMOUNT *(REFUND_RETIREMENT_RATE / REFUND_CNTR_RATE), 2 ) AS REFUND_RETIREMENT "+
                " FROM "+
                " REFUND_RATE ";
        param.put("holdReceiveId", holdReceiveId);
        return queryForObject(sql, param, new RateHoldReceiveRowMapper());
    }
}
