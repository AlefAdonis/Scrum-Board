package com.ufcg.psoft.scrumboard.models.responses;

public class TaskResponse {
    private String description;

    private boolean status;

    public TaskResponse(String description, boolean status) {
        this.description = description;
        this.status = status;
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
