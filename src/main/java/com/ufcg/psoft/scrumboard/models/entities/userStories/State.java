package com.ufcg.psoft.scrumboard.models.entities.userStories;

public interface State {

    String getState();

    void nextState();
}
