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

@Service
public class DiseaseServiceImpl implements DiseaseService{

    private ModelMapper modelMapper;
    private DiseaseRepository diseaseRepository;

    public DiseaseServiceImpl(ModelMapper modelMapper, DiseaseRepository diseaseRepository){
        this.diseaseRepository = diseaseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DiseaseResponseDto saveDisease(DiseaseRequestDto diseaseRequestDto) {

        Disease disease = modelMapper.map(diseaseRequestDto, Disease.class);
        Disease saveDisease = diseaseRepository.save(disease);
        return modelMapper.map(saveDisease, DiseaseResponseDto.class);
    }

    @Override
    public Page<DiseaseResponseDto> getAllDiseases(Pageable pageable) {
        return diseaseRepository.findAll(pageable)
                .map(d -> modelMapper.map(d, DiseaseResponseDto.class));

    }

    @Override
    public DiseaseResponseDto getDiseaseByName(String diseaseName) {
        Disease disease = diseaseRepository.findDiseaseByNameIgnoreCase(diseaseName).orElseThrow(
                ()-> new ResourceNotFoundException("Disease Not found with name: " + diseaseName)
        );

        return modelMapper.map(disease, DiseaseResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        diseaseRepository.deleteById(id);
    }
}
