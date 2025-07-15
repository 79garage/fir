package th.go.sso.newcore.cont.refund.inquiry.business.payback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.refund.inquiry.bean.payback.PaybackItemBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.payback.RefundItemBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.payback.PaybackM33Dao;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchM33PaybackListRequest;

import java.util.List;

@Service
public class PaybackM33Service {
	private static final Logger log = LoggerFactory.getLogger(PaybackM33Service.class);

	@Autowired
	private TimerHelper timer;
	@Autowired
	private PaybackM33Dao paybackM33Dao;

	public List<PaybackItemBean> searchCompanyPaybackList(SearchM33PaybackListRequest request, Paginable paginable) throws Exception {
		return paybackM33Dao.searchCompanyPaybackList(request, paginable);
	}

	public List<PaybackItemBean> searchEmployeePaybackList(SearchM33PaybackListRequest request, Paginable paginable) throws Exception {
		return paybackM33Dao.searchEmployeePaybackList(request, paginable);
	}

	public List<RefundItemBean> searchCompanyRefundSuccessList(SearchM33PaybackListRequest request, Paginable paginable) throws Exception {
		return paybackM33Dao.searchCompanyRefundSuccessList(request, paginable);
	}

	public List<RefundItemBean> searchEmployeeRefundSuccessList(SearchM33PaybackListRequest request, Paginable paginable) throws Exception {
		return paybackM33Dao.searchEmployeeRefundSuccessList(request, paginable);
	}
}
