package com.example.demo.dto.model;

import com.example.demo.model.Task;
import com.example.demo.model.embeds.Timestamps;
import com.example.demo.model.enums.TaskStatus;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status = TaskStatus.TODO;
    private Timestamps timestamps;

    public TaskDto() {}

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.timestamps = task.getTimestamps();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public Timestamps getTimestamps() { return timestamps; }
    public void setTimestamps(Timestamps timestamps) { this.timestamps = timestamps; }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", timestamps=" + timestamps +
                '}';
    }
}