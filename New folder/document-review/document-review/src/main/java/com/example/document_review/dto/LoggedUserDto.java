package com.example.document_review.dto;

public class LoggedUserDto {

    private String username;

    public LoggedUserDto(String username) {
        this.username = username;
    }
    public LoggedUserDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
