package com.photoalbum.service;

import com.photoalbum.model.Photo;
import com.photoalbum.repository.PhotoRepository;

import org.springframework.core.io.Resource;  
import org.springframework.core.io.UrlResource; 

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    
    @Value("${app.upload.dir}")
    private String uploadDir;
    
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }
    
    public List<Photo> getUserPhotos(Long userId) {
        return photoRepository.findByUserId(userId);
    }
    
    public void savePhoto(MultipartFile file, String description, Long userId) throws IOException {
        // Проверка и создание директории
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Генерация уникального имени файла
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID() + extension;

        // Сохранение файла
        Path filePath = uploadPath.resolve(newFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Сохранение в БД
        Photo photo = new Photo();
        photo.setFilename(newFilename);
        photo.setDescription(description);
        photo.setUserId(userId);
        photoRepository.save(photo);
    }
    
    public Path getPhotoPath(String filename) {
        return Paths.get(uploadDir).resolve(filename).normalize();
    }

    public Resource getPhotoResource(String filename) throws IOException {
        Path filePath = getPhotoPath(filename);
        return new UrlResource(filePath.toUri());
    }
}