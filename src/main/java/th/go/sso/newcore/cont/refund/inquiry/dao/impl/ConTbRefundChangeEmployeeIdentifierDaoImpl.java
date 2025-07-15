package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEmployeeIdentifierBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundChangeEmployeeIdentifierRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConTbRefundChangeEmployeeIdentifierDaoImpl extends DaoBase<Long> {
    public ConTbRefundChangeEmployeeIdentifierDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_CHANGE_EMPLOYEE_IDENTIFIER";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_CHANGE_EMPLOYEE_IDENTIFIER_ID";
    }

    public RefundChangeEmployeeIdentifierBean queryRefundChangeEmployeeIdentifier(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "select id_card_no,a.id_card_no_change,a.refund_request_change_type_id,a.source_data,a.refund_change_employee_identifier_id" +
                ",a.create_date,a.create_by " +
                " from con_tb_refund_change_employee_identifier a  where  a.refund_request_change_type_id = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param,new ConTbRefundChangeEmployeeIdentifierRowMapper());
    }
}
