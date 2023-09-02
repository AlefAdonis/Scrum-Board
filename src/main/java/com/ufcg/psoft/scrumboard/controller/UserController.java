package com.ufcg.psoft.scrumboard.controller;

import com.ufcg.psoft.scrumboard.dto.UserDTO;
import com.ufcg.psoft.scrumboard.dto.UserUpdateDTO;
import com.ufcg.psoft.scrumboard.exception.user.EmailNotValidException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotFoundException;
import com.ufcg.psoft.scrumboard.models.responses.UserResponse;
import com.ufcg.psoft.scrumboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO userDTO) {
        try {
            String newUser = this.userService.addUser(userDTO);
            return new ResponseEntity<>("Usuario " + newUser + " criado com sucesso!", HttpStatus.CREATED);
        } catch (UserNotFoundException|EmailNotValidException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{userName}/")
    public ResponseEntity<?> updateUser(@PathVariable String userName, @RequestBody UserUpdateDTO userDTO){
        try {
            String userUpdated = this.userService.updateUser(userName, userDTO);
            return new ResponseEntity<>("Usuario " + userUpdated + " atualizado com sucesso!",
                    HttpStatus.ACCEPTED);
        } catch (UserNotFoundException|EmailNotValidException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("{userName}/")
    public ResponseEntity<?> deleteUser(@PathVariable String userName){
        try{
            this.userService.deleteUser(userName);
            return new ResponseEntity<>("Usuario deletado com sucesso", HttpStatus.ACCEPTED);
        }catch(UserNotFoundException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{userName}/") 
    public ResponseEntity<?> getUserByUserName(@PathVariable String userName){
        try {
            UserResponse user = this.userService.getUserByUserName(userName);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

}
