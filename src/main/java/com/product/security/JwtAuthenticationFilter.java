package com.product.security;

import com.product.security.contract.JwtService;
import com.product.security.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final List<String> PUBLIC_URLS = List.of(
      "/auth/login",
      "/auth/register",
      "/swagger-ui",
      "/v3/",
      "/favicon.ico",
      "/logout",
      "/h2-console"
  );
  private final CustomUserDetailsService userDetailsService;
  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {

    String requestPath = request.getRequestURI();
    if (PUBLIC_URLS.stream().anyMatch(requestPath::startsWith)) {
      log.debug("Skipping JWT validation for public URL: {}", requestPath);
      filterChain.doFilter(request, response);
      return;
    }

    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
      Optional<Cookie> cookie = Arrays.stream(cookies)
          .filter(c -> c.getName().equals("jwt_token")).findFirst();
      if (cookie.isEmpty()) {
        filterChain.doFilter(request, response);
        logger.info("User token is not valid by " + requestPath);
        return;
      }
      UUID userId = jwtService.pullsOutUserIdFromToken(cookie.get().getValue());
      UserPrincipal principal = userDetailsService.loadUserById(userId);

      Authentication auth = new UsernamePasswordAuthenticationToken(
          principal, null, principal.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
      logger.info("User token is valid by " + requestPath);
    }
    filterChain.doFilter(request, response);
  }
}
