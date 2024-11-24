package com.example.crud_app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc://mysql://localhost:3306/jbdl77", "root", "root1234");
    }
}
