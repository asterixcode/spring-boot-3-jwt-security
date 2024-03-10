package com.asterixcode.security.config;

import com.asterixcode.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UserRepository userRepository;

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  // Implementation of AuthenticationProvider that retrieves user details from a UserDetailsService.
  // securityFilterChain method in SecurityConfiguration class uses this bean as the authentication provider.
  // AuthenticationProvider is responsible for fetching user details from the database and encoding the password.
  // AuthenticationProvider has many implementations, but we are using DaoAuthenticationProvider.
  @Bean
  public AuthenticationProvider authenticationProvider() {
    // AuthenticationProvider implementation responsible for fetching user details from database
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    // Set which userDetailsService to use in order to fetch user details
    // It's possible to have multiple userDetailsService, such as for different profiles like from in-memory, database, etc.
    authProvider.setUserDetailsService(userDetailsService());
    // Set which password encoder to use
    // When authenticating, the password will be encoded and compared with the encoded password in the database.
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  // AuthenticationManager is responsible for authenticating the user.
  // It has methods that helps authenticate a user just by username and password.
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    // AuthenticationConfiguration holds already the information about the AuthenticationManager.
    // So, just return the default implementation of Spring Security's AuthenticationManager.
    return configuration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
