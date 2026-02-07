package com.pramodvaddiraju.MedNotes.dto;

import jakarta.validation.constraints.NotBlank;

public class DiseaseRequestDto {

    @NotBlank(message = "Disease name must not be null")
    private String diseaseName;

    @NotBlank(message = "Description must not be null")
    private String description;

    @NotBlank(message = "Medications must not be null")
    private String medications;

    public DiseaseRequestDto(){

    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }
}
