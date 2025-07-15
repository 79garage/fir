package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundCompanyByIdRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RefundCompanyRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.SplitEmployeeRefundRowMapper;

@Repository
public class RefundCompanyDaoImpl extends DaoBase<Long> {


    public RefundCompanyDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public RefundCompanyRequestBean searchRefundCompanyInfo(Long companyId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        String sql = " SELECT "+
                " CMSC.SSO_BRANCH_CODE, "+
                " CMSC.SSO_BRANCH_NAME, "+
                " CMSC.SSO_BRANCH_CODE || ' - ' || CMSC.SSO_BRANCH_NAME AS ssoBranchDescription, "+
                " CMSC.COMPANY_NO, "+
                " CMSC.COMPANY_NAME, "+
                " CMSC.COMPANY_NO || ' - ' || CMSC.COMPANY_NAME AS companyDescription, "+
                " CMSC.COMPANY_BRANCH_CODE, "+
                " CMSC.COMPANY_BRANCH_NAME, "+
                " CMSC.COMPANY_BRANCH_CODE || ' - ' || CMSC.COMPANY_BRANCH_NAME AS companyBranchDescription, "+
                " CMSC.BUSINESS_TYPE "+
                " FROM CON_MS_COMPANY CMSC "+
                " WHERE 1 = 1 "+
                " AND CMSC.COMPANY_ID = :companyId "+
                " GROUP BY "+
                " CMSC.COMPANY_ID, "+
                " CMSC.COMPANY_NO, "+
                " CMSC.COMPANY_NAME, "+
                " CMSC.COMPANY_BRANCH_CODE, "+
                " CMSC.COMPANY_BRANCH_NAME, "+
                " CMSC.SSO_BRANCH_CODE, "+
                " CMSC.SSO_BRANCH_NAME, "+
                " CMSC.BUSINESS_TYPE ";
        param.put("companyId", companyId);

        return queryForObject(sql, param, new RefundCompanyRowMapper());

//        try {
//
//            final StringBuilder sql = new StringBuilder();
//
//            sql.append("SELECT mcomp.sso_branch_code,");
//            sql.append(" mcomp.sso_branch_name,");
////            sql.append(" mcomp.sso_branch_code,");
//            sql.append(" mcomp.sso_branch_code || ' - ' || mcomp.sso_branch_name As ssoBranchDescription,");
//            sql.append(" mcomp.company_no,");
//            sql.append(" mcomp.company_name,");
//            sql.append(" mcomp.company_no || ' - ' || mcomp.company_name As companyDescription ,");
//            sql.append(" mcomp.company_branch_code,");
//            sql.append(" mcomp.company_branch_name,");
//            sql.append(" mcomp.company_branch_code || ' - ' || mcomp.company_branch_name As companyBranchDescription,");
//            sql.append(" mcomp.business_type");
//
//            sql.append(" FROM con_ms_company mcomp");
//
//            sql.append(" WHERE 1 = 1");
//
//            Map<String, Object> param = new HashMap<>();
//
//            if (!NumberUtils.isZeroOrNull(companyId)) {
//                sql.append(" AND mcomp.company_id = :companyId");
//                param.put("companyId", companyId);
//            }
//
//            sql.append(" GROUP BY ( mcomp.company_id, mcomp.company_no, mcomp.company_name, mcomp.company_branch_code, ");
//            sql.append(" mcomp.company_branch_name,mcomp.sso_branch_code, mcomp.sso_branch_name, mcomp.business_type ) ");
//            return queryForObject(sql.toString(), param, new RefundCompanyRowMapper());
//
//        } catch (EmptyResultDataAccessException ex) {
//            return null;
//        }
    }

