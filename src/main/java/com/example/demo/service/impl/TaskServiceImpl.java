package com.example.demo.service.impl;

import com.example.demo.dto.model.TaskDto;
import com.example.demo.dto.request.task.CreateTaskRequest;
import com.example.demo.dto.request.task.UpdateTaskRequest;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskDto getTask(Long id) {
        return taskRepository.findById(id).stream().map(TaskDto::new).findFirst().orElse(null);
    };

    @Override
    public Set<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskDto::new).collect(Collectors.toSet());
    };

    @Override
    public TaskDto createTask(CreateTaskRequest request) {
        Task task = new Task()
            .setTitle(request.getTitle())
            .setDescription(request.getDescription());
        
        Task savedTask = taskRepository.save(task);
        
        return savedTask != null ? new TaskDto(savedTask) : null;
    }

    @Override
    public TaskDto updateTask(Long id, UpdateTaskRequest request) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setTitle(request.getTitle());
            existingTask.setDescription(request.getDescription());
            Task updatedTask = taskRepository.save(existingTask);
            return new TaskDto(updatedTask);
        }).orElse(null);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}