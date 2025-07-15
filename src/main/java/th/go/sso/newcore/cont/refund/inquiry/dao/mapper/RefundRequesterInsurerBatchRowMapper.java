package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequesterInsurerBatchBean;

public class RefundRequesterInsurerBatchRowMapper implements RowMapper<RefundRequesterInsurerBatchBean> {
    @Override
    public RefundRequesterInsurerBatchBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        RefundRequesterInsurerBatchBean bean = new RefundRequesterInsurerBatchBean();

        bean.setRequesterId(rs.getLong("requester_id"));
        bean.setRefundRequestId(getNullableLong(rs, "refund_request_id"));
        bean.setRefundReceiptId(getNullableLong(rs, "refund_receipt_id"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setRefundPoOrder(rs.getString("refund_po_order"));
        bean.setRefundPromptPay(rs.getString("refund_prompt_pay"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setSection(rs.getString("section"));
        bean.setSplitRefund(rs.getString("split_refund"));
        bean.setDepCode(rs.getString("dep_code"));
        bean.setBankCode(rs.getString("bank_code"));
        bean.setIdCardNo(rs.getString("id_card_no"));
        bean.setFullName(rs.getString("full_name"));
        bean.setTitleCode(rs.getString("title_code"));
        bean.setFirstName(rs.getString("first_name"));
        bean.setLastName(rs.getString("last_name"));
        bean.setAddress(rs.getString("address"));
        bean.setVillage(rs.getString("village"));
        bean.setRoad(rs.getString("road"));
        bean.setProvinceCode(rs.getString("province_code"));
        bean.setProvinceName(rs.getString("prov_name"));
        bean.setDistCode(rs.getString("dist_code"));
        bean.setDistName(rs.getString("dist_name"));
        bean.setSubdistCode(rs.getString("subdist_code"));
        bean.setSubdistName(rs.getString("subdist_name"));
        bean.setZipCode(rs.getString("zip_code"));
        bean.setTelephone(rs.getString("telephone"));
        bean.setMobile(rs.getString("mobile"));
        bean.setEmail(rs.getString("email"));
        bean.setNationality(rs.getString("nationality"));
        bean.setBankCode(rs.getString("bank_code"));
        bean.setNationalityCode(rs.getString("nationality_code"));
        return bean;
    }
    private Long getNullableLong(ResultSet rs, String columnName) throws SQLException {
        Long value = rs.getLong(columnName);
        return rs.wasNull() ? null : value;
    }
}
