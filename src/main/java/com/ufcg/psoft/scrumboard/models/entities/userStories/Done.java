package com.ufcg.psoft.scrumboard.models.entities.userStories;

import com.ufcg.psoft.scrumboard.resource.enums.StateUserStory;

public class Done implements State {

    private UserStory userStory;

    public void setContextUS(UserStory userStory) {
        this.userStory = userStory;
    }

    @Override
    public String getState() {
        return StateUserStory.DONE.getState();
    }

    @Override
    public void nextState() {
    }
}
