package com.xpto.sales_score_api.controller;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.xpto.sales_score_api.model.Salesperson;
import com.xpto.sales_score_api.repo.SalespersonRepository;
import com.xpto.sales_score_api.service.SalespersonService;

@WebMvcTest(SalespersonController.class)
@AutoConfigureMockMvc
public class SalespersonControllerIntegrationTest {

    @MockBean
    private SalespersonRepository salespersonRepository;

    @MockBean
    private SalespersonService salespersonService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetSalespersonById_thenReturnSalesperson() throws Exception {
        Long salespersonId = 1L;
        Salesperson mockSalesperson = new Salesperson();
        mockSalesperson.setName("Eric");
        mockSalesperson.setRegistration("bb23423");

        Mockito.when(salespersonRepository.findById(salespersonId)).thenReturn(Optional.of(mockSalesperson));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/salespersons/{id}", salespersonId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Eric"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.registration").value("bb23423"));
    }

    @Test
    public void createSalesperson_withValidData_returnsCreatedStatus() throws Exception {
        Salesperson mockSalesperson = new Salesperson();
        mockSalesperson.setName("eric");
        mockSalesperson.setRegistration("aa222");

        Mockito.when(salespersonService.createSalesperson(Mockito.any(Salesperson.class)))
                .thenReturn(mockSalesperson);

        String salesperson = "{\"name\": \"eric\", \"registration\" : \"aa222\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/salespersons")
                .content(salesperson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPutSalesperson_thenUpdateSalesperson() throws Exception {
        Long salespersonId = 1L;
        Salesperson mockSalesperson = new Salesperson();
        mockSalesperson.setName("Eric");
        mockSalesperson.setRegistration("kkkk77");

        Salesperson updatedSalesperson = new Salesperson();
        updatedSalesperson.setName("Updated Eric");
        updatedSalesperson.setRegistration("abcdefg");

        Mockito.when(salespersonRepository.findById(salespersonId)).thenReturn(Optional.of(mockSalesperson));
        Mockito.when(salespersonRepository.save(Mockito.any(Salesperson.class))).thenReturn(updatedSalesperson);

        String updatedSalespersonJson = "{\"name\": \"Updated Eric\", \"registration\": \"abcdefg\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/salespersons/{id}", salespersonId)
                .content(updatedSalespersonJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Eric"));
    }

    @Test
    public void whenDeleteSalesperson_thenReturnOk() throws Exception {
        Long salespersonId = 1L;

        Mockito.doNothing().when(salespersonRepository).deleteById(salespersonId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/salespersons/{id}", salespersonId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void postSalesperson_withNullRegistration_returnsBadRequest() throws Exception {
        String invalidSalesperson = "{\"name\" : \"eric\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/salespersons")
                .content(invalidSalesperson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.registration").value("registration cannot be blank"));
    }

    @Test
    public void postSalesperson_withNullName_returnsBadRequest() throws Exception {
        String invalidSalesperson = "{\"registration\" : \"ffgasd\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/salespersons")
                .content(invalidSalesperson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name cannot be blank"));
    }
}
