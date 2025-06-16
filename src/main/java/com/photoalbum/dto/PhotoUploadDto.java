package com.photoalbum.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PhotoUploadDto {
	@NotNull(message = "File is required")
    private MultipartFile file;
    
    @NotEmpty(message = "Description cannot be empty")
    private String description;
   
    
    // Getters and Setters
    public MultipartFile getFile() {
        return file;
    }
    
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}