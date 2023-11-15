package com.example.security.service;

import com.example.security.domain.Sign;
import com.example.security.domain.dto.SignDto;
import com.example.security.repository.SignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.data.util.Lazy.empty;

class SignServiceTest {
    @Mock
    private SignRepository signRepositoryMock;

    @InjectMocks
    private SignService signServiceTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successfulGetSign() {
        Integer signId = 9876;
        Sign fakeSign = new Sign(signId, "title", "Description text", 53.37424053657951, -1.47420922986738);
        when(signRepositoryMock.findById(signId)).thenReturn(Optional.of(fakeSign));
        SignDto signDto = signServiceTest.getSign(signId);
        assertEquals(fakeSign.getId(), signDto.getId());
        assertEquals(fakeSign.getTitle(), signDto.getTitle());
        assertEquals(fakeSign.getDescription(), signDto.getDescription());
        assertEquals(fakeSign.getLat(), signDto.getLat());
        assertEquals(fakeSign.getLon(), signDto.getLon());
    }

    @Test
    void unsuccessfulGetSign() {
        Integer signId = 9876;
        when(signRepositoryMock.findById(signId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> signServiceTest.getSign(signId));
    }

}

//Test that the exception does and does not get thrown
    // mock empty id find by id is empty -> get exception error is thrown
    // mock filled id -> check there is returning a sign.dto()

