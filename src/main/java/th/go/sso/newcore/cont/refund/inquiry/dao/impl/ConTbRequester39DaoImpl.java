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
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRequester39RowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRequesterInRequesterListRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OrderRowMapper;
@Repository
public class ConTbRequester39DaoImpl extends DaoBase<Long> {
    public ConTbRequester39DaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<ConTbRequesterBean> queryConTbRequester39(Long refundRequestId) throws Exception{
//        final String sql = """
//                SELECT requester_id,refund_request_id,refund_receipt_id,order_no,order_status,id_card_no,full_name,title_code,first_name,last_name,requester_type,
//                 relation,address,village,moo,soi,road,province_code,province_name,dist_code,dist_name,subdist_code,subdist_name,zip_code,telephone,mobile,
//                 email,payment_place_type,bank_code,bank_acc,bank_acc_name,post_office,promptpay,sso_code,receipt_no,receipt_date,refund_total_amount,
//                 relation_group,nationality_code,nationality,null as section,order_remark, period, po_order, po_order_name,
//                 refund_interest_amount, nvl(refund_total_amount, 0) - nvl(refund_interest_amount, 0) AS refund_empe_amount
//                 FROM con_tb_requester39 WHERE 1 = 1 AND (status = 'A' OR status = 'C') AND refund_request_id = :refundRequestId
//                 ORDER BY requester_id
//                """;
    	final String sql = "SELECT " +
    	                   " t1.requester_id, " + 
    	                   " t1.refund_request_id,  " +
    	                   " t1.refund_receipt_id,  " +
    	                   " t1.order_no,  " +
    	                   " t1.order_status,  " +
    	                   " t1.id_card_no,  " +
    	                   " t1.full_name,  " +
    	                   " t1.title_code,  " +
    	                   " t1.first_name,  " +
    	                   " t1.last_name,  " +
    	                   " t1.requester_type,  " +
    	                   " t1.relation,  " +
    	                   " t1.address,  " +
    	                   " t1.village,  " +
    	                   " t1.moo,  " +
    	                   " t1.soi,  " +
    	                   " t1.road,  " +
    	                   " t1.province_code,  " +
    	                   " t1.province_name,  " +
    	                   " t1.dist_code,  " +
    	                   " t1.dist_name,  " +
    	                   " t1.subdist_code,  " +
    	                   //"  t1.subdist_name, " +
    					   " t2.loc_name_rep AS subdist_name,  " +
    					   " t2.loc_name, " +
    					   " t2.loc_name_rep,  " +
    					   " t1.zip_code,  " +
    					   " t1.telephone,  " +
    					   " t1.mobile,  " +
    					   " t1.email,  " +
    					   " t1.payment_place_type,  " +
    					   " t1.bank_code,  " +
    					   " t1.bank_acc,  " +
    					   " t1.bank_acc_name,  " +
    					   " t1.post_office,  " +
    					   " t1.promptpay,  " +
    					   " t1.sso_code,  " +
    					   " t1.receipt_no,  " +
    					   " t1.receipt_date,  " +
    					   " t1.refund_total_amount,  " +
    					   " t1.relation_group,  " +
    					   " t1.nationality_code,  " +
    					   " t1.nationality,  " +
    					   " NULL  AS section, " +
    					   " t1.order_remark, " +
    					   " t1.period, " +
    					   " t1.po_order, " +
    					   " t1.po_order_name, " +
    					   " t1.refund_interest_amount, " +
    					   " nvl(t1.refund_total_amount, 0) - nvl(t1.refund_interest_amount, 0) AS refund_empe_amount,  " +
    					   " COALESCE(t1.refund_retirement_amount, t3.refund_retirement_amount, 0 ) AS refund_retirement_amount, " +
    					   " COALESCE(t1.refund_sickness_amount, t3.refund_sickness_amount, 0 ) AS refund_sickness_amount, " +
    					   " COALESCE(t1.refund_unemployment_amount, t3.refund_unemployment_amount, 0 ) AS refund_unemployment_amount, " +
    					   " COALESCE(t1.refund_retirement_rate, t3.refund_retirement_rate, 0 ) AS refund_retirement_rate, " +
    					   " COALESCE(t1.refund_sickness_rate, t3.refund_sickness_rate, 0 ) AS refund_sickness_rate, " +
    					   " COALESCE(t1.refund_unemployment_rate, t3.refund_unemployment_rate, 0 ) AS refund_unemployment_rate " +
    					   " FROM con_tb_requester39 t1  " +
    					   " LEFT JOIN con_ms_location t2 ON t1.subdist_code = t2.loc_code   " +   
    					   " LEFT JOIN con_tb_refund_period t3 ON t1.period = t3.refund_period AND t1.refund_request_id = t3.refund_request_id AND t1.requester_type = '1' " +
    					   " WHERE 1 = 1  " +
    					   "  AND ( t1.status = 'A' OR t1.status = 'C' )  " +
    					   "  AND t1.refund_request_id = :refundRequestId " +
    					   " ORDER BY t1.requester_id ";
    	
        Map<String,Object> param = new HashMap<>(1);
        param.put("refundRequestId",refundRequestId);
        return queryForList(sql, param, new ConTbRequester39RowMapper());
    }
    public List<ConTbRequesterBean> queryRequester39InRequesterList(Long refundRequestId) throws Exception{
//        final String sql = """
//                SELECT refund_request_id,refund_receipt_id,order_no,order_status,id_card_no,full_name,title_code,first_name,last_name,requester_type,
//                 relation,address,village,moo,soi,road,province_code,province_name,dist_code,dist_name,subdist_code,subdist_name,zip_code,telephone,mobile,
//                 email,payment_place_type,bank_code,bank_acc,bank_acc_name,post_office,promptpay,sso_code,receipt_no,receipt_date,SUM(refund_total_amount) AS refund_total_amount,
//                 relation_group,nationality_code,nationality,null as section,order_remark, po_order, po_order_name,
//                 SUM(nvl(refund_interest_amount, 0)) AS refund_interest_amount, SUM(nvl(refund_total_amount, 0)) - SUM(nvl(refund_interest_amount, 0)) AS refund_empe_amount, 0 AS refund_empr_amount
//                 ,sum(nvl(refund_sickness_amount, 0)) as refund_sickness_amount, sum(nvl(refund_retirement_amount, 0)) as refund_retirement_amount
//                 ,sum(nvl(refund_unemployment_amount, 0)) as refund_unemployment_amount,sum(nvl(refund_total_amount, 0)) as refund_total_amount
//                 FROM con_tb_requester39 WHERE 1 = 1 AND (status = 'A' OR status = 'C') AND refund_request_id = :refundRequestId
//                 GROUP BY refund_request_id,refund_receipt_id,order_no,order_status,id_card_no,full_name,title_code,first_name,last_name,requester_type,
//                 relation,address,village,moo,soi,road,province_code,province_name,dist_code,dist_name,subdist_code,subdist_name,zip_code,telephone,mobile,
//                 email,payment_place_type,bank_code,bank_acc,bank_acc_name,post_office,promptpay,sso_code,receipt_no,receipt_date,
//                 relation_group,nationality_code,nationality,order_remark, po_order, po_order_name
//                 ORDER BY id_card_no
//                """;
    	
    	final String sql = "SELECT "
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
//    			+ "    t1.subdist_name, "
    			+ "    t2.loc_name_rep AS subdist_name, "
    			+ "    t2.loc_name,  "
    			+ "    t2.loc_name_rep, "
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
    			+ "    SUM(t1.refund_total_amount) AS refund_total_amount, "
    			+ "    t1.relation_group, "
    			+ "    t1.nationality_code, "
    			+ "    t1.nationality, "
    			+ "    NULL AS section, "
    			+ "    t1.order_remark, "
    			+ "    t1.po_order, "
    			+ "    t1.po_order_name, "
    			+ "    SUM(nvl(t1.refund_interest_amount, 0)) AS refund_interest_amount, "
    			+ "    SUM(nvl(t1.refund_total_amount, 0)) - SUM(nvl(t1.refund_interest_amount, 0)) AS refund_empe_amount, "
    			+ "    0 AS refund_empr_amount, "
//    			+ "    SUM(nvl(t1.refund_sickness_amount, 0)) AS refund_sickness_amount, "
//    			+ "    SUM(nvl(t1.refund_retirement_amount, 0)) AS refund_retirement_amount, "
//    			+ "    SUM(nvl(t1.refund_unemployment_amount, 0)) AS refund_unemployment_amount, "
//    			+ "    SUM(nvl(t1.refund_sickness_rate, 0)) AS refund_sickness_rate, "
//    			+ "    SUM(nvl(t1.refund_retirement_rate, 0)) AS refund_retirement_rate, "
//    			+ "    SUM(nvl(t1.refund_unemployment_rate, 0)) AS refund_unemployment_rate, "
    			+ "    SUM(COALESCE(t1.refund_sickness_amount, t3.refund_sickness_amount, 0)) AS refund_sickness_amount, "
    			+ "    SUM(COALESCE(t1.refund_retirement_amount, t3.refund_retirement_amount, 0)) AS refund_retirement_amount, "
			    + "    SUM(COALESCE(t1.refund_unemployment_amount, t3.refund_unemployment_amount, 0)) AS refund_unemployment_amount, "
    			+ "    SUM(COALESCE(t1.refund_sickness_amount, t3.refund_sickness_amount, 0)) AS refund_sickness_rate, "
    			+ "    SUM(COALESCE(t1.refund_retirement_rate, t3.refund_retirement_rate, 0)) AS refund_retirement_rate, "
			    + "    SUM(COALESCE(t1.refund_unemployment_rate, t3.refund_unemployment_rate, 0)) AS refund_unemployment_rate, "
    			+ "    SUM(nvl(t1.refund_total_amount, 0))  AS refund_total_amount " 
    			+ "FROM con_tb_requester39 t1 "
    			+ "LEFT JOIN con_ms_location t2 on t1.subdist_code = t2.loc_code "
    			+ "LEFT JOIN con_tb_refund_period t3 ON t1.period = t3.refund_period AND t1.refund_request_id = t3.refund_request_id AND t1.requester_type = '1' "
    			+ "WHERE 1 = 1 "
    			+ "    AND ( t1.status = 'A' OR t1.status = 'C' ) "
    			+ "    AND t1.refund_request_id = :refundRequestId "
    			+ "GROUP BY "
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
    			+ "    t1.relation_group, "
    			+ "    t1.nationality_code, "
    			+ "    t1.nationality, "
    			+ "    t1.order_remark, "
    			+ "    t1.po_order, "
    			+ "    t1.po_order_name, "
    			+ "    t2.loc_name, "
    			+ "    t2.loc_name_rep "
    			+ "ORDER BY  t1.id_card_no";
    	
        Map<String,Object> param = new HashMap<>(1);
        param.put("refundRequestId",refundRequestId);
        return queryForList(sql, param, new ConTbRequesterInRequesterListRowMapper());
    }
    public List<OrderBean> queryOrderNoAndOrderStatus(Long refundRequestId, String orderStatus, String receiveNo, String period) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ctr39.order_no,");
        sql.append(" ctr39.order_status,");
        sql.append(" ctrr.receipt_no,");
        sql.append(" ctrr.payment_date");
        sql.append(" FROM con_tb_requester39 ctr39");
        sql.append(" LEFT JOIN con_tb_refund_receipt ctrr ON ctrr.refund_receipt_id = ctr39.refund_receipt_id");
        sql.append(" WHERE 1 = 1");
        sql.append(" AND ctr39.refund_request_id = :refundRequestId");
        if (!StringUtils.isEmpty(period)) {
            sql.append(" AND ctr39.period = :period ");
        }
        if (StringUtils.isEmpty(receiveNo)) {
//            if (!StringUtils.isEmpty(orderStatus) && ("1".equals(orderStatus) || "0".equals(orderStatus) || "C".equals(orderStatus))){
//                sql.append(" AND (order_status IN ('W','P','F','T','C') OR order_status IS NULL)");
//            }else {
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
//        final String sql = """
//                SELECT ctr39.order_no,ctr39.order_status,ctrr.receipt_no
//                 FROM con_tb_requester39 ctr39
//                 LEFT JOIN con_tb_refund_receipt ctrr ON ctrr.refund_receipt_id = ctr39.refund_receipt_id
//                 WHERE 1 = 1 AND refund_request_id = :refundRequestId AND order_status = :orderStatus ORDER BY requester_id ASC
//                """;
        Map<String,Object> param = new HashMap<>(1);
        param.put("refundRequestId",refundRequestId);
        param.put("period", period);
        param.put("orderStatus", orderStatus);
        return queryForList(sql.toString(), param, new OrderRowMapper());
    }
    public List<OrderBean> queryOrderByRefundRequestId(Long refundRequestId) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ctr39.order_no,");
        sql.append(" ctr39.order_status,");
        sql.append(" ctrr.receipt_no,");
        sql.append(" ctrr.payment_date");
        sql.append(" FROM con_tb_requester39 ctr39");
        sql.append(" LEFT JOIN con_tb_refund_receipt ctrr ON ctrr.refund_receipt_id = ctr39.refund_receipt_id");
        sql.append(" WHERE 1 = 1");
        sql.append(" AND refund_request_id = :refundRequestId");
        sql.append(" ORDER BY requester_id ASC");
        Map<String,Object> param = new HashMap<>(1);
        param.put("refundRequestId",refundRequestId);
        return queryForList(sql.toString(), param, new OrderRowMapper());
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REQUESTER39";
    }

    @Override
    protected String getPkColumnName() {
        return "REQUESTER_ID";
    }
}
