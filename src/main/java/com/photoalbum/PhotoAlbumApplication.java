package com.photoalbum;

import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

import java.nio.file.Paths;
import java.io.IOException;

@SpringBootApplication
public class PhotoAlbumApplication {

	@Value("${app.upload.dir}")
    private String uploadDir;
	
	public static void main(String[] args) {
		SpringApplication.run(PhotoAlbumApplication.class, args);
	}

	// создание директории загрузок
	@PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
            System.out.println("Created upload directory: " + uploadDir);
        } catch (IOException e) {
            System.err.println("Could not create upload directory: " + e.getMessage());
            throw new RuntimeException("Could not create upload folder!", e);
        }
    }
}
