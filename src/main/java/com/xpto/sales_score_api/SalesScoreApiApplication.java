package com.xpto.sales_score_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class SalesScoreApiApplication {

	@GetMapping("/")
	public String home() {
		return "Hello locally";
	}

	public static void main(String[] args) {
		SpringApplication.run(SalesScoreApiApplication.class, args);
	}

}
