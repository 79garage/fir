package th.go.sso.newcore.cont.refund.inquiry.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbInformationBean;

public class ConTbInformationMapper implements RowMapper<ConTbInformationBean> {

    @Override
    public ConTbInformationBean mapRow(@NonNull ResultSet rs, int rowNum)  throws SQLException {

        ConTbInformationBean bean = new ConTbInformationBean();

        bean.setId(rs.getLong("information_id"));
        bean.setSubject(rs.getString("subject"));
        bean.setValue1(rs.getString("value1"));
        bean.setValue2(rs.getString("value2"));
        bean.setValue3(rs.getString("value3"));
        bean.setGroupCode(rs.getString("group_code"));
        bean.setSysCode(rs.getString("sys_code"));
        bean.setRemark(rs.getString("remark"));
        bean.setStartDate(rs.getDate("start_date"));
        bean.setEndDate(rs.getDate("end_date"));

        return bean;

    }
}