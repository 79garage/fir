package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestCompanyBatchBean;

public class RefundRequestCompanyBatchRowMapper implements RowMapper<RefundRequestCompanyBatchBean> {

    @Override
    public RefundRequestCompanyBatchBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        RefundRequestCompanyBatchBean bean = new RefundRequestCompanyBatchBean();
        bean.setRefundRequestId(getNullableLong(rs, "refund_request_id"));
        bean.setCompanyId(getNullableLong(rs, "company_id"));
        bean.setRefundTypePlace(rs.getString("refund_type_place"));
        bean.setRefundBankAccount(rs.getString("refund_bank_account"));
        bean.setRefundPoOrder(rs.getString("refund_po_order"));
        bean.setRefundPromptPay(rs.getString("refund_prompt_pay"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setSection(rs.getString("section"));
        bean.setSplitRefund(rs.getString("split_refund"));
        bean.setBankCode(rs.getString("bank_code"));
        bean.setDepCode(rs.getString("dep_code"));
//        bean.setRefundReceiptId(getNullableLong(rs, "refund_receipt_id"));
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
        bean.setRefRefundRequestId(rs.getLong("ref_refund_request_id"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setPeriod(rs.getString("refund_period"));
        bean.setRefundPoOrderName(rs.getString("refund_po_order_name"));
        bean.setRefundSicknessRate(rs.getBigDecimal("refund_sickness_rate"));
        bean.setRefundRetirementRate(rs.getBigDecimal("refund_retirement_rate"));
        bean.setRefundUnemploymentRate(rs.getBigDecimal("refund_unemployment_rate"));
        return bean;
    }

    private Long getNullableLong(ResultSet rs, String columnName) throws SQLException {
        Long value = rs.getLong(columnName);
        return rs.wasNull() ? null : value;
    }
}
