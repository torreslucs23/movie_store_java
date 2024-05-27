package com.example.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) {


		Dotenv dotenv = Dotenv.load();

		String dbUrl = dotenv.get("DATABASE_URL");
		String dbUsername = dotenv.get("DATABASE_USERNAME");
		String dbPassword = dotenv.get("DATABASE_PASSWORD");

		System.setProperty("DATABASE_URL", dbUrl);
		System.setProperty("DATABASE_USERNAME", dbUsername);
		System.setProperty("DATABASE_PASSWORD", dbPassword);
		SpringApplication.run(MoviesApplication.class, args);
	}

}
