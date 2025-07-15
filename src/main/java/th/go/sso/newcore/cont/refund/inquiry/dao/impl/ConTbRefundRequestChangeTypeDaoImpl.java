package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.constant.type.OrderDirection;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundHistoryBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbRefundRequestChangeTypeRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundHistoryRequest;

@Repository
public class ConTbRefundRequestChangeTypeDaoImpl extends DaoBase<Long> {
    public ConTbRefundRequestChangeTypeDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_REQUEST_CHANGE_TYPE";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_REQUEST_CHANGE_TYPE_ID";
    }

    public List<RefundHistoryBean> queryRefundHistory(SearchRefundHistoryRequest request, String startPeriod, String endPeriod, Paginable paginable) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " ctbrrct.refund_request_change_type_id, "+
                " ctbrrct.refund_request_id, "+
                " nvl(t3.RECEIVE_NO, ctbrrct.RECEIVE_NO) AS receive_no, "+
                " ctbrrct.refund_change_type, "+
                " ctbrrct.refund_operation_code, "+
                " coalesce( ctbrrct.update_by, ctbrrct.create_by ) AS create_by, "+
                " coalesce( ctbrrct.update_date, ctbrrct.create_date ) AS create_date ," +
                " t2.ref_refund_request_id, " +
                " ctbrrct.receive_no AS original_receive_no," +
                " case when ctbrrct.source_data is not null then substr(ctbrrct.source_data, -3) end as source_data, ctbrrct.section "+
                " FROM "+
                " con_tb_refund_request_change_type ctbrrct " +
                " left JOIN CON_TB_REFUND_REQUEST t2 ON t2.REFUND_REQUEST_ID = ctbrrct.REFUND_REQUEST_ID " +
                " left JOIN CON_TB_REFUND_REQUEST t3 ON t3.REFUND_REQUEST_ID = t2.REF_REFUND_REQUEST_ID "+
                " WHERE "+
                " 1 = 1 "+
                " AND ctbrrct.refund_request_id IN ( "+
                " SELECT "+
                " ctbrp.refund_request_id "+
                " FROM "+
                " con_tb_refund_request ctbrr "+
                " JOIN con_tb_refund_period ctbrp ON ctbrp.refund_request_id = ctbrr.refund_request_id "+
                " LEFT JOIN con_ms_insurer       cmsi ON cmsi.insurer_id = ctbrr.insurer_id "+
                " LEFT JOIN con_ms_company       cmsc ON cmsc.company_id = ctbrr.company_id " +
                " left JOIN CON_TB_REFUND_REQUEST t21 ON t21.REFUND_REQUEST_ID = ctbrrct.REFUND_REQUEST_ID " +
                " left JOIN CON_TB_REFUND_REQUEST t31 ON t31.REFUND_REQUEST_ID = t2.REF_REFUND_REQUEST_ID "+
                " WHERE "+
                " 1 = 1 " +
                " AND ctbrp.refund_period_year || ctbrp.refund_period_month BETWEEN :startPeriod AND :endPeriod";
        param.put("startPeriod", startPeriod);
        param.put("endPeriod", endPeriod);
        if (!StringUtils.isEmpty(request.getIdCardNo())) {
            sql += " AND cmsi.id_card_no = :idCardNo";
            param.put("idCardNo", request.getIdCardNo());
        }
        if (!NumberUtils.isZeroOrNull(request.getCompanyId())) {
            sql += " AND cmsc.company_id = :companyId" ;
            // " AND ctbrr.ref_refund_request_id IS NULL" for case แยกคืนส่วนลูกจ้าง
            param.put("companyId", request.getCompanyId());
        }
        if (!StringUtils.isEmpty(request.getReceiveNo())) {
            sql += " AND nvl(t31.receive_no, ctbrrct.receive_no) = :receiveNo";
            param.put("receiveNo", request.getReceiveNo());
        }
        sql += " )";

        if(!StringUtils.isEmpty(request.getReceiveNo())){
            sql += " or (ctbrrct.source_data is not null and ctbrrct.receive_no = :receiveNo) ";
            param.put("receiveNo", request.getReceiveNo());
        }

        if (paginable != null) {
            paginable.setOrderBy("create_date");
            paginable.setOrderDirection(OrderDirection.DESC);
        } else {
            sql += " create_date DESC";
        }
        return queryForList(sql, param, paginable, new ConTbRefundRequestChangeTypeRowMapper());
    }
}
