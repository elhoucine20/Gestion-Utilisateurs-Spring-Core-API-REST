package config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * import org.springframework.beans.factory.annotation.Value;
 * import org.springframework.context.annotation.Configuration;
 * import org.springframework.context.annotation.PropertySource;
 * import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 * import org.springframework.transaction.annotation.EnableTransactionManagement;
 *
 * @Configuration
 * @PropertySource("classpath:application.properties") // read properties
 * @EnableTransactionManagement // utilise les transactions
 * @EnableJpaRepositories // peux database connectet avec jpa repository
 * public class DatabaseConfig {
 *
 *     @Value("db.url")
 *     private static String url;
 *
 *     @Value("db.username")
 *     private static String username;
 *
 *     @Value("db.password")
 *     private static String password;
 * }
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.userApi.repository") // indique a Spring a rechercher sur les interfaces de repository
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(){  // DataSource => fournit les connexions a database H2
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        // org.h2.Driver ? C'est le nom complet de la classe Java qui permet au pilote JDBC de communiquer avec H2.
        //dataSource.setUrl("jdbc:h2:mem:userApiDb");
        dataSource.setUrl("jdbc:h2:mem:userApiDb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("userApiDb");
        dataSource.setPassword("12345678");

        System.out.println("DataSource H2 configure !");

        try (java.sql.Connection c = dataSource.getConnection()) {
            // ouvre une connexion pour créer la base en mémoire
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.userApi.model");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.setProperty("hibernate.show_sql", "true");
        emf.setJpaProperties(props);
        return emf;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}

/**
 *
 package com.example.config;

 import javax.sql.DataSource;

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.context.annotation.PropertySource;
 import org.springframework.core.env.Environment;
 import org.springframework.jdbc.datasource.DriverManagerDataSource;

 @Configuration
 @PropertySource("classpath:application.properties")
 public class DatabaseConfig {

 @Bean
 public DataSource dataSource(Environment env) {
 DriverManagerDataSource ds = new DriverManagerDataSource();

 ds.setDriverClassName(
 env.getProperty("spring.datasource.driverClassName")
 );
 ds.setUrl(
 env.getProperty("spring.datasource.url")
 );
 ds.setUsername(
 env.getProperty("spring.datasource.username")
 );
 ds.setPassword(
 env.getProperty("spring.datasource.password", "")
 );

 return ds;
 }
 }

 */
