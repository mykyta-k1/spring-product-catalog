package com.product.security.service;

import com.product.security.JwtAuthenticationFilter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Було реалізовано базовий функціонал автентифікації як додаткову фічу, але для демонстрації
 * основного завдання вона не є активна (bonus feature)
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .ignoringRequestMatchers("/swagger-ui/**", "/v3/**", "/h2-console/**"))
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/swagger-ui/**", "/v3/**", "/h2-console/**").permitAll()
            .requestMatchers("/api/v1/**", "/api/v2/**").permitAll()
            .requestMatchers("/products/**").permitAll()
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .deleteCookies("jwt_token")
            .logoutSuccessUrl("/login")
        ).formLogin(AbstractHttpConfigurer::disable)
        .headers(headers -> headers
            .frameOptions(FrameOptionsConfig::sameOrigin)
        ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  /**
   * Налаштовує, які зовнішні джерела, методи та заголовки можуть звертатися до API.
   *
   * @return конфігурація CORS для всіх енд поінтів проєкту
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration cors = new CorsConfiguration();
    cors.setAllowedOrigins(List.of(
        "http://localhost:8080"));
    cors.setAllowedMethods(List.of(
        "GET", "POST", "PUT",
        "DELETE"
    )); // Дозволяє клієнтам використовувати всі основні методи HTTP
    cors.setAllowedHeaders(
        List.of("*")); // Дозволяє клієнтам надсилати будь-які заголовки у запиті
    cors.setAllowCredentials(true); // Дозволяє браузерам надсилати куки

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", cors);
    return source;
  }
}
