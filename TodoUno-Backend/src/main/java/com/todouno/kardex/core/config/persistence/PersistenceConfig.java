package com.todouno.kardex.core.config.persistence;

import com.todouno.kardex.core.config.deploy.enums.DeployType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Bean
    @ConfigurationProperties(value = "spring.datasource.primary")
    public PersistenceProperties persistenceProperties(){
        return new PersistenceProperties();
    }

    @Bean
    @Profile({DeployType.DEVELOP})
    public DataSource dataSourceConfiguration(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(persistenceProperties().driverClassName);
        dataSource.setUrl(persistenceProperties().url);
        dataSource.setSchema(persistenceProperties().schema);
        dataSource.setUsername(persistenceProperties().username);
        dataSource.setPassword(persistenceProperties().password);

        return dataSource;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class PersistenceProperties{

        private String url;
        private String username;
        private String password;
        private String driver;
        private String driverClassName;
        private String maxActive;
        private String maxIdle;
        private String minIdle;
        private String initialSize;
        private String removeAbandoned;
        private String schema;


    }

}
