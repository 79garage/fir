package th.go.sso.newcore.cont.refund.inquiry.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.business.ConTbSequenceGeneratorService;
import th.go.sso.newcore.cont.common.constant.type.RequestChannel;
import th.go.sso.newcore.cont.common.constant.type.RequestTopic;
import th.go.sso.newcore.cont.common.constant.type.RequestType;
import th.go.sso.newcore.cont.common.utils.CollectionUtils;
import th.go.sso.newcore.cont.common.utils.DateTimeUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsBankBranchBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsDepartmentBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RateInformationBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyRequestBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsBankDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConMsDepartmentDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundCompanyDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundCompanyPeriodDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.SplitEmployeeRefundDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchSplitEmployeeRefundRequest;

@Service
public class TransactionSplitEmployeeRefundCompanyServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(TransactionSplitEmployeeRefundCompanyServiceImpl.class);

    @Autowired
    private SplitEmployeeRefundDaoImpl splitEmployeeRefundDao;

    @Autowired
    private RefundCompanyDaoImpl refundCompanyDao;

    @Autowired
    private ConMsDepartmentDaoImpl conMsDepartmentDao;

    @Autowired
    private ConMsBankDaoImpl conMsBankDao;

    @Autowired
    private ConTbSequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private RefundCompanyPeriodDaoImpl refundCompanyPeriodDao;

    @Autowired
    private CalRateServiceImpl calRateService;

    @Transactional
    public SplitEmployeeRefundCompanyRequestBean searchRefundSplitEmployee(Long companyId, Long refundRequestId) throws Exception {
        log.info("Start searchRefundSplitEmployee");
        SplitEmployeeRefundCompanyRequestBean bean = refundCompanyDao.getSplitEmployeeRefund(companyId, refundRequestId);
        if (!NumberUtils.isZeroOrNull(bean.getRefundBankId())) {
            ConMsBankBean bank = conMsBankDao.searchBank(bean.getRefundBankId());
            bean.setRefundBankCode(bank.getBankCode());
            if (!NumberUtils.isZeroOrNull(bean.getRefundBankBranchId())) {
                ConMsBankBranchBean bankBranch = conMsBankDao.searchBankBranch(bean.getRefundBankBranchId());
                bean.setRefundBankBranchCode(bankBranch.getBankBranchCode());
            }
        }
        log.info("End searchRefundSplitEmployee");
        return bean;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public SplitEmployeeRefundCompanyRequestBean searchSplitEmployeeRefund(SearchSplitEmployeeRefundRequest request) throws Exception {

        log.debug("Start SplitEmployeeRefund ");
        try {

            Long companyId = request.getCompanyId();
            Long refundRequestId = request.getRefundRequestId();

            SplitEmployeeRefundCompanyRequestBean splitEmployeeRefund = refundCompanyDao.getSplitEmployeeRefund(companyId, refundRequestId);

            if (!StringUtils.isEmpty(splitEmployeeRefund.getSsoBranchCode())) {

                ConMsDepartmentBean department = conMsDepartmentDao.findDepartment(splitEmployeeRefund.getSsoBranchCode());
                splitEmployeeRefund.setDepartmentResponsibleId(department.getDepartmentId());
                log.debug("setDepartmentResponsibleId success");
            }
            splitEmployeeRefund.setDepartmentReceiveCode(splitEmployeeRefund.getDepartmentReceiveCode());
//            if (!NumberUtils.isZeroOrNull(splitEmployeeRefund.getDepartmentReceiveId())) {
//
//                ConMsDepartmentBean department = conMsDepartmentDao.searchDepName(splitEmployeeRefund.getDepartmentReceiveId());
//                splitEmployeeRefund.setDepartmentReceiveCode(department.getDepCode());
//                log.debug("setDepartmentReceiveCode success");
//            }

            if (!NumberUtils.isZeroOrNull(splitEmployeeRefund.getRefundBankId())) {

                ConMsBankBean bank = conMsBankDao.searchBank(splitEmployeeRefund.getRefundBankId());
                splitEmployeeRefund.setRefundBankCode(bank.getBankCode());
                log.debug("setRefundBankCode success");
            }

            if (!NumberUtils.isZeroOrNull(splitEmployeeRefund.getRefundBankBranchId())) {

                ConMsBankBranchBean bankBranch = conMsBankDao.searchBankBranch(splitEmployeeRefund.getRefundBankBranchId());
                splitEmployeeRefund.setRefundBankBranchCode(bankBranch.getBankBranchCode());
                log.debug("setRefundBankBranchCode success");
            }
            return splitEmployeeRefund;

        } catch (Exception e) {
            log.error("SplitEmployeeRefund : {}", e.getMessage());
        }

        log.debug("End SplitEmployeeRefund ");
        return null;

    }

    @Transactional
    public String generateRunningNo(String depCode, String requestType, String requestChannel) throws Exception {
        RequestChannel reqChannel = getRequestChanel(requestChannel);
        RequestType reqType = getRequestType(requestType);
        String currentThYear = String.valueOf(DateTimeUtils.getCurrentThaiYear()).substring(2);
        String tableName = "CON_TB_REFUND_REQUEST";
        return sequenceGeneratorService.generateRunningNo(RequestTopic.M33_RF11, reqChannel, reqType, depCode, currentThYear, tableName);
    }

    @Transactional
    public List<SplitEmployeeRefundCompanyPeriodBean> updateSplit(List<SplitEmployeeRefundBean> splitEmployeeList
            , List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList, Long companyId, String accessToken) throws Exception {
        log.info("set split employee");
        for (SplitEmployeeRefundCompanyPeriodBean employee :splitEmployeePeriodList){
            SplitEmployeeRefundBean splitEmployee = splitEmployeeList
                    .stream()
                    .filter(rec -> employee.getInsurerId().equals(rec.getInsurerId()))
                    .findAny()
                    .orElse(null);
            log.debug("filter InsurerId");
            if (splitEmployee != null) {
                log.debug("filterSplitEmployee isNotNull");
                employee.setReceiveNo(splitEmployee.getReceiveNo());
                employee.setRefundTypePlace(splitEmployee.getRefundTypePlace());
                employee.setRefundPoOrder(splitEmployee.getRefundPoOrder());
                employee.setRefundPoOrderName(splitEmployee.getRefundPoOrderName());
                employee.setRefundPromptPay(splitEmployee.getRefundPromptPay());
                employee.setRefundBankId(splitEmployee.getRefundBankId());
                employee.setRefundBankBranchId(splitEmployee.getRefundBankBranchId());
                employee.setRefundBankBranchCode(splitEmployee.getRefundBankBranchCode());
                employee.setRefundBankAccount(splitEmployee.getRefundBankAccount());
                employee.setDistCode(splitEmployee.getDistCode());
                employee.setSubDistCode(splitEmployee.getSubDistCode());
                employee.setProvinceCode(splitEmployee.getProvinceCode());

                RateInformationBean rate = findRateEmployee(companyId, employee.getPayPeriod(), employee.getRequestTotalAmount(), accessToken);
                mapRate(employee, rate);
                log.debug("setValue employee success");
            }
        }
        log.info("set split employee");
        return splitEmployeePeriodList;
    }

    @Transactional
    public List<SplitEmployeeRefundCompanyPeriodBean> searchRefundSplitEmployeePeriod(SearchSplitEmployeeRefundRequest request,
                                                                                      String departmentReceiveCode, String requestType,
                                                                                      String requestChannel, String accessToken) throws Exception {
        log.info("Start searchRefundSplitEmployeePeriod");
        List<SplitEmployeeRefundBean> splitEmployeeList = splitEmployeeRefundDao.getSplitEmployee(request.getRefundRequestId());
        for (SplitEmployeeRefundBean splitEmployee :splitEmployeeList) {
            String receiveNo = generateRunningNo(departmentReceiveCode, requestType, requestChannel);
            splitEmployee.setReceiveNo(receiveNo);
        }
        List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList = new ArrayList<>();
        splitEmployeePeriodList = splitEmployeeRefundDao.getSplitEmployeeRefundPeriod(request.getRefundRequestId());
        if (!CollectionUtils.isEmpty(splitEmployeePeriodList)) {
            splitEmployeePeriodList = updateSplit(splitEmployeeList, splitEmployeePeriodList, request.getCompanyId(), accessToken);
        } else {
            splitEmployeePeriodList = splitEmployeeRefundDao.getTbSplitEmployeeRefund(request.getRefundRequestId());
            splitEmployeePeriodList = updateSplit(splitEmployeeList, splitEmployeePeriodList, request.getCompanyId(), accessToken);
        }
        splitEmployeePeriodList.stream().filter(x -> !StringUtils.isEmpty(x.getReceiveNo())).sorted((o1, o2) -> o1.getReceiveNo().compareTo(o2.getReceiveNo())).collect(Collectors.toList());
        log.debug("sort by ReceiveNo");

        log.info("End searchRefundSplitEmployeePeriod");
        return splitEmployeePeriodList;
    }
    

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SplitEmployeeRefundCompanyPeriodBean> searchSplitEmployeePeriod(SearchSplitEmployeeRefundRequest request,
                                                                   String departmentReceiveCode, String requestType,
                                                                   String requestChannel, String accessToken) throws Exception {
        log.debug("Start searchSplitEmployeePeriod ");
        try {

            List<SplitEmployeeRefundBean> splitEmployeeRefundList = splitEmployeeRefundDao.getSplitEmployee(request.getRefundRequestId());
            log.debug("searchSplitEmployeeFilterReceiveNo");
            for (SplitEmployeeRefundBean employeeRefund :splitEmployeeRefundList) {
                if ((employeeRefund.getReceiveNo() == null || StringUtils.isEmpty(employeeRefund.getReceiveNo()))) {
                    log.debug("Start ReceiveNo isNull");
                    RequestChannel reqChannel = getRequestChanel(requestChannel);
                    RequestType reqType = getRequestType(requestType);
                    String depCode = departmentReceiveCode;
                    String currentThYear = String.valueOf(DateTimeUtils.getCurrentThaiYear()).substring(2);
                    String tbName = "CON_TB_REFUND_REQUEST";
                    String receiveNo = (sequenceGeneratorService.generateRunningNo(RequestTopic.M33_RF11, reqChannel, reqType, depCode, currentThYear, tbName));
                    log.debug("ReceiveNo isNull generateRunningNo success");
                    employeeRefund.setReceiveNo(receiveNo);
                    log.debug("setReceiveNo");
                }
            }
            List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeePeriodList = new ArrayList<>();

//            splitEmployeePeriodList = splitEmployeeRefundDao.getSplitEmployeeRefund(request.getRefundRequestId(),null);
            splitEmployeePeriodList = splitEmployeeRefundDao.getSplitEmployeeRefundPeriod(request.getRefundRequestId());
            log.debug("searchSplitEmployeePeriod");
            if (!CollectionUtils.isEmpty(splitEmployeePeriodList)) {
                for (SplitEmployeeRefundCompanyPeriodBean employeePeriod :splitEmployeePeriodList){
                    SplitEmployeeRefundBean splitEmployee = splitEmployeeRefundList.stream()
                            .filter(rec -> employeePeriod.getInsurerId().equals(rec.getInsurerId())).findAny().orElse(null);
                    log.debug("filter InsurerId");
                    if (splitEmployee != null){
                        log.debug("filterSplitEmployee isNotNull");
                        employeePeriod.setReceiveNo(splitEmployee.getReceiveNo());
                        employeePeriod.setRefundTypePlace(splitEmployee.getRefundTypePlace());
                        employeePeriod.setRefundPoOrder(splitEmployee.getRefundPoOrder());
                        employeePeriod.setRefundPoOrderName(splitEmployee.getRefundPoOrderName());
                        employeePeriod.setRefundPromptPay(splitEmployee.getRefundPromptPay());
                        employeePeriod.setRefundBankId(splitEmployee.getRefundBankId());
                        employeePeriod.setRefundBankCode(splitEmployee.getRefundBankCode());
                        employeePeriod.setRefundBankBranchId(splitEmployee.getRefundBankBranchId());
                        employeePeriod.setRefundBankBranchCode(splitEmployee.getRefundBankBranchCode());
                        employeePeriod.setRefundBankAccount(splitEmployee.getRefundBankAccount());
                        employeePeriod.setDistCode(splitEmployee.getDistCode());
                        employeePeriod.setSubDistCode(splitEmployee.getSubDistCode());
                        employeePeriod.setProvinceCode(splitEmployee.getProvinceCode());
                        log.debug("setValue employeePeriod success");
                    }
                }
            } else {
                splitEmployeePeriodList = splitEmployeeRefundDao.getTbSplitEmployeeRefund(request.getRefundRequestId());
                for (SplitEmployeeRefundCompanyPeriodBean employeePeriod :splitEmployeePeriodList){
                    SplitEmployeeRefundBean splitEmployee = splitEmployeeRefundList.stream()
                            .filter(rec -> employeePeriod.getInsurerId().equals(rec.getInsurerId())).findAny().orElse(null);
                    log.debug("filter InsurerId");
                    if (splitEmployee != null){
                        log.debug("filterSplitEmployee isNotNull");
                        //map rate period company
                        employeePeriod.setReceiveNo(splitEmployee.getReceiveNo());
                        employeePeriod.setRefundTypePlace(splitEmployee.getRefundTypePlace());
                        employeePeriod.setRefundPoOrder(splitEmployee.getRefundPoOrder());
                        employeePeriod.setRefundPoOrderName(splitEmployee.getRefundPoOrderName());
                        employeePeriod.setRefundPromptPay(splitEmployee.getRefundPromptPay());
                        employeePeriod.setRefundBankId(splitEmployee.getRefundBankId());
                        employeePeriod.setRefundBankBranchId(splitEmployee.getRefundBankBranchId());
                        employeePeriod.setRefundBankBranchCode(splitEmployee.getRefundBankBranchCode());
                        employeePeriod.setRefundBankAccount(splitEmployee.getRefundBankAccount());
                        employeePeriod.setDistCode(splitEmployee.getDistCode());
                        employeePeriod.setSubDistCode(splitEmployee.getSubDistCode());
                        employeePeriod.setProvinceCode(splitEmployee.getProvinceCode());

//                        RateInformationBean rate = findRateEmployee(request.getCompanyId(), employeePeriod.getPayPeriod(), employeePeriod.getRequestTotalAmount(), accessToken);
//                        mapRate(employeePeriod, rate);
                        log.debug("setValue employeePeriod success");
                    }
                }
            }

            splitEmployeePeriodList.sort((o1, o2) -> o1.getReceiveNo().compareTo(o2.getReceiveNo()));
//            splitEmployeePeriodList.stream().filter(x -> !StringUtils.isEmpty(x.getReceiveNo())).sorted((o1, o2) -> o1.getReceiveNo().compareTo(o2.getReceiveNo())).collect(Collectors.toList());
            log.debug("sort by ReceiveNo");
            return splitEmployeePeriodList;

        }catch (Exception e) {
            log.error("Error searchSplitEmployeePeriod : {}", e.getMessage());
        }
        log.debug("End searchSplitEmployeePeriod ");
        return null;
    }

    //method เก่าเก็บไว้ก่อนเผื่อ user ต้องการเปลี่ยนกลับมาใช้
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SplitEmployeeRefundBean> searchSplitEmployeeRefundPeriod(SearchSplitEmployeeRefundRequest request,
                                                                                      String departmentReceiveCode, String requestType,
                                                                                      String requestChannel) throws Exception{
        log.debug("Start SplitEmployeeRefundPeriod ");
        try {
            List<SplitEmployeeRefundBean> splitEmployeeRefundList = splitEmployeeRefundDao.getSplitEmployee(request.getRefundRequestId());
            log.debug("splitEmployeeRefundList");
            List<SplitEmployeeRefundCompanyPeriodBean> splitEmployeeRefundCompanyPeriodList = splitEmployeeRefundDao.getSplitEmployeeRefund(request.getRefundRequestId(),null);
            log.debug("splitEmployeeRefundCompanyPeriodList");
            for (SplitEmployeeRefundBean employeeRefund : splitEmployeeRefundList) {

                if (employeeRefund.getReceiveNo() == null || StringUtils.isEmpty(employeeRefund.getReceiveNo())) {
                    RequestChannel reqChannel = getRequestChanel(requestChannel);
                    RequestType reqType = getRequestType(requestType);
                    String depCode = departmentReceiveCode;
                    String currentThYear = String.valueOf(DateTimeUtils.getCurrentThaiYear()).substring(2);
                    String tbName = "CON_TB_REFUND_REQUEST";
                    String receiveNo = (sequenceGeneratorService.generateRunningNo(RequestTopic.M33_RF01, reqChannel, reqType, depCode, currentThYear, tbName));
                    employeeRefund.setReceiveNo(receiveNo);
                    log.debug("ReceiveNo is null generateRunningNo");
                }
                if (!NumberUtils.isZeroOrNull(employeeRefund.getRefundRequestId())){
                    SplitEmployeeRefundBean trRefundEmployee = splitEmployeeRefundDao.getConTrRefundEmployee(employeeRefund.getRefundRequestId());

                    if (!ObjectUtils.isObjectNull(trRefundEmployee)) {
                        employeeRefund.setRefundEmployeeId(trRefundEmployee.getRefundEmployeeId());
                        employeeRefund.setRefundSicknessAmount(trRefundEmployee.getRefundSicknessAmount());
                        employeeRefund.setRefundRetirementAmount(trRefundEmployee.getRefundRetirementAmount());
                        employeeRefund.setRefundUnemploymentAmount(trRefundEmployee.getRefundUnemploymentAmount());
                        employeeRefund.setRequestEmpeAmount(trRefundEmployee.getRequestEmpeAmount());
                        employeeRefund.setRequestTotalAmount(trRefundEmployee.getRequestTotalAmount());
                    }
                }
                List<SplitEmployeeRefundCompanyPeriodBean> periodList = splitEmployeeRefundDao.getSplitEmployeeRefund(request.getRefundRequestId(),employeeRefund.getInsurerId());
                employeeRefund.setSplitEmployeePeriodList(periodList);
                log.debug("setSplitEmployeePeriodList success");
            }

            BigDecimal empeTotal = BigDecimal.ZERO;
            BigDecimal refundEmpeTotal = BigDecimal.ZERO;
            for (SplitEmployeeRefundCompanyPeriodBean splitEmployeeRefundPeriod : splitEmployeeRefundCompanyPeriodList) {
                empeTotal = empeTotal.add(splitEmployeeRefundPeriod.getRequestEmpeAmount());
                refundEmpeTotal = refundEmpeTotal.add(splitEmployeeRefundPeriod.getRefundEmpeAmount());
            }
            // set value in RequestDetail
            SplitEmployeeRefundCompanyRequestBean splitEmployeeRefund = searchSplitEmployeeRefund(request);
            splitEmployeeRefund.setRequestEmpeAmount(empeTotal);
            splitEmployeeRefund.setRequestTotalEmpeAmount(empeTotal);
            splitEmployeeRefund.setRequestTotalAmount(splitEmployeeRefund.getRequestTotalEmpeAmount());
            splitEmployeeRefund.setRefundEmpeAmount(refundEmpeTotal);
            splitEmployeeRefund.setRefundTotalEmpeAmount(refundEmpeTotal);
            splitEmployeeRefund.setRefundTotalAmount(splitEmployeeRefund.getRefundTotalEmpeAmount());
            return splitEmployeeRefundList;
        } catch (Exception e) {
            log.error("Error SplitEmployeeRefundPeriod : {}", e.getMessage());
        }
        log.debug("End SplitEmployeeRefundPeriod ");
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RefundCompanyPeriodBean> searchRefundPeriod(SearchSplitEmployeeRefundRequest request) throws Exception {

        Long companyId = request.getCompanyId();
        Long refundRequestId = request.getRefundRequestId();

        return refundCompanyPeriodDao.searchRefundRequestId(refundRequestId, companyId);
    }

    private RequestChannel getRequestChanel(String requestChannel){
        RequestChannel value = null;
        if (requestChannel != null){
            switch (requestChannel){
                case "00":
                    value = RequestChannel.SSO;
                    break;
                case "01":
                    value = RequestChannel.POST;
                    break;
                case "02":
                    value = RequestChannel.EMAIL;
                    break;
            }
        }else {
            throw new RuntimeException("channel must not be null ");
        }

        return value;
    }
    private RequestType getRequestType(String requestType){
        RequestType value = null;
        if (requestType != null){
            switch (requestType){
                case "00":
                    value = RequestType.DOCUMENT;
                    break;
                case "01":
                    value = RequestType.SEND_MAIL;
                    break;
                case "02":
                    value = RequestType.E_DOCUMENT;
                    break;
            }
        }else {
            throw new RuntimeException("type must not be null ");
        }

        return value;
    }

    private RateInformationBean findRateEmployee(Long companyId, String payPeriod, BigDecimal requestTotalAmount, String accessToken) throws Exception {
        return calRateService.getRateEmployee(companyId
                , payPeriod
                , requestTotalAmount
                , accessToken);
    }

    private void mapRate(SplitEmployeeRefundCompanyPeriodBean receiveData, RateInformationBean rate) {
        receiveData.setRefundSicknessRate(rate.getSickness());
        receiveData.setRefundRetirementRate(rate.getRetirement());
        receiveData.setRefundUnemploymentRate(rate.getUnemployment());
        receiveData.setRefundMaternityRate(rate.getMaternity());
        receiveData.setRefundInvalidityRate(rate.getInvalidity());
        receiveData.setRefundChildAllowanceRate(rate.getChildAllowance());
        receiveData.setRefundDeathRate(rate.getDeath());
        receiveData.setRefundSicknessAmount(rate.getSicknessAmount());
        receiveData.setRefundRetirementAmount(rate.getRetirementAmount());
        receiveData.setRefundUnemploymentAmount(rate.getUnemploymentAmount());
    }
}
