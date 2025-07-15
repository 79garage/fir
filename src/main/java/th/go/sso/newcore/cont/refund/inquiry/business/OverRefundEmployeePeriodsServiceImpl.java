package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RateInformationBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReceiptInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SelectedReceiptBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsInsurerIdCardDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.OverRefundEmployeeDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundInsurerSelectedRefundRequestReceiptDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundInsurerMultipleCompanyInfoByPeriodsRequest;

@Service
public class OverRefundEmployeePeriodsServiceImpl {

    @Autowired
    TimerHelper timer;

    @Autowired
    private OverRefundEmployeeDaoImpl overRefundEmployeeDao;

    @Autowired
    private ConMsInsurerIdCardDaoImpl conMsInsurerIdCardDao;

    @Autowired
    private RefundInsurerSelectedReceiptDaoImpl refundInsurerSelectedReceiptDao;

    @Autowired
    private RefundInsurerSelectedRefundRequestReceiptDaoImpl refundInsurerSelectedRefundRequestReceiptDao;

    @Autowired
    private CalRateServiceImpl calRateService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RefundInsurerBean searchEmployeeDetails(String idCardNo) throws Exception {
        return conMsInsurerIdCardDao.findEmployeeDetail(idCardNo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OverRefundEmployeeBean> searchEmployeeRefundPeriods(GetRefundInsurerMultipleCompanyInfoByPeriodsRequest request, String accessToken) throws Exception {
        timer.start("searchEmployeeRefundPeriods");

        String idCardNo = request.getIdCardNo();
        List<String> period = request.getPeriods();

        List<OverRefundEmployeeBean> periodList = overRefundEmployeeDao.findReceiveEmployeeMultipleCompaniesPeriods(idCardNo, period);

        for (OverRefundEmployeeBean periodData : periodList) {
            String[] receiptIds = periodData.getReceiptIds().split(",");
            List<RefundReceiptInsurerBean> receiptList = refundInsurerSelectedReceiptDao.findReceiptReceiveEmployee(List.of(receiptIds));
            List<SelectedReceiptBean> refundRequestReceiptList = refundInsurerSelectedRefundRequestReceiptDao.findReceiptRefundRequestReceipt(List.of(receiptIds));
            List<OverRefundEmployeeCompanyBean> companies = overRefundEmployeeDao.findCompanyDetailsMultipleCompanies(request.getIdCardNo()
                    , periodData.getPayPeriod());

            if (!CollectionUtils.isEmpty(companies)) {
                Long companyId = companies.stream()
                        .max(Comparator.comparing(OverRefundEmployeeCompanyBean::getCntrRate))
                        .map(OverRefundEmployeeCompanyBean::getCompanyId)
                        .get();
                RateInformationBean rate = findRate(companyId, periodData.getPayPeriod(), accessToken);
                mapRate(periodData, rate);

            }
//            periodData.setCountCompany(companies.size());
            periodData.setCountCompany(countDistinctCompany(companies));
            periodData.setReceiptList(receiptList);
            periodData.setSelectedReceiptList(refundRequestReceiptList);
            periodData.setCompanyDetails(companies);
        }
        timer.endToString("searchEmployeeRefundPeriods");
        return periodList;
    }

    private int countDistinctCompany(List<OverRefundEmployeeCompanyBean> companies) {
        List<String> companyNoList = companies.stream().map(OverRefundEmployeeCompanyBean::getCompanyNo).distinct().collect(Collectors.toList());
        return companyNoList.size();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public int getCountCompany(String idCardNo) throws Exception{
        return overRefundEmployeeDao.getCountCompany(idCardNo);
    }

    private RateInformationBean findRate(Long companyId, String payPeriod, String accessToken) throws Exception {
        return calRateService.getRateEmployee(companyId
                , payPeriod
                , null
                , accessToken);
    }

    private void mapRate(OverRefundEmployeeBean receiveData, RateInformationBean rate) {
        receiveData.setRefundSicknessRate(rate.getSickness());
        receiveData.setRefundRetirementRate(rate.getRetirement());
        receiveData.setRefundUnemploymentRate(rate.getUnemployment());
        receiveData.setRefundMaternityRate(rate.getMaternity());
        receiveData.setRefundInvalidityRate(rate.getInvalidity());
        receiveData.setRefundChildAllowanceRate(rate.getChildAllowance());
        receiveData.setRefundDeathRate(rate.getDeath());
    }
}
