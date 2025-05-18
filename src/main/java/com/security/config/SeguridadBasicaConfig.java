package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadBasicaConfig {

    @Bean
    public SecurityFilterChain FiltroSeguridad(HttpSecurity http) throws Exception {
        http. //Depreciado! ya no se usa -> csrf().disable() // no se necesita CSRF para APIs REST
        authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // consola H2 libre
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll() // solo GET públicos
                .anyRequest().authenticated() // el resto requiere login
        );
        /*
            .headers(headers -> headers.frameOptions().disable()) // permite usar H2 en navegador
            .httpBasic(); // autenticación básica
         */
        return http.build();
    }
}
