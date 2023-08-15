package com.accenture.tigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accenture.tigital.libraries.implementations.Env;
import com.accenture.tigital.libraries.interfaces.IEnv;

@SpringBootApplication
public class TigitalApplication {

	public static void main(String[] args) {

		try {
            IEnv env = new Env();
			env.load();

			SpringApplication.run(TigitalApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace(); // Print the full stack trace
        }
	}

}
