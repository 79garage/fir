package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;

public class ConTbRequesterM39RowMapper implements RowMapper<ConTbRequesterBean> {
    @Override
    public ConTbRequesterBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        ConTbRequesterBean bean = new ConTbRequesterBean();
        bean.setRequesterId(rs.getLong("requester_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setTitleCode(rs.getString("title_code"));
        bean.setFirstName(rs.getString("first_name"));
        bean.setLastName(rs.getString("last_name"));
        bean.setRequesterType(rs.getString("requester_type"));
        bean.setRelation(rs.getString("relation"));
        bean.setAddress(rs.getString("address"));
        bean.setVillage(rs.getString("village"));
        bean.setMoo(rs.getString("moo"));
        bean.setSoi(rs.getString("soi"));
        bean.setRoad(rs.getString("road"));
        bean.setProvinceCode(rs.getString("province_code"));
        bean.setProvinceName(rs.getString("province_name"));
        bean.setDistCode(rs.getString("dist_code"));
        bean.setDistName(rs.getString("dist_name"));
        bean.setSubdistCode(rs.getString("subdist_code"));
        bean.setSubdistName(rs.getString("subdist_name"));
        bean.setZipCode(rs.getString("zip_code"));
        bean.setTelephone(rs.getString("telephone"));
        bean.setMobile(rs.getString("mobile"));
        bean.setEmail(rs.getString("email"));
        bean.setPaymentPlaceType(rs.getString("payment_place_type"));
        bean.setBankCode(rs.getString("bank_code"));
        bean.setBankAcc(rs.getString("bank_acc"));
        bean.setBankAccName(rs.getString("bank_acc_name"));
        bean.setPostOffice(rs.getString("post_office"));
        bean.setPromptPay(rs.getString("promptpay"));
        bean.setSsoCode(rs.getString("sso_code"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setRelationGroup(rs.getString("relation_group"));
        bean.setNationalityCode(rs.getString("nationality_code"));
        bean.setNationality(rs.getString("nationality"));
        bean.setSection(rs.getString("section"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundSicknessRate(rs.getBigDecimal("refund_sickness_rate"));
        bean.setRefundRetirementRate(rs.getBigDecimal("refund_retirement_rate"));
        bean.setRefundUnemploymentRate(rs.getBigDecimal("refund_unemployment_rate"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setPeriod(rs.getString("period"));
        bean.setRefundPoOrder(rs.getString("po_order"));
        bean.setRefundPoOrderName(rs.getString("po_order_name"));
        
        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderStatus(rs.getString("order_status"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setDepCode(rs.getString("dep_code"));
        bean.setOldOrderNo(rs.getString("old_order_no"));

        
        return bean;
    }
}