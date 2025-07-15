package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.CheckPaybackBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbPaybackRequestInfoBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OrderBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.PaybackRefundPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterPaybackBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.CheckPaybackRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbPaybackCompanyRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbPaybackEmployeeRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbPaybackInsurerRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbPaybackRequestInfoRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbPaybackRequestRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.OrderRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.PaybackRefundPeriodRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.RequesterPaybackRowMapper;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchConTbPaybackRequestRequest;

@Repository
public class ConTbPaybackRequestDaoImpl extends DaoBase<ConTbPaybackRequestBean> {

	public ConTbPaybackRequestDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	public List<ConTbPaybackRequestInfoBean> getPaybackRequestByCondition(SearchConTbPaybackRequestRequest request) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> param = new HashMap<>();
		if ("0".equals(request.getSection())) {
			if ("0".equals(request.getCategory())) {
				sql.append("SELECT ctbrr.receive_no,");
				sql.append(" ctbpr.payback_request_no,");
				sql.append(" NULL AS id_card_no,");
				sql.append(" NULL AS full_name,");
				sql.append(" ctbpr.payback_request_date,");
				sql.append(" ctbpr.payback_total_amount,");
				sql.append(" ctbpr.status,");
				sql.append(" ctbpr.payback_approve_status,");
				sql.append(" cmsc.company_no,");
				sql.append(" cmsc.company_name,");
				sql.append(" cmsc.company_branch_code,");
				sql.append(" cmsc.company_branch_name,");
				sql.append(" ctbpr.payback_request_id,");
				sql.append(" ctbpr.payback_status");
				sql.append(" FROM con_tb_payback_request ctbpr");
				sql.append(" LEFT JOIN con_tb_refund_request  ctbrr ON ctbrr.refund_request_id = ctbpr.refund_request_id");
				sql.append(" JOIN con_tb_requester_company      ctbrc ON ctbrc.requester_id = ctbpr.requester_company_id");
				sql.append(" LEFT JOIN con_ms_company         cmsc ON cmsc.company_id = ctbrr.company_id");
				sql.append(" WHERE 1 = 1");
				sql.append(" AND ctbrr.section = '0' ");

				if (!StringUtils.isEmpty(request.getPaybackRequestNo())) {
					sql.append(" AND ctbpr.payback_request_no = :getPaybackRequestNo ");
					param.put("getPaybackRequestNo", request.getPaybackRequestNo());
				} else {
//                    if (!StringUtils.isEmpty(request.getPaybackApproveStatus())) {
//                        switch (request.getPaybackApproveStatus()) {
//                            case "N" -> sql.append(" AND (ctbpr.payback_approve_status = 'N' OR ctbpr.payback_approve_status IS NULL)");
//                            case "W" -> sql.append(" AND ctbpr.payback_approve_status = 'W' ");
//                            case "0" -> sql.append(" AND ctbpr.payback_approve_status = '0' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
//                            case "1" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
//                            case "S" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'W' ");
//                            case "F" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'F' ");
//                            case "P" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'P' ");
//                            case "T" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'T' ");
//                            case "C" -> sql.append(" AND ctbpr.payback_approve_status = 'C' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' OR ctbpr.payback_status = 'F' ) ");
//                        }
//                    }
					if (!NumberUtils.isZeroOrNull(request.getCompanyId())) {
						sql.append(" AND cmsc.company_id = :companyId ");
						param.put("companyId", request.getCompanyId());
					}
				}
				////////////////////////////
			} else if ("1".equals(request.getCategory())) {
				sql.append("SELECT ctbrr.receive_no,");
				sql.append(" ctbpr.payback_request_no,");
				sql.append(" ctbr33.id_card_no AS id_card_no,");
				sql.append(" ctbr33.full_name AS full_name,");
				sql.append(" ctbpr.payback_request_date,");
				sql.append(" ctbpr.payback_total_amount,");
				sql.append(" ctbpr.status,");
				sql.append(" ctbpr.payback_approve_status,");
				sql.append(" NULL AS company_no,");
				sql.append(" NULL AS company_name,");
				sql.append(" NULL AS company_branch_code,");
				sql.append(" NULL AS company_branch_name,");
				sql.append(" ctbpr.payback_request_id,");
				sql.append(" ctbpr.payback_status");
				sql.append(" FROM con_tb_payback_request ctbpr");
				sql.append(" LEFT JOIN con_tb_refund_request  ctbrr ON ctbrr.refund_request_id = ctbpr.refund_request_id");
				sql.append(" JOIN con_tb_requester33            ctbr33 ON ctbr33.requester_id = ctbpr.requester33_id");
				sql.append(" LEFT JOIN con_ms_insurer cmsi ON cmsi.insurer_id = ctbrr.insurer_id ");
				sql.append(" WHERE 1 = 1");
				sql.append(" AND (ctbrr.section = '0' OR ctbrr.section = '3') ");

				if (!StringUtils.isEmpty(request.getPaybackRequestNo())) {
					sql.append(" AND ctbpr.payback_request_no = :getPaybackRequestNo ");
					param.put("getPaybackRequestNo", request.getPaybackRequestNo());
				} else {
//                    if (!StringUtils.isEmpty(request.getPaybackApproveStatus())) {
//                        switch (request.getPaybackApproveStatus()) {
//                            case "N" -> sql.append(" (AND ctbpr.payback_approve_status = 'N' OR ctbpr.payback_approve_status IS NULL)");
//                            case "W" -> sql.append(" AND ctbpr.payback_approve_status = 'W' ");
//                            case "0" -> sql.append(" AND ctbpr.payback_approve_status = '0' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
//                            case "1" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
//                            case "S" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'W' ");
//                            case "F" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'F' ");
//                            case "P" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'P' ");
//                            case "T" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'T' ");
//                            case "C" -> sql.append(" AND ctbpr.payback_approve_status = 'C' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' OR ctbpr.payback_status = 'F' ) ");
//                        }
//                    }
					if (!StringUtils.isEmpty(request.getIdCardNo())) {
						sql.append(" AND cmsi.id_card_no = :idCardNo ");
						param.put("idCardNo", request.getIdCardNo());
					}
				}
			}
			///////////////////////////////
		} else if ("1".equals(request.getSection())) {
			sql.append("SELECT ctbrr.receive_no,");
			sql.append(" ctbpr.payback_request_no,");
			sql.append(" ctbr39.id_card_no AS id_card_no,");
			sql.append(" ctbr39.full_name AS full_name,");
			sql.append(" ctbpr.payback_request_date,");
			sql.append(" ctbpr.payback_total_amount,");
			sql.append(" ctbpr.status,");
			sql.append(" ctbpr.payback_approve_status,");
			sql.append(" NULL AS company_no,");
			sql.append(" NULL AS company_name,");
			sql.append(" NULL AS company_branch_code,");
			sql.append(" NULL AS company_branch_name,");
			sql.append(" ctbpr.payback_request_id,");
			sql.append(" ctbpr.payback_status");
			sql.append(" FROM con_tb_payback_request ctbpr");
			sql.append(" LEFT JOIN con_tb_refund_request  ctbrr ON ctbrr.refund_request_id = ctbpr.refund_request_id");
			sql.append(" JOIN con_tb_requester39            ctbr39 ON ctbr39.requester_id = ctbpr.requester39_id");
			sql.append(" LEFT JOIN con_ms_insurer cmsi ON cmsi.insurer_id = ctbrr.insurer_id ");
			sql.append(" WHERE 1 = 1");
			sql.append(" AND ctbrr.section = '1' ");

			if (!StringUtils.isEmpty(request.getPaybackRequestNo())) {
				sql.append(" AND ctbpr.payback_request_no = :getPaybackRequestNo ");
				param.put("getPaybackRequestNo", request.getPaybackRequestNo());
			} else {
//                if (!StringUtils.isEmpty(request.getPaybackApproveStatus())) {
//                    switch (request.getPaybackApproveStatus()) {
//                        case "N" -> sql.append(" AND (ctbpr.payback_approve_status = 'N' OR ctbpr.payback_approve_status IS NULL)");
//                        case "W" -> sql.append(" AND ctbpr.payback_approve_status = 'W' ");
//                        case "0" -> sql.append(" AND ctbpr.payback_approve_status = '0' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
//                        case "1" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
//                        case "S" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'W' ");
//                        case "F" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'F' ");
//                        case "P" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'P' ");
//                        case "T" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'T' ");
//                        case "C" -> sql.append(" AND ctbpr.payback_approve_status = 'C' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' OR ctbpr.payback_status = 'F' ) ");
//                    }
//                }
				if (!StringUtils.isEmpty(request.getIdCardNo())) {
					sql.append(" AND cmsi.id_card_no = :idCardNo ");
					param.put("idCardNo", request.getIdCardNo());
				}
			}
		}
		if (!StringUtils.isEmpty(request.getPaybackApproveStatus()) && StringUtils.isEmpty(request.getPaybackRequestNo())) {
			switch (request.getPaybackApproveStatus()) {
			case "N" -> sql.append(" AND (ctbpr.payback_approve_status = 'N' OR ctbpr.payback_approve_status IS NULL)");
			case "W" -> sql.append(" AND ctbpr.payback_approve_status = 'W' ");
			case "0" -> sql.append(" AND ctbpr.payback_approve_status = '0' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
			case "1" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
			case "S" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'W' ");
			case "F" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'F' ");
			case "P" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'P' ");
			case "T" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'T' ");
			case "C" -> sql.append(" AND ctbpr.payback_approve_status = 'C' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' OR ctbpr.payback_status = 'F' ) ");
			}
		}

//        sql.append("SELECT ctbrr.receive_no,");
//        sql.append(" ctbpr.payback_request_no,");
//        sql.append(" nvl(ctbr33.id_card_no, ctbr39.id_card_no) AS id_card_no,");
//        sql.append(" nvl(ctbr33.full_name, ctbr39.full_name) AS full_name,");
//        sql.append(" ctbpr.payback_request_date,");
//        sql.append(" ctbpr.payback_total_amount,");
//        sql.append(" ctbpr.status,");
//        sql.append(" ctbpr.payback_approve_status,");
//        sql.append(" cmsc.company_no,");
//        sql.append(" cmsc.company_name,");
//        sql.append(" cmsc.company_branch_code,");
//        sql.append(" cmsc.company_branch_name,");
//        sql.append(" ctbpr.payback_request_id");
//        sql.append(" FROM con_tb_payback_request ctbpr");
//        sql.append(" LEFT JOIN con_tb_refund_request  ctbrr ON ctbrr.refund_request_id = ctbpr.refund_request_id");
//        sql.append(" LEFT JOIN con_tb_requester_company      ctbrc ON ctbrc.requester_id = ctbpr.requester_company_id");
//        sql.append(" LEFT JOIN con_tb_requester33            ctbr33 ON ctbr33.requester_id = ctbpr.requester33_id");
//        sql.append(" LEFT JOIN con_tb_requester39            ctbr39 ON ctbr39.requester_id = ctbpr.requester39_id");
//        sql.append(" LEFT JOIN con_ms_company         cmsc ON cmsc.company_id = ctbrr.company_id");
//        sql.append(" LEFT JOIN con_ms_insurer cmsi ON cmsi.insurer_id = ctbrr.insurer_id ");
//        sql.append(" WHERE 1 = 1");
//
//        if(!StringUtils.isEmpty(request.getPaybackRequestNo())){
//            sql.append(" AND ctbpr.payback_request_no = :getPaybackRequestNo ");
//            param.put("getPaybackRequestNo", request.getPaybackRequestNo());
//        } else {
//            if(!StringUtils.isEmpty(request.getStatus())){
//                sql.append(" AND ctbpr.status = :status ");
//                param.put("status", request.getStatus());
//            }
//            if (!StringUtils.isEmpty(request.getSection())){
//                if ("0".equals(request.getSection()) || "3".equals(request.getSection())){
//                    sql.append(" AND (ctbrr.section = '0' OR ctbrr.section = '3') ");
//                    param.put("section", request.getSection());
//                } else if ("1".equals(request.getSection())) {
//                    sql.append(" AND ctbrr.section = '1' ");
//                    param.put("section", request.getSection());
//                }
//            }
//
//            if (!StringUtils.isEmpty(request.getPaybackApproveStatus())) {
//                switch (request.getPaybackApproveStatus()) {
//                    case "N" -> sql.append(" AND ctbpr.payback_approve_status = 'N' OR ctbpr.payback_approve_status IS NULL");
//                    case "W" -> sql.append(" AND ctbpr.payback_approve_status = 'W' ");
//                    case "0" -> sql.append(" AND ctbpr.payback_approve_status = '0' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
//                    case "1" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' ) ");
//                    case "S" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'W' ");
//                    case "F" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'F' ");
//                    case "P" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'P' ");
//                    case "T" -> sql.append(" AND ctbpr.payback_approve_status = '1' AND ctbpr.payback_status = 'T' ");
//                    case "C" -> sql.append(" AND ctbpr.payback_approve_status = 'C' AND ( ctbpr.payback_status IS NULL OR ctbpr.payback_status = 'C' OR ctbpr.payback_status = 'F' ) ");
//                }
//            }
//
////            if (!StringUtils.isEmpty(request.getPaybackApproveStatus()) && request.getPaybackApproveStatus().equals("N")){
////                sql.append(" AND (ctbpr.payback_approve_status is null OR ctbpr.payback_approve_status = 'N') ");
////            }else if(!StringUtils.isEmpty(request.getPaybackApproveStatus())){
////                sql.append(" AND ctbpr.payback_approve_status = :paybackApproveStatus ");
////                param.put("paybackApproveStatus", request.getPaybackApproveStatus());
////            }
//
//            if(!StringUtils.isEmpty(request.getIdCardNo())){
////            sql.append(" and nvl(ctbr33.id_card_no, ctbr33.id_card_no) = :idCardNo ");
//                sql.append(" AND cmsi.id_card_no = :idCardNo ");
//                param.put("idCardNo", request.getIdCardNo());
//            }
//
//            if(request.getCompanyId() != null){
////            sql.append(" and ctbrc.company_id = :companyId ");
//                sql.append(" AND cmsc.company_id = :companyId ");
//                param.put("companyId", request.getCompanyId());
//            }
//        }
///////////////////////////////////////////////////
//        sql.append(" select t2.receive_no, t1.payback_request_no, t4.id_card_no, t4.full_name, t1.payback_request_date");
//        sql.append(" , t1.payback_total_amount , t1.status, t1.payback_approve_status, t3.company_no, t3.company_name, t3.company_branch_code, t3.company_branch_name");
//        sql.append(" , t1.payback_request_id");
//        sql.append(" from con_tb_payback_request t1");
//        sql.append(" left join con_tb_refund_request t2 on t2.refund_request_id = t1.refund_request_id ");
//        sql.append(" left join con_ms_company t3 on t3.company_id  = t1.company_id ");
//        sql.append(" left join con_ms_insurer t4 on t4.insurer_id = t1.insurer_id");
//        sql.append(" where 1=1");

//        Map<String, Object> param = new HashMap<>();
//
//        if(!StringUtils.isEmpty(request.getStatus())){
//            sql.append(" and t1.status = :status ");
//            param.put("status", request.getStatus());
//        }
//
//        if (!StringUtils.isEmpty(request.getPaybackApproveStatus()) && request.getPaybackApproveStatus().equals("N")){
//            sql.append(" and (t1.payback_approve_status is null OR t1.payback_approve_status = 'N') ");
//        }else if(!StringUtils.isEmpty(request.getPaybackApproveStatus())){
//            sql.append(" and t1.payback_approve_status = :paybackApproveStatus ");
//            param.put("paybackApproveStatus", request.getPaybackApproveStatus());
//        }
//
//        if(!StringUtils.isEmpty(request.getPaybackRequestNo())){
//            sql.append(" and t1.payback_request_no = :getPaybackRequestNo ");
//            param.put("getPaybackRequestNo", request.getPaybackRequestNo());
//        }
//
//        if(!StringUtils.isEmpty(request.getIdCardNo())){
//            sql.append(" and t4.id_card_no = :idCardNo ");
//            param.put("idCardNo", request.getIdCardNo());
//        }
//
//        if(request.getCompanyId() != null){
//            sql.append(" and t1.company_id = :companyId ");
//            param.put("companyId", request.getCompanyId());
//        }

		return queryForList(sql.toString(), param, request.getPaginable(), new ConTbPaybackRequestInfoRowMapper());
	}

