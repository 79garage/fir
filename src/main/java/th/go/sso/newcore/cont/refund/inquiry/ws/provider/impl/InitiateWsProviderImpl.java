package th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.base.WebServiceBase;
import th.go.sso.newcore.cont.common.constant.ResponseStatus;
import th.go.sso.newcore.cont.common.dto.RequestBean;
import th.go.sso.newcore.cont.common.dto.ResponseBean;
import th.go.sso.newcore.cont.common.utils.handler.MessageHandler;
import th.go.sso.newcore.cont.refund.inquiry.business.SystemCommonServiceImpl;

@Component
@RequestScope
public class InitiateWsProviderImpl extends WebServiceBase<RequestBean, ResponseBean> {
    private static final Logger log = LoggerFactory.getLogger(InitiateWsProviderImpl.class);
    @Autowired
    private SystemCommonServiceImpl systemCommonService;

    public InitiateWsProviderImpl(MessageHandler messageHandler) {
        super(messageHandler);
    }

    @Override
    protected ResponseBean validate(RequestBean request) throws Exception {
        return null;
    }

    @Override
    protected ResponseBean implement(RequestBean request) throws Exception {
        ResponseBean response = new ResponseBean(ResponseStatus.SUCCESS);
        try {
            log.debug("INITIATE START: by username: {}", getUser().getUsername());
            systemCommonService.reloadSystemConfig();
            response.setResult(true);
        } catch (Exception e) {
            log.error("InitiateWsImpl " + e.getMessage());
            response.setStatus(ResponseStatus.ERROR);
            response.setErrorMsg(e.getMessage());
        }
        return response;
    }

}
