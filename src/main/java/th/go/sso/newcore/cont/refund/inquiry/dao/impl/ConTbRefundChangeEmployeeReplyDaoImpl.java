package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeReplyBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundChangeEmployeeReplyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConTbRefundChangeEmployeeReplyDaoImpl extends DaoBase<Long> {
    public ConTbRefundChangeEmployeeReplyDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_CHANGE_EMPLOYEE_REPLY";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_REQUEST_CHANGE_EMPLOYEE_ID";
    }

    public RefundChangeEmployeeReplyBean queryRefundChangeEmployeeReply(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "select approve_status,a.approve_status_change,a.approve_date,a.approve_date_change,a.notice_date" +
                ",a.notice_date_change,a.notice_no,a.notice_no_change,a.refund_period_end_year,a.refund_period_end_month" +
                ",a.refund_period_str_year,a.refund_period_str_month,a.refund_period_end_year_change,a.refund_period_end_month_change" +
                ",a.refund_period_str_year_change,a.refund_period_str_month_change,a.refund_empe_amount,a.refund_empe_amount_change" +
                ",a.refund_interest_amount,a.refund_interest_amount_change,a.refund_retirement_amount,a.refund_retirement_amount_change" +
                ",a.request_over_paid_amount,a.request_over_paid_amount_change,a.refund_sickness_amount,a.refund_sickness_amount_change" +
                ",a.refund_unemployment_amount,a.refund_unemployment_amount_change,a.reply_date,a.reply_date_change,a.id_card_no" +
                ",a.refund_request_change_type_id,a.source_data,a.refund_change_employee_reply_id,a.create_date,a.create_by " +
                " from con_tb_refund_change_employee_reply a " +
                " where a.refund_request_change_type_id = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param,new ConTbRefundChangeEmployeeReplyRowMapper());
    }
}
