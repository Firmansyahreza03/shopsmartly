package com.rezalab.shopsmartly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager",
        basePackages = {
                "com.rezalab.shopsmartly",
        })
public class DataSourceConfig {

    private final Environment env;

    @Autowired
    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean(name = "appDataSource")
    @ConfigurationProperties(prefix = "spring.shopsmartly.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("appDataSource") DataSource dataSource) {
        Map<String, Object> hibernateProps = new HashMap<String, Object>();
        hibernateProps.put("hibernate.hbm2ddl.auto",env.getProperty("spring.shopsmartly.jpa.hibernate.ddl-auto"));
        hibernateProps.put("spring.jpa.database-platform", env.getProperty("spring.shopsmartly.jpa.database-platform"));

        return builder.dataSource(dataSource).packages("com.rezalab.shopsmartly")
                .persistenceUnit("ksm").properties(hibernateProps)
                .build();
    }
    @Bean(name = "appTransactionManager")
    public PlatformTransactionManager appTransactionManager(@Qualifier("appEntityManagerFactory") EntityManagerFactory appEntityManagerFactory) {
        return new JpaTransactionManager(appEntityManagerFactory);
    }
}

