package com.nhnent.study.springcore.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.nhnent.study.springcore")
@EnableJpaRepositories(basePackages = "com.nhnent.study.springcore.repository")
public class ApplicationContextConfiguration {
    private static final Map<String, String> JDBC_PROPERTY_LOCATION_MAP = new HashMap<>();

    static {
        JDBC_PROPERTY_LOCATION_MAP.put("h2", "classpath:properties/h2.jdbc.properties");
        JDBC_PROPERTY_LOCATION_MAP.put("mysql", "classpath:properties/mysql.jdbc.properties");
    }

    @Bean
    public String dbms() {
        return "h2";
    }

    @Bean
    public Resource jdbcPropertyLocation(ApplicationContext applicationContext, String dbms) {
        return applicationContext.getResource(JDBC_PROPERTY_LOCATION_MAP.get(dbms));
    }

    @Bean
    public PropertyPlaceholderConfigurer placeholderConfigurer(Resource jdbcProperties) {
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
        placeholderConfigurer.setLocation(jdbcProperties);
        return placeholderConfigurer;
    }

    @Bean
    public DataSource dataSource(@Value("${jdbc.driverClassName}") String driverClassName,
                                 @Value("${jdbc.url}") String url,
                                 @Value("${jdbc.username}") String username,
                                 @Value("${jdbc.password}") String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, String dbms) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.nhnent.study.springcore.vo");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        if (Objects.equals(dbms, "h2")) {
            vendorAdapter.setDatabase(Database.H2);
        }
        else if (Objects.equals(dbms, "mysql")) {
            vendorAdapter.setDatabase(Database.MYSQL);
        }

        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(jpaProperties());

        return em;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.use_sql_comments", "false");
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");

        return properties;
    }

}
