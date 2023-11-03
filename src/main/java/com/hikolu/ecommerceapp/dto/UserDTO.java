package com.hikolu.ecommerceapp.dto;

public class UserDTO {

    // define fields that are needed for the purpose of this dto
    private String username;
    private String email;

    // define constructors
    public UserDTO() {
    }

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // define getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
