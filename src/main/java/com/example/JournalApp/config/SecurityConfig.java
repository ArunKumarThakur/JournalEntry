package com.example.JournalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // disable CSRF for Postman testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/journal/save").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults()) // enable basic auth
                .build();
    }
}
