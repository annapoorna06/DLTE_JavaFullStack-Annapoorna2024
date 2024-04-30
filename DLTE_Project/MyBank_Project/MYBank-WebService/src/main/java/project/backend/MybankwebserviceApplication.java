package project.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:apps.properties")
public class MybankwebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybankwebserviceApplication.class, args);
    }

}
