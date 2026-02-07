package com.pramodvaddiraju.MedNotes.service;

import com.pramodvaddiraju.MedNotes.dto.DiseaseRequestDto;
import com.pramodvaddiraju.MedNotes.dto.DiseaseResponseDto;
import com.pramodvaddiraju.MedNotes.entity.Disease;
import com.pramodvaddiraju.MedNotes.exception.ResourceNotFoundException;
import com.pramodvaddiraju.MedNotes.repository.DiseaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DiseaseServiceImpl implements DiseaseService {

    private final ModelMapper modelMapper;
    private final DiseaseRepository diseaseRepository;

    public DiseaseServiceImpl(ModelMapper modelMapper,
                              DiseaseRepository diseaseRepository) {
        this.modelMapper = modelMapper;
        this.diseaseRepository = diseaseRepository;
    }

    // CREATE
    @Override
    public DiseaseResponseDto createDisease(DiseaseRequestDto dto) {
        Disease disease = modelMapper.map(dto, Disease.class);
        Disease saved = diseaseRepository.save(disease);
        return modelMapper.map(saved, DiseaseResponseDto.class);
    }

    // UPDATE (THIS FIXES DUPLICATES)
    @Override
    public DiseaseResponseDto updateDisease(Long id, DiseaseRequestDto dto) {

        Disease disease = diseaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disease not found"));

        disease.setDiseaseName(dto.getDiseaseName());
        disease.setDescription(dto.getDescription());
        disease.setMedications(dto.getMedications());

        Disease updated = diseaseRepository.save(disease);
        return modelMapper.map(updated, DiseaseResponseDto.class);
    }

    @Override
    public Page<DiseaseResponseDto> getAllDiseases(Pageable pageable) {
        return diseaseRepository.findAll(pageable)
                .map(d -> modelMapper.map(d, DiseaseResponseDto.class));
    }

    @Override
    public List<DiseaseResponseDto> searchByDiseaseName(String name) {
        return diseaseRepository
                .findByDiseaseNameContainingIgnoreCase(name)
                .stream()
                .map(d -> modelMapper.map(d, DiseaseResponseDto.class))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        diseaseRepository.deleteById(id);
    }
}
