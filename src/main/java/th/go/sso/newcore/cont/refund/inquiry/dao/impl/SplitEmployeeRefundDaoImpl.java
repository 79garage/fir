package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTrRefundEmployeeRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.SplitEmployeeRefundListAggRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.SplitEmployeeRefundPeriodRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.SplitTbEmployeeRefundRowMapper;

@Repository
public class SplitEmployeeRefundDaoImpl extends DaoBase<Long> {


    public SplitEmployeeRefundDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<SplitEmployeeRefundCompanyPeriodBean> getSplitEmployeeRefundPeriod(Long refundRequestId) throws Exception{
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " T1.REFUND_REQUEST_ID, "+
                " T2.REF_REFUND_REQUEST_ID, "+
                " T1.RECEIVE_EMPLOYEE_ID, "+
                " T3.REFUND_EMPLOYEE_ID, "+
                " T5.INSURER_ID, "+
                " T5.ID_CARD_NO, "+
                " T5.FULL_NAME, "+
                " T6.COMPANY_NO, "+
                " T6.COMPANY_NAME, "+
                " T6.COMPANY_BRANCH_CODE, "+
                " T6.COMPANY_BRANCH_NAME, "+
                " NVL( T1.REQUEST_EMPE_AMOUNT, 0 ) AS REQUEST_EMPE_AMOUNT, "+
                " NVL( T1.REFUND_EMPE_AMOUNT, 0 ) AS REFUND_EMPE_AMOUNT, "+
                " T1.REFUND_SICKNESS_AMOUNT, "+
                " T1.REFUND_RETIREMENT_AMOUNT, "+
                " T1.REFUND_UNEMPLOYMENT_AMOUNT, "+
                " T1.REFUND_INTEREST_AMOUNT, "+
                " T1.APPROVE_STATUS AS PROGRESS_STATUS, "+
                " T1.REFUND_PERIOD  AS PAY_PERIOD, "+
                " T4.ORDER_NO, "+
                " T4.ORDER_STATUS, "+
                " T4.ORDER_REMARK, "+
                " T4.REQUESTER_ID, "+
                " T1.REFUND_SICKNESS_RATE, "+
                " T1.REFUND_RETIREMENT_RATE, "+
                " T1.REFUND_UNEMPLOYMENT_RATE, "+
                " T2.PERIOD_START, "+
                " T2.PERIOD_END "+
                " FROM "+
                " CON_TB_REFUND_PERIOD   T1 "+
                " LEFT JOIN CON_TB_REFUND_REQUEST  T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID "+
                " LEFT JOIN CON_TR_REFUND_EMPLOYEE T3 ON T3.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID "+
                " LEFT JOIN CON_TB_REQUESTER33     T4 ON T4.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID AND T4.PERIOD = T1.REFUND_PERIOD "+
                " LEFT JOIN CON_MS_INSURER         T5 ON T5.INSURER_ID = T2.INSURER_ID "+
                " LEFT JOIN CON_MS_COMPANY         T6 ON T6.COMPANY_ID = T2.COMPANY_ID "+
                " WHERE "+
                " 1 = 1 "+
                " AND T2.REF_REFUND_REQUEST_ID = :refundRequestId "+
                " ORDER BY "+
                " T2.REFUND_REQUEST_ID ASC, T5.INSURER_ID ASC, TO_DATE(T1.REFUND_PERIOD, 'MM/YYYY') ASC ";

        param.put("refundRequestId", refundRequestId);

        return queryForList(sql, param, new SplitEmployeeRefundPeriodRowMapper());
    }

