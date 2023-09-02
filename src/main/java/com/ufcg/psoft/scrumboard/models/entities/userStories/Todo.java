package com.ufcg.psoft.scrumboard.models.entities.userStories;

import com.ufcg.psoft.scrumboard.resource.enums.StateUserStory;

import java.util.Objects;

public class Todo implements State {

    private UserStory userStory;


    public void setContextUS(UserStory userStory) {
        this.userStory = userStory;
    }

    @Override
    public String getState() {
        return StateUserStory.TODO.getState();
    }

    @Override
    public void nextState() {

        if(Objects.equals(this.userStory.getStateToString(), StateUserStory.TODO.getState())){

            State newState = new WorkInProgress();
            this.userStory.changeState(newState);
        }
    }
}
