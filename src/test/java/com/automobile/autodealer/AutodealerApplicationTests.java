package com.automobile.autodealer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

		autosRepository.saveAll(this.testAutos);
	}

	@AfterEach
	void tearDown() {
		autosRepository.deleteAll();
	}


	@Test
	void contextLoads() {
	}

}
