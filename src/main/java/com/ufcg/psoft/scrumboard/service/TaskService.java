package com.ufcg.psoft.scrumboard.service;

import com.ufcg.psoft.scrumboard.dto.TaskDTO;
import com.ufcg.psoft.scrumboard.exception.ProjectNotFoundException;
import com.ufcg.psoft.scrumboard.exception.TaskIsAlreadyDone;
import com.ufcg.psoft.scrumboard.exception.TaskNotFoundException;
import com.ufcg.psoft.scrumboard.exception.UserStoryNotFoundException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotFoundException;
import com.ufcg.psoft.scrumboard.models.entities.Task;
import com.ufcg.psoft.scrumboard.models.responses.TaskResponse;
import com.ufcg.psoft.scrumboard.repository.TaskRepository;
import com.ufcg.psoft.scrumboard.resource.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserStoryService userStoryService;

    @Autowired
    private TaskMapper taskMapper;


    public String saveNewTasks(TaskDTO taskDTO, String userName, String projectId, String userStoryId)
            throws UserNotFoundException, ProjectNotFoundException {
        Task newTask = new Task(taskDTO.getDescription());
        this.userStoryService.addTaskToUserStory(newTask, userName, projectId, userStoryId);
        return this.taskRepository.addTask(newTask);
    }

    public TaskResponse getTaskById(String taskId, String userName, String projectId, String userStoryId)
                                    throws TaskNotFoundException, UserNotFoundException, ProjectNotFoundException {

        Task task = this.userStoryService.getTaskFromUserStory(taskId, userName, projectId, userStoryId);
        if(task == null){
            throw new TaskNotFoundException("Task não existe para essa User Story!");
        }
        return taskMapper.taskToDto(task);
    }

    public String updateTask(String taskId, TaskDTO taskDTOUpdated, String userName, String projectId, String userStoryId)
            throws TaskNotFoundException, UserNotFoundException, ProjectNotFoundException, UserStoryNotFoundException {
        Task taskToUpdate = this.userStoryService.getTaskFromUserStory(taskId,userName,projectId, userStoryId);

        taskToUpdate.setDescription(taskDTOUpdated.getDescription());
        this.userStoryService.updateTaskInUserStory(taskToUpdate, userName, projectId, userStoryId);
        return taskRepository.updateTask(taskToUpdate);
    }

    public void removeTask(String taskId, String userName, String projectId, String userStoryId) throws TaskNotFoundException, UserNotFoundException, ProjectNotFoundException, UserStoryNotFoundException {
        if(taskRepository.getTaskByID(taskId) == null){
            throw new TaskNotFoundException("Task não foi encontrada!");
        }

        this.userStoryService.deleteTaskFromUserStory(taskId, userName, projectId, userStoryId);
        taskRepository.removeTask(taskId);
    }

    public String updateTaskToDone(String taskId, String userName, String projectId, String userStoryId) throws TaskNotFoundException, UserNotFoundException, ProjectNotFoundException, UserStoryNotFoundException, TaskIsAlreadyDone {
        if(taskRepository.getTaskByID(taskId) == null){
            throw new TaskNotFoundException("Task não foi encontrada!");
        }

        Task taskToDone = this.taskRepository.getTaskByID(taskId);

        if(taskToDone.getStatus()){
            throw new TaskIsAlreadyDone("Task já marcada como concluida!");
        }
        taskToDone.setStatus(true);

        this.userStoryService.updateTaskInUserStory(taskToDone, userName, projectId, userStoryId);
        return taskRepository.updateTask(taskToDone);
    }
}
