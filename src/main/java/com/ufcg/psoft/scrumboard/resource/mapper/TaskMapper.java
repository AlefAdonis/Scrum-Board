package com.ufcg.psoft.scrumboard.resource.mapper;

import com.ufcg.psoft.scrumboard.models.entities.Task;
import com.ufcg.psoft.scrumboard.models.responses.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TaskMapper {

    public TaskResponse taskToDto(Task task) {
        return new TaskResponse(task.getDescription(), task.getStatus());
    }

    public List<TaskResponse> tasksListToDtoList(Collection<Task> tasks) {
        List<TaskResponse> taskDTOList = new ArrayList<>();
        for (Task task: tasks) {
            taskDTOList.add(taskToDto(task));
        }
        return taskDTOList;
    }
}
