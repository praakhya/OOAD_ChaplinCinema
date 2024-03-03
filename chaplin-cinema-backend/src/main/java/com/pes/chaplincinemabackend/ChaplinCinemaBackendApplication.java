package com.pes.chaplincinemabackend;

import com.pes.chaplincinemabackend.auth.factories.IdentityPasswordEncoderFactories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = {"com.pes.chaplincinemabackend"})
@EnableMongoRepositories
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
public class ChaplinCinemaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChaplinCinemaBackendApplication.class, args);
	}
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = IdentityPasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passwordEncoder;
	}
}
