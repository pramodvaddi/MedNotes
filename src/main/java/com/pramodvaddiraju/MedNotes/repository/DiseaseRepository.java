package com.pramodvaddiraju.MedNotes.repository;

import com.pramodvaddiraju.MedNotes.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    Optional<Disease> findDiseaseByNameIgnoreCase(String diseaseName);

}
