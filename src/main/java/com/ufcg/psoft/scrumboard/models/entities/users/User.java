package com.ufcg.psoft.scrumboard.models.entities.users;

public class User {
    private String fullName;
    private String userName;
    private String email;

    private UserRole role;

    public User(String fullName, String userName, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.role = null;
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

    public String getRole() {
        return role.getRole();
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
