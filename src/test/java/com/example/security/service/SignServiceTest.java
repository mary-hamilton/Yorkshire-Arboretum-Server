package com.example.security.service;

import com.example.security.domain.Sign;
import com.example.security.domain.SignImage;
import com.example.security.domain.dto.SignDto;
import com.example.security.repository.SignImageRepository;
import com.example.security.repository.SignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;


public class SignServiceTest {



    @Mock
    private SignRepository signRepositoryMock;

    @Mock
    private SignImageRepository signImageRepositoryMock;

    @Mock
    private SignImageService signImageServiceMock;

    @Mock
    private SignDto signDtoMock;

    @Mock
    private SignImage signImageMock;

    @Mock
    private SignDto signDtoMockedResult;

    @InjectMocks
    private SignService signServiceTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNewSignSuccessWithImage() {
        String title = "my title";
        String description = "my description";
        double lat = 1.23;
        double lon = 2.34;
        MockMultipartFile mockedImage = new MockMultipartFile("treeImage", "treeImage", MediaType.IMAGE_PNG_VALUE, new byte[] {1, 2, 3});
        SignImage signImage = new SignImage(1, new byte[] {1, 2, 3});
        Sign savedSign = new Sign(1, title, description, lat, lon, signImage);
        when(signRepositoryMock.save(any())).thenReturn(savedSign);
        signServiceTest.addNewSign(title, description, lat, lon, mockedImage);
        verify(signImageServiceMock, times(1)).saveSignImage(mockedImage);
    }

    @Test
    void addNewSignSuccessWithoutImage() {
        String title = "my title";
        String description = "my description";
        double lat = 1.23;
        double lon = 2.34;
        MultipartFile nullFile = null;
        SignImage signImage = new SignImage(1, new byte[] {1, 2, 3});
        Sign savedSign = new Sign(1, title, description, lat, lon, signImage);
        when(signRepositoryMock.save(any())).thenReturn(savedSign);
        signServiceTest.addNewSign(title, description, lat, lon, nullFile);
        verify(signImageServiceMock, times(0)).saveSignImage(nullFile);
    }

    @Test
    void deleteSignSuccess() {
        Integer signId = 1;
        when(signRepositoryMock.existsById(signId)).thenReturn(true);
        verify(signRepositoryMock, times(1)).deleteById(signId);
    }


}
