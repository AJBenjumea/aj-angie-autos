package com.automobile.autodealer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
class AutodealerApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	AutosRepository autosRepository;

	List<Auto> testAutos = new ArrayList<>();
	Random r = new Random();

	@BeforeEach
	void setup() {
		String[] colors = {"Red", "Blue", "Green", "Black", "Yellow"};
		String[] makes = {"Honda", "Toyota", "Nissan", "BMW", "Porsche"};

		for (int i = 0; i < 50; i++) {
			Auto auto = new Auto(1990 + i, makes[r.nextInt(50) % 5],
					"ABC" + String.valueOf(r.nextInt(50) % 5),
					colors[r.nextInt(50) % 5],
					"DEF" + String.valueOf(r.nextInt(50) % 5),
					"VIN" + String.valueOf(r.nextInt(50) % 5)
					);
			testAutos.add(auto);
		}
		autosRepository.saveAll(testAutos);
	}

	@AfterEach
	void tearDown() {
		autosRepository.deleteAll();
	}


	@Test
	void contextLoads() {
	}

	@Test
	void getAutos_Exist_returnAutomobiles() {
		ResponseEntity<Automobiles> response = restTemplate.getForEntity("/api/autos", Automobiles.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().isEmpty()).isFalse();
	}

	@Test
	void getAutos_doesNotExist_returnNoContent() {
		autosRepository.deleteAll();

		ResponseEntity<Automobiles> response = restTemplate.getForEntity("/api/autos", Automobiles.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertThat(response.getBody()).isNull();
	}

	@Test
	void getAutos_byVin_exists_returnsAuto() {
		Auto auto = new Auto(2019, "Lexus", "coolCar", "PINK", "Angie", "abc123");
		autosRepository.save(auto);
		ResponseEntity<Auto> response = restTemplate.getForEntity("/api/autos/" + auto.getVin(), Auto.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getColor()).isEqualTo("PINK");
	}

	@Test
	void getAutos_byVin_doesNotExists_returnsNoContent() {
		ResponseEntity<Auto> response = restTemplate.getForEntity("/api/autos/5732942hjfk", Auto.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertThat(response.getBody()).isNull();
	}
}
