package com.example.demo.dto.model;
import com.example.demo.model.User;

public class UserDto {
    private Long id;
    private String username;
    

    public UserDto() {}

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}