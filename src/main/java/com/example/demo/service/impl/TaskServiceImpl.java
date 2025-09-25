package com.example.demo.service.impl;

import com.example.demo.dto.model.TaskDto;
import com.example.demo.dto.request.task.CreateTaskRequest;
import com.example.demo.dto.request.task.UpdateTaskRequest;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(@Autowired TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<TaskDto> getTask(Long id) {
        return taskRepository.findById(id).map(TaskDto::new);
    };


    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
    };

    @Override
    public Optional<TaskDto> createTask(CreateTaskRequest request) {
        Task task = new Task()
            .setTitle(request.getTitle())
            .setDescription(request.getDescription());
        Task savedTask = taskRepository.save(task);
        return Optional.of(new TaskDto(savedTask));
    }

   @Override
    public Optional<TaskDto> updateTask(Long id, UpdateTaskRequest request) {
        return taskRepository.findById(id).map(existingTask -> {
            Task updatedTask = taskRepository.save(existingTask);
            return new TaskDto(updatedTask);
        });
    }   

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}