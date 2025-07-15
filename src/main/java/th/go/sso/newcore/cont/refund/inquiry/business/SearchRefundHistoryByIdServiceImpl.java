package th.go.sso.newcore.cont.refund.inquiry.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.*;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.*;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundHistoryByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchRefundHistoryByIdResponse;

import java.util.ArrayList;
import java.util.List;

import static th.go.sso.newcore.cont.common.constant.type.RefundChangeType.*;

@Service
public class SearchRefundHistoryByIdServiceImpl {
    private final static Logger log = LoggerFactory.getLogger(SearchRefundHistoryServiceImpl.class);

    @Autowired
    private ConTbRefundRequestDaoImpl conTbRefundRequestDao;
    @Autowired
    private SelectedEmployeeExistsDaoImpl selectedEmployeeExistsDao;
    @Autowired
    private SelectedReceiptExistsDaoImpl conTbReceiptExistsDao;
    @Autowired
    private RefundInsurerSelectedRefundRequestReceiptDaoImpl selectedInsurerExistsDao;
    @Autowired
    private ConTbRefundPeriodDaoImpl conTbRefundPeriodDao;
    @Autowired
    private ConTbRefundRequestChangeDaoImpl conTbRefundRequestChangeDao;

    @Autowired
    private ConTbRefundChangeEmployerDaoImpl conTbRefundChangeEmployerDao;
    @Autowired
    private ConTbRefundChangeEmployerBranchDaoImpl conTbRefundChangeEmployerBranchDao;
    @Autowired
    private ConTbRefundChangeEmployerPeriodDaoImpl conTbRefundChangeEmployerPeriodDao;
    @Autowired
    private ConTbRefundChangeEmployerIdentifierDaoImpl conTbRefundChangeEmployerIdentifierDao;
    @Autowired
    private ConTbRefundChangeEmployeeDaoImpl conTbRefundChangeEmployeeDao;
    @Autowired
    private ConTbRefundChangeEmployeeReplyDaoImpl conTbRefundChangeEmployeeReplyDao;
    @Autowired
    private ConTbRefundChangeEmployeeRequestDaoImpl conTbRefundChangeEmployeeRequestDao;
    @Autowired
    private ConTbRefundChangeEmployeeBranchPeriodDaoImpl conTbRefundChangeEmployeeBranchPeriodDao;
    @Autowired
    private ConTbRefundChangeEmployeeIdentifierDaoImpl conTbRefundChangeEmployeeIdentifierDao;
    @Autowired
    private ConTbRefundChangeEligibleDaoImpl conTbRefundChangeEligibleDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RefundRequestBean getRefundDetail(Long refundRequestId, Long refundRequestChangeTypeId) throws Exception {
        return conTbRefundRequestDao.searchConTbRefundRequestByRefundRequestId(refundRequestId, refundRequestChangeTypeId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public SearchRefundHistoryByIdResponse getRefundHistoryById(SearchRefundHistoryByIdRequest request) throws Exception {
        log.debug("START getRefundHistoryById");
        RefundPeriodActiveAndInactiveBean cancelAndDelete = new RefundPeriodActiveAndInactiveBean();
        ConTbRefundRequestChangeBean edit = new ConTbRefundRequestChangeBean();
        SearchRefundHistoryByIdResponse response = new SearchRefundHistoryByIdResponse();
        if (List.of("C", "D", "P").contains(request.getRefundChangeType())) {
            cancelAndDelete = mapRefundPeriodActiveAndInactive(request.getRefundRequestId());
            response.setStatus("0");
        } else {
            edit = mapConTbRefundRequestChange(request.getRefundRequestChangeTypeId());
            response.setStatus("1");

            setResponseByRefundChangeType(response, request.getRefundRequestChangeTypeId(), request.getRefundRequestId(), request.getRefundChangeType());
        }

        response.setCaseCancelAndDelete(cancelAndDelete);
        response.setCaseEdit(edit);
        log.debug("END getRefundHistoryById");
        return response;
    }

    public void setResponseByRefundChangeType(SearchRefundHistoryByIdResponse response, Long refundRequestChangeTypeId
            , Long refundRequestId, String refundChangeType) throws Exception {
        if (REFUND_EDIT_01.getValue().equals(refundChangeType)) {
            response.setEditingEmployerChangeType01(conTbRefundChangeEmployerDao.queryRefundChangeEmployer(refundRequestChangeTypeId));
        } else if (REFUND_EDIT_02.getValue().equals(refundChangeType)) {
            response.setEditingEmployerBranchChangeType02(conTbRefundChangeEmployerBranchDao.queryRefundChangeEmployer(refundRequestChangeTypeId));
        } else if (REFUND_EDIT_03.getValue().equals(refundChangeType)) {
            response.setEditingEmployerPeriodChangeType03(conTbRefundChangeEmployerPeriodDao.queryRefundChangeEmployerPeriod(refundRequestChangeTypeId));
        } else if (REFUND_EDIT_04.getValue().equals(refundChangeType)) {
            response.setEditingEmployeeChangeType04(conTbRefundChangeEmployeeDao.queryRefundChangeEmployee(refundRequestChangeTypeId));
        } else if (REFUND_EDIT_05.getValue().equals(refundChangeType)) {
            response.setEditingEmployeeReplyChangeType05(conTbRefundChangeEmployeeReplyDao.queryRefundChangeEmployeeReply(refundRequestChangeTypeId));
        } else if (REFUND_EDIT_06.getValue().equals(refundChangeType)) {
            response.setEditingEmployeeRequestChangeType06(conTbRefundChangeEmployeeRequestDao.queryRefundChangeEmployeeRequest(refundRequestChangeTypeId));
        } else if (List.of(REFUND_EDIT_07.getValue(), REFUND_EDIT_08.getValue()).contains(refundChangeType)) {
            response.setEditingEmployeeBranchPeriodChangeType0708(conTbRefundChangeEmployeeBranchPeriodDao.queryRefundChangeEmployeeBranchPeriod(refundRequestChangeTypeId));
        } else if (REFUND_EDIT_09.getValue().equals(refundChangeType)) {
            response.setEditingEmployerIdentifierChangeType09(conTbRefundChangeEmployerIdentifierDao.queryRefundChangeEmployerIdentifier(refundRequestChangeTypeId));
        } else if (REFUND_EDIT_A.getValue().equals(refundChangeType)) {
            response.setEditingEmployeeIdentifierChangeTypeA(conTbRefundChangeEmployeeIdentifierDao.queryRefundChangeEmployeeIdentifier(refundRequestChangeTypeId));
        } else if (REFUND_EDIT_B.getValue().equals(refundChangeType)) {
            response.setEditingEligibleChangeTypeB(conTbRefundChangeEligibleDao.queryRefundChangeEligible(refundRequestChangeTypeId));
        }
    }

    public ConTbRefundRequestChangeBean mapConTbRefundRequestChange(Long refundRequestChangeId) throws Exception {
        return conTbRefundRequestChangeDao.queryConTbRefundRequestChange(refundRequestChangeId);
    }

    public RefundPeriodActiveAndInactiveBean mapRefundPeriodActiveAndInactive(Long refundRequestId) throws Exception {
        log.debug("Start mapRefundPeriodActiveAndInactive");
        RefundPeriodActiveAndInactiveBean bean = new RefundPeriodActiveAndInactiveBean();
        List<RefundPeriodActiveBean> activeList = new ArrayList<>();
        List<RefundPeriodActiveBean> inactiveList = new ArrayList<>();
        log.debug("Start searchRefundPeriod");
        List<RefundPeriodActiveBean> refundPeriodList = searchRefundPeriod(refundRequestId);
        log.debug("End searchRefundPeriod");
        for (RefundPeriodActiveBean refundPeriod : refundPeriodList) {
            if (StringUtils.isEmpty(refundPeriod.getStatus())) {
                if (!NumberUtils.isZeroOrNull(refundPeriod.getReceiveCompanyId())) {
                    log.debug(" ReceiveCompanyId not empty");
                    List<SelectedEmployeeBean> selectedEmployeeList = selectedEmployeeExistsDao.findSelectedEmployee(refundPeriod.getRefundPeriodId());
                    log.debug("Query SelectedEmployeeList Success");
                    List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.searchReceiptExists(refundPeriod.getReceiveCompanyId());
                    log.debug("Query SelectedReceiptList Success");
                    refundPeriod.setSelectedEmployeeList(selectedEmployeeList);
                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
                    log.debug("setSelected 33");
                } else if (!NumberUtils.isZeroOrNull(refundPeriod.getReceiveInsurerId())) {
                    List<SelectedReceiptBean> selectedReceiptList = selectedInsurerExistsDao.findRefundRequestReceipt(refundPeriod.getReceiveInsurerId());
                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
                    log.debug("setSelectedReceipt 39");
                } else {
                    List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.searchReceiptExistsMultiple(refundPeriod.getRefundPeriodId());
                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
                    log.debug("setSelectedReceiptMultiple");
                }
                List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.queryRefundReceiptByRefundPeriodId(refundPeriod.getRefundPeriodId());
                refundPeriod.setSelectedReceiptList(selectedReceiptList);
                activeList.add(refundPeriod);
                log.debug("add active");
            } else if (refundPeriod.getStatus().equals("D") || refundPeriod.getStatus().equals("C")) {
                if (!NumberUtils.isZeroOrNull(refundPeriod.getRefundPeriodId())) {
                    if (!NumberUtils.isZeroOrNull(refundPeriod.getReceiveCompanyId())) {
                        log.debug(" ReceiveCompanyId not empty");
                        List<SelectedEmployeeBean> selectedEmployeeList = selectedEmployeeExistsDao.findSelectedEmployee(refundPeriod.getRefundPeriodId());
                        log.debug("Query SelectedEmployeeList Success");
                        refundPeriod.setSelectedEmployeeList(selectedEmployeeList);
                    }
                    List<SelectedReceiptBean> selectedReceiptList = conTbReceiptExistsDao.queryRefundReceiptByRefundPeriodId(refundPeriod.getRefundPeriodId());
                    refundPeriod.setSelectedReceiptList(selectedReceiptList);
                }
                inactiveList.add(refundPeriod);
                log.debug("add inActive");
            }
        }
        bean.setRefundPeriodActiveList(activeList);
        bean.setRefundPeriodInactiveList(inactiveList);

        return bean;
    }

    private List<RefundPeriodActiveBean> searchRefundPeriod(Long refundRequestId) throws Exception {
        return conTbRefundPeriodDao.searchRefundPeriod(refundRequestId);
    }
}
