package com.pramodvaddiraju.MedNotes.controller;

import com.pramodvaddiraju.MedNotes.dto.DiseaseRequestDto;
import com.pramodvaddiraju.MedNotes.dto.DiseaseResponseDto;
import com.pramodvaddiraju.MedNotes.service.DiseaseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/diseases")
public class DiseaseController {

    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<DiseaseResponseDto> createDisease(
            @Valid @RequestBody DiseaseRequestDto dto) {

        return ResponseEntity.status(201)
                .body(diseaseService.createDisease(dto));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<DiseaseResponseDto> updateDisease(
            @PathVariable Long id,
            @Valid @RequestBody DiseaseRequestDto dto) {

        return ResponseEntity.ok(diseaseService.updateDisease(id, dto));
    }

    @GetMapping
    public ResponseEntity<Page<DiseaseResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(diseaseService.getAllDiseases(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<List<DiseaseResponseDto>> search(@RequestParam String name) {
        return ResponseEntity.ok(diseaseService.searchByDiseaseName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        diseaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
