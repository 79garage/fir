package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRequester33RowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRequesterInRequesterListRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OrderRowMapper;

@Repository
public class ConTbRequester33DaoImpl extends DaoBase<Long> {
    public ConTbRequester33DaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<ConTbRequesterBean> queryConTbRequester33(Long refundRequestId) throws Exception{
        final String sql = """
                SELECT requester_id,refund_request_id,refund_receipt_id,order_no,order_status,id_card_no,full_name,title_code,first_name,last_name,requester_type,
                 relation,address,village,moo,soi,road,province_code,province_name,dist_code,dist_name,subdist_code,subdist_name,zip_code,telephone,mobile,
                 email,payment_place_type,bank_code,bank_acc,bank_acc_name,post_office,promptpay,sso_code,receipt_no,receipt_date,refund_total_amount,
                 relation_group,nationality_code,nationality,null as section,order_remark, period, po_order, po_order_name,
                 refund_interest_amount, nvl(refund_total_amount, 0) - nvl(refund_interest_amount, 0) AS refund_empr_amount
                 FROM con_tb_requester33 WHERE 1 = 1 AND status = 'A' AND refund_request_id = :refundRequestId
                 ORDER BY requester_id
                """;
        Map<String,Object> param = new HashMap<>(1);
        param.put("refundRequestId",refundRequestId);
        return queryForList(sql, param, new ConTbRequester33RowMapper());
    }

    public List<ConTbRequesterBean> queryRequester33InRequesterList(Long refundRequestId) throws Exception{
        final String sql = """
                SELECT refund_request_id,refund_receipt_id,order_no,order_status,id_card_no,full_name,title_code,first_name,last_name,requester_type,
                 relation,address,village,moo,soi,road,province_code,province_name,dist_code,dist_name,subdist_code,subdist_name,null AS loc_name, null AS loc_name_rep, zip_code,telephone,mobile,
                 email,payment_place_type,bank_code,bank_acc,bank_acc_name,post_office,promptpay,sso_code,receipt_no,receipt_date,SUM(refund_total_amount) AS refund_total_amount,
                 relation_group,nationality_code,nationality,null as section,order_remark, po_order, po_order_name,
                 SUM(nvl(refund_interest_amount, 0)) AS refund_interest_amount, SUM(nvl(refund_total_amount, 0)) - SUM(nvl(refund_interest_amount, 0)) AS refund_empr_amount, 0 AS refund_empe_amount
                 ,sum(nvl(refund_sickness_amount, 0)) as refund_sickness_amount, sum(nvl(refund_retirement_amount, 0)) as refund_retirement_amount
                 ,sum(nvl(refund_sickness_rate, 0)) as refund_sickness_rate, sum(nvl(refund_retirement_rate, 0)) as refund_retirement_rate
                 ,sum(nvl(refund_unemployment_rate, 0)) as refund_unemployment_rate
                 ,sum(nvl(refund_unemployment_amount, 0)) as refund_unemployment_amount,sum(nvl(refund_total_amount, 0)) as refund_total_amount
                 FROM con_tb_requester33 WHERE 1 = 1 AND status = 'A' AND refund_request_id = :refundRequestId
                 GROUP BY refund_request_id,refund_receipt_id,order_no,order_status,id_card_no,full_name,title_code,first_name,last_name,requester_type,
                 relation,address,village,moo,soi,road,province_code,province_name,dist_code,dist_name,subdist_code,subdist_name,zip_code,telephone,mobile,
                 email,payment_place_type,bank_code,bank_acc,bank_acc_name,post_office,promptpay,sso_code,receipt_no,receipt_date,
                 relation_group,nationality_code,nationality,order_remark, po_order, po_order_name
                 ORDER BY id_card_no
                """;
        Map<String,Object> param = new HashMap<>(1);
        param.put("refundRequestId",refundRequestId);
        return queryForList(sql, param, new ConTbRequesterInRequesterListRowMapper());
    }

    public List<OrderBean> queryOrderNoAndOrderStatus(Long refundRequestId, String orderStatus, String receiveNo, String period) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ctr33.order_no,");
        sql.append(" ctr33.order_status,");
        sql.append(" ctrr.receipt_no,");
        sql.append(" ctrr.payment_date");
        sql.append(" FROM con_tb_requester33 ctr33");
        sql.append(" LEFT JOIN con_tb_refund_receipt ctrr ON ctrr.refund_receipt_id = ctr33.refund_receipt_id");
        sql.append(" WHERE 1 = 1");
        sql.append(" AND refund_request_id = :refundRequestId");
        if (!StringUtils.isEmpty(period)) {
            sql.append(" AND ctr33.period = :period ");
        }
        if (StringUtils.isEmpty(receiveNo)) {
//            if (!StringUtils.isEmpty(orderStatus) && ("1".equals(orderStatus) || "0".equals(orderStatus) || "C".equals(orderStatus))) {
//                sql.append(" AND (order_status IN ('W','P','F','T','C') OR order_status IS NULL)");
//            } else {
//                sql.append(" AND order_status = :orderStatus");
//            }
            if (!StringUtils.isEmpty(orderStatus) && List.of("1", "0", "C").contains(orderStatus)) {
                sql.append(" AND (order_status IN ('W','P','F','T','C') OR order_status IS NULL)");
            } else if (!StringUtils.isEmpty(orderStatus) && List.of("all", "ALL").contains(orderStatus)) {
                sql.append(" ");
            } else {
                sql.append(" AND order_status = :orderStatus");
            }
        }
        sql.append(" ORDER BY requester_id ASC");
        Map<String, Object> param = new HashMap<>();
        param.put("period", period);
        param.put("refundRequestId", refundRequestId);
        param.put("orderStatus", orderStatus);
        return queryForList(sql.toString(), param, new OrderRowMapper());
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REQUESTER33";
    }

    @Override
    protected String getPkColumnName() {
        return "REQUESTER_ID";
    }
}
