package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//@ComponentScan(basePackageClasses = Controller.class) //
@EnableWebMvc //
@ComponentScan("com.userApi.controller") //  WebConfig scan le controller
public class WebConfig {

}
