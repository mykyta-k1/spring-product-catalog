package com.product.user.application.controller;

import com.product.user.application.dto.req.UserLoginDto;
import com.product.user.application.dto.req.UserRegisterDto;
import com.product.user.application.service.contract.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Підтримується автентифікація, проте на рівні URL без UI, працюючи з токенами що зберігаються в кукі
 */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/login")
    public String login(HttpServletResponse resp, @Valid UserLoginDto dto) {
        String token = authService.login(dto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true); // Protection from JS
        cookie.setSecure(false); // Only HTTPS (true)
        cookie.setPath("/"); // Work for all URL
        cookie.setMaxAge(86400); // Expiration
        resp.addCookie(cookie);

        return "redirect:/posts";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDto dto) {
        authService.register(dto);
        return "redirect:login";
    }
}
