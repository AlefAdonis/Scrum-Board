package com.ufcg.psoft.scrumboard.dto;

public class UserUpdateDTO {
    private String fullName;
    private String email;

    public UserUpdateDTO(String fullName, String userName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

}
