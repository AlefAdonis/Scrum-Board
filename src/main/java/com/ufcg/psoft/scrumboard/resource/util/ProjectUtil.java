package com.ufcg.psoft.scrumboard.resource.util;

import com.ufcg.psoft.scrumboard.exception.ProjectNotFoundException;
import com.ufcg.psoft.scrumboard.exception.UserAlreayInProjectException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotFoundException;
import com.ufcg.psoft.scrumboard.models.entities.Project;
import com.ufcg.psoft.scrumboard.models.entities.userStories.UserStory;
import com.ufcg.psoft.scrumboard.models.entities.users.User;
import com.ufcg.psoft.scrumboard.repository.ProjectRepository;
import com.ufcg.psoft.scrumboard.resource.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProjectUtil {

    @Autowired
    private  ProjectRepository projectRepository;

    public void verifyUserNotAssociated(Project project, User association) throws UserAlreayInProjectException {
        if(userInProject(project, association)) {
            throw new UserAlreayInProjectException("Usuário já associado ao projeto!");
        }
    }

    public void verifyUserAssociated(Project project, User association) throws UserNotFoundException {
        if(!userInProject(project, association)) {
            throw new UserNotFoundException("Usuário não está associado ao projeto!");
        }
    }

    private boolean userInProject(Project project, User association) {
        return project.getAssociates().containsKey(association.getUserName());
    }

    public Project verifyProjectScrum(String projectId, String userName) throws UserNotFoundException, ProjectNotFoundException {
        Project  project = verifyProject(projectId);
        String role = project.getAssociates().get(userName);

        if(!Objects.equals(role, Role.SCRUM_MASTER.getRole())) {
            throw new UserNotFoundException("Usuario não é scrum master do projeto!");
        }
        return project;
    }

    public Project verifyProject(String projectId) throws ProjectNotFoundException {
        Project project = this.projectRepository.getProjectByID(projectId);
        if(project == null) {
            throw new ProjectNotFoundException("Projeto não encontrado!");
        }
        return project;
    }

    public void addUserStoryInProject(Project project, UserStory us) {
        project.getUserStories().put(us, us.getState());
    }
}
