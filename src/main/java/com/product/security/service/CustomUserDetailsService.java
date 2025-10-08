package com.product.security.service;

import com.product.security.UserPrincipal;
import com.product.user.domain.model.User;
import com.product.user.infrastructure.dao.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        return buildUserPrincipal(u);
    }

    // Loading user principal use id from jwt filter
    public UserPrincipal loadUserById(UUID id) {
        User u = userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with id"));
        return buildUserPrincipal(u);
    }

    private UserPrincipal buildUserPrincipal(User user) {
        return new UserPrincipal(
            user.getId(),
            user.getEmail(),
            user.isActive(),
            user.isVerified(),
            user.getRole()
        );
    }
}
