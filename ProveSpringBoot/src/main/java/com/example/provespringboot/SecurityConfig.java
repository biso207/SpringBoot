package com.example.provespringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll() // Swagger è accessibile senza autenticazione
                        .anyRequest().authenticated() // Tutto il resto deve essere autenticato
                )
                .csrf(csrf -> csrf.disable()); // Disabilitiamo CSRF per test API

        return http.build();
    }
}

