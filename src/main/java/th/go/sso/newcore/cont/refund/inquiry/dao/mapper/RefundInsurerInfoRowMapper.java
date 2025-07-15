package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;import org.springframework.lang.NonNull;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;

public class RefundInsurerInfoRowMapper implements RowMapper<RefundInsurerBean>{

    @Override
    public RefundInsurerBean mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException{

        RefundInsurerBean bean = new RefundInsurerBean();
        bean.setInsurerId(rs.getLong("INSURER_ID"));
        bean.setIdCardNo(rs.getString("ID_CARD_NO"));
        bean.setTitleCode(rs.getString("TITLE_CODE"));
        bean.setFirstName(rs.getString("FIRST_NAME"));
        bean.setLastName(rs.getString("LAST_NAME"));
        bean.setFullName(rs.getString("FULL_NAME"));
        bean.setInsurerStatus(rs.getString("INSURER_STATUS"));
        bean.setAddress(rs.getString("ADDRESS"));
        bean.setDistCode(rs.getString("DIST_CODE"));
        bean.setDistName(rs.getString("DIST_NAME"));
        bean.setSubdistCode(rs.getString("SUBDIST_CODE"));
        bean.setSubdistName(rs.getString("SUBDIST_NAME"));
        bean.setProvinceCode(rs.getString("PROVINCE_CODE"));
        bean.setProvinceName(rs.getString("PROVINCE_NAME"));
        bean.setZipCode(rs.getString("ZIP_CODE"));
        bean.setTelephone(rs.getString("TELEPHONE"));
        bean.setMoo(rs.getString("MOO"));
        bean.setSoi(rs.getString("SOI"));
        bean.setRoad(rs.getString("ROAD"));
        bean.setVillage(rs.getString("VILLAGE"));
        bean.setMobile(rs.getString("MOBILE"));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setCreateBy(rs.getString("CREATE_BY"));
        bean.setCreateDate(rs.getDate("CREATE_DATE"));
        bean.setSection(rs.getString("SECTION"));
        bean.setDepCode(rs.getString("DEP_CODE"));
        bean.setLocName(rs.getString("LOC_NAME"));
        bean.setLocNameRep(rs.getString("LOC_NAME_REP"));
        return bean;
    }
}
