package sv.com.nipro.interfaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InterfazApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterfazApplication.class, args);
	}
}
