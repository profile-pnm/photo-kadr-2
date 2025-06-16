package com.photoalbum.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;
    
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    @NotEmpty(message = "Password confirmation cannot be empty")
    private String confirmPassword;
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}