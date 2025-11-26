package com.baonguyen.fitnesstrackingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitnessTrackingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessTrackingAppApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder, UserRepository userRepository) {
//		return args -> {
//
//			var admin = User.builder()
//					.firstname("admin")
//					.lastname("admin")
//					.email("admin@mail.com")
//					.password(passwordEncoder.encode("admin"))
//					.role(ADMIN)
//					.build();
//
//			userRepository.save(admin);
//
//		};
//	}
}
