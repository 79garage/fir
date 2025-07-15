package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.SearchDeleteCancelHistoryBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.OverRefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.SearchRefundDeleteCancelHistoryDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundDeleteCancelHistoryRequest;

@Service
public class SearchRefundDeleteCancelHistServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(SearchRefundDeleteCancelHistServiceImpl.class);

    @Autowired
    SearchRefundDeleteCancelHistoryDaoImpl deleteCancelHistoryDao;
    @Autowired
    OverRefundEmployeeDaoImpl overRefundEmployeeDao;

    public String getSectionByReceiveNo(String receiveNo) throws Exception {
        return deleteCancelHistoryDao.getSectionByReceiveNo(receiveNo);
    }

    public List<SearchDeleteCancelHistoryBean> searchHistoryList(SearchRefundDeleteCancelHistoryRequest request, Paginable paginable) throws Exception {
        log.debug("START: searchHistoryList");
        if (!StringUtils.isEmpty(request.getReceiveNo()) && !StringUtils.isEmpty(request.getSection())) {
            return "0".equals(request.getSection())
                    ? deleteCancelHistoryDao.searchDeleteCancelCompanyByReceiveNo(request.getReceiveNo(), paginable)
                    : "1".equals(request.getSection())
                            ? deleteCancelHistoryDao.searchDeleteCancelInsurerByReceiveNo(request.getReceiveNo(), paginable)
                            : getMultiCompanyList(deleteCancelHistoryDao.searchDeleteCancelMultiCompanyByReceiveNo(request.getReceiveNo(), paginable));
        }
        String startPeriod = request.getStartPeriodYear() + request.getStartPeriodMonth();
        String endPeriod = request.getEndPeriodYear() + request.getEndPeriodMonth();
        return "0".equals(request.getSection())
                ? deleteCancelHistoryDao.searchDeleteCancelCompany(request.getCompanyId(), startPeriod, endPeriod, paginable)
                : "1".equals(request.getSection())
                    ? deleteCancelHistoryDao.searchDeleteCancelInsurer(request.getIdCardNo(), startPeriod, endPeriod, paginable)
                    : getMultiCompanyList(deleteCancelHistoryDao.searchDeleteCancelMultiCompany(request.getIdCardNo(), startPeriod, endPeriod, paginable));
    }

    private List<SearchDeleteCancelHistoryBean> getMultiCompanyList(List<SearchDeleteCancelHistoryBean> list) throws Exception {
        for (SearchDeleteCancelHistoryBean bean : list) {
            bean.setCompanyDetails(overRefundEmployeeDao.findCompanyDetailsMultipleCompanies(bean.getIdCardNo(), bean.getRefundPeriod()));
        }
        return list;
    }
}
