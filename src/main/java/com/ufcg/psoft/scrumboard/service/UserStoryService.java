package com.ufcg.psoft.scrumboard.service;

import com.ufcg.psoft.scrumboard.dto.UserStoryDTO;
import com.ufcg.psoft.scrumboard.exception.NotChangeUserStory;
import com.ufcg.psoft.scrumboard.exception.ProjectNotFoundException;
import com.ufcg.psoft.scrumboard.exception.UserAlreadySubscribedException;
import com.ufcg.psoft.scrumboard.exception.UserStoryNotFoundException;
import com.ufcg.psoft.scrumboard.exception.user.RoleNotValidException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotFoundException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotInProjectException;
import com.ufcg.psoft.scrumboard.models.entities.Project;
import com.ufcg.psoft.scrumboard.models.entities.Task;
import com.ufcg.psoft.scrumboard.models.entities.userStories.*;
import com.ufcg.psoft.scrumboard.models.entities.users.User;
import com.ufcg.psoft.scrumboard.models.responses.UserStoryResponse;
import com.ufcg.psoft.scrumboard.observer.Notification;
import com.ufcg.psoft.scrumboard.repository.StateRepository;
import com.ufcg.psoft.scrumboard.repository.UserStoryRepository;

import com.ufcg.psoft.scrumboard.resource.enums.Role;
import com.ufcg.psoft.scrumboard.resource.enums.StateUserStory;
import com.ufcg.psoft.scrumboard.resource.mapper.UserStoryMapper;
import com.ufcg.psoft.scrumboard.resource.util.ProjectUtil;
import com.ufcg.psoft.scrumboard.resource.util.UserStoryUtil;
import com.ufcg.psoft.scrumboard.resource.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserStoryService {

    @Autowired
    private UserStoryRepository userStoryRepository;

    @Autowired
    private ProjectUtil projectUtil;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private UserStoryUtil userStoryUtil;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private UserStoryMapper storyMapper;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private Notification notificator;

    public String saveNewUserStory(String projectId, String userName, UserStoryDTO userStoryDTO) throws ProjectNotFoundException, UserNotFoundException {
        Project project = projectUtil.verifyProject(projectId);
        User user = userUtil.verifyUser(userName);
        projectUtil.verifyUserAssociated(project, user);

        UserStory newUserStory = new UserStory(userStoryDTO.getDescription(), userStoryDTO.getTitle());
        projectUtil.addUserStoryInProject(project, newUserStory);

        return userStoryRepository.addUserStory(newUserStory);
    }
    
    public void removeUserStory(String projectId, String userStoryId, String userName) throws UserStoryNotFoundException, ProjectNotFoundException, UserNotFoundException {
        Project project = projectUtil.verifyProject(projectId);
        User user = userUtil.verifyUser(userName);
        projectUtil.verifyUserAssociated(project, user);

    	UserStory userStory = userStoryUtil.getUserStory(userStoryId);

        project.getUserStories().remove(userStory);
        userStoryRepository.deleteUserStory(userStoryId);

    }
    
    public String updateUserStory(String projectId, String userStoryId, String userName, UserStoryDTO userStoryDTO) throws UserStoryNotFoundException, ProjectNotFoundException, UserNotFoundException {
        this.verifyConditionsOfUserStory(userName,projectId);

        UserStory userStoryToUpdate = userStoryUtil.getUserStory(userStoryId);
        updateUserStoryFound(userStoryDTO, userStoryToUpdate, userName);

        return userStoryRepository.updateUserStory(userStoryToUpdate);
    }
    
    private void updateUserStoryFound(UserStoryDTO userStoryDTO, UserStory userStoryToUpdate,String username) {
        if(!(userStoryToUpdate.getDescription().equals(userStoryDTO.getDescription()))){
            this.notificator.mudancaDescricao(username);
        }
    	userStoryToUpdate.setDescription(userStoryDTO.getDescription());
    	userStoryToUpdate.setTitle(userStoryDTO.getTitle());
    }
    
    public UserStoryResponse getUserStoryResponseById(String projectId, String userStoryId, String userName) throws UserStoryNotFoundException, ProjectNotFoundException, UserNotFoundException {
        this.verifyConditionsOfUserStory(userName,projectId);

        UserStory userStory = userStoryUtil.getUserStory(userStoryId);
        return storyMapper.userStoryToResponse(userStory);
    }

    public StateUserStory[] getAllStates(String projectId, String userName) throws UserNotFoundException, ProjectNotFoundException {
        projectUtil.verifyProjectScrum(projectId, userName);
        return StateUserStory.values();
    }

    public void joinInUserStory(String userStoryId, String userName) throws UserStoryNotFoundException, UserNotInProjectException, RoleNotValidException {

    	Project project = this.projectService.getProjectByUserStory(userStoryId);
        if (project == null) {throw new UserStoryNotFoundException("UserStory não foi encontrada.");}
        User user = this.userUtil.canEnterInUserStory(userName, project);
        this.userStoryRepository.getUserStoryByID(userStoryId).getUsers().put(userName, user);

        changeState(userStoryId, userName);
    }

    public void addUserToUserStory(String userStoryId, String scrumUserName, String userName) throws UserStoryNotFoundException, UserNotInProjectException, RoleNotValidException, UserNotFoundException, ProjectNotFoundException {
    	Project project = this.projectService.getProjectByUserStory(userStoryId);

        if (project == null) {
            throw new ProjectNotFoundException("Projeto não foi encontrado.");
        }
        if(project.getAssociates().get(scrumUserName) == null){
        	throw new UserNotFoundException("Usuário não encontrado");
        }
        else if (!project.getAssociates().get(scrumUserName).equals(Role.SCRUM_MASTER.getRole())) {
        	throw new RoleNotValidException("O usuário não é Scrum master deste projeto");
        }

        User user = this.userUtil.canEnterInUserStory(userName, project);
        this.userStoryRepository.getUserStoryByID(userStoryId).getUsers().put(userName, user);
            
        changeState(userStoryId, userName);
    }

    public void addTaskToUserStory(Task task, String userName, String projectId, String userStoryId)
                                                                throws UserNotFoundException,
                                                                       ProjectNotFoundException{

        this.verifyConditionsOfUserStory(userName,projectId);

        UserStory userStory = this.userStoryRepository.getUserStoryByID(userStoryId);

        userStory.setTask(task.getId(), task);
    }

    public Task getTaskFromUserStory(String taskId, String userName, String projectId, String userStoryId) throws UserNotFoundException, ProjectNotFoundException {
        this.verifyConditionsOfUserStory(userName, projectId);

        UserStory us = this.userStoryRepository.getUserStoryByID(userStoryId);
        return us.getTasks().get(taskId);
    }

    private void verifyConditionsOfUserStory(String userName, String projectId) throws UserNotFoundException, ProjectNotFoundException {
        User user = this.userUtil.verifyUser(userName);
        Project project = this.projectUtil.verifyProject(projectId);
        this.projectUtil.verifyUserAssociated(project, user);

    }

    public void WorkInProgressChangeToVerify(String projectId, String userStoryId, String userName) throws UserNotFoundException, ProjectNotFoundException, UserStoryNotFoundException, NotChangeUserStory {
        this.verifyConditionsOfUserStory(userName, projectId);
        UserStory userStory = userStoryUtil.getUserStory(userStoryId);

        if (!userStory.getState().getState().equals(StateUserStory.WIP.getState())) {
            throw new NotChangeUserStory("Não é possível mudar estágio da UserStory");
        }

        ToVerify initialState = new ToVerify();
        initialState.setContextUS(userStory);
        userStory.changeState(initialState);

        this.notificator.mudancaEstagio(userName);
    }

    public void toVerifyChangeDone(String projectId, String userStoryId, String userName) throws UserNotFoundException, ProjectNotFoundException, UserStoryNotFoundException, NotChangeUserStory {
        this.projectUtil.verifyProject(projectId);
        userUtil.verifyUser(userName);
        projectUtil.verifyProjectScrum(projectId, userName);
        UserStory userStory = userStoryUtil.getUserStory(userStoryId);

        if (!userStory.getState().getState().equals(StateUserStory.TO_VERIFY.getState())) {
            throw new NotChangeUserStory("Não é possível mudar estágio da UserStory");
        }

        changeState(userStoryId, userName);
    }

	
    private void changeState(String userStoryId, String userName) {
        UserStory us = this.userStoryRepository.getUserStoryByID(userStoryId);
        us.getState().nextState();

        User user = us.getUsers().get(userName);

        if(!(user == null)){
            if(user.getRole().equals(Role.PRODUCT_OWNER.getRole()) &&
                    us.getState().getState().equals(StateUserStory.DONE.getState())){
                this.notificator.usFinalizada();
            }
        }
        this.notificator.mudancaEstagio(userName);
    }

    public void updateTaskInUserStory(Task taskUpdated, String userName, String projectId, String userStoryId) throws UserNotFoundException, ProjectNotFoundException, UserStoryNotFoundException {
        this.verifyConditionsOfUserStory(userName, projectId);
        UserStory us = this.userStoryRepository.getUserStoryByID(userStoryId);
        if(us == null){
            throw new UserStoryNotFoundException("User Story não é válida!");
        }

        if(us.getUsers().get(userName).getRole().equals(Role.SCRUM_MASTER.getRole())){
            if(!(us.getTasks().get(taskUpdated.getId()).getStatus() == taskUpdated.getStatus())){
                this.notificator.taskRealizada();
            }
        }

        us.getTasks().put(taskUpdated.getId(), taskUpdated);

        if(this.verifyIfAllTasksAreDone(us.getId())){
            this.changeState(userStoryId, userName);
        }
    }

    private boolean verifyIfAllTasksAreDone(String userStoryId) {
        UserStory us = this.userStoryRepository.getUserStoryByID(userStoryId);
        Collection<Task> tasks = us.getTasks().values();
        if(tasks.isEmpty()){
            return false;
        }
        int quantTasksUndone = tasks.size();
        for(Task t: tasks){
            if(t.getStatus()){
                quantTasksUndone--;
            }
        }
        return quantTasksUndone == 0;
    }

    public void deleteTaskFromUserStory(String taskId, String userName, String projectId, String userStoryId) throws UserNotFoundException, ProjectNotFoundException, UserStoryNotFoundException {
        this.verifyConditionsOfUserStory(userName, projectId);
        UserStory us = this.userStoryRepository.getUserStoryByID(userStoryId);
        if(us == null){
            throw new UserStoryNotFoundException("User Story não é válida!");
        }
        us.getTasks().remove(taskId);
    }


    public void subscribeUserToUserStory(String projectId, String userStoryId, String username) throws UserNotFoundException, ProjectNotFoundException, UserStoryNotFoundException, UserAlreadySubscribedException {
        this.verifyConditionsOfUserStory(username, projectId);

        if(this.userStoryRepository.getUserStoryByID(userStoryId) == null){
            throw new UserStoryNotFoundException("User Story não existe!");
        }

        this.notificator.subscribe(username);

    }
}