package com.example.security.service;
import com.example.security.properties.BaseURLProperties;
import com.example.security.repository.SignRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class QRCodeService {

    public static final String ZXING_ERROR_MESSAGE = "Error creating QR code";

    private final SignRepository signRepository;

    private final ZxingService zxingService;

    private final BaseURLProperties baseURLProperties;

    public QRCodeService(SignRepository signRepository, ZxingService zxingService, BaseURLProperties baseURLProperties) {
        this.signRepository = signRepository;
        this.zxingService = zxingService;
        this.baseURLProperties = baseURLProperties;
    }

    public byte[] getQRCode(Integer signId) {
        if (!signRepository.existsById(signId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sign not found");
        }
        String baseUrl = baseURLProperties.getBaseURL();
        String url = baseUrl + signId;
        byte[] qrBytes = null;
        try {
            qrBytes = zxingService.getQrBytes(url);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ZXING_ERROR_MESSAGE);
        }
        return qrBytes;
    }
}
