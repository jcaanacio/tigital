package com.accenture.tigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class TigitalApplication {

	public static void main(String[] args) {

		try {
            Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

			// Access the environment variables using dotenv
			String dbHost = dotenv.get("DB_HOST");
			String dbPort = dotenv.get("DB_PORT");
			String dbUser = dotenv.get("DB_USER");
			String dbPassword = dotenv.get("DB_PASSWORD");
			String dbName = dotenv.get("DB_NAME");

			// Set the environment variables for Spring Boot
			System.setProperty("DB_NAME", dbName);
			System.setProperty("DB_HOST", dbHost);
			System.setProperty("DB_PORT", dbPort);
			System.setProperty("DB_USER", dbUser);
			System.setProperty("DB_PASSWORD", dbPassword);

			SpringApplication.run(TigitalApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace(); // Print the full stack trace
        }
	}

}
