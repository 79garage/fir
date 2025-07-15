package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRequesterCompanyRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OrderRowMapper;

@Repository
public class ConTbRequesterCompanyDaoImpl extends DaoBase<Long> {
    public ConTbRequesterCompanyDaoImpl(DataSource dataSource) {
        super(dataSource);
    }
    public List<OrderBean> queryOrderNoAndOrderStatus(Long refundRequestId, String orderStatus, String receiveNo, String period) throws Exception{
//        boolean checkOrderStatus = "1".equals(orderStatus) || "0".equals(orderStatus) || "C".equals(orderStatus);
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * ");
        sql.append(" FROM ");
        sql.append(" ( ");
        sql.append(" SELECT ");
        sql.append(" ctr33.order_no, ");
        sql.append(" ctr33.order_status, ");
        sql.append(" ctrr.receipt_no, ");
        sql.append(" ctrr.payment_date ");
        sql.append(" FROM con_tb_requester33    ctr33 ");
        sql.append(" LEFT JOIN con_tb_refund_receipt ctrr ON ctrr.refund_receipt_id = ctr33.refund_receipt_id ");
        sql.append(" WHERE 1 = 1 ");
        sql.append(" AND refund_request_id = :refundRequestId ");
        if (!StringUtils.isEmpty(period)) {
            sql.append(" AND ctr33.period = :period ");
        }
        if (StringUtils.isEmpty(receiveNo)) {
//            if (!StringUtils.isEmpty(orderStatus) && checkOrderStatus){
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
        sql.append(" UNION ALL ");
        sql.append(" SELECT ");
        sql.append(" ctbrc.order_no, ");
        sql.append(" ctbrc.order_status, ");
        sql.append(" ctrr.receipt_no, ");
        sql.append(" ctrr.payment_date ");
        sql.append(" FROM con_tb_requester_company ctbrc ");
        sql.append(" LEFT JOIN con_tb_refund_receipt    ctrr ON ctrr.refund_receipt_id = ctbrc.refund_receipt_id ");
        sql.append(" WHERE 1 = 1 ");
        sql.append(" AND refund_request_id = :refundRequestId ");
        if (!StringUtils.isEmpty(period)) {
            sql.append(" AND ctbrc.period = :period ");
        }
        if (StringUtils.isEmpty(receiveNo)) {
//            if (!StringUtils.isEmpty(orderStatus) && checkOrderStatus){
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
        sql.append(" ) t0 ");
        sql.append(" WHERE 1 = 1 ");
        sql.append(" ORDER BY order_no ASC ");
//        sql.append("SELECT ctbrc.order_no,");
//        sql.append(" ctbrc.order_status,");
//        sql.append(" ctrr.receipt_no,");
//        sql.append(" ctrr.payment_date");
//        sql.append(" FROM con_tb_requester_company ctbrc");
//        sql.append(" LEFT JOIN con_tb_refund_receipt ctrr ON ctrr.refund_receipt_id = ctbrc.refund_receipt_id");
//        sql.append(" WHERE 1 = 1");
//        sql.append(" AND refund_request_id = :refundRequestId");
//        if (StringUtils.isEmpty(receiveNo)) {
//            if (!StringUtils.isEmpty(orderStatus) && ("1".equals(orderStatus) || "0".equals(orderStatus) || "C".equals(orderStatus))){
//                sql.append(" AND (order_status IN ('W','P','F','T','C') OR order_status IS NULL)");
//            }else {
//                sql.append(" AND order_status = :orderStatus");
//            }
//        }
//        sql.append(" ORDER BY requester_id ASC");
        Map<String,Object> param = new HashMap<>(1);
        param.put("period", period);
        param.put("refundRequestId",refundRequestId);
        param.put("orderStatus", orderStatus);
        return queryForList(sql.toString(), param, new OrderRowMapper());
    }

    public List<ConTbRequesterCompanyBean> queryConTbRequesterCompany(Long refundRequestId) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        final String sql = """
                SELECT * FROM con_tb_requester_company WHERE 1 = 1 AND status = 'A' AND refund_request_id = :refundRequestId ORDER BY requester_id
                """;
        param.put("refundRequestId", refundRequestId);

        return queryForList(sql, param, new ConTbRequesterCompanyRowMapper());
    }
    @Override
    protected String getTableName() {
        return "CON_TB_REQUESTER_COMPANY";
    }

    @Override
    protected String getPkColumnName() {
        return "REQUESTER_ID";
    }
}
