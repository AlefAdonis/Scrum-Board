package com.ufcg.psoft.scrumboard.dto;

public class UserStoryDTO {

    private String description;
    private String title;

    public UserStoryDTO(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

}
