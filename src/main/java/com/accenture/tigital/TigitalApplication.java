package com.accenture.tigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class TigitalApplication {

	public static void main(String[] args) {
		Dotenv.configure()
                .ignoreIfMissing()
                .load();

		SpringApplication.run(TigitalApplication.class, args);
	}

}
