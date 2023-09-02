package com.ufcg.psoft.scrumboard.dto;

public class TaskDTO {
    private String description;

    public TaskDTO(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
