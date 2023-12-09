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
        // Configurar el comportamiento del servicio mock
        when(workService.getAllWorks()).thenReturn(Collections.singletonList(/* Work simulado */));

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.get("/api/works")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetWorkById() throws Exception {
        Long workId = 1L;

        // Configurar el comportamiento del servicio mock
        when(workService.getWorkById(workId)).thenReturn(/* Work simulado */);

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.get("/api/works/{id}", workId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateWork() throws Exception {
        // Configurar el comportamiento del servicio mock
        when(workService.saveWork(any(Work.class))).thenReturn(/* Work simulado */);

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.post("/api/works")
                .content("{\"id\":1,\"workCheckBox\":[],\"task\":{\"id\":1}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
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
