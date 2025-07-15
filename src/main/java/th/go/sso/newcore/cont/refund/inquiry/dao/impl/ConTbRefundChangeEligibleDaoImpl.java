package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundChangeEligibleBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundChangeEligibleRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConTbRefundChangeEligibleDaoImpl extends DaoBase<Long> {
    public ConTbRefundChangeEligibleDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_CHANGE_ELIGIBLE";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_CHANGE_ELIGIBLE_ID";
    }

    public RefundChangeEligibleBean queryRefundChangeEligible(Long refundRequestChangeId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = "select id_card_no,a.full_name,a.title_code,a.first_name,a.last_name,a.relation,a.address,a.village,a.moo,a.soi,a.road,a.province_code" +
                ",a.province_name,a.dist_code,a.dist_name,a.subdist_code,a.subdist_name,a.zip_code,a.telephone,a.mobile,a.email,a.payment_place_type,a.bank_code" +
                ",a.bank_acc,a.bank_acc_name,a.post_office,a.promptpay,a.sso_code,a.create_by,a.create_date,a.relation_group,a.nationality_code,a.nationality" +
                ",a.refund_request_change_type_id,a.refund_change_eligible_id,a.refund_total_amount,a.refund_interest_amount,a.refund_total_amount_change" +
                ",a.id_card_no_change,a.full_name_change,a.title_code_change,a.first_name_change,a.last_name_change,a.refund_interest_amount_change" +
                ",a.relation_change,a.address_change,a.village_change,a.moo_change,a.soi_change,a.road_change,a.province_code_change,a.province_name_change" +
                ",a.dist_code_change,a.dist_name_change,a.subdist_code_change,a.subdist_name_change,a.zip_code_change,a.telephone_change,a.mobile_change" +
                ",a.email_change,a.payment_place_type_change,a.bank_code_change,a.bank_acc_change,a.bank_acc_name_change,a.post_office_change" +
                ",a.promptpay_change,a.sso_code_change,a.relation_group_change,a.nationality_code_change,a.nationality_change,a.refund_sickness_amount" +
                ",a.refund_sickness_amount_change,a.refund_retirement_amount,a.refund_retirement_amount_change" +
                ",a.refund_unemployment_amount,a.refund_unemployment_amount_change " +
                " from con_tb_refund_change_eligible a  " +
                " where a.refund_request_change_type_id = :refundRequestChangeId";
        param.put("refundRequestChangeId", refundRequestChangeId);

        return queryForObject(sql, param, new ConTbRefundChangeEligibleRowMapper());
    }
}
