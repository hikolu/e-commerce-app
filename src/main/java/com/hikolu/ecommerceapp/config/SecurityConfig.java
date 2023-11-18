package com.hikolu.ecommerceapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // get user details from the database
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    //     set filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/store/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/profile/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/order/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return httpSecurity.build();
    }
}
