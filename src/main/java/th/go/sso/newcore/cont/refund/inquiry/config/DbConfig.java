package th.go.sso.newcore.cont.refund.inquiry.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.datasource.impl.OracleDataSource;

@Configuration
public class DbConfig {

    @Value("${db.con.url}")
    private String url;
    @Value("${db.con.username}")
    private String username;
    @Value("${db.con.password}")
    private String password;

    @Bean
    public DataSource dataSource() throws SQLException {
    	Properties props = new Properties();
    	props.put("MaxLimit", 5);
    	props.put("InitialLimit", 5);
    	
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setConnectionProperties(props);
        return dataSource;
    }
}