    public RefundCompanyRequestBean searchRefundCompanyChange(Long companyId, Long refundRequestId) throws Exception {
        try {
            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT mcomp.sso_branch_code,");
            sql.append(" mcomp.sso_branch_name,");
            sql.append(" mcomp.sso_branch_code || ' - ' || mcomp.sso_branch_name As ssoBranchDescription,");
            sql.append(" rereq.DEPARTMENT_RESPONSIBLE_Code,");
            sql.append(" rereq.department_receive_code,");
//            sql.append(" rereq.DEPARTMENT_RESPONSIBLE_ID,");
//            sql.append(" rereq.department_receive_id,");
            sql.append(" rereq.receive_no,");
            sql.append(" rereq.receive_date,");
            sql.append(" mcomp.company_no,");
            sql.append(" mcomp.company_name,");
            sql.append(" mcomp.company_no || ' - ' || mcomp.company_name As companyDescription ,");
            sql.append(" mcomp.company_branch_code,");
            sql.append(" mcomp.company_branch_name,");
            sql.append(" mcomp.company_branch_code || ' - ' || mcomp.company_branch_name As companyBranchDescription,");
            sql.append(" mcomp.business_type,");
            sql.append(" rereq.request_type,");
            sql.append(" rereq.request_reason_code,");
            sql.append(" rereq.request_reason_other,");
            sql.append(" rereq.refund_bank_account,");
            sql.append(" rereq.refund_bank_branch_id,");
            sql.append(" rereq.refund_bank_id,");
            sql.append(" rereq.DIST_CODE,");
            sql.append(" rereq.SUBDIST_CODE,");
            sql.append(" rereq.PROVINCE_CODE,");
            sql.append(" msloc.subdist_name,");
            sql.append(" msloc.dist_name,");
            sql.append(" msloc.prov_name,");
            sql.append(" rereq.refund_po_order,");
            sql.append(" rereq.refund_prompt_pay,");
            sql.append(" rereq.refund_remark,");
            sql.append(" rereq.refund_transfer_bank,");
            sql.append(" rereq.refund_transfer_date,");
            sql.append(" rereq.refund_type_place,");
            sql.append(" rereq.notice_date,");
            sql.append(" rereq.notice_no,");
            sql.append(" rereq.notice_status,");
            sql.append(" rereq.approve_date,");
            sql.append(" rereq.approve_no,");
            sql.append(" rereq.approve_status,");
            sql.append(" nvl (refcomp.request_empe_amount,0) As request_empe_amount, ");
            sql.append(" nvl (refcomp.request_empr_amount,0) As request_empr_amount, ");
            sql.append(" nvl (refcomp.request_total_amount,0) As request_total_amount, ");
            sql.append(" nvl (refcomp.request_interest_amount,0) As request_interest_amount, ");
            sql.append(" nvl (refcomp.request_over_paid_amount,0) As request_over_paid_amount, ");
            sql.append(" nvl (refcomp.refund_empe_amount,0) As refund_empe_amount, ");
            sql.append(" nvl (refcomp.refund_empr_amount,0) As refund_empr_amount, ");
            sql.append(" refcomp.refund_interest_amount, ");
            sql.append(" nvl (refcomp.refund_over_paid_amount,0) As refund_over_paid_amount, ");
            sql.append(" nvl (refcomp.refund_total_amount,0) As refund_total_amount, ");
            sql.append(" refcomp.refund_sickness_amount,");
            sql.append(" refcomp.refund_retirement_amount, ");
            sql.append(" refcomp.refund_unemployment_amount, ");
            sql.append(" refcomp.progress_status,");
            sql.append(" rereq.REQUEST_CHANNEL,");
            sql.append(" rereq.DISAPPROVE_REASON,");
            sql.append(" rereq.REFUND_REQUEST_ID,");
            sql.append(" rereq.CREATE_BY,");
            sql.append(" rereq.CREATE_DATE,");
            sql.append(" rereq.UPDATE_BY,");
            sql.append(" rereq.UPDATE_DATE,");
            sql.append(" rereq.reply_date,");
            sql.append(" rereq.notice_ar_date,");
            sql.append(" rereq.announce_no,");
            sql.append(" rereq.announce_status,");
            sql.append(" rereq.announce_date,");
            sql.append(" rereq.announce_ar_date,");
            sql.append(" rereq.announce_ar_create_by,");
            sql.append(" rereq.announce_ar_create_date,");
            sql.append(" rereq.record_approve_by,");
            sql.append(" rereq.record_approve_date,");
            sql.append(" rereq.SPLIT_REFUND,");
            sql.append(" rereq.REVERSE_STATUS,");
            sql.append(" rereq.reverse_remark,");
            sql.append(" rereq.SECTION,");
//            sql.append(" reqcom.order_no,");
//            sql.append(" reqcom.ORDER_STATUS,");
//            sql.append(" reqcom.order_remark");
            sql.append(" NULL AS order_no,");
            sql.append(" NULL AS ORDER_STATUS,");
            sql.append(" NULL AS order_remark,");
            sql.append(" rereq.ELIGIBLE_FLAG,");
            sql.append(" rereq.refund_po_order_name,");
            sql.append(" rereq.period_start,");
            sql.append(" rereq.period_end,");
            sql.append(" rereq.request_amount");

            sql.append(" FROM con_ms_company mcomp");
            sql.append(" LEFT JOIN con_tb_refund_request rereq ON ( rereq.company_id = mcomp.company_id )");
            sql.append(" LEFT JOIN con_tr_refund_company refcomp ON ( refcomp.refund_request_id = rereq.refund_request_id )");
//            sql.append(" LEFT OUTER JOIN con_tb_requester_company reqcom ON ( reqcom.refund_request_id = rereq.refund_request_id )");
            sql.append(" LEFT JOIN con_ms_location msloc ON (rereq.dist_code = msloc.dist_code AND rereq.subdist_code = msloc.subdist_code AND rereq.province_code = msloc.prov_code)");

            sql.append(" WHERE 1 = 1");

            Map<String, Object> param = new HashMap<>();

//            if (!NumberUtils.isZeroOrNull(companyId)) {
//                sql.append(" AND rereq.company_id = :companyId ");
//                param.put("companyId", companyId);
//            }

            if (!NumberUtils.isZeroOrNull(refundRequestId)) {
                sql.append(" AND refcomp.refund_request_id = :refundRequestId ");
                param.put("refundRequestId", refundRequestId);
            }

            //Group By
//            sql.append(" GROUP BY mcomp.sso_branch_code,mcomp.sso_branch_name,rereq.DEPARTMENT_RESPONSIBLE_CODE,rereq.department_receive_code,");
////            sql.append(" GROUP BY (mcomp.sso_branch_code,mcomp.sso_branch_name,rereq.DEPARTMENT_RESPONSIBLE_ID,rereq.department_receive_id,");
//            sql.append(" rereq.receive_no,rereq.receive_date,mcomp.company_no,mcomp.company_name,mcomp.company_branch_code,");
//            sql.append(" mcomp.company_branch_name,rereq.request_type,rereq.request_reason_code,rereq.request_reason_other,");
//            sql.append(" rereq.refund_bank_account,rereq.refund_bank_branch_id,rereq.refund_bank_id,rereq.refund_po_order,");
//            sql.append(" rereq.refund_prompt_pay,rereq.refund_remark,rereq.refund_transfer_bank,rereq.refund_transfer_date,");
//            sql.append(" rereq.refund_type_place,rereq.notice_date,rereq.notice_no,rereq.notice_status,rereq.approve_date,");
//            sql.append(" rereq.approve_no,rereq.approve_status,refcomp.progress_status,rereq.request_channel,rereq.DISAPPROVE_REASON,");
//            sql.append(" rereq.REFUND_REQUEST_ID,rereq.CREATE_BY,rereq.CREATE_DATE,rereq.UPDATE_BY,rereq.UPDATE_DATE,");
//            sql.append(" rereq.reply_date,rereq.notice_ar_date,rereq.announce_no,rereq.announce_date,rereq.announce_ar_date,rereq.announce_ar_create_by,rereq.announce_ar_create_date,");
//            sql.append(" rereq.record_approve_by,rereq.record_approve_date,rereq.SPLIT_REFUND, rereq.SECTION,refcomp.request_empe_amount,rereq.REVERSE_STATUS,");
//            sql.append(" refcomp.request_empr_amount, refcomp.request_total_amount,refcomp.request_interest_amount,refcomp.request_over_paid_amount,");
//            sql.append(" refcomp.refund_sickness_amount, refcomp.refund_retirement_amount,  refcomp.refund_unemployment_amount, rereq.reverse_remark, ");
//            sql.append(" rereq.DIST_CODE,rereq.SUBDIST_CODE,rereq.PROVINCE_CODE, msloc.subdist_name, msloc.dist_name, msloc.prov_name,");
//            sql.append(" refcomp.refund_empe_amount,refcomp.refund_empr_amount,refcomp.refund_interest_amount,refcomp.refund_over_paid_amount,refcomp.refund_total_amount, ");
////            sql.append(" reqcom.ORDER_STATUS,reqcom.order_remark,reqcom.order_no, ");
//            sql.append(" mcomp.business_type, rereq.ELIGIBLE_FLAG, rereq.refund_po_order_name,rereq.period_start,rereq.period_end,rereq.request_amount");

            return queryForObject(sql.toString(), param, new RefundCompanyByIdRowMapper());

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public SplitEmployeeRefundCompanyRequestBean getSplitEmployeeRefund(Long companyId, Long refundRequestId) throws Exception {

        Map<String, Object> param = new HashMap<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT mcomp.sso_branch_code,");
        sql.append(" mcomp.sso_branch_name,");
        sql.append(" mcomp.sso_branch_code || ' - ' || mcomp.sso_branch_name As ssoBranchDescription,");
        sql.append(" rereq.department_receive_code,");
//            sql.append(" rereq.department_receive_id,");
        sql.append(" rereq.receive_no,");
        sql.append(" rereq.receive_date,");
        sql.append(" mcomp.COMPANY_ID,");
        sql.append(" mcomp.company_no,");
        sql.append(" mcomp.company_name,");
        sql.append(" mcomp.company_no || ' - ' || mcomp.company_name As companyDescription ,");
        sql.append(" mcomp.company_branch_code,");
        sql.append(" mcomp.company_branch_name,");
        sql.append(" mcomp.company_branch_code || ' - ' || mcomp.company_branch_name As companyBranchDescription,");
        sql.append(" rereq.request_type,");
        sql.append(" rereq.request_reason_code,");
        sql.append(" rereq.request_reason_other,");
        sql.append(" rereq.refund_bank_account,");
        sql.append(" rereq.refund_bank_branch_id,");
        sql.append(" rereq.refund_bank_id,");
        sql.append(" rereq.refund_po_order,");
        sql.append(" rereq.refund_prompt_pay,");
        sql.append(" rereq.refund_remark,");
        sql.append(" rereq.refund_transfer_bank,");
        sql.append(" rereq.refund_transfer_date,");
        sql.append(" rereq.refund_type_place,");
        sql.append(" rereq.notice_date,");
        sql.append(" rereq.notice_no,");
        sql.append(" rereq.notice_status,");
        sql.append(" rereq.approve_date,");
        sql.append(" rereq.approve_no,");
        sql.append(" rereq.approve_status,");
        sql.append(" refcomp.progress_status,");
        sql.append(" nvl (refcomp.request_empe_amount,0) As request_empe_amount, ");
        sql.append(" nvl (refcomp.request_empr_amount,0) As request_empr_amount, ");
        sql.append(" nvl (refcomp.request_total_amount,0) As request_total_amount, ");
        sql.append(" nvl (refcomp.request_interest_amount,0) As request_interest_amount, ");
        sql.append(" nvl (refcomp.request_over_paid_amount,0) As request_over_paid_amount, ");
        sql.append(" nvl (refcomp.refund_empe_amount,0) As refund_empe_amount, ");
        sql.append(" nvl (refcomp.refund_empr_amount,0) As refund_empr_amount, ");
        sql.append(" refcomp.refund_interest_amount,");
        sql.append(" nvl (refcomp.refund_over_paid_amount,0) As refund_over_paid_amount, ");
        sql.append(" nvl (refcomp.refund_total_amount,0) As refund_total_amount, ");
        sql.append(" refcomp.refund_sickness_amount,");
        sql.append(" refcomp.refund_retirement_amount,");
        sql.append(" refcomp.refund_unemployment_amount,");
        sql.append(" rereq.REQUEST_CHANNEL,");
        sql.append(" rereq.DISAPPROVE_REASON,");
        sql.append(" rereq.REFUND_REQUEST_ID,");
        sql.append(" rereq.DIST_CODE,");
        sql.append(" rereq.SUBDIST_CODE,");
        sql.append(" rereq.PROVINCE_CODE,");
        sql.append(" rereq.CREATE_BY,");
        sql.append(" rereq.CREATE_DATE,");
        sql.append(" rereq.UPDATE_BY,");
        sql.append(" rereq.UPDATE_DATE,");
        sql.append(" rereq.reply_date,");
        sql.append(" rereq.notice_ar_date,");
        sql.append(" rereq.announce_no,");
        sql.append(" rereq.announce_date,");
        sql.append(" rereq.announce_ar_date,");
        sql.append(" rereq.record_approve_by,");
        sql.append(" rereq.record_approve_date,");
        sql.append(" rereq.SPLIT_REFUND,");
        sql.append(" rereq.SECTION");

        sql.append(" FROM con_ms_company mcomp");
        sql.append(" LEFT JOIN con_tb_refund_request rereq ON ( rereq.company_id = mcomp.company_id )");
        sql.append(" LEFT JOIN con_tr_refund_company refcomp ON ( refcomp.refund_request_id = rereq.refund_request_id )");
        sql.append(" WHERE 1 = 1");
        sql.append(" AND rereq.company_id = :companyId ");
        sql.append(" AND rereq.refund_request_id = :refundRequestId ");

        sql.append(" GROUP BY (mcomp.sso_branch_code,mcomp.sso_branch_name,rereq.department_receive_code,rereq.receive_no,rereq.receive_date,mcomp.company_no,");
//            sql.append(" GROUP BY (mcomp.sso_branch_code,mcomp.sso_branch_name,rereq.department_receive_id,rereq.receive_no,rereq.receive_date,mcomp.company_no,");
        sql.append(" mcomp.COMPANY_ID,mcomp.company_name,mcomp.company_branch_code,mcomp.company_branch_name,rereq.request_type,rereq.request_reason_code,rereq.request_reason_other,");
        sql.append(" rereq.refund_bank_account,rereq.refund_bank_branch_id,rereq.refund_bank_id,rereq.refund_po_order,rereq.refund_prompt_pay,rereq.refund_remark,");
        sql.append(" rereq.refund_transfer_bank,rereq.refund_transfer_date,rereq.refund_type_place,rereq.notice_date,rereq.notice_no,rereq.notice_status,rereq.approve_date,");
        sql.append(" refcomp.request_empe_amount, refcomp.request_empr_amount, refcomp.request_total_amount, refcomp.request_interest_amount, refcomp.request_over_paid_amount, ");
        sql.append(" refcomp.refund_empe_amount, refcomp.refund_empr_amount, refcomp.refund_interest_amount, refcomp.refund_over_paid_amount, refcomp.refund_total_amount, ");
        sql.append(" rereq.approve_no,rereq.approve_status,refcomp.progress_status,rereq.request_channel,rereq.DISAPPROVE_REASON,rereq.REFUND_REQUEST_ID,rereq.CREATE_BY,rereq.CREATE_DATE,rereq.UPDATE_BY,rereq.UPDATE_DATE,");
        sql.append(" refcomp.refund_sickness_amount, refcomp.refund_retirement_amount,  refcomp.refund_unemployment_amount,rereq.DIST_CODE, rereq.SUBDIST_CODE,rereq.PROVINCE_CODE,");
        sql.append(" rereq.reply_date,rereq.notice_ar_date,rereq.announce_no,rereq.announce_date,rereq.announce_ar_date,rereq.record_approve_by,rereq.record_approve_date,rereq.SPLIT_REFUND,rereq.SECTION)");

        param.put("companyId", companyId);
        param.put("refundRequestId", refundRequestId);

        return queryForObject(sql.toString(), param, new SplitEmployeeRefundRowMapper());
    }

    public String getProgressStatusByRefundRequestId(Long refundRequestId) throws Exception {
        final String sql = "SELECT progress_status FROM con_tr_refund_company WHERE refund_request_id = :refundRequestId";
        final Map<String, Object> param = new HashMap<>();
        param.put("refundRequestId", refundRequestId);
        return queryForObject(sql, param, String.class);
    }

    @Override
    protected String getTableName() {
        return "CON_TR_REFUND_COMPANY";
    }

    @Override
    protected String getPkColumnName() {
        return "REFUND_COMPANY_ID";
    }
}
