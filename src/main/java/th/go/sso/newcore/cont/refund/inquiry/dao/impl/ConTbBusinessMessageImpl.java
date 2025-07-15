package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.IBusinessMessageDao;
import th.go.sso.newcore.cont.common.dto.BusinessMessageBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.BusinessMessageMapper;

@Repository
public class ConTbBusinessMessageImpl implements IBusinessMessageDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public BusinessMessageBean getBusinessMessageByCode(String code) throws Exception {

        Map<String, Object> param = new HashMap<>(1);

        String sql = " SELECT * FROM CON_TB_BUSINESS_MESSAGE WHERE BUSINESS_CODE = :code AND STATUS = 'A' ";

        param.put("code", code);

        return jdbcTemplate.queryForObject(sql, param, new BusinessMessageMapper());

    }
}
