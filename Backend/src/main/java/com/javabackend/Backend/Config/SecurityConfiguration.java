package com.javabackend.Backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String[] PERMIT_ALL_URLS = {"*","/api/products","/", "/login", "/register","/add-product","/api/products/{id}"};

    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(PERMIT_ALL_URLS).permitAll();
                    auth.anyRequest().authenticated();
                })
                .csrf().disable()
                .build();
    }
}