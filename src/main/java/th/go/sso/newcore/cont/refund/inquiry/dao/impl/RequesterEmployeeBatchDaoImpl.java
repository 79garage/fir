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
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterCompanyBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterEmployeeBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRequesterInsurer33RowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RequesterEmployeeBatchRowMapper;

@Repository
public class RequesterEmployeeBatchDaoImpl extends DaoBase<RequesterCompanyBatchBean> {

    public RequesterEmployeeBatchDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<RequesterEmployeeBatchBean> queryRequestEmployeeBatch(String createBy, Date createDate, Paginable paginable, List<Long> requesterIds) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select t1.requester_id, t1.sso_code as sso_no, t1.order_no");
        sql.append("	,t2.receive_no as order_doc_no,t2.receive_date as order_doc_date");
        sql.append("	,t1.refund_total_amount as order_mny,t2.approve_date");
        sql.append("	,t1.id_card_no as claimant_no, t1.full_name as claimant_name");
        sql.append("	,null as branch_no");
        sql.append("	,t1.address,t1.village,t1.road,t1.province_code,t5.prov_name,t1.dist_code,t5.dist_name,t1.subdist_code,t5.subdist_name,t1.zip_code");
        sql.append("	,t4.refund_interest_amount as interest_mny");
        sql.append("	,t1.refund_total_amount as pay_mny");
        sql.append("	,t1.payment_place_type");
        sql.append("	,t1.bank_acc, t1.bank_code");
        sql.append("	,t1.refund_sickness_amount, t1.refund_retirement_amount, t1.refund_unemployment_amount, t1.refund_sickness_rate");
        sql.append("	,t1.refund_retirement_rate, t1.refund_unemployment_rate, t1.refund_interest_amount, t1.period, t1.po_order, t1.po_order_name");
        sql.append("	,t3.id_card_no as payee_no, t3.full_name as payee_name");
        sql.append("	from con_tb_requester33 t1");
        sql.append("	inner join con_tb_refund_request t2 on t2.refund_request_id = t1.refund_request_id");
        sql.append("	inner join con_ms_insurer t3 on t3.insurer_id = t2.insurer_id");
        sql.append("	left join con_tr_refund_employee t4 on t4.refund_request_id = t1.refund_request_id");
        sql.append("	LEFT JOIN con_ms_location t5 ON t5.subdist_code = t1.subdist_code");
        sql.append("	where 1 = 1");
//        sql.append("	and t1.create_by = :createBy");
        sql.append("	and trunc(t1.create_date) = trunc( :createDate )");
        sql.append("	and t1.order_status is null and t2.approve_status = '1' and t2.status = 'A' AND t2.approve_date IS NOT NULL");

        Map<String, Object> param = new HashMap<>();

        if(!CollectionUtils.isEmpty(requesterIds)){
            sql.append(" and t1.requester_id in ( :requesterIds ) ");
            param.put("requesterIds", requesterIds);
        }

        param.put("createBy", createBy);
        param.put("createDate", createDate);

        return queryForList(sql.toString(), param, paginable, new RequesterEmployeeBatchRowMapper());
    }

    public List<ConTbRequesterBean> queryConTbRequester33Batch(Paginable paginable, List<Long> refundRequestIds) throws Exception{
        String sql = """
                SELECT ctr33.requester_id,ctr33.refund_request_id,ctr33.refund_receipt_id,ctr33.id_card_no,ctr33.full_name,ctr33.title_code,
                 ctr33.first_name,ctr33.last_name,ctr33.requester_type,ctr33.relation,ctr33.address,ctr33.village,ctr33.moo,ctr33.soi,
                 ctr33.road,ctr33.province_code,ctr33.province_name,ctr33.dist_code,ctr33.dist_name,ctr33.subdist_code,ctr33.subdist_name,
                 ctr33.zip_code,ctr33.telephone,ctr33.mobile,ctr33.email,ctr33.payment_place_type,ctr33.bank_code,ctr33.bank_acc,ctr33.bank_acc_name,
                 ctr33.post_office,ctr33.promptpay,ctr33.sso_code,ctr33.refund_total_amount,ctr33.relation_group,ctr33.nationality_code,ctr33.nationality,ctrr.section,
                 ctr33.refund_sickness_amount, ctr33.refund_retirement_amount, ctr33.refund_unemployment_amount, ctr33.refund_sickness_rate, ctr33.refund_retirement_rate,
                 ctr33.refund_unemployment_rate, ctr33.refund_interest_amount, ctr33.period, ctr33.po_order, ctr33.po_order_name
                 FROM con_tb_requester33 ctr33
                 JOIN con_tb_refund_request ctrr ON ctrr.refund_request_id = ctr33.refund_request_id
                 WHERE 1 = 1
                 AND ctr33.requester_type = '2'
                 AND ctr33.order_no IS NULL
                 AND ctr33.order_status IS NULL
                 AND trunc(ctr33.create_date) = trunc(sysdate)
                 AND trunc(ctrr.approve_date) = trunc(sysdate)
                 AND ctrr.status = 'A'
                """;
        Map<String,Object> param = new HashMap<>(1);
        if (!CollectionUtils.isEmpty(refundRequestIds)) {
            sql += " AND ctrr.refund_request_id in ( :refundRequestIds )";
            param.put("refundRequestIds", refundRequestIds);
        }
        return queryForList(sql, param, paginable, new ConTbRequesterInsurer33RowMapper());
    }

    @Override
    protected String getTableName() {
        return "con_tb_requester33";
    }

    @Override
    protected String getPkColumnName() {
        return "requester_id";
    }
}
