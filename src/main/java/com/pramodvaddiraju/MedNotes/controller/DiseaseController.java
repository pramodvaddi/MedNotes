package com.pramodvaddiraju.MedNotes.controller;

import com.pramodvaddiraju.MedNotes.dto.DiseaseRequestDto;
import com.pramodvaddiraju.MedNotes.dto.DiseaseResponseDto;
import com.pramodvaddiraju.MedNotes.service.DiseaseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diseases")
public class DiseaseController {

    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PostMapping
    ResponseEntity<DiseaseResponseDto> saveDisease(@Valid @RequestBody DiseaseRequestDto diseaseRequestDto){
        return ResponseEntity.status(201).body(diseaseService.saveDisease(diseaseRequestDto));
    }

    @GetMapping
    ResponseEntity<Page<DiseaseResponseDto>> getAllDiseases(Pageable pageable){
        return ResponseEntity.ok().body(diseaseService.getAllDiseases(pageable));
    }

    @GetMapping("/search")
    ResponseEntity<DiseaseResponseDto> searchByName(@RequestParam String name){
        return ResponseEntity.ok().body(diseaseService.getDiseaseByName(name));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id){
        diseaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
