package com.example.demo.service;

import com.example.demo.dto.model.TaskDto;
import com.example.demo.dto.request.task.CreateTaskRequest;
import com.example.demo.dto.request.task.UpdateTaskRequest;

import java.util.Set;

public interface TaskService {
    TaskDto createTask(CreateTaskRequest request);
    TaskDto updateTask(Long id, UpdateTaskRequest request);
    TaskDto getTask(Long id);
    Set<TaskDto> getAllTasks();
    void deleteTask(Long id);
}
