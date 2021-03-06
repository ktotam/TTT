package pr.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@ComponentScan("pr")
@EntityScan(basePackages = "pr.models")
@EnableJpaRepositories(basePackages = "pr.repositories")
@Controller("pr.controllers")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}