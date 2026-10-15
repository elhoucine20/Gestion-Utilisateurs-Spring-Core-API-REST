package config;

import com.userApi.model.User;
import com.userApi.util.PasswordUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    // par default recuperer cette objet par le nom de method si ne specifier pas une nom
    public User getUser(){
        return new User();
    }

    @Bean(name = "passwordUtils")
    public PasswordUtils getPasswordUtils(){
        return new PasswordUtils();
    }


}
