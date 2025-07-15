package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.EmployeeDetailBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.EmployeeDetailRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundEmployeeRowMapper;

@Repository
public class RefundEmployeeDaoImpl extends DaoBase<Long> {


    public RefundEmployeeDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<RefundEmployeeBean> searchRefundEmployeeOver(Long receiveCompanyId) throws Exception{
        try {
            StringBuilder sql = new StringBuilder();
            Map<String, Object> param = new HashMap<>();
            sql.append(" SELECT * FROM  ");
            sql.append(" (SELECT ");
            sql.append(" cmsi.id_card_no, ");
            sql.append(" cmsi.full_name, ");
            sql.append(" ctbr.receipt_no, ");
            sql.append(" ctrre.total_wages, ");
            sql.append(" ctrre.real_wages, ");
            sql.append(" nvl( ctrre.refund_real_wages, 0 ) AS refund_real_wages, ");
            sql.append(" ctrre.cntr_amount, ");
//            sql.append(" ctrre.empe_overp, ");
            sql.append(" nvl( CASE WHEN ctrre.refund_reason_code IN('01', '02', '07', '09', '10', '11') THEN ctrre.cntr_amount ");
            sql.append(" WHEN ctrre.refund_reason_code IN('06', '04') THEN ctrre.empe_overp END, 0 ) AS empe_overp, ");
            sql.append(" nvl( ctrre.refund_amount, 0 ) AS refund_amount, ");
            sql.append(" ctrre.receive_employee_id, ");
            sql.append(" ctrre.insurer_status, ");
            sql.append(" ctrre.insurer_id, ");
            sql.append(" ctrre.refund_status, ");
            sql.append(" ctrre.refund_reason_code ");
            sql.append(" FROM con_tr_receive_employee ctrre ");
            sql.append(" LEFT JOIN con_tr_receive_company  ctrrc ON ( ctrre.receive_company_id = ctrrc.receive_company_id ) ");
            sql.append(" LEFT JOIN con_tb_receipt ctbr ON ctbr.receipt_id = ctrre.receipt_id ");
            sql.append(" LEFT JOIN ( SELECT eplm.insurer_id, eplm.company_id FROM con_tb_employment eplm JOIN ");
            sql.append(" ( SELECT emp.insurer_id, emp.company_id, MAX(emp.create_date) create_date FROM con_tb_employment emp WHERE emp.status = 'A' GROUP BY emp.insurer_id, emp.company_id ) mx ON ( eplm.insurer_id = mx.insurer_id ");
            sql.append(" AND eplm.company_id = mx.company_id ");
            sql.append(" AND eplm.create_date = mx.create_date ) ");
            sql.append(" ) ctbe ON ( ctbe.insurer_id = ctrre.insurer_id ");
            sql.append(" AND ctrrc.company_id = ctbe.company_id ) ");
            sql.append(" LEFT JOIN con_ms_insurer          cmsi ON ( cmsi.insurer_id = ctbe.insurer_id ) ");
            sql.append(" WHERE 1 = 1 ");
            sql.append(" AND ctrre.refund_reason_code IS NOT NULL ");
//            sql.append(" AND ( ctrre.empe_overp > 0 AND ctrre.empe_overp IS NOT NULL ) ");
            sql.append(" AND ctrre.status = 'A' ");
            sql.append(" AND ctrre.receive_company_id = :receiveCompanyId ");
            sql.append(" ) t0 ");
            sql.append(" WHERE 1 = 1 AND t0.refund_amount < t0.empe_overp ");

            param.put("receiveCompanyId", receiveCompanyId);

//            final StringBuilder sql = new StringBuilder();
//
//            sql.append("SELECT msins.id_card_no,");
//            sql.append(" msins.full_name,");
//            sql.append(" recempe.TOTAL_WAGES,");
//            sql.append(" recempe.REAL_WAGES,");
//            sql.append(" nvl(recempe.REFUND_REAL_WAGES,0) As REFUND_REAL_WAGES,");
//            sql.append(" recempe.cntr_amount,");
//            sql.append(" recempe.empe_overp,");
//            sql.append(" recempe.receive_employee_id,");
//            sql.append(" recempe.INSURER_STATUS,");
//            sql.append(" recempe.INSURER_ID");
//            sql.append(" FROM con_tr_receive_employee recempe");
//            sql.append(" LEFT JOIN con_tr_receive_company reccomp ON (recempe.RECEIVE_COMPANY_ID = reccomp.RECEIVE_COMPANY_ID)");
//            sql.append(" left join ( select eplm.insurer_id ,eplm.company_id  from con_tb_employment eplm  join( select  emp.insurer_id    ,emp.company_id    ,max(emp.CREATE_DATE) CREATE_DATE   from con_tb_employment emp   where emp.status = 'A' ");
//            sql.append(" GROUP BY emp.insurer_id ,emp.company_id ) mx on (eplm.insurer_id = mx.insurer_id and eplm.company_id = mx.company_id and eplm.create_date = mx.create_date)    ) tbemp on ( tbemp.insurer_id = recempe.insurer_id AND reccomp.company_id = tbemp.company_id ) ");
//            sql.append(" LEFT JOIN con_ms_insurer msins ON (msins.insurer_id = tbemp.insurer_id )  ");
//            sql.append(" WHERE 1 = 1");
//            sql.append(" AND ( recempe.empe_overp > 0 AND recempe.empe_overp IS NOT NULL )");
//            sql.append(" AND recempe.STATUS = 'A'");
//
//            Map<String, Object> param = new HashMap<>();
//
//            if (!NumberUtils.isZeroOrNull(receiveCompanyId)) {
//                sql.append(" AND recempe.receive_company_id = :receiveCompanyId");
//                param.put("receiveCompanyId", receiveCompanyId);
//            } else {
//                sql.append(" AND recempe.receive_company_id = 0 ");
//            }

            return queryForList(sql.toString(), param, new RefundEmployeeRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }
    public List<RefundEmployeeBean> searchOverRefundEmployee(Long refundPeriodId) throws Exception{
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
            sql.append(" T1.REQUEST_TOTAL_AMOUNT, ");
            sql.append(" T1.REFUND_TOTAL_AMOUNT AS empe_overp, ");
            sql.append(" T3.REFUND_STATUS, ");
            sql.append(" T3.RECEIVE_EMPLOYEE_ID, ");
            sql.append(" T5.INSURER_ID, ");
            sql.append(" T3.INSURER_STATUS, ");
            sql.append(" T1.REFUND_EMPLOYEE_ID, ");
            sql.append(" NULL AS RECEIPT_NO, ");
            sql.append(" NULL AS REFUND_STATUS, ");
            sql.append(" NULL AS REFUND_REASON_CODE ");
            sql.append(" FROM ");
            sql.append(" CON_TB_REFUND_EMPLOYEE  T1 ");
            sql.append(" LEFT JOIN CON_TB_REFUND_PERIOD    T2 ON T2.REFUND_PERIOD_ID = T1.REFUND_PERIOD_ID ");
            sql.append(" LEFT JOIN CON_TR_RECEIVE_EMPLOYEE T3 ON T3.RECEIVE_EMPLOYEE_ID = T1.RECEIVE_EMPLOYEE_ID ");
            sql.append(" LEFT JOIN CON_MS_INSURER          T5 ON T5.INSURER_ID = T1.INSURER_ID ");
            sql.append(" WHERE ");
            sql.append(" 1 = 1 ");
            sql.append(" AND T1.REFUND_PERIOD_ID = :refundPeriodId ");
            param.put("refundPeriodId",refundPeriodId);

//            final StringBuilder sql = new StringBuilder();
//
//            sql.append("SELECT msins.id_card_no,");
//            sql.append(" msins.full_name,");
//            sql.append(" recempe.TOTAL_WAGES,");
//            sql.append(" recempe.REAL_WAGES,");
//            sql.append(" nvl(recempe.REFUND_REAL_WAGES,0) As REFUND_REAL_WAGES,");
//            sql.append(" recempe.cntr_amount,");
//            sql.append(" recempe.empe_overp,");
//            sql.append(" recempe.receive_employee_id,");
//            sql.append(" recempe.INSURER_STATUS,");
//            sql.append(" recempe.INSURER_ID");
//            sql.append(" FROM con_tr_receive_employee recempe");
//            sql.append(" LEFT JOIN con_tr_receive_company reccomp ON (recempe.RECEIVE_COMPANY_ID = reccomp.RECEIVE_COMPANY_ID)");
//            sql.append(" left join ( select eplm.insurer_id ,eplm.company_id  from con_tb_employment eplm  join( select  emp.insurer_id    ,emp.company_id    ,max(emp.CREATE_DATE) CREATE_DATE   from con_tb_employment emp   where emp.status = 'A' ");
//            sql.append(" GROUP BY emp.insurer_id ,emp.company_id ) mx on (eplm.insurer_id = mx.insurer_id and eplm.company_id = mx.company_id and eplm.create_date = mx.create_date)    ) tbemp on ( tbemp.insurer_id = recempe.insurer_id AND reccomp.company_id = tbemp.company_id ) ");
//            sql.append(" LEFT JOIN con_ms_insurer msins ON (msins.insurer_id = tbemp.insurer_id )  ");
//            sql.append(" WHERE 1 = 1");
//            sql.append(" AND ( recempe.empe_overp > 0 AND recempe.empe_overp IS NOT NULL )");
//            sql.append(" AND recempe.STATUS = 'A'");
//
//            Map<String, Object> param = new HashMap<>();
//
//            if (!NumberUtils.isZeroOrNull(receiveCompanyId)) {
//                sql.append(" AND recempe.receive_company_id = :receiveCompanyId");
//                param.put("receiveCompanyId", receiveCompanyId);
//            } else {
//                sql.append(" AND recempe.receive_company_id = 0 ");
//            }
//
            return queryForList(sql.toString(), param, new RefundEmployeeRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public List<RefundEmployeeBean> searchRefundEmployee(Long receiveCompanyId) throws Exception{

        try {

            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT msins.id_card_no,");
            sql.append(" msins.full_name,");
            sql.append(" recempe.TOTAL_WAGES,");
            sql.append(" recempe.REAL_WAGES,");
            sql.append(" nvl(recempe.REFUND_REAL_WAGES,0) As REFUND_REAL_WAGES,");
            sql.append(" recempe.cntr_amount,");
            sql.append(" recempe.empe_overp,");
            sql.append(" recempe.receive_employee_id,");
            sql.append(" recempe.INSURER_STATUS,");
            sql.append(" recempe.INSURER_ID,");
            sql.append(" ctbr.receipt_no AS receipt_no,");
            sql.append(" recempe.refund_status,");
            sql.append(" recempe.refund_reason_code");
            sql.append(" FROM con_tr_receive_employee recempe ");
            sql.append(" LEFT JOIN con_tr_receive_company reccomp ON (recempe.RECEIVE_COMPANY_ID = reccomp.RECEIVE_COMPANY_ID)");
            sql.append(" LEFT JOIN con_tb_receipt ctbr ON ctbr.receipt_id = recempe.receipt_id");
            sql.append(" LEFT JOIN con_tb_employment    tbemp ON ( tbemp.insurer_id = recempe.insurer_id AND reccomp.company_id = tbemp.company_id)");
            sql.append(" LEFT JOIN con_ms_insurer          msins ON ( msins.insurer_id = tbemp.insurer_id ) ");
            sql.append(" WHERE 1 = 1");
            sql.append(" AND recempe.refund_reason_code IS NULL ");
//            sql.append(" AND ( recempe.empe_overp = 0 OR recempe.empe_overp IS NULL )");
            sql.append(" AND recempe.STATUS = 'A'");
            sql.append(" AND tbemp.resign_date IS NULL ");
            sql.append(" AND recempe.receive_company_id = :receiveCompanyId");

            Map<String, Object> param = new HashMap<>();
            param.put("receiveCompanyId", receiveCompanyId);

//            if (!NumberUtils.isZeroOrNull(receiveCompanyId)) {
//                sql.append(" AND recempe.receive_company_id = :receiveCompanyId");
//                param.put("receiveCompanyId", receiveCompanyId);
//            } else {
//                sql.append(" AND recempe.receive_company_id = 0 ");
//            }

            return queryForList(sql.toString(), param, new RefundEmployeeRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public List<EmployeeDetailBean> findConTrReceiveEmployee(Long companyId, Long receiveCompanyId) throws Exception {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        sql.append(" SELECT ");
        sql.append(" msins.id_card_no, ");
        sql.append(" msins.full_name, ");
        sql.append(" SUM(nvl(recempe.cntr_amount,0)) AS cntr_amount, ");
        sql.append(" SUM(nvl(recempe.empe_overp,0)) AS empe_overp, ");
        sql.append(" NULL AS receive_employee_id, ");
        sql.append(" NULL AS insurer_id, ");
        sql.append(" recempe.refund_reason_code ");
        sql.append(" FROM con_tr_receive_employee recempe ");
        sql.append(" LEFT JOIN con_tr_receive_company  reccomp ON ( recempe.receive_company_id = reccomp.receive_company_id ) ");
        sql.append(" LEFT JOIN ( SELECT eplm.insurer_id, eplm.company_id FROM con_tb_employment eplm JOIN ");
        sql.append(" ( SELECT emp.insurer_id, emp.company_id, MAX(emp.create_date) create_date FROM con_tb_employment emp WHERE emp.status = 'A' GROUP BY emp.insurer_id, emp.company_id ) mx ON ( eplm.insurer_id = mx.insurer_id ");
        sql.append(" AND eplm.company_id = mx.company_id ");
        sql.append(" AND eplm.create_date = mx.create_date ) ");
        sql.append(" ) tbemp ON ( tbemp.insurer_id = recempe.insurer_id AND reccomp.company_id = tbemp.company_id ) ");
        sql.append(" LEFT JOIN con_ms_insurer          msins ON ( msins.insurer_id = tbemp.insurer_id ) ");
        sql.append(" WHERE 1 = 1 ");
        sql.append(" AND recempe.status = 'A' ");
        sql.append(" AND tbemp.company_id = :companyId ");
        sql.append(" AND recempe.receive_company_id = :receiveCompanyId ");
        sql.append(" GROUP BY msins.id_card_no, msins.full_name, recempe.refund_reason_code ");
        sql.append(" ORDER BY msins.id_card_no ");

        param.put("companyId",companyId);
        param.put("receiveCompanyId",receiveCompanyId);


//        final StringBuilder sql = new StringBuilder();
//
//        sql.append(" SELECT msins.id_card_no, ");
//        sql.append(" msins.full_name, ");
//        sql.append(" recempe.cntr_amount, ");
//        sql.append(" recempe.empe_overp, ");
//        sql.append(" recempe.receive_employee_id, ");
//        sql.append(" recempe.insurer_id ");
//        sql.append(" FROM con_tr_receive_employee recempe ");
//        sql.append(" LEFT JOIN con_tr_receive_company reccomp ON (recempe.RECEIVE_COMPANY_ID = reccomp.RECEIVE_COMPANY_ID)");
//        sql.append(" left join ( select eplm.insurer_id ,eplm.company_id  from con_tb_employment eplm  join( select  emp.insurer_id    ,emp.company_id    ,max(emp.CREATE_DATE) CREATE_DATE   from con_tb_employment emp   where emp.status = 'A' ");
//        sql.append(" GROUP BY emp.insurer_id ,emp.company_id ) mx on (eplm.insurer_id = mx.insurer_id and eplm.company_id = mx.company_id and eplm.create_date = mx.create_date)    ) tbemp on ( tbemp.insurer_id = recempe.insurer_id AND reccomp.company_id = tbemp.company_id ) ");
//        sql.append(" LEFT JOIN con_ms_insurer          msins ON ( msins.insurer_id = tbemp.insurer_id ) ");
//        sql.append(" WHERE 1 = 1");
//        sql.append(" AND recempe.status = 'A' ");
//
//        Map<String, Object> param = new HashMap<>();
//        if (!NumberUtils.isZeroOrNull(companyId)) {
//            sql.append(" AND tbemp.company_id = :companyId");
//            param.put("companyId",companyId);
//        }
//        if (!NumberUtils.isZeroOrNull(receiveCompanyId)) {
//            sql.append("  AND recempe.receive_company_id = :receiveCompanyId ");
//            param.put("receiveCompanyId",receiveCompanyId);
//
//        }
        return queryForList(sql.toString(), param, new EmployeeDetailRowMapper());
    }

    public String getProgressStatusByRefundRequestId(Long refundRequestId) throws Exception {
        final String sql = "SELECT progress_status FROM con_tr_refund_employee WHERE refund_request_id = :refundRequestId";
        final Map<String, Object> param = new HashMap<>();
        param.put("refundRequestId", refundRequestId);
        return queryForObject(sql, param, String.class);
    }

    @Override
    protected String getTableName() {
        return "con_tr_receive_employee";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }
}
