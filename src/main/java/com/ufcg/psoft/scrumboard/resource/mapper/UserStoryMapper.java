package com.ufcg.psoft.scrumboard.resource.mapper;

import com.ufcg.psoft.scrumboard.models.entities.userStories.State;
import com.ufcg.psoft.scrumboard.models.entities.userStories.UserStory;
import com.ufcg.psoft.scrumboard.models.responses.UserStoryResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserStoryMapper {

    public UserStoryResponse userStoryToResponse(UserStory userStory) {
        return new UserStoryResponse(userStory.getTasks(),
                userStory.getDescription(), userStory.getTitle(), userStory.getStateToString());
    }

    public List<UserStoryResponse> userStoryResponseList(HashMap<UserStory, State> userStoryMap) {
        Set<UserStory> userStorySet = userStoryMap.keySet();

        return userStorySet.stream().map(this::userStoryToResponse).collect(Collectors.toList());
    }

}
