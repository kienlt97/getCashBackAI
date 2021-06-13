package com.kienlt.CashBack.config;

import com.kienlt.CashBack.util.Constants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;


@Configuration
@EnableJpaRepositories(basePackages = "com.kienlt.CashBack.repository",
        entityManagerFactoryRef = "EntityManager",
        transactionManagerRef = "TransactionManager")
@EnableTransactionManagement
public class DBConfig {
    private final Environment env; // Contains Properties Load by @PropertySources

    @Autowired
    public DBConfig(Environment env) {
        this.env = env;
    }

    @Bean(name = "Datasource")
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.cashback"));
        config.setJdbcUrl(env.getProperty("spring.datasource.url.cashback"));
        config.setUsername(env.getProperty("spring.datasource.username.cashback"));
        config.setPassword(env.getProperty("spring.datasource.password.cashback"));
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(300000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }

    @Bean("EntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean vasEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());

        // Scan Entities in Package:
        em.setPackagesToScan(Constants.SPRING_SCAN.CASHBACK_PACKAGE_ENTITIES);
        em.setPersistenceUnitName(Constants.SPRING_SCAN.CASHBACK_JPA_UNIT_NAME);

        //
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        // JPA & Hibernate
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("hibernate.enable_lazy_load_no_trans", env.getProperty("spring.jpa.enable_lazy_load_no_trans"));

        // Solved Error: PostGres createClob() is not yet implemented.
        // PostGres Only:
        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",  false);

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean("TransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(vasEntityManager().getObject());
        return transactionManager;
    }
}
