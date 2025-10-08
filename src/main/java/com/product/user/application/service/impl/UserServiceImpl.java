package com.product.user.application.service.impl;

import com.product.user.application.dto.req.UserLoginDto;
import com.product.user.application.dto.req.UserRegisterDto;
import com.product.user.application.dto.req.UserUpdateRequest;
import com.product.user.application.dto.resp.UserUpdateResponse;
import com.product.user.application.exception.UserAlreadyExistsException;
import com.product.user.application.exception.UserNotFoundException;
import com.product.user.application.mapper.UserMapper;
import com.product.user.application.service.contract.PasswordEncoderService;
import com.product.user.application.service.contract.UserService;
import com.product.user.domain.model.User;
import com.product.user.infrastructure.dao.UserRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoder;

    @Transactional
    @Override
    public void save(UserRegisterDto dto) {
        log.info("Saving user {}", dto);
        userRepository.save(User.builder()
            .email(dto.getEmail())
            .passwordHash(passwordEncoder.hashPassword(dto.getPassword()))
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .patronymic(dto.getPatronymic())
            .build()
        );
    }

    @Transactional
    @Override
    public UserUpdateResponse update(UserUpdateRequest dto, UUID currentUserId) {
        User u = findUserById(currentUserId);

        if (!u.getFirstName().equals(dto.getFirstName())) {
            u.setFirstName(dto.getFirstName());
        }
        if (!u.getLastName().equals(dto.getLastName())) {
            u.setLastName(dto.getLastName());
        }
        if (!u.getPatronymic().equals(dto.getPatronymic())) {
            u.setPatronymic(dto.getPatronymic());
        }

        return userMapper.userToUserUpdateResponse(u);
    }

    @Transactional
    @Override
    public UUID checkUserAndPassword(UserLoginDto dto) {
        User u = findUserByEmail(dto.getEmail());

        if (!passwordEncoder.checkPassword(dto.getPassword(), u.getPasswordHash())) {
            throw new UserNotFoundException("Incorrect email, nickname, or password");
        }

        return u.getId();
    }

    @Transactional
    @Override
    public void isExistsUserByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(
                "A user with this email or nickname already exists");
        }
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
