package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsDepartmentBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConMsDepartmentRowMapper;

@Repository
public class ConMsDepartmentDaoImpl extends DaoBase<Long> {


    public ConMsDepartmentDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_MS_DEPARTMENT";
    }

    @Override
    protected String getPkColumnName() {
        return "DEPARTMENT_ID";
    }

    public ConMsDepartmentBean findDepartment(String ssoBranchCode) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT * FROM CON_MS_DEPARTMENT WHERE 1 = 1 AND DEP_STATUS = 'A' AND DEP_CODE = :ssoBranchCode ";
        param.put("ssoBranchCode", ssoBranchCode);
        return queryForObject(sql, param, new ConMsDepartmentRowMapper());
    }

    public ConMsDepartmentBean searchDepName(Long departmentReceiveId) throws Exception {

        try {

            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT department_id,");
            sql.append(" dep_code,");
            sql.append(" dep_name");
            sql.append(" FROM con_ms_department");
            sql.append(" WHERE 1 = 1");
            sql.append(" AND dep_status = 'A' ");

            Map<String, Object> param = new HashMap<>();

            if (!NumberUtils.isZeroOrNull(departmentReceiveId)) {
                sql.append(" AND department_id = :departmentReceiveId");
                param.put("departmentReceiveId",departmentReceiveId);
            }

            return queryForObject(sql.toString(), param, new ConMsDepartmentRowMapper());

        }  catch (EmptyResultDataAccessException ex){
            return null;
        }
    }
}
