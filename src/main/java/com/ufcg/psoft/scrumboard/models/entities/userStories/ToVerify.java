package com.ufcg.psoft.scrumboard.models.entities.userStories;

import com.ufcg.psoft.scrumboard.resource.enums.StateUserStory;

import java.util.Objects;

public class ToVerify implements State {


    private UserStory userStory;


    public void setContextUS(UserStory userStory) {
        this.userStory = userStory;
    }

    @Override
    public String getState() {
        return StateUserStory.TO_VERIFY.getState();
    }

    @Override
    public void nextState() {
        if(Objects.equals(this.userStory.getStateToString(), StateUserStory.TO_VERIFY.getState())){

            State newState = new Done();
            this.userStory.changeState(newState);
        }
    }
}
