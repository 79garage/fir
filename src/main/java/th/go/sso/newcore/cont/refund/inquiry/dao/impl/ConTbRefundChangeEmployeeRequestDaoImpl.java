package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundChangeEmployeeRequestRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConTbRefundChangeEmployeeRequestDaoImpl extends DaoBase<Long> {
    public ConTbRefundChangeEmployeeRequestDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_CHANGE_EMPLOYEE_REQUEST";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_CHANGE_EMPLOYEE_REQUEST_ID";
    }

    public RefundChangeEmployeeRequestBean queryRefundChangeEmployeeRequest(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "select approve_status,a.refund_bank_branch_code,a.refund_bank_code,a.department_receive_code,a.refund_bank_account" +
                ",a.approve_date,a.notice_date,a.notice_no,a.period_end_year,a.period_end_month,a.period_str_year,a.period_str_month,a.refund_period_end_year" +
                ",a.refund_period_str_year,a.refund_empe_amount,a.refund_type_place,a.reply_date,a.request_amount,a.request_reason_code,a.id_card_no" +
                ",a.refund_request_change_type_id,a.source_data,a.refund_change_employee_request_id" +
                ",a.create_date,a.create_by,a.refund_period_end_month,a.refund_period_str_month " +
                " from con_tb_refund_change_employee_request a  " +
                " where a.refund_request_change_type_id = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param,new ConTbRefundChangeEmployeeRequestRowMapper());
    }
}
