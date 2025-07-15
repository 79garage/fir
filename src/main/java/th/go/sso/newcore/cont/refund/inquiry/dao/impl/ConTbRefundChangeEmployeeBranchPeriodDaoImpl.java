package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeBranchPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundChangeEmployeeBranchPeriodRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConTbRefundChangeEmployeeBranchPeriodDaoImpl extends DaoBase<Long> {
    public ConTbRefundChangeEmployeeBranchPeriodDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_CHANGE_EMPLOYEE_BRANCH_PERIOD";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_CHANGE_EMPLOYEE_BRANCH_PERIOD_ID";
    }

    public RefundChangeEmployeeBranchPeriodBean queryRefundChangeEmployeeBranchPeriod(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "select company_branch_code,a.company_no,a.refund_period_end_year,a.refund_period_end_month,a.refund_period_str_year" +
                ",a.refund_period_str_month,a.refund_request_change_type_id,a.source_data,a.refund_change_employee_branch_period_id,a.create_date" +
                ",a.create_by,a.refund_sickness_amount,a.refund_sickness_amount_change,a.refund_retirement_amount,a.refund_retirement_amount_change" +
                ",a.request_empe_amount,a.request_interest_amount,a.request_total_amount " +
                " from con_tb_refund_change_employee_branch_period a " +
                " where a.refund_request_change_type_id = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param,new ConTbRefundChangeEmployeeBranchPeriodRowMapper());
    }
}
