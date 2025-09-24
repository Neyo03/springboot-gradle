package com.example.demo.controller.task;

import com.example.demo.dto.model.TaskDto;
import com.example.demo.dto.request.task.CreateTaskRequest;
import com.example.demo.dto.request.task.UpdateTaskRequest;
import com.example.demo.dto.response.Response;
import com.example.demo.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Response<List<TaskDto>> getAllTasks() {
        try {
            if (taskService.getAllTasks().isEmpty()) {
                return Response.noContent();
            }
            List<TaskDto> tasks = taskService.getAllTasks().stream().toList();
       
            return Response.<List<TaskDto>>ok().setPayload(tasks);
        } catch (Exception e) {
           return Response.<List<TaskDto>>exception().setErrors(e.getMessage());
        }
    }

    @PostMapping
    public Response<TaskDto> createTask(@RequestBody CreateTaskRequest request) {
        try {
            TaskDto createdTask = taskService.createTask(request);
            return Response.<TaskDto>ok().setPayload(createdTask);
        } catch (Exception e) {
           return Response.<TaskDto>exception().setErrors(e.getMessage());
        }
       
    }

    @GetMapping("/{id}")
    public Response<TaskDto> getTask(@PathVariable Long id) {
        try {
            TaskDto task = taskService.getTask(id);
            System.err.println("Fetched Task: " + task); // Debugging line
            if (task == null) {
                return Response.notFound();
            }
            
            return Response.<TaskDto>ok().setPayload(task);
        } catch (Exception e) {
           return Response.<TaskDto>exception().setErrors(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public Response<TaskDto> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        try {
            TaskDto updatedTask = taskService.updateTask(id, request);
     
            if (updatedTask == null) {
                return Response.notFound();
            }

            return Response.<TaskDto>ok().setPayload(updatedTask);
        } catch (Exception e) {
           return Response.<TaskDto>exception().setErrors(e.getMessage());
        }
    }   
}