	public ConTbPaybackRequestBean getConTbPaybackRequestByIdOrReceiveNo(String receiveNo, String separateSection) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("receiveNo", receiveNo);
		String sql = " SELECT " + " T0.* " + " FROM " + " ( " + " SELECT " + " T1.REFUND_REQUEST_ID, " + " T1.SECTION, " + " NULL AS INSURER_ID, " + " NULL AS COMPANY_ID, " + " T2.PROGRESS_STATUS, " + " T1.RECEIVE_NO, " + " T3.FULL_NAME, "
				+ " T3.ID_CARD_NO, " + " T1.REQUEST_REASON_CODE, " + " T1.REQUEST_REASON_OTHER, " + " T1.APPROVE_DATE, " + " T4.COMPANY_NO, " + " T4.COMPANY_NAME, " + " T4.COMPANY_BRANCH_CODE, " + " T4.COMPANY_BRANCH_NAME, " + " T1.REQUEST_TYPE, "
				+ " T1.REQUEST_CHANNEL, " + " T5.DEP_CODE, " + " T1.REFUND_TOTAL_AMOUNT, " +
//                " NVL( T3.INSURER_STATUS, T4.COMPANY_STATUS ) AS ALL_STATUS, "+
				" T1.APPROVE_STATUS, " + " T1.SPLIT_REFUND, " + " NULL                   AS RECEIPT_NO, " + " NULL                   AS PAYMENT_DATE, " + " NULL                   AS TOTAL_AMOUNT, " + " NULL                   AS PAYBACK_STATUS, "
				+ " NULL                   AS ORDER_NO, " + " NULL                   AS PAYBACK_REQUEST_ID, " + " NULL                   AS PAYBACK_TOTAL_AMOUNT, " + " NULL                   AS PAYBACK_REQUEST_NO, "
				+ " NULL                   AS PAYBACK_REQUEST_DATE, " + " NULL                   AS PAYBACK_REQUEST_TYPE, " + " NULL                   AS PAYBACK_APPROVE_STATUS, " + " NULL                   AS PAYBACK_APPROVE_DATE, "
				+ " NULL                   AS PAYBACK_RECEIVE_STATUS, " + " NULL                   AS PAYBACK_RECEIVE_DATE, " + " NULL                   AS REVERSE_STATUS, " + " NULL                   AS PAYBACK_CAUSE, "
				+ " NULL                   AS REMARK, " + " NULL                   AS MANAGE_BY, " + " NULL                   AS CREATE_BY, " + " NULL                   AS CREATE_DATE, " + " NULL                   AS UPDATE_BY, "
				+ " NULL                   AS UPDATE_DATE, " + " NULL                   AS STATUS, ";
		if (!StringUtils.isEmpty(separateSection) && Objects.equals("1", separateSection)) {
			sql += " T3.INSURER_STATUS AS ALL_STATUS" + " FROM " + " CON_TB_REFUND_REQUEST T1 " + " LEFT JOIN CON_TR_REFUND_INSURER T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID "
					+ " LEFT JOIN CON_MS_INSURER        T3 ON T3.INSURER_ID = T1.INSURER_ID " + " LEFT JOIN CON_MS_COMPANY        T4 ON T4.COMPANY_ID = T1.COMPANY_ID " + " LEFT JOIN CON_MS_DEPARTMENT     T5 ON T5.DEP_CODE = T1.DEPARTMENT_RECEIVE_CODE "
					+ " ) T0 " + " WHERE " + " 1 = 1 " + " AND NOT EXISTS ( " + " SELECT CTBPR.REFUND_REQUEST_ID FROM CON_TB_PAYBACK_REQUEST CTBPR "
					+ " WHERE CTBPR.REFUND_REQUEST_ID = T0.REFUND_REQUEST_ID AND CTBPR.PAYBACK_APPROVE_STATUS NOT IN ( '0', 'C' ) " + " ) " + " AND EXISTS ( " + " SELECT 1 FROM CON_TB_REFUND_REQUEST CTRR "
					+ " LEFT JOIN CON_TB_REQUESTER39    CTR39 ON CTR39.REFUND_REQUEST_ID = CTRR.REFUND_REQUEST_ID " + " WHERE 1 = 1 AND CTR39.ORDER_STATUS = 'P' " + " ) ";
		} else if (!StringUtils.isEmpty(separateSection) && Objects.equals("3", separateSection)) {
			sql += " T4.COMPANY_STATUS AS ALL_STATUS" + " FROM " + " CON_TB_REFUND_REQUEST T1 " + " LEFT JOIN CON_TR_REFUND_EMPLOYEE T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID "
					+ " LEFT JOIN CON_MS_INSURER        T3 ON T3.INSURER_ID = T1.INSURER_ID " + " LEFT JOIN CON_MS_COMPANY        T4 ON T4.COMPANY_ID = T1.COMPANY_ID " + " LEFT JOIN CON_MS_DEPARTMENT     T5 ON T5.DEP_CODE = T1.DEPARTMENT_RECEIVE_CODE "
					+ " ) T0 " + " WHERE " + " 1 = 1 " + " AND NOT EXISTS ( " + " SELECT CTBPR.REFUND_REQUEST_ID FROM CON_TB_PAYBACK_REQUEST CTBPR "
					+ " WHERE CTBPR.REFUND_REQUEST_ID = T0.REFUND_REQUEST_ID AND CTBPR.PAYBACK_APPROVE_STATUS NOT IN ( '0', 'C' ) " + " ) " + " AND EXISTS ( " + " SELECT 1 FROM CON_TB_REFUND_REQUEST CTRR "
					+ " LEFT JOIN CON_TB_REQUESTER33    CTR33 ON CTR33.REFUND_REQUEST_ID = CTRR.REFUND_REQUEST_ID " + " WHERE 1 = 1 AND CTR33.ORDER_STATUS = 'P' " + " ) ";
		} else if (!StringUtils.isEmpty(separateSection) && Objects.equals("0", separateSection)) {
			sql += " T3.INSURER_STATUS AS ALL_STATUS" + " FROM " + " CON_TB_REFUND_REQUEST T1 " + " LEFT JOIN CON_TR_REFUND_COMPANY T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID "
					+ " LEFT JOIN CON_MS_INSURER        T3 ON T3.INSURER_ID = T1.INSURER_ID " + " LEFT JOIN CON_MS_COMPANY        T4 ON T4.COMPANY_ID = T1.COMPANY_ID " + " LEFT JOIN CON_MS_DEPARTMENT     T5 ON T5.DEP_CODE = T1.DEPARTMENT_RECEIVE_CODE "
					+ " ) T0 " + " WHERE " + " 1 = 1 " + " AND NOT EXISTS ( " + " SELECT CTBPR.REFUND_REQUEST_ID FROM CON_TB_PAYBACK_REQUEST CTBPR "
					+ " WHERE CTBPR.REFUND_REQUEST_ID = T0.REFUND_REQUEST_ID AND CTBPR.PAYBACK_APPROVE_STATUS NOT IN ( '0', 'C' ) " + " ) " + " AND EXISTS ( " + " SELECT 1 FROM CON_TB_REFUND_REQUEST CTRR "
					+ " LEFT JOIN CON_TB_REQUESTER_COMPANY    CTRC ON CTRC.REFUND_REQUEST_ID = CTRR.REFUND_REQUEST_ID " + " WHERE 1 = 1 AND CTRC.ORDER_STATUS = 'P' " + " ) ";
		}

