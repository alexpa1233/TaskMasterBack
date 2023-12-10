package com.Alejandro.TFG;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testCreateTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/task/")  // <-- Ajusta la URL aquí
                .content("{\"title\":\"Test\",\"description\":\"Test Description\",\"type\":\"SOCIAL\",\"user\":{\"id\":2},\"social\":null,\"work\":null}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetTaskById() throws Exception {
        Long taskId = 2L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/{taskId}", taskId)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllTasks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    

    @Test
    public void testUpdateTask() throws Exception {
        Long taskId = 2L;
        mockMvc.perform(MockMvcRequestBuilders.put("/api/task/{taskId}", taskId)
                    .content("{\"title\":\"Updated Title\",\"description\":\"Updated Description\",\"type\":\"WORK\",\"user\":{\"id\":2},\"social\":null,\"work\":null}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteTask() throws Exception {
        Long taskId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/task/{taskId}", taskId)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testSearchTasksByKeyword() throws Exception {
        String keyword = "test";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/search?keyword={keyword}", keyword)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllTasksByUser() throws Exception {
        Long userId = 2L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/user/{userId}", userId)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSendNotifications() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/task/send-notifications")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
