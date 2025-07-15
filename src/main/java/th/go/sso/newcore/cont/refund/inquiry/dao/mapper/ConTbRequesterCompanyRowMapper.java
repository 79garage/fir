package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterCompanyBean;

public class ConTbRequesterCompanyRowMapper implements RowMapper<ConTbRequesterCompanyBean> {
    @Override
    public ConTbRequesterCompanyBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {
        ConTbRequesterCompanyBean bean= new ConTbRequesterCompanyBean();
        bean.setRequesterId(rs.getLong("requester_id"));
        bean.setRefundRequestId(rs.getLong("refund_request_id"));
        bean.setRefundReceiptId(rs.getLong("refund_receipt_id"));
        bean.setOrderNo(rs.getString("order_no"));
        bean.setOrderStatus(rs.getString("order_status"));
        bean.setCompanyId(rs.getString("company_id"));
        bean.setRequesterType(rs.getString("requester_type"));
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
        bean.setPostOffice(rs.getString("post_office"));
        bean.setPromptPay(rs.getString("promptpay"));
        bean.setSsoCode(rs.getString("sso_code"));
        bean.setReceiptNo(rs.getString("receipt_no"));
        bean.setReceiptDate(rs.getDate("receipt_date"));
        bean.setRefundTotalAmount(rs.getBigDecimal("refund_total_amount"));
        bean.setStatus(rs.getString("status"));
        bean.setOrderRemark(rs.getString("order_remark"));
        bean.setPeriod(rs.getString("period"));
        return bean;
    }
}