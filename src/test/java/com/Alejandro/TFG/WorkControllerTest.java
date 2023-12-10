package com.Alejandro.TFG;

import com.Alejandro.TFG.controller.WorkController;
import com.Alejandro.TFG.model.Work;
import com.Alejandro.TFG.Service.WorkService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WorkControllerTest {

    @InjectMocks
    private WorkController workController;

    @Mock
    private WorkService workService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllWorks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/work/")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetWorkById() throws Exception {
        Long workId = 1L;

         mockMvc.perform(MockMvcRequestBuilders.get("/api/work/{id}", workId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateWork() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api//")  // <-- Ajusta la URL aquí
        .content("{\"task\":\"{\"id\":2}\",\"workCheckBox\":\"null\}")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testDeleteWork() throws Exception {
        Long workId = 1L;

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/works/{id}", workId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verificar que el servicio se llamó correctamente
        verify(workService, times(1)).deleteWork(workId);
    }
}
