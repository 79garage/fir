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
import th.go.sso.newcore.cont.refund.inquiry.bean.SearchDeleteCancelHistoryBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.SearchRefundDeleteCancelHistRowMapper;

@Repository
public class SearchRefundDeleteCancelHistoryDaoImpl extends DaoBase<Long> {

    public SearchRefundDeleteCancelHistoryDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_REFUND_PERIOD";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_PERIOD_ID";
    }

    private String getSqlColumns() {
        return "SELECT rp.REFUND_PERIOD_ID, rq.REFUND_REQUEST_ID, rq.RECEIVE_NO, rp.REFUND_PERIOD, rp.REQUEST_EMPR_AMOUNT, rp.REQUEST_EMPE_AMOUNT,"
                + " rp.REQUEST_INTEREST_AMOUNT, rp.APPROVE_STATUS, rp.STATUS, rq.STATUS AS request_status, rp.UPDATE_BY, rp.UPDATE_DATE ";
    }

    private List<SearchDeleteCancelHistoryBean> searchDeleteCancelCompany(Long companyId, String receiveNo, String startPeriod, String endPeriod, Paginable paginable) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = getSqlColumns()
                + " , tr.OVER_PAID,"
                + " 0 AS count_company,"
                + " tr.COMPANY_ID,"
                + " NULL AS insurer_id,"
                + " NULL AS id_card_no"
                + " FROM CON_TB_REFUND_PERIOD rp"
                + " JOIN CON_TB_REFUND_REQUEST rq ON (rp.REFUND_REQUEST_ID = rq.REFUND_REQUEST_ID)"
                + " JOIN CON_TR_RECEIVE_COMPANY tr ON (rp.RECEIVE_COMPANY_ID = tr.RECEIVE_COMPANY_ID)"
                + " JOIN CON_MS_COMPANY ms ON (rq.COMPANY_ID = ms.COMPANY_ID)"
                + " WHERE (rp.STATUS = 'D' OR rp.STATUS = 'C')";
        if (!NumberUtils.isZeroOrNull(companyId)) {
            sql += " AND rq.COMPANY_ID = :companyId";
            param.put("companyId", companyId);
        }
        if (!StringUtils.isEmpty(receiveNo)) {
            sql += " AND rq.RECEIVE_NO = :receiveNo";
            param.put("receiveNo", receiveNo);
        }
        if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
            sql += " AND rp.REFUND_PERIOD_YEAR || rp.REFUND_PERIOD_MONTH BETWEEN :startPeriod AND :endPeriod";
            param.put("startPeriod", startPeriod);
            param.put("endPeriod", endPeriod);
        }
        if (paginable != null) {
            paginable.setOrderBy("rq.RECEIVE_NO DESC, rp.REFUND_PERIOD_YEAR DESC, rp.REFUND_PERIOD_MONTH");
            paginable.setOrderDirection(OrderDirection.DESC);
        } else {
            sql += " ORDER BY rq.RECEIVE_NO DESC, rp.REFUND_PERIOD_YEAR DESC, rp.REFUND_PERIOD_MONTH DESC";
        }
        return queryForList(sql, param, paginable, new SearchRefundDeleteCancelHistRowMapper());
    }

    private List<SearchDeleteCancelHistoryBean> searchDeleteCancelInsurer(String idCardNo, String receiveNo, String startPeriod, String endPeriod, Paginable paginable) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = getSqlColumns()
                + " , tr.EMPE_OVERP AS over_paid,"
                + " 0 AS count_company,"
                + " NULL AS company_id,"
                + " tr.INSURER_ID,"
                + " ms.ID_CARD_NO"
                + " FROM CON_TB_REFUND_PERIOD rp"
                + " JOIN CON_TB_REFUND_REQUEST rq ON (rp.REFUND_REQUEST_ID = rq.REFUND_REQUEST_ID)"
                + " JOIN CON_TR_RECEIVE_INSURER tr ON (rp.RECEIVE_INSURER_ID = tr.RECEIVE_INSURER_ID)"
                + " JOIN CON_MS_INSURER ms ON (rq.INSURER_ID = ms.INSURER_ID)"
                + " WHERE (rp.STATUS = 'D' OR rp.STATUS = 'C')";
        if (!StringUtils.isEmpty(idCardNo)) {
            sql += " AND ms.ID_CARD_NO = :idCardNo";
            param.put("idCardNo", idCardNo);
        }
        if (!StringUtils.isEmpty(receiveNo)) {
            sql += " AND rq.RECEIVE_NO = :receiveNo";
            param.put("receiveNo", receiveNo);
        }
        if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
            sql += " AND rp.REFUND_PERIOD_YEAR || rp.REFUND_PERIOD_MONTH BETWEEN :startPeriod AND :endPeriod";
            param.put("startPeriod", startPeriod);
            param.put("endPeriod", endPeriod);
        }
        if (paginable != null) {
            paginable.setOrderBy("rq.RECEIVE_NO DESC, rp.REFUND_PERIOD_YEAR DESC, rp.REFUND_PERIOD_MONTH");
            paginable.setOrderDirection(OrderDirection.DESC);
        } else {
            sql += " ORDER BY rq.RECEIVE_NO DESC, rp.REFUND_PERIOD_YEAR DESC, rp.REFUND_PERIOD_MONTH DESC";
        }
        return queryForList(sql, param, paginable, new SearchRefundDeleteCancelHistRowMapper());
    }

    private List<SearchDeleteCancelHistoryBean> searchDeleteCancelMultiCompany(String idCardNo, String receiveNo, String startPeriod, String endPeriod, Paginable paginable) throws Exception {
        final Map<String, Object> param = new HashMap<>();
        String sql = getSqlColumns()
                + " , NULL AS over_paid,"
                + " COUNT(mscomp.COMPANY_NO) AS count_company,"
                + " NULL AS company_id,"
                + " tr.INSURER_ID,"
                + " msins.ID_CARD_NO"
                + " FROM CON_TB_REFUND_PERIOD rp"
                + " JOIN CON_TB_REFUND_REQUEST rq ON (rp.REFUND_REQUEST_ID = rq.REFUND_REQUEST_ID AND rq.SECTION = '3')"
                + " JOIN CON_TR_RECEIVE_EMPLOYEE tr ON (rp.EMPLOYEE_ID = tr.INSURER_ID AND rp.REFUND_PERIOD = tr.PAY_PERIOD)"
                + " JOIN CON_TR_RECEIVE_COMPANY trcomp ON (tr.RECEIVE_COMPANY_ID = trcomp.RECEIVE_COMPANY_ID)"
                + " JOIN CON_MS_COMPANY mscomp ON (trcomp.COMPANY_ID = mscomp.COMPANY_ID)"
                + " JOIN CON_MS_INSURER msins ON (tr.INSURER_ID = msins.INSURER_ID)"
                + " WHERE (rp.STATUS = 'D' OR rp.STATUS = 'C')";
        if (!StringUtils.isEmpty(idCardNo)) {
            sql += " AND msins.ID_CARD_NO = :idCardNo";
            param.put("idCardNo", idCardNo);
        }
        if (!StringUtils.isEmpty(receiveNo)) {
            sql += " AND rq.RECEIVE_NO = :receiveNo";
            param.put("receiveNo", receiveNo);
        }
        if (!StringUtils.isEmpty(startPeriod) && !StringUtils.isEmpty(endPeriod)) {
            sql += " AND rp.REFUND_PERIOD_YEAR || rp.REFUND_PERIOD_MONTH BETWEEN :startPeriod AND :endPeriod";
            param.put("startPeriod", startPeriod);
            param.put("endPeriod", endPeriod);
        }
        sql += " GROUP BY rp.REFUND_PERIOD_ID, rq.REFUND_REQUEST_ID, rq.RECEIVE_NO, rp.REFUND_PERIOD,"
                + " rp.REFUND_PERIOD_YEAR, rp.REFUND_PERIOD_MONTH, rp.REQUEST_EMPR_AMOUNT, rp.REQUEST_EMPE_AMOUNT,"
                + " rp.REQUEST_INTEREST_AMOUNT, rp.APPROVE_STATUS, rp.STATUS, rq.STATUS, tr.INSURER_ID, msins.ID_CARD_NO, "
                + " rp.UPDATE_BY, rp.UPDATE_DATE";
        if (paginable != null) {
            paginable.setOrderBy("rq.RECEIVE_NO DESC, rp.REFUND_PERIOD_YEAR DESC, rp.REFUND_PERIOD_MONTH");
            paginable.setOrderDirection(OrderDirection.DESC);
        } else {
            sql += " ORDER BY rq.RECEIVE_NO DESC, rp.REFUND_PERIOD_YEAR DESC, rp.REFUND_PERIOD_MONTH DESC";
        }
        return queryForList(sql, param, paginable, new SearchRefundDeleteCancelHistRowMapper());
    }

    public List<SearchDeleteCancelHistoryBean> searchDeleteCancelCompany(Long companyId, String startPeriod, String endPeriod, Paginable paginable) throws Exception {
        return searchDeleteCancelCompany(companyId, null ,startPeriod, endPeriod, paginable);
    }

    public List<SearchDeleteCancelHistoryBean> searchDeleteCancelInsurer(String idCardNo, String startPeriod, String endPeriod, Paginable paginable) throws Exception {
        return searchDeleteCancelInsurer(idCardNo, null ,startPeriod, endPeriod, paginable);
    }

    public List<SearchDeleteCancelHistoryBean> searchDeleteCancelMultiCompany(String idCardNo, String startPeriod, String endPeriod, Paginable paginable) throws Exception {
        return searchDeleteCancelMultiCompany(idCardNo, null ,startPeriod, endPeriod, paginable);
    }

    public List<SearchDeleteCancelHistoryBean> searchDeleteCancelCompanyByReceiveNo(String receiveNo, Paginable paginable) throws Exception {
        return searchDeleteCancelCompany(null, receiveNo, null, null, paginable);
    }

    public List<SearchDeleteCancelHistoryBean> searchDeleteCancelInsurerByReceiveNo(String receiveNo, Paginable paginable) throws Exception {
        return searchDeleteCancelInsurer(null, receiveNo, null, null, paginable);
    }

    public List<SearchDeleteCancelHistoryBean> searchDeleteCancelMultiCompanyByReceiveNo(String receiveNo, Paginable paginable) throws Exception {
        return searchDeleteCancelMultiCompany(null, receiveNo, null, null, paginable);
    }

    public String getSectionByReceiveNo(String receiveNo) throws Exception {
        final String sql = "SELECT SECTION FROM CON_TB_REFUND_REQUEST WHERE RECEIVE_NO = :receiveNo";
        final Map<String, Object> param = new HashMap<>();
        param.put("receiveNo", receiveNo);
        return queryForObject(sql, param, String.class);
    }
}
