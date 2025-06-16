package com.photoalbum.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.photoalbum.config.CustomUserDetails;
import com.photoalbum.dto.PhotoUploadDto;
import com.photoalbum.service.PhotoService;

import jakarta.validation.Valid;

import org.apache.tika.Tika;


@Controller
@RequestMapping("/photos")
public class PhotoController {
    private final PhotoService photoService;
    

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/upload")
    public String showUploadForm() {
        return "photos/upload";
    }


    @PostMapping("/upload")
    public String handleFileUpload(
            @Valid PhotoUploadDto photoUploadDto,
            BindingResult result,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            RedirectAttributes redirectAttributes) {
        
        // Проверка валидации DTO
        if (result.hasErrors()) {
            return "photos/upload";
        }
        
        MultipartFile file = photoUploadDto.getFile();
        
        // Проверка типа файла
        try {
            Tika tika = new Tika();
            String detectedType = tika.detect(file.getBytes());
            
            if (!detectedType.startsWith("image/")) {
                result.rejectValue("file", "error.file", "Only image files are allowed");
                return "photos/upload";
            }
            
            // Сохранение файла
            photoService.savePhoto(file, photoUploadDto.getDescription(), userDetails.getUserId());
            redirectAttributes.addFlashAttribute("message", "Photo uploaded successfully!");
            
        } catch (IOException e) {
            result.rejectValue("file", "error.file", "Failed to process file");
            return "photos/upload";
        }
        
        return "redirect:/";
    }    
    @GetMapping("/view/{filename:.+}")
    public ResponseEntity<Resource> viewPhoto(@PathVariable String filename) {
    	try {
            Resource resource = photoService.getPhotoResource(filename);
            
            if (resource.exists() && resource.isReadable()) {
                java.nio.file.Path filePath = photoService.getPhotoPath(filename);
                String contentType = Files.probeContentType(filePath);
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(
                            contentType != null ? contentType : "application/octet-stream"))
                        .body(resource);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}