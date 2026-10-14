package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(value = "com.userApi",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                Controller.class,WebConfig.class
        })
})
@Import(DatabaseConfig.class) // acces a les properties dans DatabaseConfig?java
public class RootConfig {
        
}
