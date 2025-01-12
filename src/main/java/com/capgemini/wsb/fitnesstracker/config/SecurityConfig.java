package com.capgemini.wsb.fitnesstracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Configuration class for setting up Spring Security.
 * It defines the static list of users and roles, and configures the security rules.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines the in-memory user details service with static users and roles.
     * 
     * @return the UserDetailsService implementation
     */
    @SuppressWarnings("deprecation")
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

    /**
     * Configures the HTTP security settings, including authorization rules and authentication mechanisms.
     * 
     * @param http the HttpSecurity object
     * @return SecurityFilterChain
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/actuator/**").permitAll()  // Actuator endpoints are open
                .requestMatchers(HttpMethod.GET, "/**").authenticated()  // GET requests are for authenticated users
                .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")  // POST requests are for ADMIN users
                .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")  // PUT requests are for ADMIN users
                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")  // DELETE requests are for ADMIN users
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {})  // Use Basic Auth
            .csrf(csrf -> csrf.disable());  // Disable CSRF for simplicity
        return http.build();
    }
}