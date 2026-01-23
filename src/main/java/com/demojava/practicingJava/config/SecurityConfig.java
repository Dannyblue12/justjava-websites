package com.demojava.practicingJava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 1. Allow everyone to access static resources (css, images)
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()
                        // 2. Allow everyone to access the public endpoint
                        .requestMatchers("/public/**", "/").permitAll()
                        // 3. Lock the admin endpoint (Requires Login)
                        .requestMatchers("/admin/**").authenticated()
                        // 4. Any other request also needs login (Safety net)
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        // When they successfully login, send them to the /admin page
                        .defaultSuccessUrl("/admin", true)
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/public") // Go back to public view after logout
                );

        return http.build();
    }
}