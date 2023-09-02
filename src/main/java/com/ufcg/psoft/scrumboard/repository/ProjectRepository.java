package com.ufcg.psoft.scrumboard.repository;

import com.ufcg.psoft.scrumboard.models.entities.Project;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProjectRepository {
    private final Map<String, Project> projectMap;

    public ProjectRepository() {
        this.projectMap = new HashMap<>();
    }

    public String addProject(Project project) {
        this.projectMap.put(project.getId(), project);
        return project.getId();
    }

    public Project getProjectByID(String id) {
        return this.projectMap.get(id);
    }

    public String updateProject(Project project) {
        this.projectMap.replace(project.getId(), project);
        return project.getId();
    }

    public void deleteProject(String id) {
        this.projectMap.remove(id);
    }

    public Collection<Project> getAllProjects(){ return this.projectMap.values();}

}
