package com.ufcg.psoft.scrumboard.models.entities.userStories;

import com.ufcg.psoft.scrumboard.resource.enums.StateUserStory;

import java.util.Objects;

public class WorkInProgress implements State {

    private UserStory userStory;


    public void setContextUS(UserStory userStory) {
        this.userStory = userStory;
    }

    @Override
    public String getState() {
        return StateUserStory.WIP.getState();
    }

    @Override
    public void nextState() {
        if(Objects.equals(this.userStory.getStateToString(), StateUserStory.WIP.getState())){

            State newState = new ToVerify();
            this.userStory.changeState(newState);
        }
    }
}