		sql += " AND T0.PROGRESS_STATUS = '1' " + " AND T0.RECEIVE_NO = :receiveNo ";
		param.put("receiveNo", receiveNo);
		return queryForObject(sql, param, new ConTbPaybackRequestRowMapper());
	}

	public List<PaybackRefundPeriodBean> getRefundPeriodByRefundRequestId(Long refundRequestId, String separateSection) throws Exception {
		Map<String, Object> param = new HashMap<>(1);

		String sql = " SELECT " + " T2.REFUND_PERIOD_ID, " + " T3.REFUND_REQUEST_ID, " + " T2.REFUND_PERIOD, " + " T3.APPROVE_DATE, " + " T5.RECEIPT_NO, " + " T5.PAYMENT_DATE, "
				+ " T1.REFUND_TOTAL_AMOUNT - NVL( T1.PAYBACK_AMOUNT, 0 ) AS REFUND_TOTAL_AMOUNT, " + " T2.PAYBACK_AMOUNT, " + " T1.ORDER_STATUS, " + " T1.ORDER_NO, " + " T1.REQUESTER_ID, " + " NULL              AS PAYBACK_PERIOD_ID, "
				+ " NULL              AS PAYBACK_STATUS, " + " NULL              AS PAYBACK_ORDER_NO, ";

		if (Objects.equals("1", separateSection)) {
			sql += " T1.FULL_NAME      AS FULL_NAME, " + " NULL              AS COMPANY_BRANCH_NAME, " + " T1.RELATION_GROUP, " + " NULL              AS REFUND_COMPANY_ID, " + " NULL              AS REFUND_EMPLOYEE_ID, " + " T4.REFUND_INSURER_ID, "
					+ " T1.PAYBACK_AMOUNT AS REFUND_PAYBACK_AMOUNT " + " FROM " + " CON_TB_REQUESTER39    T1 " + " LEFT JOIN CON_TB_REFUND_PERIOD  T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID AND T1.PERIOD = T2.REFUND_PERIOD "
					+ " LEFT JOIN CON_TB_REFUND_REQUEST T3 ON T3.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID " + " LEFT JOIN CON_TR_REFUND_INSURER T4 ON T4.REFUND_REQUEST_ID = T3.REFUND_REQUEST_ID "
					+ " LEFT JOIN CON_TB_REFUND_RECEIPT T5 ON T5.REFUND_RECEIPT_ID = T1.REFUND_RECEIPT_ID ";

		} else if (Objects.equals("3", separateSection)) {
			sql += " T1.FULL_NAME      AS FULL_NAME, " + " NULL              AS COMPANY_BRANCH_NAME, " + " T1.RELATION_GROUP, " + " NULL              AS REFUND_COMPANY_ID, " + " T4.REFUND_EMPLOYEE_ID, " + " NULL              AS REFUND_INSURER_ID, "
					+ " T1.PAYBACK_AMOUNT AS REFUND_PAYBACK_AMOUNT " + " FROM " + " CON_TB_REQUESTER33    T1 " + " LEFT JOIN CON_TB_REFUND_PERIOD  T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID AND T1.PERIOD = T2.REFUND_PERIOD "
					+ " LEFT JOIN CON_TB_REFUND_REQUEST T3 ON T3.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID " + " LEFT JOIN CON_TR_REFUND_EMPLOYEE T4 ON T4.REFUND_REQUEST_ID = T3.REFUND_REQUEST_ID "
					+ " LEFT JOIN CON_TB_REFUND_RECEIPT T5 ON T5.REFUND_RECEIPT_ID = T1.REFUND_RECEIPT_ID ";

		} else if (Objects.equals("0", separateSection)) {
			sql += " T6.COMPANY_NAME    AS FULL_NAME, " + " T6.COMPANY_BRANCH_NAME, " + " NULL              AS RELATION_GROUP, " + " T4.REFUND_COMPANY_ID, " + " NULL              AS REFUND_EMPLOYEE_ID, " + " NULL              AS REFUND_INSURER_ID, "
					+ " T1.PAYBACK_AMOUNT AS REFUND_PAYBACK_AMOUNT " + " FROM " + " CON_TB_REQUESTER_COMPANY    T1 " + " LEFT JOIN CON_TB_REFUND_PERIOD  T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID AND T1.PERIOD = T2.REFUND_PERIOD "
					+ " LEFT JOIN CON_TB_REFUND_REQUEST T3 ON T3.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID " + " LEFT JOIN CON_TR_REFUND_COMPANY T4 ON T4.REFUND_REQUEST_ID = T3.REFUND_REQUEST_ID "
					+ " LEFT JOIN CON_TB_REFUND_RECEIPT T5 ON T5.REFUND_RECEIPT_ID = T1.REFUND_RECEIPT_ID " + " LEFT JOIN CON_MS_COMPANY        T6 ON T6.COMPANY_ID = T3.COMPANY_ID";

		}
		sql += " WHERE 1 = 1 " + " AND T3.REFUND_REQUEST_ID = :refundRequestId " + " AND T1.ORDER_STATUS IS NOT NULL " + " AND T1.REFUND_TOTAL_AMOUNT > NVL( T1.PAYBACK_AMOUNT, 0 ) " + " AND T1.STATUS = 'A' " + " ORDER BY T3.REFUND_REQUEST_ID DESC ";

		param.put("refundRequestId", refundRequestId);

		return queryForList(sql, param, new PaybackRefundPeriodRowMapper());
	}

