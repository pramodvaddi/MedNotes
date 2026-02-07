package com.pramodvaddiraju.MedNotes.service;

import com.pramodvaddiraju.MedNotes.dto.DiseaseRequestDto;
import com.pramodvaddiraju.MedNotes.dto.DiseaseResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiseaseService {

    DiseaseResponseDto saveDisease(DiseaseRequestDto diseaseRequestDto);
    Page<DiseaseResponseDto> getAllDiseases(Pageable pageable);
    DiseaseResponseDto getDiseaseByName(String diseaseName);
    void deleteById(Long id);


}
