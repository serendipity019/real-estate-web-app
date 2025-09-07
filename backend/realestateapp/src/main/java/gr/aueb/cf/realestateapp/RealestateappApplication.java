package gr.aueb.cf.realestateapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "gr.aueb.cf.realestateapp.repository")
public class RealestateappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealestateappApplication.class, args);
	}

}
