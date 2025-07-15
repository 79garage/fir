package th.go.sso.newcore.cont.refund.inquiry.dao.impl.payback;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import th.go.sso.newcore.cont.common.constant.type.OrderDirection;
import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.payback.PaybackItemBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.payback.RefundItemBean;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchM39PaybackListRequest;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PaybackM39Dao extends DaoBase<Long> {

	public PaybackM39Dao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected String getTableName() {
		return "con_tb_payback_request";
	}

	@Override
	protected String getPkColumnName() {
		return "payback_request_id";
	}

	public List<PaybackItemBean> searchInsurerPaybackList(SearchM39PaybackListRequest request, Paginable paginable) throws Exception {
		Map<String, Object> param = new HashMap<>();
		String sql = """
				SELECT
					pb.payback_request_id,
					pb.payback_request_no,
					pb.payback_request_date,
					pb.payback_total_amount,
					pb.payback_approve_status,
					pb.payback_status,
					pb.status,
					ref.refund_request_id,
					ref.receive_no,
					ref.section,
					NULL AS split_refund,
					req.REQUESTER_ID,
					req.PERIOD AS REFUND_PERIOD,
					NULL AS company_id,
					NULL AS company_no,
					NULL AS company_name,
					NULL AS company_branch_code,
					NULL AS company_branch_name,
					ins.insurer_id,
					ins.id_card_no,
					ins.full_name
				FROM con_tb_payback_request pb
				JOIN con_tb_refund_request ref ON ref.refund_request_id = pb.refund_request_id
				JOIN CON_TB_REQUESTER39 req ON (pb.REQUESTER39_ID = req.REQUESTER_ID)
				LEFT JOIN con_ms_insurer ins ON ins.insurer_id = ref.insurer_id
				WHERE
					1 = 1
					AND pb.status = 'A'
					AND ref.section = '1'
				""";
		if (!StringUtils.isEmpty(request.getIdCardNo())) {
			sql += " AND ins.id_card_no = :idCardNo";
			param.put("idCardNo", request.getIdCardNo());
		}
		if (!StringUtils.isEmpty(request.getPaybackNo())) {
			sql += " AND pb.payback_request_no = :paybackNo";
			param.put("paybackNo", request.getPaybackNo());
		}
		if (!StringUtils.isEmpty(request.getReceiveNo())) {
			sql += " AND ref.receive_no = :receiveNo";
			param.put("receiveNo", request.getReceiveNo());
		}
		if (!StringUtils.isEmpty(request.getStatus())) {
			switch (request.getStatus()) {
			case "N" -> sql += " AND (pb.payback_approve_status = 'N' OR pb.payback_approve_status IS NULL)";
			case "W" -> sql += " AND pb.payback_approve_status = 'W' ";
			case "0" -> sql += " AND pb.payback_approve_status = '0' AND ( pb.payback_status IS NULL OR pb.payback_status = 'C' ) ";
			case "1" -> sql += " AND pb.payback_approve_status = '1' AND ( pb.payback_status IS NULL OR pb.payback_status = 'C' ) ";
			case "S" -> sql += " AND pb.payback_approve_status = '1' AND pb.payback_status = 'W' ";
			case "F" -> sql += " AND pb.payback_approve_status = '1' AND pb.payback_status = 'F' ";
			case "P" -> sql += " AND pb.payback_approve_status = '1' AND pb.payback_status = 'P' ";
			case "T" -> sql += " AND pb.payback_approve_status = '1' AND pb.payback_status = 'T' ";
			case "C" -> sql += " AND pb.payback_approve_status = 'C' AND ( pb.payback_status IS NULL OR pb.payback_status = 'C' OR pb.payback_status = 'F' ) ";
			}
		}
		if (paginable == null) {
			paginable = new Paginable();
			paginable.setCurrentPage(1);
			paginable.setRowPerPage(1000L);
		}
		if (paginable.getOrderDirection() == null) {
			paginable.setOrderDirection(OrderDirection.DESC);
		}
		paginable.setOrderBy(StringUtils.isEmpty(paginable.getOrderBy()) ? "payback_request_date" : List.of("paybackRequestNo", "payback_request_no").contains(paginable.getOrderBy()) ? "payback_request_no" : "payback_request_date");
		paginable.setPrimaryKey("payback_request_id");
		return queryForList(sql, param, paginable, new BeanPropertyRowMapper<>(PaybackItemBean.class));
	}

	public List<RefundItemBean> searchInsurerRefundSuccessList(SearchM39PaybackListRequest request, Paginable paginable) throws Exception {
		Map<String, Object> param = new HashMap<>();
		String sql = """
				SELECT
					refu.REFUND_REQUEST_ID,
					refu.COMPANY_ID,
					refu.INSURER_ID,
					refu.RECEIVE_NO,
					refu.NOTICE_NO,
					refu.ANNOUNCE_NO,
					refu.APPROVE_NO,
					refu.APPROVE_DATE,
					refu.APPROVE_STATUS,
					refu.SECTION,
					refu.REASON_CODE,
					refp.REFUND_PERIOD,
					refins.REFUND_INSURER_ID AS TR_REFUND_ID,
					refins.PROGRESS_STATUS,
					refins.PAY_STATUS,
					refins.STATUS,
					NULL AS REQUEST_EMPR_AMOUNT,
					refins.REQUEST_EMPE_AMOUNT,
					refins.REQUEST_TOTAL_AMOUNT,
					refins.REQUEST_INTEREST_AMOUNT,
					refins.REQUEST_OVER_PAID_AMOUNT,
					NULL AS REFUND_EMPR_AMOUNT,
					refins.REFUND_EMPE_AMOUNT,
					refins.REFUND_TOTAL_AMOUNT,
					refins.REFUND_INTEREST_AMOUNT,
					refins.REFUND_OVER_PAID_AMOUNT,
					req.REQUESTER_ID,
					req.ORDER_NO,
					req.ORDER_STATUS,
					req.REFUND_SICKNESS_AMOUNT,
					req.REFUND_RETIREMENT_AMOUNT,
					req.REFUND_UNEMPLOYMENT_AMOUNT,
					req.REFUND_SICKNESS_RATE,
					req.REFUND_RETIREMENT_RATE,
					req.REFUND_UNEMPLOYMENT_RATE,
					req.REQUESTER_TYPE,
					NULL AS COMPANY_NO,
					NULL AS COMPANY_NAME,
					NULL AS COMPANY_BRANCH_CODE,
					NULL AS COMPANY_BRANCH_NAME,
					ins.ID_CARD_NO,
					ins.FULL_NAME
				FROM CON_TB_REFUND_REQUEST refu
				JOIN CON_TB_REFUND_PERIOD refp ON (refu.REFUND_REQUEST_ID = refp.REFUND_REQUEST_ID)
				JOIN CON_TR_REFUND_INSURER refins ON (refu.REFUND_REQUEST_ID = refins.REFUND_REQUEST_ID)
				JOIN CON_TB_REQUESTER39 req ON (refu.REFUND_REQUEST_ID = req.REFUND_REQUEST_ID AND refp.REFUND_PERIOD = req.PERIOD)
				JOIN CON_MS_INSURER ins ON (refu.insurer_id = ins.insurer_id)
				WHERE
					1 = 1
					AND refu.STATUS = 'A'
					AND refu.SECTION = '1'
					AND refu.APPROVE_STATUS = '1'
					AND req.ORDER_STATUS = 'P'
					AND NOT EXISTS (SELECT 1 FROM con_tb_payback_request pb WHERE pb.REQUESTER39_ID = req.REQUESTER_ID)
				""";
		if (!StringUtils.isEmpty(request.getIdCardNo())) {
			sql += " AND ins.id_card_no = :idCardNo";
			param.put("idCardNo", request.getIdCardNo());
		}
		if (!StringUtils.isEmpty(request.getReceiveNo())) {
			sql += " AND refu.receive_no = :receiveNo";
			param.put("receiveNo", request.getReceiveNo());
		}
		if (paginable == null) {
			paginable = new Paginable();
			paginable.setCurrentPage(1);
			paginable.setRowPerPage(1000L);
		}
		if (paginable.getOrderDirection() == null) {
			paginable.setOrderDirection(OrderDirection.DESC);
		}
		paginable.setOrderBy(StringUtils.isEmpty(paginable.getOrderBy()) ? "APPROVE_DATE" : List.of("orderNo", "order_no").contains(paginable.getOrderBy()) ? "ORDER_NO" : "APPROVE_DATE");
		paginable.setPrimaryKey("REFUND_REQUEST_ID");
		return queryForList(sql, param, paginable, new BeanPropertyRowMapper<>(RefundItemBean.class));
	}
}
