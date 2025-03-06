package ru.wintermute.postal_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postal");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("postgres");
        return dataSourceBuilder.build();
    }
    @Bean
    public JdbcTemplate jdbcTemplate() {return  new JdbcTemplate(getDataSource());}

}