    public SplitEmployeeRefundCompanyPeriodBean findRefundEmployeePeriod(Long refundRequestId) throws Exception{
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " T1.REFUND_REQUEST_ID, "+
                " T2.REF_REFUND_REQUEST_ID, "+
                " T1.RECEIVE_EMPLOYEE_ID, "+
                " T3.REFUND_EMPLOYEE_ID, "+
                " T5.INSURER_ID, "+
                " T5.ID_CARD_NO, "+
                " T5.FULL_NAME, "+
                " NULL AS COMPANY_NO, "+
                " NULL AS COMPANY_NAME, "+
                " NULL AS COMPANY_BRANCH_CODE, "+
                " NULL AS COMPANY_BRANCH_NAME, "+
                " NVL( T1.REQUEST_EMPE_AMOUNT, 0 ) AS REQUEST_EMPE_AMOUNT, "+
                " NVL( T1.REFUND_EMPE_AMOUNT, 0 ) AS REFUND_EMPE_AMOUNT, "+
                " T1.REFUND_SICKNESS_AMOUNT, "+
                " T1.REFUND_RETIREMENT_AMOUNT, "+
                " T1.REFUND_UNEMPLOYMENT_AMOUNT, "+
                " T1.REFUND_INTEREST_AMOUNT, "+
                " T1.APPROVE_STATUS AS PROGRESS_STATUS, "+
                " T1.REFUND_PERIOD  AS PAY_PERIOD, "+
                " T4.ORDER_NO, "+
                " T4.ORDER_STATUS, "+
                " T4.ORDER_REMARK, "+
                " T4.REQUESTER_ID, "+
                " T1.REFUND_SICKNESS_RATE, "+
                " T1.REFUND_RETIREMENT_RATE, "+
                " T1.REFUND_UNEMPLOYMENT_RATE, "+
                " T2.PERIOD_START, "+
                " T2.PERIOD_END "+
                " FROM "+
                " CON_TB_REFUND_PERIOD   T1 "+
                " LEFT JOIN CON_TB_REFUND_REQUEST  T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID "+
                " LEFT JOIN CON_TR_REFUND_EMPLOYEE T3 ON T3.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID "+
                " LEFT JOIN CON_TB_REQUESTER33     T4 ON T4.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID AND T4.PERIOD = T1.REFUND_PERIOD "+
                " LEFT JOIN CON_MS_INSURER         T5 ON T5.INSURER_ID = T2.INSURER_ID "+
                " WHERE "+
                " 1 = 1 "+
                " AND T2.REFUND_REQUEST_ID = :refundRequestId ";

        param.put("refundRequestId", refundRequestId);

        return queryForObject(sql, param, new SplitEmployeeRefundPeriodRowMapper());
    }

