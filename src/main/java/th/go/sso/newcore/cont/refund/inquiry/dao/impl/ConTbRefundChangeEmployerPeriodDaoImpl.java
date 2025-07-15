package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployerPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundChangeEmployerPeriodRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConTbRefundChangeEmployerPeriodDaoImpl extends DaoBase<Long> {
    public ConTbRefundChangeEmployerPeriodDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_CHANGE_EMPLOYER_PERIOD";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_CHANGE_EMPLOYER_PERIOD_ID";
    }

    public RefundChangeEmployerPeriodBean queryRefundChangeEmployerPeriod(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "select company_branch_code,a.approve_status,a.approve_status_change,a.total_wages_change,a.total_wages,a.refund_amount_empe" +
                ",a.refund_amount_empe_change,a.request_amount_empe,a.request_amount_empe_change,a.id_card_no,a.refund_request_change_type_id" +
                ",a.source_data,a.refund_change_employer_period_id,a.create_date,a.create_by,a.refund_period_year,a.refund_period_month,a.refund_sickness_amount" +
                ",a.refund_sickness_amount_change,a.refund_retirement_amount,a.refund_retirement_amount_change,a.request_interest_amount" +
                ",a.request_total_amount,a.refund_unemployment_amount,a.refund_unemployment_amount_change,a.refund_real_wages " +
                ",a.refund_real_wages_change,a.request_interest_amount_change " +
                "from con_tb_refund_change_employer_period a " +
                " where a.refund_request_change_type_id = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param,new ConTbRefundChangeEmployerPeriodRowMapper());
    }
}
