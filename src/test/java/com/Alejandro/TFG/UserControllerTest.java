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
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Find_User_By_Id_Status_200() throws Exception{
        Long userId = 2L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{userId}", userId)
            
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    public void Find__All_User_Status_200() throws Exception{
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/")
            
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    public void Crete_User() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/")
            .content("{\"name\":\"Test\",\"surname\":\"Testeo\",\"username\":\"test1\",\"password\":\"test123\",\"email\":\"test@gmail.com\"}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
        
    }

    @Test
    public void Update_User() throws Exception{
        Long userId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/{userId}", userId)
            .content("{\"name\":\"Alejandro\",\"surname\":\"Paris\",\"username\":\"apl123\",\"password\":\"test123\",\"email\":\"test@gmail.com\",\"deviceId\":\"apl123-apl456\"}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void Delete_User() throws Exception{
        Long userId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/{userId}", userId)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
        
    }

    @Test
    public void Login_Correct() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
            .content("{\"username\":\"test1\",\"password\":\"test123\"}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    

    
}
