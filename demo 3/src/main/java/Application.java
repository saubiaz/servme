import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(value = {
    "com.servme.todo.demo",
    })
@EntityScan("com.servme.todo.demo.model")
@EnableJpaRepositories("com.servme.todo.demo.repository")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}