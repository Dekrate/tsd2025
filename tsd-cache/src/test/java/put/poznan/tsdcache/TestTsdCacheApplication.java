package put.poznan.tsdcache;

import org.springframework.boot.SpringApplication;

public class TestTsdCacheApplication {

    public static void main(String[] args) {
        SpringApplication.from(TsdCacheApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
