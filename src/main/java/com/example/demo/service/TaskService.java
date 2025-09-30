package com.example.demo.service;

import com.example.demo.dto.model.TaskDto;
import com.example.demo.dto.request.task.CreateTaskRequest;
import com.example.demo.dto.request.task.UpdateTaskRequest;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<TaskDto> createTask(CreateTaskRequest request, User user);
    Optional<TaskDto> updateTask(Long id, UpdateTaskRequest request);
    Optional<TaskDto> getTask(Long id);
    List<TaskDto> getAllTasks();
    void deleteTask(Long id);
}
