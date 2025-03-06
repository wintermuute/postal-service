package ru.wintermute.postal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PostalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostalServiceApplication.class, args);
	}


}
