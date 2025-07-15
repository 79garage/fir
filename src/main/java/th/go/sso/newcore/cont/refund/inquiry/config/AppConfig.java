package th.go.sso.newcore.cont.refund.inquiry.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Value("${redis.hostName:#{null}}")
	private String ssoRedisIp;
	@Value("${redis.port:#{null}}")
	private String ssoRedisPort;
	@Value("${redis.password:#{null}}")
	private String ssoRedisPassword;
	@Value("${redis.cluster.hosts:#{null}}")
	private String[] redisClusterIps;
	private String keycloakPublicKey;
	private boolean enableKeycloakPublicKey;
	private String ipSsoMasterInq;

	public String getSsoRedisIp() {
		return ssoRedisIp;
	}

	public String getSsoRedisPort() {
		return ssoRedisPort;
	}

	public String getSsoRedisPassword() {
		return ssoRedisPassword;
	}

	public String[] getRedisClusterIps() {
		return redisClusterIps;
	}

	public String getKeycloakPublicKey() {
		return keycloakPublicKey;
	}

	public void setKeycloakPublicKey(String keycloakPublicKey) {
		this.keycloakPublicKey = keycloakPublicKey;
	}

	public boolean isEnableKeycloakPublicKey() {
		return enableKeycloakPublicKey;
	}

	public void setEnableKeycloakPublicKey(boolean enableKeycloakPublicKey) {
		this.enableKeycloakPublicKey = enableKeycloakPublicKey;
	}

	public String getIpSsoMasterInq() {
		return ipSsoMasterInq;
	}

	public void setIpSsoMasterInq(String ipSsoMasterInq) {
		this.ipSsoMasterInq = ipSsoMasterInq;
	}
}
