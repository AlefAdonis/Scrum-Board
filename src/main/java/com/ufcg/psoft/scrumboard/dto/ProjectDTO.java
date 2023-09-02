package com.ufcg.psoft.scrumboard.dto;

public class ProjectDTO {
    private String projectName;
    private String description;
    private String institution;

    public ProjectDTO(String projectName, String description, String institution) {
        this.projectName = projectName;
        this.description = description;
        this.institution = institution;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getInstitution() {
        return institution;
    }

}
