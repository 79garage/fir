package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterCompanyBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterInsurerBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRequesterInsurer39RowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRequesterM39RowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RequesterInsurerBatchRowMapper;

@Repository
public class RequesterInsurerBatchDaoImpl extends DaoBase<RequesterCompanyBatchBean> {

    public RequesterInsurerBatchDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<RequesterInsurerBatchBean> queryRequestInsurerBatch(String createBy, Date createDate, Paginable paginable, List<Long> requesterIds) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = """
                select t1.requester_id, t1.sso_code as sso_no, t1.order_no
                ,t2.receive_no as order_doc_no,t2.receive_date as order_doc_date
                ,t1.refund_total_amount as order_mny,t2.approve_date
                ,t1.id_card_no as claimant_no, t1.full_name as claimant_name
                ,null as branch_no
                ,t1.address,t1.village,t1.road,t1.province_code,t5.prov_name,t1.dist_code,t5.dist_name,t1.subdist_code,t5.subdist_name,t1.zip_code
                ,t4.refund_interest_amount as interest_mny
                ,t1.refund_total_amount as pay_mny
                ,t1.payment_place_type
                ,t1.bank_acc, t1.bank_code
                ,t1.refund_sickness_amount
                ,t1.refund_retirement_amount
                ,t1.refund_unemployment_amount
                ,t1.refund_sickness_rate
                ,t1.refund_retirement_rate
                ,t1.refund_unemployment_rate
                ,t1.refund_interest_amount
                ,t1.period
                ,t1.po_order
                ,t1.po_order_name
                ,coalesce(t3.id_card_no, t7.id_card_no)             AS payee_no
                ,coalesce(t3.full_name, t7.receipt_name)              AS payee_name
                from con_tb_requester39 t1
                inner join con_tb_refund_request t2 on t2.refund_request_id = t1.refund_request_id
                LEFT JOIN con_tb_refund_period t6 ON t6.refund_request_id = t1.refund_request_id AND t6.refund_period = t1.period
                LEFT JOIN con_tb_hold_receive t7 ON t7.hold_receive_id = t6.hold_receive_id
                LEFT join con_ms_insurer t3 on t3.insurer_id = t2.insurer_id
                left join con_tr_refund_insurer t4 on t4.refund_request_id = t1.refund_request_id
                LEFT JOIN con_ms_location t5 ON t5.subdist_code = t1.subdist_code
                where 1 = 1
                -- and t1.create_by = :createBy
                and trunc(t1.create_date) = trunc( :createDate )
                and t1.order_status is null
                and t2.approve_status = '1'
                and t2.status = 'A'
                AND t2.approve_date IS NOT NULL
                """;
        if(!CollectionUtils.isEmpty(requesterIds)) {
            sql += "  and t1.requester_id in ( :requesterIds )";
            param.put("requesterIds", requesterIds);
        }

        param.put("createBy", createBy);
        param.put("createDate", createDate);

        return queryForList(sql, param, paginable, new RequesterInsurerBatchRowMapper());
    }
