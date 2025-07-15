package th.go.sso.newcore.cont.refund.inquiry.config;

import java.time.Duration;
import java.util.Arrays;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfig {

    @Autowired
    private AppConfig appConfig;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        if (appConfig.getRedisClusterIps() != null && appConfig.getRedisClusterIps().length > 0) {
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(Arrays.asList(appConfig.getRedisClusterIps()));
            //redisClusterConfiguration.setPassword(RedisPassword.of(clusterPassword));
            return new JedisConnectionFactory(redisClusterConfiguration, jedisClientConfiguration());
        } else {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(appConfig.getSsoRedisIp(),
                    Integer.parseInt(appConfig.getSsoRedisPort()));
            redisStandaloneConfiguration.setPassword(RedisPassword.of(appConfig.getSsoRedisPassword()));
            return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration());
        }
    }

    @Bean
    public JedisClientConfiguration jedisClientConfiguration() {
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = (JedisClientConfiguration.JedisClientConfigurationBuilder) JedisClientConfiguration.builder();
        @SuppressWarnings("rawtypes")
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(5);
        genericObjectPoolConfig.setMaxIdle(5);
        genericObjectPoolConfig.setMinIdle(2);
        genericObjectPoolConfig.setTestOnBorrow(true);
        genericObjectPoolConfig.setTestOnReturn(true);
        genericObjectPoolConfig.setTestWhileIdle(true);
        genericObjectPoolConfig.setMinEvictableIdleTime(Duration.ofSeconds(60));
        genericObjectPoolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(30));
        genericObjectPoolConfig.setNumTestsPerEvictionRun(-1);
        genericObjectPoolConfig.setBlockWhenExhausted(true);
        return builder.usePooling().poolConfig(genericObjectPoolConfig).build();
    }

}
