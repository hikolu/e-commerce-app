package com.hikolu.ecommerceapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // setup encryptor
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // get user details from the database
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

//     set filter chain
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(configurer ->
//                configurer
//                        .requestMatchers(HttpMethod.GET, "/").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/profile").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/profile").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/profile/my-orders").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/profile/my-orders").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/store").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/store").hasRole("ADMIN"));
//
//        httpSecurity.httpBasic(Customizer.withDefaults());
//
//        httpSecurity.csrf(csrf -> csrf.disable());
//
//        return httpSecurity.build();
//    }
}
