package com.accenture.tigital.libraries.implementations;

import com.accenture.tigital.libraries.interfaces.IEnv;

import io.github.cdimascio.dotenv.Dotenv;

public class Env implements IEnv {
    private Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

    @Override
    public void load() {
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
    }

}
