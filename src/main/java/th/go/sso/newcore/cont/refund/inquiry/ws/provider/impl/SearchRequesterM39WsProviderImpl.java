package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.dto.SearchResponse;
import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.business.ConTbRequesterInsurerServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequesterM39Request;

@Component
@RequestScope
public class SearchRequesterM39WsProviderImpl extends WebServiceBase<SearchRequesterM39Request, SearchResponse> {

    @Autowired
    private ConTbRequesterInsurerServiceImpl conTbRequesterInsurerService;


    public SearchRequesterM39WsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(SearchRequesterM39Request request) throws Exception {

        if (request == null) {
            return getResponseMessageByCode("BE0006");
        }

        return null;
    }

    @Override
    protected SearchResponse implement(SearchRequesterM39Request request) throws Exception {
    	
    	SearchResponse response = new SearchResponse();
		
    	try {
			
			ConTbRequesterBean result = conTbRequesterInsurerService.getConTbRequester39ById(request.getRequesterId());
			if(result != null) {
				response.setResult(result);
				response.setStatus(ResponseStatus.SUCCESS);				
			}else {
				response.setStatus(ResponseStatus.DATA_NOT_FOUND);				
			}
		}catch (BusinessException e) {
			response.setStatus(ResponseStatus.BUSINESS_ERROR);		
		}catch (Exception e) {
			response.setStatus(ResponseStatus.ERROR);
		}
		return response;
    }
}
