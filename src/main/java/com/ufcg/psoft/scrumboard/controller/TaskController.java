package com.ufcg.psoft.scrumboard.controller;

import com.ufcg.psoft.scrumboard.dto.TaskDTO;
import com.ufcg.psoft.scrumboard.exception.TaskNotFoundException;
import com.ufcg.psoft.scrumboard.models.responses.TaskResponse;
import com.ufcg.psoft.scrumboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("{projectId}/{userStoryId}/")
    public ResponseEntity<?> createTask(
            @RequestBody TaskDTO taskDTO,
            @PathVariable String projectId,
            @PathVariable String userStoryId,
            @RequestParam String userName) {

        try{
            String taskId = taskService.saveNewTasks(taskDTO, userName, projectId, userStoryId);
            return new ResponseEntity<>(taskId, HttpStatus.CREATED);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("{projectId}/{userStoryId}/{taskId}/")
    public ResponseEntity<?> getTaskById(
            @PathVariable String taskId,
            @RequestParam String userName,
            @PathVariable String projectId,
            @PathVariable String userStoryId) {

        TaskResponse task;
        try {
            task = taskService.getTaskById(taskId, userName, projectId, userStoryId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{projectId}/{userStoryId}/{taskId}/")
    public ResponseEntity<String> updateTask(
            @PathVariable String taskId,
            @RequestBody TaskDTO taskDTO,
            @RequestParam String userName,
            @PathVariable String projectId,
            @PathVariable String userStoryId
            )
    {
        String taskUpdatedId;
        try {
            taskUpdatedId = taskService.updateTask(taskId, taskDTO, userName, projectId, userStoryId);
            return new ResponseEntity<>(taskUpdatedId, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{projectId}/{userStoryId}/{taskId}/")
    public ResponseEntity<String> removeTask(
            @PathVariable String taskId,
            @RequestParam String userName,
            @PathVariable String projectId,
            @PathVariable String userStoryId) {
        try {
            taskService.removeTask(taskId, userName, projectId, userStoryId);
            return new ResponseEntity<>("Removido com sucesso", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("task-to-done/{projectId}/{userStoryId}/{taskId}/")
    public ResponseEntity<String> updateTaskToDone(
            @PathVariable String taskId,
            @RequestParam String userName,
            @PathVariable String projectId,
            @PathVariable String userStoryId) {
        try {
            String taskUpdatedId = taskService.updateTaskToDone(taskId, userName, projectId, userStoryId);
            return new ResponseEntity<>(taskUpdatedId, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
