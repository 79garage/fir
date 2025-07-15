package th.go.sso.newcore.cont.refund.inquiry.ws.requestor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.common.utils.WebServiceUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.RateBean;
import th.go.sso.newcore.cont.refund.inquiry.config.AppConfig;
import th.go.sso.newcore.cont.refund.inquiry.ws.requestor.request.SearchCompanyRateRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.requestor.request.SearchInsurerRateRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.requestor.response.SearchRateResponse;

@Component
@RequestScope
public class SearchRateImpl {
    private static final Logger log = LoggerFactory.getLogger(SearchRateImpl.class);
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private RestTemplate restTemplate;

    public RateBean submitRateCompany(SearchCompanyRateRequest requestList, String token) throws Exception {
        final String url = appConfig.getIpSsoMasterInq() + "/search-company-rate";
        log.debug("CALL FORWARD TO MASTER-INQ : " + url);
        HttpEntity<SearchCompanyRateRequest> requestEntity = new HttpEntity<>(requestList, getDefaultHeaders(token));
        HttpEntity<SearchRateResponse> body = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SearchRateResponse.class);
        SearchRateResponse response = body.getBody();
        log.debug("response : {}", WebServiceUtils.toJson(body.getBody()));
        RateBean bean = new RateBean();
        if (response != null) {
            bean  = response.getResult();
        }
        return bean;
    }

    public RateBean submitRateInsurer(SearchInsurerRateRequest requestList, String token) throws Exception {
        final String url = appConfig.getIpSsoMasterInq() + "/search-insurer-rate";
        log.debug("CALL FORWARD TO MASTER-INQ : " + url);
        HttpEntity<SearchInsurerRateRequest> requestEntity = new HttpEntity<>(requestList, getDefaultHeaders(token));
        HttpEntity<SearchRateResponse> body = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SearchRateResponse.class);
        SearchRateResponse response = body.getBody();
        log.debug("response : {}", WebServiceUtils.toJson(body.getBody()));
        RateBean bean = new RateBean();
        if (response != null) {
            bean  = response.getResult();
        }
        return bean;
    }

    private MultiValueMap<String, String> getDefaultHeaders(String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("authorization", "Bearer " + token);
        return headers;
    }
}
