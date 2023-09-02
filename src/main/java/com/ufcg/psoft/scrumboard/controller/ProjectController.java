package com.ufcg.psoft.scrumboard.controller;

import com.ufcg.psoft.scrumboard.dto.AssociationProjectDTO;
import com.ufcg.psoft.scrumboard.dto.ProjectDTO;
import com.ufcg.psoft.scrumboard.dto.ProjectUpdateDTO;
import com.ufcg.psoft.scrumboard.exception.ProjectNotFoundException;
import com.ufcg.psoft.scrumboard.exception.UserAlreayInProjectException;
import com.ufcg.psoft.scrumboard.exception.user.RoleNotValidException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotFoundException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotInProjectException;
import com.ufcg.psoft.scrumboard.models.responses.ProjectResponse;
import com.ufcg.psoft.scrumboard.models.responses.UserStoryResponse;
import com.ufcg.psoft.scrumboard.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping()
    public ResponseEntity<?> createNewProject(@RequestBody ProjectDTO projectDTO, @RequestParam String userName) {
        try {
            String projectId = this.projectService.addProject(projectDTO, userName);
            return new ResponseEntity<>("Projeto " + projectId + " criado com sucesso!", HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{projectId}/")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId, @RequestParam String userName) {
        try {
            ProjectResponse project = this.projectService.getProjectById(projectId, userName);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (ProjectNotFoundException | UserNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("{projectId}/")
    public ResponseEntity<?> updateProject(
            @PathVariable String projectId,
            @RequestBody ProjectUpdateDTO projectDTO,
            @RequestParam String userName) {
        try {
            String projectUpdatedId = this.projectService.updateProject(projectId, userName, projectDTO);
            return new ResponseEntity<>("Projeto " + projectUpdatedId + " atualizado com sucesso!", HttpStatus.ACCEPTED);
        } catch (ProjectNotFoundException | UserNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{projectId}/")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId, @RequestParam String userName) {
        try {
            this.projectService.deleteProject(projectId, userName);
            return new ResponseEntity<>("Projeto deletado com sucesso", HttpStatus.ACCEPTED);
        } catch (ProjectNotFoundException | UserNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("roles/")
    public ResponseEntity<?> getRolesAvailableForRegistration(){
        return new ResponseEntity<>(projectService.getAllRolesAvailables(), HttpStatus.OK);
    }

    @PutMapping("add-association/{projectId}/")
    public ResponseEntity<?> associationUserProject(
            @PathVariable String projectId,
            @RequestParam String userName,
            @RequestBody AssociationProjectDTO associtionProjectDTO
            ) {
        try {
            this.projectService.associationUserProject(projectId, userName, associtionProjectDTO);

            return new ResponseEntity<>("Usuario " + associtionProjectDTO.getAssociationName() +
                    " associado ao projeto " + projectId, HttpStatus.ACCEPTED);

        } catch (ProjectNotFoundException | UserNotFoundException | RoleNotValidException |
                 UserAlreayInProjectException exception) {

            return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("user-stories/{projectId}")
    public ResponseEntity<?> getAllUserStoriesInProject(@PathVariable String projectId, @RequestParam String userName) {
        try {
            List<UserStoryResponse> uSResponseList = this.projectService.getAllUserStoriesInProject(projectId, userName);
            return new ResponseEntity<>(uSResponseList, HttpStatus.OK);
        } catch (UserNotFoundException | ProjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("report-user/{projectId}/{userName}/")
    public ResponseEntity<?> getUserReport(@RequestParam String userName, @PathVariable String projectId){
        try{
            String report = this.projectService.getUserReport(userName, projectId);
            return new ResponseEntity<String>(report, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @GetMapping("report-productOwner/{projectId}/{userName}/")
    public ResponseEntity<?> getProductOwnerReport(@RequestParam String userName, @PathVariable String projectId){
        try{
            String report = this.projectService.getProductOwnerReport(userName, projectId);
            return new ResponseEntity<String>(report, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @GetMapping("report-scrumMaster/{projectId}/{userName}/")
    public ResponseEntity<?> getScrumMasterReport(@RequestParam String userName, @PathVariable String projectId){
        try{
            String report = this.projectService.getScrumMasterReport(userName, projectId);
            return new ResponseEntity<String>(report, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}