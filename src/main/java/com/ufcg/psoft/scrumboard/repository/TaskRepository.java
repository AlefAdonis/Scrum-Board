package com.ufcg.psoft.scrumboard.repository;

import com.ufcg.psoft.scrumboard.models.entities.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TaskRepository {
    private final Map<String, Task> taskMap;

    public TaskRepository() {
        this.taskMap = new HashMap<>();
    }

    public String addTask(Task task) {
        this.taskMap.put(task.getId(), task);
        return task.getId();
    }

    public Collection<Task> getAllTask() {
        return this.taskMap.values();
    }

    public Task getTaskByID(String id) {
        return this.taskMap.get(id);
    }
    public String updateTask(Task task) {
        this.taskMap.replace(task.getId(), task);
        return task.getId();
    }

    public void removeTask(String id) {
        this.taskMap.remove(id);
    }

}
