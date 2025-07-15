package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.MasterCompanyRowMapper;

@Repository
public class ConMsCompanyDaoImpl extends DaoBase<Long> {

    public ConMsCompanyDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public ConMsCompanyBean searchCompany(Long companyId, String companyBranchCode, String receiveNo, String approveNo) throws Exception{

        try {

            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT mcomp.company_id,");
            sql.append(" mcomp.company_no,");
            sql.append(" mcomp.company_name,");
            sql.append(" mcomp.company_branch_code,");
            sql.append(" mcomp.company_branch_name,");
            sql.append(" mcomp.company_branch_code || ' - ' || mcomp.company_branch_name As companyBranchDescription,");
            sql.append(" mcomp.sso_branch_code,");
            sql.append(" mcomp.sso_branch_name,");
            sql.append(" mcomp.sso_branch_code || ' - ' || mcomp.sso_branch_name As ssoBranchDescription,");
            sql.append(" mcomp.business_type");
            sql.append(" FROM con_ms_company mcomp");

            if (!StringUtils.isEmpty(receiveNo) || !StringUtils.isEmpty(approveNo)) {
                sql.append(" LEFT OUTER JOIN con_tb_refund_request rereq ON ( rereq.company_id = mcomp.company_id )");
            }

            sql.append(" WHERE 1 = 1");
//            sql.append(" AND mcomp.company_status = 'A'");

            Map<String, Object> param = new HashMap<>();

            if (!NumberUtils.isZeroOrNull(companyId)){
                sql.append(" AND mcomp.company_id = :companyId");
                param.put("companyId", companyId);
            }

            if (!StringUtils.isEmpty(companyBranchCode)) {
                sql.append(" AND mcomp.company_branch_code = :companyBranchCode");
                param.put("companyBranchCode", companyBranchCode);
            }

            if (!StringUtils.isEmpty(receiveNo)){
                sql.append(" AND rereq.receive_no = :receiveNo");
                param.put("receiveNo", receiveNo);
            }
            if (!StringUtils.isEmpty(approveNo)){
                sql.append(" AND rereq.approve_no = :approveNo");
                param.put("approveNo", approveNo);
            }

            return queryForObject(sql.toString(), param, new MasterCompanyRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public List<ConMsCompanyBean> queryCompanyList(String companyNo, String companyBranchCode, String receiveNo) throws Exception{
        try {
            Map<String, Object> param = new HashMap<>();
            final StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append(" cmsc.company_id, ");
            sql.append(" cmsc.company_no, ");
            sql.append(" cmsc.company_name, ");
            sql.append(" cmsc.company_branch_code, ");
            sql.append(" cmsc.company_branch_name, ");
            sql.append(" cmsc.company_branch_code || ' - ' || cmsc.company_branch_name AS companyBranchDescription, ");
            sql.append(" cmsc.sso_branch_code, ");
            sql.append(" cmsc.sso_branch_name, ");
            sql.append(" cmsc.sso_branch_code || ' - ' || cmsc.sso_branch_name     AS ssoBranchDescription, ");
            sql.append(" cmsc.business_type ");
            sql.append(" FROM ");
            sql.append(" con_ms_company cmsc ");
            if (!StringUtils.isEmpty(receiveNo)) {
                sql.append(" LEFT JOIN con_tb_refund_request ctbrr ON ( ctbrr.company_id = cmsc.company_id )");
            }
            sql.append(" WHERE 1 = 1 ");
            if (!StringUtils.isEmpty(companyNo)){
                sql.append(" AND cmsc.company_no = :companyNo");
                param.put("companyNo", companyNo);
            }
            if (!StringUtils.isEmpty(companyBranchCode)) {
                sql.append(" AND cmsc.company_branch_code = :companyBranchCode");
                param.put("companyBranchCode", companyBranchCode);
            } else if (StringUtils.isEmpty(receiveNo)) {
                sql.append(" AND cmsc.company_branch_code = '000000'");
            }
            if (!StringUtils.isEmpty(receiveNo)){
                sql.append(" AND ctbrr.receive_no = :receiveNo");
                param.put("receiveNo", receiveNo);
            }
            return queryForList(sql.toString(), param, new MasterCompanyRowMapper());
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "CON_MS_COMPANY";
    }

    @Override
    protected String getPkColumnName() {
        return "COMPANY_ID";
    }
}