    public List<SplitEmployeeRefundCompanyPeriodBean> getSplitEmployeeRefund(Long refundRequestId,Long insurerId) throws Exception{

        try {

            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT ");
            sql.append(" t3.refund_request_id,	 ");
            sql.append(" t2.ref_refund_request_id,	 ");
            sql.append(" t3.receive_employee_id,	 ");
            sql.append(" t1.refund_employee_id,");
            sql.append(" t4.insurer_id,	 ");
            sql.append(" t4.id_card_no,	 ");
            sql.append(" t4.full_name,	 ");
            sql.append(" NULL AS COMPANY_NO,	 ");
            sql.append(" NULL AS COMPANY_NAME,	 ");
            sql.append(" NULL AS COMPANY_BRANCH_CODE,	 ");
            sql.append(" NULL AS COMPANY_BRANCH_NAME,	 ");
            sql.append(" nvl(t3.request_empe_amount, 0)                  AS request_empe_amount,	 ");
            sql.append(" nvl(t3.refund_empe_amount, 0)                   AS refund_empe_amount,	 ");
            sql.append(" t3.refund_sickness_amount,	 ");
            sql.append(" t3.refund_retirement_amount,	 ");
            sql.append(" t3.refund_unemployment_amount,	 ");
            sql.append(" t3.refund_interest_amount,	 ");
            sql.append(" t3.approve_status AS progress_status, ");
            sql.append(" t3.refund_period                                 AS pay_period,	 ");
            sql.append(" t5.order_no,");
            sql.append(" t5.ORDER_STATUS,");
            sql.append(" t5.order_remark,");
            sql.append(" t5.requester_id,");
            sql.append(" t3.refund_sickness_rate,");
            sql.append(" t3.refund_retirement_rate,");
            sql.append(" t3.refund_unemployment_rate,");
            sql.append(" NULL AS PERIOD_START,");
            sql.append(" NULL AS PERIOD_END");
            sql.append(" FROM ");
            sql.append(" con_tr_refund_employee t1	 ");
            sql.append(" JOIN con_tb_refund_request t2 ON (t1.refund_request_id = t2.refund_request_id)	 ");
            sql.append(" JOIN con_tb_refund_period   t3 ON ( t1.refund_request_id = t3.refund_request_id )	 ");
            sql.append(" JOIN con_tb_refund_employee t6 ON t3.receive_employee_id = t6.receive_employee_id	 ");
            sql.append(" JOIN con_ms_insurer         t4 ON ( t4.insurer_id = t6.insurer_id )	 ");
            sql.append(" LEFT JOIN CON_TB_REQUESTER33     t5 ON (t1.refund_request_id = t5.refund_request_id)");
            sql.append(" WHERE 1 = 1 ");

            Map<String, Object> param = new HashMap<>();

            if (!NumberUtils.isZeroOrNull(refundRequestId)) {
                sql.append(" AND   t2.ref_refund_request_id  = :refundRequestId ");
                param.put("refundRequestId",refundRequestId);
            }
            if (!NumberUtils.isZeroOrNull(insurerId)) {
                sql.append(" AND t6.insurer_id = :insurerId");
                param.put("insurerId",insurerId);
            }

            sql.append("	GROUP BY	 ");
            sql.append(" t3.refund_request_id,	 ");
            sql.append(" t2.ref_refund_request_id,	 ");
            sql.append(" t3.receive_employee_id,	 ");
            sql.append(" t1.refund_employee_id,");
            sql.append(" t4.insurer_id,	 ");
            sql.append(" t4.id_card_no,	 ");
            sql.append(" t4.full_name,	 ");
            sql.append(" t3.refund_sickness_amount,	 ");
            sql.append(" t3.refund_retirement_amount,	 ");
            sql.append(" t3.refund_unemployment_amount,	 ");
            sql.append(" t3.refund_interest_amount,	 ");
            sql.append(" t3.request_empe_amount,	 ");
            sql.append(" t3.refund_empe_amount,	 ");
            sql.append(" t3.approve_status,	 ");
            sql.append(" t3.refund_period,	 ");
            sql.append(" t3.refund_period_month,	 ");
            sql.append(" t5.order_no,");
            sql.append(" t5.ORDER_STATUS,");
            sql.append(" t5.order_remark,");
            sql.append(" t5.requester_id,");
            sql.append(" t3.refund_period_year,	 ");
            sql.append(" t3.refund_sickness_rate,");
            sql.append(" t3.refund_retirement_rate,");
            sql.append(" t3.refund_unemployment_rate");
            sql.append(" ORDER BY   t4.insurer_id, TO_DATE(t3.refund_period, 'MM/YYYY') ASC ");

            return queryForList(sql.toString(), param, new SplitEmployeeRefundPeriodRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }

    }

    public List<SplitEmployeeRefundCompanyPeriodBean> getTbSplitEmployeeRefund(Long refundRequestId) throws Exception{
        Map<String, Object> param = new HashMap<>();
        String sql = " WITH OVER_PAID AS ( "+
                " SELECT "+
                " TRE.RECEIVE_EMPLOYEE_ID, "+
                " TRE.RECEIVE_COMPANY_ID, "+
                " TRE.REFUND_REASON_CODE, "+
                " TRC.COMPANY_ID, "+
                " TRE.INSURER_ID, "+
                " RR.REFUND_REQUEST_ID, "+
                " MI.FULL_NAME, "+
                " MI.ID_CARD_NO, "+
                " TRE.REFUND_STATUS, "+
                " TRE.RECEIPT_ID, "+
                " TRE.PAY_PERIOD, "+
                " TRE.PAY_PERIOD_MONTH, "+
                " TRE.PAY_PERIOD_YEAR, "+
                " TRC.PAY_DATE                     AS PAYMENT_DATE, "+
                " TRE.TOTAL_WAGES, "+
                " TRE.CNTR_AMOUNT                  AS EMPE_AMOUNT, "+
                " TRE.CNTR_SICKNESS                AS EMPE_CNTR_SICKNESS, "+
                " TRE.CNTR_RETIREMENT              AS EMPE_CNTR_RETIREMENT, "+
                " TRE.CNTR_UNEMPLOYMENT            AS EMPE_CNTR_UNEMPLOYMENT, "+
                " TRE.EMPR_AMOUNT, "+
                " TRE.EMPR_CNTR_SICKNESS, "+
                " TRE.EMPR_CNTR_RETIREMENT, "+
                " TRE.EMPR_CNTR_UNEMPLOYMENT, "+
                " TRE.EMPR_OVERP                   AS EMPR_OVERP, "+
                " RE.REQUEST_EMPE_AMOUNT           AS EMPE_OVERP, "+
                " TRC.INTEREST_PAID, "+
                " TRC.OVER_INTEREST_PAID, "+
                " TRE.EMPR_CAL, "+
                " TRE.EMPE_CAL, "+
                " TRE.PAY_STATUS, "+
                " MC.COMPANY_NO, "+
                " MC.COMPANY_NAME, "+
                " MC.COMPANY_BRANCH_CODE, "+
                " MC.COMPANY_BRANCH_NAME, "+
                " SUBSTR(MC.SSO_BRANCH_CODE, 0, 2) AS PROVINCE_CODE, "+
                " TO_DATE((TRE.PAY_PERIOD_YEAR - 543) || TRE.PAY_PERIOD_MONTH || '01', 'YYYYMMDD')             AS PERIOD_DATE "+
                " FROM "+
                " CON_TB_REFUND_EMPLOYEE RE "+
                " JOIN CON_TB_REFUND_PERIOD    RP ON RP.REFUND_PERIOD_ID = RE.REFUND_PERIOD_ID "+
                " JOIN CON_TB_REFUND_REQUEST   RR ON RR.REFUND_REQUEST_ID = RP.REFUND_REQUEST_ID "+
                " JOIN CON_TR_RECEIVE_EMPLOYEE TRE ON TRE.RECEIVE_EMPLOYEE_ID = RE.RECEIVE_EMPLOYEE_ID "+
                " JOIN CON_TR_RECEIVE_COMPANY  TRC ON TRE.RECEIVE_COMPANY_ID = TRC.RECEIVE_COMPANY_ID "+
                " JOIN CON_MS_COMPANY          MC ON TRC.COMPANY_ID = MC.COMPANY_ID "+
                " JOIN CON_MS_INSURER          MI ON MI.INSURER_ID = TRE.INSURER_ID "+
                " WHERE "+
                " 1 = 1 "+
                " AND RP.REFUND_REQUEST_ID = :refundRequestId "+
                " ), "+
                " CNTR_RATE AS ( "+
                " SELECT "+
                " TRUNC(I.START_DATE) AS START_DATE, "+
                " TRUNC(I.END_DATE) AS END_DATE, "+
                " MAX(E3.VALUE1) + MAX(E2.VALUE1) + MAX(E1.VALUE1) AS EMPE_CNTR_RATE, "+
                " MAX(E3.VALUE1) AS EMPE_SICKNESS_RATE, "+
                " MAX(E2.VALUE1) AS EMPE_RETIREMENT_RATE, "+
                " MAX(E1.VALUE1) AS EMPE_UNEMPLOYMENT_RATE, "+
                " MAX(C3.VALUE1) + MAX(C2.VALUE1) + MAX(C1.VALUE1) AS EMPR_CNTR_RATE, "+
                " MAX(C3.VALUE1) AS EMPR_SICKNESS_RATE, "+
                " MAX(C2.VALUE1) AS EMPR_RETIREMENT_RATE, "+
                " MAX(C1.VALUE1) AS EMPR_UNEMPLOYMENT_RATE, "+
                " I.IMFO_FLAG, "+
                " I.CONFIG_RATE_GROUP, "+
                " I.ACROSS_COUNTRY, "+
                " CTAA.PROVINCE_CODE "+
                " FROM CON_TB_INFORMATION I "+
                " LEFT JOIN OVER_PAID OV ON I.START_DATE <= OV.PERIOD_DATE "+
                " AND (I.END_DATE IS NULL OR I.END_DATE >= OV.PERIOD_DATE) "+
                " LEFT JOIN CON_TB_INFORMATION E1 ON E1.SUBJECT = 'UNEMPLOYMENT' AND E1.GROUP_CODE = 'EMPLOYEE_RATE' AND E1.SYS_CODE = I.SYS_CODE AND E1.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION E2 ON E2.SUBJECT = 'RETIREMENT' AND E2.GROUP_CODE = 'EMPLOYEE_RATE' AND E2.SYS_CODE = I.SYS_CODE AND E2.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION E3 ON E3.SUBJECT = 'SICKNESS' AND E3.GROUP_CODE = 'EMPLOYEE_RATE' AND E3.SYS_CODE = I.SYS_CODE AND E3.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION C1 ON C1.SUBJECT = 'UNEMPLOYMENT' AND C1.GROUP_CODE = 'COMPANY_RATE' AND C1.SYS_CODE = I.SYS_CODE AND C1.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION C2 ON C2.SUBJECT = 'RETIREMENT' AND C2.GROUP_CODE = 'COMPANY_RATE' AND C2.SYS_CODE = I.SYS_CODE AND C2.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION C3 ON C3.SUBJECT = 'SICKNESS' AND C3.GROUP_CODE = 'COMPANY_RATE' AND C3.SYS_CODE = I.SYS_CODE AND C3.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_APPLICABLE_AREAS CTAA ON CTAA.CONFIG_RATE_GROUP = I.CONFIG_RATE_GROUP "+
                " WHERE I.SYS_CODE = 'M33' "+
                " AND I.GROUP_CODE IN ('EMPLOYEE_RATE', 'COMPANY_RATE') "+
                " AND I.STATUS = 'A' "+
                " AND I.START_DATE <= OV.PERIOD_DATE "+
                " AND (I.END_DATE IS NULL OR I.END_DATE >= OV.PERIOD_DATE) "+
                " GROUP BY "+
                " TRUNC(I.START_DATE), "+
                " TRUNC(I.END_DATE), "+
                " I.IMFO_FLAG, "+
                " I.CONFIG_RATE_GROUP, "+
                " I.ACROSS_COUNTRY, "+
                " CTAA.PROVINCE_CODE "+
                " ), "+
                " CNTR_CAL AS ( "+
                " SELECT "+
                " OV.RECEIVE_EMPLOYEE_ID, "+
                " OV.RECEIVE_COMPANY_ID, "+
                " OV.INSURER_ID, "+
                " OV.REFUND_REQUEST_ID, "+
                " OV.FULL_NAME, "+
                " OV.ID_CARD_NO, "+
                " OV.COMPANY_NO, "+
                " OV.COMPANY_NAME, "+
                " OV.COMPANY_BRANCH_CODE, "+
                " OV.COMPANY_BRANCH_NAME, "+
                " OV.REFUND_STATUS, "+
                " OV.RECEIPT_ID, "+
                " OV.PAY_PERIOD, "+
                " OV.PAY_PERIOD_MONTH, "+
                " OV.PAY_PERIOD_YEAR, "+
                " OV.PAYMENT_DATE, "+
                " OV.TOTAL_WAGES, "+
                " OV.EMPE_AMOUNT, "+
                " OV.EMPE_CNTR_SICKNESS, "+
                " OV.EMPE_CNTR_RETIREMENT, "+
                " OV.EMPE_CNTR_UNEMPLOYMENT, "+
                " OV.EMPR_AMOUNT, "+
                " OV.EMPR_CNTR_SICKNESS, "+
                " OV.EMPR_CNTR_RETIREMENT, "+
                " OV.EMPR_CNTR_UNEMPLOYMENT, "+
                " OV.EMPR_OVERP, "+
                " OV.EMPE_OVERP, "+
                " OV.INTEREST_PAID, "+
                " OV.OVER_INTEREST_PAID, "+
                " OV.EMPR_CAL, "+
                " OV.EMPE_CAL, "+
                " OV.PAY_STATUS, "+
                " OV.PROVINCE_CODE, "+
                " COALESCE(TO_NUMBER(DC.EMPE_CNTR_RATE), TO_NUMBER(CNTR.EMPE_CNTR_RATE), TO_NUMBER(CNTR1.EMPE_CNTR_RATE)) AS REAL_EMPE_CNTR_RATE, "+
                " COALESCE(TO_NUMBER(DC.EMPE_SICKNESS_RATE), TO_NUMBER(CNTR.EMPE_SICKNESS_RATE), TO_NUMBER(CNTR1.EMPE_SICKNESS_RATE)) AS REAL_EMPE_SICKNESS_RATE, "+
                " COALESCE(TO_NUMBER(DC.EMPE_RETIREMENT_RATE), TO_NUMBER(CNTR.EMPE_RETIREMENT_RATE), TO_NUMBER(CNTR1.EMPE_RETIREMENT_RATE)) AS REAL_EMPE_RETIREMENT_RATE, "+
                " COALESCE(TO_NUMBER(DC.EMPE_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR.EMPE_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR1.EMPE_UNEMPLOYMENT_RATE)) AS REAL_EMPE_UNEMPLOYMENT_RATE, "+
                " EMPE_CAL - ((ROUND(OV.TOTAL_WAGES * (COALESCE(TO_NUMBER(DC.EMPE_RETIREMENT_RATE), TO_NUMBER(CNTR.EMPE_RETIREMENT_RATE), TO_NUMBER(CNTR1.EMPE_RETIREMENT_RATE)) / 100), 2)) + "+
                " (ROUND(OV.TOTAL_WAGES * (COALESCE(TO_NUMBER(DC.EMPE_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR.EMPE_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR1.EMPE_UNEMPLOYMENT_RATE)) / 100), 2))) AS CAL_EMPE_SICKNESS, "+
                " ROUND(OV.TOTAL_WAGES * (COALESCE(TO_NUMBER(DC.EMPE_RETIREMENT_RATE), TO_NUMBER(CNTR.EMPE_RETIREMENT_RATE), TO_NUMBER(CNTR1.EMPE_RETIREMENT_RATE)) / 100), 2) AS CAL_EMPE_RETIREMENT, "+
                " ROUND(OV.TOTAL_WAGES * (COALESCE(TO_NUMBER(DC.EMPE_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR.EMPE_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR1.EMPE_UNEMPLOYMENT_RATE)) / 100), 2) AS CAL_EMPE_UNEMPLOYMENT "+
                " FROM OVER_PAID OV "+
                " LEFT JOIN CON_TB_DISCOUNT_COMPANY_RATE DC ON OV.COMPANY_ID = DC.COMPANY_ID "+
                " AND OV.PERIOD_DATE "+
                " BETWEEN DC.EFFECT_DATE AND NVL(DC.END_OF_EFFECT_DATE, SYSDATE) "+
                " LEFT JOIN CNTR_RATE CNTR ON  CNTR.START_DATE <= OV.PERIOD_DATE "+
                " AND (CNTR.END_DATE IS NULL OR CNTR.END_DATE >= OV.PERIOD_DATE) "+
                " AND CNTR.PROVINCE_CODE = OV.PROVINCE_CODE "+
                " AND CNTR.ACROSS_COUNTRY = '1' "+
                " LEFT JOIN CNTR_RATE CNTR1 ON  CNTR1.START_DATE <= OV.PERIOD_DATE "+
                " AND (CNTR1.END_DATE IS NULL OR CNTR1.END_DATE >= OV.PERIOD_DATE) "+
                " AND CNTR1.ACROSS_COUNTRY = '0' "+
                " ), "+
                " REFUND_CNTR AS ( "+
                " SELECT "+
                " RECEIVE_EMPLOYEE_ID, "+
                " RECEIVE_COMPANY_ID, "+
                " INSURER_ID, "+
                " REFUND_REQUEST_ID, "+
                " FULL_NAME, "+
                " ID_CARD_NO, "+
                " COMPANY_NO, "+
                " COMPANY_NAME, "+
                " COMPANY_BRANCH_CODE, "+
                " COMPANY_BRANCH_NAME, "+
                " REFUND_STATUS, "+
                " RECEIPT_ID, "+
                " PAY_PERIOD, "+
                " PAY_PERIOD_MONTH, "+
                " PAY_PERIOD_YEAR, "+
                " PAYMENT_DATE, "+
                " TOTAL_WAGES, "+
                " EMPE_AMOUNT, "+
                " EMPR_AMOUNT, "+
                " EMPR_OVERP, "+
                " EMPE_OVERP, "+
                " REAL_EMPE_CNTR_RATE,REAL_EMPE_SICKNESS_RATE,REAL_EMPE_RETIREMENT_RATE,REAL_EMPE_UNEMPLOYMENT_RATE, "+
                " EMPE_CNTR_SICKNESS,EMPE_CNTR_RETIREMENT,EMPE_CNTR_UNEMPLOYMENT, "+
                " CAL_EMPE_SICKNESS,CAL_EMPE_RETIREMENT,CAL_EMPE_UNEMPLOYMENT, "+
                " EMPE_CNTR_SICKNESS - CAL_EMPE_SICKNESS AS REFUND_EMPE_SICKNESS, "+
                " EMPE_CNTR_RETIREMENT - CAL_EMPE_RETIREMENT AS REFUND_EMPE_RETIREMENT, "+
                " EMPE_CNTR_UNEMPLOYMENT - CAL_EMPE_UNEMPLOYMENT AS REFUND_EMPE_UNEMPLOYMENT, "+
                " INTEREST_PAID, "+
                " OVER_INTEREST_PAID, "+
                " EMPR_CAL, "+
                " EMPE_CAL, "+
                " PAY_STATUS "+
                " FROM CNTR_CAL "+
                " ) "+
                " SELECT "+
                " RECEIVE_COMPANY_ID, "+
                " RECEIVE_EMPLOYEE_ID, "+
                " INSURER_ID, "+
                " REFUND_REQUEST_ID, "+
                " FULL_NAME, "+
                " ID_CARD_NO, "+
                " COMPANY_NO, "+
                " COMPANY_NAME, "+
                " COMPANY_BRANCH_CODE, "+
                " COMPANY_BRANCH_NAME, "+
                " REFUND_STATUS, "+
                " PAY_PERIOD, "+
                " PAYMENT_DATE, "+
                " EMPE_AMOUNT  AS CNTR_AMOUNT, "+
                " EMPR_OVERP AS EMPR_OVER, "+
                " EMPE_OVERP AS EMPE_OVER, "+
                " REAL_EMPE_CNTR_RATE,REAL_EMPE_SICKNESS_RATE,REAL_EMPE_RETIREMENT_RATE,REAL_EMPE_UNEMPLOYMENT_RATE, "+
                " EMPE_CNTR_SICKNESS,EMPE_CNTR_RETIREMENT,EMPE_CNTR_UNEMPLOYMENT, "+
                " CAL_EMPE_SICKNESS,CAL_EMPE_RETIREMENT,CAL_EMPE_UNEMPLOYMENT, "+
                " REFUND_EMPE_SICKNESS AS REFUND_EMPE_SICKNESS, "+
                " REFUND_EMPE_RETIREMENT AS REFUND_EMPE_RETIREMENT, "+
                " REFUND_EMPE_UNEMPLOYMENT AS REFUND_EMPE_UNEMPLOYMENT, "+
                " INTEREST_PAID AS INTEREST_PAID, "+
                " OVER_INTEREST_PAID AS OVER_INTEREST_PAID, "+
                " 0 AS REFUND_INTEREST_AMOUNT, "+
                " 0 AS REFUND_EMPE_AMOUNT, "+
                " NULL AS PROGRESS_STATUS "+
                " FROM REFUND_CNTR ";
        param.put("refundRequestId", refundRequestId);
        return queryForList(sql, param, new SplitTbEmployeeRefundRowMapper());
    }

    public List<SplitEmployeeRefundBean> getSplitEmployee(Long refundRequestId) throws Exception{
            Map<String, Object> param = new HashMap<>();
            String sql = " SELECT "+
                    " T0.INSURER_ID, "+
                    " T0.ID_CARD_NO, "+
                    " T0.FULL_NAME, "+
                    " T0.PROGRESS_STATUS, "+
                    " T0.PAY_PERIOD, "+
                    " T1.REFUND_REQUEST_ID, "+
                    " T0.REFUND_REQUEST_ID AS REF_REFUND_REQUEST_ID, "+
                    " T1.RECEIVE_NO, "+
                    " T1.REFUND_TYPE_PLACE, "+
                    " T1.REFUND_PO_ORDER, "+
                    " T1.REFUND_PO_ORDER_NAME, "+
                    " T1.DIST_CODE, "+
                    " T1.SUBDIST_CODE, "+
                    " T1.PROVINCE_CODE, "+
                    " T1.REFUND_PROMPT_PAY, "+
                    " T1.REFUND_BANK_ID, "+
                    " T1.REFUND_BANK_BRANCH_ID, "+
                    " T1.REFUND_BANK_ACCOUNT, "+
                    " T2.BANK_CODE, "+
                    " T3.BANK_BRANCH_CODE, "+
                    " T0.REFUND_STATUS "+
                    " FROM "+
                    " ( "+
                    " SELECT "+
                    " REPER.REFUND_REQUEST_ID, "+
                    " MSINS.INSURER_ID, "+
                    " MSINS.ID_CARD_NO, "+
                    " MSINS.FULL_NAME, "+
                    " RECOMP.PROGRESS_STATUS, "+
                    " LISTAGG(REPER.REFUND_PERIOD, ',') WITHIN GROUP( ORDER BY REPER.REFUND_PERIOD ) AS PAY_PERIOD, "+
                    " CTRRE.REFUND_STATUS, "+
                    " CTRRC.COMPANY_ID, "+
                    " CMSC.COMPANY_NO, "+
                    " CMSC.COMPANY_BRANCH_CODE "+
                    " FROM "+
                    " CON_TB_REFUND_EMPLOYEE  REEMPE "+
                    " LEFT JOIN CON_TB_REFUND_PERIOD    REPER ON REEMPE.REFUND_PERIOD_ID = REPER.REFUND_PERIOD_ID "+
                    " LEFT JOIN CON_MS_INSURER          MSINS ON MSINS.INSURER_ID = REEMPE.INSURER_ID "+
                    " LEFT JOIN CON_TR_REFUND_COMPANY   RECOMP ON RECOMP.REFUND_REQUEST_ID = REPER.REFUND_REQUEST_ID "+
                    " LEFT JOIN CON_TR_RECEIVE_EMPLOYEE CTRRE ON CTRRE.RECEIVE_EMPLOYEE_ID = REEMPE.RECEIVE_EMPLOYEE_ID "+
                    " LEFT JOIN CON_TR_RECEIVE_COMPANY  CTRRC ON CTRRC.RECEIVE_COMPANY_ID = CTRRE.RECEIVE_COMPANY_ID "+
                    " LEFT JOIN CON_MS_COMPANY CMSC ON CMSC.COMPANY_ID = CTRRC.COMPANY_ID "+
                    " WHERE "+
                    " 1 = 1 ";
        if (!NumberUtils.isZeroOrNull(refundRequestId)) {
            sql += " AND REPER.REFUND_REQUEST_ID = :refundRequestId ";
            param.put("refundRequestId",refundRequestId);
        }
        sql += " GROUP BY "+
                    " REPER.REFUND_REQUEST_ID, "+
                    " MSINS.INSURER_ID, "+
                    " MSINS.ID_CARD_NO, "+
                    " MSINS.FULL_NAME, "+
                    " RECOMP.PROGRESS_STATUS, "+
                    " CTRRE.REFUND_STATUS, "+
                    " CTRRC.COMPANY_ID, "+
                    " CMSC.COMPANY_NO, "+
                    " CMSC.COMPANY_BRANCH_CODE "+
                    " )                     T0 "+
                    " LEFT JOIN CON_TB_REFUND_REQUEST T1 ON T1.INSURER_ID = T0.INSURER_ID AND T1.REF_REFUND_REQUEST_ID = T0.REFUND_REQUEST_ID "+
                    " LEFT JOIN CON_MS_BANK           T2 ON T2.BANK_ID = T1.REFUND_BANK_ID "+
                    " LEFT JOIN CON_MS_BANK_BRANCH    T3 ON T3.BANK_ID = T2.BANK_ID AND T3.BANK_BRANCH_ID = T1.REFUND_BANK_BRANCH_ID "+
                    " ORDER BY "+
                    " T0.COMPANY_BRANCH_CODE, "+
                    " T0.ID_CARD_NO, "+
                    " T0.PAY_PERIOD, "+
                    " T1.RECEIVE_NO ";
            return queryForList(sql, param, new SplitEmployeeRefundListAggRowMapper());
    }

    public List<SplitEmployeeRefundCompanyPeriodBean> getSplitEmployeeRefundCntr(Integer refundRequestId) throws Exception{

        try {

            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT reper.refund_request_id, ");
            sql.append(" msins.insurer_id, ");
            sql.append(" msins.id_card_no, ");
            sql.append(" msins.full_name, ");
            sql.append(" nvl (reempe.request_empe_amount,0) as request_empe_amount, ");
            sql.append(" nvl (reempe.refund_empe_amount,0) as refund_empe_amount, ");
            sql.append(" recomp.progress_status, ");
            sql.append(" LISTAGG(reper.refund_period, ',' ) WITHIN GROUP(ORDER BY reper.refund_period) As PAY_PERIOD ");
            sql.append(" FROM con_tb_refund_employee reempe");
            sql.append(" LEFT OUTER JOIN con_tb_refund_period   reper  ON ( reempe.refund_period_id = reper.refund_period_id )");
            sql.append(" LEFT OUTER JOIN con_ms_insurer         msins  ON ( msins.insurer_id = reempe.insurer_id ) ");
            sql.append(" LEFT OUTER JOIN con_tr_refund_company recomp  ON (recomp.refund_request_id = reper.refund_request_id) ");
            sql.append(" WHERE 1 = 1 ");


            Map<String, Object> param = new HashMap<>();

            if (!NumberUtils.isZeroOrNull(refundRequestId)) {
                sql.append(" AND  reper.refund_request_id = :refundRequestId ");
                param.put("refundRequestId",refundRequestId);
            }
            sql.append(" GROUP BY reper.refund_request_id,msins.insurer_id, msins.id_card_no, msins.full_name,");
            sql.append(" reempe.request_empe_amount, reempe.refund_empe_amount,recomp.progress_status");

            return queryForList(sql.toString(), param, new SplitEmployeeRefundPeriodRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }

    }

    public SplitEmployeeRefundBean getConTrRefundEmployee(Long refundRequestId) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT * FROM con_tr_refund_employee ");
            sql.append(" WHERE ");

            Map<String, Object> param = new HashMap<>();
            if (!NumberUtils.isZeroOrNull(refundRequestId)) {
                sql.append(" refund_request_id = :refundRequestId ");
                param.put("refundRequestId",refundRequestId);
            }

            return queryForObject(sql.toString(),param, new ConTrRefundEmployeeRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "con_tb_refund_period";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }
}
