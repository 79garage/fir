package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundCompanyPeriodByIdRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundCompanyPeriodRowMapper;

@Repository
public class RefundCompanyPeriodDaoImpl extends DaoBase<Long> {
    
    public RefundCompanyPeriodDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<RefundCompanyPeriodBean> findReceiveCompanyPeriod(List<Long> receiveCompanyId, Long companyId) throws Exception{

        Map<String, Object> param = new HashMap<>();
        String sql = " WITH OVER_PAID AS ( "+
                " SELECT "+
                " TE.*, "+
                " TC.EMPR_OVER AS EMPR_OVERP1, "+
                " TC.INTEREST_PAID, "+
                " TC.OVER_INTEREST_PAID, "+
                " MC.COMPANY_NO, "+
                " MC.COMPANY_BRANCH_CODE, "+
                " MC.COMPANY_NAME, "+
                " MC.COMPANY_BRANCH_NAME "+
                " FROM "+
                " CON_TR_RECEIVE_COMPANY TC "+
                " LEFT JOIN ( "+
                " SELECT "+
                " EMP.RECEIVE_COMPANY_ID, "+
//                " EMP.REFUND_REASON_CODE, "+
                " COM.COMPANY_ID, "+
//                " EMP.RECEIPT_ID, "+
                " EMP.PAY_PERIOD, "+
                " EMP.PAY_PERIOD_MONTH, "+
                " EMP.PAY_PERIOD_YEAR, "+
                " COM.PAY_DATE                       AS PAYMENT_DATE, "+
                " SUM(EMP.TOTAL_WAGES)               AS TOTAL_WAGES, "+
                " SUM(EMP.CNTR_AMOUNT)               AS EMPE_AMOUNT, "+
                " SUM(EMP.CNTR_SICKNESS)             AS EMPE_CNTR_SICKNESS, "+
                " SUM(EMP.CNTR_RETIREMENT)           AS EMPE_CNTR_RETIREMENT, "+
                " SUM(EMP.CNTR_UNEMPLOYMENT)         AS EMPE_CNTR_UNEMPLOYMENT, "+
                " SUM(EMP.EMPR_AMOUNT)               AS EMPR_AMOUNT, "+
                " SUM(EMP.EMPR_CNTR_SICKNESS)        AS EMPR_CNTR_SICKNESS, "+
                " SUM(EMP.EMPR_CNTR_RETIREMENT)      AS EMPR_CNTR_RETIREMENT, "+
                " SUM(EMP.EMPR_CNTR_UNEMPLOYMENT)    AS EMPR_CNTR_UNEMPLOYMENT, "+
//                " SUM(EMP.EMPR_OVERP)                AS EMPR_OVERP1, "+
                " SUM(EMP.EMPE_OVERP)                AS EMPE_OVERP, "+
                " SUM(EMP.EMPR_CAL)                  AS EMPR_CAL, "+
                " SUM(EMP.EMPE_CAL)                  AS EMPE_CAL, "+
                " EMP.PAY_STATUS, "+
                " SUBSTR(COMP.SSO_BRANCH_CODE, 0, 2) AS PROVINCE_CODE, "+
                " TO_DATE((EMP.PAY_PERIOD_YEAR - 543) || EMP.PAY_PERIOD_MONTH || '01', 'YYYYMMDD')               AS PERIOD_DATE "+
                " FROM "+
                " CON_TR_RECEIVE_EMPLOYEE EMP "+
                " JOIN CON_TR_RECEIVE_COMPANY COM ON EMP.RECEIVE_COMPANY_ID = COM.RECEIVE_COMPANY_ID "+
                " JOIN CON_MS_COMPANY         COMP ON COM.COMPANY_ID = COMP.COMPANY_ID "+
                " WHERE 1 = 1"+
                " AND EMP.RECEIVE_COMPANY_ID IN ( :receiveCompanyId ) "+
                " AND EMP.REFUND_REASON_CODE IS NOT NULL "+
                " GROUP BY "+
                " EMP.RECEIVE_COMPANY_ID, "+
//                " EMP.REFUND_REASON_CODE, "+
                " COM.COMPANY_ID, "+
//                " EMP.RECEIPT_ID, "+
                " EMP.PAY_PERIOD, "+
                " EMP.PAY_PERIOD_MONTH, "+
                " EMP.PAY_PERIOD_YEAR, "+
                " COM.PAY_DATE, "+
                " EMP.PAY_STATUS, "+
                " COMP.SSO_BRANCH_CODE, "+
                " EMP.PAY_PERIOD_YEAR, "+
                " EMP.PAY_PERIOD_MONTH "+
                " )                      TE ON TE.RECEIVE_COMPANY_ID = TC.RECEIVE_COMPANY_ID "+
                " LEFT JOIN CON_MS_COMPANY MC ON MC.COMPANY_ID = TC.COMPANY_ID "+
                " WHERE 1 = 1 "+
                " AND TC.RECEIVE_COMPANY_ID IN ( :receiveCompanyId ) "+
                " AND TC.REFUND_REASON_CODE IS NOT NULL "+
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
                " OV.RECEIVE_COMPANY_ID, "+
//                " OV.RECEIPT_ID, "+
                " OV.PAY_PERIOD, "+
                " OV.PAY_PERIOD_MONTH, "+
                " OV.PAY_PERIOD_YEAR, "+
                " OV.COMPANY_NO, "+
                " OV.COMPANY_BRANCH_CODE, "+
                " OV.COMPANY_NAME, "+
                " OV.COMPANY_BRANCH_NAME, "+
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
                " OV.EMPR_OVERP1 AS EMPR_OVERP, "+
                " OV.EMPE_OVERP, "+
                " OV.INTEREST_PAID, "+
                " OV.OVER_INTEREST_PAID, "+
                " OV.EMPR_CAL, "+
                " OV.EMPE_CAL, "+
                " OV.PAY_STATUS, "+
                " OV.PROVINCE_CODE, "+
                " COALESCE(TO_NUMBER(DC.EMPR_CNTR_RATE), TO_NUMBER(CNTR.EMPR_CNTR_RATE), TO_NUMBER(CNTR1.EMPR_CNTR_RATE)) AS REAL_EMPR_CNTR_RATE, "+
                " COALESCE(TO_NUMBER(DC.EMPR_SICKNESS_RATE), TO_NUMBER(CNTR.EMPR_SICKNESS_RATE), TO_NUMBER(CNTR1.EMPR_SICKNESS_RATE)) AS REAL_EMPR_SICKNESS_RATE, "+
                " COALESCE(TO_NUMBER(DC.EMPR_RETIREMENT_RATE), TO_NUMBER(CNTR.EMPR_RETIREMENT_RATE), TO_NUMBER(CNTR1.EMPR_RETIREMENT_RATE)) AS REAL_EMPR_RETIREMENT_RATE, "+
                " COALESCE(TO_NUMBER(DC.EMPR_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR.EMPR_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR1.EMPR_UNEMPLOYMENT_RATE)) AS REAL_EMPR_UNEMPLOYMENT_RATE, "+
                " EMPR_CAL - ((ROUND(OV.TOTAL_WAGES * (COALESCE(TO_NUMBER(DC.EMPR_RETIREMENT_RATE), TO_NUMBER(CNTR.EMPR_RETIREMENT_RATE), TO_NUMBER(CNTR1.EMPR_RETIREMENT_RATE)) / 100), 2)) + "+
                " (ROUND(OV.TOTAL_WAGES * (COALESCE(TO_NUMBER(DC.EMPR_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR.EMPR_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR1.EMPR_UNEMPLOYMENT_RATE)) / 100), 2))) AS CAL_EMPR_SICKNESS, "+
                " ROUND(OV.TOTAL_WAGES * (COALESCE(TO_NUMBER(DC.EMPR_RETIREMENT_RATE), TO_NUMBER(CNTR.EMPR_RETIREMENT_RATE), TO_NUMBER(CNTR1.EMPR_RETIREMENT_RATE)) / 100), 2) AS CAL_EMPR_RETIREMENT, "+
                " ROUND(OV.TOTAL_WAGES * (COALESCE(TO_NUMBER(DC.EMPR_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR.EMPR_UNEMPLOYMENT_RATE), TO_NUMBER(CNTR1.EMPR_UNEMPLOYMENT_RATE)) / 100), 2) AS CAL_EMPR_UNEMPLOYMENT, "+
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
                " RECEIVE_COMPANY_ID, "+
//                " RECEIPT_ID, "+
                " PAY_PERIOD, "+
                " PAY_PERIOD_MONTH, "+
                " PAY_PERIOD_YEAR, "+
                " COMPANY_NO, "+
                " COMPANY_BRANCH_CODE, "+
                " COMPANY_NAME, "+
                " COMPANY_BRANCH_NAME, "+
                " PAYMENT_DATE, "+
                " TOTAL_WAGES, "+
                " EMPE_AMOUNT, "+
                " EMPR_AMOUNT, "+
                " EMPR_OVERP, "+
                " EMPE_OVERP, "+
                " REAL_EMPR_CNTR_RATE,REAL_EMPR_SICKNESS_RATE,REAL_EMPR_RETIREMENT_RATE,REAL_EMPR_UNEMPLOYMENT_RATE, "+
                " EMPE_CNTR_SICKNESS,EMPE_CNTR_RETIREMENT,EMPE_CNTR_UNEMPLOYMENT, "+
                " CAL_EMPE_SICKNESS,CAL_EMPE_RETIREMENT,CAL_EMPE_UNEMPLOYMENT, "+
                " EMPR_CNTR_SICKNESS,EMPR_CNTR_RETIREMENT,EMPR_CNTR_UNEMPLOYMENT, "+
                " CAL_EMPR_SICKNESS,CAL_EMPR_RETIREMENT,CAL_EMPR_UNEMPLOYMENT, "+
                " EMPE_CNTR_SICKNESS - CAL_EMPE_SICKNESS AS REFUND_EMPE_SICKNESS, "+
                " EMPE_CNTR_RETIREMENT - CAL_EMPE_RETIREMENT AS REFUND_EMPE_RETIREMENT, "+
                " EMPE_CNTR_UNEMPLOYMENT - CAL_EMPE_UNEMPLOYMENT AS REFUND_EMPE_UNEMPLOYMENT, "+
                " EMPR_CNTR_SICKNESS - CAL_EMPR_SICKNESS AS REFUND_EMPR_SICKNESS, "+
                " EMPR_CNTR_RETIREMENT - CAL_EMPR_RETIREMENT AS REFUND_EMPR_RETIREMENT, "+
                " EMPR_CNTR_UNEMPLOYMENT - CAL_EMPR_UNEMPLOYMENT AS REFUND_EMPR_UNEMPLOYMENT, "+
                " INTEREST_PAID, "+
                " OVER_INTEREST_PAID, "+
                " EMPR_CAL, "+
                " EMPE_CAL, "+
                " PAY_STATUS "+
                " FROM CNTR_CAL "+
                " ) "+
                " SELECT "+
                " RECEIVE_COMPANY_ID, "+
                " PAY_PERIOD, "+
                " COMPANY_NO, "+
                " COMPANY_BRANCH_CODE, "+
                " COMPANY_NAME, "+
                " COMPANY_BRANCH_NAME, "+
                " PAYMENT_DATE, "+
                " SUM(EMPE_AMOUNT) + SUM(EMPE_AMOUNT) AS CNTR_AMOUNT, "+
                " SUM(EMPR_OVERP) AS EMPR_OVER, "+
                " SUM(EMPE_OVERP) AS EMPE_OVER, "+
                " REAL_EMPR_CNTR_RATE,REAL_EMPR_SICKNESS_RATE,REAL_EMPR_RETIREMENT_RATE,REAL_EMPR_UNEMPLOYMENT_RATE, "+
                " EMPE_CNTR_SICKNESS,EMPE_CNTR_RETIREMENT,EMPE_CNTR_UNEMPLOYMENT, "+
                " EMPR_CNTR_SICKNESS,EMPR_CNTR_RETIREMENT,EMPR_CNTR_UNEMPLOYMENT, "+
                " CAL_EMPE_SICKNESS,CAL_EMPE_RETIREMENT,CAL_EMPE_UNEMPLOYMENT, "+
                " CAL_EMPR_SICKNESS,CAL_EMPR_RETIREMENT,CAL_EMPR_UNEMPLOYMENT, "+
                " SUM(REFUND_EMPE_SICKNESS) AS REFUND_EMPE_SICKNESS, "+
                " SUM(REFUND_EMPE_RETIREMENT) AS REFUND_EMPE_RETIREMENT, "+
                " SUM(REFUND_EMPE_UNEMPLOYMENT) AS REFUND_EMPE_UNEMPLOYMENT, "+
                " SUM(REFUND_EMPR_SICKNESS) AS REFUND_EMPR_SICKNESS, "+
                " SUM(REFUND_EMPR_RETIREMENT) AS REFUND_EMPR_RETIREMENT, "+
                " SUM(REFUND_EMPR_UNEMPLOYMENT) AS REFUND_EMPR_UNEMPLOYMENT, "+
                " ROUND(SUM(REFUND_EMPE_SICKNESS) + SUM(REFUND_EMPR_SICKNESS), 0) AS SUM_REFUND_SICKNESS, "+
                " ROUND(SUM(REFUND_EMPE_RETIREMENT) + SUM(REFUND_EMPR_RETIREMENT), 0) AS SUM_REFUND_RETIREMENT, "+
                " ROUND(SUM(REFUND_EMPE_UNEMPLOYMENT) + SUM(REFUND_EMPR_UNEMPLOYMENT), 0) AS SUM_REFUND_UNEMPLOYMENT, "+
                " SUM(INTEREST_PAID) AS INTEREST_PAID, "+
                " SUM(OVER_INTEREST_PAID) AS OVER_INTEREST_PAID, "+
                " NULL AS PROGRESS_STATUS "+
                " FROM REFUND_CNTR "+
                " GROUP BY "+
                " RECEIVE_COMPANY_ID, "+
                " PAY_PERIOD, "+
                " COMPANY_NO, "+
                " COMPANY_BRANCH_CODE, "+
                " COMPANY_NAME, "+
                " COMPANY_BRANCH_NAME, "+
                " PAYMENT_DATE, "+
                " REAL_EMPR_CNTR_RATE,REAL_EMPR_SICKNESS_RATE,REAL_EMPR_RETIREMENT_RATE,REAL_EMPR_UNEMPLOYMENT_RATE, "+
                " CAL_EMPE_SICKNESS,CAL_EMPE_RETIREMENT,CAL_EMPE_UNEMPLOYMENT, "+
                " CAL_EMPR_SICKNESS,CAL_EMPR_RETIREMENT,CAL_EMPR_UNEMPLOYMENT, "+
                " EMPE_CNTR_SICKNESS,EMPE_CNTR_RETIREMENT,EMPE_CNTR_UNEMPLOYMENT, "+
                " EMPR_CNTR_SICKNESS,EMPR_CNTR_RETIREMENT,EMPR_CNTR_UNEMPLOYMENT ";
        param.put("receiveCompanyId", receiveCompanyId);
        return queryForList(sql, param, new RefundCompanyPeriodRowMapper());
    }
    public List<RefundCompanyPeriodBean> findReceiveCompanyPeriodOverPart1(List<Long> receiveCompanyId) throws Exception {

        Map<String, Object> param = new HashMap<>();
        String sql = " WITH OVER_PAID AS ( "+
                " SELECT "+
                " TC.RECEIVE_COMPANY_ID, "+
                " TC.PAY_DATE                        AS PAYMENT_DATE, "+
                " SUM(NVL(CC.INTEREST_PAID, 0))      AS INTEREST_PAID, "+
                " SUM(NVL(CC.EMPR_OVER, 0))          AS EMPR_OVER, "+
                " SUM(NVL(CC.OVER_INTEREST_PAID, 0)) AS OVER_INTEREST_PAID, "+
                " SUM(NVL(0, 0))                     AS EMPE_OVER, "+
                " CC.COMPANY_ID, "+
                " COMP.COMPANY_NO, "+
                " COMP.COMPANY_BRANCH_CODE, "+
                " COMP.COMPANY_NAME, "+
                " COMP.COMPANY_BRANCH_NAME, "+
                " CC.RECEIPT_ID, "+
                " CC.PAY_PERIOD, "+
                " CC.PAY_PERIOD_MONTH, "+
                " CC.PAY_PERIOD_YEAR, "+
                " SUM(CC.CNTR_AMOUNT)                AS CNTR_AMOUNT, "+
                " SUM(CC.CALC_CNTR)                  AS CALC_CNTR, "+
//                " --        CC.EMPR_OVER, "+
                " SUBSTR(COMP.SSO_BRANCH_CODE, 0, 2) AS PROVINCE_CODE, "+
                " TO_DATE((CC.PAY_PERIOD_YEAR - 543) || CC.PAY_PERIOD_MONTH || '01', 'YYYYMMDD')               AS PERIOD_DATE "+
                " FROM "+
                " CON_TB_CONTRIBUTION_COMPANY CC "+
                " LEFT JOIN CON_TR_RECEIVE_COMPANY      TC ON TC.COMPANY_ID = CC.COMPANY_ID AND TC.PAY_PERIOD = CC.PAY_PERIOD "+
                " JOIN CON_MS_COMPANY              COMP ON CC.COMPANY_ID = COMP.COMPANY_ID "+
                " WHERE "+
                " 1 = 1 "+
                " AND TC.RECEIVE_COMPANY_ID IN ( :receiveCompanyId ) "+
                " GROUP BY "+
                " TC.RECEIVE_COMPANY_ID, "+
                " TC.PAY_DATE, "+
                " CC.COMPANY_ID, "+
                " CC.RECEIPT_ID, "+
                " CC.PAY_PERIOD, "+
                " CC.PAY_PERIOD_MONTH, "+
                " CC.PAY_PERIOD_YEAR, "+
                " COMP.COMPANY_NO, "+
                " COMP.COMPANY_BRANCH_CODE, "+
                " COMP.COMPANY_NAME, "+
                " COMP.COMPANY_BRANCH_NAME, "+
                " COMP.SSO_BRANCH_CODE "+
                " ), CNTR_RATE AS ( "+
                " SELECT "+
                " I.START_DATE, "+
                " I.END_DATE, "+
                " MAX(E3.VALUE1) + MAX(E2.VALUE1) + MAX(E1.VALUE1)                                                    AS EMPE_CNTR_RATE, "+
                " MAX(E3.VALUE1)                                                                                      AS EMPE_SICKNESS_RATE, "+
                " MAX(E2.VALUE1)                                                                                      AS EMPE_RETIREMENT_RATE, "+
                " MAX(E1.VALUE1)                                                                                      AS EMPE_UNEMPLOYMENT_RATE, "+
                " MAX(C3.VALUE1) + MAX(C2.VALUE1) + MAX(C1.VALUE1)                                                    AS EMPR_CNTR_RATE, "+
                " MAX(C3.VALUE1)                                                                                      AS EMPR_SICKNESS_RATE, "+
                " MAX(C2.VALUE1)                                                                                      AS EMPR_RETIREMENT_RATE, "+
                " MAX(C1.VALUE1)                                                                                      AS EMPR_UNEMPLOYMENT_RATE, "+
                " MAX(E3.VALUE1) + MAX(E2.VALUE1) + MAX(E1.VALUE1) + MAX(C3.VALUE1) + MAX(C2.VALUE1) + MAX(C1.VALUE1) AS CNTR_RATE, "+
                " MAX(E3.VALUE1) + MAX(C3.VALUE1)                                                                     AS SICKNESS_RATE, "+
                " MAX(E2.VALUE1) + MAX(C2.VALUE1)                                                                     AS RETIREMENT_RATE, "+
                " MAX(E1.VALUE1) + MAX(C1.VALUE1)                                                                     AS UNEMPLOYMENT_RATE, "+
                " I.IMFO_FLAG, "+
                " I.CONFIG_RATE_GROUP, "+
                " I.ACROSS_COUNTRY, "+
                " CTAA.PROVINCE_CODE "+
                " FROM "+
                " CON_TB_INFORMATION      I "+
                " LEFT JOIN OVER_PAID OV ON I.START_DATE <= OV.PERIOD_DATE AND ( I.END_DATE IS NULL OR I.END_DATE >= OV.PERIOD_DATE ) "+
                " LEFT JOIN CON_TB_INFORMATION      E1 ON E1.SUBJECT = 'UNEMPLOYMENT' "+
                " AND E1.GROUP_CODE = 'EMPLOYEE_RATE' "+
                " AND E1.SYS_CODE = I.SYS_CODE "+
                " AND E1.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION      E2 ON E2.SUBJECT = 'RETIREMENT' "+
                " AND E2.GROUP_CODE = 'EMPLOYEE_RATE' "+
                " AND E2.SYS_CODE = I.SYS_CODE "+
                " AND E2.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION      E3 ON E3.SUBJECT = 'SICKNESS' "+
                " AND E3.GROUP_CODE = 'EMPLOYEE_RATE' "+
                " AND E3.SYS_CODE = I.SYS_CODE "+
                " AND E3.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION      C1 ON C1.SUBJECT = 'UNEMPLOYMENT' "+
                " AND C1.GROUP_CODE = 'COMPANY_RATE' "+
                " AND C1.SYS_CODE = I.SYS_CODE "+
                " AND C1.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION      C2 ON C2.SUBJECT = 'RETIREMENT' "+
                " AND C2.GROUP_CODE = 'COMPANY_RATE' "+
                " AND C2.SYS_CODE = I.SYS_CODE "+
                " AND C2.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_INFORMATION      C3 ON C3.SUBJECT = 'SICKNESS' "+
                " AND C3.GROUP_CODE = 'COMPANY_RATE' "+
                " AND C3.SYS_CODE = I.SYS_CODE "+
                " AND C3.START_DATE = I.START_DATE "+
                " LEFT JOIN CON_TB_APPLICABLE_AREAS CTAA ON CTAA.CONFIG_RATE_GROUP = I.CONFIG_RATE_GROUP "+
                " WHERE 1 = 1 "+
                " AND I.SYS_CODE = 'M33' "+
                " AND I.GROUP_CODE IN ( 'EMPLOYEE_RATE', 'COMPANY_RATE' ) "+
                " AND I.STATUS = 'A' "+
                " AND I.START_DATE <= OV.PERIOD_DATE "+
                " AND ( I.END_DATE IS NULL OR I.END_DATE >= OV.PERIOD_DATE ) "+
                " GROUP BY "+
                " I.START_DATE, "+
                " I.END_DATE, "+
                " I.IMFO_FLAG, "+
                " I.CONFIG_RATE_GROUP, "+
                " I.ACROSS_COUNTRY, "+
                " CTAA.PROVINCE_CODE "+
                " ), DISCOUNT_COMPANY_RATE AS ( "+
                " SELECT "+
                " DC.EFFECT_DATE, "+
                " DC.END_OF_EFFECT_DATE, "+
                " DC.COMPANY_ID, "+
                " DC.EMPR_CNTR_RATE, "+
                " DC.EMPR_SICKNESS_RATE, "+
                " DC.EMPR_RETIREMENT_RATE, "+
                " DC.EMPR_UNEMPLOYMENT_RATE, "+
                " DC.EMPE_CNTR_RATE, "+
                " DC.EMPE_SICKNESS_RATE, "+
                " DC.EMPE_RETIREMENT_RATE, "+
                " DC.EMPE_UNEMPLOYMENT_RATE, "+
                " DC.EMPR_CNTR_RATE + DC.EMPE_CNTR_RATE                 AS CNTR_RATE, "+
                " DC.EMPR_SICKNESS_RATE + DC.EMPE_SICKNESS_RATE         AS SICKNESS_RATE, "+
                " DC.EMPR_RETIREMENT_RATE + DC.EMPE_RETIREMENT_RATE     AS RETIREMENT_RATE, "+
                " DC.EMPR_UNEMPLOYMENT_RATE + DC.EMPE_UNEMPLOYMENT_RATE AS UNEMPLOYMENT_RATE "+
                " FROM "+
                " CON_TB_DISCOUNT_COMPANY_RATE DC "+
                " LEFT JOIN OVER_PAID                    OV ON OV.COMPANY_ID = DC.COMPANY_ID "+
                " AND OV.PERIOD_DATE BETWEEN DC.EFFECT_DATE AND NVL(DC.END_OF_EFFECT_DATE, SYSDATE) "+
                " ), CNTR_CAL AS ( "+
                " SELECT "+
//                " --        OV.CONTRIBUTION_COMPANY_ID, "+
                " OV.RECEIVE_COMPANY_ID, "+
                " NVL(CNTR.IMFO_FLAG, CNTR1.IMFO_FLAG)         AS IMFO_FLAG, "+
                " OV.PAYMENT_DATE, "+
                " NVL(TO_NUMBER(CNTR.EMPR_CNTR_RATE), "+
                " TO_NUMBER(CNTR1.EMPR_CNTR_RATE))         AS REAL_EMPR_CNTR_RATE, "+
                " NVL(TO_NUMBER(CNTR.EMPR_SICKNESS_RATE), "+
                " TO_NUMBER(CNTR1.EMPR_SICKNESS_RATE))     AS REAL_EMPR_SICKNESS_RATE, "+
                " NVL(TO_NUMBER(CNTR.EMPR_RETIREMENT_RATE), "+
                " TO_NUMBER(CNTR1.EMPR_RETIREMENT_RATE))   AS REAL_EMPR_RETIREMENT_RATE, "+
                " NVL(TO_NUMBER(CNTR.EMPR_UNEMPLOYMENT_RATE), "+
                " TO_NUMBER(CNTR1.EMPR_UNEMPLOYMENT_RATE)) AS REAL_EMPR_UNEMPLOYMENT_RATE, "+
                " OV.INTEREST_PAID, "+
                " OV.OVER_INTEREST_PAID, "+
                " OV.EMPE_OVER, "+
                " OV.COMPANY_ID, "+
                " OV.PAY_PERIOD, "+
                " OV.PAY_PERIOD_MONTH, "+
                " OV.PAY_PERIOD_YEAR, "+
                " OV.COMPANY_NO, "+
                " OV.COMPANY_BRANCH_CODE, "+
                " OV.COMPANY_NAME, "+
                " OV.COMPANY_BRANCH_NAME, "+
                " OV.EMPR_OVER, "+
                " OV.CNTR_AMOUNT, "+
                " OV.CNTR_AMOUNT - ( ( SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.RETIREMENT_RATE), "+
                " TO_NUMBER(CNTR.RETIREMENT_RATE), TO_NUMBER(CNTR1.RETIREMENT_RATE)) / 100), 2)) + "+
                " SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.UNEMPLOYMENT_RATE), "+
                " TO_NUMBER(CNTR.UNEMPLOYMENT_RATE), TO_NUMBER(CNTR1.UNEMPLOYMENT_RATE)) / 100), 2)) ) ) AS CNTR_SICKNESS, "+
                " CASE "+
                " WHEN OV.CNTR_AMOUNT >= SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.RETIREMENT_RATE), "+
                " TO_NUMBER(CNTR.RETIREMENT_RATE), TO_NUMBER(CNTR1.RETIREMENT_RATE)) / 100), 2)) THEN "+
                " SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.RETIREMENT_RATE), "+
                " TO_NUMBER(CNTR.RETIREMENT_RATE), TO_NUMBER(CNTR1.RETIREMENT_RATE)) / 100), 2)) "+
                " ELSE "+
                " OV.CNTR_AMOUNT "+
                " END                                          AS CNTR_RETIREMENT, "+
                " CASE "+
                " WHEN OV.CNTR_AMOUNT < ( SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.RETIREMENT_RATE), "+
                " TO_NUMBER(CNTR.RETIREMENT_RATE), "+
                " TO_NUMBER(CNTR1.RETIREMENT_RATE)) / 100), "+
                " 2)) + SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.UNEMPLOYMENT_RATE), "+
                " TO_NUMBER(CNTR.UNEMPLOYMENT_RATE), "+
                " TO_NUMBER(CNTR1.UNEMPLOYMENT_RATE)) / 100), "+
                " 2)) ) THEN "+
                " OV.CNTR_AMOUNT - SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.RETIREMENT_RATE), TO_NUMBER(CNTR.RETIREMENT_RATE), "+
                " TO_NUMBER(CNTR1.RETIREMENT_RATE)) / 100), 2)) "+
                " ELSE "+
                " SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.UNEMPLOYMENT_RATE), TO_NUMBER(CNTR.UNEMPLOYMENT_RATE), "+
                " TO_NUMBER(CNTR1.UNEMPLOYMENT_RATE)) / 100), 2)) END AS CNTR_UNEMPLOYMENT, "+
                " OV.CALC_CNTR, "+
                " OV.CALC_CNTR - ( SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.RETIREMENT_RATE), "+
                " TO_NUMBER(CNTR.RETIREMENT_RATE), TO_NUMBER(CNTR1.RETIREMENT_RATE)) / 100), 2)) + "+
                " SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.UNEMPLOYMENT_RATE), "+
                " TO_NUMBER(CNTR.UNEMPLOYMENT_RATE), TO_NUMBER(CNTR1.UNEMPLOYMENT_RATE)) / 100), 2)) ) AS CAL_SICKNESS, "+
                " SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.RETIREMENT_RATE), "+
                " TO_NUMBER(CNTR.RETIREMENT_RATE), TO_NUMBER(CNTR1.RETIREMENT_RATE)) / 100), 2)) AS CAL_RETIREMENT, "+
                " SUM(ROUND(TRE.TOTAL_WAGES *(COALESCE(TO_NUMBER(DC.UNEMPLOYMENT_RATE), "+
                " TO_NUMBER(CNTR.UNEMPLOYMENT_RATE), TO_NUMBER(CNTR1.UNEMPLOYMENT_RATE)) / 100), 2)) AS CAL_UNEMPLOYMENT "+
                " FROM "+
                " OVER_PAID OV "+
                " JOIN CON_TR_RECEIVE_COMPANY  TRC ON OV.COMPANY_ID = TRC.COMPANY_ID AND OV.PAY_PERIOD = TRC.PAY_PERIOD "+
                " JOIN CON_TR_RECEIVE_EMPLOYEE TRE ON TRC.RECEIVE_COMPANY_ID = TRE.RECEIVE_COMPANY_ID AND OV.RECEIPT_ID = TRE.RECEIPT_ID "+
                " LEFT JOIN DISCOUNT_COMPANY_RATE   DC ON OV.COMPANY_ID = DC.COMPANY_ID "+
                " AND OV.PERIOD_DATE BETWEEN DC.EFFECT_DATE AND NVL(DC.END_OF_EFFECT_DATE, SYSDATE) "+
                " LEFT JOIN CNTR_RATE               CNTR ON CNTR.START_DATE <= OV.PERIOD_DATE "+
                " AND ( CNTR.END_DATE IS NULL OR CNTR.END_DATE >= OV.PERIOD_DATE ) "+
                " AND CNTR.PROVINCE_CODE = OV.PROVINCE_CODE "+
                " AND CNTR.ACROSS_COUNTRY = '1' "+
                " LEFT JOIN CNTR_RATE               CNTR1 ON CNTR1.START_DATE <= OV.PERIOD_DATE "+
                " AND ( CNTR1.END_DATE IS NULL OR CNTR1.END_DATE >= OV.PERIOD_DATE ) "+
                " AND CNTR1.ACROSS_COUNTRY = '0' "+
                " GROUP BY "+
                " OV.RECEIVE_COMPANY_ID, "+
                " CNTR.IMFO_FLAG, "+
                " CNTR1.IMFO_FLAG, "+
                " OV.PAYMENT_DATE, "+
                " CNTR.EMPR_CNTR_RATE, "+
                " CNTR1.EMPR_CNTR_RATE, "+
                " CNTR.EMPR_SICKNESS_RATE, "+
                " CNTR1.EMPR_SICKNESS_RATE, "+
                " CNTR.EMPR_RETIREMENT_RATE, "+
                " CNTR1.EMPR_RETIREMENT_RATE, "+
                " CNTR.EMPR_UNEMPLOYMENT_RATE, "+
                " CNTR1.EMPR_UNEMPLOYMENT_RATE, "+
                " OV.INTEREST_PAID, "+
                " OV.OVER_INTEREST_PAID, "+
                " OV.EMPE_OVER, "+
                " OV.COMPANY_ID, "+
                " OV.PAY_PERIOD, "+
                " OV.PAY_PERIOD_MONTH, "+
                " OV.PAY_PERIOD_YEAR, "+
                " OV.COMPANY_NO, "+
                " OV.COMPANY_BRANCH_CODE, "+
                " OV.COMPANY_NAME, "+
                " OV.COMPANY_BRANCH_NAME, "+
                " OV.CNTR_AMOUNT, "+
                " OV.CALC_CNTR, "+
                " OV.EMPR_OVER "+
                " ) "+
                " SELECT "+
                " RECEIVE_COMPANY_ID, "+
                " IMFO_FLAG, "+
                " PAYMENT_DATE, "+
                " COMPANY_ID, "+
                " PAY_PERIOD, "+
                " PAY_PERIOD_MONTH, "+
                " PAY_PERIOD_YEAR, "+
                " COMPANY_NO, "+
                " COMPANY_BRANCH_CODE, "+
                " COMPANY_NAME, "+
                " COMPANY_BRANCH_NAME, "+
                " INTEREST_PAID, "+
                " OVER_INTEREST_PAID, "+
                " EMPE_OVER, "+
                " EMPR_OVER, "+
                " CNTR_AMOUNT, "+
                " REAL_EMPR_CNTR_RATE, "+
                " REAL_EMPR_SICKNESS_RATE, "+
                " REAL_EMPR_RETIREMENT_RATE, "+
                " REAL_EMPR_UNEMPLOYMENT_RATE, "+
//                " CNTR_SICKNESS - CAL_SICKNESS         AS REFUND_SICKNESS, "+
//                " CNTR_RETIREMENT - CAL_RETIREMENT     AS REFUND_RETIREMENT, "+
//                " CNTR_UNEMPLOYMENT - CAL_UNEMPLOYMENT AS REFUND_UNEMPLOYMENT, "+
                " CNTR_SICKNESS - CAL_SICKNESS         AS SUM_REFUND_SICKNESS, "+
                " CNTR_RETIREMENT - CAL_RETIREMENT     AS SUM_REFUND_RETIREMENT, "+
                " CNTR_UNEMPLOYMENT - CAL_UNEMPLOYMENT AS SUM_REFUND_UNEMPLOYMENT, "+
                " NULL                                 AS PROGRESS_STATUS "+
                " FROM "+
                " CNTR_CAL ";

        param.put("receiveCompanyId", receiveCompanyId);
        return queryForList(sql, param, new RefundCompanyPeriodRowMapper());
    }

