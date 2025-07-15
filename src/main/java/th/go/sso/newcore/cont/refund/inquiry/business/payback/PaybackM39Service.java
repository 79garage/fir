package th.go.sso.newcore.cont.refund.inquiry.business.payback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.refund.inquiry.bean.payback.PaybackItemBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.payback.RefundItemBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.payback.PaybackM39Dao;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchM39PaybackListRequest;

import java.util.List;

@Service
public class PaybackM39Service {
	private static final Logger log = LoggerFactory.getLogger(PaybackM39Service.class);

	@Autowired
	private TimerHelper timer;
	@Autowired
	private PaybackM39Dao paybackM39Dao;

	public List<PaybackItemBean> searchInsurerPaybackList(SearchM39PaybackListRequest request, Paginable paginable) throws Exception {
		return paybackM39Dao.searchInsurerPaybackList(request, paginable);
	}

	public List<RefundItemBean> searchInsurerRefundSuccessList(SearchM39PaybackListRequest request, Paginable paginable) throws Exception {
		return paybackM39Dao.searchInsurerRefundSuccessList(request, paginable);
	}
}
