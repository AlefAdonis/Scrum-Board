package com.ufcg.psoft.scrumboard.resource.enums;

public enum StateUserStory {
    TODO("TODO"),
    WIP("Work in Progress"),
    TO_VERIFY("To Verify"),
    DONE("Done");
    private final String state;

    StateUserStory(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