    public List<RefundCompanyPeriodBean> searchRefundRequestId(Long refundRequestId, Long companyId) throws Exception {

        try {
            final StringBuilder sql = new StringBuilder();
            final Map<String, Object> param = new HashMap<>();

            sql.append("SELECT refperd.refund_period,");
            sql.append(" refperd.refund_request_id,");
            sql.append(" refperd.receive_company_id,");
            sql.append(" nvl(rcomp.cntr_amount, 0)                  AS cntr_amount,");
            sql.append(" nvl(rcomp.interest_paid , 0)               AS interest_paid,");
            sql.append(" nvl(rcomp.empe_cntr, 0)                    AS empe_amount,");
            sql.append(" nvl(rcomp.empr_cntr, 0)                    AS empr_amount,");
            sql.append(" nvl(rcomp.cntr_amount, 0)                  AS total_amount,");
            sql.append(" nvl(rcomp.interest_amount , 0)             AS interest_amount,");
            sql.append(" refperd.REFUND_PERIOD_ID,");
            sql.append(" nvl (refperd.request_empe_amount,0) As request_empe_amount, ");
            sql.append(" nvl (refperd.request_empr_amount,0) As request_empr_amount, ");
            sql.append(" nvl (refperd.request_total_amount,0) As request_total_amount, ");
            sql.append(" nvl (refperd.request_interest_amount,0) As request_interest_amount, ");
            sql.append(" nvl (refperd.request_over_paid_amount,0) As request_over_paid_amount, ");
            sql.append(" nvl (refperd.refund_empe_amount,0) As refund_empe_amount, ");
            sql.append(" nvl (refperd.refund_empr_amount,0) As refund_empr_amount, ");
            sql.append(" refperd.refund_interest_amount,");
            sql.append(" nvl (refperd.refund_over_paid_amount,0) As refund_over_paid_amount, ");
            sql.append(" nvl (refperd.refund_total_amount,0) As refund_total_amount, ");
            sql.append(" refperd.refund_sickness_amount,");
            sql.append(" refperd.refund_retirement_amount,");
            sql.append(" refperd.refund_unemployment_amount,");
            sql.append(" refcomp.progress_status,");
            sql.append(" refperd.APPROVE_STATUS,");
            sql.append(" refperd.refund_sickness_rate,");
            sql.append(" refperd.refund_retirement_rate,");
            sql.append(" refperd.refund_unemployment_rate,");
            sql.append(" ctbrc.order_no,");
            sql.append(" ctbrc.order_status,");
            sql.append(" ctbrc.order_remark,");
            sql.append(" refperd.disapprove_reason,");
            sql.append(" rcomp.pay_date AS payment_date,");
            sql.append(" mc.company_no,");
            sql.append(" mc.company_name,");
            sql.append(" mc.company_branch_code,");
            sql.append(" mc.company_branch_name");
//            sql.append(" to_date(to_char(refperd.refund_period_month || '/' || to_number(refperd.refund_period_year - 543)), 'MM/YYYY') AS date_temp");
            sql.append(" FROM con_tb_refund_request rereq");
            sql.append(" LEFT JOIN con_tb_refund_period refperd ON (refperd.refund_request_id = rereq.refund_request_id AND refperd.receive_company_id IS NOT NULL AND ((refperd.STATUS != 'D' AND refperd.STATUS != 'C') OR refperd.STATUS IS NULL))");
            sql.append(" LEFT JOIN con_tr_refund_company refcomp ON (refcomp.refund_request_id = refperd.refund_request_id)");
            sql.append(" LEFT JOIN con_tr_receive_company rcomp ON (refperd.receive_company_id = rcomp.receive_company_id)");
            sql.append(" LEFT JOIN con_tb_requester_company ctbrc ON ctbrc.period = refperd.refund_period AND ctbrc.refund_request_id = rereq.refund_request_id AND ctbrc.requester_type = '1'");
            sql.append(" LEFT JOIN con_ms_company           mc ON mc.company_id = rcomp.company_id");
            sql.append(" WHERE  1 = 1");

            if (!NumberUtils.isZeroOrNull(refundRequestId)) {
                sql.append(" AND refcomp.refund_request_id = :refundRequestId ");
                param.put("refundRequestId",refundRequestId);
            }
//            if (!NumberUtils.isZeroOrNull(companyId)) {
//                sql.append(" AND rereq.company_id = :companyId");
//                param.put("companyId", companyId);
//            }
            sql.append(" ORDER BY TO_DATE(refperd.refund_period, 'MM/YYYY') DESC");

            return queryForList(sql.toString(), param, new RefundCompanyPeriodByIdRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "con_tb_refund_request";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }
}
