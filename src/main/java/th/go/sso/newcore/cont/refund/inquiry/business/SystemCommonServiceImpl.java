package th.go.sso.newcore.cont.refund.inquiry.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.business.ISystemCommonService;
import th.go.sso.newcore.cont.common.dto.BusinessMessageBean;
import th.go.sso.newcore.cont.common.redis.entity.AuthenEntity;
import th.go.sso.newcore.cont.refund.inquiry.config.AppConfig;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbBusinessMessageImpl;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.ConTbConfigDaoImpl;
import th.go.sso.newcore.cont.refund.inquiry.redis.data.impl.AuthenDataImpl;

@Service
public class SystemCommonServiceImpl implements ISystemCommonService {

    private static final Logger log = LoggerFactory.getLogger(SystemCommonServiceImpl.class);

    @Autowired
    private ConTbBusinessMessageImpl conTbBusinessMessageImpl;
    @Autowired
    private AuthenDataImpl authenDataImpl;
    @Autowired
    private ConTbConfigDaoImpl conTbConfigDao;
    @Autowired
    private AppConfig appConfig;

    
    @Transactional(propagation = Propagation.SUPPORTS)
    public BusinessMessageBean getMessageByCode(String code) throws Exception {
        try {
            return conTbBusinessMessageImpl.getBusinessMessageByCode(code);
        } catch (Exception e) {
            log.error("getMessageByCode : {}", e.getMessage());
        }

        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void reloadSystemConfig() throws Exception {
        appConfig.setIpSsoMasterInq(conTbConfigDao.queryValueByConfigName("IP_SSO_MASTER_INQ"));
        boolean enablePublicKey = Boolean.parseBoolean(conTbConfigDao.queryValueByConfigName("ENABLE_KEYCLOAK_PUBLIC_KEY"));
        appConfig.setKeycloakPublicKey(conTbConfigDao.queryValueByConfigName("KEYCLOAK_PUBLIC_KEY"));
        appConfig.setEnableKeycloakPublicKey(enablePublicKey);
        log.debug("reloadSystemConfig SUCCESS: enablePublicKey: {}", enablePublicKey);
    }

    @Override
    public AuthenEntity findByPreferredUsername(String username) throws Exception {
        return authenDataImpl.findByPreferredUsername(username);
    }

    @Override
    public AuthenEntity addAuthenInfo(AuthenEntity entity) throws Exception {
        return authenDataImpl.save(entity);
    }

}
