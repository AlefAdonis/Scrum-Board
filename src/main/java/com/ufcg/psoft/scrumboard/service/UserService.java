package com.ufcg.psoft.scrumboard.service;

import com.ufcg.psoft.scrumboard.dto.UserDTO;
import com.ufcg.psoft.scrumboard.dto.UserUpdateDTO;
import com.ufcg.psoft.scrumboard.exception.user.EmailNotValidException;
import com.ufcg.psoft.scrumboard.exception.user.UserNotFoundException;
import com.ufcg.psoft.scrumboard.models.entities.users.User;
import com.ufcg.psoft.scrumboard.models.responses.UserResponse;
import com.ufcg.psoft.scrumboard.repository.UserRepository;
import com.ufcg.psoft.scrumboard.resource.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private UserUtil userUtil;

    public String addUser(UserDTO userDTO) throws UserNotFoundException, EmailNotValidException {
        User newUser = this.userRepository.getUserByUserName(userDTO.getUserName());

        if(newUser == null) {
            if(verifyUserEmail(userDTO.getEmail())){
                throw new EmailNotValidException("Email não é válido");
            }
            newUser = new User(userDTO.getFullName(), userDTO.getUserName(), userDTO.getEmail());
            return userRepository.addUser(newUser);
        }

        throw new UserNotFoundException("User name já existente!");
    }

    private boolean verifyUserEmail(String email){
        ArrayList<User> users = this.userRepository.getAllUsers();

        for(User user: users){
            if(user.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }

        return false;
    }
    public String updateUser(String userName, UserUpdateDTO userDTO) throws UserNotFoundException, EmailNotValidException {
        User user = this.userUtil.verifyUser(userName);
        if(verifyUserEmail(userDTO.getEmail())){
            throw new EmailNotValidException("Email não é válido!");
        }
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());

        return this.userRepository.updateUser(user);
    }

    public UserResponse getUserByUserName(String userName) throws UserNotFoundException {
        User user = this.userUtil.verifyUser(userName);
        return getUserResponse(user);
    }

    private static UserResponse getUserResponse(User user) {
        return new UserResponse(user.getFullName(), user.getUserName(), user.getEmail());
    }

    public void deleteUser(String userName) throws UserNotFoundException{
        this.userUtil.verifyUser(userName);
        this.userRepository.deleteUser(userName);
    }
}
