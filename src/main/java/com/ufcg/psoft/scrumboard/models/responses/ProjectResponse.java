package com.ufcg.psoft.scrumboard.models.responses;

import java.util.HashMap;

public class ProjectResponse {
    private String projectName;
    private String projectDescription;
    private String partnerInstitution;

    private final HashMap<String, String> associates;

    public ProjectResponse(String projectName, String projectDescription, String partnerInstitution, HashMap<String, String> associates) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.partnerInstitution = partnerInstitution;
        this.associates = associates;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getPartnerInstitution() {
        return partnerInstitution;
    }

    public void setPartnerInstitution(String partnerInstitution) {
        this.partnerInstitution = partnerInstitution;
    }

    public HashMap<String, String> getAssociates() {
        return associates;
    }
}
