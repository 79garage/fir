package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.StringUtils;

@Repository
public class ConTbConfigDaoImpl extends DaoBase<Long> {

	public ConTbConfigDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected String getTableName() {
		return "CON_TB_CONFIG";
	}

	@Override
	protected String getPkColumnName() {
		return null;
	}

	public String queryValueByConfigName(String key) throws Exception {

		StringBuilder sql = new StringBuilder();
		// TODO
		sql.append(" SELECT  ");
		sql.append(" CONFIG_VALUE ");
		sql.append(" FROM ");
		sql.append(" CON_TB_CONFIG ");
		sql.append(" WHERE 1 = 1 ");

		Map<String, Object> param = new HashMap<>();

		if (!StringUtils.isEmpty(key)) {
			sql.append(" AND CONFIG_NAME = :configName");
			param.put("configName", key);
		}
		
		sql.append(" FETCH FIRST ROW ONLY ");

		return queryForObject(sql.toString(), param, String.class);
	}

}
