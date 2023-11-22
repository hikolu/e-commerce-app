package com.hikolu.ecommerceapp.config;

import com.hikolu.ecommerceapp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    // define bcrypt bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // define DaoAuth. Provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }

    // set filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/store/**").hasAnyRole( "USER", "ADMIN")
                        .requestMatchers("/profile/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/order/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(authenticationSuccessHandler)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return httpSecurity.build();
    }
}
