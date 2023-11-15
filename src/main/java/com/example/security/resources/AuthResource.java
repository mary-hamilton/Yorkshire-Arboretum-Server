package com.example.security.resources;

import com.example.security.domain.dto.AuthSuccessDTO;
import com.example.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private final UserService userService;

    public AuthResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthSuccessDTO> login(Authentication authentication) {
        AuthSuccessDTO authSuccessDTO = userService.login(authentication);
        return new ResponseEntity<>(authSuccessDTO, HttpStatus.OK);
    }
}
