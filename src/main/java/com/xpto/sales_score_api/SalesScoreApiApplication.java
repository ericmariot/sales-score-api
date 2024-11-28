package com.xpto.sales_score_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@RestController
public class SalesScoreApiApplication {

	@GetMapping("/")
	public RedirectView home() {
		return new RedirectView("/api/health");
	}

	public static void main(String[] args) {
		SpringApplication.run(SalesScoreApiApplication.class, args);
	}
}
