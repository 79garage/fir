package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerPeriodListBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTrReceiveInsurerRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ReceiveInsurerPeriodRowMapper;

@Repository
public class ConTrReceiveInsurerDaoImpl extends DaoBase<Long> {

    public ConTrReceiveInsurerDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TR_RECEIVE_INSURER";
    }

    @Override
    protected String getPkColumnName() {
        return "RECEIVE_INSURER_ID";
    }

    //    public List<RefundInsurerPeriodListBean> searchRefundInsurerPeriod(int insurerId, List<Integer> receiveInsurerId) throws Exception{
//
//        try {
//
//            final StringBuilder sql = new StringBuilder();
//
//            sql.append("SELECT t1.pay_period,");
//            sql.append(" nvl(t1.cntr_amount, 0) AS cntr_amount,");
//            sql.append(" nvl(t1.interest_paid, 0) AS interest_paid,");
//            sql.append(" nvl(t1.empe_overp, 0) AS empe_overp,");
//            sql.append(" nvl(t1.over_interest_paid, 0) AS over_interest_paid,");
//            sql.append(" t1.receive_insurer_id");
//            sql.append(" FROM con_tr_receive_insurer t1");
//            sql.append(" WHERE 1 = 1");
//
//            Map<String, Object> param = new HashMap<>();
//
//            if(!NumberUtils.isZeroOrNull(insurerId)){
//                sql.append(" AND t1.insurer_id = :insurerId");
//                param.put("insurerId", insurerId);
//            }
//
//            if(!receiveInsurerId.isEmpty()){
//                sql.append(" AND t1.receive_insurer_id IN ( :receiveInsurerId )");
//                param.put("receiveInsurerId", receiveInsurerId);
//            }
//
//            sql.append(" ORDER BY t1.pay_period ASC");
//
//            return queryForList(sql.toString(), param, new ConTrReceiveInsurerRowMapper());
//
//        } catch (EmptyResultDataAccessException ex) {
//            return null;
//        }
//    }
    public RefundInsurerPeriodListBean findReceiveInsurerPeriod(String idCardNo, Long receiveInsurerId) throws Exception {
        try {
            final Map<String, Object> param = new HashMap<>();
            String sql = " SELECT "+
                    " CTRRI.PAY_PERIOD, "+
                    " NVL( CTRRI.CNTR_AMOUNT, 0 ) AS CNTR_AMOUNT, "+
                    " NVL( CTRRI.INTEREST_PAID, 0 ) AS INTEREST_PAID, "+
                    " NVL( CTRRI.EMPE_OVERP, 0 ) AS EMPE_OVERP, "+
                    " NVL( CTRRI.OVER_INTEREST_PAID, 0 ) AS OVER_INTEREST_PAID, "+
                    " CTRRI.RECEIVE_INSURER_ID, "+
                    " CTRRI.REFUND_REASON_CODE, "+
                    " NVL( CTRRI.REFUND_AMOUNT, 0 ) AS REFUND_AMOUNT, "+
                    " NVL( CTRRI.REFUND_INTEREST_AMOUNT, 0 ) AS REFUND_INTEREST_AMOUNT, "+
                    " 1 AS SECTION, "+
                    " CTRRI.PAY_DATE AS PAYMENT_DATE, "+
                    " CTRRI.INSURER_ID "+
                    " FROM CON_TR_RECEIVE_INSURER CTRRI "+
                    " WHERE 1 = 1 "+
                    " AND CTRRI.RECEIVE_INSURER_ID = :receiveInsurerId ";
            param.put("receiveInsurerId", receiveInsurerId);

            return queryForObject(sql, param, new ConTrReceiveInsurerRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public RefundInsurerPeriodListBean findReceiveInsurerContributionPeriod(Long receiveInsurerId) throws Exception {
        try {
            final Map<String, Object> param = new HashMap<>();
            String sql = " SELECT "+
                    " CTRRI.PAY_PERIOD, "+
                    " NVL( CTRRI.CNTR_AMOUNT, 0 ) AS CNTR_AMOUNT, "+
                    " NVL( CTRRI.INTEREST_PAID, 0 ) AS INTEREST_PAID, "+
                    " NVL( CTRRI.EMPE_OVERP, 0 ) AS EMPE_OVERP, "+
                    " NVL( CTRRI.OVER_INTEREST_PAID, 0 ) AS OVER_INTEREST_PAID, "+
                    " CTRRI.RECEIVE_INSURER_ID, "+
                    " CTRRI.REFUND_REASON_CODE, "+
                    " NVL( CTRRI.REFUND_AMOUNT, 0 ) AS REFUND_AMOUNT, "+
                    " NVL( CTRRI.REFUND_INTEREST_AMOUNT, 0 ) AS REFUND_INTEREST_AMOUNT, "+
                    " 1 AS SECTION, "+
                    " CTRRI.PAY_DATE AS PAYMENT_DATE, "+
                    " CTRRI.INSURER_ID "+
                    " FROM CON_TR_RECEIVE_INSURER CTRRI "+
                    " WHERE 1 = 1 "+
                    " AND CTRRI.RECEIVE_INSURER_ID = :receiveInsurerId ";
            param.put("receiveInsurerId", receiveInsurerId);
            return queryForObject(sql, param, new ConTrReceiveInsurerRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public RefundInsurerPeriodListBean findReceiveInsurerPeriods(Long receiveInsurerId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " WITH OVER_PAID AS ( "+
                " SELECT "+
                " TR.RECEIVE_INSURER_ID, "+
                " TR.INSURER_ID, "+
                " TR.PAY_PERIOD, "+
                " TR.PAY_PERIOD_MONTH, "+
                " TR.PAY_PERIOD_YEAR, "+
                " TR.REFUND_REASON_CODE, "+
                " EMP.SECTION, "+
                " TR.PAY_DATE AS PAYMENT_DATE, "+
                " TR.TOTAL_WAGES, "+
                " TR.CNTR_AMOUNT, "+
                " TR.INTEREST_PAID, "+
                " CASE "+
                " WHEN TR.REFUND_REASON_CODE = '06' OR TR.REFUND_REASON_CODE = '09' "+
                " THEN NVL(TR.EMPE_OVERP, 0) - NVL(REFUND_AMOUNT, 0) "+
                " WHEN TR.REFUND_REASON_CODE != '06' OR TR.REFUND_REASON_CODE != '09' "+
                " THEN NVL(TR.CNTR_AMOUNT, 0) - NVL(REFUND_AMOUNT, 0) "+
                " END AS CAL_EMPE_OVERP, "+
                " CASE "+
                " WHEN TR.REFUND_REASON_CODE = '06' OR TR.REFUND_REASON_CODE = '09' "+
                " THEN NVL(TR.EMPE_OVERP, 0) "+
                " WHEN TR.REFUND_REASON_CODE != '06' OR TR.REFUND_REASON_CODE != '09' "+
                " THEN NVL(TR.CNTR_AMOUNT, 0) "+
                " END AS EMPE_OVERP, "+
                " CASE "+
                " WHEN TR.REFUND_REASON_CODE = '06' OR TR.REFUND_REASON_CODE = '09' "+
                " THEN NVL(TR.OVER_INTEREST_PAID, 0) - NVL(REFUND_INTEREST_AMOUNT, 0) "+
                " WHEN TR.REFUND_REASON_CODE != '06' OR TR.REFUND_REASON_CODE != '09' "+
                " THEN NVL(TR.INTEREST_PAID, 0) - NVL(REFUND_INTEREST_AMOUNT, 0) "+
                " END AS CAL_OVER_INTEREST_PAID, "+
                " CASE "+
                " WHEN TR.REFUND_REASON_CODE = '06' OR TR.REFUND_REASON_CODE = '09' "+
                " THEN NVL(TR.OVER_INTEREST_PAID, 0) "+
                " WHEN TR.REFUND_REASON_CODE != '06' OR TR.REFUND_REASON_CODE != '09' "+
                " THEN NVL(TR.INTEREST_AMOUNT, 0) "+
                " END AS OVER_INTEREST_PAID, "+
                " TR.CNTR_SICKNESS, "+
                " TR.CNTR_RETIREMENT, "+
                " TR.REFUND_AMOUNT, "+
                " TR.REFUND_INTEREST_AMOUNT, "+
                " SUBSTR(EMP.DEP_CODE, 0, 2) AS PROVINCE_CODE, "+
                " TO_DATE((TR.PAY_PERIOD_YEAR - 543) || TR.PAY_PERIOD_MONTH || '01', 'YYYYMMDD')       AS PERIOD_DATE "+
                " FROM CON_TR_RECEIVE_INSURER TR "+
                " JOIN CON_TB_EMPLOYMENT EMP ON "+
                " TR.INSURER_ID = EMP.INSURER_ID "+
                " AND EMP.SECTION = '1' "+
                " AND ((TO_DATE(TR.PAY_PERIOD,'MM-YYYY', 'NLS_CALENDAR=''THAI BUDDHA') BETWEEN EMP.START_DATE AND COALESCE(EMP.RESIGN_DATE, SYSDATE) AND TR.REFUND_REASON_CODE <> '07' AND EMP.EMP_STATUS <> 'D' ) "+
                " OR (TO_DATE(TR.PAY_PERIOD,'MM-YYYY', 'NLS_CALENDAR=''THAI BUDDHA')  > EMP.RESIGN_DATE AND TR.REFUND_REASON_CODE = '07' AND EMP.EMP_STATUS = 'D' )) "+
                " WHERE "+
                " 1 = 1 "+
                " AND TR.RECEIVE_INSURER_ID IN ( :receiveInsurerId ) "+
                " AND TR.REFUND_REASON_CODE IS NOT NULL "+
                " ), "+
                " CNTR_RATE AS ( "+
                " SELECT "+
                " TRUNC(I.START_DATE)             AS START_DATE, "+
                " TRUNC(I.END_DATE)               AS END_DATE, "+
                " MAX(E3.VALUE1) + MAX(E2.VALUE1) AS CNTR_RATE, "+
                " MAX(E3.VALUE1)                  AS SICKNESS_RATE, "+
                " MAX(E2.VALUE1)                  AS RETIREMENT_RATE, "+
                " MAX(E4.VALUE1)                  AS DECIMAL_SIZE, "+
                " I.IMFO_FLAG, "+
                " I.CONFIG_RATE_GROUP, "+
                " I.ACROSS_COUNTRY, "+
                " CTAA.PROVINCE_CODE "+
                " FROM CON_TB_INFORMATION I "+
                " LEFT JOIN OVER_PAID OV ON I.START_DATE <= OV.PERIOD_DATE AND ( I.END_DATE IS NULL OR I.END_DATE >= OV.PERIOD_DATE ) "+
                " LEFT JOIN CON_TB_INFORMATION E2 ON E2.SUBJECT = 'RETIREMENT' AND E2.GROUP_CODE = 'INSURER_RATE' AND E2.SYS_CODE = I.SYS_CODE AND E2.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION E3 ON E3.SUBJECT = 'SICKNESS' AND E3.GROUP_CODE = 'INSURER_RATE' AND E3.SYS_CODE = I.SYS_CODE AND E3.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION E4 ON E4.SUBJECT = 'DECIMAL_SIZE' AND E4.GROUP_CODE = 'SEPARATE_ROUNDING_RATE' AND E4.SYS_CODE = I.SYS_CODE AND E4.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_APPLICABLE_AREAS CTAA ON CTAA.CONFIG_RATE_GROUP = I.CONFIG_RATE_GROUP "+
                " WHERE "+
                " I.SYS_CODE = 'M39' "+
                " AND I.GROUP_CODE = 'INSURER_RATE' "+
                " AND I.STATUS = 'A' "+
                " AND I.START_DATE <= OV.PERIOD_DATE "+
                " AND ( I.END_DATE IS NULL OR I.END_DATE >= OV.PERIOD_DATE ) "+
                " GROUP BY "+
                " TRUNC(I.START_DATE), "+
                " TRUNC(I.END_DATE), "+
                " I.IMFO_FLAG, "+
                " I.CONFIG_RATE_GROUP, "+
                " I.ACROSS_COUNTRY, "+
                " CTAA.PROVINCE_CODE "+
                " ), "+
                " REFUND_RATE AS ( "+
                " SELECT "+
                " OV.RECEIVE_INSURER_ID, "+
                " OV.INSURER_ID, "+
                " OV.PAY_PERIOD, "+
                " OV.PAY_PERIOD_MONTH, "+
                " OV.PAY_PERIOD_YEAR, "+
                " OV.REFUND_REASON_CODE, "+
                " OV.SECTION, "+
                " OV.PAYMENT_DATE, "+
                " OV.PROVINCE_CODE, "+
                " OV.TOTAL_WAGES, "+
                " OV.CNTR_AMOUNT, "+
                " OV.INTEREST_PAID, "+
                " OV.EMPE_OVERP, "+
                " OV.CAL_EMPE_OVERP, "+
                " OV.OVER_INTEREST_PAID, "+
                " OV.CAL_OVER_INTEREST_PAID, "+
                " OV.REFUND_AMOUNT, "+
                " OV.REFUND_INTEREST_AMOUNT, "+
                " OV.CNTR_SICKNESS, "+
                " OV.CNTR_RETIREMENT, "+
                " NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) AS IMFO_FLAG, "+
                " NVL(CASE "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' "+
                " THEN CNTR.DECIMAL_SIZE "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N' "+
                " THEN CNTR1.DECIMAL_SIZE "+
                " END, 2) AS DECIMAL_SIZE, "+
                " CASE "+
                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09' "+
                " THEN CASE "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' "+
                " THEN NVL(TO_NUMBER(CNTR.CNTR_RATE), TO_NUMBER(CNTR1.CNTR_RATE)) "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N' "+
                " THEN TO_NUMBER(CNTR1.CNTR_RATE) "+
                " END "+
                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' "+
                " THEN TO_NUMBER(CNTR1.CNTR_RATE) "+
                " END AS REAL_CNTR_RATE, "+
                " CASE "+
                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09' "+
                " THEN CASE "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' "+
                " THEN NVL(TO_NUMBER(CNTR.SICKNESS_RATE), TO_NUMBER(CNTR1.SICKNESS_RATE)) "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N' "+
                " THEN TO_NUMBER(CNTR1.SICKNESS_RATE) "+
                " END "+
                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' "+
                " THEN TO_NUMBER(CNTR1.SICKNESS_RATE) "+
                " END AS REAL_SICKNESS_RATE, "+
                " CASE "+
                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09' "+
                " THEN CASE "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' "+
                " THEN NVL(TO_NUMBER(CNTR.RETIREMENT_RATE), TO_NUMBER(CNTR1.RETIREMENT_RATE)) "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N' "+
                " THEN TO_NUMBER(CNTR1.RETIREMENT_RATE) "+
                " END "+
                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' "+
                " THEN TO_NUMBER(CNTR1.RETIREMENT_RATE) "+
                " END AS REAL_RETIREMENT_RATE, "+
                " CASE "+
                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09' "+
                " THEN CASE "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' "+
                " THEN 9 - NVL(TO_NUMBER(CNTR.CNTR_RATE), TO_NUMBER(CNTR1.CNTR_RATE)) "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N' "+
                " THEN 3 "+
                " END "+
                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' "+
                " THEN TO_NUMBER(CNTR1.CNTR_RATE) "+
                " END AS REFUND_CNTR_RATE, "+
                " CASE "+
                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09' "+
                " THEN CASE "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' "+
                " THEN 3 - NVL(TO_NUMBER(CNTR.SICKNESS_RATE), TO_NUMBER(CNTR1.SICKNESS_RATE)) "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N' "+
                " THEN TO_NUMBER(CNTR1.SICKNESS_RATE) "+
                " END "+
                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' "+
                " THEN CASE "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' "+
                " THEN 3 - NVL(TO_NUMBER(CNTR.SICKNESS_RATE), TO_NUMBER(CNTR1.SICKNESS_RATE)) "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N' "+
                " THEN TO_NUMBER(CNTR1.SICKNESS_RATE) "+
                " END "+
                " END AS REFUND_SICKNESS_RATE, "+
                " CASE "+
                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09' "+
                " THEN CASE "+
                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' "+
                " THEN 6 - NVL(TO_NUMBER(CNTR.RETIREMENT_RATE), TO_NUMBER(CNTR1.RETIREMENT_RATE)) "+
                " ELSE 0 "+
                " END "+
                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' "+
                " THEN TO_NUMBER(CNTR1.RETIREMENT_RATE) "+
                " END AS REFUND_RETIREMENT_RATE "+
                " FROM "+
                " OVER_PAID OV "+
                " LEFT JOIN CNTR_RATE CNTR ON CNTR.START_DATE <= OV.PERIOD_DATE "+
                " AND ( CNTR.END_DATE IS NULL OR CNTR.END_DATE >= OV.PERIOD_DATE ) "+
                " AND CNTR.PROVINCE_CODE = OV.PROVINCE_CODE "+
                " AND CNTR.ACROSS_COUNTRY = '1' "+
                " LEFT JOIN CNTR_RATE CNTR1 ON CNTR1.START_DATE <= OV.PERIOD_DATE "+
                " AND ( CNTR1.END_DATE IS NULL OR CNTR1.END_DATE >= OV.PERIOD_DATE ) "+
                " AND CNTR1.ACROSS_COUNTRY = '0' "+
                " ) "+
                " SELECT "+
                " RECEIVE_INSURER_ID, "+
                " DECIMAL_SIZE, "+
                " IMFO_FLAG, "+
                " NULL AS FULL_NAME, "+
                " NULL AS ID_CARD_NO, "+
                " INSURER_ID, "+
                " PAY_PERIOD, "+
                " PAY_PERIOD_MONTH, "+
                " PAY_PERIOD_YEAR, "+
                " REFUND_REASON_CODE, "+
                " SECTION, "+
                " PAYMENT_DATE, "+
                " PROVINCE_CODE, "+
                " CNTR_AMOUNT, "+
                " INTEREST_PAID, "+
                " EMPE_OVERP, "+
                " CAL_EMPE_OVERP, "+
                " OVER_INTEREST_PAID, "+
                " CAL_OVER_INTEREST_PAID, "+
                " REFUND_AMOUNT, "+
                " REFUND_INTEREST_AMOUNT, "+
                " REAL_CNTR_RATE, "+
                " REAL_SICKNESS_RATE, "+
                " REAL_RETIREMENT_RATE, "+
                " CNTR_AMOUNT - ROUND(CNTR_RETIREMENT, DECIMAL_SIZE)                                                                       AS CNTR_SICKNESS, "+
                " ROUND(CNTR_RETIREMENT, DECIMAL_SIZE)                                                                                     AS CNTR_RETIREMENT, "+
                " ROUND(TOTAL_WAGES *(REAL_CNTR_RATE / 100), 0)                                                       AS CAL_CNTR_AMOUNT, "+
                " ROUND(TOTAL_WAGES *(REAL_CNTR_RATE / 100), 0) - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), DECIMAL_SIZE) AS CAL_CNTR_SICKNESS, "+
                " ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), DECIMAL_SIZE)                                                 AS CAL_CNTR_RETIREMENT, "+
                " CASE "+
                " WHEN REFUND_REASON_CODE = '06' "+
                " THEN ( CNTR_AMOUNT - ROUND(CNTR_RETIREMENT, DECIMAL_SIZE) ) - ( ROUND(TOTAL_WAGES * (REAL_CNTR_RATE / 100), 0) - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100 ), DECIMAL_SIZE) ) "+
                " WHEN REFUND_REASON_CODE = '09' "+
                " THEN ( CNTR_AMOUNT - ROUND(CNTR_RETIREMENT, DECIMAL_SIZE) ) - ( ROUND(TOTAL_WAGES * (REAL_CNTR_RATE / 100), 0) - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100 ), DECIMAL_SIZE) ) "+
                " WHEN REFUND_REASON_CODE IS NOT NULL AND REFUND_REASON_CODE != '06' OR REFUND_REASON_CODE != '09' "+
                " THEN CNTR_SICKNESS "+
                " END AS REFUND_SICKNESS, "+
                " CASE "+
                " WHEN REFUND_REASON_CODE = '06' "+
                " THEN ROUND(CNTR_RETIREMENT - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), DECIMAL_SIZE), 0) "+
                " WHEN REFUND_REASON_CODE = '09' "+
                " THEN ROUND(CNTR_RETIREMENT - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), DECIMAL_SIZE), 0) "+
                " WHEN REFUND_REASON_CODE IS NOT NULL AND REFUND_REASON_CODE != '06' OR REFUND_REASON_CODE != '09' "+
                " THEN CNTR_RETIREMENT "+
                " END AS REFUND_RETIREMENT "+
                " FROM REFUND_RATE ";
//        String sql = " WITH OVER_PAID AS ( "+
//                " SELECT "+
//                " TR.RECEIVE_INSURER_ID, "+
//                " TR.INSURER_ID, "+
//                " TR.PAY_PERIOD, "+
//                " TR.PAY_PERIOD_MONTH, "+
//                " TR.PAY_PERIOD_YEAR, "+
//                " TR.REFUND_REASON_CODE, "+
//                " EMP.SECTION, "+
//                " TR.PAY_DATE                AS PAYMENT_DATE, "+
//                " TR.TOTAL_WAGES, "+
//                " TR.CNTR_AMOUNT, "+
//                " TR.INTEREST_PAID, "+
//                " CASE "+
//                " WHEN TR.REFUND_REASON_CODE = '06' OR TR.REFUND_REASON_CODE = '09' THEN "+
//                " NVL(TR.EMPE_OVERP, 0) - NVL(REFUND_AMOUNT, 0) "+
//                " WHEN TR.REFUND_REASON_CODE != '06' OR TR.REFUND_REASON_CODE != '09' THEN "+
//                " NVL(TR.CNTR_AMOUNT, 0) - NVL(REFUND_AMOUNT, 0) "+
//                " END                        AS CAL_EMPE_OVERP, "+
//                " CASE "+
//                " WHEN TR.REFUND_REASON_CODE = '06' OR TR.REFUND_REASON_CODE = '09' THEN "+
//                " NVL(TR.EMPE_OVERP, 0) "+
//                " WHEN TR.REFUND_REASON_CODE != '06' OR TR.REFUND_REASON_CODE != '09' THEN "+
//                " NVL(TR.CNTR_AMOUNT, 0) "+
//                " END                        AS EMPE_OVERP, "+
//                " CASE "+
//                " WHEN TR.REFUND_REASON_CODE = '06' OR TR.REFUND_REASON_CODE = '09' THEN "+
//                " NVL(TR.OVER_INTEREST_PAID, 0) - NVL(REFUND_INTEREST_AMOUNT, 0) "+
//                " WHEN TR.REFUND_REASON_CODE != '06' OR TR.REFUND_REASON_CODE != '09' THEN "+
//                " NVL(TR.INTEREST_PAID, 0) - NVL(REFUND_INTEREST_AMOUNT, 0) "+
//                " END                        AS CAL_OVER_INTEREST_PAID, "+
//                " CASE "+
//                " WHEN TR.REFUND_REASON_CODE = '06' OR TR.REFUND_REASON_CODE = '09' THEN "+
//                " NVL(TR.OVER_INTEREST_PAID, 0) "+
//                " WHEN TR.REFUND_REASON_CODE != '06' OR TR.REFUND_REASON_CODE != '09' THEN "+
//                " NVL(TR.INTEREST_AMOUNT, 0) "+
//                " END                        AS OVER_INTEREST_PAID, "+
//                " TR.CNTR_SICKNESS, "+
//                " TR.CNTR_RETIREMENT, "+
//                " TR.REFUND_AMOUNT, "+
//                " TR.REFUND_INTEREST_AMOUNT, "+
//                " SUBSTR(EMP.DEP_CODE, 0, 2) AS PROVINCE_CODE, "+
//                " TO_DATE((TR.PAY_PERIOD_YEAR - 543) || TR.PAY_PERIOD_MONTH || '01', 'YYYYMMDD')       AS PERIOD_DATE "+
//                " FROM "+
//                " CON_TR_RECEIVE_INSURER TR "+
//                " JOIN CON_TB_EMPLOYMENT EMP ON TR.INSURER_ID = EMP.INSURER_ID AND EMP.SECTION = '1' "+
//                " AND ((TO_DATE(TR.PAY_PERIOD,'MM-YYYY', 'NLS_CALENDAR=''THAI BUDDHA') BETWEEN EMP.START_DATE AND COALESCE(EMP.RESIGN_DATE, SYSDATE) AND TR.REFUND_REASON_CODE <> '07' AND EMP.EMP_STATUS <> 'D' ) "+
//                " OR (TO_DATE(TR.PAY_PERIOD,'MM-YYYY', 'NLS_CALENDAR=''THAI BUDDHA')  > EMP.RESIGN_DATE AND TR.REFUND_REASON_CODE = '07' AND EMP.EMP_STATUS = 'D' )) "+
////                " AND TR.PAY_DATE BETWEEN EMP.START_DATE AND COALESCE(EMP.RESIGN_DATE, SYSDATE) "+
//                " WHERE "+
//                " 1 = 1 "+
//                " AND TR.RECEIVE_INSURER_ID IN ( :receiveInsurerId ) "+
//                " AND TR.REFUND_REASON_CODE IS NOT NULL "+
//                " ), CNTR_RATE AS ( "+
//                " SELECT "+
//                " TRUNC(I.START_DATE)             AS START_DATE, "+
//                " TRUNC(I.END_DATE)               AS END_DATE, "+
//                " MAX(E3.VALUE1) + MAX(E2.VALUE1) AS CNTR_RATE, "+
//                " MAX(E3.VALUE1)                  AS SICKNESS_RATE, "+
//                " MAX(E2.VALUE1)                  AS RETIREMENT_RATE, "+
//                " I.IMFO_FLAG, "+
//                " I.CONFIG_RATE_GROUP, "+
//                " I.ACROSS_COUNTRY, "+
//                " CTAA.PROVINCE_CODE "+
//                " FROM "+
//                " CON_TB_INFORMATION      I "+
//                " LEFT JOIN OVER_PAID               OV ON I.START_DATE <= OV.PERIOD_DATE "+
//                " AND ( I.END_DATE IS NULL OR I.END_DATE >= OV.PERIOD_DATE ) "+
//                " LEFT JOIN CON_TB_INFORMATION      E2 ON E2.SUBJECT = 'RETIREMENT' "+
//                " AND E2.GROUP_CODE = 'INSURER_RATE' "+
//                " AND E2.SYS_CODE = I.SYS_CODE "+
//                " AND E2.START_DATE = I.START_DATE "+
//                " LEFT JOIN CON_TB_INFORMATION      E3 ON E3.SUBJECT = 'SICKNESS' "+
//                " AND E3.GROUP_CODE = 'INSURER_RATE' "+
//                " AND E3.SYS_CODE = I.SYS_CODE "+
//                " AND E3.START_DATE = I.START_DATE "+
//                " LEFT JOIN CON_TB_APPLICABLE_AREAS CTAA ON CTAA.CONFIG_RATE_GROUP = I.CONFIG_RATE_GROUP "+
//                " WHERE "+
//                " I.SYS_CODE = 'M39' "+
//                " AND I.GROUP_CODE = 'INSURER_RATE' "+
//                " AND I.STATUS = 'A' "+
//                " AND I.START_DATE <= OV.PERIOD_DATE "+
//                " AND ( I.END_DATE IS NULL "+
//                " OR I.END_DATE >= OV.PERIOD_DATE ) "+
//                " GROUP BY "+
//                " TRUNC(I.START_DATE), "+
//                " TRUNC(I.END_DATE), "+
//                " I.IMFO_FLAG, "+
//                " I.CONFIG_RATE_GROUP, "+
//                " I.ACROSS_COUNTRY, "+
//                " CTAA.PROVINCE_CODE "+
//                " ), REFUND_RATE AS ( "+
//                " SELECT "+
//                " OV.RECEIVE_INSURER_ID, "+
//                " OV.INSURER_ID, "+
//                " OV.PAY_PERIOD, "+
//                " OV.PAY_PERIOD_MONTH, "+
//                " OV.PAY_PERIOD_YEAR, "+
//                " OV.REFUND_REASON_CODE, "+
//                " OV.SECTION, "+
//                " OV.PAYMENT_DATE, "+
//                " OV.PROVINCE_CODE, "+
//                " OV.TOTAL_WAGES, "+
//                " OV.CNTR_AMOUNT, "+
//                " OV.INTEREST_PAID, "+
//                " OV.EMPE_OVERP, "+
//                " OV.CAL_EMPE_OVERP, "+
//                " OV.OVER_INTEREST_PAID, "+
//                " OV.CAL_OVER_INTEREST_PAID, "+
//                " OV.REFUND_AMOUNT, "+
//                " OV.REFUND_INTEREST_AMOUNT, "+
//                " OV.CNTR_SICKNESS, "+
//                " OV.CNTR_RETIREMENT, "+
//                " NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) AS IMFO_FLAG, "+
//                " CASE "+
//                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09'  THEN "+
//                " CASE "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' THEN "+
//                " NVL(TO_NUMBER(CNTR.CNTR_RATE), TO_NUMBER(CNTR1.CNTR_RATE)) "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N'  THEN "+
//                " TO_NUMBER(CNTR1.CNTR_RATE) "+
//                " END "+
//                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' THEN "+
//                " TO_NUMBER(CNTR1.CNTR_RATE) "+
//                " END                                  AS REAL_CNTR_RATE, "+
//                " CASE "+
//                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09'  THEN "+
//                " CASE "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' THEN "+
//                " NVL(TO_NUMBER(CNTR.SICKNESS_RATE), "+
//                " TO_NUMBER(CNTR1.SICKNESS_RATE)) "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N'  THEN "+
//                " TO_NUMBER(CNTR1.SICKNESS_RATE) "+
//                " END "+
//                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' THEN "+
//                " TO_NUMBER(CNTR1.SICKNESS_RATE) "+
//                " END                                  AS REAL_SICKNESS_RATE, "+
//                " CASE "+
//                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09'  THEN "+
//                " CASE "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' THEN "+
//                " NVL(TO_NUMBER(CNTR.RETIREMENT_RATE), TO_NUMBER(CNTR1.RETIREMENT_RATE)) "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N'  THEN "+
//                " TO_NUMBER(CNTR1.RETIREMENT_RATE) "+
//                " END "+
//                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' THEN "+
//                " TO_NUMBER(CNTR1.RETIREMENT_RATE) "+
//                " END                                  AS REAL_RETIREMENT_RATE, "+
//                " CASE "+
//                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09'  THEN "+
//                " CASE "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' THEN "+
//                " 9 - NVL(TO_NUMBER(CNTR.CNTR_RATE), TO_NUMBER(CNTR1.CNTR_RATE)) "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N'  THEN "+
//                " 3 "+
//                " END "+
//                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' THEN "+
//                " TO_NUMBER(CNTR1.CNTR_RATE) "+
//                " END                                  AS REFUND_CNTR_RATE, "+
//                " CASE "+
//                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09'  THEN "+
//                " CASE "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' THEN "+
//                " 3 - NVL(TO_NUMBER(CNTR.SICKNESS_RATE), TO_NUMBER(CNTR1.SICKNESS_RATE)) "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N'  THEN "+
//                " TO_NUMBER(CNTR1.SICKNESS_RATE) "+
//                " END "+
//                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' THEN "+
//                " CASE "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' THEN "+
//                " 3 - NVL(TO_NUMBER(CNTR.SICKNESS_RATE), TO_NUMBER(CNTR1.SICKNESS_RATE)) "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'N'  THEN "+
//                " TO_NUMBER(CNTR1.SICKNESS_RATE) "+
//                " END "+
//                " END                                  AS REFUND_SICKNESS_RATE, "+
//                " CASE "+
//                " WHEN OV.REFUND_REASON_CODE = '06' OR OV.REFUND_REASON_CODE = '09'  THEN "+
//                " CASE "+
//                " WHEN NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG) = 'RR' THEN "+
//                " 6 - NVL(TO_NUMBER(CNTR.RETIREMENT_RATE), TO_NUMBER(CNTR1.RETIREMENT_RATE)) "+
//                " ELSE "+
//                " 0 "+
//                " END "+
//                " WHEN OV.REFUND_REASON_CODE != '06' OR OV.REFUND_REASON_CODE != '09' THEN "+
//                " TO_NUMBER(CNTR1.RETIREMENT_RATE) "+
//                " END                                  AS REFUND_RETIREMENT_RATE "+
//                " FROM "+
//                " OVER_PAID OV "+
//                " LEFT JOIN CNTR_RATE CNTR ON CNTR.START_DATE <= OV.PERIOD_DATE "+
//                " AND ( CNTR.END_DATE IS NULL OR CNTR.END_DATE >= OV.PERIOD_DATE ) "+
//                " AND CNTR.PROVINCE_CODE = OV.PROVINCE_CODE "+
//                " AND CNTR.ACROSS_COUNTRY = '1' "+
//                " LEFT JOIN CNTR_RATE CNTR1 ON CNTR1.START_DATE <= OV.PERIOD_DATE "+
//                " AND ( CNTR1.END_DATE IS NULL OR CNTR1.END_DATE >= OV.PERIOD_DATE ) "+
//                " AND CNTR1.ACROSS_COUNTRY = '0' "+
//                " ) "+
//                " SELECT "+
//                " RECEIVE_INSURER_ID, "+
//                " IMFO_FLAG, "+
//                " NULL AS FULL_NAME, "+
//                " NULL AS ID_CARD_NO, "+
//                " INSURER_ID, "+
//                " PAY_PERIOD, "+
//                " PAY_PERIOD_MONTH, "+
//                " PAY_PERIOD_YEAR, "+
//                " REFUND_REASON_CODE, "+
//                " SECTION, "+
//                " PAYMENT_DATE, "+
//                " PROVINCE_CODE, "+
//                " CNTR_AMOUNT, "+
//                " INTEREST_PAID, "+
//                " EMPE_OVERP, "+
//                " CAL_EMPE_OVERP, "+
//                " OVER_INTEREST_PAID, "+
//                " CAL_OVER_INTEREST_PAID, "+
//                " REFUND_AMOUNT, "+
//                " REFUND_INTEREST_AMOUNT, "+
//                " REAL_CNTR_RATE, "+
//                " REAL_SICKNESS_RATE, "+
//                " REAL_RETIREMENT_RATE, "+
//                " CNTR_AMOUNT - CNTR_RETIREMENT                                                                       AS CNTR_SICKNESS, "+
//                " CNTR_RETIREMENT                                                                                     AS CNTR_RETIREMENT, "+
//                " ROUND(TOTAL_WAGES *(REAL_CNTR_RATE / 100), 0)                                                       AS CAL_CNTR_AMOUNT, "+
//                " ROUND(TOTAL_WAGES *(REAL_CNTR_RATE / 100), 0) - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), 2) AS CAL_CNTR_SICKNESS, "+
//                " ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), 2)                                                 AS CAL_CNTR_RETIREMENT, "+
//                " CASE "+
//                " WHEN REFUND_REASON_CODE = '06' THEN "+
//                " ( CNTR_AMOUNT - CNTR_RETIREMENT ) - ( ROUND(TOTAL_WAGES *(REAL_CNTR_RATE / 100), 0) - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100 ), 2) ) "+
////                " ROUND(( CNTR_AMOUNT - CNTR_RETIREMENT ) - ( ROUND(TOTAL_WAGES *(REAL_CNTR_RATE / 100), 0) - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100 ), 2) ), 0) "+
//                " WHEN REFUND_REASON_CODE = '09' THEN "+
//                " ( CNTR_AMOUNT - CNTR_RETIREMENT ) - ( ROUND(TOTAL_WAGES *(REAL_CNTR_RATE / 100), 0) - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100 ), 2) ) "+
////                " ROUND(( CNTR_AMOUNT - CNTR_RETIREMENT ) - ( ROUND(TOTAL_WAGES *(REAL_CNTR_RATE / 100), 0) - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100 ), 2) ), 0) "+
////                " ROUND(ROUND(TOTAL_WAGES *(REAL_CNTR_RATE / 100), 0) - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), 2) - ( ( CNTR_AMOUNT - CNTR_RETIREMENT ) - EMPE_OVERP ), 0) "+
//                " WHEN REFUND_REASON_CODE IS NOT NULL AND REFUND_REASON_CODE != '06' OR REFUND_REASON_CODE != '09' THEN "+
//                " CNTR_SICKNESS "+
//                " END                                                                                                 AS REFUND_SICKNESS, "+
//                " CASE "+
//                " WHEN REFUND_REASON_CODE = '06' THEN "+
//                " ROUND(CNTR_RETIREMENT - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), 2), 0) "+
//                " WHEN REFUND_REASON_CODE = '09' THEN "+
//                " ROUND(CNTR_RETIREMENT - ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), 2), 0) "+
////                " ROUND(ROUND(TOTAL_WAGES *(REAL_RETIREMENT_RATE / 100), 2) - CNTR_RETIREMENT, 0) "+
//                " WHEN REFUND_REASON_CODE IS NOT NULL AND REFUND_REASON_CODE != '06' OR REFUND_REASON_CODE != '09' THEN "+
//                " CNTR_RETIREMENT "+
//                " END                                                                                                 AS REFUND_RETIREMENT "+
//                " FROM "+
//                " REFUND_RATE ";
        param.put("receiveInsurerId", receiveInsurerId);

        return queryForObject(sql, param, new ReceiveInsurerPeriodRowMapper());
    }
}
