package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.common.dto.BusinessMessageBean;

public class BusinessMessageMapper implements RowMapper<BusinessMessageBean> {

    @Override
    public BusinessMessageBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        BusinessMessageBean bean = new BusinessMessageBean();

        bean.setMessageId(rs.getInt("MESSAGE_ID"));
        bean.setBussinessCode(rs.getString("BUSINESS_CODE"));
        bean.setBussinessMessage(rs.getString("BUSINESS_MESSAGE"));
        bean.setType(rs.getString("TYPE"));
        bean.setStatus(rs.getString("STATUS"));

        return bean;
    }
}
