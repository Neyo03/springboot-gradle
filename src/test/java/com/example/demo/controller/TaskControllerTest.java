// package com.example.demo.controller;

// import com.example.demo.dto.model.TaskDto;
// import com.example.demo.dto.request.task.CreateTaskRequest;
// import com.example.demo.service.TaskService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// import java.util.List;
// import java.util.Optional;

// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// @WebMvcTest(TaskController.class)
// class TaskControllerTest {

//     private String BASE_URL = "/api/tasks";

//     @Autowired
//     private MockMvc mockMvc;

//     @MockitoBean
//     private TaskService taskService;

//     private TaskDto task1;
//     private TaskDto task2;

//     @BeforeEach
//     void setup() {
//         task1 = new TaskDto();
//         task1.setId(1L);
//         task1.setTitle("Task 1");

//         task2 = new TaskDto();
//         task2.setId(2L);
//         task2.setTitle("Task 2");
//     }

//     @Test
//     @DisplayName("POST /api/tasks - should create task")
//     void createTask() throws Exception {
//         CreateTaskRequest req = new CreateTaskRequest();
//         req.setTitle("Test");
//         req.setDescription("desc");
//         TaskDto dto = new TaskDto();
//         dto.setId(1L);
//         dto.setTitle("Test");
//         Mockito.when(taskService.createTask(Mockito.any())).thenReturn(Optional.of(dto));
//         mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{\"title\":\"Test\",\"description\":\"desc\"}"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     @DisplayName("GET /api/tasks/{id} - should return single task")
//     void getTask() throws Exception {
//         Mockito.when(taskService.getTask(1L)).thenReturn(Optional.of(task1));
//         mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/1"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.payload.id").value(1))
//                 .andExpect(jsonPath("$.payload.title").value("Task 1"))
//                 .andDo(print());
//     }

//     @Test
//     @DisplayName("GET /api/tasks/{id} - should return 404 when task not found")
//     void getTaskNotFound() throws Exception {
//         Mockito.when(taskService.getTask(10L)).thenReturn(Optional.empty());

//         mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/10"))
//                 .andExpect(status().isNotFound())
//                 .andExpect(jsonPath("$.errors").value("Task not found"))
//                 .andDo(print());
//     }
    
//     @Test
//     @DisplayName("PUT /api/tasks/{id} - update task")
//     void updateTask() throws Exception {
//         TaskDto dto = new TaskDto();
//         dto.setId(1L);
//         Mockito.when(taskService.updateTask(Mockito.eq(1L), Mockito.any())).thenReturn(Optional.of(dto));
//         mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     @DisplayName("PUT /api/tasks/{id} - update task not found")
//     void updateTaskNotFound() throws Exception {
//         TaskDto dto = new TaskDto();
//         dto.setId(1L);
//         Mockito.when(taskService.updateTask(Mockito.eq(10L), Mockito.any())).thenReturn(Optional.empty());
//         mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/10")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isNotFound());
//     }

//     @Test
//     @DisplayName("GET /api/tasks - should return tasks list")
//     void getAllTasks() throws Exception {
//         Mockito.when(taskService.getAllTasks()).thenReturn(List.of(task1, task2));

//         mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.payload.length()").value(2))
//                 .andExpect(jsonPath("$.payload[0].id").value(1))
//                 .andExpect(jsonPath("$.payload[0].title").value("Task 1"))
//                 .andExpect(jsonPath("$.payload[1].id").value(2))
//                 .andExpect(jsonPath("$.payload[1].title").value("Task 2"))
//                 .andDo(print());
//     }
// }
