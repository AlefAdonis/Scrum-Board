package com.ufcg.psoft.scrumboard.models.responses;

import com.ufcg.psoft.scrumboard.models.entities.Task;


import java.util.Map;

public class UserStoryResponse {

    private Map<String, Task> tasks;
    private String description;
    private String title;
    private String state;

    public UserStoryResponse(Map<String, Task> tasks, String description, String title, String state) {
        this.tasks = tasks;
        this.description = description;
        this.title = title;
        this.state = state;
    }

    public Map<String, Task> getTasks() {
        return tasks;
    }

    public String getDescription() {
        return description;
    }


    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

}
