package com.ufcg.psoft.scrumboard.repository;

import com.ufcg.psoft.scrumboard.models.entities.users.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Repository
public class UserRepository {
    private final Map<String, User> userMap;

    public UserRepository(){
        this.userMap = new HashMap<String, User>();
    }

    public String addUser(User user) {
        this.userMap.put(user.getUserName(), user);
        return user.getUserName();
    }

    public User getUserByUserName(String userName){
        return this.userMap.get(userName);
    }

    public String updateUser(User user){
        this.userMap.replace(user.getUserName(), user);
        return user.getUserName();
    }

    public void deleteUser(String userName){
        this.userMap.remove(userName);
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<User>(this.userMap.values());
    }

}