//    public List<RefundRequesterInsurerBatchBean> queryRequester39Batch(String createBy, Date createDate, Paginable paginable) throws Exception {
//        final String sql = """
//                SELECT ctr39.requester_id,ctr39.refund_request_id,NULL as refund_receipt_id,ctr39.payment_place_type as refund_type_place,NULL as refund_bank_account,NULL as refund_po_order,
//                 NULL as refund_prompt_pay,ctr39.refund_total_amount,ctrr.section,ctrr.split_refund,cmd.dep_code,ctr39.id_card_no,ctr39.full_name,
//                 ctr39.title_code,ctr39.first_name,ctr39.last_name,ctr39.address,ctr39.village,ctr39.road,ctr39.province_code,
//                 ctr39.province_name as prov_name,ctr39.dist_code,ctr39.dist_name,ctr39.subdist_code,ctr39.subdist_name,ctr39.zip_code,ctr39.telephone,
//                 ctr39.mobile,ctr39.email,ctr39.nationality,ctr39.bank_code,ctr39.nationality_code
//                 FROM con_tb_requester39 ctr39
//                 LEFT JOIN con_tb_refund_request ctrr ON ctr39.refund_request_id = ctrr.refund_request_id
//                 LEFT JOIN con_ms_department cmd ON cmd.department_id = ctrr.department_receive_id
//                 LEFT JOIN con_ms_insurer cmi ON cmi.insurer_id = ctrr.insurer_id
//                 WHERE 1 = 1 AND ctr39.requester_type = '2' AND ctr39.order_no IS NULL AND ctr39.order_status IS NULL
//                """;
//
//        Map<String, Object> param = new HashMap<>();
//        param.put("createBy", createBy);
//        param.put("createDate", createDate);
//
//        return queryForList(sql, param, paginable, new RefundRequesterInsurerBatchRowMapper());
//    }
    public List<ConTbRequesterBean> queryConTbRequester39Batch(Paginable paginable, List<Long> refundRequestIds, String requesterType) throws Exception {
        String sql = """
                SELECT ctr39.requester_id,ctr39.refund_request_id,ctr39.refund_receipt_id,ctr39.id_card_no,ctr39.full_name,ctr39.title_code,
                 ctr39.first_name,ctr39.last_name,ctr39.requester_type,ctr39.relation,ctr39.address,ctr39.village,ctr39.moo,ctr39.soi,
                 ctr39.road,ctr39.province_code,ctr39.province_name,ctr39.dist_code,ctr39.dist_name,ctr39.subdist_code,ctr39.subdist_name,
                 ctr39.zip_code,ctr39.telephone,ctr39.mobile,ctr39.email,ctr39.payment_place_type,ctr39.bank_code,ctr39.bank_acc,ctr39.bank_acc_name,
                 ctr39.post_office,ctr39.promptpay,ctr39.sso_code,ctr39.refund_total_amount,ctr39.relation_group,ctr39.nationality_code,ctr39.nationality,ctrr.section,
                 ctr39.refund_sickness_amount, ctr39.refund_retirement_amount, ctr39.refund_unemployment_amount, ctr39.refund_sickness_rate, ctr39.refund_retirement_rate,
                 ctr39.refund_unemployment_rate, ctr39.refund_interest_amount, ctr39.period, ctr39.po_order, ctr39.po_order_name
                 FROM con_tb_requester39 ctr39
                 JOIN con_tb_refund_request ctrr ON ctrr.refund_request_id = ctr39.refund_request_id
                 WHERE 1 = 1
                 --AND ctr39.requester_type = '2'
                 --AND ctr39.order_no IS NULL
                 --AND ctr39.order_status IS NULL
                 --AND trunc(ctr39.create_date) = trunc(sysdate)
                 --AND trunc(ctrr.approve_date) = trunc(sysdate)
                 AND ctrr.status = 'A'
                """;
        Map<String, Object> param = new HashMap<>();
        if (!StringUtils.isEmpty(requesterType)) {
            sql += " AND ctr39.requester_type = :requesterType ";
            param.put("requesterType", requesterType);
        } else {
//            sql += " AND ctr39.requester_type = '2' ";
        }
        if(!CollectionUtils.isEmpty(refundRequestIds)) {
            sql += " AND ctrr.refund_request_id in ( :refundRequestIds ) ";
            param.put("refundRequestIds", refundRequestIds);
        }
        return queryForList(sql, param, paginable, new ConTbRequesterInsurer39RowMapper());
    }
    
    public ConTbRequesterBean getConTbRequester39ById(final Long requesterId) throws Exception {
        String sql = 
        		"SELECT "
        		+ "    t1.requester_id, "
        		+ "    t1.refund_request_id, "
        		+ "    t1.refund_receipt_id, "
        		+ "    t1.order_no, "
        		+ "    t1.order_status, "
        		+ "    t1.id_card_no, "
        		+ "    t1.full_name, "
        		+ "    t1.title_code, "
        		+ "    t1.first_name, "
        		+ "    t1.last_name, "
        		+ "    t1.requester_type, "
        		+ "    t1.relation, "
        		+ "    t1.address, "
        		+ "    t1.village, "
        		+ "    t1.moo, "
        		+ "    t1.soi, "
        		+ "    t1.road, "
        		+ "    t1.province_code, "
        		+ "    t1.province_name, "
        		+ "    t1.dist_code, "
        		+ "    t1.dist_name, "
        		+ "    t1.subdist_code, "
        		+ "    t1.subdist_name, "
        		+ "    t1.zip_code, "
        		+ "    t1.telephone, "
        		+ "    t1.mobile, "
        		+ "    t1.email, "
        		+ "    t1.payment_place_type, "
        		+ "    t1.bank_code, "
        		+ "    t1.bank_acc, "
        		+ "    t1.bank_acc_name, "
        		+ "    t1.post_office, "
        		+ "    t1.promptpay, "
        		+ "    t1.sso_code, "
        		+ "    t1.receipt_no, "
        		+ "    t1.receipt_date, "
        		+ "    t1.refund_total_amount, "
        		+ "    t1.relation_group, "
        		+ "    t1.nationality_code, "
        		+ "    t1.nationality, "
        		+ "    t1.status, "
        		+ "    t1.order_remark, "
        		+ "    t1.section, "
        		+ "    t1.refund_sickness_amount, "
        		+ "    t1.refund_retirement_amount, "
        		+ "    t1.refund_unemployment_amount, "
        		+ "    t1.refund_sickness_rate, "
        		+ "    t1.refund_retirement_rate, "
        		+ "    t1.refund_unemployment_rate, "
        		+ "    t1.refund_interest_amount, "
        		+ "    t1.period, "
        		+ "    t1.po_order, "
        		+ "    t1.po_order_name, "
        		+ "    t2.approve_date, "
        		+ "    t2.department_receive_code AS dep_code, "
        		+ "    t1.old_order_no, "
        		+ "    t1.payback_amount "
        		+ "FROM  con_tb_requester39 t1 "
        		+ "LEFT JOIN con_tb_refund_request t2 ON t1.refund_request_id = t2.refund_request_id AND t2.section = '1' "
        		+ "WHERE t1.requester_id =:requesterId "
        		;
        Map<String, Object> param = new HashMap<>();
        param.put("requesterId", requesterId);
        
        return queryForObject(sql, param, new ConTbRequesterM39RowMapper());
    }

    @Override
    protected String getTableName() {
        return "con_tb_requester39";
    }

    @Override
    protected String getPkColumnName() {
        return "requester_id";
    }
}
