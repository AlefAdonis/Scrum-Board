package com.ufcg.psoft.scrumboard.dto;

public class ProjectUpdateDTO {
    private String projectName;
    private String description;

    public ProjectUpdateDTO(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectName() {
        return projectName;
    }
}
