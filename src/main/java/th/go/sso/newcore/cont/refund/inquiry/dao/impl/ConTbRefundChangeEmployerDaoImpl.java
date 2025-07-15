package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployerBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundChangeEmployerRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConTbRefundChangeEmployerDaoImpl extends DaoBase<Long> {
    public ConTbRefundChangeEmployerDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_CHANGE_EMPLOYER";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_CHANGE_EMPLOYER_ID";
    }

    public RefundChangeEmployerBean queryRefundChangeEmployer(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "select company_branch_code,a.company_no,a.approve_status,a.approve_status_change,a.refund_bank_branch_code" +
                ",a.refund_bank_branch_code_change,a.refund_bank_code,a.refund_bank_code_change,a.department_receive_code,a.department_receive_code_change" +
                ",a.refund_bank_account,a.refund_bank_account_change,a.update_date,a.approve_date,a.approve_date_change,a.notice_date,a.notice_date_change" +
                ",a.notice_no,a.notice_no_change,a.receive_date,a.receive_date_change,a.refund_amount_empe,a.refund_amount_empr,a.refund_amount_empe_change" +
                ",a.refund_amount_empr_change,a.refund_type_place,a.refund_amount_int,a.refund_amount_int_change,a.refund_amount_oldage" +
                ",a.refund_amount_oldage_change,a.refund_amount_over,a.refund_amount_over_change,a.refund_amount_sick,a.refund_amount_sick_change" +
                ",a.refund_amount_unemp,a.refund_amount_unemp_change,a.refund_type_place_change,a.reply_date,a.reply_date_change,a.request_amount_empe" +
                ",a.request_amount_empr,a.request_amount_empe_change,a.request_amount_empr_change,a.request_reason_code,a.request_reason_code_change" +
                ",a.refund_request_change_type_id,a.source_data,a.refund_change_employer_id,a.create_date,a.create_by,a.refund_po_order" +
                ",a.refund_po_order_name,a.refund_prompt_pay,a.refund_po_order_change,a.refund_po_order_name_change,a.refund_prompt_pay_change" +
                ",a.period_end_month,a.period_end_month_change,a.period_end_year,a.period_end_year_change,a.period_str_month,a.period_str_month_change" +
                ",a.period_str_year,a.period_str_year_change,a.request_reason_other,a.request_reason_other_change,a.refund_remark" +
                ",a.refund_remark_change,a.refund_request_amount,a.refund_request_amount_change " +
                " from con_tb_refund_change_employer a " +
                " where a.refund_request_change_type_id = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param,new ConTbRefundChangeEmployerRowMapper());
    }
}
