package sv.com.nipro.interfaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages={"sv.com.nipro.interfaz.controller"})
@EnableJpaAuditing
public class InterfazApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterfazApplication.class, args);
	}

}
