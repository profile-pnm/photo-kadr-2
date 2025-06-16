package com.photoalbum.controller;

import com.photoalbum.config.CustomUserDetails;
import com.photoalbum.model.Photo;
import com.photoalbum.service.PhotoService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Controller
public class MainController {
    private final PhotoService photoService;
    
    public MainController(PhotoService photoService) {
        this.photoService = photoService;
    }
    
    // для получения дампа базы данных
    @RestController
    public class DbExportController {
        
        private final JdbcTemplate jdbcTemplate;
        
        public DbExportController(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }
        
        // ввести localhost:8080/export-db и в корне проекта будет сохранен дамп
        @GetMapping("/export-db")
        public String exportDb() throws Exception {
            String exportPath = "./h2_export.sql";
            jdbcTemplate.execute("SCRIPT TO '" + exportPath + "'");
            return "DB exported to: " + exportPath;
        }
    }
    
    @GetMapping("/")
    public String home(Model model, 
                      @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails != null) {
            // запрос вывода фотографий для конкретного пользователя
        	List<Photo> photos = photoService.getUserPhotos(userDetails.getUserId());
            model.addAttribute("photos", photos);
        }
        return "index";
    }
}