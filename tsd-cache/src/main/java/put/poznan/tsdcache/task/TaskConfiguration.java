package put.poznan.tsdcache.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class TaskConfiguration {

    @Bean
    RedisTaskQueue redisTaskQueue(
            RedisTemplate<String, String> taskRedisTemplate
    ) {
        return new RedisTaskQueue(taskRedisTemplate);
    }

    @Bean
    public RedisTemplate<String, String> taskRedisTemplate(
            JedisConnectionFactory jedisConnectionFactory
    ) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }
}
