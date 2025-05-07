package put.poznan.tsdcache;

import com.redis.testcontainers.RedisContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    @Bean
    @ServiceConnection(name = "redis")
    RedisContainer redisContainer() {
        try (RedisContainer redisContainer = new RedisContainer(DockerImageName.parse("redis:latest"))) {
            redisContainer
                    .withExposedPorts(6379)
                    .withReuse(true)
                    .start();
            return redisContainer;
        }
    }

}