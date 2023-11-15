package com.example.security.service;

import com.example.security.domain.User;
import com.example.security.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private String initialUsername = "LittleSteve";
    private String initialPassword = "password";
    private String encodedPassword = "password1";

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoderMock;

    @InjectMocks
    private UserService userServiceTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createInitialUserAlreadyExists() {

        when(userRepositoryMock.existsUserByUsername(initialUsername)).thenReturn(true);

        userServiceTest.createInitial(initialUsername, initialPassword);

        verify(userRepositoryMock, never()).save(any(User.class));
    }

    @Test
    void createInitialUserDoesNotAlreadyExist() {

        when(userRepositoryMock.existsUserByUsername(initialUsername)).thenReturn(false);
        when(passwordEncoderMock.encode(initialPassword)).thenReturn(encodedPassword);

        userServiceTest.createInitial(initialUsername, initialPassword);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryMock).save(argumentCaptor.capture());
        User initialAdmin = argumentCaptor.getValue();
        assertEquals(initialUsername, initialAdmin.getUsername());
        assertEquals(encodedPassword, initialAdmin.getPassword());
    }
}