package dev.satadru.runnerz;

import dev.satadru.runnerz.run.Location;
import dev.satadru.runnerz.run.Run;
import dev.satadru.runnerz.run.RunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class RunnerzApplication {

	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(RunnerzApplication.class, args);
//		log.info("Application Started Successfully!");
//		log.info("Something has changed in the application!");
	}

//	@Bean
//	CommandLineRunner runner(RunRepository runRepository) {
//		return args -> {
//			Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR);
//			runRepository.create(run);
//			log.info("Run: {}", run);
//		};
//	}
}
