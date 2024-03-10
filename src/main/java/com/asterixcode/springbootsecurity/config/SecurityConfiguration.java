package com.asterixcode.springbootsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Enables Spring Security on Spring Boot 3.0+
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  // At application startup, Spring Security will look for a bean of type SecurityFilterChain.
  // The bean is responsible for configuring all http security rules of the application.
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            // Disable CSRF verification as we are using JWT.
            .csrf(AbstractHttpConfigurer::disable)
            // What are the URLs and Paths that need to be secured,
            // and what is the white list (doesn't require any authentication or authorization token).
            // Example of white listing: /login, /register, /forgot-password, /reset-password
            .authorizeHttpRequests(
            authorizeRequests ->
                authorizeRequests
                        // list of URLs that are whitelisted
                        .requestMatchers("api/v1/auth/**").permitAll()
                        // any other URL should be authenticated
                        .anyRequest().authenticated()
            )
        // Filter implement as "once per request filter" = every request should be authenticated.
        // This means that the server doesn't store the authentication/session state = Stateless.
        .sessionManagement(
            sessionManagement ->
                    // Set Spring to create a new session for every request.
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // Tell Spring which authentication provider to use.
        .authenticationProvider(authenticationProvider)
        // Add and set Spring to use the JWT filter before the UsernamePasswordAuthenticationFilter.
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
