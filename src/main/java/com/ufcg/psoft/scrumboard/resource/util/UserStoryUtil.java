package com.ufcg.psoft.scrumboard.resource.util;

import com.ufcg.psoft.scrumboard.exception.UserStoryNotFoundException;
import com.ufcg.psoft.scrumboard.models.entities.Project;
import com.ufcg.psoft.scrumboard.models.entities.userStories.UserStory;
import com.ufcg.psoft.scrumboard.repository.UserStoryRepository;
import com.ufcg.psoft.scrumboard.resource.enums.StateUserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserStoryUtil {

    @Autowired
    private UserStoryRepository userStoryRepository;

    public UserStory getUserStory(String userStoryId) throws UserStoryNotFoundException {
        UserStory userStory = userStoryRepository.getUserStoryByID(userStoryId);
        if(userStory == null) throw new UserStoryNotFoundException("UserStory não encontrada!");
        return userStory;
    }

    public double[] countUserStorysOfUserInProject(String userName, Project project){
        double[] count = new double[5];
        for(UserStory userStory: project.getUserStories().keySet()){
            if (userStory.getUsers().containsKey(userName)) {
                count[0]++;
                if(userStory.getState().getState().equals(StateUserStory.TODO.getState())){count[1]++;}
                else if(userStory.getState().getState().equals(StateUserStory.WIP.getState())){count[2]++;}
                else if(userStory.getState().getState().equals(StateUserStory.TO_VERIFY.getState())){count[3]++;}
                else{count[4]++;}
            }
        }
        return count;
    }

    public double[] getTotalUserStorysInEstates(Project project){
        double[] count = new double[4];
        for(UserStory userStory: project.getUserStories().keySet()){
            if(userStory.getState().getState().equals(StateUserStory.TODO.getState())){count[0]++;}
            else if(userStory.getState().getState().equals(StateUserStory.WIP.getState())){count[1]++;}
            else if(userStory.getState().getState().equals(StateUserStory.TO_VERIFY.getState())){count[2]++;}
            else{count[3]++;}
        }
        return count;
    }
}
