package com.pramodvaddiraju.MedNotes.service;

import com.pramodvaddiraju.MedNotes.dto.DiseaseRequestDto;
import com.pramodvaddiraju.MedNotes.dto.DiseaseResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiseaseService {

    DiseaseResponseDto createDisease(DiseaseRequestDto dto);

    DiseaseResponseDto updateDisease(Long id, DiseaseRequestDto dto);

    Page<DiseaseResponseDto> getAllDiseases(Pageable pageable);

    List<DiseaseResponseDto> searchByDiseaseName(String name);

    void deleteById(Long id);
}

