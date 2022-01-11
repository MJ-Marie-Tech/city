package fr.bred.example.interview;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.bred.example.interview.model.City;
import fr.bred.example.interview.service.CityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class InterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CityService cityService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<City>> typeReference = new TypeReference<>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/cities.json");
			try {
				List<City> cities = mapper.readValue(inputStream, typeReference);
				cityService.createCities(cities);
				System.out.println("Cities Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save cities: " + e.getMessage());
			}
		};
	}

}
