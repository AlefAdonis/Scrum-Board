package com.ufcg.psoft.scrumboard.models.entities.userStories;

import com.ufcg.psoft.scrumboard.models.entities.Task;
import com.ufcg.psoft.scrumboard.models.entities.users.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserStory {

    private String id; //Retirado o final para testes
    private Map<String, Task> tasks;
    private Map<String, User> users;
    private String description;
    private String title;
    private State state;

    public UserStory(String description, String title) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.title = title;
        this.tasks = new HashMap<>();
        this.users = new HashMap<>();
        Todo initialState = new Todo();
        initialState.setContextUS(this);
        this.state = initialState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;} //criado para testes

    public Map<String, Task> getTasks() {
        return tasks;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public State getState() {
        return state;
    }

    public String getStateToString() {
        return state.getState();
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void setTask(String id, Task task) {
        this.tasks.put(id, task);
    }
}
