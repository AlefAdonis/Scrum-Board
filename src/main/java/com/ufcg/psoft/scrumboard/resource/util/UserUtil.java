package com.ufcg.psoft.scrumboard.resource.util;

import com.ufcg.psoft.scrumboard.exception.user.RoleNotValidException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotFoundException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotInProjectException;
import com.ufcg.psoft.scrumboard.models.entities.Project;
import com.ufcg.psoft.scrumboard.models.entities.users.User;
import com.ufcg.psoft.scrumboard.repository.ProjectRepository;
import com.ufcg.psoft.scrumboard.repository.UserRepository;
import com.ufcg.psoft.scrumboard.resource.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserUtil {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  ProjectRepository projectRepository;

    public User verifyUser(String userName) throws UserNotFoundException {
        User user = this.userRepository.getUserByUserName(userName);
        if(user == null) {
            throw new UserNotFoundException("Usuário não encontrado");
        }
        return user;
    }

    public User isInProject(String projectId, String userName) throws UserNotInProjectException {
        Project project = this.projectRepository.getProjectByID(projectId);
        if(project.getAssociates().containsKey(userName)){
            return this.userRepository.getUserByUserName(userName);
        }else{throw new UserNotInProjectException();}
    }

    public User canEnterInUserStory(String userName, Project project) throws UserNotInProjectException, RoleNotValidException {
        User user = isInProject(project.getId(), userName);

        HashSet rolesPossibles = new HashSet<>();
        rolesPossibles.add(Role.ESTAGIARIO.getRole());
        rolesPossibles.add(Role.DESENVOLVEDOR.getRole());
        rolesPossibles.add(Role.PESQUISADOR.getRole());
        if (rolesPossibles.contains(project.getAssociates().get(userName))) {
            return user;
        }else {throw new RoleNotValidException("Função de usuário inválida para esta ação.");}
    }

}
