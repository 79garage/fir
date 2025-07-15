package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBranchBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConMsBankBranchRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConMsBankRowMapper;

@Repository
public class ConMsBankDaoImpl extends DaoBase<Long> {


    public ConMsBankDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public ConMsBankBean searchBank(Long refundBankId) throws Exception{
        Map<String, Object> param = new HashMap<>();
        String sql = """
                SELECT * FROM CON_MS_BANK WHERE 1 = 1 AND BANK_ID = :refundBankId
                """;
        param.put("refundBankId",refundBankId);

        return queryForObject(sql, param, new ConMsBankRowMapper());

//        try {
//
//            final StringBuilder sql = new StringBuilder();
//
//            sql.append("SELECT bank_id,");
//            sql.append(" bank_code,");
//            sql.append(" bank_name");
//            sql.append(" FROM con_ms_bank");
//            sql.append(" WHERE 1 = 1 ");
////            sql.append(" AND bank_status = 'A' ");
//
//            Map<String, Object> param = new HashMap<>();
//
//            if (!NumberUtils.isZeroOrNull(refundBankId)) {
//
//                sql.append(" AND bank_id = :refundBankId");
//                param.put("refundBankId",refundBankId);
//            }
//
//            return queryForObject(sql.toString(), param, new ConMsBankRowMapper());
//
//        } catch (EmptyResultDataAccessException ex){
//            return null;
//        }
    }

    public ConMsBankBranchBean searchBankBranch(Long refundBankBranchId) throws Exception{
        Map<String, Object> param = new HashMap<>();
        String sql = """
                SELECT * FROM CON_MS_BANK_BRANCH WHERE 1 = 1 AND BANK_BRANCH_ID = :refundBankBranchId
                """;
        param.put("refundBankBranchId",refundBankBranchId);

        return queryForObject(sql, param, new ConMsBankBranchRowMapper());

//        try {
//
//            final StringBuilder sql = new StringBuilder();
//
//            sql.append("SELECT bank_branch_id,");
//            sql.append(" bank_branch_code,");
//            sql.append(" bank_id,");
//            sql.append(" bank_branch_identifier,");
//            sql.append(" bank_branch_name");
//            sql.append(" FROM con_ms_bank_branch");
//            sql.append(" WHERE 1 = 1 ");
////            sql.append(" AND bank_branch_status = 'A' ");
//
//            Map<String, Object> param = new HashMap<>();
//
//            if (!NumberUtils.isZeroOrNull(refundBankBranchId)) {
//
//                sql.append(" AND bank_branch_id = :refundBankBranchId");
//                param.put("refundBankBranchId",refundBankBranchId);
//            }
//
//            return queryForObject(sql.toString(), param, new ConMsBankBranchRowMapper());
//
//        } catch (EmptyResultDataAccessException ex){
//            return null;
//        }
    }

    @Override
    protected String getTableName() {
        return "CON_MS_BANK";
    }

    @Override
    protected String getPkColumnName() {
        return null;
    }
}
