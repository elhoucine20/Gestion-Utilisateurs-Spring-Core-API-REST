package config;

import com.userApi.model.Produit;
import com.userApi.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

   @Bean(name = "monProduit")
    // par default recuperer cette objet par le nom de method si ne specifier pas une nom
    public Produit produit(){
        return new Produit();
    }


    @Bean
    // par default recuperer cette objet par le nom de method si ne specifier pas une nom
    public User getUser(){
        return new User();
    }


}
