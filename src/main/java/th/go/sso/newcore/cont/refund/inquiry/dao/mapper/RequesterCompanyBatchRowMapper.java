package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterCompanyBatchBean;

public class RequesterCompanyBatchRowMapper implements RowMapper<RequesterCompanyBatchBean>{

    @Override
    public RequesterCompanyBatchBean mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException{

        RequesterCompanyBatchBean bean = new RequesterCompanyBatchBean();
        bean.setRequesterId(rs.getLong("requester_id"));
        bean.setSsoNo(rs.getString("sso_no"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderDocNo(rs.getString("order_doc_no"));
        bean.setOrderDocDate(rs.getDate("order_doc_date"));
        bean.setOrderMny(rs.getBigDecimal("order_mny"));
        bean.setApproveDate(rs.getDate("approve_date"));
        bean.setClaimantNo(rs.getString("claimant_no"));
        bean.setClaimantName(rs.getString("claimant_name"));
        bean.setBranchNo(rs.getString("branch_no"));
        bean.setInterestMny(rs.getBigDecimal("interest_mny"));
        bean.setPayMny(rs.getBigDecimal("pay_mny"));
        bean.setPaymentPlaceType(rs.getString("payment_place_type"));
        bean.setBankAcc(rs.getString("bank_acc"));
        bean.setBankCode(rs.getString("bank_code"));
        bean.setAddress(rs.getString("address"));
        bean.setDistCode(rs.getString("dist_code"));
        bean.setDistName(rs.getString("dist_name"));
        bean.setSubDistCode(rs.getString("subdist_code"));
        bean.setSubDistName(rs.getString("subdist_name"));
        bean.setProvinceCode(rs.getString("prov_code"));
        bean.setProvinceName(rs.getString("prov_name"));
        bean.setZipCode(rs.getString("zip_code"));
        bean.setRefundSicknessAmount(rs.getBigDecimal("refund_sickness_amount"));
        bean.setRefundRetirementAmount(rs.getBigDecimal("refund_retirement_amount"));
        bean.setRefundUnemploymentAmount(rs.getBigDecimal("refund_unemployment_amount"));
        bean.setRefundSicknessRate(rs.getBigDecimal("refund_sickness_rate"));
        bean.setRefundRetirementRate(rs.getBigDecimal("refund_retirement_rate"));
        bean.setRefundUnemploymentRate(rs.getBigDecimal("refund_unemployment_rate"));
        bean.setRefundInterestAmount(rs.getBigDecimal("refund_interest_amount"));
        bean.setPeriod(rs.getString("period"));
        bean.setPoOrder(rs.getString("po_order"));
        bean.setPoOrderName(rs.getString("po_order_name"));
        return bean;
    }
    
}
