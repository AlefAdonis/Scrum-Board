package com.ufcg.psoft.scrumboard.repository;

import com.ufcg.psoft.scrumboard.models.entities.userStories.UserStory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserStoryRepository {
    private final Map<String, UserStory> userStoryMap;

    public UserStoryRepository(){
        this.userStoryMap = new HashMap<>();
    }

    public String addUserStory(UserStory userStory){
        this.userStoryMap.put(userStory.getId(), userStory);
        return userStory.getId();
    }

    public Collection<UserStory> getAllUserStories(){
    	return this.userStoryMap.values();
    }

    public UserStory getUserStoryByID(String id){
        return this.userStoryMap.get(id);
    }

    public String updateUserStory(UserStory userStory){
        this.userStoryMap.replace(userStory.getId(), userStory);
        return userStory.getId();
    }

    public void deleteUserStory(String id){
        this.userStoryMap.remove(id);
    }
}
