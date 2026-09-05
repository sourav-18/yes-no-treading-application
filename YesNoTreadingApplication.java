package com.ms.yes_no_treading_application;

import com.ms.yes_no_treading_application.services.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@RequiredArgsConstructor
public class YesNoTreadingApplication implements CommandLineRunner {

	private final MatchingService matchingService;
	public static void main(String[] args) {
		SpringApplication.run(YesNoTreadingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		matchingService.matchHandler();
	}
}
