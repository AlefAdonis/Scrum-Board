package com.ufcg.psoft.scrumboard.models.responses;

public class UserResponse {
    private String fullName;
    private String userName;
    private String email;

    public UserResponse(String fullName, String userName, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
