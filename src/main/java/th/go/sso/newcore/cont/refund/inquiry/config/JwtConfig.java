package th.go.sso.newcore.cont.refund.inquiry.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import th.go.sso.newcore.cont.common.filter.JwtFilter;

@Configuration
@Order(value = 100)
public class JwtConfig extends WebSecurityConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(JwtConfig.class);

	@Autowired
	private JwtFilter jwtFilter;

	@PostConstruct
	public void init() {
		log.debug("JwtConfig:: init JwtConfig");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/public/**")
				.permitAll()
				.anyRequest().authenticated();

		// Add our custom JWT security filter
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
