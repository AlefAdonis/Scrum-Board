package com.ufcg.psoft.scrumboard.models.entities;

import com.ufcg.psoft.scrumboard.models.entities.userStories.State;
import com.ufcg.psoft.scrumboard.models.entities.userStories.UserStory;
import com.ufcg.psoft.scrumboard.models.entities.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Project {

    private final String id;
    private String projectName;
    private String projectDescription;
    private String partnerInstitution;
    private final HashMap<String, String> associates = new HashMap<>();
    private final HashMap<UserStory, State> userStories;

    public Project(String projectName, String projectDescription, String partnerInstitution, User user) {
        this.id = UUID.randomUUID().toString();
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.partnerInstitution = partnerInstitution;
        this.associates.put(user.getUserName(), user.getRole());
        this.userStories = new HashMap<>();
    }

    public String getId() {
        return id;
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
        return this.partnerInstitution;
    }

    public void setPartnerInstitution(String partnerInstitution) {
        this.partnerInstitution = partnerInstitution;
    }

    public HashMap<String, String> getAssociates() {
        return this.associates;
    }

    public HashMap<UserStory, State> getUserStories() {
        return this.userStories;
    }

}
