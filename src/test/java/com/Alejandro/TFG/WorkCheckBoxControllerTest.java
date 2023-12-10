package com.Alejandro.TFG;


import com.Alejandro.TFG.controller.WorkCheckBoxController;
import com.Alejandro.TFG.model.WorkCheckBox;
import com.Alejandro.TFG.Service.WorkCheckBoxService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WorkCheckBoxControllerTest {

    @InjectMocks
    private WorkCheckBoxController workCheckBoxController;

    @Mock
    private WorkCheckBoxService workCheckBoxService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateWorkCheckBox() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/social/create")
                .content("{\"workCheckBox\":\"null\",\"task\":{\"id\":2}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateWorkCheckBox() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/workCheckBox/update")
                .content("{\"id\":1,\"name\":\"Updated\",\"work\":{\"id\":1}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetWorkCheckBoxById() throws Exception {
        Long workCheckBoxId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/workCheckBox/{id}", workCheckBoxId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAllCheckBoxByWork() throws Exception {
        Long workId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/workCheckBox/getAllByWork/{workId}", workId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteLabel() throws Exception {
        Long workCheckBoxId = 1L;

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/workCheckBox/delete/{id}", workCheckBoxId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        
    }
}
