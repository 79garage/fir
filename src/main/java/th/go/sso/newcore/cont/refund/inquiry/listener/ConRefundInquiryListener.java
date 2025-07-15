package th.go.sso.newcore.cont.refund.inquiry.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import th.go.sso.newcore.cont.common.cache.SsoRealmDataCache;
import th.go.sso.newcore.cont.refund.inquiry.business.SystemCommonServiceImpl;
import th.go.sso.newcore.cont.refund.inquiry.config.AppConfig;

@Component
@ApplicationScope
public class ConRefundInquiryListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(ConRefundInquiryListener.class);

    @Value("${server.servlet.contextPath}")
    private String contextPath;
    @Autowired
    private SsoRealmDataCache ssoRealmDataCache;
    @Autowired
    private SystemCommonServiceImpl systemCommonService;
    @Autowired
    private AppConfig appConfig;

    @Override
    public void contextInitialized(ServletContextEvent e) {
        try {
            systemCommonService.reloadSystemConfig();
            ssoRealmDataCache.addKeycloakConfig(appConfig.getKeycloakPublicKey(), appConfig.isEnableKeycloakPublicKey());
        } catch (Exception ex) {
            log.error("ERROR contextInitialized {}", ex.getMessage());
        }
        log.info("ConRefundInquiryListener ServletContext Initialized on Context path : {} ", contextPath);
    }
}
