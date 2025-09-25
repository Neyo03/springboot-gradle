package com.example.demo.controller;

import com.example.demo.dto.model.TaskDto;
import com.example.demo.dto.request.task.CreateTaskRequest;
import com.example.demo.dto.request.task.UpdateTaskRequest;
import com.example.demo.dto.response.Response;
import com.example.demo.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(@Autowired TaskService taskService) {
        this.taskService = taskService;
    }
    

    @GetMapping
    public ResponseEntity<Response<List<TaskDto>>> getAllTasks() {
        try {
            List<TaskDto> tasks = taskService.getAllTasks().stream().toList();
            return ResponseEntity.ok(Response.<List<TaskDto>>ok().setPayload(tasks));
        } catch (Exception e) {
           return ResponseEntity.internalServerError().body(Response.<List<TaskDto>>exception().setErrors(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Response<TaskDto>> createTask(@RequestBody CreateTaskRequest request) {
        try {
            TaskDto createdTask = taskService.createTask(request).map(task -> task).orElse(null);
            return ResponseEntity.ok((Response.<TaskDto>ok().setPayload(createdTask)));
        } catch (Exception e) {
           return ResponseEntity.internalServerError().body(Response.<TaskDto>exception().setErrors(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<TaskDto>> getTask(@PathVariable Long id) {
        return taskService.getTask(id)
            .map(task -> ResponseEntity.ok(Response.<TaskDto>ok().setPayload(task)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.<TaskDto>notFound().setErrors("Task not found"))
            );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Response<TaskDto>> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        try {
            return taskService.updateTask(id, request)
                .map(task -> ResponseEntity.ok(Response.<TaskDto>ok().setPayload(task)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.<TaskDto>notFound().setErrors("Task not found")));
        } catch (Exception e) {
           return ResponseEntity.internalServerError().body(Response.<TaskDto>exception().setErrors(e.getMessage()));
        }
    }   
}