package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundChangeEmployeeRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConTbRefundChangeEmployeeDaoImpl extends DaoBase<Long> {
    public ConTbRefundChangeEmployeeDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_CHANGE_EMPLOYEE";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_REQUEST_CHANGE_EMPLOYEE_ID";
    }

    public RefundChangeEmployeeBean queryRefundChangeEmployee(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "select refund_bank_branch_code,a.refund_bank_branch_code_change,a.refund_bank_code" +
                ",a.refund_bank_code_change,a.department_receive_code,a.department_receive_code_change" +
                ",a.refund_bank_account,a.refund_bank_account_change,a.section,a.section_change,a.receive_date,a.receive_date_change" +
                ",a.receive_no_change,a.period_end_year,a.period_end_month,a.period_str_year,a.period_str_month" +
                ",a.period_end_year_change,a.period_end_month_change,a.period_str_year_change,a.period_str_month_change" +
                ",a.refund_type_place,a.refund_type_place_change,a.request_empe_amount,a.request_empe_amount_change" +
                ",a.request_reason_code,a.request_reason_code_change,a.id_card_no,a.refund_request_change_type_id" +
                ",a.source_data,a.refund_request_change_employee_id,a.create_date,a.create_by,a.request_reason_other" +
                ",a.request_reason_other_change,a.refund_remark,a.refund_remark_change,a.refund_po_order,a.refund_po_order_name" +
                ",a.refund_prompt_pay,a.refund_po_order_change,a.refund_po_order_name_change,a.refund_prompt_pay_change " +
                " from con_tb_refund_change_employee a " +
                " where a.refund_request_change_type_id = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param,new ConTbRefundChangeEmployeeRowMapper());
    }
}