//    public List<PaybackRefundPeriodBean> getRefundPeriodByRefundRequestId(Long refundRequestId, String separateSection) throws Exception {
//        Map<String, Object> param = new HashMap<>(1);
//        String sql = " SELECT "+
//                " T1.REFUND_PERIOD_ID, "+
//                " T2.REFUND_REQUEST_ID, "+
////                " NULL         AS REFUND_COMPANY_ID, "+
////                " NULL         AS REFUND_EMPLOYEE_ID, "+
////                " T3.REFUND_INSURER_ID, "+
//                " T1.REFUND_PERIOD, "+
//                " T2.APPROVE_DATE, "+
//                " T5.RECEIPT_NO, "+
//                " T5.PAYMENT_DATE, "+
////                " T4.FULL_NAME AS FULL_NAME, "+
//                " T1.REFUND_TOTAL_AMOUNT, "+
//                " T1.PAYBACK_AMOUNT, "+
//                " T4.ORDER_STATUS, "+
//                " T4.ORDER_NO, "+
//                " T4.REQUESTER_ID, "+
//                " NULL         AS PAYBACK_PERIOD_ID, "+
//                " NULL         AS PAYBACK_STATUS, "+
//                " NULL         AS PAYBACK_ORDER_NO, ";
//
//        if (Objects.equals("1", separateSection)) {
//            sql += " T4.FULL_NAME AS FULL_NAME, "+
//                    " NULL AS COMPANY_BRANCH_NAME, "+
//                    " T4.RELATION_GROUP, "+
//                    " NULL         AS REFUND_COMPANY_ID, "+
//                    " NULL         AS REFUND_EMPLOYEE_ID, "+
//                    " T3.REFUND_INSURER_ID "+
//                    " FROM "+
//                    " CON_TB_REFUND_PERIOD  T1 "+
//                    " LEFT JOIN CON_TB_REFUND_REQUEST T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID "+
//                    " LEFT JOIN CON_TR_REFUND_INSURER T3 ON T3.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID "+
//                    " LEFT JOIN CON_TB_REQUESTER39    T4 ON T4.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID "+
//                    " AND T4.PERIOD = T1.REFUND_PERIOD AND T4.STATUS = 'A' "+
//                    " LEFT JOIN CON_TB_REFUND_RECEIPT T5 ON T5.REFUND_RECEIPT_ID = T4.REFUND_RECEIPT_ID "+
//                    " WHERE 1 = 1 ";
//
//        } else if (Objects.equals("3", separateSection)) {
//            sql += " T4.FULL_NAME AS FULL_NAME, "+
//                    " NULL AS COMPANY_BRANCH_NAME, "+
//                    " T4.RELATION_GROUP, "+
//                    " NULL         AS REFUND_COMPANY_ID, "+
//                    " T3.REFUND_EMPLOYEE_ID, "+
//                    " NULL         AS REFUND_INSURER_ID "+
//                    " FROM "+
//                    " CON_TB_REFUND_PERIOD  T1 "+
//                    " LEFT JOIN CON_TB_REFUND_REQUEST T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID "+
//                    " LEFT JOIN CON_TR_REFUND_EMPLOYEE T3 ON T3.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID "+
//                    " LEFT JOIN CON_TB_REQUESTER33    T4 ON T4.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID "+
//                    " AND T4.PERIOD = T1.REFUND_PERIOD AND T4.STATUS = 'A' "+
//                    " LEFT JOIN CON_TB_REFUND_RECEIPT T5 ON T5.REFUND_RECEIPT_ID = T4.REFUND_RECEIPT_ID "+
//                    " WHERE 1 = 1 ";
//
//        } else if (Objects.equals("0", separateSection)) {
//            sql += " T6.COMPANY_NAME AS FULL_NAME, "+
//                    " T6.COMPANY_BRANCH_NAME, "+
//                    " T7.RELATION_GROUP, "+
//                    " T3.REFUND_COMPANY_ID, "+
//                    " NULL         AS REFUND_EMPLOYEE_ID, "+
//                    " NULL         AS REFUND_INSURER_ID "+
//                    " FROM "+
//                    " CON_TB_REFUND_PERIOD  T1 "+
//                    " LEFT JOIN CON_TB_REFUND_REQUEST T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID "+
//                    " LEFT JOIN CON_TR_REFUND_COMPANY T3 ON T3.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID "+
//                    " LEFT JOIN CON_TB_REQUESTER_COMPANY    T4 ON T4.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID "+
//                    " AND T4.PERIOD = T1.REFUND_PERIOD AND T4.STATUS = 'A' "+
//                    " LEFT JOIN CON_TB_REQUESTER33    T7 ON T7.REFUND_REQUEST_ID = T2.REFUND_REQUEST_ID "+
//                    " AND T7.PERIOD = T1.REFUND_PERIOD AND T7.STATUS = 'A' "+
//                    " LEFT JOIN CON_TB_REFUND_RECEIPT T5 ON T5.REFUND_RECEIPT_ID = T4.REFUND_RECEIPT_ID " +
//                    " LEFT JOIN CON_MS_COMPANY        T6 ON T6.COMPANY_ID = T4.COMPANY_ID"+
//                    " WHERE 1 = 1 ";
//
//        }
//        sql += " AND T2.REFUND_REQUEST_ID = :refundRequestId AND T4.ORDER_STATUS IS NOT NULL "+
//                " ORDER BY "+
//                " T2.REFUND_REQUEST_ID DESC ";
//
//        param.put("refundRequestId", refundRequestId);
//
//        return queryForList(sql, param, new PaybackRefundPeriodRowMapper());
//    }

	public List<ConTbPaybackBean> queryConTbRefundRequestCompanyBatch(Paginable paginable, List<Long> paybackRequestIds) throws Exception {
		Map<String, Object> param = new HashMap<>();
		String sql = """
				SELECT
				    t1.payback_request_id,
				    t1.refund_request_id,
				    NULL AS company_id,
				    NULL AS insurer_id,
				    t1.payback_total_amount,
				    t1.payback_request_no,
				    t1.payback_request_date,
				    t1.payback_request_type,
				    t1.payback_approve_status,
				    t1.payback_approve_date,
				    t1.payback_receive_status,
				    t1.payback_receive_date,
				    t1.section,
				    t1.status,
				    t1.remark,
				    t1.manage_by,
				    t1.create_by,
				    t1.create_date,
				    t1.update_by,
				    t1.update_date,
				    t1.reverse_status,
				    t1.payback_cause,
				    t1.receive_total_amount,
				    t1.dep_code,
				    t1.payback_status,
				    t1.order_no,
				    t1.payback_order_no,
				    t2.company_name,
				    t2.company_no,
				    t2.company_branch_code,
				    t1.requester_company_id,
				    NULL AS requester33_id,
				    NULL AS requester39_id
				FROM con_tb_payback_request t1
				    JOIN con_tb_requester_company t3 ON t3.requester_id = t1.requester_company_id
				    JOIN con_ms_company t2 ON t2.company_id = t3.company_id
				WHERE 1 = 1
				    AND t1.section = '0'
				    AND t1.requester_company_id IS NOT NULL
				    AND t1.payback_order_no IS NULL
				    AND t1.payback_status IS NULL
				    AND t1.payback_approve_status = '1'
				    AND trunc( t1.payback_approve_date ) = trunc(sysdate)
				""";
		if (!CollectionUtils.isEmpty(paybackRequestIds)) {
			sql += " AND t1.payback_request_id in ( :paybackRequestIds )";
			param.put("paybackRequestIds", paybackRequestIds);
		}
//        final String sql = """
//                SELECT t1.payback_request_id,t1.refund_request_id,t1.company_id,t1.insurer_id,t1.payback_total_amount,t1.payback_request_no,t1.payback_request_date,
//                 t1.payback_request_type,t1.payback_approve_status,t1.payback_approve_date,t1.payback_receive_status,t1.payback_receive_date,t1.section,
//                 t1.status,t1.remark,t1.manage_by,t1.create_by,t1.create_date,t1.update_by,t1.update_date,t1.reverse_status,t1.payback_cause,
//                 t1.receive_total_amount,t1.dep_code,t1.payback_status,t1.order_no,t1.payback_order_no,
//                 t2.company_name,t2.company_no,t2.company_branch_code
//                 FROM con_tb_payback_request t1
//                 JOIN con_ms_company t2 ON t2.company_id = t1.company_id
//                 WHERE 1 = 1
//                 AND t1.section = '0' AND t1.company_id IS NOT NULL
//                 AND t1.payback_order_no IS NULL AND t1.payback_status IS NULL
//                 AND t1.payback_approve_status = '1'
//                 AND trunc(t1.payback_approve_date) = trunc(sysdate)
//                """;
		return queryForList(sql, param, paginable, new ConTbPaybackCompanyRowMapper());
	}

	public List<ConTbPaybackBean> queryConTbRefundRequestInsurerBatch(Paginable paginable, List<Long> paybackRequestIds) throws Exception {
		Map<String, Object> param = new HashMap<>();
		String sql = """
				SELECT
				 t1.payback_request_id,
				 t1.refund_request_id,
				 t1.payback_total_amount,
				 t1.payback_request_no,
				 t1.payback_request_date,
				 t1.payback_request_type,
				 t1.payback_approve_status,
				 t1.payback_approve_date,
				 t1.payback_receive_status,
				 t1.payback_receive_date,
				 t1.section,
				 t1.status,
				 t1.remark,
				 t1.manage_by,
				 t1.create_by,
				 t1.create_date,
				 t1.update_by,
				 t1.update_date,
				 t1.reverse_status,
				 t1.payback_cause,
				 t1.receive_total_amount,
				 t1.dep_code,
				 t1.payback_status,
				 t1.order_no,
				 t1.payback_order_no,
				 t2.id_card_no,
				 t2.full_name,
				 NULL AS insurer_id,
				 NULL AS company_id,
				 NULL AS requester_company_id,
				 NULL AS requester33_id,
				 t1.requester39_id
				 FROM con_tb_payback_request t1
				 JOIN con_tb_requester39 t2 ON t2.requester_id = t1.requester39_id
				 WHERE 1 = 1
				 AND t1.section = '1'
				 AND t1.requester39_id IS NOT NULL
				 AND t1.payback_order_no IS NULL
				 AND t1.payback_status IS NULL
				 AND t1.payback_approve_status = '1'
				 AND trunc( t1.payback_approve_date ) = trunc(sysdate)
				""";
		if (!CollectionUtils.isEmpty(paybackRequestIds)) {
			sql += " AND t1.payback_request_id in ( :paybackRequestIds )";
			param.put("paybackRequestIds", paybackRequestIds);
		}
//        final String sql = """
//                SELECT t1.payback_request_id,t1.refund_request_id,t1.company_id,t1.insurer_id,t1.payback_total_amount,t1.payback_request_no,t1.payback_request_date,
//                 t1.payback_request_type,t1.payback_approve_status,t1.payback_approve_date,t1.payback_receive_status,t1.payback_receive_date,t1.section,
//                 t1.status,t1.remark,t1.manage_by,t1.create_by,t1.create_date,t1.update_by,t1.update_date,t1.reverse_status,t1.payback_cause,
//                 t1.receive_total_amount,t1.dep_code,t1.payback_status,t1.order_no,t1.payback_order_no,
//                 t2.id_card_no,t2.full_name
//                 FROM con_tb_payback_request t1
//                 JOIN con_ms_insurer t2 ON t2.insurer_id = t1.insurer_id
//                 WHERE 1 = 1
//                 AND t1.section = '1' AND t1.insurer_id IS NOT NULL
//                 AND t1.payback_order_no IS NULL AND t1.payback_status IS NULL
//                 AND t1.payback_approve_status = '1'
//                 AND trunc(t1.payback_approve_date) = trunc(sysdate)
//                """;
		return queryForList(sql, param, paginable, new ConTbPaybackInsurerRowMapper());
	}

	public List<ConTbPaybackBean> queryConTbRefundRequestEmployeeBatch(Paginable paginable, List<Long> paybackRequestIds) throws Exception {
		Map<String, Object> param = new HashMap<>();
		String sql = """
				SELECT
				    t1.payback_request_id,
				    t1.refund_request_id,
				    NULL AS company_id,
				    NULL AS insurer_id,
				    t1.payback_total_amount,
				    t1.payback_request_no,
				    t1.payback_request_date,
				    t1.payback_request_type,
				    t1.payback_approve_status,
				    t1.payback_approve_date,
				    t1.payback_receive_status,
				    t1.payback_receive_date,
				    t1.section,
				    t1.status,
				    t1.remark,
				    t1.manage_by,
				    t1.create_by,
				    t1.create_date,
				    t1.update_by,
				    t1.update_date,
				    t1.reverse_status,
				    t1.payback_cause,
				    t1.receive_total_amount,
				    t1.dep_code,
				    t1.payback_status,
				    t1.order_no,
				    t1.payback_order_no,
				    t2.id_card_no,
				    t2.full_name,
				    NULL AS company_name,
				    NULL AS company_no,
				    NULL AS company_branch_code,
				    NULL AS requester_company_id,
				    t1.requester33_id,
				    NULL AS requester39_id
				FROM con_tb_payback_request t1
				    JOIN con_tb_requester33 t2 ON t2.requester_id = t1.requester33_id
				WHERE 1 = 1
				    AND (t1.section = '0' OR t1.section = '3')
				    AND t1.requester33_id IS NOT NULL
				    AND t1.payback_order_no IS NULL
				    AND t1.payback_status IS NULL
				    AND t1.payback_approve_status = '1'
				    AND trunc(
				        t1.payback_approve_date
				    ) = trunc(sysdate)
				""";
		if (!CollectionUtils.isEmpty(paybackRequestIds)) {
			sql += " AND t1.payback_request_id in ( :paybackRequestIds )";
			param.put("paybackRequestIds", paybackRequestIds);
		}
//        final String sql = """
//                SELECT t1.payback_request_id,t1.refund_request_id,t1.company_id,t1.insurer_id,t1.payback_total_amount,t1.payback_request_no,t1.payback_request_date,
//                 t1.payback_request_type,t1.payback_approve_status,t1.payback_approve_date,t1.payback_receive_status,t1.payback_receive_date,t1.section,
//                 t1.status,t1.remark,t1.manage_by,t1.create_by,t1.create_date,t1.update_by,t1.update_date,t1.reverse_status,t1.payback_cause,
//                 t1.receive_total_amount,t1.dep_code,t1.payback_status,t1.order_no,t1.payback_order_no,
//                 t3.id_card_no,t3.full_name,t2.company_name,t2.company_no,t2.company_branch_code
//                 FROM con_tb_payback_request t1
//                 JOIN con_ms_company t2 ON t2.company_id = t1.company_id
//                 JOIN con_ms_insurer t3 ON t3.insurer_id = t1.insurer_id
//                 WHERE 1 = 1
//                 AND t1.section = '0' AND t1.insurer_id IS NOT NULL
//                 AND t1.payback_order_no IS NULL AND t1.payback_status IS NULL
//                 AND t1.payback_approve_status = '1'
//                 AND trunc(t1.payback_approve_date) = trunc(sysdate)
//                """;
		return queryForList(sql, param, paginable, new ConTbPaybackEmployeeRowMapper());
	}

	public List<ConTbPaybackBean> queryConTbPaybackCompanyBatch(String createBy, Date createDate, Paginable paginable, List<Long> paybackRequestIds) throws Exception {
		Map<String, Object> param = new HashMap<>();
		String sql = """
				SELECT
				    t1.payback_request_id,
				    t1.refund_request_id,
				    NULL AS company_id,
				    NULL AS insurer_id,
				    t1.payback_total_amount,
				    t1.payback_request_no,
				    t1.payback_request_date,
				    t1.payback_request_type,
				    t1.payback_approve_status,
				    t1.payback_approve_date,
				    t1.payback_receive_status,
				    t1.payback_receive_date,
				    t1.section,
				    t1.status,
				    t1.remark,
				    t1.manage_by,
				    t1.create_by,
				    t1.create_date,
				    t1.update_by,
				    t1.update_date,
				    t1.reverse_status,
				    t1.payback_cause,
				    t1.receive_total_amount,
				    t1.dep_code,
				    t1.payback_status,
				    t1.order_no,
				    t1.payback_order_no,
				    t2.company_name,
				    t2.company_no,
				    t2.company_branch_code,
				    t1.requester_company_id,
				    NULL AS requester33_id,
				    NULL AS requester39_id
				FROM con_tb_payback_request t1
				    JOIN con_tb_requester_company t3 ON t3.requester_id = t1.requester_company_id
				    JOIN con_ms_company t2 ON t2.company_id = t3.company_id
				WHERE 1 = 1
				    AND t1.section = '0'
				    AND t1.requester_company_id IS NOT NULL
				    AND t1.payback_order_no IS NOT NULL
				    AND t1.payback_status IS NULL
				    AND t1.payback_approve_status = '1'
				    AND trunc(t1.payback_approve_date) = trunc(:createDate)
				""";
		if (!CollectionUtils.isEmpty(paybackRequestIds)) {
			sql += " AND t1.payback_request_id IN ( :paybackRequestIds )";
			param.put("paybackRequestIds", paybackRequestIds);
		}
//        final String sql = """
//                SELECT t1.payback_request_id,t1.refund_request_id,t1.company_id,t1.insurer_id,t1.payback_total_amount,t1.payback_request_no,t1.payback_request_date,
//                 t1.payback_request_type,t1.payback_approve_status,t1.payback_approve_date,t1.payback_receive_status,t1.payback_receive_date,t1.section,
//                 t1.status,t1.remark,t1.manage_by,t1.create_by,t1.create_date,t1.update_by,t1.update_date,t1.reverse_status,t1.payback_cause,
//                 t1.receive_total_amount,t1.dep_code,t1.payback_status,t1.order_no,t1.payback_order_no,
//                 t2.company_name,t2.company_no,t2.company_branch_code
//                 FROM con_tb_payback_request t1
//                 JOIN con_ms_company t2 ON t2.company_id = t1.company_id
//                 WHERE 1 = 1
//                 AND t1.section = '0' AND t1.company_id IS NOT NULL
//                 AND t1.payback_order_no IS NOT NULL AND t1.payback_status IS NULL
//                 AND t1.payback_approve_status = '1' AND trunc(t1.payback_approve_date) = trunc(:createDate)
//                """;
		param.put("createBy", createBy);
		param.put("createDate", createDate);
		return queryForList(sql, param, paginable, new ConTbPaybackCompanyRowMapper());
	}

	public List<ConTbPaybackBean> queryConTbPaybackInsurerBatch(String createBy, Date createDate, Paginable paginable, List<Long> paybackRequestIds) throws Exception {
		Map<String, Object> param = new HashMap<>();
		String sql = """
				SELECT
				 t1.payback_request_id,
				 t1.refund_request_id,
				 t1.payback_total_amount,
				 t1.payback_request_no,
				 t1.payback_request_date,
				 t1.payback_request_type,
				 t1.payback_approve_status,
				 t1.payback_approve_date,
				 t1.payback_receive_status,
				 t1.payback_receive_date,
				 t1.section,
				 t1.status,
				 t1.remark,
				 t1.manage_by,
				 t1.create_by,
				 t1.create_date,
				 t1.update_by,
				 t1.update_date,
				 t1.reverse_status,
				 t1.payback_cause,
				 t1.receive_total_amount,
				 t1.dep_code,
				 t1.payback_status,
				 t1.order_no,
				 t1.payback_order_no,
				 t2.id_card_no,
				 t2.full_name,
				 NULL AS insurer_id,
				 NULL AS company_id,
				 NULL AS requester_company_id,
				 NULL AS requester33_id,
				 t1.requester39_id
				 FROM con_tb_payback_request t1
				 JOIN con_tb_requester39 t2 ON t2.requester_id = t1.requester39_id
				 WHERE 1 = 1
				 AND t1.section = '1'
				 AND t1.requester39_id IS NOT NULL
				 AND t1.payback_order_no IS NOT NULL
				 AND t1.payback_status IS NULL
				 AND t1.payback_approve_status = '1'
				 AND trunc(t1.payback_approve_date) = trunc(:createDate)
				""";
		if (!CollectionUtils.isEmpty(paybackRequestIds)) {
			sql += " AND t1.payback_request_id IN ( :paybackRequestIds )";
			param.put("paybackRequestIds", paybackRequestIds);
		}
//        final String sql = """
//                SELECT t1.payback_request_id,t1.refund_request_id,t1.company_id,t1.insurer_id,t1.payback_total_amount,t1.payback_request_no,t1.payback_request_date,
//                 t1.payback_request_type,t1.payback_approve_status,t1.payback_approve_date,t1.payback_receive_status,t1.payback_receive_date,t1.section,
//                 t1.status,t1.remark,t1.manage_by,t1.create_by,t1.create_date,t1.update_by,t1.update_date,t1.reverse_status,t1.payback_cause,
//                 t1.receive_total_amount,t1.dep_code,t1.payback_status,t1.order_no,t1.payback_order_no,
//                 t2.id_card_no,t2.full_name
//                 FROM con_tb_payback_request t1
//                 JOIN con_ms_insurer t2 ON t2.insurer_id = t1.insurer_id
//                 WHERE 1 = 1
//                 AND t1.section = '1' AND t1.insurer_id IS NOT NULL
//                 AND t1.payback_order_no IS NOT NULL AND t1.payback_status IS NULL
//                 AND payback_approve_status = '1' AND trunc(t1.payback_approve_date) = trunc(:createDate)
//                """;
//        Map<String, Object> param = new HashMap<>();
		param.put("createBy", createBy);
		param.put("createDate", createDate);
		return queryForList(sql, param, paginable, new ConTbPaybackInsurerRowMapper());
	}

	public List<ConTbPaybackBean> queryConTbPaybackEmployeeBatch(String createBy, Date createDate, Paginable paginable, List<Long> paybackRequestIds) throws Exception {
		Map<String, Object> param = new HashMap<>();
		String sql = """
				SELECT
				    t1.payback_request_id,
				    t1.refund_request_id,
				    NULL AS company_id,
				    NULL AS insurer_id,
				    t1.payback_total_amount,
				    t1.payback_request_no,
				    t1.payback_request_date,
				    t1.payback_request_type,
				    t1.payback_approve_status,
				    t1.payback_approve_date,
				    t1.payback_receive_status,
				    t1.payback_receive_date,
				    t1.section,
				    t1.status,
				    t1.remark,
				    t1.manage_by,
				    t1.create_by,
				    t1.create_date,
				    t1.update_by,
				    t1.update_date,
				    t1.reverse_status,
				    t1.payback_cause,
				    t1.receive_total_amount,
				    t1.dep_code,
				    t1.payback_status,
				    t1.order_no,
				    t1.payback_order_no,
				    t2.id_card_no,
				    t2.full_name,
				    NULL AS company_name,
				    NULL AS company_no,
				    NULL AS company_branch_code,
				    NULL AS requester_company_id,
				    t1.requester33_id,
				    NULL AS requester39_id
				FROM con_tb_payback_request t1
				    JOIN con_tb_requester33 t2 ON t2.requester_id = t1.requester33_id
				WHERE 1 = 1
				    AND (t1.section = '0' OR t1.section = '3')
				    AND t1.requester33_id IS NOT NULL
				    AND t1.payback_order_no IS NOT NULL
				    AND t1.payback_status IS NULL
				    AND payback_approve_status = '1'
				    AND trunc(t1.payback_approve_date) = trunc(:createDate)
				""";
		if (!CollectionUtils.isEmpty(paybackRequestIds)) {
			sql += " AND t1.payback_request_id IN ( :paybackRequestIds )";
			param.put("paybackRequestIds", paybackRequestIds);
		}
//        final String sql = """
//                SELECT t1.payback_request_id,t1.refund_request_id,t1.company_id,t1.insurer_id,t1.payback_total_amount,t1.payback_request_no,t1.payback_request_date,
//                 t1.payback_request_type,t1.payback_approve_status,t1.payback_approve_date,t1.payback_receive_status,t1.payback_receive_date,t1.section,
//                 t1.status,t1.remark,t1.manage_by,t1.create_by,t1.create_date,t1.update_by,t1.update_date,t1.reverse_status,t1.payback_cause,
//                 t1.receive_total_amount,t1.dep_code,t1.payback_status,t1.order_no,t1.payback_order_no,
//                 t3.id_card_no,t3.full_name,t2.company_name,t2.company_no,t2.company_branch_code
//                 FROM con_tb_payback_request t1
//                 JOIN con_ms_company t2 ON t2.company_id = t1.company_id
//                 JOIN con_ms_insurer t3 ON t3.insurer_id = t1.insurer_id
//                 WHERE 1 = 1
//                 AND t1.section = '0' AND t1.insurer_id IS NOT NULL
//                 AND t1.payback_order_no IS NOT NULL AND t1.payback_status IS NULL
//                 AND payback_approve_status = '1' AND trunc(t1.payback_approve_date) = trunc(:createDate)
//                """;
//        Map<String, Object> param = new HashMap<>();
		param.put("createBy", createBy);
		param.put("createDate", createDate);
		return queryForList(sql, param, paginable, new ConTbPaybackEmployeeRowMapper());
	}

	public List<CheckPaybackBean> findPaybackRequestNoByRefundRequestId(Long refundRequestId) throws Exception {
		final Map<String, Object> param = new HashMap<>();
		final String sql = """
				SELECT PAYBACK_REQUEST_NO, PAYBACK_APPROVE_STATUS FROM CON_TB_PAYBACK_REQUEST WHERE REFUND_REQUEST_ID = :refundRequestId AND ( PAYBACK_APPROVE_STATUS NOT IN ( '0', 'C' ) OR PAYBACK_APPROVE_STATUS IS NULL )
				""";
		param.put("refundRequestId", refundRequestId);
		return queryForList(sql, param, new CheckPaybackRowMapper());
	}

	public List<OrderBean> queryOrderNoPayback(Long paybackRequestId) throws Exception {
		final String sql = """
				SELECT
				 nvl( ctbrc.order_no, nvl( ctbr33.order_no, ctbr39.order_no ) ) AS order_no,
				 nvl( ctbrc.order_status, nvl( ctbr33.order_status, ctbr39.order_status ) ) AS order_status,
				 ctbrr.receipt_no, ctbrr.payment_date
				 FROM con_tb_payback_request ctbpr
				 LEFT JOIN con_tb_requester_company ctbrc ON ctbrc.requester_id = ctbpr.requester_company_id
				 LEFT JOIN con_tb_requester33       ctbr33 ON ctbr33.requester_id = ctbpr.requester33_id
				 LEFT JOIN con_tb_requester39       ctbr39 ON ctbr39.requester_id = ctbpr.requester39_id
				 LEFT JOIN con_tb_refund_receipt    ctbrr ON ctbrr.refund_receipt_id = ctbrc.refund_receipt_id
				                                    OR ctbrr.refund_receipt_id = ctbr33.refund_receipt_id OR ctbrr.refund_receipt_id = ctbr39.refund_receipt_id
				 WHERE 1 = 1
				 AND ctbpr.payback_request_id = :paybackRequestId
				    """;
		Map<String, Object> param = new HashMap<>();
		param.put("paybackRequestId", paybackRequestId);
		return queryForList(sql, param, new OrderRowMapper());
	}

	public List<RequesterPaybackBean> queryOrderByRefundRequestId(Long refundRequestId, String separateSection) throws Exception {
		Map<String, Object> param = new HashMap<>();
		String sql = " SELECT " + " T2.ORDER_NO, " + " T2.ORDER_STATUS, " + " T2.PERIOD, " + " T2.REFUND_TOTAL_AMOUNT, " + " T3.RECEIPT_NO, " + " T3.PAYMENT_DATE, ";

		if (Objects.equals("1", separateSection)) {
			sql += " T2.FULL_NAME, " + " NULL AS COMPANY_NO, " + " NULL AS COMPANY_NAME, " + " NULL AS COMPANY_BRANCH_CODE, " + " NULL AS COMPANY_BRANCH_NAME " + " FROM " + " CON_TB_REFUND_REQUEST T1 "
					+ " LEFT JOIN CON_TB_REQUESTER39    T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID " + " LEFT JOIN CON_TB_REFUND_RECEIPT T3 ON T3.REFUND_RECEIPT_ID = T2.REFUND_RECEIPT_ID " + " WHERE 1 = 1 ";

		} else if (Objects.equals("3", separateSection)) {
			sql += " T2.FULL_NAME, " + " NULL AS COMPANY_NO, " + " NULL AS COMPANY_NAME, " + " NULL AS COMPANY_BRANCH_CODE, " + " NULL AS COMPANY_BRANCH_NAME " + " FROM " + " CON_TB_REFUND_REQUEST T1 "
					+ " LEFT JOIN CON_TB_REQUESTER33    T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID " + " LEFT JOIN CON_TB_REFUND_RECEIPT T3 ON T3.REFUND_RECEIPT_ID = T2.REFUND_RECEIPT_ID " + " WHERE 1 = 1 ";

		} else if (Objects.equals("0", separateSection)) {
			sql += " T5.FULL_NAME, " + " T4.COMPANY_NO, " + " T4.COMPANY_NAME, " + " T4.COMPANY_BRANCH_CODE, " + " T4.COMPANY_BRANCH_NAME " + " FROM " + " CON_TB_REFUND_REQUEST    T1 "
					+ " LEFT JOIN CON_TB_REQUESTER_COMPANY T2 ON T2.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID " + " LEFT JOIN CON_TB_REQUESTER33       T5 ON T5.REFUND_REQUEST_ID = T1.REFUND_REQUEST_ID "
					+ " LEFT JOIN CON_TB_REFUND_RECEIPT    T3 ON T3.REFUND_RECEIPT_ID = T2.REFUND_RECEIPT_ID " + " LEFT JOIN CON_MS_COMPANY           T4 ON T4.COMPANY_ID = T2.COMPANY_ID " + " WHERE 1 = 1 ";
		}
		sql += " AND T1.REFUND_REQUEST_ID = :refundRequestId ";
		param.put("refundRequestId", refundRequestId);

		return queryForList(sql, param, new RequesterPaybackRowMapper());
	}

