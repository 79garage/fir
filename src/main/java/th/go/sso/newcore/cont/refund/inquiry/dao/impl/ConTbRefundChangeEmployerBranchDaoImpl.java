package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployerBranchBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundChangeEmployerBranchRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConTbRefundChangeEmployerBranchDaoImpl extends DaoBase<Long> {
    public ConTbRefundChangeEmployerBranchDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_CHANGE_EMPLOYER_BRANCH";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_CHANGE_EMPLOYER_BRANCH_ID";
    }

    public RefundChangeEmployerBranchBean queryRefundChangeEmployer(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "select company_branch_code,a.approve_status,a.approve_status_change,a.refund_period_year,a.refund_period_month,a.refund_amount_empe" +
                ",a.refund_amount_empr,a.refund_amount_empe_change,a.refund_amount_empr_change,a.request_amount_empe,a.request_amount_empr" +
                ",a.request_amount_empe_change,a.request_amount_empr_change,a.refund_request_change_type_id,a.source_data,a.refund_change_employer_branch_id" +
                ",a.create_date,a.create_by " +
                " from con_tb_refund_change_employer_branch a " +
                " where a.refund_request_change_type_id = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param,new ConTbRefundChangeEmployerBranchRowMapper());
    }
}
