package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRefundRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHoldReceiveBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInfoBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestCompanyBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestEmployeeBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundRequestInsurerBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundRequestRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundHoldReceiveRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundInfoRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundRequestCompanyBatchRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundRequestDelAndCcHistRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundRequestEmployeeBatchRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundRequestInsurerBatchRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.SplitEmployeeRefundListAggRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverHoldReceiveRequest;

@Repository
public class ConTbRefundRequestDaoImpl extends DaoBase<RefundRequestCompanyBatchBean> {


    public ConTbRefundRequestDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_REQUEST";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_REQUEST_ID";
    }

    public ConTbRefundRequestBean findConTbRefundRequestByReceiveNo(String receiveNo) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = """
                SELECT * FROM CON_TB_REFUND_REQUEST WHERE 1 = 1 AND RECEIVE_NO = :receiveNo
                """;
        param.put("receiveNo", receiveNo);
        return queryForObject(sql, param, new ConTbRefundRequestRowMapper());
    }

    public RefundInfoBean findRefundInfoDetail(String receiveNo) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = """
                SELECT NULL AS FULL_NAME, NULL AS ID_CARD_NO, SECTION, DEPARTMENT_RESPONSIBLE_CODE AS DEP_CODE FROM CON_TB_REFUND_REQUEST WHERE 1 = 1 AND RECEIVE_NO = :receiveNo
                """;
        param.put("receiveNo", receiveNo);
        return queryForObject(sql, param, new RefundInfoRowMapper());
    }

    public List<RefundRequestCompanyBatchBean> queryConTbRefundRequestCompanyBatch(Paginable paginable, List<Long> refundRequestIds) throws Exception {
        StringBuilder sql = new StringBuilder("select t1.refund_request_id,t1.company_id,t1.refund_type_place,t1.refund_bank_account " +
                ",t1.refund_po_order,t1.refund_prompt_pay" +
//                ",t12.refund_total_amount" +
                ",CASE WHEN split_refund = 00 THEN t12.refund_total_amount ELSE t12.refund_empr_amount END AS refund_total_amount" +
                ",t1.section,t1.split_refund  " +
                ",t2.dep_code,t6.bank_code,t3.address,t3.village,t3.road,t3.province_code" +
                ",t11.prov_name,t3.dist_code,t11.dist_name,t3.subdist_code,t11.subdist_name,t3.zip_code,t1.ref_refund_request_id " +
                ",t12.refund_sickness_amount, t12.refund_retirement_amount, t12.refund_unemployment_amount, t12.refund_interest_amount, t12.refund_period " +
                ",t1.refund_po_order_name, t12.refund_sickness_rate, t12.refund_retirement_rate, t12.refund_unemployment_rate " +
                "from con_tb_refund_request t1 " +
                "left join con_ms_department t2 on t2.dep_code = t1.department_receive_code  " +
//                "left join con_ms_department t2 on t2.department_id = t1.department_receive_id  " +
                "left join con_ms_company t3 on t3.company_id = t1.company_id  " +
                "left join con_ms_bank t6 on t6.bank_id = t1.refund_bank_id " +
                "LEFT JOIN con_tb_refund_period  t12 ON t12.refund_request_id = t1.refund_request_id " +
                "left join con_tr_refund_company t9 on t9.refund_request_id = t1.refund_request_id  " +
//                "left join con_tb_refund_receipt t10 on t10.refund_company_id = t9.refund_company_id  " +
                "left join con_ms_location t11 ON t11.subdist_code = t3.subdist_code " +
                "where t1.approve_status = '1' " +
                "and t1.status = 'A' " +
                "and t1.company_id is not null " +
                "and t1.section = '0' " +
                "and t1.ref_refund_request_id IS NULL " +
//                "and t1.split_refund = '00' " +
                "and not exists (select 1 from con_tb_requester_company ctrc where ctrc.refund_request_id = t1.refund_request_id) " +
                "AND t1.refund_total_amount > 0 " +
                "AND t1.eligible_flag IS NULL ");

        Map<String, Object> param = new HashMap<>();
        if (!CollectionUtils.isEmpty(refundRequestIds)) {
            sql.append("and t1.refund_request_id in ( :refundRequestIds ) ");
            param.put("refundRequestIds", refundRequestIds);
        }
//        sql.append("order by t1.refund_request_id");
        return queryForList(sql.toString(), param, paginable, new RefundRequestCompanyBatchRowMapper());
    }

    public List<RefundRequestEmployeeBatchBean> queryConTbRefundRequestEmployeeBatch(Paginable paginable, List<Long> refundRequestIds) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select t1.refund_request_id,t1.refund_type_place,t1.refund_bank_account,t1.refund_po_order");
        sql.append("	,t1.refund_prompt_pay,t12.refund_total_amount,t1.section,t1.split_refund,t2.dep_code,t3.id_card_no,t3.full_name");
        sql.append("	,t3.title_code,t3.first_name,t3.last_name");
        sql.append("	,t3.address,t3.village,t3.road,t3.province_code,t11.prov_name,t3.dist_code,t11.dist_name,t3.subdist_code,t11.subdist_name,t3.zip_code");
        sql.append("	,t3.telephone,t3.mobile,t3.email,t7.nationality_name as nationality,t6.bank_code,t3.nation_code as nationality_code, t1.ref_refund_request_id");
        sql.append("	,t12.refund_sickness_amount, t12.refund_retirement_amount, t12.refund_unemployment_amount, t12.refund_interest_amount, t12.refund_period");
        sql.append("    ,t1.refund_po_order_name, t12.refund_sickness_rate, t12.refund_retirement_rate, t12.refund_unemployment_rate");
        sql.append("	from con_tb_refund_request t1 ");
        sql.append("	left join con_ms_department t2 on t2.dep_code = t1.department_receive_code ");
        sql.append("	left join con_ms_insurer t3 on t3.insurer_id = t1.insurer_id ");
        sql.append("	LEFT JOIN con_tb_refund_period   t12 ON t12.refund_request_id = t1.refund_request_id ");
        sql.append("	left join con_tr_refund_employee t4 on t4.refund_request_id = t1.refund_request_id ");
