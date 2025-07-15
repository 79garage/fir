package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.SelectedEmployeeRowMapper;

@Repository
public class SelectedEmployeeExistsDaoImpl extends DaoBase<Long> {
    
    public SelectedEmployeeExistsDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<SelectedEmployeeBean> findSelectedEmployee(Long refundPeriodId) throws Exception {
        try {

            final StringBuilder sql = new StringBuilder();
            Map<String, Object> param = new HashMap<>();
            sql.append(" SELECT ");
            sql.append(" T1.REFUND_PERIOD_ID, ");
            sql.append(" T2.RECEIVE_COMPANY_ID, ");
            sql.append(" T5.ID_CARD_NO, ");
            sql.append(" T5.FULL_NAME, ");
            sql.append(" T3.TOTAL_WAGES, ");
            sql.append(" T3.REAL_WAGES, ");
            sql.append(" NVL( T3.REFUND_REAL_WAGES, 0 ) AS REFUND_REAL_WAGES, ");
            sql.append(" T3.CNTR_AMOUNT, ");
//            sql.append(" NVL(NULLIF(T1.REFUND_TOTAL_AMOUNT, 0 ), NVL(T1.REQUEST_TOTAL_AMOUNT, 0 )) AS REQUEST_TOTAL_AMOUNT, ");
            sql.append(" T1.REQUEST_TOTAL_AMOUNT, ");
            sql.append(" T1.REFUND_TOTAL_AMOUNT, ");
            sql.append(" T3.REFUND_STATUS, ");
            sql.append(" T3.RECEIVE_EMPLOYEE_ID, ");
            sql.append(" T5.INSURER_ID, ");
            sql.append(" T3.INSURER_STATUS, ");
            sql.append(" T1.REFUND_EMPLOYEE_ID, ");
            sql.append(" T3.REFUND_STATUS ");
            sql.append(" FROM ");
            sql.append(" CON_TB_REFUND_EMPLOYEE  T1 ");
            sql.append(" LEFT JOIN CON_TB_REFUND_PERIOD    T2 ON T2.REFUND_PERIOD_ID = T1.REFUND_PERIOD_ID ");
            sql.append(" LEFT JOIN CON_TR_RECEIVE_EMPLOYEE T3 ON T3.RECEIVE_EMPLOYEE_ID = T1.RECEIVE_EMPLOYEE_ID ");
            sql.append(" LEFT JOIN CON_MS_INSURER          T5 ON T5.INSURER_ID = T1.INSURER_ID ");
            sql.append(" WHERE ");
            sql.append(" 1 = 1 ");
            sql.append(" AND T1.REFUND_PERIOD_ID = :refundPeriodId ");
            param.put("refundPeriodId",refundPeriodId);

//            sql.append(" SELECT  t1.refund_period_id,");
//            sql.append(" t2.receive_company_id, ");
//            sql.append(" t5.id_card_no, ");
//            sql.append(" t5.full_name, ");
//            sql.append(" t3.total_wages, ");
//            sql.append(" t3.real_wages, ");
//            sql.append(" nvl(t3.REFUND_REAL_WAGES,0) As REFUND_REAL_WAGES,");
//            sql.append(" t3.CNTR_AMOUNT,");
//            sql.append(" t1.request_empe_amount, ");
//            sql.append(" t1.request_interest_amount, ");
//            sql.append(" t3.refund_status, ");
//            sql.append(" t3.receive_employee_id, ");
//            sql.append(" t5.insurer_id, ");
//            sql.append(" t3.INSURER_STATUS,");
//            sql.append(" t1.refund_employee_id ");
//            sql.append(" FROM con_tb_refund_employee  t1 ");
//            sql.append(" LEFT  JOIN  con_tb_refund_period t2 ON (t2.REFUND_PERIOD_ID = t1.REFUND_PERIOD_ID) ");
//            sql.append(" LEFT  JOIN  con_tr_receive_employee t3 ON ( t3.RECEIVE_EMPLOYEE_ID =  t1.RECEIVE_EMPLOYEE_ID ) ");
//            sql.append(" LEFT JOIN con_tr_receive_company    t6 ON (t3.RECEIVE_COMPANY_ID = t6.RECEIVE_COMPANY_ID)");
//            sql.append(" LEFT  JOIN  con_tb_employment       t4 ON (  t4.insurer_id = t3.insurer_id AND t6.company_id = t4.company_id ) ");
//            sql.append(" LEFT  JOIN  con_ms_insurer          t5 ON (  t4.insurer_id = t5.insurer_id  )");
////            sql.append(" WHERE EXISTS (SELECT NULL FROM con_tr_receive_employee recempe WHERE recempe.receive_employee_id = t2.receive_employee_id )");
//            sql.append(" WHERE 1=1");
//            sql.append(" AND t4.resign_date IS NULL ");
//
//            Map<String, Object> param = new HashMap<>();
//
//            if (!NumberUtils.isZeroOrNull(refundPeriodId)){
//                sql.append(" AND t1.REFUND_PERIOD_ID = :refundPeriodId ");
//                param.put("refundPeriodId",refundPeriodId);
//            } else {
//                sql.append(" AND t1.REFUND_PERIOD_ID = 0 ");
//            }

            return queryForList(sql.toString(), param , new SelectedEmployeeRowMapper());

        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "con_tb_refund_employee";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }
}
