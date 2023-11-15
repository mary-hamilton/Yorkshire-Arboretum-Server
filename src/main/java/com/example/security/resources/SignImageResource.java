package com.example.security.resources;

import com.example.security.domain.dto.ImageResponseDto;
import com.example.security.domain.dto.ResultResponseDto;
import com.example.security.service.SignImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/sign-images")
public class SignImageResource {

    private final SignImageService signImageService;

    public SignImageResource(SignImageService signImageService) {
        this.signImageService = signImageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getSignImage(@PathVariable("id") Integer signImageId) {
        byte[] signImage = signImageService.getSignImage(signImageId);
        return new ResponseEntity<>(signImage, HttpStatus.OK);
    }

}
