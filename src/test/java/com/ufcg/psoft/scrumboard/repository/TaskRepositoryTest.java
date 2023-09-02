package com.ufcg.psoft.scrumboard.repository;

import com.ufcg.psoft.scrumboard.models.entities.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryTest {

    protected TaskRepository taskRepository;

    @BeforeEach
    protected void setUp(){
        this.taskRepository = new TaskRepository();
    }

    protected String setUp2(){
        Task task = new Task("updateTask test");
        this.taskRepository.addTask(task);
        return task.getId();
    }

    @Test
    void addTask() {
        Task task = new Task("addTask and getTaskByID tests...");
        this.taskRepository.addTask(task);
        assertEquals(task, this.taskRepository.getTaskByID(task.getId()));
    }

    @Test
    void getAllTask() {
        Task[] tasks = new Task[3];
        Task task = new Task("getAllTask test 1");
        Task task2 = new Task("getAllTask test 2");
        Task task3 = new Task("getAllTask test 3");

        tasks[0] = task;
        tasks[1] = task2;
        tasks[2] = task3;

        for(Task t: tasks){this.taskRepository.addTask(t);}

        int contador = 0;
        for(Task t: this.taskRepository.getAllTask()){contador++;}

        assertEquals(tasks.length, contador);
    }

    @Test
    void updateTask() {
        String ID = setUp2();
        Task task = this.taskRepository.getTaskByID(ID);
        task.setDescription("task atualizada!");

        assertEquals(task.getId(), this.taskRepository.updateTask(task));
        assertEquals("task atualizada!", this.taskRepository.getTaskByID(ID).getDescription());

        boolean verificador = true;
        int size = 0;
        for(Task t: this.taskRepository.getAllTask()){
            size++;
            if(size == 2){verificador = false;}
        }
        assertTrue(verificador);
    }

    @Test
    void removeTask() {
        String ID = setUp2();

        assertSame(this.taskRepository.getTaskByID(ID), this.taskRepository.getTaskByID(ID));
        this.taskRepository.removeTask(ID);
        assertNull(this.taskRepository.getTaskByID(ID));
    }
}