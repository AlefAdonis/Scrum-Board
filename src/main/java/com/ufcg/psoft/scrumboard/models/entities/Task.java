package com.ufcg.psoft.scrumboard.models.entities;

import java.util.UUID;

public class Task {
    private final String id;

    private String description;

    private boolean status;

    public Task(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.status = false;
    }

    public String getId() {
        return id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
