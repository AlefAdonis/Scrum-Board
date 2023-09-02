package com.ufcg.psoft.scrumboard.dto;

public class UserDTO {
    private String fullName;
    private String userName;
    private String email;

    public UserDTO(String fullName, String userName, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

}
