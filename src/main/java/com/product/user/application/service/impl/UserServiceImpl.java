package com.product.user.application.service.impl;

import com.product.user.application.dto.req.UserDtoRequestFactory.UserLoginDto;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserRegisterDto;
import com.product.user.application.dto.req.UserDtoRequestFactory.UserUpdateRequest;
import com.product.user.application.dto.resp.UserUpdateResponse;
import com.product.user.application.exception.UserAlreadyExistsException;
import com.product.user.application.exception.UserNotFoundException;
import com.product.user.application.mapper.UserMapper;
import com.product.user.application.service.contract.PasswordEncoderService;
import com.product.user.application.service.contract.UserService;
import com.product.user.domain.model.User;
import com.product.user.infrastructure.dao.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

  private final PasswordEncoderService passwordEncoder;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional(readOnly = false)
  @Override
  public void save(UserRegisterDto dto) {
    userRepository.save(User.builder()
        .email(dto.getEmail())
        .passwordHash(passwordEncoder.hashPassword(dto.getPassword()))
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .patronymic(dto.getPatronymic())
        .build()
    );
    log.info("Saving user {}", dto);
  }

  @Transactional(readOnly = false)
  @Override
  public UserUpdateResponse update(UserUpdateRequest dto, UUID currentUserId) {
    User u = findUserById(currentUserId);
    userMapper.updateUserFromDto(dto, u);
    return userMapper.userToUserUpdateResponse(u);
  }

  @Override
  public UUID checkUserAndPassword(UserLoginDto dto) {
    User u = findUserByEmail(dto.getEmail());

    if (!passwordEncoder.checkPassword(dto.getPassword(), u.getPasswordHash())) {
      throw new UserNotFoundException("Incorrect email, nickname, or password");
    }

    return u.getId();
  }

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
