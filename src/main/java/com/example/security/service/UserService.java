package com.example.security.service;

import com.example.security.domain.User;
import com.example.security.domain.dto.AuthSuccessDTO;
import com.example.security.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final TokenService tokenService;

  public static final String ADMIN_ROLE = "ROLE_ADMIN";
  public static final String USER_ROLE = "ROLE_USER";

  public UserService(UserRepository userRepository, TokenService tokenService) {
    this.userRepository = userRepository;
    this.tokenService = tokenService;
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  public AuthSuccessDTO login(Authentication authentication) {
    String token = tokenService.generateToken(authentication);
    User user = userRepository.findUserByUsername(authentication.getName())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return new AuthSuccessDTO(token, user.toDto());
  }
}
