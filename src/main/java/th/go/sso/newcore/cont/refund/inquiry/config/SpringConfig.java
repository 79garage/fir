package th.go.sso.newcore.cont.refund.inquiry.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("th.go.sso.newcore.cont.*")
@EntityScan(basePackages = "th.go.sso.newcore.cont.*")
@ComponentScan("th.go.sso.newcore.cont.*")
public class SpringConfig {
}
