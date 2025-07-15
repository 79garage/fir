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
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterCompanyBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RequesterCompanyBatchRowMapper;

@Repository
public class RequesterCompanyBatchDaoImpl extends DaoBase<RequesterCompanyBatchBean> {

    public RequesterCompanyBatchDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<RequesterCompanyBatchBean> queryRequestCompanyBatch(String createBy, Date createDate, Paginable paginable, List<Long> requesterIds) throws Exception {
        String sql = """
                select t1.requester_id, t1.sso_code as sso_no, t1.order_no
                ,t2.receive_no as order_doc_no,t2.receive_date as order_doc_date
                ,t1.refund_total_amount as order_mny,t2.approve_date
                ,t3.company_no as claimant_no, t3.company_name as claimant_name
                ,t3.company_branch_code as branch_no
                ,t4.refund_interest_amount as interest_mny
                ,t1.refund_total_amount as pay_mny
                ,t1.payment_place_type
                ,t1.bank_acc, t1.bank_code
                ,t3.address, t5.dist_code, t5.dist_name, t5.subdist_code, t5.subdist_name, t5.prov_code, t5.prov_name, t5.zip_code
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
                from con_tb_requester_company t1
                inner join con_tb_refund_request t2 on t2.refund_request_id = t1.refund_request_id
                inner join con_ms_company t3 on t3.company_id = t1.company_id
                left join con_ms_location t5 on t5.subdist_code = t3.subdist_code
                left join con_tr_refund_company t4 on t4.refund_request_id = t1.refund_request_id
                where 1 = 1
                --t1.create_by = :createBy
                and trunc(t1.create_date) = trunc( :createDate )
                and t2.approve_status = '1'
                and t2.status = 'A'
                and t1.order_status is null
                """;
        StringBuilder sb = new StringBuilder(sql);
        Map<String, Object> param = new HashMap<>();
        param.put("createBy", createBy);
        param.put("createDate", createDate);

        if(!CollectionUtils.isEmpty(requesterIds)){
            sb.append(" and t1.requester_id in ( :requesterIds ) ");
            param.put("requesterIds", requesterIds);
        }

        return queryForList(sb.toString(), param, paginable, new RequesterCompanyBatchRowMapper());
    }


    @Override
    protected String getTableName() {
        return "con_tb_requester_company";
    }

    @Override
    protected String getPkColumnName() {
        return "requester_id";
    }
}
