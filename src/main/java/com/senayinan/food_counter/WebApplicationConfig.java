package com.senayinan.food_counter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    // Create spring-managed object to allow the app to access our filter

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter(); // Or use @Autowired constructor here
    }

    // Register the filter with the Spring container
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor( authenticationFilter() );
    }

}

