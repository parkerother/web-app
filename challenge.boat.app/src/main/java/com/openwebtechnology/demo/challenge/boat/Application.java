package com.openwebtechnology.demo.challenge.boat;

import com.openwebtechnology.demo.challenge.boat.model.Boat;
import com.openwebtechnology.demo.challenge.boat.repository.BoatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner initDatabase(BoatRepository repository) {
		return args -> {
			repository.save(new Boat("Coral", "big Boat1"));
			repository.save(new Boat("Go with the Flow", "big Boat1"));
			repository.save(new Boat("Great White", "big Boat1"));
			repository.save(new Boat("Unsinkable", "big Boat1"));
			repository.save(new Boat("The Kraken", "big Boat1"));
		};
	}



}
