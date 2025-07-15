package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsInsurerBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundEmployeeBean;
import th.go.sso.newcore.cont.refund.inquiry.business.SearchRefundEmployeeMultipleCompaniesServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundEmployeeRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchOverRefundEmployeeResponse;

@Component
@RequestScope
public class SearchOverRefundEmployeeWsProviderImpl
        extends WebServiceBase<SearchOverRefundEmployeeRequest, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(SearchOverRefundEmployeeWsProviderImpl.class);

    @Autowired
    private SearchRefundEmployeeMultipleCompaniesServiceImpl searchRefundEmployeeMultipleCompaniesService;

    public SearchOverRefundEmployeeWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchOverRefundEmployeeRequest request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }
        if (!StringUtils.isEmpty(request.getReceiveNo())) {
            request.setReceiveNo(request.getReceiveNo().toUpperCase());
            request.setStartPeriodMonth(null);
            request.setStartPeriodYear(null);
            request.setEndPeriodMonth(null);
            request.setEndPeriodYear(null);
            return null;
        }
        if (StringUtils.isEmpty(request.getIdCardNo())) {
            return getResponseMessageByCode("BE0000", "เลขบัตรประจำตัวประชาชน");
        }
        if (StringUtils.isEmpty(request.getProgressStatus())){
            return getResponseMessageByCode("BE0000","Progress Status");
        }
        if (StringUtils.isEmpty(request.getStartPeriodMonth())){
            return getResponseMessageByCode("BE0000","Start Period Month");
        }
        if (StringUtils.isEmpty(request.getStartPeriodYear())){
            return getResponseMessageByCode("BE0000","Start Period Year");
        }
        if (StringUtils.isEmpty(request.getEndPeriodMonth())){
            return getResponseMessageByCode("BE0000","End Period Month");
        }
        if (StringUtils.isEmpty(request.getEndPeriodYear())){
            return getResponseMessageByCode("BE0000","End Period Year");
        }
        return null;
    }

    @Override
    protected ResponseBean implement(SearchOverRefundEmployeeRequest request) throws Exception {
        try {
            ConMsInsurerBean insurerDetails = searchRefundEmployeeMultipleCompaniesService.searchEmployeeDetails(request.getIdCardNo(), request.getReceiveNo());
            if (ObjectUtils.isObjectNull(insurerDetails) && !StringUtils.isEmpty(request.getReceiveNo())) {
                return getResponseMessageByCode("BS0010", request.getReceiveNo());
            } else if (ObjectUtils.isObjectNull(insurerDetails)) {
                throw new BusinessException("ไม่พบข้อมูลผู้ประกันตน");
            }
            List<OverRefundEmployeeBean> periods = searchRefundEmployeeMultipleCompaniesService.searchEmployeeRefundPeriods(request, insurerDetails.getIdCardNo());
            SearchOverRefundEmployeeResponse refundEmployeeResponse = new SearchOverRefundEmployeeResponse();
            refundEmployeeResponse.setInsurerDetail(insurerDetails);
            refundEmployeeResponse.setRefundEmployeeList(periods);

            ResponseBean response = new ResponseBean();
            response.setResult(refundEmployeeResponse);
            response.setStatus(ResponseStatus.SUCCESS);
            return response;
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        }catch (Exception e) {
            log.error("SearchOverRefundEmployee : ", e);
            return getResponseMessageByCode("BE0001");
        }
    }
}
