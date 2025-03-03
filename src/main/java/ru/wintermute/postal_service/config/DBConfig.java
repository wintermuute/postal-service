package ru.wintermute.postal_service.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

//@Configuration
//@PropertySource("classpath:application.properties")
public class DBConfig {

    private final Environment environment;

    //@Autowired
    public DBConfig(Environment environment) {
        this.environment = environment;
    }

    //@Bean
    public DataSource dataSource(@Value("${driver}") String driver) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("driver")));
        dataSource.setDriverClassName(Objects.requireNonNull(driver));
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("user"));
        dataSource.setPassword(environment.getProperty("password"));
        return  dataSource;
    }

    //@Bean
    public JdbcTemplate jdbcTemplate() {return  new JdbcTemplate(dataSource(""));}
}
