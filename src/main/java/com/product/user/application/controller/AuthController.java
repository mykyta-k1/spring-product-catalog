package com.product.user.application.controller;

import com.product.user.application.dto.req.UserDtoRequestFactory.UserLoginDto;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserRegisterDto;
import com.product.user.application.service.contract.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        log.warn("loginPage loaded");
//        model.addAttribute("loginForm", new UserLoginDto());
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        log.warn("registerPage loaded");
//        model.addAttribute("registerForm", new UserRegisterDto());
        return "register";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletResponse resp, @Validated UserLoginDto dto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("loginForm", dto);
            return "login";
        }

        String token = authService.login(dto);

        Cookie cookie = new Cookie("jwt_token", token);
        cookie.setHttpOnly(true); // Protection from JS
        cookie.setSecure(false); // Only HTTPS (true)
        cookie.setPath("/"); // Work for all URL
        cookie.setMaxAge(86400); // Expiration
        resp.addCookie(cookie);

        return "redirect:/categories";
    }

    @PostMapping("/register")
    public String register(Model model, @Validated UserRegisterDto dto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("registerForm", dto);
            return "register";
        }

        authService.register(dto);
        return "redirect:login";
    }
}
