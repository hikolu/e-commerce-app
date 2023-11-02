package com.hikolu.ecommerceapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // get user details from the database
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    // set filter chain
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(configurer ->
//                configurer
//                        .requestMatchers(HttpMethod.GET, "/store").hasRole("USER"));
//
//        httpSecurity.httpBasic(Customizer.withDefaults());
//
//        httpSecurity.csrf(csrf -> csrf.disable());
//
//        return httpSecurity.build();
//    }
}
