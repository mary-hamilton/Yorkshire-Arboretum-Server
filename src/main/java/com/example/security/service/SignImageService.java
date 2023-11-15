package com.example.security.service;

import com.example.security.domain.SignImage;
import com.example.security.domain.dto.ErrorResponseDto;
import com.example.security.repository.SignImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

import static com.example.security.utils.ImageUtil.compressImage;
import static com.example.security.utils.ImageUtil.decompressImage;

@Service
@Transactional
public class SignImageService {

    private final SignImageRepository signImageRepository;

    public SignImageService(SignImageRepository signImageRepository) {
        this.signImageRepository = signImageRepository;
    }

    public void validateSignImage(MultipartFile signImageFile) {
        InputStream inputStream;
        try {
            inputStream = signImageFile.getInputStream();
            if (ImageIO.read(inputStream) == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not an image.");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No image found.");
        }
    }

    public void saveSignImage(MultipartFile signImageFile) {
        validateSignImage(signImageFile);
        byte[] compressedImage;
        try {
            compressedImage = compressImage(signImageFile.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image compression failed.");
        }
        SignImage signImage = new SignImage();
        signImage.setImageData(compressedImage);
        signImageRepository.save(signImage);
    }

    public byte[] getSignImage(Integer signImageId) {
        SignImage signImage = signImageRepository.findById(signImageId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
        return decompressImage(signImage.getImageData());
    }

}
