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
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final CustomUserDetailsService userDetailsService;
  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {

    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
      Optional<Cookie> cookie = Arrays.stream(cookies)
          .filter(c -> c.getName().equals("jwt_token")).findFirst();
      if (cookie.isEmpty()) {
        filterChain.doFilter(request, response);
        return;
      }
      UUID userId = jwtService.pullsOutUserIdFromToken(cookie.get().getValue());
      UserPrincipal principal = userDetailsService.loadUserById(userId);

      Authentication auth = new UsernamePasswordAuthenticationToken(
          principal, null, principal.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
      filterChain.doFilter(request, response);
    }
  }
}
