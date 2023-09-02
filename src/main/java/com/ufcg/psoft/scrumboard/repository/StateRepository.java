package com.ufcg.psoft.scrumboard.repository;

import com.ufcg.psoft.scrumboard.models.entities.userStories.Done;
import com.ufcg.psoft.scrumboard.models.entities.userStories.State;
import com.ufcg.psoft.scrumboard.models.entities.userStories.ToVerify;
import com.ufcg.psoft.scrumboard.models.entities.userStories.WorkInProgress;
import com.ufcg.psoft.scrumboard.resource.enums.StateUserStory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StateRepository {

    private final Map<String, State> userStoryState = new HashMap<>();

    private void setUserStoryState() {
        userStoryState.put(StateUserStory.WIP.getState(), new WorkInProgress());
        userStoryState.put(StateUserStory.TO_VERIFY.getState(), new ToVerify());
        userStoryState.put(StateUserStory.DONE.getState(), new Done());
    }

    public State getUsersState(String state) {
        setUserStoryState();
        return userStoryState.get(state);
    }
}
