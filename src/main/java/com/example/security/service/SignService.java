package com.example.security.service;


import com.example.security.domain.Sign;
import com.example.security.domain.dto.SignDto;
import com.example.security.repository.SignImageRepository;
import com.example.security.repository.SignRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SignService {

    private final SignRepository signRepository;

    private final SignImageRepository signImageRepository;

    public SignService(SignRepository signRepository, SignImageRepository signImageRepository) {
        this.signRepository = signRepository;
        this.signImageRepository = signImageRepository;
    }

    public void addNewSign(@Valid SignDto signDto) {
        Sign signToSave = new Sign(signDto.getId(), signDto.getTitle(), signDto.getDescription(), signDto.getLat(), signDto.getLon());
        signRepository.save(signToSave).dto();
    }

    public void deleteSign(Integer signId) {
        Sign sign = signRepository.findById(signId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sign not found"));
        signRepository.delete(sign);
    }

    public List<SignDto> getAllSigns() {
        return signRepository
                .findAll()
                .stream()
                .map(Sign::dto)
                .collect(Collectors.toList());
    }

    public void editSign(Integer signId, @Valid SignDto signDto) {
        Sign signToEdit = signRepository.findById(signId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sign not found"));
        signToEdit.setTitle(signDto.getTitle());
        signToEdit.setDescription(signDto.getDescription());
        signToEdit.setLat(signDto.getLat());
        signToEdit.setLon(signDto.getLon());
        signRepository.save(signToEdit);
    }

    public SignDto getSign(Integer signId) {
        Sign sign = signRepository.findById(signId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sign not found"));
        return sign.dto();
    }

}
