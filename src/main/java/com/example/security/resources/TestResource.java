package com.example.security.resources;

import com.example.security.domain.dto.ResultResponseDto;
import com.example.security.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Profile("test")
public class TestResource {

    private final UserRepository userRepository;

    public TestResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @DeleteMapping("/users")
    public ResponseEntity<ResultResponseDto> resetUsers() {
        userRepository.deleteAll();
        return new ResponseEntity<>(new ResultResponseDto("Done"), HttpStatus.OK);
    }
}
