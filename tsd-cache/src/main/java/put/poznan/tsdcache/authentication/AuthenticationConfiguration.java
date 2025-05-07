package put.poznan.tsdcache.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AuthenticationConfiguration {

    @Bean
    Authenticator authenticator(
            RedisBadLoginAttemptsStorage redisBadLoginAttemptsStorage,
            PasswordChecker passwordChecker
    ) {
        return new Authenticator(redisBadLoginAttemptsStorage, passwordChecker, 5);
    }

    @Bean
    PasswordChecker passwordChecker() {
        return new PasswordChecker();
    }

    @Bean
    RedisBadLoginAttemptsStorage redisBadLoginAttemptsStorage(
            RedisTemplate<String, Integer> authenticationRedisTemplate
    ) {
        return new RedisBadLoginAttemptsStorage(authenticationRedisTemplate, 60);
    }

    @Bean
    EmbeddedBadLoginAttemptsStorage embeddedBadLoginAttemptsStorage() {
        return new EmbeddedBadLoginAttemptsStorage();
    }

    @Bean
    public RedisTemplate<String, Integer> authenticationRedisTemplate(
            JedisConnectionFactory jedisConnectionFactory
    ) {
        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }
}
