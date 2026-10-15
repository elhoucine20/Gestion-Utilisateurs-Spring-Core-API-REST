package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(value = "com.userApi",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                Controller.class
        })
})
@Import(DatabaseConfig.class) // charge les beans de DatabaseConfig (DataSource, JPA, transactions)
// WebConfig scan le controller et RootConfig charge le rest dans DatabaseConfig
public class RootConfig {
        
}
