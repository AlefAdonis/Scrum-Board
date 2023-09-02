package com.ufcg.psoft.scrumboard.controller;

import com.ufcg.psoft.scrumboard.dto.UserStoryDTO;
import com.ufcg.psoft.scrumboard.exception.NotChangeUserStory;
import com.ufcg.psoft.scrumboard.exception.ProjectNotFoundException;
import com.ufcg.psoft.scrumboard.exception.UserStoryNotFoundException;
import com.ufcg.psoft.scrumboard.exception.user.RoleNotValidException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotFoundException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotInProjectException;
import com.ufcg.psoft.scrumboard.models.entities.userStories.UserStory;
import com.ufcg.psoft.scrumboard.models.responses.UserStoryResponse;
import com.ufcg.psoft.scrumboard.resource.enums.StateUserStory;
import com.ufcg.psoft.scrumboard.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user-story/")
public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;

    @PostMapping("{projectId}/")
    public ResponseEntity<?> createUserStory(
            @PathVariable String projectId,
            @RequestParam String userName,
            @RequestBody UserStoryDTO userStoryDTO) {
        try {
            String userStoryId = userStoryService.saveNewUserStory(projectId, userName, userStoryDTO);
            return new ResponseEntity<>(userStoryId, HttpStatus.CREATED);
        } catch (UserNotFoundException | ProjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("{projectId}/{userStoryId}/")
    public ResponseEntity<String> removeUserStory(
            @PathVariable String projectId,
            @PathVariable String userStoryId,
            @RequestParam String userName) {
        try {
        	userStoryService.removeUserStory(projectId, userStoryId, userName);
            return new ResponseEntity<>("UserStory removido com sucesso", HttpStatus.ACCEPTED);
        } catch (UserStoryNotFoundException | ProjectNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("{projectId}/{userStoryId}/")
    public ResponseEntity<String> updateUserStory(
            @PathVariable String projectId,
            @PathVariable String userStoryId,
            @RequestParam String userName,
            @RequestBody UserStoryDTO userStoryDTO) {
        try {
        	String userStoryUpdatedId = userStoryService.updateUserStory(projectId, userStoryId, userName, userStoryDTO);
            return new ResponseEntity<>(userStoryUpdatedId, HttpStatus.ACCEPTED);
        } catch (UserStoryNotFoundException | ProjectNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("{projectId}/{userStoryId}/")
    public ResponseEntity<?> getUserStoryById(
            @PathVariable String projectId,
            @PathVariable String userStoryId,
            @RequestParam String userName) {
        try {
        	UserStoryResponse userStory = userStoryService.getUserStoryResponseById(projectId, userStoryId, userName);
            return new ResponseEntity<>(userStory, HttpStatus.OK);
        } catch (UserStoryNotFoundException | UserNotFoundException | ProjectNotFoundException  e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("states/{projectId}")
    public ResponseEntity<?> getAllState(@PathVariable String projectId, @RequestParam String userName) {
        try {
            StateUserStory[] response = userStoryService.getAllStates(projectId, userName);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFoundException | ProjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("join/{userStoryId}/")
    public ResponseEntity<String> joinInUserStory(@PathVariable String userStoryId, @RequestParam String userName) {
        try{
            this.userStoryService.joinInUserStory(userStoryId, userName);
            return new ResponseEntity<>("Usuario com userName: " + userName + ", adicionado na UserStory com ID: " +"\n " + userStoryId, HttpStatus.ACCEPTED);
        } catch (UserStoryNotFoundException | UserNotInProjectException | RoleNotValidException e){
        	 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }

    @PutMapping("add-user/{userStoryId}/")
    public ResponseEntity<String> addUserToUserStory(@PathVariable String userStoryId, @RequestParam String scrumUserName, @RequestParam String userName){
        try{
            this.userStoryService.addUserToUserStory(userStoryId, scrumUserName, userName);
            return new ResponseEntity<>("Usuario com userName: " + userName + ", adicionado na UserStory com ID: " +"\n " + userStoryId, HttpStatus.ACCEPTED);
        } catch (Exception e){
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }
    
    @PutMapping("move-state/wip-to-verify/{projectId}/{userStoryId}/")
    public ResponseEntity<String> WorkInProgresschangeToVerify(
            @PathVariable String projectId,
            @PathVariable String userStoryId,
            @RequestParam String userName){
        try {
        	userStoryService.WorkInProgressChangeToVerify(projectId, userStoryId, userName);
            return new ResponseEntity<>("UserStory movida para o estágio ToVerify", HttpStatus.ACCEPTED);
        } catch (UserStoryNotFoundException | ProjectNotFoundException | UserNotFoundException | NotChangeUserStory e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("move-state/verify-to-done/{projectId}/{userStoryId}/")
    public ResponseEntity<?> toVerifyChangeToDone(@PathVariable String userStoryId, @PathVariable String projectId,
                                           @RequestParam String username) {
        try {
            userStoryService.toVerifyChangeDone(projectId,userStoryId, username);
            return new ResponseEntity<>("User Story atualizada com sucesso!", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("subscribe/{projectId}/{userStoryId}/")
    public ResponseEntity<?> subscribeToUserStory(@PathVariable String projectId, @PathVariable String userStoryId,
                                                  @RequestParam String username){
        try {
            userStoryService.subscribeUserToUserStory(projectId, userStoryId, username);
            return new ResponseEntity<>("Usuario " + username + " inscrito nas notificaçoes.", HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}