//    public List<OrderBean> queryOrderByRefundRequestId(Long refundRequestId) throws Exception {
//        final String sql = """
//                SELECT
//                 nvl( ctbrc.order_no, nvl( ctbr33.order_no, ctbr39.order_no ) ) AS order_no,
//                 nvl( ctbrc.order_status, nvl( ctbr33.order_status, ctbr39.order_status ) ) AS order_status,
//                 ctbrr.receipt_no, ctbrr.payment_date
//                 FROM con_tb_refund_request ctbrr
//                 LEFT JOIN con_tb_requester_company ctbrc ON ctbrc.refund_request_id = ctbrr.refund_request_id
//                 LEFT JOIN con_tb_requester33       ctbr33 ON ctbr33.refund_request_id = ctbrr.refund_request_id
//                 LEFT JOIN con_tb_requester39       ctbr39 ON ctbr39.refund_request_id = ctbrr.refund_request_id
//                 LEFT JOIN con_tb_refund_receipt    ctbrr ON ctbrr.refund_receipt_id = ctbrc.refund_receipt_id
//                                                     OR ctbrr.refund_receipt_id = ctbr33.refund_receipt_id OR ctbrr.refund_receipt_id = ctbr39.refund_receipt_id
//                 WHERE 1 = 1
//                 AND ctbrr.refund_request_id = :refundRequestId
//                    """;
//        Map<String, Object> param = new HashMap<>();
//        param.put("refundRequestId", refundRequestId);
//        return queryForList(sql, param, new OrderRowMapper());
//    }
//    public List<String> findPaybackRequestNoByRefundRequestId(Long refundRequestId) throws Exception{
//        final String sql = """
//                SELECT PAYBACK_REQUEST_NO FROM CON_TB_PAYBACK_REQUEST WHERE REFUND_REQUEST_ID = :refundRequestId
//                """;
//        final Map<String,Object> param = new HashMap<>();
//        param.put("refundRequestId", refundRequestId);
//        return queryForList(sql,param,new RowMapper<String>() {
//            @Override
//            public String mapRow(ResultSet resultSet, int i) throws SQLException {
//                return resultSet.getString("PAYBACK_REQUEST_NO");
//            }
//        });
//    }

	@Override
	protected String getTableName() {
		return "con_tb_payback_request";
	}

	@Override
	protected String getPkColumnName() {
		return "payback_request_id";
	}
}
