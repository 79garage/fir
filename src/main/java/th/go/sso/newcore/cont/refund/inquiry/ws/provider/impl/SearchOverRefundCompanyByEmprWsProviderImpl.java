package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.dto.SearchResponse;
import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConMsCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.OverRefundCompanyBean;
import th.go.sso.newcore.cont.refund.inquiry.business.OverRefundCompanyByEmprServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundCompanyByEmprRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.response.SearchOverRefundCompanyByEmprResponse;


@Component
@RequestScope
public class SearchOverRefundCompanyByEmprWsProviderImpl extends WebServiceBase<SearchOverRefundCompanyByEmprRequest, SearchResponse>{
    private static final Logger log = LoggerFactory.getLogger(SearchOverRefundCompanyByEmprWsProviderImpl.class);

    @Autowired
    private OverRefundCompanyByEmprServiceImpl overRefundCompanyByEmprService;

    @Autowired
    public SearchOverRefundCompanyByEmprWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchOverRefundCompanyByEmprRequest request) throws Exception {

        if (request == null){
            return getResponseMessageByCode("Request");
        }
        if (!StringUtils.isEmpty(request.getReceiveNo())) {
            request.setReceiveNo(request.getReceiveNo().toUpperCase());
            request.setStartPeriodMonth(null);
            request.setStartPeriodYear(null);
            request.setEndPeriodMonth(null);
            request.setEndPeriodYear(null);
            return null;
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
    protected SearchResponse implement(SearchOverRefundCompanyByEmprRequest request) throws Exception {
        SearchResponse response = new SearchResponse();
        try {
            ConMsCompanyBean companyDetail = overRefundCompanyByEmprService.searchCompany(request);
            if (!StringUtils.isEmpty(request.getReceiveNo()) && ObjectUtils.isObjectEmpty(companyDetail)){
                response.setStatus(ResponseStatus.BUSINESS_ERROR);
                response.setErrorMsg(getResponseMessageByCode("BS0010", request.getReceiveNo()).getErrorMsg());
                return response;
            } else if (ObjectUtils.isObjectNull(companyDetail)) {
                throw new BusinessException("ไม่พบข้อมูลสถานประกอบการ");
            }
            if (!ObjectUtils.isObjectEmpty(companyDetail)) {
                List<OverRefundCompanyBean> refundCompanyList = overRefundCompanyByEmprService.searchRefundCompany(request
                        , !ObjectUtils.isObjectNull(request.getPaginable()) ? request.getPaginable() : null);
                SearchOverRefundCompanyByEmprResponse _response = new SearchOverRefundCompanyByEmprResponse();
                _response.setCompanyDetail(companyDetail);
                _response.setRefundCompanyList(refundCompanyList);

                response.setResult(_response);
                response.setStatus(ResponseStatus.SUCCESS);
            }
            return response;
        } catch (BusinessException be) {
            log.debug(be.getMessage());
            throw new BusinessException(be.getMessage());
        }catch (Exception e) {
            log.error("SearchOverRefundEmployee : ", e);
            response.setStatus(ResponseStatus.BUSINESS_ERROR);
            response.setErrorMsg(getResponseMessageByCode("BE0001").getErrorMsg());
            return response;
        }
//        ConMsCompanyBean companyDetail = overRefundCompanyByEmprService.searchCompany(request);
//
//        List<OverRefundCompanyBean> refundCompanyList = overRefundCompanyByEmprService.searchRefundCompany(request
//                , !ObjectUtils.isObjectNull(request.getPaginable()) ? request.getPaginable() : null);
////        List<OverRefundCompanyBean> refundCompanyList = overRefundCompanyByEmprService.searchRefundCompany(request);
//
//        SearchResponse response = new SearchResponse();
//        SearchOverRefundCompanyByEmprResponse _response = new SearchOverRefundCompanyByEmprResponse();
//        _response.setCompanyDetail(companyDetail);
//        _response.setRefundCompanyList(refundCompanyList);
//        if (!ObjectUtils.isObjectNull(companyDetail) || !refundCompanyList.isEmpty()) {
//            response.setResult(_response);
//            response.setStatus(ResponseStatus.SUCCESS);
//            if (!StringUtils.isEmpty(request.getReceiveNo()) && !ObjectUtils.isObjectNull(companyDetail)) {
////            if (!StringUtils.isEmpty(request.getReceiveNo()) && !ObjectUtils.isObjectNull(companyDetail) && refundCompanyList.isEmpty()) {
//                response.setStatus(ResponseStatus.BUSINESS_ERROR);
//                response.setErrorMsg(getResponseMessageByCode("BS0010", request.getReceiveNo()).getErrorMsg());
//                return response;
//            }
//        } else if (!StringUtils.isEmpty(request.getReceiveNo())) {
//            response.setStatus(ResponseStatus.BUSINESS_ERROR);
//            response.setErrorMsg(getResponseMessageByCode("BS0010", request.getReceiveNo()).getErrorMsg());
//            return response;
//        } else {
//            response.setStatus(ResponseStatus.BUSINESS_ERROR);
//            response.setErrorMsg(getResponseMessageByCode("BS0001").getErrorMsg());
//            return response;
//        }
//        return response;
    }
}