//        sql.append("	left join con_tb_refund_receipt t5 on t5.refund_employee_id = t4.refund_employee_id ");
        sql.append("	left join con_ms_bank t6 on t6.bank_id = t1.refund_bank_id ");
        sql.append("	left join con_ms_nationality t7 on t7.nationality_code = t3.nation_code");
        sql.append("	left join con_ms_location t11 ON t11.subdist_code = t3.subdist_code");
        sql.append("	where t1.approve_status = '1' AND t12.approve_status = '1'");
        sql.append("	and t1.status = 'A'");
        sql.append("	and ((t1.section = '0' and t1.ref_refund_request_id IS NOT NULL) or t1.section = '3')");
        sql.append("	and not exists (select 1 from con_tb_requester33 ctrc where ctrc.refund_request_id = t1.refund_request_id)");
        sql.append("	AND t1.eligible_flag IS NULL");

        Map<String, Object> param = new HashMap<>();
        if (!CollectionUtils.isEmpty(refundRequestIds)) {
            sql.append("	and t1.refund_request_id in ( :refundRequestIds ) ");
            param.put("refundRequestIds", refundRequestIds);
        }
//        sql.append("order by t1.refund_request_id");
        return queryForList(sql.toString(), param, paginable, new RefundRequestEmployeeBatchRowMapper());
    }

    public List<RefundRequestInsurerBatchBean> queryConTbRefundRequestInsurerBatch(Paginable paginable, List<Long> refundRequestIds) throws Exception {
        // ไม่เอาเคสกรณีตาย request_reason_code != '07' แต่ต้องไปเอาเคสตายที่ Con_Tb_requester39 ที่ Requester_type = '2'
        String sql = """
                select t1.refund_request_id,t1.refund_type_place,t1.refund_bank_account,t1.refund_po_order
                ,t1.refund_prompt_pay,t12.refund_total_amount,t1.section,t1.split_refund,t2.dep_code,t3.id_card_no,t3.full_name
                ,t3.title_code,t3.first_name,t3.last_name
                ,t3.address,t3.village,t3.road,t3.province_code,t11.prov_name,t3.dist_code,t11.dist_name,t3.subdist_code,t11.subdist_name,t3.zip_code
                ,t3.telephone,t3.mobile,t3.email,t7.nationality_name as nationality,t6.bank_code,t3.nation_code as nationality_code
                ,t12.refund_sickness_amount, t12.refund_retirement_amount, t12.refund_unemployment_amount, t12.refund_interest_amount, t12.refund_period
                ,t1.refund_po_order_name, t12.refund_sickness_rate, t12.refund_retirement_rate, t12.refund_unemployment_rate
                from con_tb_refund_request t1 
                left join con_ms_department t2 on t2.dep_code = t1.department_receive_code 
                left join con_ms_insurer t3 on t3.insurer_id = t1.insurer_id 
                LEFT JOIN con_tb_refund_period  t12 ON t12.refund_request_id = t1.refund_request_id AND t12.approve_status = '1'
                left join con_tr_refund_insurer t4 on t4.refund_request_id = t1.refund_request_id 
                -- left join con_tb_refund_receipt t5 on t5.refund_insurer_id = t4.refund_insurer_id 
                left join con_ms_bank t6 on t6.bank_id = t1.refund_bank_id 
                left join con_ms_nationality t7 on t7.nationality_code = t3.nation_code
                left join con_ms_location t11 ON t11.subdist_code = t3.subdist_code
                where t1.approve_status = '1'
                and t1.status = 'A' 
                and t1.section = '1' and t1.request_reason_code != '07' AND t4.progress_status = '1' 
                and not exists (select 1 from con_tb_requester39 ctrc where ctrc.refund_request_id = t1.refund_request_id) 
                AND t1.eligible_flag IS NULL
                """;
        Map<String, Object> param = new HashMap<>();
        if (!CollectionUtils.isEmpty(refundRequestIds)) {
            sql += " and t1.refund_request_id in ( :refundRequestIds )";
            param.put("refundRequestIds", refundRequestIds);
        }
//        sql += "order by t1.refund_request_id";
        return queryForList(sql, param, paginable, new RefundRequestInsurerBatchRowMapper());
    }

    public List<SplitEmployeeRefundBean> searchConTbRefundRequestSplitEmployee(Long refRefundRequestId) throws Exception {

        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT t1.refund_request_id,");
        sql.append(" t1.receive_no,");
        sql.append(" t1.insurer_id,");
        sql.append(" t1.ref_refund_request_id,");
        sql.append(" t1.refund_type_place,");
        sql.append(" t1.refund_po_order,");
        sql.append(" t1.refund_po_order_name,");
        sql.append(" t1.DIST_CODE,");
        sql.append(" t1.SUBDIST_CODE,");
        sql.append(" t1.PROVINCE_CODE,");
        sql.append(" t1.refund_prompt_pay,");
        sql.append(" t1.refund_bank_id,");
        sql.append(" t1.refund_bank_branch_id,");
        sql.append(" t1.refund_bank_account,");
        sql.append(" t2.id_card_no,");
        sql.append(" t2.full_name,");
        sql.append(" t3.progress_status,");
        sql.append(" NULL AS bank_code,");
        sql.append(" NULL AS bank_branch_code,");
        sql.append(" NULL AS refund_status");
        sql.append(" FROM con_tb_refund_request t1 ");
        sql.append(" LEFT JOIN con_ms_insurer t2 ON (t2.insurer_id = t1.insurer_id) ");
        sql.append(" LEFT JOIN con_tr_refund_company t3 ON(t3.refund_request_id =  t1.ref_refund_request_id) ");
        sql.append(" WHERE ");
        Map<String, Object> param = new HashMap<>();

        if (!NumberUtils.isZeroOrNull(refRefundRequestId)) {
            sql.append(" t1.ref_refund_request_id = :refRefundRequestId ");
            param.put("refRefundRequestId", refRefundRequestId);
        } else {
            sql.append(" t1.ref_refund_request_id = 0 ");
        }

        return queryForList(sql.toString(), param, new SplitEmployeeRefundListAggRowMapper());
    }


    public RefundRequestBean searchConTbRefundRequestByRefundRequestId(Long refundRequestId, Long refundRequestChangeTypeId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT " +
                " t0.* " +
                " FROM " +
                " ( " +
                " SELECT " +
                " mc.company_id, " +
                " mc.company_no || ' - ' || mc.company_name        AS company, " +
                " mc.company_branch_code || ' - ' || mc.company_branch_name AS branch, " +
                " NULL                      AS insurer_id, " +
                " NULL                      AS full_name, " +
                " NULL                      AS id_card_no, " +
                " NULL                      AS insurer_status, " +
                " rq.refund_request_id, " +
                " rq.request_type, " +
                " rq.request_channel, " +
                " rq.department_responsible_code, " +
                " rq.department_receive_code, " +
                " rq.receive_no, " +
                " rq.receive_date, " +
                " rq.request_reason_code, " +
                " rq.request_reason_other, " +
                " rq.refund_type_place, " +
                " rq.refund_bank_account, " +
                " rq.refund_bank_branch_id, " +
                " rq.refund_bank_id, " +
                " rq.dist_code, " +
                " rq.subdist_code, " +
                " rq.province_code, " +
                " rq.refund_po_order, " +
                " rq.refund_prompt_pay, " +
                " rq.refund_remark, " +
                " rq.status, " +
                " rq.section, " +
                " ctbrrct.refund_change_type, " +
                " ctbrrct.refund_operation_code, " +
                " ctbrrc.reverse_remark, " +
                " rq.ref_refund_request_id," +
                " null as original_receive_no " +
                " FROM " +
                " con_tb_refund_request rq " +
                " JOIN con_ms_company                    mc ON ( rq.company_id = mc.company_id ) " +
                " LEFT JOIN con_tb_refund_request_change_type ctbrrct ON ctbrrct.refund_request_id = rq.refund_request_id ";
        if (!NumberUtils.isZeroOrNull(refundRequestChangeTypeId)) {
            sql += " AND refund_request_change_type_id = :refundRequestChangeTypeId ";
        }
        sql += " LEFT JOIN con_tb_refund_request_change      ctbrrc ON ctbrrc.refund_request_change_type_id = ctbrrct.refund_request_change_type_id " +
                " WHERE " +
                " 1 = 1 AND rq.ref_refund_request_id IS null " +
                " UNION " +
                " SELECT " +
                " mc.company_id, " +
                " mc.company_no || ' - ' || mc.company_name AS company, " +
                " mc.company_branch_code || ' - ' || mc.company_branch_name AS branch, " +
                " mi.insurer_id, " +
                " mi.full_name, " +
                " mi.id_card_no, " +
                " mi.insurer_status, " +
                " rq.refund_request_id, " +
                " rq.request_type, " +
                " rq.request_channel, " +
                " rq.department_responsible_code, " +
                " rq.department_receive_code, " +
                " nvl(rqc.receive_no, rq.receive_no) as receive_no, " +
                " rq.receive_date, " +
                " rq.request_reason_code, " +
                " rq.request_reason_other, " +
                " rq.refund_type_place, " +
                " rq.refund_bank_account, " +
                " rq.refund_bank_branch_id, " +
                " rq.refund_bank_id, " +
                " rq.dist_code, " +
                " rq.subdist_code, " +
                " rq.province_code, " +
                " rq.refund_po_order, " +
                " rq.refund_prompt_pay, " +
                " rq.refund_remark, " +
                " rq.status, " +
                " rq.section, " +
                " ctbrrct.refund_change_type, " +
                " ctbrrct.refund_operation_code, " +
                " ctbrrc.reverse_remark, " +
                " rq.ref_refund_request_id, " +
                " rq.receive_no as original_receive_no " +
                " FROM " +
                " con_tb_refund_request rq " +
                " left join con_tb_refund_request rqc on rqc.refund_request_id = rq.ref_refund_request_id " +
                " JOIN con_ms_insurer                    mi ON ( rq.insurer_id = mi.insurer_id ) " +
                " LEFT JOIN con_ms_company mc ON ( rq.company_id = mc.company_id ) " +
                " LEFT JOIN con_tb_refund_request_change_type ctbrrct ON ctbrrct.refund_request_id = rq.refund_request_id ";
        if (!NumberUtils.isZeroOrNull(refundRequestChangeTypeId)) {
            sql += " AND refund_request_change_type_id = :refundRequestChangeTypeId ";
        }
        sql += " LEFT JOIN con_tb_refund_request_change      ctbrrc ON ctbrrc.refund_request_change_type_id = ctbrrct.refund_request_change_type_id " +
                " WHERE " +
                " 1 = 1 " +
                " ) t0 " +
                " WHERE " +
                " t0.refund_request_id = :refundRequestId ";
        param.put("refundRequestId", refundRequestId);
        param.put("refundRequestChangeTypeId", refundRequestChangeTypeId);

//        final StringBuilder sql = new StringBuilder();

//        sql.append(" SELECT");
//        sql.append("     mc.company_id,");
//        sql.append("     mc.company_no || ' - ' || mc.company_name        AS company,");
//        sql.append("     mc.company_branch_code  || ' - ' || mc.company_branch_name AS branch,");
//        sql.append("     NULL AS insurer_id,");
//        sql.append("     NULL AS full_name,");
//        sql.append("     NULL AS id_card_no,");
//        sql.append("     NULL AS insurer_status,");
//        sql.append("     rq.refund_request_id,");
//        sql.append("     rq.request_type,");
//        sql.append("     rq.request_channel,");
//        sql.append("     rq.department_responsible_code,");
//        sql.append("     rq.department_receive_code,");
////        sql.append("     rq.department_responsible_id,");
////        sql.append("     rq.department_receive_id,");
//        sql.append("     rq.receive_no,");
//        sql.append("     rq.receive_date,");
//        sql.append("     rq.request_reason_code,");
//        sql.append("     rq.request_reason_other,");
//        sql.append("     rq.refund_type_place,");
//        sql.append("     rq.refund_bank_account,");
//        sql.append("     rq.refund_bank_branch_id,");
//        sql.append("     rq.refund_bank_id,");
//        sql.append("     rq.dist_code,");
//        sql.append("     rq.subdist_code,");
//        sql.append("     rq.province_code,");
//        sql.append("     rq.refund_po_order,");
//        sql.append("     rq.refund_prompt_pay,");
//        sql.append("     rq.refund_remark,");
//        sql.append("     rq.status,");
//        sql.append("     rq.section,");
//        sql.append("     ctbrrct.refund_change_type,");
//        sql.append("     ctbrrct.refund_operation_code");
//        sql.append(" FROM con_tb_refund_request rq");
//        sql.append(" JOIN con_ms_company mc ON ( rq.company_id = mc.company_id )");
//        sql.append(" LEFT JOIN con_tb_refund_request_change_type ctbrrct ON ctbrrct.refund_request_id = rq.refund_request_id");
//        sql.append(" WHERE   1 = 1 ");
//
//        if (!NumberUtils.isZeroOrNull(refundRequestId)) {
//            sql.append(" AND rq.refund_request_id = :refundRequestId");
//        }
//        sql.append(" UNION ");
//        sql.append(" SELECT");
//        sql.append("     NULL AS company_id,");
//        sql.append("     NULL AS company,");
//        sql.append("     NULL AS branch,");
//        sql.append("     mi.insurer_id,");
//        sql.append("     mi.full_name,");
//        sql.append("     mi.id_card_no,");
//        sql.append("     mi.insurer_status,");
//        sql.append("     rq.refund_request_id,");
//        sql.append("     rq.request_type,");
//        sql.append("     rq.request_channel,");
//        sql.append("     rq.department_responsible_code,");
//        sql.append("     rq.department_receive_code,");
////        sql.append("     rq.department_responsible_id,");
////        sql.append("     rq.department_receive_id,");
//        sql.append("     rq.receive_no,");
//        sql.append("     rq.receive_date,");
//        sql.append("     rq.request_reason_code,");
//        sql.append("     rq.request_reason_other,");
//        sql.append("     rq.refund_type_place,");
//        sql.append("     rq.refund_bank_account,");
//        sql.append("     rq.refund_bank_branch_id,");
//        sql.append("     rq.refund_bank_id,");
//        sql.append("     rq.dist_code,");
//        sql.append("     rq.subdist_code,");
//        sql.append("     rq.province_code,");
//        sql.append("     rq.refund_po_order,");
//        sql.append("     rq.refund_prompt_pay,");
//        sql.append("     rq.refund_remark,");
//        sql.append("     rq.status,");
//        sql.append("     rq.section,");
//        sql.append("     ctbrrct.refund_change_type,");
//        sql.append("     ctbrrct.refund_operation_code");
//        sql.append(" FROM con_tb_refund_request rq");
//        sql.append(" JOIN con_ms_insurer mi ON ( rq.insurer_id = mi.insurer_id ) ");
//        sql.append(" LEFT JOIN con_tb_refund_request_change_type ctbrrct ON ctbrrct.refund_request_id = rq.refund_request_id");
//        sql.append(" WHERE   1 = 1");
//        if (!NumberUtils.isZeroOrNull(refundRequestId)) {
//            sql.append(" AND rq.refund_request_id = :refundRequestId");
//        }
//        param.put("refundRequestId",refundRequestId);

        return queryForObject(sql, param, new RefundRequestDelAndCcHistRowMapper());
    }

    public Long findRefundRequestIdByReceiveNo(String receiveNo) throws Exception {
        final String sql = """
                SELECT T01.REFUND_REQUEST_ID FROM(SELECT * FROM (SELECT CTRR.REFUND_REQUEST_ID,CTRR.RECEIVE_NO,
                 NVL(CTRC.PROGRESS_STATUS, NVL(CTRI.PROGRESS_STATUS,CTRE.PROGRESS_STATUS)) AS PROGRESS_STATUS,
                 NVL(CTBRC.ORDER_STATUS, NVL(CTB33.ORDER_STATUS,CTB39.ORDER_STATUS)) AS ORDER_STATUS
                 FROM CON_TB_REFUND_REQUEST CTRR
                 LEFT JOIN CON_TR_REFUND_COMPANY CTRC ON CTRC.REFUND_REQUEST_ID = CTRR.REFUND_REQUEST_ID 
                 LEFT JOIN CON_TR_REFUND_INSURER CTRI ON CTRI.REFUND_REQUEST_ID = CTRR.REFUND_REQUEST_ID 
                 LEFT JOIN CON_TR_REFUND_EMPLOYEE CTRE ON CTRE.REFUND_REQUEST_ID = CTRR.REFUND_REQUEST_ID 
                 LEFT JOIN CON_TB_REQUESTER_COMPANY CTBRC ON CTBRC.REFUND_REQUEST_ID = CTRR.REFUND_REQUEST_ID 
                 LEFT JOIN CON_TB_REQUESTER33 CTB33 ON CTB33.REFUND_REQUEST_ID = CTRR.REFUND_REQUEST_ID 
                 LEFT JOIN CON_TB_REQUESTER39 CTB39 ON CTB39.REFUND_REQUEST_ID = CTRR.REFUND_REQUEST_ID 
                 WHERE 1 = 1 AND CTRR.APPROVE_STATUS = '1') T0 
                 WHERE 1 = 1 AND T0.PROGRESS_STATUS = '1' AND T0.ORDER_STATUS = 'P' AND T0.RECEIVE_NO = :receiveNo) T01
                 GROUP BY T01.REFUND_REQUEST_ID
                """;
        final Map<String, Object> param = new HashMap<>();
        param.put("receiveNo", receiveNo);
        return queryForObject(sql, param, Long.class);
    }

    public String queryInsurerIdByReceiveNo(String receiveNo) throws Exception {
        try {
            final String sql = """
                    SELECT CMI.ID_CARD_NO FROM CON_TB_REFUND_REQUEST CTRI
                     JOIN CON_MS_INSURER CMI ON CMI.INSURER_ID = CTRI.INSURER_ID WHERE CTRI.RECEIVE_NO = :receiveNo
                    """;
            final Map<String, Object> param = new HashMap<>(1);
            param.put("receiveNo", receiveNo);

            return queryForObject(sql, param, String.class);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String findReceiveNoByReceiveInsurerIds(List<Long> receiveInsurerIds) throws Exception {
        try {
            final String sql = """
                    SELECT T1.RECEIVE_NO 
                    FROM CON_TB_REFUND_REQUEST T1 
                    LEFT JOIN CON_TB_REFUND_PERIOD T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID 
                    WHERE T2.RECEIVE_INSURER_ID IN  (:receiveInsurerIds) AND T2.STATUS = 'A'  
                    ORDER BY RECEIVE_NO DESC 
                    FETCH FIRST ROW ONLY
                    """;
            final Map<String, Object> param = new HashMap<>(1);
            param.put("receiveInsurerIds", receiveInsurerIds);

            return queryForObject(sql, param, String.class);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public List<RefundHoldReceiveBean> findRefundRequestHoldReceive(SearchOverHoldReceiveRequest request) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT " +
                " * " +
                " FROM " +
                " ( ";
        if (StringUtils.isEmpty(request.getReceiveNo())) {
            sql += " SELECT " +
                    " NULL                                      AS REFUND_REQUEST_ID, " +
                    " CTBHR.HOLD_RECEIVE_ID, " +
                    " NULL AS RECEIVE_NO, " +
                    " CTBHR.ID_CARD_NO, " +
                    " CTBHR.PAY_PERIOD, " +
                    " CTBHR.PAY_PERIOD_MONTH, " +
                    " CTBHR.PAY_PERIOD_YEAR, " +
                    " NVL( CTBHR.CNTR_AMOUNT, 0 )               AS CNTR_AMOUNT, " +
                    " NVL( CTBHR.INTEREST_AMOUNT, 0 )           AS INTEREST_AMOUNT, " +
                    " NVL( CTBHR.CNTR_AMOUNT, 0 ) + NVL( CTBHR.INTEREST_AMOUNT, 0 ) AS TOTAL_CNTR, " +
                    " CTBHR.RECEIPT_NO, " +
                    " CTBHR.RECEIPT_NAME                        AS FULL_NAME, " +
                    " CTBHR.RECEIPT_DATE                        AS PAYMENT_DATE, " +
                    " CTBHR.SECTION, " +
                    " NVL( CTBHR.CNTR_AMOUNT, 0 )                         AS OVER_PAID, " +
                    " NVL( CTBHR.INTEREST_AMOUNT, 0 )                     AS OVER_INTEREST_PAID, " +
                    " NVL( CTBHR.CNTR_AMOUNT, 0 ) + NVL( CTBHR.INTEREST_AMOUNT, 0 ) AS TOTAL_OVER, " +
                    " NULL                                      AS PROGRESS_STATUS, " +
                    " NULL                                      AS REVERSE_STATUS, " +
                    " NULL                                      AS REVERSE_REMARK, " +
                    " '10'                                      AS REFUND_REASON_CODE, " +
                    " NULL                                      AS APPROVE_DATE " +
                    " FROM " +
                    " CON_TB_HOLD_RECEIVE CTBHR " +
                    " WHERE " +
                    " 1 = 1 " +
                    " AND CTBHR.SECTION = '1' " +
                    " AND CTBHR.FLAG_STATUS = 'X' " +
                    " AND CTBHR.REASON_CODE = '1' " +
                    " AND CTBHR.REFUND_REASON_CODE = '10' " +
                    " AND NOT EXISTS ( SELECT CTBRP.REFUND_REQUEST_ID FROM CON_TB_REFUND_PERIOD CTBRP " +
                    " WHERE CTBRP.HOLD_RECEIVE_ID = CTBHR.HOLD_RECEIVE_ID AND CTBRP.STATUS NOT IN ( 'C', 'D' ) " +
                    " ) ";
            if (!StringUtils.isEmpty(request.getReceiptNo())) {
                sql += " AND RECEIPT_NO = :receiptNo ";
            }
            if (!StringUtils.isEmpty(request.getIdCardNo())) {
                sql += " AND ID_CARD_NO = :idCardNo ";
            }
            if (!StringUtils.isEmpty(request.getFullName())) {
                sql += " AND RECEIPT_NAME = :fullName ";
            }
            sql += " UNION ALL";
        }
        sql += " SELECT " +
                " CTBRR.REFUND_REQUEST_ID, " +
                " CTBRP.HOLD_RECEIVE_ID, " +
                " CTBRR.RECEIVE_NO, " +
                " CTBHR.ID_CARD_NO, " +
                " CTBRP.REFUND_PERIOD                       AS PAY_PERIOD, " +
                " CTBRP.REFUND_PERIOD_MONTH                 AS PAY_PERIOD_MONTH, " +
                " CTBRP.REFUND_PERIOD_YEAR                  AS PAY_PERIOD_YEAR, " +
                " NVL(CTBHR.CNTR_AMOUNT, 0)                 AS CNTR_AMOUNT, " +
                " NVL(CTBHR.INTEREST_AMOUNT, 0)             AS INTEREST_AMOUNT, " +
                " NVL(CTBHR.CNTR_AMOUNT, 0) + NVL(CTBHR.INTEREST_AMOUNT, 0) AS TOTAL_CNTR, " +
                " CTBHR.RECEIPT_NO, " +
//                " CTBR39.FULL_NAME, "+
                " CTBHR.RECEIPT_NAME                        AS FULL_NAME, " +
                " CTBHR.RECEIPT_DATE                        AS PAYMENT_DATE, " +
                " CTBRR.SECTION, " +
                " NVL( CTBRP.REFUND_EMPE_AMOUNT, NVL( CTBRP.REQUEST_EMPE_AMOUNT, 0 ) )                                         AS OVER_PAID, " +
                " NVL( CTBRP.REFUND_INTEREST_AMOUNT, NVL( CTBRP.REQUEST_INTEREST_AMOUNT, 0 ) )                                 AS OVER_INTEREST_PAID, " +
                " NVL( CTBRP.REFUND_EMPE_AMOUNT, NVL( CTBRP.REQUEST_EMPE_AMOUNT, 0 ) ) + NVL( CTBRP.REFUND_INTEREST_AMOUNT, NVL( CTBRP.REQUEST_INTEREST_AMOUNT, 0 ) ) AS TOTAL_OVER, " +
                " CTRRI.PROGRESS_STATUS, " +
                " CTBRR.REVERSE_STATUS, " +
                " CTBRR.REVERSE_REMARK, " +
                " CTBRR.REQUEST_REASON_CODE                 AS REFUND_REASON_CODE, " +
                " CTBRR.APPROVE_DATE " +
                " FROM " +
                " CON_TB_REFUND_REQUEST CTBRR " +
                " JOIN CON_TB_REFUND_PERIOD  CTBRP ON CTBRP.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID " +
                " JOIN CON_TR_REFUND_INSURER CTRRI ON CTRRI.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID " +
                " LEFT JOIN CON_TB_HOLD_RECEIVE   CTBHR ON CTBHR.HOLD_RECEIVE_ID = CTBRP.HOLD_RECEIVE_ID " +
//                " LEFT JOIN CON_TB_REQUESTER39    CTBR39 ON CTBR39.REFUND_REQUEST_ID = CTBRR.REFUND_REQUEST_ID "+
                " WHERE " +
                " 1 = 1 " +
                " AND CTBRR.SECTION = '1' " +
                " AND ( CTBRP.STATUS != 'D' OR CTBRP.STATUS IS NULL ) " +
                " AND CTBRR.REQUEST_REASON_CODE = '10' ";
        if (!StringUtils.isEmpty(request.getReceiveNo())) {
            sql += " AND CTBRR.RECEIVE_NO = :receiveNo ";
        } else {
            if (!StringUtils.isEmpty(request.getReceiptNo())) {
                sql += " AND CTBHR.RECEIPT_NO = :receiptNo ";
            }
            if (!StringUtils.isEmpty(request.getIdCardNo())) {
                sql += " AND CTBHR.ID_CARD_NO = :idCardNo ";
            }
            if (!StringUtils.isEmpty(request.getFullName())) {
                sql += " AND CTBHR.RECEIPT_NAME = :fullName ";
            }
        }
        sql += " ) T0 " +
                " WHERE " +
                " 1 = 1 ";
        if (!StringUtils.isEmpty(request.getProgressStatus())) {
            switch (request.getProgressStatus()) {
                case "N" -> sql += (" AND ( t0.progress_status = 'N' OR t0.progress_status IS NULL )");
//                    case "W" -> sql.append(" AND t0.progress_status = 'W' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
//                            " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status IS NULL ) ");
                case "W" -> sql += (" AND t0.progress_status = 'W' ");
                case "0" ->
                        sql += (" AND t0.progress_status = '0' AND ( EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                                " WHERE ctr39.refund_request_id = t0.refund_request_id AND ( ctr39.order_status IS NULL OR ctr39.order_status = 'C' ) ) " +
                                " OR NOT EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39 WHERE ctr39.refund_request_id = t0.refund_request_id ) ) ");
                case "1" ->
                        sql += (" AND t0.progress_status = '1' AND ( EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                                " WHERE ctr39.refund_request_id = t0.refund_request_id AND ( ctr39.order_status IS NULL OR ctr39.order_status = 'C' ) ) " +
                                " OR NOT EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39 WHERE ctr39.refund_request_id = t0.refund_request_id ) ) ");
                case "S" ->
                        sql += (" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                                " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status = 'W' ) ");
                case "F" ->
                        sql += (" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                                " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status = 'F' ) ");
                case "P" ->
                        sql += (" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                                " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status = 'P' ) ");
                case "T" ->
                        sql += (" AND t0.progress_status = '1' AND EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                                " WHERE ctr39.refund_request_id = t0.refund_request_id AND ctr39.order_status = 'T' ) ");
                case "C" ->
                        sql += (" AND t0.progress_status = 'C' AND ( EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39" +
                                " WHERE ctr39.refund_request_id = t0.refund_request_id AND ( ctr39.order_status IS NULL OR ctr39.order_status = 'C' OR ctr39.order_status = 'F' ) ) " +
                                " OR NOT EXISTS ( SELECT ctr39.refund_request_id FROM con_tb_requester39 ctr39 WHERE ctr39.refund_request_id = t0.refund_request_id ) ) ");
            }
        }
        if (StringUtils.isEmpty(request.getReceiveNo())) {
            if (!StringUtils.isEmpty(request.getStartPeriodYear())
                    && !StringUtils.isEmpty(request.getStartPeriodMonth())
                    && !StringUtils.isEmpty(request.getEndPeriodYear())
                    && !StringUtils.isEmpty(request.getEndPeriodMonth())) {
                sql += (" AND pay_period_year || pay_period_month BETWEEN :startPeriod AND :endPeriod");
                param.put("startPeriod", request.getStartPeriodYear() + request.getStartPeriodMonth());
                param.put("endPeriod", request.getEndPeriodYear() + request.getEndPeriodMonth());
            }
        }
        param.put("receiptNo", request.getReceiptNo());
        param.put("idCardNo", request.getIdCardNo());
        param.put("fullName", request.getFullName());
        param.put("receiveNo", request.getReceiveNo());
        return queryForList(sql, param, new RefundHoldReceiveRowMapper());
    }
}
