package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadBasicaConfig {

    @Bean
    public SecurityFilterChain FiltroSeguridad(HttpSecurity http) throws Exception {
        http. //Depreciado! ya no se usa -> csrf().disable() // no se necesita CSRF para APIs REST
        csrf(csrf -> csrf.disable()). // Para API REST esta es la forma
        authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // consola H2 libre
                .requestMatchers(HttpMethod.GET, "/producto/**").permitAll() // solo GET públicos
                .requestMatchers(HttpMethod.POST, "/producto/**").authenticated() // Metodos POST con autenticación
                .anyRequest().authenticated() // el resto requiere login
        )
                .httpBasic(Customizer.withDefaults()); // <-- Esto activa Basic Auth

            // permite usar H2 en navegador
//              .headers(headers -> headers.frameOptions(config -> config.disable()));

        return http.build();
    }
}
