package br.com.limitessvc;

import br.com.limitessvc.entity.LimiteDiario;
import br.com.limitessvc.repository.LimiteDiarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = LimiteDiarioRepository.class)
@EntityScan(basePackageClasses = LimiteDiario.class)
public class LimitesSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitesSvcApplication.class, args);
	}

}
