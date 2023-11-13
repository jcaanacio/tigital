package com.accenture.tigital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/your-api-endpoint")
public class YourController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public YourController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/get-unique-constraint")
    public String getUniqueConstraintName() {
        String sql = "SELECT constraint_name " +
                     "FROM information_schema.constraint_column_usage " +
                     "WHERE table_name = 'notes' AND column_name = 'user_id'";

        try {
            return jdbcTemplate.queryForObject(sql, String.class);
        } catch (Exception e) {
            // Handle exceptions (e.g., if no result is found)
            return "Error: " + e.getMessage();
        }
    }
}
