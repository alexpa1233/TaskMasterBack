package com.Alejandro.TFG;


import com.Alejandro.TFG.controller.StepController;
import com.Alejandro.TFG.Service.StepService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StepControllerTest {

    @InjectMocks
    private StepController workCheckBoxController;

    @Mock
    private StepService workCheckBoxService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateStep() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/step/")
                .content("{\"work\":\"{\"id\":4}\",\"name\":\"test1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateStep() throws Exception {
        Long stepId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.put("/api/step/{stepId}", stepId)
                .content("{\"name\":\"Updated\",\"work\":{\"id\":4}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetWorkCheckBoxById() throws Exception {
        Long stepId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/step/{id}", stepId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAllStepsByWork() throws Exception {
        Long workId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/step/getAllByWork/{workId}", workId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteStep() throws Exception {
        Long stepId = 1L;

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/step/delete/{id}", stepId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        
    }
}
