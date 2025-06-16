package com.photoalbum.controller;

import com.photoalbum.config.CustomUserDetails;
import com.photoalbum.model.Photo;
import com.photoalbum.service.PhotoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private final PhotoService photoService;
    
    public MainController(PhotoService photoService) {
        this.photoService = photoService;
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